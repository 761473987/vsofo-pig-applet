package com.vsofo.authentication.filter.handler;

import com.alibaba.fastjson.JSONObject;
import com.vsofo.authentication.entity.Result;
import com.vsofo.authentication.entity.po.Farms;
import com.vsofo.authentication.entity.po.Organ;
import com.vsofo.authentication.entity.po.RolePO;
import com.vsofo.authentication.entity.po.UserPO;
import com.vsofo.authentication.entity.vo.ReturnAuthVO;
import com.vsofo.authentication.exception.annotation.AuthCheck;
import com.vsofo.authentication.exception.log.LoginLog;
import com.vsofo.authentication.exception.service.LogService;
import com.vsofo.authentication.service.UsersService;
import com.vsofo.common.utils.HttpClientUtil;
import com.vsofo.common.utils.IpAddressUtil;
import com.vsofo.common.utils.ResponseUtil;
import com.vsofo.common.utils.TokenUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/5/18 11:12
 * @description 登录成功跳转
 */
@Component
@Slf4j
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${wx.getUrl}")
    private String getUrl;

    @Autowired
    UsersService usersService;

    @Autowired
    LogService logService;

    @SneakyThrows
    @Override
    @AuthCheck(descrption = "登录成功")
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        ReturnAuthVO returnAuthVO = new ReturnAuthVO();
        String wxCode = request.getParameter("code");
        String wxAppId = request.getParameter("appid");
        String wxSecret = request.getParameter("appsecret");
        UserPO users = (UserPO) auth.getPrincipal();
        Integer phouldChangePasswordOnNextLogin = users.getShouldChangePasswordOnNextLogin();
        //获取用户名
        String loginName = users.getLoginName();
        //获取用户养殖场
        response.setContentType("text/html;charset=utf-8");
        //  String authHeader = request.getHeader(SecurityConstant.HEADER);
        long roleId = 0;
        for (RolePO rolePO : users.getRoles()) {
            roleId = rolePO.getRoleId();
            returnAuthVO.setRole(roleId);
            returnAuthVO.setRoleName(rolePO.getRoleName());
            returnAuthVO.setData(TokenUtils.createAccessJwtToken(users,
                    rolePO.getRoleId(), users.getUserId(),
                    loginName, rolePO.getRoleName()));
            break;
        }
        if (phouldChangePasswordOnNextLogin == 1) {
            returnAuthVO.setVerifyCode(1);
        } else {
            returnAuthVO.setVerifyCode(0);
        }
        returnAuthVO.setVerifyFlag(true);
        if (StringUtils.isNotBlank(wxCode) && StringUtils.isNotBlank(wxAppId) && StringUtils.isNotBlank(wxSecret)) {
            returnAuthVO.setOpenId(generateOpenId(wxCode, wxAppId, wxSecret).getString("openid"));
        }else{
            returnAuthVO.setOpenId("");
        }
        if (roleId == 1) {
            returnAuthVO.setFarmID(0);
            returnAuthVO.setFarmName("");
            returnAuthVO.setOrganId(-1);
            returnAuthVO.setOrganName("深圳金新农科技股份有限公司");
        } else if (roleId == 2) {   //大区管理员返回
            List<UserPO> user = usersService.getOrganByLoginName(loginName);
            for (UserPO userss : user) {
                for (Organ organ : userss.getOrgans()) {
                    returnAuthVO.setFarmID(0);
                    returnAuthVO.setFarmName("");
                    returnAuthVO.setOrganId(organ.getOrganID());
                    returnAuthVO.setOrganName(organ.getOrganName());
                    break;
                }
            }
        } else if(roleId == 3) {
            List<UserPO> user = usersService.getFarmsByLoginName(loginName);
            for (UserPO userss : user) {
                for (Farms farms : userss.getFarms()) {
                    returnAuthVO.setFarmID(farms.getFarmID());
                    returnAuthVO.setFarmName(farms.getFarmName());
                    returnAuthVO.setOrganId(0);
                    returnAuthVO.setOrganName("");
                    break;
                }
            }
        } else if(roleId == 4){

        }
        returnAuthVO.setHeadImg("");
        returnAuthVO.setAccountName(users.getUsername());
        if (StringUtils.isNotBlank(users.getUsername())) {
            LoginLog loginLog = new LoginLog();
            loginLog.setLoginName(loginName);
            loginLog.setLoginTime(new Date());
            loginLog.setHostAddress(IpAddressUtil.ipAddress(request));
            loginLog.setUserId((int) users.getUserId());
            loginLog.setRoleId((int) users.getRoleId());
            loginLog.setMessage("");
            logService.loginLog(loginLog);
        }
        log.info("【" + loginName + "】登录成功 ----------> 登录时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        ResponseUtil.out(response, new Result<>(true, 1, "登录成功！", returnAuthVO));
    }


    private JSONObject generateOpenId(String wxCode, String wxAppId, String wxSecret) {
        // 授权（必填）
        String grant_type = "authorization_code";
        // 请求参数
        String params = "appid=" + wxAppId + "&secret=" + wxSecret + "&js_code=" + wxCode + "&grant_type="
                + grant_type;
        log.info("解析code请求参数：" + params);
        // 发送请求
        String sr = HttpClientUtil.doGet(getUrl, params);
        // 解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(sr);
        log.info("解析code请求结果:" + json.toString());
        // 获取会话密钥（session_key）
        return json;
    }


}