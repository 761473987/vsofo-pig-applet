package com.vsofo.applet.pigfarmstat.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description  
 * @Author  wangtao
 * @Date 2020-07-29 
 */

@Setter
@Getter
@ToString
@TableName("JZZ_DictPigVariety")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = false)
public class JzzDictPigVariety  implements Serializable {

	private static final long serialVersionUID =  837501442195594551L;

	/**
	 * 唯一标示，自增
	 */
   	@TableField("ID")
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 3.0猪只品种ID
	 */
	@TableField("PigVarietyID")
	private Long pigVarietyId;

	/**
	 * 金智猪猪只品种ID
	 */
	@TableField("JZZ_PigVarietyID")
	private Long jzzPigVarietyId;

	/**
	 * 金智猪猪只品种名称
	 */
	@TableField("JZZ_VarietyName")
	private String jzzVarietyName;

}
