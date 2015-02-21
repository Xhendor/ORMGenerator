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
 * Genrador de entidades para GreenDAO for Android This Version Support only
 * this data types: -DATE -INT -STRING (text,mediumtext,etc,etc) -BOOLEAN
 * -DOUBLE -FLOAT Don't support Relations
 *
 * @author xhendor
 */
import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.File;

/**
 * @author www.javaworkspace.com
 *
 */
public class ConnectSQLite implements DataTypes {

    private static ResultSet resultSetTables;
    private Connection connection;
    private Statement statement;
    private Schema esquema;

    public ConnectSQLite() {
    }


    public int integerDataType(String type) {


        for (int i = 0; i < INTEGER.length; i++) {
            String string = INTEGER[i];
            if (type.toLowerCase().startsWith(string)) {
                return i;
            }

        }
        return -1;
    }

    public int textDataType(String type) {
        for (int i = 0; i < TEXT.length; i++) {
            String string = TEXT[i];
            if (type.toLowerCase().startsWith(string)) {
                return i;
            }

        }
        return -1;
    }

    public int blobDataType(String type) {
        for (int i = 0; i < BLOB.length; i++) {
            String string = BLOB[i];
            if (type.toLowerCase().startsWith(string)) {
                return i;
            }

        }
        return -1;
    }

    public int realDataType(String type) {
        for (int i = 0; i < REAL.length; i++) {
            String string = REAL[i];
            if (type.toLowerCase().startsWith(string)) {
                return i;
            }

        }
        return -1;
    }

    public int numericDataType(String type) {
        for (int i = 0; i < NUMERIC.length; i++) {
            String string = NUMERIC[i];
            if (type.toLowerCase().startsWith(string)) {
                return i;
            }

        }
        return -1;
    }

    public void loadBD(File file) throws Exception {
        connection = null;
        statement = null;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager
                .getConnection("jdbc:sqlite:" + file.getPath());
    }

    public void setPackageName(String pn) {

        esquema = new Schema(1, pn);


    }

    public void setOutputDir(String path) throws Exception {
        new DaoGenerator().generateAll(esquema, path+"/");


    }
    
    public boolean gen(){
           return generateORMFiles();

    }

    private boolean generateORMFiles() {

        try {

        statement = connection.createStatement();

            ResultSet resultSet = null;


            resultSetTables = statement
                    .executeQuery("SELECT name FROM sqlite_master WHERE type='table' ORDER BY name;");

            ArrayList<String> tables = new ArrayList();
            while (resultSetTables.next()) {

                tables.add(resultSetTables.getString("name"));

            }

            for (String nameTable : tables) {
                resultSet = statement
                        .executeQuery("PRAGMA table_info(" + nameTable + ")");

                Entity entityIPH = esquema.addEntity(nameTable);
                //entityIPH.addIdProperty();

                while (resultSet.next()) {
                    System.out.println("Registrando entidad:["+resultSet.getString("name")+"], TIPO DE DATO:["+resultSet.getString("type")+"]");

                    if (resultSet.getInt("pk") == integer) {

                        entityIPH.addIntProperty(resultSet.getString("name")).primaryKey();
                    } else {
                        if (integerDataType(resultSet.getString("type"))!=-1) {

                            if (resultSet.getInt("notnull") == 1) {
                                entityIPH.addIntProperty(resultSet.getString("name")).notNull();
                            } else {

                                entityIPH.addIntProperty(resultSet.getString("name"));

                            }
                        } else if (numericDataType(resultSet.getString("type")) == BOOLEAN||numericDataType(resultSet.getString("type")) == BOOL) {
                            if (resultSet.getInt("notnull") == 1) {
                                entityIPH.addBooleanProperty(resultSet.getString("name")).notNull();
                            } else {

                                entityIPH.addBooleanProperty(resultSet.getString("name"));

                            }
                        }else if (numericDataType(resultSet.getString("type")) == NUMERIC_VALUE) {
                            if (resultSet.getInt("notnull") == 1) {
                                entityIPH.addLongProperty(resultSet.getString("name")).notNull();
                            } else {

                                entityIPH.addLongProperty(resultSet.getString("name"));

                            }
                        } else if (numericDataType(resultSet.getString("type")) == DATE) {
                            if (resultSet.getInt("notnull") == 1) {
                                entityIPH.addDateProperty(resultSet.getString("name")).notNull();
                            } else {

                                entityIPH.addDateProperty(resultSet.getString("name"));

                            }
                        } else if (numericDataType(resultSet.getString("type")) == DATE_TIME) {
                            if (resultSet.getInt("notnull") == 1) {
                                entityIPH.addDateProperty(resultSet.getString("name")).notNull();
                            } else {

                                entityIPH.addDateProperty(resultSet.getString("name"));

                            }
                        } else if (textDataType(resultSet.getString("type")) != -1) {
                            if (resultSet.getInt("notnull") == 1) {
                                entityIPH.addStringProperty(resultSet.getString("name")).notNull();
                            } else {

                                entityIPH.addStringProperty(resultSet.getString("name"));

                            }
                        } else if (realDataType(resultSet.getString("type")) == DOUBLE || realDataType(resultSet.getString("type")) == DOUBLE_P ||realDataType(resultSet.getString("type")) == REAL_VALUE) {
                            if (resultSet.getInt("notnull") == 1) {
                                entityIPH.addDoubleProperty(resultSet.getString("name")).notNull();
                            } else {

                                entityIPH.addDoubleProperty(resultSet.getString("name"));

                            }
                        } else if (realDataType(resultSet.getString("type")) == FLOAT) {
                            if (resultSet.getInt("notnull") == 1) {
                                entityIPH.addFloatProperty(resultSet.getString("name")).notNull();
                            } else {

                                entityIPH.addFloatProperty(resultSet.getString("name"));

                            }
                        }else  if (blobDataType(resultSet.getString("type")) == BLOB_VALUE) {
                            if (resultSet.getInt("notnull") == 1) {
                                entityIPH.addByteArrayProperty(resultSet.getString("name")).notNull();
                            } else {

                                entityIPH.addByteArrayProperty(resultSet.getString("name"));

                            }
                        }



                    }


                }
                resultSet.close();



            }




        } catch (Exception e) {
            return false;
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                
                return false;
            }
        }
    
    return true;
    }
}
