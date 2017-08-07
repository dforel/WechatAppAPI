package com.df.dao;

import com.df.model.UserbasisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017-08-06.
 */
@Repository
public interface UserbasisDao extends JpaRepository<UserbasisEntity, Long> {

//    AcountcoinEntity findByUserid(long Userid);
//
//    @DataSource(DataSourceType.Master)
//    @Query("select t from AcountcoinEntity t where t.userid = ?1")
//    AcountcoinEntity findByUserid(String userid);
//
//    @Query(value="select * from Acountcoin t where t.userid = ?",  nativeQuery = true)
//    AcountcoinEntity findByUseridnative(String userid);

    @Query("select t from UserbasisEntity t where t.name = ?1")
    public UserbasisEntity findByName(String name);

//    @Modifying
//    @Query
//    public UserbasisEntity save(UserbasisEntity entity);
}
