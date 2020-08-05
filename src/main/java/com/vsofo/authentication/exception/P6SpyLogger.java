package com.vsofo.authentication.exception;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;

public class P6SpyLogger  implements MessageFormattingStrategy {

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return !"".equals(sql.trim()) ? "耗时" + elapsed + "ms, 第" + connectionId + "个连接, sql: " + sql.replace("\n", "") : "";
    }

}
