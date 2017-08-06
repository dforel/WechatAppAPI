package com.df.common;

import com.df.cache.MemcachedBasis;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017-08-04.
 */
@Component
public class ValidateCodeUtil extends MemcachedBasis{

    @Autowired
    protected MemcachedClient memcachedClient;


    final int EXPIRY_TIME = 60*3 ; //验证码有效期3分钟

    public String getCodeByid(String verifysession){
        String vcode=null;
        try {
            vcode = memcachedClient.get(this.Prefix + "VCode" +verifysession);
            if(vcode != null){
                memcachedClient.delete(this.Prefix + "VCode" +verifysession);
            }
        } catch (TimeoutException |InterruptedException|MemcachedException e) {
            e.printStackTrace();
        }
        return vcode;
    }


    /**
     * 验证 验证码 和 缓存中存储的是否一致
     * @param verifysession 验证缓存
     * @param code 验证码
     * @return
     */
    public boolean verifyCode(String verifysession,String code){
        String codeCache = getCodeByid(verifysession);
        if(codeCache==null){
            return false;
        }
        // 将验证码转换成小写
        String codeCacheToLower = codeCache.toLowerCase();
        if(code.toLowerCase().equals(codeCacheToLower)  ){
            return true;
        }
        //System.out.println(code.toLowerCase()+"------"+codeCacheToLower);
        return false;
    }

    /**
     *  保存验证码到缓存中去
     * @param uuid
     * @param value
     */
    public void saveToCache(String uuid,String value){
        try {
            memcachedClient.set(this.Prefix + "VCode" + uuid,EXPIRY_TIME,value);
        } catch (TimeoutException |InterruptedException|MemcachedException e) {
            e.printStackTrace();
        }
    }
}
