package com.vsofo.applet.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author  wangtao
 * @Date 2020-05-14
 */
public class HogInvoicingStatStatDay  implements Serializable {

	private static final long serialVersionUID =  1296079292174174030L;

	/**
	 * 商品猪存栏表
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
	 * 栋舍ID
	 */
	private Long houseId;

	/**
	 * 栋舍类型ID
	 */
	private Long houseTypeId;

	/**
	 * 批次编号ID
	 */
	private Long hogBatchId;

	/**
	 * 栋舍生产状态ID
	 */
	private Long produceStatusId;

	/**
	 * 转舍入数量
	 */
	private Long changeInNum;

	/**
	 * 转舍入重量
	 */
	private BigDecimal changeInWeight;

	/**
	 * 转出出数量
	 */
	private Long changeOutNum;

	/**
	 * 转舍出重量
	 */
	private BigDecimal changeOutWeight;

	/**
	 * 分娩入数量
	 */
	private Long deliverInNum;

	/**
	 * 分娩入重量
	 */
	private BigDecimal deliverInWeight;

	/**
	 * 盘点入数量
	 */
	private Long inventoryInNum;

	/**
	 * 盘点入重量
	 */
	private BigDecimal inventoryInWeight;

	/**
	 * 盘点出数量
	 */
	private Long inventoryOutNum;

	/**
	 * 盘点出重量
	 */
	private BigDecimal inventoryOutWeight;

	/**
	 * 死亡出数量
	 */
	private Long deathOutNum;

	/**
	 * 死亡出重量
	 */
	private BigDecimal deathOutWeight;

	/**
	 * 购入数量
	 */
	private Long buyInNum;

	/**
	 * 购入重量
	 */
	private BigDecimal buyInWeight;

	/**
	 * 销售数量
	 */
	private Long saleOutNum;

	/**
	 * 销售重量
	 */
	private BigDecimal saleOutWeight;

	/**
	 * 转生产数量
	 */
	private Long produceOutNum;

	/**
	 * 转生产重量
	 */
	private BigDecimal produceOutWeight;

	/**
	 * 调拨调入数量
	 */
	private Long allocationInNum;

	/**
	 * 调拨调入重量
	 */
	private BigDecimal allocationInWeight;

	/**
	 * 调拨调出数量
	 */
	private Long allocationOutNum;

	/**
	 * 调拨调出重量
	 */
	private BigDecimal allocationOutWeight;

	/**
	 * 存栏数量
	 */
	private Long stockNum;

	/**
	 * 老存栏数量
	 */
	private Long oldStockNum;

	/**
	 * 存栏重量（显示时从期末盘点表取）
	 */
	private BigDecimal stockWeight;

	/**
	 * 统计日期
	 */
	private Date statDate;

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

	public Long getHouseId() {
		return this.houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public Long getHouseTypeId() {
		return this.houseTypeId;
	}

	public void setHouseTypeId(Long houseTypeId) {
		this.houseTypeId = houseTypeId;
	}

	public Long getHogBatchId() {
		return this.hogBatchId;
	}

	public void setHogBatchId(Long hogBatchId) {
		this.hogBatchId = hogBatchId;
	}

	public Long getProduceStatusId() {
		return this.produceStatusId;
	}

	public void setProduceStatusId(Long produceStatusId) {
		this.produceStatusId = produceStatusId;
	}

	public Long getChangeInNum() {
		return this.changeInNum;
	}

	public void setChangeInNum(Long changeInNum) {
		this.changeInNum = changeInNum;
	}

	public BigDecimal getChangeInWeight() {
		return this.changeInWeight;
	}

	public void setChangeInWeight(BigDecimal changeInWeight) {
		this.changeInWeight = changeInWeight;
	}

	public Long getChangeOutNum() {
		return this.changeOutNum;
	}

	public void setChangeOutNum(Long changeOutNum) {
		this.changeOutNum = changeOutNum;
	}

	public BigDecimal getChangeOutWeight() {
		return this.changeOutWeight;
	}

	public void setChangeOutWeight(BigDecimal changeOutWeight) {
		this.changeOutWeight = changeOutWeight;
	}

	public Long getDeliverInNum() {
		return this.deliverInNum;
	}

	public void setDeliverInNum(Long deliverInNum) {
		this.deliverInNum = deliverInNum;
	}

	public BigDecimal getDeliverInWeight() {
		return this.deliverInWeight;
	}

	public void setDeliverInWeight(BigDecimal deliverInWeight) {
		this.deliverInWeight = deliverInWeight;
	}

	public Long getInventoryInNum() {
		return this.inventoryInNum;
	}

	public void setInventoryInNum(Long inventoryInNum) {
		this.inventoryInNum = inventoryInNum;
	}

	public BigDecimal getInventoryInWeight() {
		return this.inventoryInWeight;
	}

	public void setInventoryInWeight(BigDecimal inventoryInWeight) {
		this.inventoryInWeight = inventoryInWeight;
	}

	public Long getInventoryOutNum() {
		return this.inventoryOutNum;
	}

	public void setInventoryOutNum(Long inventoryOutNum) {
		this.inventoryOutNum = inventoryOutNum;
	}

	public BigDecimal getInventoryOutWeight() {
		return this.inventoryOutWeight;
	}

	public void setInventoryOutWeight(BigDecimal inventoryOutWeight) {
		this.inventoryOutWeight = inventoryOutWeight;
	}

	public Long getDeathOutNum() {
		return this.deathOutNum;
	}

	public void setDeathOutNum(Long deathOutNum) {
		this.deathOutNum = deathOutNum;
	}

	public BigDecimal getDeathOutWeight() {
		return this.deathOutWeight;
	}

	public void setDeathOutWeight(BigDecimal deathOutWeight) {
		this.deathOutWeight = deathOutWeight;
	}

	public Long getBuyInNum() {
		return this.buyInNum;
	}

	public void setBuyInNum(Long buyInNum) {
		this.buyInNum = buyInNum;
	}

	public BigDecimal getBuyInWeight() {
		return this.buyInWeight;
	}

	public void setBuyInWeight(BigDecimal buyInWeight) {
		this.buyInWeight = buyInWeight;
	}

	public Long getSaleOutNum() {
		return this.saleOutNum;
	}

	public void setSaleOutNum(Long saleOutNum) {
		this.saleOutNum = saleOutNum;
	}

	public BigDecimal getSaleOutWeight() {
		return this.saleOutWeight;
	}

	public void setSaleOutWeight(BigDecimal saleOutWeight) {
		this.saleOutWeight = saleOutWeight;
	}

	public Long getProduceOutNum() {
		return this.produceOutNum;
	}

	public void setProduceOutNum(Long produceOutNum) {
		this.produceOutNum = produceOutNum;
	}

	public BigDecimal getProduceOutWeight() {
		return this.produceOutWeight;
	}

	public void setProduceOutWeight(BigDecimal produceOutWeight) {
		this.produceOutWeight = produceOutWeight;
	}

	public Long getAllocationInNum() {
		return this.allocationInNum;
	}

	public void setAllocationInNum(Long allocationInNum) {
		this.allocationInNum = allocationInNum;
	}

	public BigDecimal getAllocationInWeight() {
		return this.allocationInWeight;
	}

	public void setAllocationInWeight(BigDecimal allocationInWeight) {
		this.allocationInWeight = allocationInWeight;
	}

	public Long getAllocationOutNum() {
		return this.allocationOutNum;
	}

	public void setAllocationOutNum(Long allocationOutNum) {
		this.allocationOutNum = allocationOutNum;
	}

	public BigDecimal getAllocationOutWeight() {
		return this.allocationOutWeight;
	}

	public void setAllocationOutWeight(BigDecimal allocationOutWeight) {
		this.allocationOutWeight = allocationOutWeight;
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

	public BigDecimal getStockWeight() {
		return this.stockWeight;
	}

	public void setStockWeight(BigDecimal stockWeight) {
		this.stockWeight = stockWeight;
	}

	public Date getStatDate() {
		return this.statDate;
	}

	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}

}
