package com.df.dao;

import com.df.model.UserbasisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017-08-06.
 */
@Repository
public interface UserbasisDao extends JpaRepository<UserbasisEntity, Long>,JpaSpecificationExecutor<UserbasisEntity> {

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

    @Query("select 1 from UserbasisEntity t where t.name = ?1")
    public int ExistsByName(String name);

    @Query
    Integer countByName(String name);

    @Query
    UserbasisEntity findAllByPhoneAndPassword(String phone,String password);

    @Query
    UserbasisEntity findFirstByPhoneAndPassword(String phone,String password);

    @Query
    Integer countByPhoneAndPassword(String phone,String password);

    @Query
    Integer countByPhone(String phone);

    @Query
    UserbasisEntity findFirstByPhone(String phone);

//    @Modifying
//    @Query
//    public UserbasisEntity save(UserbasisEntity entity);
}
