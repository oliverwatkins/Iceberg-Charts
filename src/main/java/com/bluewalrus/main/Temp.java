/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluewalrus.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author lauren
 */
public class Temp {

    public static void main(String args[]) {

        final Object rowData[][] = {{"1", "one", "I"}, {"2", "two", "II"}, {"3", "three", "III"}};

        final String columnNames[] = {"#", "English", "Roman"};

        final TableModel fixedColumnModel = new AbstractTableModel() {
            public int getColumnCount() {
                return 1;
            }

            public String getColumnName(int column) {
                return columnNames[column];
            }

            public int getRowCount() {
                return rowData.length;
            }

            public Object getValueAt(int row, int column) {
                return rowData[row][column];
            }
        };

        final TableModel mainModel = new AbstractTableModel() {
            public int getColumnCount() {
                return columnNames.length - 1;
            }

            public String getColumnName(int column) {
                return columnNames[column + 1];
            }

            public int getRowCount() {
                return rowData.length;
            }

            public Object getValueAt(int row, int column) {
                return rowData[row][column + 1];
            }
        };

        JTable fixedTable = new JTable(fixedColumnModel);
        fixedTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JTable mainTable = new JTable(mainModel);
        mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        ListSelectionModel model = fixedTable.getSelectionModel();
        mainTable.setSelectionModel(model);

        JScrollPane scrollPane = new JScrollPane(mainTable);
        Dimension fixedSize = fixedTable.getPreferredSize();
        JViewport viewport = new JViewport();
        viewport.setView(fixedTable);
        viewport.setPreferredSize(fixedSize);
        viewport.setMaximumSize(fixedSize);
        scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, fixedTable.getTableHeader());
        scrollPane.setRowHeaderView(viewport);

        JFrame frame = new JFrame("Fixed Column Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(300, 150);
        frame.setVisible(true);
    }
}
