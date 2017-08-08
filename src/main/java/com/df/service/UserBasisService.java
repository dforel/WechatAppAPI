package com.df.service;

import com.df.interceptor.DataSource;
import com.df.interceptor.DataSourceType;
import com.df.model.UserbasisEntity;

import java.util.List;
import java.util.Map;


public interface UserBasisService {

	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	@DataSource
	public long Save(UserbasisEntity entity);
	
	/**
	 * 删除
	 * @param ID
	 * @return
	 */
	@DataSource
	public void Delete(long ID);
	
	/**
	 * 获取信息
	 * @param ID
	 * @return
	 */
	@DataSource(DataSourceType.Slave)
	public UserbasisEntity getEntity(long ID);

	/**
	 * 确认名字是否已经使用
	 * @param name
	 * @return
	 */
	@DataSource(DataSourceType.Slave)
	Boolean isExistName(String name);

	/**
	 * 确认名字电话是否已经使用
	 * @param phone
	 * @return Boolean
	 */
	@DataSource(DataSourceType.Slave)
	Boolean isExistPhone(String phone);

	/**
	 * 获取信息
	 * @param phone
	 * @return Boolean
	 */
	@DataSource(DataSourceType.Slave)
	public UserbasisEntity getEntity(String phone);
	
	/**
	 * 根据条件获取
	 * @return
	 */
	@DataSource(DataSourceType.Slave)
	public List<UserbasisEntity> getList(Map<String, Object> whereMap, String OrderBy,String OrderByProperties, int nStart, int nLimit);
	
	/**
	 * 根据条件获取数据条数
	 * @return
	 */
	@DataSource(DataSourceType.Slave)
	public int getListCount(Map<String, Object> whereMap);
	
	/**
	 * 获取所有
	 * @return
	 */
	@DataSource(DataSourceType.Slave)
	public List<UserbasisEntity> getList();
}