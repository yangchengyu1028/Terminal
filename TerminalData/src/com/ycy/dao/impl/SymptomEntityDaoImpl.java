package com.ycy.dao.impl;

import com.ycy.dao.SymptomEntityDao;
import com.ycy.entity.SymptomEntity;
import com.ycy.util.DBUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SymptomEntityDaoImpl implements SymptomEntityDao {
    public static Log log = LogFactory.getLog(SymptomEntityDaoImpl.class);
    @Override
    public void saveOrUpdate(List<SymptomEntity> list) {
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnZD();
            for (SymptomEntity symptomEntity : list){
                SymptomEntity symptomEntity1 = null;
                String sql1 = "select symptom_id from noer_symptom where symptom_id="+symptomEntity.getSymptom_id();
                ps1 = conn.prepareStatement(sql1);
                rs = ps1.executeQuery();
                if (rs.next()){
                    symptomEntity1 = new SymptomEntity();
                }
                if (null == symptomEntity1){
                    String sql2 = "insert into noer_symptom (symptom_id,symptom_name) values (?,?)";
                    ps2 = conn.prepareStatement(sql2);
                    ps2.setInt(1,symptomEntity.getSymptom_id());
                    ps2.setString(2,symptomEntity.getSymptom_name());
                }else {
                    String sql2 = "update noer_symptom set symptom_name=? where symptom_id="+symptomEntity.getSymptom_id();
                    ps2 = conn.prepareStatement(sql2);
                    ps2.setString(1,symptomEntity.getSymptom_name());
                }
                ps2.executeUpdate();
            }
        }catch (SQLException e){
            log.error("更新症状数据发生异常:"+e);
        }finally {
            DBUtil.close(rs, ps1,ps2,conn);
        }
    }

    @Override
    public List<SymptomEntity> getSymptomByYS(int num) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<SymptomEntity> list = new ArrayList<>();

        try {
            conn = DBUtil.getConnYS();
            String sql = "select cat_id,cat_name from dsc_category where is_show=1 limit "+num+",1000;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                SymptomEntity symptomEntity = new SymptomEntity();
                symptomEntity.setSymptom_id(rs.getInt("cat_id"));
                symptomEntity.setSymptom_name(rs.getString("cat_name"));
                list.add(symptomEntity);
            }
        } catch (SQLException e) {
            log.error("获取易索症状数据sql发生异常:"+e);
        }finally {
            DBUtil.close(rs,ps,conn);
        }

        return list;

    }
}
