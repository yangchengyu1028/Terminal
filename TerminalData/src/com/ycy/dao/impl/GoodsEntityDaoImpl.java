package com.ycy.dao.impl;

import com.ycy.dao.GoodsEntityDao;
import com.ycy.entity.GoodsEntity;
import com.ycy.util.DBUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsEntityDaoImpl implements GoodsEntityDao {
    public static Log log = LogFactory.getLog(GoodsEntityDaoImpl.class);
    @Override
    public void saveOrUpdate(List<GoodsEntity> list) {
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnZD();
            for (GoodsEntity goodsEntity : list){
                GoodsEntity goodsEntity1 = null;
                String sql1 = "select goods_id from noer_goods where goods_id="+goodsEntity.getGoods_id();
                ps1 = conn.prepareStatement(sql1);
                rs = ps1.executeQuery();
                if (rs.next()){
                    goodsEntity1 = new GoodsEntity();
                }
                if (null == goodsEntity1){
                    String sql2 = "insert into noer_goods (goods_id,symptom_id,supplier_id,brand_id,goods_sn,bar_code,goods_name,goods_number,shop_price,goods_brief" +
                            ",goods_img,first_py,common_name,manufacturer,goods_attr,license_number,brand_name) values " +
                            " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
                    ps2.setString(17,goodsEntity.getBrand_name());
                }else {
                    String sql2 = "update noer_goods set goods_sn=?,bar_code=?,goods_name=?,goods_number=?,shop_price=?,goods_brief=?,goods_img=?,first_py=?," +
                            "common_name=?,manufacturer=?,goods_attr=?,license_number=?,symptom_id=?,brand_id=?,brand_name=? where goods_id="+goodsEntity.getGoods_id();
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
                    ps2.setInt(13,goodsEntity.getSymptom_id());
                    ps2.setInt(14,goodsEntity.getBrand_id());
                    ps2.setString(15,goodsEntity.getBrand_name());
                }
                ps2.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("更新商品数据sql发生异常:"+e);
        }finally {
            DBUtil.close(rs, ps1,ps2,conn);
        }
    }

    @Override
    public List<GoodsEntity> getListByYS(int num) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<GoodsEntity> list = new ArrayList<>();

        try {
            conn = DBUtil.getConnYS();
            String sql = "select goods_id,cat_id,user_id,brand_id,goods_sn,bar_code,goods_name,goods_number,shop_price,goods_brief,goods_img," +
                    "common_name,manufacturer,goods_attr,license_number,first_py,brand_name from dsc_goods where lib_goods_ids!=0 and is_on_sale=1 limit "+num+",1000;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                GoodsEntity goodsEntity = new GoodsEntity();
                goodsEntity.setGoods_id(rs.getInt("goods_id"));
                goodsEntity.setSymptom_id(rs.getInt("cat_id"));
                goodsEntity.setSupplier_id(rs.getInt("user_id"));
                goodsEntity.setBrand_id(rs.getInt("brand_id"));
                goodsEntity.setGoods_sn(rs.getString("goods_sn"));
                goodsEntity.setBar_code(rs.getString("bar_code"));
                goodsEntity.setGoods_name(rs.getString("goods_name"));
                goodsEntity.setGoods_number(rs.getInt("goods_number"));
                goodsEntity.setShop_price(rs.getString("shop_price"));
                goodsEntity.setGoods_brief(rs.getString("goods_brief"));
                goodsEntity.setGoods_img(rs.getString("goods_img"));
                goodsEntity.setCommon_name(rs.getString("common_name"));
                goodsEntity.setManufacturer(rs.getString("manufacturer"));
                goodsEntity.setGoods_attr(rs.getString("goods_attr"));
                goodsEntity.setLicense_number(rs.getString("license_number"));
                goodsEntity.setFirst_py(rs.getString("first_py"));
                goodsEntity.setBrand_name(rs.getString("brand_name"));
                list.add(goodsEntity);
            }
        } catch (SQLException e) {
            log.error("获取易索商品数据sql发生异常:"+e);
        }finally {
            DBUtil.close(rs,ps,conn);
        }

        return list;
    }
}
