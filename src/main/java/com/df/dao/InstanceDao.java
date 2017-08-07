package com.df.dao;

import com.df.model.InstanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017-08-06.
 */
@Repository
public interface InstanceDao extends JpaRepository<InstanceEntity, Long> {

    @Query("select t from InstanceEntity t where t.iid = ?1")
    InstanceEntity findByUserid(String userid);


    @Query(value = "select 1 from Instance where itoken = ? and sid = ? and count > 0",nativeQuery = true)
    int countByItokenAndSidAndCountThenZero(String itoken,long sid);

    @Modifying
    @Query("update InstanceEntity t set t.count=t.count-1 where t.itoken = ?1 and t.sid=?2")
    void updateSubCount(String itoken,long sid);

}
