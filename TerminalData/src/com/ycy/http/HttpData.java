package com.ycy.http;

import com.ycy.entity.GoodsEntity;
import com.ycy.service.IGoodsEntityService;
import com.ycy.service.impl.GoodsEntityServiceImpl;
import com.ycy.util.GetAndPost;
import com.ycy.util.ReadFile;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HttpData {

    private boolean boo1 = false;

    private GetAndPost getAndPost = new GetAndPost();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private IGoodsEntityService goodsEntityService = new GoodsEntityServiceImpl();

    public void execute() {

        new Thread(new execute1()).start();
        new Thread(new execute2()).start();
        new Thread(new execute3()).start();
        new Thread(new execute4()).start();
    }
    /**
     * 商品信息传送
     */
    class  execute1 implements Runnable{
        @Override
        public void run() {
            while(true) {
                System.out.println("开始查询商品数据");
                boo1 = true;
                int page = 0;
                while (boo1){
                    page++;
                    try {
                        goodsEntityService.saveOrUpdate(getGoods(page));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                System.out.println("商品数据传送完成");
                try {
                    Thread.sleep(5 * 60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }
    /**
     * 症状信息传送
     */
    class  execute2 implements Runnable{
        @Override
        public void run() {
            while(true) {


            }

        }
    }
    /**
     * 品牌信息传送
     */
    class  execute3 implements Runnable{
        @Override
        public void run() {
            while(true) {


            }

        }
    }
    /**
     * 店铺信息传送
     */
    class  execute4 implements Runnable{
        @Override
        public void run() {
            while(true) {


            }

        }
    }

    /**
     * 通过接口获取获取伊索商品
     * @param page
     * @return
     */
    List<GoodsEntity> getGoods(int page){
        List<GoodsEntity> list = new ArrayList<>();
        String url =  "https://www.novochina.com/api.php?app_key=" + ReadFile.getApp_key() + "&method=dsc.goods.list.get&page="
                + page + "&page_size=500";
        String json = getAndPost.get(url);
        JSONObject job = JSONObject.fromObject(json);
        if (!"success".equals(job.getString("result"))){
            return list;
        }
        JSONArray jsonArray = job.getJSONObject("info").getJSONArray("list");
        if (jsonArray.isEmpty()){
            boo1 = false;
            return list;
        }
        for (int i = 0; i < jsonArray.size(); i++){
            GoodsEntity goodsEntity = new GoodsEntity();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (0==jsonObject.getInt("goods_id")){
                continue;
            }
            goodsEntity.setGoods_id(jsonObject.getInt("goods_id"));
            goodsEntity.setSupplier_id(jsonObject.getInt("user_id"));
            goodsEntity.setBrand_id(jsonObject.getInt("brand_id"));
            goodsEntity.setSymptom_id(jsonObject.getInt("cat_id"));
            goodsEntity.setGoods_sn(jsonObject.getString("goods_sn"));
            goodsEntity.setBar_code(jsonObject.getString("bar_code"));
            goodsEntity.setGoods_name(jsonObject.getString("goods_name"));
            goodsEntity.setGoods_number(jsonObject.getInt("goods_number"));
            goodsEntity.setShop_price(jsonObject.getString("shop_price"));
            goodsEntity.setGoods_brief(jsonObject.getString("goods_brief"));
            goodsEntity.setGoods_img(jsonObject.getString("goods_img"));
            goodsEntity.setIs_on_sale(jsonObject.getInt("is_on_sale"));
            goodsEntity.setCommon_name(jsonObject.getString("common_name"));
            goodsEntity.setManufacturer(jsonObject.getString("manufacturer"));
            goodsEntity.setGoods_attr(jsonObject.getString("goods_attr"));
            goodsEntity.setLicense_number(jsonObject.getString("license_number"));
            list.add(goodsEntity);
        }

        return list;
    }


}
