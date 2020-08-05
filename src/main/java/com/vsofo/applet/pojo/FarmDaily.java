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
@TableName("tbl_FarmDaily" )
public class FarmDaily  implements Serializable {


	/**
	 * ID
	 */
   	@TableId(type = IdType.AUTO)
	private Integer id;

	/**
	 * 程序ID
	 */
   	
	private String appId;

	/**
	 * 统计日期
	 */
   	
	private Date date;

	/**
	 * 养殖场
	 */
   	
	private Integer farmId;

	/**
	 * 存栏总数
	 */
   	
	private Integer inventoryTotal;

	/**
	 * 采购数量
	 */
   	
	private Integer purchase;

	/**
	 * 公猪数
	 */
   	
	private Integer boar;

	/**
	 * 母猪数
	 */
   	
	private Integer sow;

	/**
	 * 配怀舍母猪数
	 */
   	
	private Integer sowBreedingHouse;

	/**
	 * 分娩舍母猪数
	 */
   	
	private Integer sowDeliveryHouse;

	/**
	 * 后备舍母猪数
	 */
   	
	private Integer sowReserveHouse;

	/**
	 * 生产母猪
	 */
   	
	private Integer sowProduct;

	/**
	 * 后备母猪
	 */
   	
	private Integer sowReserve;

	/**
	 * 生产公猪
	 */
   	
	private Integer boarProduct;

	/**
	 * 后备公猪
	 */
   	
	private Integer boarReserve;

	/**
	 * 商品猪数
	 */
   	
	private Integer hog;

	/**
	 * 哺乳仔猪
	 */
   	
	private Integer hogLet;

	/**
	 * 保育猪
	 */
   	
	private Integer hogGrower;

	/**
	 * 育肥猪
	 */
   	
	private Integer hogFinisher;

	/**
	 * 哺乳仔猪公
	 */
   	
	private Integer hogLetMale;

	/**
	 * 哺乳仔猪母
	 */
   	
	private Integer hogLetFemale;

	/**
	 * 哺乳仔猪阉
	 */
   	
	private Integer hogLetCastrated;

	/**
	 * 保育猪公
	 */
   	
	private Integer hogGrowerMale;

	/**
	 * 保育猪母
	 */
   	
	private Integer hogGrowerFemale;

	/**
	 * 保育猪阉
	 */
   	
	private Integer hogGrowerCastrated;

	/**
	 * 育肥猪公
	 */
   	
	private Integer hogFinisherMale;

	/**
	 * 育肥猪母
	 */
   	
	private Integer hogFinisherFemale;

	/**
	 * 育肥猪阉
	 */
   	
	private Integer hogFinisherCastrated;

	/**
	 * 配种数
	 */
   	
	private Integer mating;

	/**
	 * 分娩数
	 */
   	
	private Integer delivery;

	/**
	 * 死亡数
	 */
   	
	private Integer death;

	/**
	 * 健仔公
	 */
   	
	private Integer healthMale;

	/**
	 * 健仔母
	 */
   	
	private Integer healthFemale;

	/**
	 * 弱仔公
	 */
   	
	private Integer weakMale;

	/**
	 * 弱仔母
	 */
   	
	private Integer weakFemale;

	/**
	 * 死胎数
	 */
   	
	private Integer stillbirth;

	/**
	 * 死亡哺乳仔猪公
	 */
   	
	private Integer deathHogLetMale;

	/**
	 * 死亡哺乳仔猪母
	 */
   	
	private Integer deathHogLetFemale;

	/**
	 * 死亡哺乳仔猪阉
	 */
   	
	private Integer deathHogLetCastrated;

	/**
	 * 死亡保育猪公
	 */
   	
	private Integer deathHogGrowerMale;

	/**
	 * 死亡保育猪母
	 */
   	
	private Integer deathHogGrowerFemale;

	/**
	 * 死亡保育猪阉
	 */
   	
	private Integer deathHogGrowerCastrated;

	/**
	 * 死亡育肥猪公
	 */
   	
	private Integer deathHogFinisherMale;

	/**
	 * 死亡育肥猪母
	 */
   	
	private Integer deathHogFinisherFemale;

	/**
	 * 死亡育肥猪阉
	 */
   	
	private Integer deathHogFinisherCastrated;

	/**
	 * 死亡种公猪
	 */
   	
	private Integer deathBoar;

	/**
	 * 死亡种母猪
	 */
   	
	private Integer deathSow;

	/**
	 * 死亡后备公猪
	 */
   	
	private Integer deathReserveBoar;

	/**
	 * 死亡后备母猪
	 */
   	
	private Integer deathReserveSow;

	/**
	 * 哺乳仔猪公猪销售
	 */
   	
	private Integer saleHogLetMale;

	/**
	 * 哺乳仔猪母猪销售
	 */
   	
	private Integer saleHogLetFemale;

	/**
	 * 哺乳仔猪阉猪销售
	 */
   	
	private Integer saleHogLetCastrated;

	/**
	 * 哺乳仔猪销售重量
	 */
   	
	private Double saleHogLetWeight;

	/**
	 * 哺乳仔猪销售金额
	 */
   	
	private Double saleHogLetPrice;

	/**
	 * 保育猪公猪销售
	 */
   	
	private Integer saleHogGrowerMale;

	/**
	 * 保育猪母猪销售
	 */
   	
	private Integer saleHogGrowerFemale;

	/**
	 * 保育猪阉猪销售
	 */
   	
	private Integer saleHogGrowerCastrated;

	/**
	 * 保育猪销售重量
	 */
   	
	private Double saleHogGrowerWeight;

	/**
	 * 保育猪销售价格
	 */
   	
	private Double saleHogGrowerPrice;

	/**
	 * 育肥猪公猪销售
	 */
   	
	private Integer saleHogFinisherMale;

	/**
	 * 育肥猪母猪销售
	 */
   	
	private Integer saleHogFinisherFemale;

	/**
	 * 育肥猪阉猪销售
	 */
   	
	private Integer saleHogFinisherCastrated;

	/**
	 * 育肥猪销售重量
	 */
   	
	private Double saleHogFinisherWeight;

	/**
	 * 育肥猪销售价格
	 */
   	
	private Double saleHogFinisherPrice;

	/**
	 * 生产公猪销售
	 */
   	
	private Integer saleProductBoar;

	/**
	 * 生产母猪销售
	 */
   	
	private Integer saleProductSow;

	/**
	 * 种猪销售重量
	 */
   	
	private Double saleProductWeight;

	/**
	 * 种猪销售价格
	 */
   	
	private Double saleProductPrice;

	/**
	 * 超大肥猪公猪销售
	 */
   	
	private Integer saleBigHogMale;

	/**
	 * 超大肥猪母猪销售
	 */
   	
	private Integer saleBigHogFemale;

	/**
	 * 超大肥猪阉猪销售
	 */
   	
	private Integer saleBigHogCastrated;

	/**
	 * 超大肥猪销售重量
	 */
   	
	private Double saleBigHogWeight;

	/**
	 * 超大肥猪销售价格
	 */
   	
	private Double saleBigHogPrice;

	/**
	 * 残次猪公猪销售
	 */
   	
	private Integer saleDefectiveMale;

	/**
	 * 残次猪母猪销售
	 */
   	
	private Integer saleDefectiveFemale;

	/**
	 * 残次猪阉猪销售
	 */
   	
	private Integer saleDefectiveCastrated;

	/**
	 * 残次猪销售重量
	 */
   	
	private Double saleDefectiveWeight;

	/**
	 * 残次猪销售价格
	 */
   	
	private Double saleDefectivePrice;

	/**
	 * 育肥公猪自宰
	 */
   	
	private Integer slaughterHogFinisherMale;

	/**
	 * 育肥母猪自宰
	 */
   	
	private Integer slaughterHogFinisherFemale;

	/**
	 * 育肥阉猪自宰
	 */
   	
	private Integer slaughterHogFinisherCastrated;

	/**
	 * 育肥猪自宰重量
	 */
   	
	private Double slaughterHogFinisherWeight;

	/**
	 * 后备公猪自宰
	 */
   	
	private Integer slaughterReserveBoar;

	/**
	 * 后备母猪自宰
	 */
   	
	private Integer slaughterReserveSow;

	/**
	 * 后备猪自宰重量
	 */
   	
	private Double slaughterReserveWeight;

	/**
	 * 淘汰公猪
	 */
   	
	private Integer eliminateBoar;

	/**
	 * 淘汰母猪
	 */
   	
	private Integer eliminateSow;

	/**
	 * 淘汰种猪重量
	 */
   	
	private Double eliminateWeight;

	/**
	 * 淘汰后备公猪
	 */
   	
	private Integer eliminateReserveBoar;

	/**
	 * 淘汰后备母猪
	 */
   	
	private Integer eliminateReserveSow;

	/**
	 * 淘汰后备猪重量
	 */
   	
	private Double eliminateReserveWeight;

	/**
	 * 自留后备公猪
	 */
   	
	private Integer retainReserveBoar;

	/**
	 * 自留后备母猪
	 */
   	
	private Integer retainReserveSow;

	/**
	 * 自留后备猪重量
	 */
   	
	private Double retainReserveWeight;

	/**
	 * 合计出栏头数公
	 */
   	
	private Integer outTotalMale;

	/**
	 * 合计出栏头数母
	 */
   	
	private Integer outTotalFemale;

	/**
	 * 合计出栏头数阉
	 */
   	
	private Integer outTotalCastrated;

	/**
	 * 合计出栏重量
	 */
   	
	private Double outTotalWeight;

}
