package com.vsofo.applet.pigfarmstat.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

/**
 * @Description  
 * @Author  Hunter
 * @Date 2020-07-29 
 */

@Setter
@Getter
@ToString
@TableName ( "JZZ_Hog_InvoicingStat_StatDay" )
public class JzzHogInvoicingStatStatDay  implements Serializable {

	private static final long serialVersionUID =  1664352218492640195L;

	/**
	 * 金智猪商品猪存栏标识ID
	 */
   	@TableField("ID" )
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 养殖场ID
	 */
   	@TableField("FarmID" )
	private Long farmId;

	/**
	 * 生产状态ID
	 */
   	@TableField("ProduceStatusID" )
	private Long produceStatusId;

	/**
	 * 猪只品种,默认LY-长约15
	 */
   	@TableField("PigVarietyID" )
	private Long pigVarietyId;

	/**
	 * 金智猪养殖场ID
	 */
   	@TableField("JZZ_FarmID" )
	private Long jzzFarmId;

	/**
	 * 金智猪养殖场名称
	 */
   	@TableField("JZZ_FarmName" )
	private String jzzFarmName;

	/**
	 * 金智猪生产线ID
	 */
   	@TableField("JZZ_LineID" )
	private Long jzzLineId;

	/**
	 * 金智猪栋舍ID
	 */
   	@TableField("JZZ_HouseID" )
	private Long jzzHouseId;

	/**
	 * 金智猪栋舍类型ID
	 */
   	@TableField("JZZ_HouseTypeID" )
	private Long jzzHouseTypeId;

	/**
	 * 金智猪猪批次ID
	 */
   	@TableField("JZZ_HogBatchID" )
	private Long jzzHogBatchId;

	/**
	 * 金智猪生产类型ID
	 */
   	@TableField("JZZ_ProduceStatusID" )
	private Long jzzProduceStatusId;

	/**
	 * 金智猪生产类型名称
	 */
   	@TableField("JZZ_ProduceStatusName" )
	private String jzzProduceStatusName;

	/**
	 * 期末存栏
	 */
   	@TableField("StockNum" )
	private Long stockNum;

	/**
	 * 统计日期
	 */
   	@TableField("StatDate" )
	private Date statDate;

	/**
	 * 转舍入数量
	 */
   	@TableField("ChangeInNum" )
	private Long changeInNum;

	/**
	 * 转出出数量
	 */
   	@TableField("ChangeOutNum" )
	private Long changeOutNum;

	/**
	 * 分娩入数量
	 */
   	@TableField("DeliverInNum" )
	private Long deliverInNum;

	/**
	 * 盘点入数量
	 */
   	@TableField("InventoryInNum" )
	private Long inventoryInNum;

	/**
	 * 盘点出数量
	 */
   	@TableField("InventoryOutNum" )
	private Long inventoryOutNum;

	/**
	 * 死亡出数量
	 */
   	@TableField("DeathOutNum" )
	private Long deathOutNum;

	/**
	 * 购入数量
	 */
   	@TableField("BuyInNum" )
	private Long buyInNum;

	/**
	 * 销售数量
	 */
   	@TableField("SaleOutNum" )
	private Long saleOutNum;

	/**
	 * 转生产数量
	 */
   	@TableField("ProduceOutNum" )
	private Long produceOutNum;

	/**
	 * 原库存数量=期初存栏
	 */
   	@TableField("OldStockNum" )
	private Long oldStockNum;

}
