package com.vsofo.applet.pigfarmstat.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName ( "JZZ_Hog_SaleStat_StatDay" )
public class JzzHogSaleStatStatDay  implements Serializable {

	private static final long serialVersionUID =  2398391769267626938L;

   	@TableField("养殖场ID3" )
	private Long 养殖场Id3;

   	@TableField("养殖场ID" )
	private Long 养殖场Id;

   	@TableField("养殖场" )
	private String 养殖场;

   	@TableField("生产线ID" )
	private Long 生产线Id;

   	@TableField("生产线" )
	private String 生产线;

   	@TableField("生产状态ID3" )
	private Long 生产状态Id3;

   	@TableField("生产状态3" )
	private String 生产状态3;

   	@TableField("猪阶段ID" )
	private Long 猪阶段Id;

   	@TableField("猪阶段" )
	private String 猪阶段;

   	@TableField("生产状态ID" )
	private Long 生产状态Id;

   	@TableField("生产状态" )
	private String 生产状态;

   	@TableField("品种ID3" )
	private Long 品种Id3;

   	@TableField("品种ID" )
	private Long 品种Id;

   	@TableField("品种名称" )
	private String 品种名称;

   	@TableField("销售数量" )
	private Long 销售数量;

   	@TableField("统计日期" )
	private Date 统计日期;

}
