package com.df.service.imp;

import com.df.cache.UserBasisCache;
import com.df.dao.UserbasisDao;
import com.df.model.UserbasisEntity;
import com.df.service.UserBasisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-08-08.
 */
@Service
public class UserBasisServiceImp implements UserBasisService {

    @Autowired
    private UserbasisDao userbasisDao;
    @Autowired
    private UserBasisCache UserBasiscache;
    /**
     * 保存
     * @param entity 用户实体
     * @return 用户id
     */
    @Override
    public long Save(UserbasisEntity entity) {
        UserbasisEntity new_entity = userbasisDao.save(entity);
        if(new_entity.getId()>0){
            UserBasiscache.set(entity);
        }
        return new_entity.getId();
    }

    @Override
    public void Delete(long ID) {
        userbasisDao.delete(ID);
        UserBasiscache.delete(ID);
    }

    @Override
    public UserbasisEntity getEntity(long ID) {
        return UserBasiscache.get(ID);
    }

    @Override
    public Boolean isExistName(String name) {
        Integer ss= userbasisDao.countByName(name);
        return ss>0;
    }

    @Override
    public Boolean isExistPhone(String phone) {
        Integer ss= userbasisDao.countByPhone(phone);
        return ss>0;
    }

    @Override
    public UserbasisEntity getEntity(String phone) {
        return userbasisDao.findFirstByPhone(phone);
    }

    @Override
    public List<UserbasisEntity> getList(Map<String, Object> whereMap, String OrderBy,String OrderByProperties, int page, int size) {

        Specification<UserbasisEntity > spe =new  Specification<UserbasisEntity>(){
            public Predicate toPredicate(Root<UserbasisEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                for (Map.Entry<String, Object> entry : whereMap.entrySet()){
                    Path<String> nameExp = root.get(entry.getKey());
                    Predicate pr =   cb.equal(nameExp, entry.getValue());
                    query.where(pr);
                }
                return null;
            }
        };
        OrderByProperties = OrderByProperties ==null?"id":OrderByProperties;
        Pageable pg =new PageRequest(page,size,Sort.Direction.fromStringOrNull(OrderBy),OrderByProperties);
        return userbasisDao.findAll(spe,pg).getContent();
    }

    @Override
    public int getListCount(Map<String, Object> whereMap) {
        Specification<UserbasisEntity > spe = (root, query, cb) -> {
            for (Map.Entry<String, Object> entry : whereMap.entrySet()){
                Path<String> nameExp = root.get(entry.getKey());
                Predicate pr =   cb.equal(nameExp, entry.getValue());
                query.where(pr);
            }
            return null;
        };
        return userbasisDao.findAll(spe).size();
    }

    @Override
    public List<UserbasisEntity> getList() {
        return userbasisDao.findAll();
    }
}
