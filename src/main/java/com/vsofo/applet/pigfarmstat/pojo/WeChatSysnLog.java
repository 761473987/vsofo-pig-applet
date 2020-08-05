package com.vsofo.applet.pigfarmstat.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

/**
 * @Description  
 * @Author  wangtao
 * @Date 2020-07-27 
 */

@Setter
@Getter
@ToString
@TableName("WeChat_Sysn_Log" )
public class WeChatSysnLog  implements Serializable {

	private static final long serialVersionUID =  1043401931517389043L;

	/**
	 * ID
	 */
   	@TableId(type = IdType.AUTO)
	@TableField("ID")
	private Long id;

	/**
	 * 同步的条件，比如同步哪一天的数据
	 */
	@TableField("SysnCondition")
	private String sysnCondition;

	/**
	 * 同步标志
	 */
	@TableField("SysnIdentify")
	private String sysnIdentify;

	/**
	 * 是否成功
	 */
	@TableField("IfSucceed")
	private String ifSucceed;

	/**
	 * 创建时间
	 */
	@TableField("CreateTime")
	private Date createTime;

	/**
	 * 备注
	 */
	@TableField("\"Desc\"")
	private String desc;


}
