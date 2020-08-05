package com.vsofo.applet.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description  
 * @Author  wangtao
 * @Date 2020-06-11 
 */

@Setter
@Getter
@ToString
@TableName("WeCat_DataType" )
public class WeCatDataType  implements Serializable {

	private static final long serialVersionUID =  5674080728620774081L;

	/**
	 * DataTypeID
	 */private String code;

	/**
	 * 名称
	 */
	private String name;

	private String groupId;

}
