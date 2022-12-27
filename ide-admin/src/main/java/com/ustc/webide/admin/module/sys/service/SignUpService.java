package com.ustc.webide.admin.module.sys.service;

public interface SignUpService {
    void sendCheckMsg(String phone,String code);

    String getCodeByPhone(String phone);
}
