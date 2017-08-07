package com.df.api.express;

import com.df.api.commonUtil.DigestUtil;
import com.df.api.commonUtil.GsonUtil;
import com.df.api.commonUtil.HttpUtil;
import com.df.api.express.model.shentongTrace;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017-08-07.
 */
public class shentong {

    public static List<shentongTrace> getTraceInfo(String data) {
        String url = "http://japi.zto.cn/zto/api_utf8/traceInterface";
        url = "http://japi.zto.cn/gateway.do";
        String charset = "utf-8";
        String msg_type = "TRACEINTERFACE_NEW_TRACES";
        String company_id = "98e86efe304849c890c7bcca5384063a";
        String key = "F524A9E86C965F2ED5BF1C8378F2BDDF";
        String datatmp = "['"+data+"']";
        try {
            String data_digest = DigestUtil.digest(datatmp, key, charset);
            String response = "";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("data", datatmp);
            params.put("data_digest", data_digest);
            params.put("company_id", company_id);
            params.put("msg_type", msg_type);
            // 向跟踪信息接口发送请求
            response = HttpUtil.post(url, charset, params);

            Gson gson = new Gson();
            JsonObject jsonobj = gson.fromJson(response, JsonObject.class);
            JsonObject data_json = jsonobj.get("data").getAsJsonArray().get(0).getAsJsonObject();
            JsonArray traces =data_json.get("traces").getAsJsonArray();

            List<shentongTrace> tracelist = GsonUtil.parseJsonArrayWithGson(traces.toString(),shentongTrace.class);
            return tracelist;
            //System.out.println(response);
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }


    public static void main(String[] args) {
        getTraceInfo("688888888888");
    }
}
