/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.busmanagementsystem;

import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Sami
 */


public class ServerConnect {
    
    Connection c;
    Statement s;
    
    public ServerConnect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/busmanagement_db","root","");
            s = c.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
