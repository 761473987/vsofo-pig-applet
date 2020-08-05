package com.vsofo.authentication.controller;


import com.vsofo.authentication.entity.Result;
import com.vsofo.authentication.entity.vo.UserModifyVO;
import com.vsofo.authentication.exception.annotation.AuthCheck;
import com.vsofo.authentication.service.UsersModifyService;
import com.vsofo.common.constants.SecurityConstant;
import com.vsofo.common.constants.enums.AuthCode;
import com.vsofo.common.utils.VerifyAccountUtil;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/7/6 14:25:52
 * @description
 */
@RestController
@RequestMapping("/auth/pig")
public class UserController {

    @Autowired
    UsersModifyService usersService;


    @RequestMapping("/modifyUserPassword")
    @AuthCheck(descrption = "修改密码")
    public Result modifyUserPassword(@RequestBody UserModifyVO users,
                                     HttpServletRequest request) throws Exception {
        String accessToken = request.getHeader(SecurityConstant.HEADER).replace("Bearer ", "");
        if (StringUtils.isBlank(users.getOldPassword()) || StringUtils.isBlank(users.getPassword()) || StringUtils.isBlank(users.getPasswordVerify())) {
            return new Result<>(true, AuthCode.VERIFY_FAIL.getCode(), "密码修改失败！" ,accessToken);
        }
        String message= VerifyAccountUtil.verifyPassword(users.getPassword(), users.getPasswordVerify());
        if (StringUtils.isNotBlank(message)) {
            return new Result<>(true, AuthCode.VERIFY_FAIL.getCode(), message , accessToken);
        }
        Integer resultCode=usersService.updateUserPassword(accessToken,users.getOldPassword(),users.getPassword());
        if (resultCode==1) {
            return new Result<>(true, AuthCode.SUCCESS.getCode() , "密码修改成功！" ,"");
        }else if(resultCode==-1) {
            return new Result<>(true, AuthCode.VERIFY_FAIL.getCode(), "原密码输入错误！" , accessToken);
        }else if(resultCode==-2) {
            return new Result<>(true, AuthCode.VERIFY_FAIL.getCode(), "对不起，请登录！","");
        }else{
            return new Result<>(true, AuthCode.VERIFY_FAIL.getCode(), "密码修改失败！" , accessToken);
        }
    }
}
