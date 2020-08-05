package com.vsofo.applet.pigfarmstat.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author  Hunter
 * @Date 2020-07-29 
 */

@Setter
@Getter
@ToString
@TableName ( "JZZ_Pig_InvoicingStat_StatDay" )
public class JzzPigInvoicingStatStatDay  implements Serializable {

	private static final long serialVersionUID =  5462342649247040388L;

	@TableId(type = IdType.AUTO)
	@TableField("ID" )
	private Long id;

	/**
	 * 养殖场ID
	 */
   	@TableField("FarmID" )
	private Long farmId;

	/**
	 * 猪只类型ID
	 */
   	@TableField("PigTypeID" )
	private Long pigTypeId;

	/**
	 * 猪只品种ID
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
   	@TableField(value = "JZZ_FarmName" )
	private String jzzFarmName;

	/**
	 * 金智猪生产线ID
	 */
   	@TableField("JZZ_LineID" )
	private Long jzzLineId;

	/**
	 * 金智猪猪只类型ID
	 */
   	@TableField("JZZ_PigTypeID" )
	private Long jzzPigTypeId;

	/**
	 * 金智猪猪只类型名称
	 */
   	@TableField("JZZ_TypeName" )
	private String jzzTypeName;

	/**
	 * 金智猪猪只品种ID
	 */
   	@TableField("JZZ_PigVarietyID" )
	private Long jzzPigVarietyId;

	/**
	 * 金智猪猪只品种名称
	 */
   	@TableField("JZZ_VarietyName" )
	private String jzzVarietyName;

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
	 * 入场数量
	 */
   	@TableField("EnterInNum" )
	private Long enterInNum;

	/**
	 * 转舍入数量
	 */
   	@TableField("ChangeHouseInNum" )
	private Long changeHouseInNum;

	/**
	 * 转舍出数量
	 */
   	@TableField("ChangeHouseOutNum" )
	private Long changeHouseOutNum;

	/**
	 * 调拨入数量
	 */
   	@TableField("TransferInNum" )
	private Long transferInNum;

	/**
	 * 调拨出数量
	 */
   	@TableField("TransferOutNum" )
	private Long transferOutNum;

	/**
	 * 转类别入数量
	 */
   	@TableField("ChangeTypeInNum" )
	private Long changeTypeInNum;

	/**
	 * 转类别出数量
	 */
   	@TableField("ChangeTypeOutNum" )
	private Long changeTypeOutNum;

	/**
	 * 调整入数量
	 */
   	@TableField("RevisionInNum" )
	private Long revisionInNum;

	/**
	 * 调整出数量
	 */
   	@TableField("RevisionOutNum" )
	private Long revisionOutNum;

	/**
	 * 销售出数量
	 */
   	@TableField("SaleOutNum" )
	private Long saleOutNum;

	/**
	 * 淘汰出数量
	 */
   	@TableField("EliminateOutNum" )
	private Long eliminateOutNum;

	/**
	 * 死亡出数量
	 */
   	@TableField("DeathOutNum" )
	private Long deathOutNum;

	/**
	 * 删除出数量
	 */
   	@TableField("DeleteOutNum" )
	private Long deleteOutNum;

	/**
	 * 原库存数量=期初存栏
	 */
   	@TableField("OldStockNum" )
	private Long oldStockNum;

}
