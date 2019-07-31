package com.oocl.web.parkingLot.common;

import com.oocl.web.parkingLot.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class IdentifyVerifycation {

    private static Map tokenPool = new HashMap<String, User>();
    private static String USER_TOKEN = "USER_TOKEN";

    public static <T> String storeUser(T user){
        String uuid = UUID.randomUUID().toString();
        tokenPool.put(uuid, user);
        return uuid;
    }

    public static <T> T fetchUser(HttpServletRequest request){
        Cookie [] cookies = request.getCookies();
        for(Cookie cookie: cookies){
            if(!cookie.getName().isEmpty() && cookie.getName().equals(USER_TOKEN)){
                return (T)tokenPool.get(cookie.getValue());
            }
        }
        return null;
    }

    public static <T> T logoutUser(HttpServletRequest httpServletRequest){
        Cookie [] cookies = httpServletRequest.getCookies();
        for(Cookie cookie: cookies){
            if(!cookie.getName().isEmpty() && cookie.getName().equals(USER_TOKEN)){
                return (T)tokenPool.remove(cookie.getValue());
            }
        }
        return null;
    }
}
