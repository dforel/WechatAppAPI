package com.df.cache;

import com.df.dao.UserBasis1Dao;
import com.df.model.UserBasis;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeoutException;

@Component
public class UserBasisCache extends MemcachedBasis {

	private Logger log = Logger.getLogger(UserBasisCache.class);
	@Autowired
	private UserBasis1Dao userBasis1Dao;

	/**
	 * 设置缓存
	 * 
	 * @param model
	 *            用户model
	 * @return
	 */
	public Boolean set(UserBasis model) {
		Boolean result = false;
		try {
			result = memcachedClient.set(getCacheKey(model.getId()), super.Exptime, model);
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			log.error("", e);
		}
		return result;
	}

	/**
	 *  获取名字是否使用
	 *
	 * @param Name
	 *            手机
	 * @return
	 */
	public Boolean isExitName(String Name) {
		Boolean result = false;
		try {
			result = memcachedClient.get(getRegExistCacheKey(Name));
			if (result == null ) {
				result = userBasis1Dao.isExitName(Name);
				memcachedClient.set(getRegExistCacheKey(Name),super.Exptime,result);
			}
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			log.error("", e);
		}
		return result;
	}



	/**
	 * 获取缓存
	 * 
	 * @param id
	 *            用户ID
	 * @return
	 */
	public UserBasis get(long id) {
		UserBasis entity = new UserBasis();
		try {
			entity = memcachedClient.get(getCacheKey(id));
			if (entity == null || entity.getId() <= 0) {
				entity = userBasis1Dao.getEntity(id);
				this.set(entity);
			}
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			log.error("", e);
			entity = userBasis1Dao.getEntity(id);
		}
		return entity;
	}

	/**
	 * 删除缓存
	 * 
	 * @param id
	 *            用户ID
	 * @return
	 */
	public Boolean delete(long id) {
		try {
			return memcachedClient.delete(getCacheKey(id));
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			log.error("", e);
		}
		return false;
	}

	/**
	 * 获取缓存 Key
	 * 
	 * @param id
	 *            用户ID
	 * @return
	 */
	private String getCacheKey(long id) {
		return super.Prefix + "UserBasis:" + id;
	}

	/**
	 * 获取缓存 Key
	 *
	 * @param value
	 *            监测存在的值
	 * @return
	 */
	private String getRegExistCacheKey(String value) {
		return super.Prefix + "RegExist:" + value;
	}
}
