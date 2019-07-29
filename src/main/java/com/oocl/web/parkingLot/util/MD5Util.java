package com.oocl.web.parkingLot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

public class MD5Util {

    static Logger logger = LoggerFactory.getLogger(MD5Util.class);

    public static String encryptPass(String pasword) {
        StringBuffer sb = new StringBuffer(32);

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(pasword.getBytes("utf-8"));

            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
            }
        } catch (Exception e) {
            logger.error("Can not encode the string '" + pasword + "' to MD5!", e);
            return null;
        }

        return sb.toString();
    }
}
