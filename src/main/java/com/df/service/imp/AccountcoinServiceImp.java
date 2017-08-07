package com.df.service.imp;

import com.df.dao.AcountcoinDao;
import com.df.model.AcountcoinEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017-08-06.
 */
@Service
public class AccountcoinServiceImp {

    private AcountcoinDao acountcoinDao;

    public void setAcountcoinDao(AcountcoinDao acountcoinDao) {
        this.acountcoinDao = acountcoinDao;
    }

    public void testAdd(AcountcoinEntity um){ acountcoinDao.save(um); }

    public AcountcoinEntity testGet(long id){ return acountcoinDao.getOne(id); }

    public List<AcountcoinEntity> testGetList(){ return acountcoinDao.findAll(); }
}
