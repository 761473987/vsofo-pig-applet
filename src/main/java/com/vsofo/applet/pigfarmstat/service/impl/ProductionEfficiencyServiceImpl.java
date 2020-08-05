package com.vsofo.applet.pigfarmstat.service.impl;

import com.vsofo.applet.pigfarmstat.data.DataTypeDic;
import com.vsofo.applet.pigfarmstat.data.ProductionEfficiencyMula;
import com.vsofo.applet.pigfarmstat.dos.indicators.NpdMonthDo;
import com.vsofo.applet.pigfarmstat.dto.indicators.IndicatorsDto;
import com.vsofo.applet.pigfarmstat.mapper.FarmsMapper;
import com.vsofo.applet.pigfarmstat.mapper.ProductionMapper;
import com.vsofo.applet.pigfarmstat.mapper.ProductionMonthMapper;
import com.vsofo.applet.pigfarmstat.pojo.DataType;
import com.vsofo.applet.pigfarmstat.pojo.Production;
import com.vsofo.applet.pigfarmstat.pojo.User;
import com.vsofo.applet.pigfarmstat.service.ProductionEfficiencyService;
import com.vsofo.applet.pigfarmstat.vo.indicators.IndDetailVo;
import com.vsofo.applet.utils.DateUtils;
import com.vsofo.applet.utils.NumberUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class ProductionEfficiencyServiceImpl implements ProductionEfficiencyService {

    @Autowired
    private ProductionMonthMapper productionMonthMapper;

    @Autowired
    private FarmsMapper farmsMapper;

    @Autowired
    private DataTypeDic dataTypeDic;

    @Autowired
    private ProductionMapper productionMapper;

    private final double defuat = -1;

    @Override
    public List<IndDetailVo> getProduction(IndicatorsDto indicatorsDto, String dateType, String groupId, User user) {
        long startTime = System.currentTimeMillis();
        setIndicatorsDto(indicatorsDto,dateType);
        long days = getDays(indicatorsDto);
        List<Production> totalAmount = null;
        List<Production> avgAmount = null;
        if (dateType.equals("1")){
            totalAmount = productionMapper.totalAmountWeek(indicatorsDto,user);
            avgAmount = productionMapper.avgAmountWeek(indicatorsDto,user);
        }else {
            totalAmount = productionMonthMapper.totalAmount(indicatorsDto,user);
            avgAmount = productionMonthMapper.avgAmount(indicatorsDto,user);
        }
        List<IndDetailVo> list = null;
        switch (groupId){
            case "1": list = getPzcj(indicatorsDto,dateType,totalAmount,avgAmount,user);break;
            case "2": list = getpzfml(indicatorsDto,dateType,totalAmount,avgAmount,user);break;
            case "3": list = getfmcj(indicatorsDto,dateType,totalAmount,avgAmount,days,user);break;
            case "4": list = getDncj(indicatorsDto,dateType,totalAmount,user);break;
            case "5": list = getCl(indicatorsDto,dateType,totalAmount,avgAmount,days,user);break;
            case "6": list = getZpc(indicatorsDto,dateType,totalAmount,user);break;
        }
        setIndetailVos(list);
        return list;
    }

    private long getDays(IndicatorsDto indicatorsDto) {
        String beginDate = indicatorsDto.getBeginDate();
        String endDate = indicatorsDto.getEndDate();
        LocalDate parseBegin = LocalDate.parse(beginDate);
        LocalDate parseEnd = LocalDate.parse(endDate);
        return parseEnd.toEpochDay() - parseBegin.toEpochDay() + 1 ;
    }

    /**
     * 获取猪批次
     * @param indicatorsDto
     * @param dateType
     * @param totalAmount
     * @param user
     * @return
     */
    private List<IndDetailVo> getZpc(IndicatorsDto indicatorsDto, String dateType, List<Production> totalAmount, User user) {
        List<IndDetailVo> indDetailVos = new ArrayList<>();
        //哺乳仔猪平均存栏	筛选哺乳仔猪大于0的记录求平均值。所选期间段每天期末头数之和÷统计天数（大于0存栏的记录天数）
        double przzpjcl = pd(totalAmount,"66");
        indDetailVos.add(new IndDetailVo("66",przzpjcl));
        //保育仔猪平均存栏	 筛选保育仔猪大于0的记录求平均值。所选期间段每天期末头数之和÷统计天数（大于0存栏的记录天数）
        double byzpjcl = pd(totalAmount,"67");
        indDetailVos.add(new IndDetailVo("67",byzpjcl));
        //育肥猪平均存栏	筛选育肥猪大于0的记录求平均值。所选期间段每天期末头数之和÷统计天数（大于0存栏的记录天数）
        double yfzpjcl = pd(totalAmount,"68");
        indDetailVos.add(new IndDetailVo("68",yfzpjcl));
        //转保育数	商品猪转舍记录转入栋舍类型为保育的数量合计（除去保育转保育）
        double zbys = pd(totalAmount, "69");
        indDetailVos.add(new IndDetailVo("69",zbys));
        //转育肥数	商品猪转舍记录转入栋舍类型为育肥的数量合计（除去育肥转育肥）
        double zyfs = pd(totalAmount, "70");
        indDetailVos.add(new IndDetailVo("70",zyfs));
        //哺乳仔猪死亡数	哺乳仔猪死亡数合计
        double brzzsws = pd(totalAmount, "76");
        indDetailVos.add(new IndDetailVo("76",brzzsws));
        //保育仔猪死亡数	保育仔猪死亡数合计
        double byzzsws = pd(totalAmount, "77");
        indDetailVos.add(new IndDetailVo("77",byzzsws));
        //育肥猪死亡数	育肥猪死亡数合计
        double yfzsws = pd(totalAmount, "78");
        indDetailVos.add(new IndDetailVo("78",yfzsws));
        //哺乳仔猪死亡率	哺乳仔猪死亡数 ÷ 哺乳仔猪平均存栏
        double brzzswl = ProductionEfficiencyMula.divide(brzzsws,przzpjcl)*100;
        indDetailVos.add(new IndDetailVo("71",brzzswl));
        double qjfmzzs = pd(totalAmount, "87");//期间分娩仔猪数
        //哺乳仔猪死亡率1	1 - 统计期内断奶仔猪数 ÷ 对应母猪产活仔数
        double brzzswl2 = (1-ProductionEfficiencyMula.divide(pd(totalAmount,"44"),pd(totalAmount,"45")))*100;
        indDetailVos.add(new IndDetailVo("109",brzzswl2));
        //保育仔猪死亡率	保育仔猪死亡数 ÷ 保育仔猪平均存栏
        double byzzswl = ProductionEfficiencyMula.divide(byzzsws,byzpjcl)*100;
        indDetailVos.add(new IndDetailVo("72",byzzswl));
        //育肥猪死亡率	育肥猪死亡数 ÷ 育肥猪平均存栏
        double yfzswl = ProductionEfficiencyMula.divide(yfzsws,yfzpjcl)*100;
        indDetailVos.add(new IndDetailVo("73",yfzswl));
        //商品猪综合死亡率	商品猪死亡数 ÷ 商品猪平均存栏
        double spzsws = brzzsws + byzzsws + yfzsws;//商品猪死亡数
        double spzpjcl = przzpjcl + byzpjcl + yfzpjcl;//商品猪平均存栏
        double spzzhswl = ProductionEfficiencyMula.divide(spzsws,spzpjcl)*100;
        indDetailVos.add(new IndDetailVo("74",spzzhswl));
        //期间哺乳仔猪死亡率	哺乳仔猪死亡数 ÷ 期间分娩仔猪数
        double qjbrzzswl = ProductionEfficiencyMula.divide(brzzsws,qjfmzzs)*100;
        indDetailVos.add(new IndDetailVo("90",qjbrzzswl));
        //期间保育仔猪死亡率	保育仔猪死亡数 ÷ 期间转保育仔猪数
        double qjbyzzswl = ProductionEfficiencyMula.divide(byzzsws,zbys)*100;
        indDetailVos.add(new IndDetailVo("91",qjbyzzswl));

        //期间育肥猪死亡率	育肥猪死亡数 ÷ 期间转育肥猪数
        double qjyfzswl = ProductionEfficiencyMula.divide(yfzsws,zyfs)*100;
        indDetailVos.add(new IndDetailVo("92",qjyfzswl));
        //期间综合死亡率	商品猪死亡数 ÷ 期间转入数量
        double qjzhswl = ProductionEfficiencyMula.divide(spzsws,zbys+zyfs+qjfmzzs)*100;
        indDetailVos.add(new IndDetailVo("93",qjzhswl));
        return indDetailVos;

    }

    /**
     * 存栏
     * @param indicatorsDto
     * @param dateType
     * @param totalAmount
     * @param avgAmount
     * @param days
     * @param user
     * @return
     */
    private List<IndDetailVo> getCl(IndicatorsDto indicatorsDto, String dateType, List<Production> totalAmount, List<Production> avgAmount, long days, User user) {
        List<IndDetailVo> indDetailVos = new ArrayList<>();
        //平均能繁母猪存栏	每天存栏头数（配种1次以上含1次的母猪）相加÷期间天数（大于0存栏的记录天数）
        double pjnfmzcl = pd(totalAmount,"55");
        indDetailVos.add(new IndDetailVo("55",pjnfmzcl));
        //平均后备母猪存栏	每天有耳号后备母猪存栏头数相加÷期间天数（大于0存栏的记录天数）
        double pjhbmzcl = pd(totalAmount,"56");
        indDetailVos.add(new IndDetailVo("56",pjhbmzcl));
        //平均胎龄	能繁母猪的平均胎龄
        double pjtl = pd(avgAmount,"57");
        indDetailVos.add(new IndDetailVo("57",pjtl));

        //母猪期末存栏头数	生产母猪期末存栏头数
        double mzqmclts = pd(totalAmount, "101");
        indDetailVos.add(new IndDetailVo("101",mzqmclts));

        //后备母猪期末存栏头数	有耳号后备母猪期末存栏头数 未确定  todo 多场为0
        double hbmzqmclts = pd(totalAmount, "102");
        indDetailVos.add(new IndDetailVo("102",hbmzqmclts));

        //新增母猪头数	购入、其他猪场调入的种母猪,场内后备首配转生产母猪
        double xzmzts = pd(totalAmount, "58");
        indDetailVos.add(new IndDetailVo("58",xzmzts));

        //母猪更新率=统计期内新增母猪头数÷统计期内平均能繁母猪存栏*365.25/统计期天数
        double mzgxl =  pd(totalAmount,"58") / pjnfmzcl * 365.25 / days * 100;
        indDetailVos.add(new IndDetailVo("59",mzgxl));
        //淘汰母猪头数	决定淘汰并已经离开猪场的种母猪头数
        double ttmzts = pd(totalAmount, "60");
        indDetailVos.add(new IndDetailVo("60",ttmzts));
        //淘汰率	统计期内淘汰母猪头数÷统计期内平均能繁母猪存栏*365.25/统计期天数
        double ttl = ttmzts / pjnfmzcl * 365.25 / days * 100;
        indDetailVos.add(new IndDetailVo("61",ttl));
        //死亡母猪头数	死亡的种母猪头数
        double swmzts = pd(totalAmount, "62");
        indDetailVos.add(new IndDetailVo("62",swmzts));

        //死亡率	统计期内死亡母猪头数÷统计期内平均能繁母猪存栏*365.25/统计期天数
        double dnqswl = ProductionEfficiencyMula.divide(swmzts,pjnfmzcl)*365/days*100;
        indDetailVos.add(new IndDetailVo("63",dnqswl));

        //淘汰母猪平均胎龄	状态为淘汰的母猪平均胎龄
        double ttmzpjtl = productionMapper.findTtmzpjtl(indicatorsDto,user);
        indDetailVos.add(new IndDetailVo("64",ttmzpjtl));
        //平均母猪NPD	(统计期间种猪群NPD天数总和÷统计期间种猪群平均存栏)×(365÷统计期间天数)
        double npd = getNpd(indicatorsDto,user);
        indDetailVos.add(new IndDetailVo("65",npd));
        //平均公猪存栏	每天存栏头数（生产公猪） 加÷期间天数（大于0存栏的记录天数）
        double pjgzcl  = pd(avgAmount, "104");
        indDetailVos.add(new IndDetailVo("104",pjgzcl));

        //平均后备公猪存栏	每天存栏头数（后备公猪）相加÷期间天数（大于0存栏的记录天数）
        double pjhbgzcl  = pd(avgAmount, "105");
        indDetailVos.add(new IndDetailVo("105",pjhbgzcl));
        return indDetailVos;
    }

    private List<IndDetailVo> getDncj(IndicatorsDto indicatorsDto, String dateType, List<Production> totalAmount, User user) {
        List<IndDetailVo> indDetailVos = new ArrayList<>();
        //断奶母猪头数	断奶母猪头数
        double dnmzts = pd(totalAmount, "42");
        indDetailVos.add(new IndDetailVo("42",dnmzts));
        //断奶仔猪数	断奶母猪断奶仔猪数量之和
        double dnzzts = pd(totalAmount, "44");
        indDetailVos.add(new IndDetailVo("44",dnzzts));
        //断奶对应的出生活仔数	断奶对应的出生活仔数
        double dndydcshzs = pd(totalAmount, "45");
        indDetailVos.add(new IndDetailVo("45",dndydcshzs));
        //每头母猪断奶数	断奶仔猪数÷断奶母猪头数
        double mtmzdns = ProductionEfficiencyMula.divide(pd(totalAmount,"44"),pd(totalAmount,"42"));
        indDetailVos.add(new IndDetailVo("46",mtmzdns));
        //断奶前死亡率	（断奶对应的出生活仔数 - 断奶仔猪数）÷断奶对应的出生活仔数
        double dnqswl = ProductionEfficiencyMula.divide(pd(totalAmount,"45")-pd(totalAmount,"44"),pd(totalAmount,"45"),4)*100;
        indDetailVos.add(new IndDetailVo("48",dnqswl));
        //仔猪成活率	断奶仔猪数÷断奶对应的出生活仔数
        double zzchl = ProductionEfficiencyMula.divide(pd(totalAmount,"44"),pd(totalAmount,"45"))*100;
        indDetailVos.add(new IndDetailVo("49",zzchl));
        //断奶窝重	断奶仔猪窝重之和÷断奶母猪头数
        double dnzzwzzh = getDnzzwzzh(indicatorsDto,user);
        double dnwz = ProductionEfficiencyMula.divide(dnzzwzzh,dnmzts);
        indDetailVos.add(new IndDetailVo("50",dnwz));
        //断奶个体均重	断奶仔猪窝重之和÷断奶仔猪头数
        double dngtjz = ProductionEfficiencyMula.divide(dnzzwzzh,dnzzts);
        indDetailVos.add(new IndDetailVo("51",dngtjz));
        //平均哺乳天数	断奶母猪哺乳天数之和÷断奶母猪头数
        double pjbrts = getPjbrts(indicatorsDto,user);
        indDetailVos.add(new IndDetailVo("52",pjbrts));
        //PSY(全群指标)	每头母猪断奶数×LSY
        double psyQqzb =  getPsyQqzb(indicatorsDto,totalAmount,user);
        indDetailVos.add(new IndDetailVo("53",psyQqzb));

        //PSY(正品数指标)	统计期内销售和自留培育仔猪数÷统计期向前115天母猪日均平均存栏×365.25÷统计期 未确定  todo
        //PSY(正品数指标)	统计期内销售和自留培育仔猪数÷统计期向前115天母猪日均平均存栏×365.25÷统计期
        double psyZpszb = getPsyZpszb(indicatorsDto,user,dnzzts);
        indDetailVos.add(new IndDetailVo("110",psyZpszb));
        //PSY(全群指标)(不包含后备猪)	每头母猪断奶数×LSY
        double lsyQuzbBbhhbz = this.getLsyQuzbBbhhbz(indicatorsDto, user);
        indDetailVos.add(new IndDetailVo("118",lsyQuzbBbhhbz * mtmzdns));
        //每头母猪提供上市肥猪数  销售记录中的育肥和种猪数(不包含保育) /平均能繁母猪存栏
        //销售记录中的育肥和种猪数(不包含保育)
        double pigSale = getPigSale(indicatorsDto,user);
        //平均能繁母猪存栏
        double pjnfmzcl = pd(totalAmount,"55");
        double mtmztgssfzs = ProductionEfficiencyMula.divide(pigSale, pjnfmzcl);
          indDetailVos.add(new IndDetailVo("81",mtmztgssfzs));
        //MSY	PSY × 保育猪成活率 × 育肥猪成活率
        //MSY	PSY × 保育猪成活率 × 育肥猪成活率
        //保育猪成活率 = 1-(保育仔猪死亡数 ÷ 保育仔猪平均存栏)；
        double byzchl = ProductionEfficiencyMula.divide(pd(totalAmount, "77"), pd(totalAmount, "67"),4);
        // 育肥猪成活率=1-(育肥猪死亡数 ÷ 育肥猪平均存栏)
        double yfzchl = ProductionEfficiencyMula.divide(pd(totalAmount, "78"), pd(totalAmount, "68"),8);
        double msy = psyQqzb * (1-byzchl) * (1-yfzchl);
        indDetailVos.add(new IndDetailVo("82",msy));

        //断奶仔猪转出数	仔猪断奶转培育+仔猪断奶销售 未确认
        double dnzzzcs = pd(totalAmount, "115");
        indDetailVos.add(new IndDetailVo("115",dnzzzcs));
        return indDetailVos;
    }

    /**
     * 销售记录中的育肥和种猪数(不包含保育)
     * @param indicatorsDto
     * @param user
     * @return
     */
    private double getPigSale(IndicatorsDto indicatorsDto, User user) {
        return productionMapper.findPigSale(indicatorsDto,user);
    }

    /**
     * 断奶窝重之和
     * @param indicatorsDto
     * @return
     */
    private double getDnzzwzzh(IndicatorsDto indicatorsDto,User user) {
        return productionMapper.findDnwzzh(indicatorsDto,user);
    }

    /**
     *PSY(全群指标)	每头母猪断奶数×LSY
     * @param indicatorsDto
     * @param totalAmount
     * @return
     */
    private double getPsyQqzb(IndicatorsDto indicatorsDto, List<Production> totalAmount,User user) {
        return ProductionEfficiencyMula.
                divide(pd(totalAmount,"44"),
                        pd(totalAmount,"42"))*getLsyQqzb(indicatorsDto,user);
    }

    /**
     * LSY(全群指标)	（365-NPD）÷（平均妊娠天数+平均哺乳天数）
     * @param npd
     * @param pjbrts
     * @param pjrsts
     * @return
     */
    private double getLsyQqzb(double npd, double pjbrts, double pjrsts) {
        //LSY(全群指标)	（365-NPD）÷（平均妊娠天数+平均哺乳天数）
        double lsy = NumberUtils.formatDouble((365 - npd) / (pjrsts + pjbrts),2);
        log.warn("lsy:{}" ,lsy);
        return lsy;
    }

    /**
     * LSY(全群指标)	（365-NPD）÷（平均妊娠天数+平均哺乳天数）
     * @return
     */
    private double getLsyQqzb(IndicatorsDto indicatorsDto,User user) {
        double npd = getNpd(indicatorsDto,user);
        double pjrsts = getPjrsts(indicatorsDto,user);
        double pjbrts = getPjbrts(indicatorsDto,user);
        //LSY(全群指标)	（365-NPD）÷（平均妊娠天数+平均哺乳天数）
        double lsy = NumberUtils.formatDouble((365 - npd) / (pjrsts + pjbrts),2);
        log.warn("lsy:{}" ,lsy);
        return lsy;
    }
    /**
     * 平均妊娠天数
     * @param indicatorsDto
     * @return
     */
    private double getPjrsts(IndicatorsDto indicatorsDto,User user) {
        return productionMapper.findGestationDayNum(indicatorsDto,user);
    }

    /**
     * 平均哺乳天数
     * @param indicatorsDto
     * @return
     */
    private double getPjbrts(IndicatorsDto indicatorsDto,User user){
        //平均哺乳天数	断奶母猪哺乳天数之和÷断奶母猪头数
        return productionMapper.findLactationDayNum(indicatorsDto,user);
    }

    /**
     * 获取npd
     * @param indicatorsDto
     * @return
     */
    private double getNpd(IndicatorsDto indicatorsDto,User user) {
        List<NpdMonthDo> npdBetweenMonth = productionMonthMapper.findNpdBetweenMonth(indicatorsDto,user);
        //npd
        int farmsCount = farmsMapper.findFarmsCount(indicatorsDto,user);
        return ProductionEfficiencyMula.npd(npdBetweenMonth, indicatorsDto.getMonth())/farmsCount;
    }

    /**
     * NPD 平均母猪NPD	(统计期间种猪群NPD天数总和÷统计期间种猪群平均存栏)×(365÷统计期间天数)
     * @param indicatorsDto
     * @param user
     * @param dateType
     * @param days
     * @return
     */
    private double getNpd2(IndicatorsDto indicatorsDto, User user, String dateType, long days){
        Double npd = productionMapper.findNpd(indicatorsDto, user);
        int farmsCount = farmsMapper.findFarmsCount(indicatorsDto,user);
        return npd / days /farmsCount;
    }

    /**
     * 获取分娩成绩
     * @param indicatorsDto
     * @param dateType
     * @param totalAmount
     * @param avgAmount
     * @param user
     * @return
     */
    private List<IndDetailVo> getfmcj(IndicatorsDto indicatorsDto, String dateType, List<Production> totalAmount, List<Production> avgAmount,long days, User user) {
        List<IndDetailVo> indDetailVos = new ArrayList<>();
        //分娩母猪头数	分娩母猪头数
        double fmmzts = pd(totalAmount, "12");
        indDetailVos.add(new IndDetailVo("12",fmmzts));
        //健仔数
        double jzs = pd(totalAmount, "25");
        indDetailVos.add(new IndDetailVo("25",jzs));
        //弱仔数
        double rzs = pd(totalAmount, "26");
        indDetailVos.add(new IndDetailVo("26",rzs));
        //畸形数
        double qxs = pd(totalAmount, "27");
        indDetailVos.add(new IndDetailVo("27",qxs));
        //死胎数
        double sts = pd(totalAmount, "28");
        indDetailVos.add(new IndDetailVo("28",sts));
        //木乃伊
        double mly = pd(totalAmount, "29");
        indDetailVos.add(new IndDetailVo("29",mly));
        //活仔数	健仔数+弱仔数
        double hzs = jzs + rzs;
        indDetailVos.add(new IndDetailVo("87",hzs));
        //总产仔头数	健仔+弱仔+畸形数+死胎数+木乃伊
        double zczts = jzs + rzs + qxs + sts + mly;
        indDetailVos.add(new IndDetailVo("24",zczts));
        //窝均产仔数
        double wjczs = ProductionEfficiencyMula.divide(pd(totalAmount,"24"),pd(totalAmount,"12"));
        indDetailVos.add(new IndDetailVo("30",wjczs));
        //窝均活仔数	活仔数÷分娩母猪头数
        double wjhzs = ProductionEfficiencyMula.divide(hzs,pd(totalAmount,"12"));
        indDetailVos.add(new IndDetailVo("88",wjhzs));
        //产活仔数占总产仔数(%)	产活仔数占总产仔数百分比  --错误 todo 多场为0
        double chzcszzczs = ProductionEfficiencyMula.divide(hzs,zczts)*100;
        indDetailVos.add(new IndDetailVo("98",chzcszzczs));
        log.warn("产活仔数占总产仔数%:{}" ,chzcszzczs+ "%");
        //窝均健仔数	健仔数÷分娩母猪头数
        double wjjzs = ProductionEfficiencyMula.divide(pd(totalAmount,"25"),pd(totalAmount,"12"));
        indDetailVos.add(new IndDetailVo("31",wjjzs));
        //窝均弱仔数	弱仔数÷分娩母猪头数
        double wjrzs = ProductionEfficiencyMula.divide(pd(totalAmount,"26"),pd(totalAmount,"12"));
        indDetailVos.add(new IndDetailVo("32",wjrzs));
        //窝均畸形数	畸形数÷分娩母猪头数
        double wjjxs = ProductionEfficiencyMula.divide(pd(totalAmount,"27"),pd(totalAmount,"12"));
        indDetailVos.add(new IndDetailVo("33",wjjxs));
        //窝均死胎数	死胎数÷分娩母猪头数
        double wjsts = ProductionEfficiencyMula.divide(pd(totalAmount,"28"),pd(totalAmount,"12"));
        indDetailVos.add(new IndDetailVo("34",wjsts));

        //死胎数占总产仔数(%)	死胎数占总产仔数百分比      --错误 todo 多场为0
        double stszzczs = ProductionEfficiencyMula.divide(sts,zczts)*100;
        indDetailVos.add(new IndDetailVo("99",stszzczs));
        log.warn("死胎数占总产仔数(%):{}" ,stszzczs);

        //木乃伊数占总产仔数(%)	木乃伊数占总产仔数百分比  --错误 todo 多场为0
        double mnyszzzs =ProductionEfficiencyMula.divide(mly,zczts)*100;
        indDetailVos.add(new IndDetailVo("100",mnyszzzs));
        log.warn("死胎数占总产仔数(%):{}" ,mnyszzzs);

        //窝均木乃伊	木乃伊÷分娩母猪头数
        double wjmny = ProductionEfficiencyMula.divide(pd(totalAmount,"29"),pd(totalAmount,"12"));
        indDetailVos.add(new IndDetailVo("35",wjmny));

        //分娩母猪平均胎龄	分娩母猪胎龄之和 ÷ 分娩窝数
        double fmmzpjtl = getFmmzpjtl(indicatorsDto,user);
        indDetailVos.add(new IndDetailVo("20",fmmzpjtl));

        //平均妊娠天数
        double pjrsts = getGestationDay(indicatorsDto,user);
        indDetailVos.add(new IndDetailVo("21",pjrsts));

        //分娩间隔	（本次分娩日期－上次的分娩日期）之和÷ 分娩窝
        double fmjg = getFmjg(indicatorsDto,user);
        indDetailVos.add(new IndDetailVo("22",fmjg));
        //活仔数＜7的母猪头数	活仔数＜7的母猪头数
        double hzsxy7dmzts = pd(totalAmount, "36");
        indDetailVos.add(new IndDetailVo("36",hzsxy7dmzts));
        //活仔数＜7的母猪比例	活仔数＜7的母猪头数÷分娩母猪头数
        double hzsxy7dmzbl = ProductionEfficiencyMula.divide(hzsxy7dmzts,fmmzts)*100;
        indDetailVos.add(new IndDetailVo("37",hzsxy7dmzbl));
        //初生窝重	分娩窝数重量之和 ÷ 分娩窝数
        double fmwszlzh =  getFmwszlzh(indicatorsDto,user);//分娩窝数重量之和
        double cswz =ProductionEfficiencyMula.divide(fmwszlzh,fmmzts);
        indDetailVos.add(new IndDetailVo("38",cswz));

        //初生个体均重	分娩窝数重量之和 ÷ 分娩活仔数
        double fmhzs = getFmhzs(indicatorsDto,user);
        double csgtjz =ProductionEfficiencyMula.divide(fmwszlzh,fmhzs);
        indDetailVos.add(new IndDetailVo("39",csgtjz));
        //LSY(全群指标)	（365-NPD）÷（平均妊娠天数+平均哺乳天数）
        double npd = getNpd2(indicatorsDto,user,dateType,days);//npd
        double pjbrts = getPjbrts(indicatorsDto,user);//平均哺乳天数
        double lsyQqzb = getLsyQqzb(npd,pjbrts,pjrsts);
        indDetailVos.add(new IndDetailVo("40",lsyQqzb));

        //LSY(生产母猪指标)	统计期内分娩总胎数/对应周期向前推115天相应周期日均生产母猪存栏数
        double lsyScmzzb = productionMapper.findLsyScmzzb(indicatorsDto,user);
        indDetailVos.add(new IndDetailVo("114",lsyScmzzb));
        //LSY(全群指标)(不包含后备猪)	（365-NPD）÷（平均妊娠天数+平均哺乳天数）
        double lsyQuzbBbhhbz = getLsyQuzbBbhhbz(indicatorsDto,user);
        indDetailVos.add(new IndDetailVo("117",lsyQuzbBbhhbz));
        //LSY(NPB)	LSY=（统计期内的有效妊娠天数*365）/（统计期内的所有猪只饲养天数合计*115）
        double lsyNpb = this.getLsyNpb(indicatorsDto,user);
        indDetailVos.add(new IndDetailVo("119",lsyNpb));
        //活仔数＜10的母猪头数	活仔小于10头的分娩记录数
        double hzsxy10dmuzts = pd(totalAmount, "111");
        indDetailVos.add(new IndDetailVo("111",hzsxy10dmuzts));
        //活仔数＜10的母猪比例	活仔数＜10的母猪头数÷分娩母猪头数
        double hzsxy10dmzbl = ProductionEfficiencyMula.divide(hzsxy10dmuzts, fmmzts)*100;
        indDetailVos.add(new IndDetailVo("112",hzsxy10dmzbl));
        //初生健仔个体均重	健仔窝重 ÷ 健仔总和
        double csjzgtjz = ProductionEfficiencyMula.divide(fmwszlzh,jzs);
        indDetailVos.add(new IndDetailVo("113",csjzgtjz));
        return indDetailVos;
    }

    /**
     * LSY(NPB)
     * @param indicatorsDto
     * @param user
     * @return
     */
    private double getLsyNpb(IndicatorsDto indicatorsDto, User user) {
        return this.productionMapper.findLsyNpb(indicatorsDto,user);
    }


    /**
     * LSY(全群指标)(不包含后备猪)
     * @param indicatorsDto
     * @param user
     * @return
     */
    private double getLsyQuzbBbhhbz(IndicatorsDto indicatorsDto, User user) {
        double npdNoHbz = this.getNPDNoHbz(indicatorsDto,user);//NPD不包含后备猪
        double pjbrts = this.getPjbrts(indicatorsDto, user);//平均哺乳天数
        double pjrsts = this.getPjrsts(indicatorsDto, user);//平均妊娠天数
        return ProductionEfficiencyMula.divide(365-npdNoHbz , pjbrts+pjrsts);
    }

    /**
     * NPD不包含后备猪
     * @param indicatorsDto
     * @param user
     * @return
     */
    private double getNPDNoHbz(IndicatorsDto indicatorsDto, User user) {
        return productionMapper.findNPDNoHbz(indicatorsDto,user);
    }


    /**
     * 分娩母猪平均胎龄
     * @param indicatorsDto
     * @param user
     * @return
     */
    private double getFmmzpjtl(IndicatorsDto indicatorsDto, User user) {
        return productionMapper.findFmmzPjtl(indicatorsDto,user);
    }

    /**
     * 分娩活仔数
     * @param indicatorsDto
     * @param user
     * @return
     */
    private double getFmhzs(IndicatorsDto indicatorsDto, User user) {
        return productionMapper.findFmhzs(indicatorsDto,user);
    }

    /**
     * 分娩窝数重量之和
     * @param indicatorsDto
     * @param user
     * @return
     */
    private double getFmwszlzh(IndicatorsDto indicatorsDto, User user) {
        return productionMapper.findFmwszlzh(indicatorsDto,user);
    }

    /**
     * 分娩间隔
     * @param indicatorsDto
     * @param user
     * @return
     */
    private double getFmjg(IndicatorsDto indicatorsDto, User user) {
        return productionMapper.findFmjg(indicatorsDto,user);
    }

    /**
     * 平均妊娠天数
     * @param indicatorsDto
     * @return
     */
    private double getGestationDay(IndicatorsDto indicatorsDto,User user) {
        Double gestationDayNumWeek = productionMapper.findGestationDayNum(indicatorsDto,user);
        log.warn("平均妊娠天数:{}" ,gestationDayNumWeek);
        return gestationDayNumWeek;
    }

    /**
     * 配种分娩率
     * @param indicatorsDto
     * @param dateType
     * @param totalAmount
     * @param avgAmount
     * @param user
     * @return
     */
    private List<IndDetailVo> getpzfml(IndicatorsDto indicatorsDto, String dateType, List<Production> totalAmount, List<Production> avgAmount, User user) {
        List<IndDetailVo> indDetailVos = new ArrayList<>();
        //对应预产期配种记录数	向前推算116天的配种记录，即预产期在所选期间的配种记录
        double dyycqpzjls = pd(totalAmount, "13");
        indDetailVos.add(new IndDetailVo("13",dyycqpzjls));
        //配种对应分娩头数	预产期范围内配种并分娩的
        double pzdyfmts = pd(totalAmount, "89");
        indDetailVos.add(new IndDetailVo("89",pzdyfmts));
        //未成功分娩头数	预产期范围内配种记录没有分娩的
        double wcgfmts = pd(totalAmount, "14");
        indDetailVos.add(new IndDetailVo("14",wcgfmts));
        //返情	预产期范围内配种后未正常分娩的记录中返情数量
        double fq = pd(totalAmount, "15");
        indDetailVos.add(new IndDetailVo("15",fq));
        //流产	预产期范围内配种后没有正常分娩的记录中流产数量
        double lc = pd(totalAmount, "16");
        indDetailVos.add(new IndDetailVo("16",lc));
        //空怀	预产期范围内配种后没有正常分娩的记录中空怀数量
        double kh = pd(totalAmount, "17");
        indDetailVos.add(new IndDetailVo("17",kh));
        //淘汰	预产期范围内配种后没有正常分娩的记录中淘汰数量
        double tt = pd(totalAmount, "18");
        indDetailVos.add(new IndDetailVo("18",tt));
        //死亡	预产期范围内配种后没有正常分娩的记录中死亡数量
        double sw = pd(totalAmount, "19");
        indDetailVos.add(new IndDetailVo("19",sw));
        //配种分娩率	（配种对应分娩头数 ÷ 对应预产期配种记录数）× 100%
        double pzfml = ProductionEfficiencyMula.divide(pd(totalAmount,"89"),pd(totalAmount,"13"))*100;
        indDetailVos.add(new IndDetailVo("23",pzfml));
        return indDetailVos;
    }

    @Override
    public List<DataType> getDataType() {
        return productionMonthMapper.getDataType();
    }

    private void setIndetailVos(List<IndDetailVo> list) {
        list.forEach(s->{
            Double reality = (Double) s.getReality();
            if (Double.isNaN(reality)){
                s.setReality(0);
            }else {
                s.setReality(NumberUtils.formatDouble(reality,2));
            }
            s.setDataTypeName(dataTypeDic.getDataTypeName(s.getDataType()));
        });
    }

    /**
     * 配种成绩
     * @param indicatorsDto
     * @param dateType
     * @param totalAmount
     * @param avgAmount
     * @param user
     * @return
     */
    private List<IndDetailVo> getPzcj(IndicatorsDto indicatorsDto, String dateType, List<Production> totalAmount, List<Production> avgAmount, User user) {
        List<IndDetailVo> indDetailVos = new ArrayList<>();
        //配种头数
        double pzts = pd(totalAmount, "1");
        indDetailVos.add(new IndDetailVo("1",pzts));
        //断奶配种头数
        double dnpzts = pd(totalAmount, "2");
        indDetailVos.add(new IndDetailVo("2",dnpzts));
        //断奶7天内的配种头数
        double dn7tndpzts = pd(totalAmount, "3");
        indDetailVos.add(new IndDetailVo("3",dn7tndpzts));
        //断奶母猪7天内发情配种率
        double dnmz7tnfqpzl = ProductionEfficiencyMula.divide(pd(totalAmount, "3"), pd(totalAmount, "2"),4)*100;
        indDetailVos.add(new IndDetailVo("4",dnmz7tnfqpzl));
        //断奶-配种间隔天数	母猪断奶至配种间隔天数平均值
        double dnpzjgts = productionMapper.findDnpzjgts(indicatorsDto, user);
        indDetailVos.add(new IndDetailVo("5",dnpzjgts));

        //异常复配次数
        double ycfpcs = pd(totalAmount, "6");
        indDetailVos.add(new IndDetailVo("6",ycfpcs));
        //流产空胎后配种
        double lckthpz = pd(totalAmount, "96");
        indDetailVos.add(new IndDetailVo("96",lckthpz));
        //返情妊检阴性后配种
        double fqrjyxhpz = pd(totalAmount, "97");
        indDetailVos.add(new IndDetailVo("97",fqrjyxhpz));
        //复配率 = 异常复配次数÷配种头数
        double fpl = ProductionEfficiencyMula.divide(pd(totalAmount,"6"),pd(totalAmount,"1"),4)*100;
        indDetailVos.add(new IndDetailVo("7",fpl));
        //后备母猪配种头数
        double hbmzpzts = pd(totalAmount, "8");
        indDetailVos.add(new IndDetailVo("8",hbmzpzts));
        return indDetailVos;
    }

    private void setIndicatorsDto(IndicatorsDto indicatorsDto, String dateType) {
        String statDate = indicatorsDto.getStatDate();
        String[] split = statDate.split("-");
        int year = Integer.valueOf(split[0]);
        int month = Integer.valueOf(split[1]);
        LocalDate now = LocalDate.now();
        if (dateType.equals("1")){
            LocalDate dateByWeek = DateUtils.getDateByWeek(year,month );
            indicatorsDto.setWeek(split[1]);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String beginDate = dateByWeek.format(dateTimeFormatter);
            indicatorsDto.setBeginDate(beginDate);
            LocalDate localDate = dateByWeek.plusWeeks(1);
            localDate = localDate.minusDays(1);
            indicatorsDto.setEndDate(localDate.format(dateTimeFormatter));
        }else {
            indicatorsDto.setYear(year);
            indicatorsDto.setMonth(month);
            String[] daySnterval = DateUtils.getDaySnterval(year, month);
            indicatorsDto.setBeginDate(daySnterval[0]);
            indicatorsDto.setEndDate(daySnterval[1]);
        }
    }

    private double pd(List<Production> productions , String dataType){
        for (Production p: productions) {
            if (dataType.equals(p.getDataType())){
                return p.getReality();
            }
        }
        return 0;
    }

    /**
     * //PSY(正品数指标)	统计期内销售和自留培育仔猪数÷统计期向前115天母猪日均平均存栏×365.25÷统计期
     * @param indicatorsDto
     * @param user
     * @param dnzzts 断奶仔猪数
     * @return
     */
    private double getPsyZpszb(IndicatorsDto indicatorsDto, User user, double dnzzts) {
        Map<String, Double> tjq = productionMapper.findTjq(indicatorsDto, user);
        Double pjcl = tjq.get("pjcl");//统计期向前115天母猪日均平均存栏
        Double tjq1 = tjq.get("tjq");//统计期
        double divide = ProductionEfficiencyMula.divide(dnzzts, pjcl)*365.25;
        return ProductionEfficiencyMula.divide(divide,tjq1);
    }

}
