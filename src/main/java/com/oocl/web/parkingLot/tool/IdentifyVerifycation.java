package com.oocl.web.parkingLot.tool;

import com.oocl.web.parkingLot.entity.User;

import javax.servlet.http.HttpSession;

public class IdentifyVerifycation {

    /***
     *
     * @param httpSession
     * @param key
     * @return null if current session does not store use info, or return current login user info.
     */
    public static User verify(HttpSession httpSession, String key){
        return (User) httpSession.getAttribute(key);
    }
}
