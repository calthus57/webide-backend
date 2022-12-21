package io.renren.modules.sys.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class SysSignupEntity {
    /*
    *   userName: '',
          password: '',
          passwordAgain: '',
          phone: '',
          checkPwd: ''
    * */
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_-]{3,15}$",message = "用户名必须以字母开始,可包含数字,长度在4-16位")
    private  String userName;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z]\\w{5,15}$",message = "密码必须以字母开头,只能包含字符、数字和下划线,长度在6-16位")
    private String password;
    @NotBlank(message = "密码不能为空")
    private  String passwordAgain;
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$",message = "电话号码不合法")
    @NotBlank(message = "电话号码不能为空")
   // @Pattern("\^[1][3,4,5,6,7,8,9][0-9]{9}$\")
    private String phone;
    @NotBlank(message = "验证码不能为空")
    @Pattern(regexp = "^\\d{5}$",message = "验证码不合法")
    private String checkPwd;
}
