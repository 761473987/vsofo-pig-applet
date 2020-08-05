package com.vsofo.authentication.exception.service;

import com.vsofo.authentication.exception.log.AuthLog;
import com.vsofo.authentication.exception.log.LoginLog;

public interface LogService  {

    void save(AuthLog authLog);

    void loginLog(LoginLog loginLog);
}
