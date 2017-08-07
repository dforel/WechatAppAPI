package com.df.dao;

import com.df.interceptor.DataSource;
import com.df.interceptor.DataSourceType;
import com.df.model.AcountcoinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017-08-06.
 */
@Repository("AcountcoinDao")
public interface AcountcoinDao extends JpaRepository<AcountcoinEntity, Long> {

    @Query
    AcountcoinEntity findByUserid(long Userid);

    @DataSource(DataSourceType.Master)
    @Query("select t from AcountcoinEntity t where t.userid = ?1")
    AcountcoinEntity findByUserid(String userid);

    @Query(value="select * from Acountcoin t where t.userid = ?",  nativeQuery = true)
    AcountcoinEntity findByUseridnative(String userid);
}
