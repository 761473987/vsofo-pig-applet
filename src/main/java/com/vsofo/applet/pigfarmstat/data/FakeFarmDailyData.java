package com.vsofo.applet.pigfarmstat.data;//package com.vsofo.applet.pigfarmstat.data;
//
//import com.vsofo.applet.pojo.FarmDaily;
//
//import java.time.*;
//import java.time.temporal.TemporalAdjuster;
//import java.time.temporal.TemporalAdjusters;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * 伪造猪场日报数据
// * @author: wangtao
// * @date: 2020/6/8
// */
//public class FakeFarmDailyData {
//
//    public List<FarmDaily> fakeFarmdaily(
//            LocalDate startDate,
//            Integer... farmId){
//        int startYear = startDate.getYear();
//        int startMonth = startDate.getDayOfMonth();
//        int startDay = startDate.getDayOfMonth();
//        LocalDate first = startDate.with(TemporalAdjusters.firstDayOfMonth());
//        LocalDate last = startDate.with(TemporalAdjusters.lastDayOfMonth());
//
//        List<FarmDaily> farmDailies = new ArrayList<>();
//            for (int k = first.; k <= last; k++) {
//                for (Integer s : farmId) {
//                    FarmDaily farmDaily = new FarmDaily();
//                    farmDaily.setAppId("【TEST】伪造数据");
//                    farmDaily.setFarmId(s);
//                    ZoneId zone = ZoneId.systemDefault();
//                    Instant instant = startDate.atStartOfDay().atZone(zone).toInstant();
//                    java.util.Date date = Date.from(instant);
//                    farmDaily.setDate(date);
//                    farmDailies.add(farmDaily);
//                }
//
//        }
//        return farmDailies;
//    }
//
//    public static void main(String[] args) {
//        LocalDate of = LocalDate.of(2020, 1, 1);
//        LocalDate of1 = LocalDate.of(2020, 6, 8);
//        new FakeFarmDailyData().fakeFarmdaily(of,of1,4550);
//    }
//}
