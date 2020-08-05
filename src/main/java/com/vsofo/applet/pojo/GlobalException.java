package com.vsofo.applet.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vsofo.applet.pigfarmstat.log.LogAction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author  wangtao
 * @Date 2020-06-10 
 */

@Setter
@Getter
@ToString
@TableName("Global_Exception")
@NoArgsConstructor
public class GlobalException  implements Serializable, LogAction {

	private static final long serialVersionUID =  7432143050551491588L;

	/**
	 * 异常表ID
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 系统ID
	 */
	private String appId;

	/**
	 * 用户ID
	 */
	private Integer userId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 请求地址
	 */
	private String requestUrl;

	/**
	 * 方法信息
	 */
	private String methodInfo;

	/**
	 * 错误信息
	 */
	private String errMsg;

	/**
	 * 功能名称
	 */
	private String functionName;

	/**
	 * 等级
	 */
	private String lev;

	public GlobalException(Integer userId, String requestUrl, String methodInfo, String errMsg, String lev) {
		this.createTime = new Date();
		this.setAppId("WeChat");
		this.userId = userId;
		this.requestUrl = requestUrl;
		this.methodInfo = methodInfo;
		this.errMsg = errMsg;
		this.lev = lev;
	}
}
