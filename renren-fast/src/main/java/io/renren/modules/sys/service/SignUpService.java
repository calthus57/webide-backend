package io.renren.modules.sys.service;

public interface SignUpService {
    void sendCheckMsg(String phone,String code);

    String getCodeByPhone(String phone);
}
