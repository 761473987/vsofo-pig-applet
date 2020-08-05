package com.vsofo.Service;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.vsofo.VsofoPigAppletApp;
import com.vsofo.applet.pigfarmstat.controller.FarmDailyController;
import com.vsofo.applet.pigfarmstat.controller.FarmMonthlyController;
import com.vsofo.applet.pigfarmstat.mapper.FarmDailyMapper;
import com.vsofo.applet.pigfarmstat.mapper.FarmsMapper;
import com.vsofo.applet.pojo.ExcelModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VsofoPigAppletApp.class)
public class ExcelTest {

    @Autowired
    FarmsMapper farmsMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    FarmDailyController farmDailyController;

    @Autowired
    FarmMonthlyController farmMonthlyController;
    @Autowired
    FarmDailyMapper farmDailyMapper;

    @Test
    public void Test() throws IOException {
        int startDay = 30;
        int endDay = 30;
        String fileName = "D:\\excel\\2020-7月"+startDay+"-"+endDay+"日小程序各养殖场存栏情况1.xlsx";
        File file = new File(fileName);
        if (!file.exists()){
            file.createNewFile();
        }
        OutputStream outputStream = new FileOutputStream(fileName);
        ExcelWriter  excelWriter = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX, true);
        for (int i = startDay; i <= endDay; i++) {
            List<ExcelModel> execlModel = farmDailyMapper.findExeclModel("2020-07-" + i);
            Sheet sheet = new Sheet(i, 0,ExcelModel.class);
            sheet.setSheetName(i+"日");
            excelWriter.write(execlModel, sheet);
        }
        excelWriter.finish();
        outputStream.close();
    }

    @Test
    public void Test2(){

    }
}
