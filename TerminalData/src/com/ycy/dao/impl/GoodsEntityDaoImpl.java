package com.ycy.dao.impl;

import com.ycy.dao.GoodsEntityDao;
import com.ycy.entity.GoodsEntity;
import com.ycy.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GoodsEntityDaoImpl implements GoodsEntityDao {
    @Override
    public void saveOrUpdate(List<GoodsEntity> list) {
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConn1();
            for (GoodsEntity goodsEntity : list){
                GoodsEntity goodsEntity1 = null;
                String sql1 = "select id from goods where goods_id="+goodsEntity.getGoods_id();
                ps1 = conn.prepareStatement(sql1);
                rs = ps1.executeQuery();
                if (rs.next()){
                    goodsEntity1 = new GoodsEntity();
                }
                if (null == goodsEntity1){
                    String sql2 = "insert into goods (goods_id,symptom_id,supplier_id,brand_id,goods_sn,bar_code,goods_name,goods_number,shop_price,goods_brief" +
                            ",goods_img,first_py,common_name,manufacturer,goods_attr,license_number) values " +
                            " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    ps2 = conn.prepareStatement(sql2);
                    ps2.setInt(1,goodsEntity.getGoods_id());
                    ps2.setInt(2,goodsEntity.getSymptom_id());
                    ps2.setInt(3,goodsEntity.getSupplier_id());
                    ps2.setInt(4,goodsEntity.getBrand_id());
                    ps2.setString(5,goodsEntity.getGoods_sn());
                    ps2.setString(6,goodsEntity.getBar_code());
                    ps2.setString(7,goodsEntity.getGoods_name());
                    ps2.setInt(8,goodsEntity.getGoods_number());
                    ps2.setString(9,goodsEntity.getShop_price());
                    ps2.setString(10,goodsEntity.getGoods_brief());
                    ps2.setString(11,goodsEntity.getGoods_img());
                    ps2.setString(12,goodsEntity.getFirst_py());
                    ps2.setString(13,goodsEntity.getCommon_name());
                    ps2.setString(14,goodsEntity.getManufacturer());
                    ps2.setString(15,goodsEntity.getGoods_attr());
                    ps2.setString(16,goodsEntity.getLicense_number());
                }else {
                    String sql2 = "update VGoodsStockHn set goods_sn=?,bar_code=?,goods_name=?,goods_number=?,shop_price=?,goods_brief=?,goods_img=?,first_py=?," +
                            "common_name=?,manufacturer=?,goods_attr=?,license_number=? where id="+goodsEntity.getGoods_id();
                    ps2 = conn.prepareStatement(sql2);
                    ps2.setString(1,goodsEntity.getGoods_sn());
                    ps2.setString(2,goodsEntity.getBar_code());
                    ps2.setString(3,goodsEntity.getGoods_name());
                    ps2.setInt(4,goodsEntity.getGoods_number());
                    ps2.setString(5,goodsEntity.getShop_price());
                    ps2.setString(6,goodsEntity.getGoods_brief());
                    ps2.setString(7,goodsEntity.getGoods_img());
                    ps2.setString(8,goodsEntity.getFirst_py());
                    ps2.setString(9,goodsEntity.getCommon_name());
                    ps2.setString(10,goodsEntity.getManufacturer());
                    ps2.setString(11,goodsEntity.getGoods_attr());
                    ps2.setString(12,goodsEntity.getLicense_number());
                }
                ps2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs, ps1,ps2,conn);
        }
    }

    @Override
    public List<GoodsEntity> getList(int page) {
        return null;
    }
}
