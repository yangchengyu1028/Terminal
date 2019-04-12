package com.ycy.controller;

import com.ycy.entity.GoodsEntity;
import com.ycy.entity.OrderGoodsEntity;
import com.ycy.service.IGoodsEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private IGoodsEntityService goodsEntityService;

    //创建本地店铺购物车
    @RequestMapping("/createLocalCart")
    @ResponseBody
    public String createLocalCart(HttpServletRequest request){
        List<OrderGoodsEntity> cartList = new ArrayList<>();
        HttpSession session = request.getSession(false);
        if (session.getAttribute("LocalCartList")==null){
            session.setAttribute("LocalCartList",cartList);
        }

        return "1";
    }

    //创建网上选药购物车
    @RequestMapping("/createOnlineCart")
    @ResponseBody
    public String createOnlineCart(HttpServletRequest request){
        List<OrderGoodsEntity> cartList = new ArrayList<>();
        HttpSession session = request.getSession(false);
        if (session.getAttribute("OnlineCartList")==null){
            session.setAttribute("OnlineCartList",cartList);
        }
        return "1";
    }
    //得到本地选药购物车
    @RequestMapping("/getLocalCart")
    @ResponseBody
    public List<OrderGoodsEntity> getLocalCart(HttpServletRequest request){
        List<OrderGoodsEntity> cartList = (List<OrderGoodsEntity>)request.getSession(false).getAttribute("LocalCartList");
        return cartList;
    }
    //得到网上选药购物车
    @RequestMapping("/getOnlineCart")
    @ResponseBody
    public List<OrderGoodsEntity> getOnlineCart(HttpServletRequest request){
        List<OrderGoodsEntity> cartList = (List<OrderGoodsEntity>)request.getSession(false).getAttribute("OnlineCartList");
        return cartList;
    }

    //给本地选药购物车加入商品
    @RequestMapping("/addLocalCart")
    @ResponseBody
    public List<OrderGoodsEntity> addLocalCart(HttpServletRequest request,int id,int buyNum){
        List<OrderGoodsEntity> cartList = (List<OrderGoodsEntity>)request.getSession(false).getAttribute("LocalCartList");
        GoodsEntity goodsEntity = goodsEntityService.getGoodsByIdToEntity(id);
        OrderGoodsEntity orderGoodsEntity = new OrderGoodsEntity();
        orderGoodsEntity.setBar_code(goodsEntity.getBar_code());
        orderGoodsEntity.setCommon_name(goodsEntity.getCommon_name());
        orderGoodsEntity.setGoods_attr(goodsEntity.getGoods_attr());
        orderGoodsEntity.setGoods_brief(goodsEntity.getGoods_brief());
        orderGoodsEntity.setGoods_id(goodsEntity.getGoods_id());
        orderGoodsEntity.setGoods_img(goodsEntity.getGoods_img());
        orderGoodsEntity.setGoods_name(goodsEntity.getGoods_name());
        orderGoodsEntity.setGoods_sn(goodsEntity.getGoods_sn());
        orderGoodsEntity.setLicense_number(goodsEntity.getLicense_number());
        orderGoodsEntity.setManufacturer(goodsEntity.getManufacturer());
        orderGoodsEntity.setShop_price(goodsEntity.getShop_price());
        orderGoodsEntity.setGoods_number(goodsEntity.getGoods_number());
        Boolean boo = false;
        for (OrderGoodsEntity goods:cartList){
            if (goods.getGoods_id() == id){
                cartList.remove(goods);
                orderGoodsEntity.setBuy_number(goods.getBuy_number()+buyNum);
                boo = true;
                break;
            }
        }
        if (!boo){
            orderGoodsEntity.setBuy_number(buyNum);
        }
        cartList.add(orderGoodsEntity);
        request.getSession(false).setAttribute("LocalCartList",cartList);

        return cartList;
    }

    //给网上选药购物车加入商品
    @RequestMapping("/addOnlineCart")
    @ResponseBody
    public List<OrderGoodsEntity> addOnlineCart(HttpServletRequest request,int id,int buyNum){
        List<OrderGoodsEntity> cartList = (List<OrderGoodsEntity>)request.getSession(false).getAttribute("OnlineCartList");
        GoodsEntity goodsEntity = goodsEntityService.getGoodsByIdToEntity(id);
        OrderGoodsEntity orderGoodsEntity = new OrderGoodsEntity();
        orderGoodsEntity.setBar_code(goodsEntity.getBar_code());
        orderGoodsEntity.setCommon_name(goodsEntity.getCommon_name());
        orderGoodsEntity.setGoods_attr(goodsEntity.getGoods_attr());
        orderGoodsEntity.setGoods_brief(goodsEntity.getGoods_brief());
        orderGoodsEntity.setGoods_id(goodsEntity.getGoods_id());
        orderGoodsEntity.setGoods_img(goodsEntity.getGoods_img());
        orderGoodsEntity.setGoods_name(goodsEntity.getGoods_name());
        orderGoodsEntity.setGoods_sn(goodsEntity.getGoods_sn());
        orderGoodsEntity.setLicense_number(goodsEntity.getLicense_number());
        orderGoodsEntity.setManufacturer(goodsEntity.getManufacturer());
        orderGoodsEntity.setShop_price(goodsEntity.getShop_price());
        orderGoodsEntity.setGoods_number(goodsEntity.getGoods_number());
        Boolean boo = false;
        for (OrderGoodsEntity goods:cartList){
            if (goods.getGoods_id() == id){
                cartList.remove(goods);
                orderGoodsEntity.setBuy_number(goods.getBuy_number()+buyNum);
                boo = true;
                break;
            }
        }
        if (!boo){
            orderGoodsEntity.setBuy_number(buyNum);
        }
        cartList.add(orderGoodsEntity);
        request.getSession(false).setAttribute("OnlineCartList",cartList);

        return cartList;
    }

    //删除本地购物车某个商品
    @RequestMapping("/delLocalCart")
    @ResponseBody
    public String delLocalCart(int id,HttpServletRequest request){
        int delId = -1;
        List<OrderGoodsEntity> cartList = (List<OrderGoodsEntity>)request.getSession(false).getAttribute("LocalCartList");
        for (int i=0;i<cartList.size();i++){
            if (cartList.get(i).getGoods_id()==id){
                delId = i;
            }
        }
        cartList.remove(delId);
        request.getSession(false).setAttribute("LocalCartList",cartList);
        return "1";
    }
    //删除网上购物车某个商品
    @RequestMapping("/delOnlineCart")
    @ResponseBody
    public String delOnlineCart(int id,HttpServletRequest request){
        int delId = -1;
        List<OrderGoodsEntity> cartList = (List<OrderGoodsEntity>)request.getSession(false).getAttribute("OnlineCartList");
        for (int i=0;i<cartList.size();i++){
            if (cartList.get(i).getGoods_id()==id){
                delId = i;
            }
        }
        cartList.remove(delId);
        request.getSession(false).setAttribute("OnlineCartList",cartList);
        return "1";
    }

    //修改本地购物车某个商品的购买数量
    @RequestMapping("/updateLocalCart")
    @ResponseBody
    public String updateLocalCart(int id,int buyNum,HttpServletRequest request){
        List<OrderGoodsEntity> cartList = (List<OrderGoodsEntity>)request.getSession(false).getAttribute("LocalCartList");
        for (OrderGoodsEntity orderGoodsEntity:cartList){
            if (orderGoodsEntity.getGoods_id()==id){
                orderGoodsEntity.setBuy_number(buyNum);
            }
        }
        request.getSession(false).setAttribute("LocalCartList",cartList);
        return "1";
    }

    //修改网上购物车某个商品的购买数量
    @RequestMapping("/updateOnlineCart")
    @ResponseBody
    public String updateOnlineCart(int id,int buyNum,HttpServletRequest request){
        List<OrderGoodsEntity> cartList = (List<OrderGoodsEntity>)request.getSession(false).getAttribute("OnlineCartList");
        for (OrderGoodsEntity orderGoodsEntity:cartList){
            if (orderGoodsEntity.getGoods_id()==id){
                orderGoodsEntity.setBuy_number(buyNum);
            }
        }
        request.getSession(false).setAttribute("OnlineCartList",cartList);
        return "1";
    }
}
