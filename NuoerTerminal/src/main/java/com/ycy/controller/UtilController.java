package com.ycy.controller;

import com.baidu.aip.speech.AipSpeech;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/util")
public class UtilController {

    // 设置APPID/AK/SK    // 百度AI开发平台的控制台中创建一个语音应用即可获得
    public static final String APP_ID = "15990746";
    public static final String API_KEY = "8ohbDjOBC1dduEXgZ5wjGtzL";
    public static final String SECRET_KEY = "366jcs7XeIrsUdqOMjT6VQco83i7Br7D";


    @RequestMapping("/voiceDiscern")
    public String voiceDiscern(@RequestParam MultipartFile audioData){

        byte[] outByte = new byte[0];
        try {
            outByte = audioData.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        // client.setHttpProxy("proxy_host", proxy_port);
        // 设置http代理
        // client.setSocketProxy("proxy_host", proxy_port);
        // 设置socket代理
        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        // System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
        JSONObject asrRes2 = client.asr(outByte, "pcm", 16000, null);
        int err_no = -1;
        try {
            err_no = asrRes2.getInt("err_no");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (err_no!=0){
            return "请重新讲话！";
        }
        JSONArray jsonArray = null;
        try {
            jsonArray = asrRes2.getJSONArray("result");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = "";
        if (jsonArray != null){
            try {
                str = (String) jsonArray.get(0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        return str.split("。")[0];
    }


    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        long length = file.length();
        byte[] bytes = new byte[(int) length];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        if (offset < bytes.length) {
            is.close();
            throw new IOException("Could not completely read file " + file.getName());
        }
        is.close();
        return bytes;
    }


}
