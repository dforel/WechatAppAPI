package com.df.service.imp;

import com.df.dao.instanceDao;
import com.df.interceptor.DataSource;
import com.df.interceptor.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017-08-06.
 */
@Service
public class instanceServiceImp {

    @Autowired
    private instanceDao idao ;


    public int getCount(String itoken,long sid){ return idao.countByItokenAndSidAndCountThenZero(itoken,sid); }

    /**
     * 判断是否toke对应服务，并且使用次数仍大于0
     * @param itoken
     * @param sid
     * @return
     */
    @DataSource(DataSourceType.ServiceSlave)
    public boolean isPermissionPass(String itoken,long sid){
        if(getCount(itoken,sid)>0){
            return true;
        }
        return  false;
    }

    @DataSource(DataSourceType.ServiceMaster)
    public void subCount(String itoken,long sid){  idao.updateSubCount(itoken,sid); }
}
