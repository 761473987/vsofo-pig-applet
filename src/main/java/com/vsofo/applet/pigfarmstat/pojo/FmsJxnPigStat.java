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
 * @Author  wangtao
 * @Date 2020-07-23 
 */

@Setter
@Getter
@ToString
@TableName("FMS_JXN_PIG_STAT")
public class FmsJxnPigStat  implements Serializable {

	private static final long serialVersionUID =  988772196904882499L;

	/**
	 * ID
	 */

	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 省份
	 */
   	
	private String province;

	/**
	 * 服务部
	 */
   	
	private String department;

	/**
	 * 技术服务员
	 */
   	
	private String attendant;

	/**
	 * 养户名
	 */
   	
	private String keepAccount;

	/**
	 * 合作类型
	 */
   	
	private String cooperation;

	/**
	 * 批次号
	 */
   	
	private String batchNo;

	/**
	 * 猪苗来源
	 */
   	
	private String pigletSource;

	/**
	 * 猪只类型
	 */
   	
	private String pigType;

	/**
	 * 购入
	 */
   	
	private Long pigBuy;

	/**
	 * 死亡数
	 */
   	
	private Long pigDeath;

	/**
	 * 销售
	 */
   	
	private Long pigSale;

	/**
	 * 期末存栏
	 */
   	
	private Long pigCountEnd;

	/**
	 * 养殖场ID
	 */
   	
	private Long farmId;

	/**
	 * 统计日期
	 */
	private Date date;

	@TableField(exist=false)
	private boolean isUpdate;

}
