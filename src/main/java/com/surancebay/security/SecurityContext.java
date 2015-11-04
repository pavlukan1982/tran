package com.surancebay.security;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavlyukevich on 04.11.2015.
 */
public class SecurityContext {

    public static void setSkipAuthorization(Boolean f){};

    public static void setUserId(Long id){};

    public static List<String> getUserRoles(){
        return new ArrayList<>();
    }
}
