package com.vsofo.applet.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @Date 2020-06-12 
 */

@Setter
@Getter
@ToString
@TableName("V_Farms")
public class Farms  implements Serializable {


	/**
	 * 养殖场ID
	 */
   	@TableId(type = IdType.NONE)
	private Integer farmId;

	/**
	 * 商家ID
	 */
   	
	private Integer dealerId;

	/**
	 * 组织ID
	 */
   	
	private Integer organId;

	/**
	 * 片区ID
	 */
   	
	private Integer areaId;

	/**
	 * 养殖场名称
	 */
   	
	private String farmName;

	/**
	 * 养殖场全称
	 */
   	
	private String farmFullName;

	/**
	 * 养殖场代码
	 */
   	
	private String farmCode;

	/**
	 * 公司代码
	 */
   	
	private String companyCode;

	/**
	 * 公司全称
	 */
   	
	private String companyFullName;

	/**
	 * 猪只仓库编码
	 */
   	
	private String pigWarehouseCode;

	/**
	 * 省代码
	 */
   	
	private String provinceCode;

	/**
	 * 市代码
	 */
   	
	private String cityCode;

	/**
	 * 区代码
	 */
   	
	private String townCode;

	/**
	 * 养殖类型：1：繁殖场；2：育肥场；3：公猪站；4：隔离舍；5：繁殖育肥一体；6：沼气站；7：区域公司
	 */
   	
	private Integer breedingType;

	/**
	 * 养殖规模
	 */
   	
	private Integer breedingScale;

	/**
	 * 育种层级：1：GP；2：GGP；3：PS
	 */
   	
	private Integer breedingGrade;

	/**
	 * 养殖场育种编码
	 */
   	
	private String breedingCode;

	/**
	 * 饲养方式：1：自养；2：放养
	 */
   	
	private Integer feedingMethod;

	/**
	 * 产权归属：1：自建；2：合资；3：租赁
	 */
   	
	private Integer ownership;

	/**
	 * 生产模式：1：一点式；2：二点式；3：三点式；4：多点式
	 */
   	
	private Integer productionMode;

	/**
	 * 投产日期
	 */
   	
	private Date productionData;

	/**
	 * 养殖场状态：1：正常运营；2：停产；3：待投产；4：复产
	 */
   	
	private Integer farmStatus;

	/**
	 * 养殖场地址
	 */
   	
	private String farmAddress;

	/**
	 * 养殖场面积
	 */
   	
	private Double farmArea;

	/**
	 * 电话号码
	 */
   	
	private String phone;

	/**
	 * 传真号码
	 */
   	
	private String fax;

	/**
	 * 邮政编码
	 */
   	
	private String zipCode;

	/**
	 * 设计公猪规模
	 */
   	
	private Integer targetBoar;

	/**
	 * 设计母猪规模
	 */
   	
	private Integer targetSow;

	/**
	 * 设计育肥规模
	 */
   	
	private Integer targetHog;

	/**
	 * 目标线别数
	 */
   	
	private Integer targetLine;

	/**
	 * 目标栋舍数
	 */
   	
	private Integer targetHouse;

	/**
	 * 目标出栏数
	 */
   	
	private Integer targetSlaughter;

	/**
	 * 实际产能
	 */
   	
	private Integer productionCapacity;

	/**
	 * 养殖场说明
	 */
   	
	private String farmIntro;

	/**
	 * 序号
	 */
   	
	private Integer orderId;

	/**
	 * 是否开启NC同步
	 */
   	
	private Boolean isNcSyncEnable;

	/**
	 * 是否停用
	 */
   	
	private Boolean isTop;

	/**
	 * 是否删除
	 */
   	
	private Boolean isDeleted;

	/**
	 * 饲喂标准ID
	 */
   	
	private Integer feedingProgramId;

	/**
	 * 免疫标准ID
	 */
   	
	private Integer immunityProgramId;

	/**
	 * 保健标准ID
	 */
   	
	private Integer healthCareProgramId;

	/**
	 * Sap编码
	 */
   	
	private String sapCode;

	/**
	 * Sap编码
	 */
   	
	private String sapCompanyCode;



}
