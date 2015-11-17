package com.surancebay.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pavlyukevich on 04.11.2015.
 */
public class SecurityContext {

    private static final SecurityContext INSTANCE = new SecurityContext();

    private SecurityContext() {};

    public static void setSkipAuthorization(Boolean f){};

    public static void setUserId(Long id){};

    public static List<String> getUserRoles(){
        return new ArrayList<String>(Arrays.asList("admin"));
    }

    public static void clean(){};

    public static SecurityContext getInstance() {
        return INSTANCE;
    }

    public static boolean isLoggedIn() {
        return true;
    }

    public static long getUserId(){
        return 1;
    }
}
