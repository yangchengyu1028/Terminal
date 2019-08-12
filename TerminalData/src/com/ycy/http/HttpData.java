package com.ycy.http;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ycy.entity.GoodsEntity;
import com.ycy.entity.SupplierEntity;
import com.ycy.entity.SymptomEntity;
import com.ycy.service.IBrandEntityService;
import com.ycy.service.IGoodsEntityService;
import com.ycy.service.ISupplierEntityService;
import com.ycy.service.ISymptomEntityService;
import com.ycy.service.impl.BrandEntityServiceImpl;
import com.ycy.service.impl.GoodsEntityServiceImpl;
import com.ycy.service.impl.SupplierEntityServiceImpl;
import com.ycy.service.impl.SymptomEntityServiceImpl;
import com.ycy.util.GetAndPost;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

public class HttpData {


    private GetAndPost getAndPost = new GetAndPost();
    private IGoodsEntityService goodsEntityService = new GoodsEntityServiceImpl();
    private ISupplierEntityService supplierEntityService = new SupplierEntityServiceImpl();
    private IBrandEntityService brandEntityService = new BrandEntityServiceImpl();
    private ISymptomEntityService symptomEntityService = new SymptomEntityServiceImpl();
    public static Log log = LogFactory.getLog(HttpData.class);

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
                log.info("------开始查询商品数据------");
                int num = 0;
                while (true){
                    try {
                        List<GoodsEntity> list = goodsEntityService.getListByYS(num);
                        if (list.isEmpty()){
                            break;
                        }
                        goodsEntityService.saveOrUpdate(list);
                    }catch (Exception e){
                        log.error("更新商品数据发生异常:",e);
                    }
                    num += 200;
                }
                log.info("------商品数据传送完成------");
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
                log.info("开始查询症状数据");
                int num = 0;
                while (true){
                    try {
                        List<SymptomEntity> list = symptomEntityService.getSymptomByYS(num);
                        if (list.isEmpty()){
                            break;
                        }
                        symptomEntityService.saveOrUpdate(list);
                    }catch (Exception e){
                        log.error("更新症状数据发生异常:",e);
                    }
                    num += 1000;
                }
                log.info("症状数据传送完成");
                try {
                    Thread.sleep(30 * 60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

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
                log.info("开始查询品牌数据");
                try {
                    brandEntityService.saveOrUpdate(brandEntityService.getBrandByYS());
                }catch (Exception e){
                    log.error("更新商家品牌发生异常:",e);
                }

                log.info("品牌数据传送完成");
                try {
                    Thread.sleep(30 * 60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
                log.info("开始查询商家数据");
                try {
                    supplierEntityService.saveOrUpdate(getSupplierByYS());
                }catch (Exception e){
                    log.error("更新商家数据发生异常:",e);
                }
                log.info("商家数据传送完成");
                try {
                    Thread.sleep(30 * 60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    /**
     * 通过接口拿取易索所有商家
     * @return
     */
    public List<SupplierEntity> getSupplierByYS(){
        List<SupplierEntity> list = new ArrayList<>();
        String json = getAndPost.get("http://www.iesoo.com/api.php?app_key=BD9D07D7-384D-42CD-BD3B-85A3C46AB2DC&method=dsc.shops.select.post&format=json");
        if (json==null){
            return list;

        }
        JSONArray jsonArray = JSONArray.parseArray(json);
        for (int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            SupplierEntity supplierEntity = new SupplierEntity();
            supplierEntity.setSupplier_id(jsonObject.getIntValue("user_id"));
            supplierEntity.setSupplier_name(jsonObject.getString("rz_shopName"));
            supplierEntity.setMobile(jsonObject.getString("mobile_phone"));

            list.add(supplierEntity);
        }

        return list;
    }

}
