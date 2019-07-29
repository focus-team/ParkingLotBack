package com.oocl.web.parkingLot.common;

import com.oocl.web.parkingLot.entity.User;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class IdentifyVerifycation {

    private static Map tokenPool = new HashMap<String, User>();

    public static String storeUser(User user){
        String uuid = UUID.randomUUID().toString();
        tokenPool.put(uuid, user);
        return uuid;
    }

    public static User fetchUser(String uuid){
        return (User) tokenPool.get(uuid);
    }
}
