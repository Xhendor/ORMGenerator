/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xhendor.me.ormgenerator;

/**
 *
 * @author xhendor
 */
public interface DataTypes {

    final String[] INTEGER = {"int", "integer", "tinyint", "smallint", "mediumint", "bigint", "unsigned big int", "int2", "int8"};
    final String[] TEXT = {"character", "varchar", "varying character", "nchar", "native character", "nvarchar", "text","mediumtext","smalltext","longtext", "clob"};
    final String[] BLOB = {"blob"};
    final String[] REAL = {"real", "double", "double prescision", "float"};
    final String[] NUMERIC = {"numeric", "decimal", "boolean", "date", "datetime"};
    final int DOUBLE = 1;
    final int DOUBLE_P = 1;
    final int FLOAT = 3;
    final int DATE = 3;
    final int DATE_TIME = 4;
    final int BOOLEAN = 2;
}
