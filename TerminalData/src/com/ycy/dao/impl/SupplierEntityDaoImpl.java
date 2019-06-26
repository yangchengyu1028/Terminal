package com.ycy.dao.impl;

import com.ycy.dao.SupplierEntityDao;
import com.ycy.entity.SupplierEntity;
import com.ycy.util.DBUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SupplierEntityDaoImpl implements SupplierEntityDao {
    public static Log log = LogFactory.getLog(SupplierEntityDaoImpl.class);
    @Override
    public void saveOrUpdate(List<SupplierEntity> list) {
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnZD();
            //conn.setAutoCommit(false);
            for (SupplierEntity supplierEntity : list){
                SupplierEntity supplierEntity1 = null;
                String sql1 = "select supplier_id from noer_supplier where supplier_id="+supplierEntity.getSupplier_id();
                ps1 = conn.prepareStatement(sql1);
                rs = ps1.executeQuery();
                if (rs.next()){
                    supplierEntity1 = new SupplierEntity();
                }
                if (null == supplierEntity1){
                    String sql2 = "insert into noer_supplier (supplier_id,supplier_name,mobile) values (?,?,?)";
                    ps2 = conn.prepareStatement(sql2);
                    ps2.setInt(1,supplierEntity.getSupplier_id());
                    ps2.setString(2,supplierEntity.getSupplier_name());
                    ps2.setString(3,supplierEntity.getMobile());
                }else {
                    String sql2 = "update noer_supplier set supplier_name=?,mobile=? where supplier_id="+supplierEntity.getSupplier_id();
                    ps2 = conn.prepareStatement(sql2);
                    ps2.setString(1,supplierEntity.getSupplier_name());
                    ps2.setString(2,supplierEntity.getMobile());
                }
                ps2.executeUpdate();
            }
            //conn.commit();
        }catch (SQLException e){
            log.error("更新商家数据发生异常:"+e);
        }finally {
            DBUtil.close(rs, ps1,ps2,conn);
        }
    }
}
