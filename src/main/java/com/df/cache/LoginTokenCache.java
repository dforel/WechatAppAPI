package com.df.cache;

import com.df.model.UserBasis;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017-08-05.
 */
@Component
public class LoginTokenCache extends MemcachedBasis {

    final int EXPIRS_TIME = 60*60*1 ; //缓存一小时。
    
    @Autowired
    private UserBasisCache UserBasisCache;
    
    public void set(String token, long id){
        try {
            memcachedClient.set( this.Prefix+"logintoken:"+token ,EXPIRS_TIME, id);
        } catch (TimeoutException |InterruptedException|MemcachedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取token对应的用户id
     * @param token
     * @return
     */
    private long getIdByToken(String token){
        try {
            long userid = memcachedClient.get( this.Prefix+"logintoken:"+token );
            return userid;
        } catch ( TimeoutException | InterruptedException | MemcachedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 是否足够权限
     * @param token 验证token
     * @return
     */
    public boolean isLogin(String token) {
        long userid = getIdByToken(token);
        if (userid == 0) {
            return false;
        } else {
            UserBasis user = UserBasisCache.get(userid);
            if (user == null) {
                return false;
            }
            if(user.getId()>0){
                return true;
            }
//            if (level <= user.getPermission()) { //用户权限是否大于所需等级
//                return true;
//            }
        }
        return false;
    }
}
