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

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author xhendor
 */
public class Window extends JFrame implements ActionListener {

    private JButton selectSQLiteBD;
    private JButton selectOutputDir;
    private JButton generarDAOs;
    private File dataBaseFile;
    private File directorio;
    private JTextField packageName;
    private final ConnectSQLite conn;

    public Window() throws HeadlessException {

        setSize(200, 400);
        selectSQLiteBD = new JButton("Seleccionar BD");
        selectOutputDir = new JButton("Seleccionar directorio");
        selectOutputDir.addActionListener(this);
        selectSQLiteBD.addActionListener(this);
        generarDAOs = new JButton("Generar entidades");
        generarDAOs.addActionListener(this);
        packageName = new JTextField();
        this.setLayout(new GridLayout(0, 1));
        this.getContentPane().add(new JLabel("Nombre del paquete:"));
        this.getContentPane().add(packageName);
        this.getContentPane().add(selectSQLiteBD);
        this.getContentPane().add(selectOutputDir);
        this.getContentPane().add(generarDAOs);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        conn = new ConnectSQLite();
    }

    public static void main(String[] args) {
        new Window().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {


        if (ae.getSource() == selectOutputDir) {
            final JFileChooser fc;
            fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fc.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File file) {

                    if (file.isDirectory()) {
                        return true;
                    }
                    return false;
                }

                @Override
                public String getDescription() {
                    return "Directorio donde se guardaran las entidades";
                }
            });
            fc.showDialog(null, null);
            fc.setVisible(true);
            directorio = fc.getSelectedFile();
        } else if (ae.getSource() == selectSQLiteBD) {
            final JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    String extension = Utils.getExtension(file);
                    if (extension != null) {
                        if (extension.equals(Utils.db)
                                || extension.equals(Utils.sqlite)) {
                            return true;
                        } else {
                            return false;
                        }
                    }

                    return false;
                }

                @Override
                public String getDescription() {
                    return "SQLite Database";
                }
            });
            fc.showDialog(null, null);
            fc.setVisible(true);
            dataBaseFile = fc.getSelectedFile();
        } else if (ae.getSource() == generarDAOs) {


            if (!packageName.getText().trim().isEmpty() && dataBaseFile != null && directorio != null) {
                try {
                    conn.loadBD(dataBaseFile);
                                        conn.setPackageName(packageName.getText());

                    conn.gen();
                                        conn.setOutputDir(directorio.getPath());

                } catch (Exception ex) {
                    System.out.println("Err:" + ex.getMessage());

                }
            }
        }
    }
}
