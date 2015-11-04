package com.surancebay.security;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by pavlyukevich on 04.11.2015.
 */
public class LoginManager {

    public static String sha256(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if ("1".equals(s)){
            return s;
        } else if (s.isEmpty()) {
            throw new NoSuchAlgorithmException();
        } else {
            throw new UnsupportedEncodingException();
        }
    }
}
