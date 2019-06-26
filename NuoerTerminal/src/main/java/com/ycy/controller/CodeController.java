package com.ycy.controller;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/code")
public class CodeController {

    private static String Url = "http://106.wx96.com/webservice/sms.php?method=Submit";

    /**
     * 获取手机验证码
     * @return
     */
    @RequestMapping("/getCode")
    public String getCode(String phone){
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);

        client.getParams().setContentCharset("GBK");
        method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");

        int mobile_code = (int)((Math.random()*9+1)*100000);

        String content = new String("您的验证码是："+mobile_code+"，请不要把验证码泄露给其他人，如非本人操作，可不用理会");

        NameValuePair[] data = {//提交短信
                new NameValuePair("account", "C56584456"), //用户名请登录用户中心->验证码、通知短信->帐户及签名设置->APIID
                new NameValuePair("password", "55ef3e970024c186b23c9bb855137835"),  //查看密码请登录用户中心->验证码、通知短信->帐户及签名设置->APIKEY
                //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
                new NameValuePair("mobile", phone),
                new NameValuePair("content", content),
        };
        method.setRequestBody(data);

        try {
            client.executeMethod(method);

            String SubmitResult =method.getResponseBodyAsString();

            //System.out.println(SubmitResult);

            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();

            String code = root.elementText("code");
            String msg = root.elementText("msg");
            String smsid = root.elementText("smsid");

//            System.out.println(code);
//            System.out.println(msg);
//            System.out.println(smsid);

            if("2".equals(code)){
                System.out.println("短信提交成功");
                return mobile_code+"";
            }

        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "0";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "0";
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "0";
        }
        return "0";
    }

}
