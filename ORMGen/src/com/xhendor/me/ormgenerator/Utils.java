package com.xhendor.me.ormgenerator;

import java.io.File;

public class Utils {

    public final static String db = "db";
    public final static String sqlite = "sqlite";
    
    /*
     * Get the extension of a file.
     */  
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}