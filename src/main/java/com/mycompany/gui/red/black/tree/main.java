/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.red.black.tree;

import java.awt.Color;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author kerols
 */
public class main {
    public static void main(String[] args) throws IOException {
        gui g = new gui();
        g.setExtendedState(JFrame.MAXIMIZED_BOTH);
        g.setLocationRelativeTo(null);
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g.getContentPane().setBackground(Color.white);
        g.setVisible(true);
        g.setResizable(false);
    }
}
