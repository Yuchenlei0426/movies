package com.bw.movie.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailRegular {
//    ^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$
    public static boolean isEmail(String email) {
        Pattern compile = Pattern.compile("^([a-z0-9A-Z]+[-|\\\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-zA-Z]{2,}$");
        Matcher matcher = compile.matcher(email);
        boolean matches = matcher.matches();
        if (matches) {
            return matches;
        }
        return false;
    }

}
