package com.df.service.imp;

import com.df.dao.AcountcoinDao;
import com.df.model.AcountcoinEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017-08-06.
 */
@Service
public class AccountcoinServiceImp {

    @Autowired
    private AcountcoinDao acd ;

    public void testAdd(AcountcoinEntity um){ acd.save(um); }

    public AcountcoinEntity testGet(long id){ return acd.getOne(id); }

    public List<AcountcoinEntity> testGetList(){ return acd.findAll(); }
}
