package com.df.service.imp;

import com.df.dao.InstanceDao;
import com.df.interceptor.DataSource;
import com.df.interceptor.DataSourceType;
import com.df.model.InstanceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017-08-06.
 */
@Service
public class instanceServiceImp {

    @Autowired
    private InstanceDao instanceDao ;


    public int getCount(String itoken, long sid){ return instanceDao.countByItokenAndSidAndCountThenZero(itoken,sid); }

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
    public void subCount(String itoken,long sid){  instanceDao.updateSubCount(itoken,sid); }

    @DataSource(DataSourceType.ServiceMaster)
    public void saveSomething(InstanceEntity entity){  instanceDao.save(entity); }

}
