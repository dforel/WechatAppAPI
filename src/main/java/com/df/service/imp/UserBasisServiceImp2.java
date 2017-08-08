package com.df.service.imp;

import com.df.cache.UserBasisCache;
import com.df.dao.UserBasis1Dao;
import com.df.model.UserBasis;
import com.df.service.UserBasisService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class UserBasisServiceImp2 implements UserBasisService2 {

	@Autowired
	private UserBasis1Dao userBasisdao1;
	@Autowired
	private UserBasisCache UserBasiscache;

	@Override
	public long Save(UserBasis entity) {
		long id = userBasisdao1.Save(entity);
		if (id > 0) {
			//UserBasiscache.set(entity);
		}
		return id;
	}

	@Override
	public Boolean Delete(long ID) {
		boolean result = userBasisdao1.Delete(ID);
		if (result) {
			UserBasiscache.delete(ID);
		}
		return result;
	}

	@Override
	public UserBasis getEntity(long ID) {

		return null;//UserBasiscache.get(ID);
	}

	@Override
	public Boolean isExistName(String name) {
		return UserBasiscache.isExitName(name);
	}

	@Override
	public Boolean isExistPhone(String phone) {
		return userBasisdao1.isExitPhone(phone);
	}

	@Override
	public UserBasis getEntity(String phone) {
		return userBasisdao1.getEntity(phone);
	}

	@Override
	public int getListCount(Map<String, Object> whereMap) {
		//throw new RuntimeException();
		return userBasisdao1.getListCount(whereMap);
	}
	
	@Override
	public List<UserBasis> getList(Map<String, Object> whereMap, String OrderBy, int nStart, int nLimit) {
		return userBasisdao1.getList(whereMap, OrderBy, nStart, nLimit);
	}

	@Override
	public List<UserBasis> getList() {
		return userBasisdao1.getList();
	}

}