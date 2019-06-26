package com.ycy.dao.impl;

import com.ycy.dao.BrandEntityDao;
import com.ycy.entity.BrandEntity;
import com.ycy.util.DBUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandEntityDaoImpl implements BrandEntityDao {
    public static Log log = LogFactory.getLog(BrandEntityDaoImpl.class);
    @Override
    public void saveOrUpdate(List<BrandEntity> list) {
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnZD();
            for (BrandEntity brandEntity : list){
                BrandEntity brandEntity1 = null;
                String sql1 = "select brand_id from noer_brand where brand_id="+brandEntity.getBrand_id();
                ps1 = conn.prepareStatement(sql1);
                rs = ps1.executeQuery();
                if (rs.next()){
                    brandEntity1 = new BrandEntity();
                }
                if (null == brandEntity1){
                    String sql2 = "insert into noer_brand (brand_id,brand_name,brand_letter,brand_logo) values (?,?,?,?)";
                    ps2 = conn.prepareStatement(sql2);
                    ps2.setInt(1,brandEntity.getBrand_id());
                    ps2.setString(2,brandEntity.getBrand_name());
                    ps2.setString(3,brandEntity.getBrand_letter());
                    ps2.setString(4,brandEntity.getBrand_logo());
                }else {
                    String sql2 = "update noer_brand set brand_name=?,brand_letter=?,brand_logo=? where brand_id="+brandEntity.getBrand_id();
                    ps2 = conn.prepareStatement(sql2);
                    ps2.setString(1,brandEntity.getBrand_name());
                    ps2.setString(2,brandEntity.getBrand_letter());
                    ps2.setString(3,brandEntity.getBrand_logo());
                }
                ps2.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("更新品牌数据sql发生异常:"+e);
        }finally {
            DBUtil.close(rs, ps1,ps2,conn);
        }
    }

    @Override
    public List<BrandEntity> getBrandByYS() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<BrandEntity> list = new ArrayList<>();

        try {
            conn = DBUtil.getConnYS();
            String sql = "select brand_id,brand_name,brand_letter,brand_logo from dsc_brand where brand_logo!=''";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                BrandEntity brandEntity = new BrandEntity();
                brandEntity.setBrand_id(rs.getInt("brand_id"));
                brandEntity.setBrand_name(rs.getString("brand_name"));
                brandEntity.setBrand_letter(rs.getString("brand_letter"));
                brandEntity.setBrand_logo(rs.getString("brand_logo"));

                list.add(brandEntity);
            }
        }catch (SQLException e){
            log.error("获取易索品牌数据sql发生异常:"+e);
        }finally {
            DBUtil.close(rs,ps,conn);
        }

        return list;
    }
}
