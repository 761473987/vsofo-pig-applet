package com.vsofo.applet.pigfarmstat.data;

import com.vsofo.applet.pigfarmstat.dos.indicators.MonthValueDo;
import com.vsofo.applet.pigfarmstat.dos.indicators.NpdMonthDo;
import com.vsofo.applet.utils.NumberUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 公式类
 */
public class ProductionEfficiencyMula {


    /**
     * 断奶母猪7天内发情配种率
     * @param db7tndpzcs 断奶7天内的配种次数 dataType = 3
     * @param dnpzcs 断奶配种次数 dataType = 2
     * @return
     */
    public static String dnmz7tnfqpzl(double db7tndpzcs,double dnpzcs){
        return NumberUtils.formatDoubleToString((db7tndpzcs/dnpzcs)*100,2)+"%";
    }

    /**
     * 复配率 = 异常复配次数÷配种头数
     * @param ycfpcs 异常复配次数
     * @param pzts 配种头数
     * @return
     */
    public static String fpl(double ycfpcs, double pzts) {
        return NumberUtils.formatDoubleToString((ycfpcs/pzts) * 100 ,2) + "%";
    }

    /**
     * 配种分娩率 = （配种对应分娩头数 ÷ 对应预产期配种记录数）× 100%
     * @param pzdyfmts 配种对应分娩头数
     * @param dyycqpzjls 对应预产期配种记录数
     * @return
     */
    public static String pzfml(double pzdyfmts, double dyycqpzjls) {
        return NumberUtils.formatDoubleToString((pzdyfmts/dyycqpzjls) * 100 ,2) + "%";

    }

    /**
     * 窝均产仔数	= 总产仔头数÷分娩母猪头数
     * @param pd 总产仔头数
     * @param pd1 分娩母猪头数
     * @return
     */
    public static String wjczs(double pd, double pd1) {
        return NumberUtils.formatDoubleToString((pd/pd1) * 100 ,2);
    }

    /**
     * 窝均活仔数=活仔数÷分娩母猪头数
     * @param pd
     * @param pd1
     * @return
     */
    public static String wjhzs(double pd, double pd1) {
        return NumberUtils.formatDoubleToString((pd/pd1) * 100 ,2);
    }

    /**
     * 两数相除
     * @param v1
     * @param v2
     * @return
     */
    public static double divide(double v1,double v2){
        if (v1 == 0 || v2 == 0) return 0;
        if (Double.isNaN(v1) || Double.isNaN(v2))return 0;
        return v1/v2;
    }

    /**
     * 两数相除
     * @param v1
     * @param v2
     * @return
     */
    public static double divide(double v1,double v2,int newScale){
        if (v1 == 0 || v2 == 0) return 0;
        if (Double.isNaN(v1) || Double.isNaN(v2))return 0;
        return NumberUtils.formatDouble((v1/v2) ,newScale);
    }


    /**
     * 根据月份获取value
     * @param list
     * @param i
     * @return
     */
    public static double getValueByMonth(List<MonthValueDo> list, int i) {
        if (CollectionUtils.isEmpty(list))return 0;
        for (MonthValueDo m:list) {
            if (i == m.getMonth()){
                return m.getValue();
            }
        }
        return 0;
    }

    /**
     * 获取npd
     * @param sndicatorsDos
     * @param month
     * @return
     */
    public static double npd(List<NpdMonthDo> sndicatorsDos, int month){
        for (NpdMonthDo p: sndicatorsDos) {
            if (p.getMonth() == month){
                return p.getValue();
            }
        }
        return 0;
    }
}
