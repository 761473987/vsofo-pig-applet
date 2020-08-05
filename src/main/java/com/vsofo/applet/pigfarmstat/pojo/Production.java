package com.vsofo.applet.pigfarmstat.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author  wangtao
 * @Date 2020-06-02 
 */

public class Production implements Serializable {

	private static final long serialVersionUID =  1358285855629090326L;

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 程序ID
	 */
	private String appId;

	/**
	 * 统计日期
	 */
	private Date date;

	/**
	 * 年
	 */
	private Long year;

	/**
	 * 月
	 */
	private Long month;

	/**
	 * 养殖场
	 */
	private Long farmId;

	/**
	 * 数据类别
	 */
	private String dataType;

	/**
	 * 数据类别名称
	 */
	private String dataTypeName;

	/**
	 * 实际
	 */
	private Double reality;

	/**
	 * 目标
	 */
	private Double target;

	/**
	 * 差额
	 */
	private Double deficit;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getYear() {
		return this.year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getMonth() {
		return this.month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public Long getFarmId() {
		return this.farmId;
	}

	public void setFarmId(Long farmId) {
		this.farmId = farmId;
	}

	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataTypeName() {
		return this.dataTypeName;
	}

	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}

	public Double getReality() {
		return this.reality;
	}

	public void setReality(Double reality) {
		this.reality = reality;
	}

	public Double getTarget() {
		return this.target;
	}

	public void setTarget(Double target) {
		this.target = target;
	}

	public Double getDeficit() {
		return this.deficit;
	}

	public void setDeficit(Double deficit) {
		this.deficit = deficit;
	}

}
