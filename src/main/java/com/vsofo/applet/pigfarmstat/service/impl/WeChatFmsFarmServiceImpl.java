package com.vsofo.applet.pigfarmstat.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vsofo.applet.pigfarmstat.mapper.WeChatFmsFarmMapper;
import com.vsofo.applet.pigfarmstat.pojo.WeChatFmsFarm;
import com.vsofo.applet.pigfarmstat.service.WeChatFmsFarmService;
import org.springframework.stereotype.Service;

@Service
public class WeChatFmsFarmServiceImpl extends ServiceImpl<WeChatFmsFarmMapper, WeChatFmsFarm> implements WeChatFmsFarmService {
}
