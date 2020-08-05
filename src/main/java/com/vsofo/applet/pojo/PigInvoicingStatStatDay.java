package com.vsofo.applet.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author  wangtao
 * @Date 2020-05-14
 */

public class PigInvoicingStatStatDay  implements Serializable {

	private static final long serialVersionUID =  4291071943386739415L;

	/**
	 * 种猪存栏表
	 */
	private Long id;

	/**
	 * 养殖场ID
	 */
	private Long farmId;

	/**
	 * 生产线ID
	 */
	private Long lineId;

	/**
	 * 类别
	 */
	private Long pigTypeId;

	/**
	 * 品种
	 */
	private Long pigVarietyId;

	/**
	 * 胎次
	 */
	private Long parity;

	/**
	 * 入场数量
	 */
	private Long enterInNum;

	/**
	 * 转舍入数量
	 */
	private Long changeHouseInNum;

	/**
	 * 转舍出数量
	 */
	private Long changeHouseOutNum;

	/**
	 * 调拨入数量
	 */
	private Long transferInNum;

	/**
	 * 调拨出数量
	 */
	private Long transferOutNum;

	/**
	 * 转类别入数量
	 */
	private Long changeTypeInNum;

	/**
	 * 转类别出数量
	 */
	private Long changeTypeOutNum;

	/**
	 * 调整入数量
	 */
	private Long revisionInNum;

	/**
	 * 调整出数量
	 */
	private Long revisionOutNum;

	/**
	 * 销售出数量
	 */
	private Long saleOutNum;

	/**
	 * 淘汰出数量
	 */
	private Long eliminateOutNum;

	/**
	 * 死亡出数量
	 */
	private Long deathOutNum;

	/**
	 * 删除出数量
	 */
	private Long deleteOutNum;

	/**
	 * 库存数量
	 */
	private Long stockNum;

	/**
	 * 原库存数量
	 */
	private Long oldStockNum;

	/**
	 * 统计日期
	 */
	private Date statDate;

	/**
	 * 胎次转变入数量
	 */
	private Long changeParityInNum;

	/**
	 * 胎次转变出数量
	 */
	private Long changeParityOutNum;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFarmId() {
		return this.farmId;
	}

	public void setFarmId(Long farmId) {
		this.farmId = farmId;
	}

	public Long getLineId() {
		return this.lineId;
	}

	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}

	public Long getPigTypeId() {
		return this.pigTypeId;
	}

	public void setPigTypeId(Long pigTypeId) {
		this.pigTypeId = pigTypeId;
	}

	public Long getPigVarietyId() {
		return this.pigVarietyId;
	}

	public void setPigVarietyId(Long pigVarietyId) {
		this.pigVarietyId = pigVarietyId;
	}

	public Long getParity() {
		return this.parity;
	}

	public void setParity(Long parity) {
		this.parity = parity;
	}

	public Long getEnterInNum() {
		return this.enterInNum;
	}

	public void setEnterInNum(Long enterInNum) {
		this.enterInNum = enterInNum;
	}

	public Long getChangeHouseInNum() {
		return this.changeHouseInNum;
	}

	public void setChangeHouseInNum(Long changeHouseInNum) {
		this.changeHouseInNum = changeHouseInNum;
	}

	public Long getChangeHouseOutNum() {
		return this.changeHouseOutNum;
	}

	public void setChangeHouseOutNum(Long changeHouseOutNum) {
		this.changeHouseOutNum = changeHouseOutNum;
	}

	public Long getTransferInNum() {
		return this.transferInNum;
	}

	public void setTransferInNum(Long transferInNum) {
		this.transferInNum = transferInNum;
	}

	public Long getTransferOutNum() {
		return this.transferOutNum;
	}

	public void setTransferOutNum(Long transferOutNum) {
		this.transferOutNum = transferOutNum;
	}

	public Long getChangeTypeInNum() {
		return this.changeTypeInNum;
	}

	public void setChangeTypeInNum(Long changeTypeInNum) {
		this.changeTypeInNum = changeTypeInNum;
	}

	public Long getChangeTypeOutNum() {
		return this.changeTypeOutNum;
	}

	public void setChangeTypeOutNum(Long changeTypeOutNum) {
		this.changeTypeOutNum = changeTypeOutNum;
	}

	public Long getRevisionInNum() {
		return this.revisionInNum;
	}

	public void setRevisionInNum(Long revisionInNum) {
		this.revisionInNum = revisionInNum;
	}

	public Long getRevisionOutNum() {
		return this.revisionOutNum;
	}

	public void setRevisionOutNum(Long revisionOutNum) {
		this.revisionOutNum = revisionOutNum;
	}

	public Long getSaleOutNum() {
		return this.saleOutNum;
	}

	public void setSaleOutNum(Long saleOutNum) {
		this.saleOutNum = saleOutNum;
	}

	public Long getEliminateOutNum() {
		return this.eliminateOutNum;
	}

	public void setEliminateOutNum(Long eliminateOutNum) {
		this.eliminateOutNum = eliminateOutNum;
	}

	public Long getDeathOutNum() {
		return this.deathOutNum;
	}

	public void setDeathOutNum(Long deathOutNum) {
		this.deathOutNum = deathOutNum;
	}

	public Long getDeleteOutNum() {
		return this.deleteOutNum;
	}

	public void setDeleteOutNum(Long deleteOutNum) {
		this.deleteOutNum = deleteOutNum;
	}

	public Long getStockNum() {
		return this.stockNum;
	}

	public void setStockNum(Long stockNum) {
		this.stockNum = stockNum;
	}

	public Long getOldStockNum() {
		return this.oldStockNum;
	}

	public void setOldStockNum(Long oldStockNum) {
		this.oldStockNum = oldStockNum;
	}

	public Date getStatDate() {
		return this.statDate;
	}

	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}

	public Long getChangeParityInNum() {
		return this.changeParityInNum;
	}

	public void setChangeParityInNum(Long changeParityInNum) {
		this.changeParityInNum = changeParityInNum;
	}

	public Long getChangeParityOutNum() {
		return this.changeParityOutNum;
	}

	public void setChangeParityOutNum(Long changeParityOutNum) {
		this.changeParityOutNum = changeParityOutNum;
	}

}
