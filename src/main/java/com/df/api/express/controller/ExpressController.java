package com.df.api.express.controller;

import com.df.api.express.model.shentongTrace;
import com.df.api.express.service.shentong;
import com.df.api.interceptor.ApiAuthority;
import com.df.api.interceptor.ApiAuthorityType;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-08-07.
 */
@Controller
@RequestMapping("/api/express/*")
public class ExpressController {

    /**
     * testGetList
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "testGetList", produces = "application/json;charset=UTF-8", method = { RequestMethod.GET })
    @ApiAuthority(authorityLevels = ApiAuthorityType.Authority_1,sid = 1)
    public String testGetList(@RequestParam("data") String data) {
        int code = -1;
        String msg = "";
        List<shentongTrace> list= shentong.getTraceInfo(data);
        Map<String, Object> responseMap = new HashMap<String, Object>();
        responseMap.put("code", code);
        responseMap.put("msg", msg);
        responseMap.put("params", list);
        responseMap.put("rows", "");
        return new Gson().toJson(responseMap);
    }
}
