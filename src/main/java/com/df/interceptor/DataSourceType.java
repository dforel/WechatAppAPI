package com.df.interceptor;

public enum DataSourceType {

	// 主库
	Master("master"),

	// 从库
	Slave("slave"),

	// 金币主库
	ServiceMaster("serviceMaster"),

	// 金币从库
	ServiceSlave("serviceSlave");

	private DataSourceType(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
