/*
 * 
 * Copyright (C)  2013 Rosendo R. Sosa. .
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */
package com.xhendor.me.ormgenerator;

/**
 *
 * @author xhendor
 */
public interface DataTypes {

    final String[] INTEGER = {"int", "integer", "tinyint", "smallint", "mediumint", "bigint", "unsigned big int", "int2", "int8"};
    final String[] TEXT = {"char", "varchar", "varying character", "nchar", "native character", "nvarchar", "text", "mediumtext", "smalltext", "longtext", "clob"};
    final String[] BLOB = {"blob"};
    final String[] REAL = {"real", "double", "double prescision", "float"};
    final String[] NUMERIC = {"numeric", "decimal", "bool", "boolean", "date", "datetime"};
    //INTEGER TYPES  
    final int INT_VALUE = 0;
    final int integer = 1;
    final int tinyint = 2;
    final int smallint = 3;
    final int mediumint = 4;
    final int bigint = 5;
    final int unsigned = 6;
    final int unsigned_big_int = 7;
    final int int2 = 8;
    final int int8 = 9;
    //TEXT Types 
    final int CHARACTER = 0;
    final int VARCHAR = 1;
    final int VARING = 2;
    final int NCHAR = 3;
    final int NATIVE_CHAR = 4;
    final int NVARCHAR = 5;
    final int TEXT_VALUE = 7;
    final int MEDIUMTEXT = 8;
    final int SMALLTEXT = 9;
    final int LONGTEXT = 10;
    final int CLONG = 11;
    //BLOB
    final int BLOB_VALUE = 0;
    //REAL
    final int REAL_VALUE = 0;
    final int DOUBLE = 1;
    final int DOUBLE_P = 2;
    final int FLOAT = 3;
    //NUMERIC
    final int NUMERIC_VALUE = 0;
    final int DECIMAL=1;
    final int BOOL = 2;
    final int BOOLEAN = 3;
    final int DATE = 4;
    final int DATE_TIME = 5;
}
