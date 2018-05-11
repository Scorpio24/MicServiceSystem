package com.yyu.domain;

import java.io.Serializable;
import java.util.Date;


public class ServiceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String serviceName;
	//
	private String serviceIP;
	//
	private String servicePort;
	//
	private String serviceParam;
	//
	private String serviceResult;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceIP() {
		return serviceIP;
	}

	public void setServiceIP(String serviceIP) {
		this.serviceIP = serviceIP;
	}

	public String getServicePort() {
		return servicePort;
	}

	public void setServicePort(String servicePort) {
		this.servicePort = servicePort;
	}

	public String getServiceParam() {
		return serviceParam;
	}

	public void setServiceParam(String serviceParam) {
		this.serviceParam = serviceParam;
	}

	public String getServiceResult() {
		return serviceResult;
	}

	public void setServiceResult(String serviceResult) {
		this.serviceResult = serviceResult;
	}
}
