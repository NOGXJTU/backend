package com.volunteer.commonweal.common;
// 加密类

import com.volunteer.commonweal.models.exceptionModels.EncryptException;
import com.volunteer.commonweal.services.env.Config;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.sun.org.apache.xerces.internal.impl.dv.util.HexBin.encode;

public class EncryptionUtils {
//    用户密码加密类
    public static String userPasswordEncrypt(String method,String password) throws EncryptException{
        try {
            MessageDigest pwd = MessageDigest.getInstance(method);
            pwd.update(password.getBytes());
            return encode(pwd.digest()).substring(0, 16).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            throw new EncryptException(205, "encrypt error");
        }
    }
}
