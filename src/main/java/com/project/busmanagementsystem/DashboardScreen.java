/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.busmanagementsystem;

import java.awt.Color;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.*;

/**
 *
 * @author Sami
 */
public class DashboardScreen extends javax.swing.JFrame {

    DefaultTableModel model;

    /**
     * Creates new form DashboardScreen
     */
    public DashboardScreen() {
        super("Bus Management System");
        initComponents();

    }

    /**
     * User Comment Title How to search in jtable without button| Part-21 Fees
     * Management System | | Unique Developer|
     *
     */
    //driver Details start----------
    private void driver_tbl_reset() {
        model = (DefaultTableModel) tbl_driver.getModel();
        model.setRowCount(0);
    }

    private void driverTableColumnHide() {

        TableColumnManager tcm = new TableColumnManager(tbl_driver);
        tcm.hideColumn(2);
        tcm.hideColumn(3);
        tcm.hideColumn(4);
        tcm.hideColumn(5);
        tcm.hideColumn(6);
        tcm.hideColumn(7);
        tcm.hideColumn(8);
        tcm.hideColumn(9);
        tcm.hideColumn(10);
    }

    //Unused
    public void search_driverTable(String str) {
        model = (DefaultTableModel) tbl_driver.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tbl_driver.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str, 0));
    }

    public void addDriverFunc(String name, String address, String contact, String dob, String doj, String license, String licenseIssue, String licenseExp, int assignBus, String medical) {
        try {
            ServerConnect con = new ServerConnect();
            PreparedStatement pst = con.c.prepareStatement("INSERT INTO driver(driver_name, address, contact_num, dob, date_of_joining, license_num, license_issue, license_exp, assign_bus, medical_issue) VALUES(?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, name);
            pst.setString(2, address);
            pst.setString(3, contact);
            pst.setString(4, dob);
            pst.setString(5, doj);
            pst.setString(6, license);
            pst.setString(7, licenseIssue);
            pst.setString(8, licenseExp);
            pst.setInt(9, assignBus);
            pst.setString(10, medical);

            int rowCnt = pst.executeUpdate();
            if (rowCnt == 1) {
                JOptionPane.showMessageDialog(this, "Added Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Insertion Failed!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Insertion Failed!");
        }
    }

    public void updateDriverFunc(int id, String name, String address, String contact, String dob, String doj, String license, String licenseIssue, String licenseExp, int assignBus, String medical) {
        try {
            ServerConnect con = new ServerConnect();
            PreparedStatement pst = con.c.prepareStatement("UPDATE driver SET driver_name=?,address=?,contact_num=?,dob=?,date_of_joining=?,license_num=?,license_issue=?,license_exp=?,assign_bus=?,medical_issue=? where driver_id=?");

            pst.setString(1, name);
            pst.setString(2, address);
            pst.setString(3, contact);
            pst.setString(4, dob);
            pst.setString(5, doj);
            pst.setString(6, license);
            pst.setString(7, licenseIssue);
            pst.setString(8, licenseExp);
            pst.setInt(9, assignBus);
            pst.setString(10, medical);
            pst.setInt(11, id);

            int rowCnt = pst.executeUpdate();
            if (rowCnt == 1) {
                JOptionPane.showMessageDialog(this, "Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Update Failed!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Update Failed!");
        }
    }

    public void deleteDriverFunc(int id) {
        try {
            ServerConnect con = new ServerConnect();
            PreparedStatement pst = con.c.prepareStatement("DELETE FROM driver where driver_id = ?");

            pst.setInt(1, id);

            int rowCnt = pst.executeUpdate();
            if (rowCnt == 1) {
                JOptionPane.showMessageDialog(this, "Deletion Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Deletion Failed!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Deletion Failed!");
        }
    }

    //Driver details End------------
    //Bus details Start--------------------
    private void bus_tbl_reset() {
        model = (DefaultTableModel) tbl_bus.getModel();
        model.setRowCount(0);
    }

    private void busTableColumnHide() {

        TableColumnManager tcm = new TableColumnManager(tbl_bus);

        tcm.hideColumn(3);
        tcm.hideColumn(4);
        tcm.hideColumn(5);
        tcm.hideColumn(6);
        tcm.hideColumn(7);
        tcm.hideColumn(8);

    }

    public void addBusDetailsFunc(String license, int type, int seat, String name, String contact, int duration, String address, String fitnessCert) {
        try {
            ServerConnect con = new ServerConnect();
            PreparedStatement pst = con.c.prepareStatement("INSERT INTO bus(bus_license, bus_type, seat_number, owner, owner_contact, duration, address, fitness_cert) VALUES(?,?,?,?,?,?,?,?)");
            pst.setString(1, license);
            pst.setInt(2, type);
            pst.setInt(3, seat);
            pst.setString(4, name);
            pst.setString(5, contact);
            pst.setInt(6, duration);
            pst.setString(7, address);
            pst.setString(8, fitnessCert);

            int rowCnt = pst.executeUpdate();
            if (rowCnt == 1) {
                JOptionPane.showMessageDialog(this, "Added Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Insertion Failed!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Insertion Failed!");
        }
    }

    public void updateBusFunc(int serial, String license, int type, int seat, String owner, String contact, String address, int duration, String fitness) {
        try {
            ServerConnect con = new ServerConnect();
            PreparedStatement pst = con.c.prepareStatement("UPDATE bus SET bus_license=?, bus_type=?,seat_number=?,owner=?,owner_contact=?,duration=?,address=?,fitness=? where serial=?");

            pst.setString(1, license);
            pst.setInt(2, type);
            pst.setInt(3, seat);
            pst.setString(4, owner);
            pst.setString(5, contact);
            pst.setInt(6, duration);
            pst.setString(7, address);
            pst.setString(8, fitness);
            pst.setInt(9, serial);

            int rowCnt = pst.executeUpdate();
            if (rowCnt == 1) {
                JOptionPane.showMessageDialog(this, "Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Update Failed!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Update Failed!");
        }
    }

    public void deleteBusFunc(int serial) {
        try {
            ServerConnect con = new ServerConnect();
            PreparedStatement pst = con.c.prepareStatement("DELETE FROM bus where serial = ?");

            pst.setInt(1, serial);

            int rowCnt = pst.executeUpdate();
            if (rowCnt == 1) {
                JOptionPane.showMessageDialog(this, "Deletion Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Deletion Failed!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Deletion Failed!");
        }
    }

    //Bus Details End---------------
    //route table--
    private void route_tbl_reset() {
        model = (DefaultTableModel) tbl_route.getModel();
        model.setRowCount(0);
    }
    //route table end

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        wholePanel = new javax.swing.JPanel();
        sideBar = new javax.swing.JPanel();
        sideDashboardPnl = new javax.swing.JPanel();
        sideDashboardLbl = new javax.swing.JLabel();
        sideDriverPnl = new javax.swing.JPanel();
        sideDriverLbl = new javax.swing.JLabel();
        sideBusPnl = new javax.swing.JPanel();
        sideBusLbl = new javax.swing.JLabel();
        sideSchedulePnl = new javax.swing.JPanel();
        sideScheduleLbl = new javax.swing.JLabel();
        sidePaymentPnl = new javax.swing.JPanel();
        sidePaymentLbl = new javax.swing.JLabel();
        sideInvoicePnl = new javax.swing.JPanel();
        sideInvoiceLbl = new javax.swing.JLabel();
        sideLogoutPnl = new javax.swing.JPanel();
        sideLogoutLbl = new javax.swing.JLabel();
        topBar = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        middlePanel = new javax.swing.JPanel();
        dashBoard = new javax.swing.JPanel();
        driverDetailsCard5 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jLabel89 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        invoice = new javax.swing.JPanel();
        btnUpdateDriver4 = new javax.swing.JPanel();
        topGenerateDriverInvcePnl = new javax.swing.JPanel();
        topGenerateDriverInvceLbl = new javax.swing.JLabel();
        topGenerateBusInvcePnl = new javax.swing.JPanel();
        topGenerateBusInvceLbl = new javax.swing.JLabel();
        invoiceCard = new javax.swing.JPanel();
        driverInvoice = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jTextField35 = new javax.swing.JTextField();
        jComboBox17 = new javax.swing.JComboBox<>();
        jTextField37 = new javax.swing.JTextField();
        busInvoice = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jPanel22 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jTextField46 = new javax.swing.JTextField();
        jComboBox18 = new javax.swing.JComboBox<>();
        jTextField47 = new javax.swing.JTextField();
        payment = new javax.swing.JPanel();
        btnUpdateDriver3 = new javax.swing.JPanel();
        topPayDriverPnl = new javax.swing.JPanel();
        topPayDriverLbl = new javax.swing.JLabel();
        topPayBusPnl = new javax.swing.JPanel();
        topPayBusLbl = new javax.swing.JLabel();
        paymentCard = new javax.swing.JPanel();
        payBus = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jTextField29 = new javax.swing.JTextField();
        jComboBox16 = new javax.swing.JComboBox<>();
        jTextField31 = new javax.swing.JTextField();
        jTextField33 = new javax.swing.JTextField();
        payDriver = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jTextField12 = new javax.swing.JTextField();
        jComboBox15 = new javax.swing.JComboBox<>();
        jTextField18 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        driverDetails = new javax.swing.JPanel();
        btnUpdateDriver = new javax.swing.JPanel();
        topAddDriverBtnPnl = new javax.swing.JPanel();
        topAddDriverBtnLbl = new javax.swing.JLabel();
        topUpdateDriverBtnPnl = new javax.swing.JPanel();
        topUpdateDriverBtnLbl = new javax.swing.JLabel();
        driverDetailsCard = new javax.swing.JPanel();
        addDriverPane = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtDriverAddress = new javax.swing.JTextField();
        txtDriverContactNo = new javax.swing.JTextField();
        txtDriverDob = new javax.swing.JTextField();
        txtDriverJoinDate = new javax.swing.JTextField();
        txtDriverLicenseNum = new javax.swing.JTextField();
        txtDriverLicenseIssue = new javax.swing.JTextField();
        txtDriverLicenseExp = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtDriverName = new javax.swing.JTextField();
        tickDriverMedicalIssue = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        addDriverBtn = new javax.swing.JButton();
        dropDriverAssignBusNo = new javax.swing.JComboBox<>();
        updateDriverPane = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        txt_u_driverId = new javax.swing.JTextField();
        txt_u_driverName = new javax.swing.JTextField();
        txt_u_address = new javax.swing.JTextField();
        txt_u_contact = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        txt_u_medicalIssue = new javax.swing.JCheckBox();
        jLabel62 = new javax.swing.JLabel();
        driverUpdateBtn = new javax.swing.JButton();
        txt_u_dob = new javax.swing.JTextField();
        txt_u_doj = new javax.swing.JTextField();
        txt_u_license = new javax.swing.JTextField();
        txt_u_licenseIssue = new javax.swing.JTextField();
        txt_u_licenseExp = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_driver = new javax.swing.JTable();
        txt_u_assignBus = new javax.swing.JComboBox<>();
        updateDriverSearchBtn = new javax.swing.JButton();
        driverRemoveBtn = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        addBus = new javax.swing.JPanel();
        btnUpdateDriver1 = new javax.swing.JPanel();
        topAddBusBtnPnl = new javax.swing.JPanel();
        topAddBusBtnLbl = new javax.swing.JLabel();
        topUpdateBusBtnPnl = new javax.swing.JPanel();
        topUpdateBusBtnLbl = new javax.swing.JLabel();
        busDetailsCard = new javax.swing.JPanel();
        addBusDetails = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtSeatNum = new javax.swing.JTextField();
        txtBusOwner = new javax.swing.JTextField();
        txtBusOwnerContact = new javax.swing.JTextField();
        txtBusOwnerAddress = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtBusLicense = new javax.swing.JTextField();
        txtBusFitnessCert = new javax.swing.JCheckBox();
        txtBusType = new javax.swing.JComboBox<>();
        busDetailsAddBtn = new javax.swing.JButton();
        txtBusContractDuration = new javax.swing.JComboBox<>();
        updateBusDetails = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        busUpdateBtn = new javax.swing.JButton();
        txtUBusSerial = new javax.swing.JTextField();
        txtUBusType = new javax.swing.JComboBox<>();
        txtUBusSeat = new javax.swing.JTextField();
        txtUBusName = new javax.swing.JTextField();
        txtUBusContact = new javax.swing.JTextField();
        txtUBusAddress = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_bus = new javax.swing.JTable();
        txtUBusDuration = new javax.swing.JComboBox<>();
        busRefreshBtn = new javax.swing.JButton();
        removeBusBtn = new javax.swing.JButton();
        updateBusSearchBtn = new javax.swing.JButton();
        txtUBusFitnessCert = new javax.swing.JCheckBox();
        jLabel51 = new javax.swing.JLabel();
        txtUBusLicense = new javax.swing.JTextField();
        busScheduling = new javax.swing.JPanel();
        btnUpdateDriver2 = new javax.swing.JPanel();
        addBusSchPnl = new javax.swing.JPanel();
        addBusSchLbl = new javax.swing.JLabel();
        addBusRoutePnl = new javax.swing.JPanel();
        addBusRouteLbl = new javax.swing.JLabel();
        busScheduleCard = new javax.swing.JPanel();
        addSchedulePane = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jSpinner3 = new javax.swing.JSpinner();
        jSpinner4 = new javax.swing.JSpinner();
        jButton3 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox<>();
        addRoutePane = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_route = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        addRouteBtn = new javax.swing.JButton();
        txtRouteName = new javax.swing.JTextField();
        removeRouteBtn = new javax.swing.JButton();
        routeRefreshBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        wholePanel.setBackground(new java.awt.Color(248, 248, 248));

        sideBar.setBackground(new java.awt.Color(99, 225, 163));

        sideDashboardPnl.setBackground(new java.awt.Color(99, 225, 163));
        sideDashboardPnl.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        sideDashboardPnl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sideDashboardPnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sideDashboardPnlMouseClicked(evt);
            }
        });

        sideDashboardLbl.setBackground(new java.awt.Color(99, 225, 163));
        sideDashboardLbl.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        sideDashboardLbl.setForeground(new java.awt.Color(255, 255, 255));
        sideDashboardLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashboardImg.png"))); // NOI18N
        sideDashboardLbl.setText("Dashboard");
        sideDashboardLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sideDashboardLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sideDashboardLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sideDashboardLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout sideDashboardPnlLayout = new javax.swing.GroupLayout(sideDashboardPnl);
        sideDashboardPnl.setLayout(sideDashboardPnlLayout);
        sideDashboardPnlLayout.setHorizontalGroup(
            sideDashboardPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideDashboardPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sideDashboardLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
        );
        sideDashboardPnlLayout.setVerticalGroup(
            sideDashboardPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sideDashboardLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        sideDriverPnl.setBackground(new java.awt.Color(99, 225, 163));
        sideDriverPnl.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        sideDriverPnl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        sideDriverLbl.setBackground(new java.awt.Color(99, 225, 163));
        sideDriverLbl.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        sideDriverLbl.setForeground(new java.awt.Color(255, 255, 255));
        sideDriverLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/driverDetails.png"))); // NOI18N
        sideDriverLbl.setText("Driver Details");
        sideDriverLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sideDriverLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sideDriverLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sideDriverLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout sideDriverPnlLayout = new javax.swing.GroupLayout(sideDriverPnl);
        sideDriverPnl.setLayout(sideDriverPnlLayout);
        sideDriverPnlLayout.setHorizontalGroup(
            sideDriverPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideDriverPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sideDriverLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sideDriverPnlLayout.setVerticalGroup(
            sideDriverPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sideDriverLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        sideBusPnl.setBackground(new java.awt.Color(99, 225, 163));
        sideBusPnl.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        sideBusPnl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sideBusPnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sideBusPnlMouseClicked(evt);
            }
        });

        sideBusLbl.setBackground(new java.awt.Color(99, 225, 163));
        sideBusLbl.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        sideBusLbl.setForeground(new java.awt.Color(255, 255, 255));
        sideBusLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/addBus.png"))); // NOI18N
        sideBusLbl.setText("Add Bus");
        sideBusLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sideBusLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sideBusLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sideBusLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout sideBusPnlLayout = new javax.swing.GroupLayout(sideBusPnl);
        sideBusPnl.setLayout(sideBusPnlLayout);
        sideBusPnlLayout.setHorizontalGroup(
            sideBusPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideBusPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sideBusLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sideBusPnlLayout.setVerticalGroup(
            sideBusPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sideBusLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        sideSchedulePnl.setBackground(new java.awt.Color(99, 225, 163));
        sideSchedulePnl.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        sideSchedulePnl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        sideScheduleLbl.setBackground(new java.awt.Color(99, 225, 163));
        sideScheduleLbl.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        sideScheduleLbl.setForeground(new java.awt.Color(255, 255, 255));
        sideScheduleLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/busSchedule.png"))); // NOI18N
        sideScheduleLbl.setText("Bus Scheduling");
        sideScheduleLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sideScheduleLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sideScheduleLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sideScheduleLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout sideSchedulePnlLayout = new javax.swing.GroupLayout(sideSchedulePnl);
        sideSchedulePnl.setLayout(sideSchedulePnlLayout);
        sideSchedulePnlLayout.setHorizontalGroup(
            sideSchedulePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideSchedulePnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sideScheduleLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sideSchedulePnlLayout.setVerticalGroup(
            sideSchedulePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sideScheduleLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        sidePaymentPnl.setBackground(new java.awt.Color(99, 225, 163));
        sidePaymentPnl.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        sidePaymentPnl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        sidePaymentLbl.setBackground(new java.awt.Color(99, 225, 163));
        sidePaymentLbl.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        sidePaymentLbl.setForeground(new java.awt.Color(255, 255, 255));
        sidePaymentLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/feesEntry.png"))); // NOI18N
        sidePaymentLbl.setText("Payment");
        sidePaymentLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sidePaymentLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sidePaymentLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sidePaymentLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout sidePaymentPnlLayout = new javax.swing.GroupLayout(sidePaymentPnl);
        sidePaymentPnl.setLayout(sidePaymentPnlLayout);
        sidePaymentPnlLayout.setHorizontalGroup(
            sidePaymentPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePaymentPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sidePaymentLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sidePaymentPnlLayout.setVerticalGroup(
            sidePaymentPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sidePaymentLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        sideInvoicePnl.setBackground(new java.awt.Color(99, 225, 163));
        sideInvoicePnl.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        sideInvoicePnl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        sideInvoiceLbl.setBackground(new java.awt.Color(99, 225, 163));
        sideInvoiceLbl.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        sideInvoiceLbl.setForeground(new java.awt.Color(255, 255, 255));
        sideInvoiceLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/feesRetrival.png"))); // NOI18N
        sideInvoiceLbl.setText("Invoice");
        sideInvoiceLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sideInvoiceLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sideInvoiceLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sideInvoiceLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout sideInvoicePnlLayout = new javax.swing.GroupLayout(sideInvoicePnl);
        sideInvoicePnl.setLayout(sideInvoicePnlLayout);
        sideInvoicePnlLayout.setHorizontalGroup(
            sideInvoicePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideInvoicePnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sideInvoiceLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sideInvoicePnlLayout.setVerticalGroup(
            sideInvoicePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sideInvoiceLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        sideLogoutPnl.setBackground(new java.awt.Color(99, 225, 163));
        sideLogoutPnl.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        sideLogoutPnl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        sideLogoutLbl.setBackground(new java.awt.Color(99, 225, 163));
        sideLogoutLbl.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        sideLogoutLbl.setForeground(new java.awt.Color(255, 255, 255));
        sideLogoutLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logoutImg.png"))); // NOI18N
        sideLogoutLbl.setText("Logout");
        sideLogoutLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sideLogoutLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sideLogoutLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sideLogoutLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout sideLogoutPnlLayout = new javax.swing.GroupLayout(sideLogoutPnl);
        sideLogoutPnl.setLayout(sideLogoutPnlLayout);
        sideLogoutPnlLayout.setHorizontalGroup(
            sideLogoutPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideLogoutPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sideLogoutLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sideLogoutPnlLayout.setVerticalGroup(
            sideLogoutPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sideLogoutLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout sideBarLayout = new javax.swing.GroupLayout(sideBar);
        sideBar.setLayout(sideBarLayout);
        sideBarLayout.setHorizontalGroup(
            sideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sideDriverPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sideBarLayout.createSequentialGroup()
                .addComponent(sideDashboardPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(sideBusPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sideSchedulePnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sidePaymentPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sideInvoicePnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sideLogoutPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sideBarLayout.setVerticalGroup(
            sideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideBarLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(sideDashboardPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideDriverPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideBusPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideSchedulePnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sidePaymentPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideInvoicePnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 260, Short.MAX_VALUE)
                .addComponent(sideLogoutPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        topBar.setBackground(new java.awt.Color(99, 225, 163));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Bus Management System");

        javax.swing.GroupLayout topBarLayout = new javax.swing.GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        topBarLayout.setVerticalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topBarLayout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        middlePanel.setBackground(new java.awt.Color(248, 248, 248));
        middlePanel.setLayout(new java.awt.CardLayout());

        dashBoard.setBackground(new java.awt.Color(239, 239, 239));

        driverDetailsCard5.setBackground(new java.awt.Color(227, 227, 227));

        jPanel23.setBackground(new java.awt.Color(239, 239, 239));

        jPanel24.setBackground(new java.awt.Color(246, 246, 246));

        jLabel76.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(102, 102, 102));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText("     Bus");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jLabel90.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(102, 102, 102));
        jLabel90.setText("10000");

        jLabel93.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(153, 153, 153));
        jLabel93.setText("<html> <p>Total Number</p> of Bus </html>");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel90)
                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel90)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jPanel26.setBackground(new java.awt.Color(239, 239, 239));

        jPanel27.setBackground(new java.awt.Color(246, 246, 246));

        jLabel66.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(102, 102, 102));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel66.setText("     Driver");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jLabel97.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(153, 153, 153));
        jLabel97.setText("<html> <p>Total Number</p> of Driver</html>");

        jLabel91.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(102, 102, 102));
        jLabel91.setText("10000");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel91)
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel91)
                .addGap(0, 48, Short.MAX_VALUE))
        );

        jPanel28.setBackground(new java.awt.Color(239, 239, 239));

        jPanel29.setBackground(new java.awt.Color(246, 246, 246));

        jLabel87.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(102, 102, 102));
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel87.setText("     Route");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );

        jLabel98.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(153, 153, 153));
        jLabel98.setText("<html> <p>Total Number</p> of Route </html>");

        jLabel99.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(102, 102, 102));
        jLabel99.setText("15");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel99)
                    .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel99)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel30.setBackground(new java.awt.Color(227, 227, 227));

        jPanel31.setBackground(new java.awt.Color(239, 239, 239));

        jPanel33.setBackground(new java.awt.Color(246, 246, 246));

        jLabel89.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(102, 102, 102));
        jLabel89.setText("Graph");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel32.setBackground(new java.awt.Color(239, 239, 239));

        jPanel34.setBackground(new java.awt.Color(246, 246, 246));

        jLabel88.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(102, 102, 102));
        jLabel88.setText("     Due Amount");

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel88, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel88, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        jLabel92.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(153, 153, 153));
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel92.setText("<html><p>Last Month</p>Total Amount of Payment<html>");

        jLabel94.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(102, 102, 102));
        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel94.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blackDollarSign.png"))); // NOI18N
        jLabel94.setText("100000");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel94, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel92))
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 69, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout driverDetailsCard5Layout = new javax.swing.GroupLayout(driverDetailsCard5);
        driverDetailsCard5.setLayout(driverDetailsCard5Layout);
        driverDetailsCard5Layout.setHorizontalGroup(
            driverDetailsCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, driverDetailsCard5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(driverDetailsCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(driverDetailsCard5Layout.createSequentialGroup()
                        .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(29, 29, 29)
                        .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(29, 29, 29)
                        .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        driverDetailsCard5Layout.setVerticalGroup(
            driverDetailsCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(driverDetailsCard5Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(driverDetailsCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jLabel86.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(69, 69, 69));
        jLabel86.setText("Dashboard");

        javax.swing.GroupLayout dashBoardLayout = new javax.swing.GroupLayout(dashBoard);
        dashBoard.setLayout(dashBoardLayout);
        dashBoardLayout.setHorizontalGroup(
            dashBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(driverDetailsCard5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(dashBoardLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel86)
                .addContainerGap(757, Short.MAX_VALUE))
        );
        dashBoardLayout.setVerticalGroup(
            dashBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dashBoardLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(driverDetailsCard5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        middlePanel.add(dashBoard, "card2");

        invoice.setBackground(new java.awt.Color(248, 248, 248));

        btnUpdateDriver4.setBackground(new java.awt.Color(248, 248, 248));
        btnUpdateDriver4.setLayout(new java.awt.GridLayout(1, 0));

        topGenerateDriverInvcePnl.setBackground(new java.awt.Color(60, 188, 225));

        topGenerateDriverInvceLbl.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        topGenerateDriverInvceLbl.setForeground(new java.awt.Color(255, 255, 255));
        topGenerateDriverInvceLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        topGenerateDriverInvceLbl.setText("Generate Invoice for Driver");
        topGenerateDriverInvceLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        topGenerateDriverInvceLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                topGenerateDriverInvceLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                topGenerateDriverInvceLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                topGenerateDriverInvceLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout topGenerateDriverInvcePnlLayout = new javax.swing.GroupLayout(topGenerateDriverInvcePnl);
        topGenerateDriverInvcePnl.setLayout(topGenerateDriverInvcePnlLayout);
        topGenerateDriverInvcePnlLayout.setHorizontalGroup(
            topGenerateDriverInvcePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topGenerateDriverInvceLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        topGenerateDriverInvcePnlLayout.setVerticalGroup(
            topGenerateDriverInvcePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topGenerateDriverInvceLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        btnUpdateDriver4.add(topGenerateDriverInvcePnl);

        topGenerateBusInvcePnl.setBackground(new java.awt.Color(250, 199, 90));

        topGenerateBusInvceLbl.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        topGenerateBusInvceLbl.setForeground(new java.awt.Color(255, 255, 255));
        topGenerateBusInvceLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        topGenerateBusInvceLbl.setText("Generate Invoice For Bus");
        topGenerateBusInvceLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        topGenerateBusInvceLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                topGenerateBusInvceLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                topGenerateBusInvceLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                topGenerateBusInvceLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout topGenerateBusInvcePnlLayout = new javax.swing.GroupLayout(topGenerateBusInvcePnl);
        topGenerateBusInvcePnl.setLayout(topGenerateBusInvcePnlLayout);
        topGenerateBusInvcePnlLayout.setHorizontalGroup(
            topGenerateBusInvcePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topGenerateBusInvceLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        topGenerateBusInvcePnlLayout.setVerticalGroup(
            topGenerateBusInvcePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topGenerateBusInvceLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        btnUpdateDriver4.add(topGenerateBusInvcePnl);

        invoiceCard.setBackground(new java.awt.Color(255, 255, 255));
        invoiceCard.setLayout(new java.awt.CardLayout());

        driverInvoice.setBackground(new java.awt.Color(255, 255, 255));

        jLabel82.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel82.setText("Generate Invoice for Driver");

        jTable9.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Serial No", "Driver ID", "Month", "Year", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(jTable9);
        if (jTable9.getColumnModel().getColumnCount() > 0) {
            jTable9.getColumnModel().getColumn(0).setResizable(false);
            jTable9.getColumnModel().getColumn(1).setResizable(false);
            jTable9.getColumnModel().getColumn(2).setResizable(false);
            jTable9.getColumnModel().getColumn(3).setResizable(false);
            jTable9.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel21.setLayout(new java.awt.GridLayout(1, 0));

        jLabel83.setBackground(new java.awt.Color(255, 255, 255));
        jLabel83.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel83.setText("Driver Employee ID");

        jLabel84.setBackground(new java.awt.Color(255, 255, 255));
        jLabel84.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel84.setText("Select Month");

        jLabel85.setBackground(new java.awt.Color(255, 255, 255));
        jLabel85.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel85.setText("Enter Year");

        jButton14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton14.setText("GENERATE");

        jTextField35.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBox17.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
        jComboBox17.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField37.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout driverInvoiceLayout = new javax.swing.GroupLayout(driverInvoice);
        driverInvoice.setLayout(driverInvoiceLayout);
        driverInvoiceLayout.setHorizontalGroup(
            driverInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(driverInvoiceLayout.createSequentialGroup()
                .addGroup(driverInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, driverInvoiceLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel82)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(driverInvoiceLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(driverInvoiceLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(driverInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, driverInvoiceLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, driverInvoiceLayout.createSequentialGroup()
                                .addGroup(driverInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel84, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel85, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(103, 103, 103)
                                .addGroup(driverInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField35, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox17, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                                    .addComponent(jTextField37, javax.swing.GroupLayout.Alignment.LEADING))))))
                .addGap(135, 135, 135))
        );
        driverInvoiceLayout.setVerticalGroup(
            driverInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(driverInvoiceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(driverInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(driverInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84)
                    .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(driverInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel85)
                    .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        invoiceCard.add(driverInvoice, "card2");

        busInvoice.setBackground(new java.awt.Color(255, 255, 255));

        jLabel72.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel72.setText("Generate Invoice for Bus");

        jTable7.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Serial No", "Bus Number", "Month", "Year", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTable7);
        if (jTable7.getColumnModel().getColumnCount() > 0) {
            jTable7.getColumnModel().getColumn(0).setResizable(false);
            jTable7.getColumnModel().getColumn(1).setResizable(false);
            jTable7.getColumnModel().getColumn(2).setResizable(false);
            jTable7.getColumnModel().getColumn(3).setResizable(false);
            jTable7.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel22.setLayout(new java.awt.GridLayout(1, 0));

        jLabel73.setBackground(new java.awt.Color(255, 255, 255));
        jLabel73.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel73.setText("Enter Bus Number");

        jLabel74.setBackground(new java.awt.Color(255, 255, 255));
        jLabel74.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel74.setText("Select Month");

        jLabel75.setBackground(new java.awt.Color(255, 255, 255));
        jLabel75.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel75.setText("Select Year");

        jButton12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton12.setText("GENERATE");

        jTextField46.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBox18.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
        jComboBox18.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField47.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout busInvoiceLayout = new javax.swing.GroupLayout(busInvoice);
        busInvoice.setLayout(busInvoiceLayout);
        busInvoiceLayout.setHorizontalGroup(
            busInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, busInvoiceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(busInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(busInvoiceLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(busInvoiceLayout.createSequentialGroup()
                        .addGroup(busInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel74, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel75, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(103, 103, 103)
                        .addGroup(busInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField46, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox18, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                            .addComponent(jTextField47, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, busInvoiceLayout.createSequentialGroup()
                        .addComponent(jLabel72)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(busInvoiceLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        busInvoiceLayout.setVerticalGroup(
            busInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(busInvoiceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(busInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(busInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(busInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addContainerGap())
        );

        invoiceCard.add(busInvoice, "card2");

        javax.swing.GroupLayout invoiceLayout = new javax.swing.GroupLayout(invoice);
        invoice.setLayout(invoiceLayout);
        invoiceLayout.setHorizontalGroup(
            invoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(invoiceCard, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(btnUpdateDriver4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        invoiceLayout.setVerticalGroup(
            invoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, invoiceLayout.createSequentialGroup()
                .addComponent(btnUpdateDriver4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(invoiceCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        middlePanel.add(invoice, "card2");

        payment.setBackground(new java.awt.Color(248, 248, 248));

        btnUpdateDriver3.setBackground(new java.awt.Color(248, 248, 248));
        btnUpdateDriver3.setLayout(new java.awt.GridLayout(1, 0));

        topPayDriverPnl.setBackground(new java.awt.Color(60, 188, 225));

        topPayDriverLbl.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        topPayDriverLbl.setForeground(new java.awt.Color(255, 255, 255));
        topPayDriverLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        topPayDriverLbl.setText("Pay Driver");
        topPayDriverLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        topPayDriverLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                topPayDriverLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                topPayDriverLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                topPayDriverLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout topPayDriverPnlLayout = new javax.swing.GroupLayout(topPayDriverPnl);
        topPayDriverPnl.setLayout(topPayDriverPnlLayout);
        topPayDriverPnlLayout.setHorizontalGroup(
            topPayDriverPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPayDriverLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        topPayDriverPnlLayout.setVerticalGroup(
            topPayDriverPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPayDriverLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        btnUpdateDriver3.add(topPayDriverPnl);

        topPayBusPnl.setBackground(new java.awt.Color(250, 199, 90));

        topPayBusLbl.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        topPayBusLbl.setForeground(new java.awt.Color(255, 255, 255));
        topPayBusLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        topPayBusLbl.setText("Pay Bus");
        topPayBusLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        topPayBusLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                topPayBusLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                topPayBusLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                topPayBusLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout topPayBusPnlLayout = new javax.swing.GroupLayout(topPayBusPnl);
        topPayBusPnl.setLayout(topPayBusPnlLayout);
        topPayBusPnlLayout.setHorizontalGroup(
            topPayBusPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPayBusLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        topPayBusPnlLayout.setVerticalGroup(
            topPayBusPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPayBusLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        btnUpdateDriver3.add(topPayBusPnl);

        paymentCard.setBackground(new java.awt.Color(255, 255, 255));
        paymentCard.setLayout(new java.awt.CardLayout());

        payBus.setBackground(new java.awt.Color(255, 255, 255));

        jLabel77.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel77.setText("Pay Bus");

        jTable8.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Serial No", "Bus Number", "Month", "Year", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(jTable8);
        if (jTable8.getColumnModel().getColumnCount() > 0) {
            jTable8.getColumnModel().getColumn(0).setResizable(false);
            jTable8.getColumnModel().getColumn(1).setResizable(false);
            jTable8.getColumnModel().getColumn(2).setResizable(false);
            jTable8.getColumnModel().getColumn(3).setResizable(false);
            jTable8.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel20.setLayout(new java.awt.GridLayout(1, 0));

        jLabel78.setBackground(new java.awt.Color(255, 255, 255));
        jLabel78.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel78.setText("Bus Number");

        jLabel79.setBackground(new java.awt.Color(255, 255, 255));
        jLabel79.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel79.setText("Select Month");

        jLabel80.setBackground(new java.awt.Color(255, 255, 255));
        jLabel80.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel80.setText("Enter Year");

        jLabel81.setBackground(new java.awt.Color(255, 255, 255));
        jLabel81.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel81.setText("Payable Amount");

        jButton13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton13.setText("ADD");

        jTextField29.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBox16.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
        jComboBox16.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField31.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextField33.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout payBusLayout = new javax.swing.GroupLayout(payBus);
        payBus.setLayout(payBusLayout);
        payBusLayout.setHorizontalGroup(
            payBusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, payBusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(payBusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(payBusLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(payBusLayout.createSequentialGroup()
                        .addGroup(payBusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel79, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel80, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(103, 103, 103)
                        .addGroup(payBusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField29, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox16, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                            .addComponent(jTextField31, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField33, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, payBusLayout.createSequentialGroup()
                        .addComponent(jLabel77)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(payBusLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        payBusLayout.setVerticalGroup(
            payBusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(payBusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(payBusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(payBusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(payBusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(payBusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addContainerGap())
        );

        paymentCard.add(payBus, "card2");

        payDriver.setBackground(new java.awt.Color(255, 255, 255));

        jLabel67.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel67.setText("Pay Driver");

        jTable6.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Serial No", "Driver ID", "Driver Name", "Month", "Year", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTable6);
        if (jTable6.getColumnModel().getColumnCount() > 0) {
            jTable6.getColumnModel().getColumn(0).setResizable(false);
            jTable6.getColumnModel().getColumn(1).setResizable(false);
            jTable6.getColumnModel().getColumn(2).setResizable(false);
            jTable6.getColumnModel().getColumn(2).setHeaderValue("Driver Name");
            jTable6.getColumnModel().getColumn(3).setResizable(false);
            jTable6.getColumnModel().getColumn(4).setResizable(false);
            jTable6.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel18.setLayout(new java.awt.GridLayout(1, 0));

        jLabel68.setBackground(new java.awt.Color(255, 255, 255));
        jLabel68.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel68.setText("Driver Employee ID");

        jLabel69.setBackground(new java.awt.Color(255, 255, 255));
        jLabel69.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel69.setText("Select Month");

        jLabel70.setBackground(new java.awt.Color(255, 255, 255));
        jLabel70.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel70.setText("Enter Year");

        jLabel71.setBackground(new java.awt.Color(255, 255, 255));
        jLabel71.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel71.setText("Payable Amount");

        jButton11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton11.setText("ADD");

        jTextField12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBox15.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
        jComboBox15.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField18.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextField22.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout payDriverLayout = new javax.swing.GroupLayout(payDriver);
        payDriver.setLayout(payDriverLayout);
        payDriverLayout.setHorizontalGroup(
            payDriverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, payDriverLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(payDriverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(payDriverLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(payDriverLayout.createSequentialGroup()
                        .addGroup(payDriverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel69, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel70, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(103, 103, 103)
                        .addGroup(payDriverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField12, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox15, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                            .addComponent(jTextField18, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField22, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, payDriverLayout.createSequentialGroup()
                        .addComponent(jLabel67)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(payDriverLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        payDriverLayout.setVerticalGroup(
            payDriverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(payDriverLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(payDriverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(payDriverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(payDriverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(payDriverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addContainerGap())
        );

        paymentCard.add(payDriver, "card2");

        javax.swing.GroupLayout paymentLayout = new javax.swing.GroupLayout(payment);
        payment.setLayout(paymentLayout);
        paymentLayout.setHorizontalGroup(
            paymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paymentCard, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(btnUpdateDriver3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        paymentLayout.setVerticalGroup(
            paymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paymentLayout.createSequentialGroup()
                .addComponent(btnUpdateDriver3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paymentCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        middlePanel.add(payment, "card2");

        driverDetails.setBackground(new java.awt.Color(248, 248, 248));

        btnUpdateDriver.setBackground(new java.awt.Color(248, 248, 248));
        btnUpdateDriver.setLayout(new java.awt.GridLayout(1, 0));

        topAddDriverBtnPnl.setBackground(new java.awt.Color(60, 188, 225));

        topAddDriverBtnLbl.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        topAddDriverBtnLbl.setForeground(new java.awt.Color(255, 255, 255));
        topAddDriverBtnLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        topAddDriverBtnLbl.setText("Add Driver");
        topAddDriverBtnLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        topAddDriverBtnLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                topAddDriverBtnLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                topAddDriverBtnLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                topAddDriverBtnLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout topAddDriverBtnPnlLayout = new javax.swing.GroupLayout(topAddDriverBtnPnl);
        topAddDriverBtnPnl.setLayout(topAddDriverBtnPnlLayout);
        topAddDriverBtnPnlLayout.setHorizontalGroup(
            topAddDriverBtnPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topAddDriverBtnLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );
        topAddDriverBtnPnlLayout.setVerticalGroup(
            topAddDriverBtnPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topAddDriverBtnLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        btnUpdateDriver.add(topAddDriverBtnPnl);

        topUpdateDriverBtnPnl.setBackground(new java.awt.Color(250, 199, 90));

        topUpdateDriverBtnLbl.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        topUpdateDriverBtnLbl.setForeground(new java.awt.Color(255, 255, 255));
        topUpdateDriverBtnLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        topUpdateDriverBtnLbl.setText("Update Driver Details");
        topUpdateDriverBtnLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        topUpdateDriverBtnLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                topUpdateDriverBtnLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                topUpdateDriverBtnLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                topUpdateDriverBtnLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout topUpdateDriverBtnPnlLayout = new javax.swing.GroupLayout(topUpdateDriverBtnPnl);
        topUpdateDriverBtnPnl.setLayout(topUpdateDriverBtnPnlLayout);
        topUpdateDriverBtnPnlLayout.setHorizontalGroup(
            topUpdateDriverBtnPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topUpdateDriverBtnLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );
        topUpdateDriverBtnPnlLayout.setVerticalGroup(
            topUpdateDriverBtnPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topUpdateDriverBtnLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        btnUpdateDriver.add(topUpdateDriverBtnPnl);

        driverDetailsCard.setBackground(new java.awt.Color(255, 255, 255));
        driverDetailsCard.setLayout(new java.awt.CardLayout());

        addDriverPane.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel3.setText("Add Driver Information");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Address");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Contact Number");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Date of Joining (DD-MM-YYYY)");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("License Number");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Date of Birth (DD-MM-YYYY)");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("License Exp. Date (DD-MM-YYYY)");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("License Issue Date (DD-MM-YYYY)");

        txtDriverAddress.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtDriverAddress.setMinimumSize(new java.awt.Dimension(5, 20));
        txtDriverAddress.setPreferredSize(new java.awt.Dimension(55, 10));
        txtDriverAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDriverAddressActionPerformed(evt);
            }
        });

        txtDriverContactNo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtDriverDob.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtDriverJoinDate.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtDriverLicenseNum.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtDriverLicenseIssue.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtDriverLicenseExp.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Driver Name");

        txtDriverName.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tickDriverMedicalIssue.setBackground(new java.awt.Color(255, 255, 255));
        tickDriverMedicalIssue.setText("Any Medical Issues?");
        tickDriverMedicalIssue.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tickDriverMedicalIssue.setBorderPainted(true);

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Assign Number of Bus");

        addDriverBtn.setBackground(new java.awt.Color(36, 202, 120));
        addDriverBtn.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        addDriverBtn.setForeground(new java.awt.Color(255, 255, 255));
        addDriverBtn.setText("ADD");
        addDriverBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addDriverBtnMouseClicked(evt);
            }
        });
        addDriverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDriverBtnActionPerformed(evt);
            }
        });

        dropDriverAssignBusNo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));

        javax.swing.GroupLayout addDriverPaneLayout = new javax.swing.GroupLayout(addDriverPane);
        addDriverPane.setLayout(addDriverPaneLayout);
        addDriverPaneLayout.setHorizontalGroup(
            addDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addDriverPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(addDriverPaneLayout.createSequentialGroup()
                .addContainerGap(133, Short.MAX_VALUE)
                .addGroup(addDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(addDriverPaneLayout.createSequentialGroup()
                        .addGroup(addDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDriverContactNo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                            .addComponent(txtDriverLicenseIssue, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                            .addComponent(txtDriverJoinDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dropDriverAssignBusNo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(addDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addDriverPaneLayout.createSequentialGroup()
                                .addGap(131, 131, 131)
                                .addGroup(addDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtDriverDob)
                                    .addGroup(addDriverPaneLayout.createSequentialGroup()
                                        .addGroup(addDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel10)
                                            .addComponent(txtDriverLicenseNum)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtDriverLicenseExp, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(addDriverPaneLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tickDriverMedicalIssue, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(txtDriverAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addDriverBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDriverName))
                .addContainerGap(160, Short.MAX_VALUE))
        );
        addDriverPaneLayout.setVerticalGroup(
            addDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addDriverPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDriverName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDriverAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDriverContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDriverDob, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDriverLicenseNum, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDriverJoinDate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDriverLicenseIssue, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDriverLicenseExp, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dropDriverAssignBusNo)
                    .addComponent(tickDriverMedicalIssue, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(addDriverBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(412, Short.MAX_VALUE))
        );

        driverDetailsCard.add(addDriverPane, "card2");

        updateDriverPane.setBackground(new java.awt.Color(255, 255, 255));

        jLabel52.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel52.setText("Update Driver Details");

        jLabel53.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel53.setText("Address");

        jLabel54.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel54.setText("Contact Number");

        jLabel55.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel55.setText("Driver ID");

        jLabel56.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel56.setText("Date of Joining (DD-MM-YYYY)");

        jLabel57.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel57.setText("License Number");

        jLabel58.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel58.setText("Date of Birth (DD-MM-YYYY)");

        jLabel59.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel59.setText("License Exp. Date (DD-MM-YYYY)");

        jLabel60.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel60.setText("License Issue Date (DD-MM-YYYY)");

        txt_u_driverId.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_u_driverName.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_u_address.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_u_contact.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel61.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel61.setText("Driver Name");

        txt_u_medicalIssue.setBackground(new java.awt.Color(255, 255, 255));
        txt_u_medicalIssue.setText("Any Medical Issues?");
        txt_u_medicalIssue.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txt_u_medicalIssue.setBorderPainted(true);

        jLabel62.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel62.setText("Assign Number of Bus");

        driverUpdateBtn.setBackground(new java.awt.Color(36, 202, 120));
        driverUpdateBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        driverUpdateBtn.setForeground(new java.awt.Color(255, 255, 255));
        driverUpdateBtn.setText("UPDATE");
        driverUpdateBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                driverUpdateBtnMouseClicked(evt);
            }
        });
        driverUpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                driverUpdateBtnActionPerformed(evt);
            }
        });

        txt_u_dob.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_u_doj.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_u_license.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_u_licenseIssue.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_u_licenseExp.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbl_driver.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Driver ID", "Driver Name", "null", "null", "null", "null", "null", "null", "null", "null", "null"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_driver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_driverMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_driver);
        if (tbl_driver.getColumnModel().getColumnCount() > 0) {
            tbl_driver.getColumnModel().getColumn(0).setResizable(false);
            tbl_driver.getColumnModel().getColumn(1).setResizable(false);
            tbl_driver.getColumnModel().getColumn(2).setResizable(false);
            tbl_driver.getColumnModel().getColumn(3).setResizable(false);
            tbl_driver.getColumnModel().getColumn(4).setResizable(false);
            tbl_driver.getColumnModel().getColumn(5).setResizable(false);
            tbl_driver.getColumnModel().getColumn(6).setResizable(false);
            tbl_driver.getColumnModel().getColumn(7).setResizable(false);
            tbl_driver.getColumnModel().getColumn(8).setResizable(false);
            tbl_driver.getColumnModel().getColumn(9).setResizable(false);
            tbl_driver.getColumnModel().getColumn(10).setResizable(false);
        }

        txt_u_assignBus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));

        updateDriverSearchBtn.setBackground(new java.awt.Color(36, 202, 120));
        updateDriverSearchBtn.setText("Search");
        updateDriverSearchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateDriverSearchBtnMouseClicked(evt);
            }
        });

        driverRemoveBtn.setBackground(new java.awt.Color(255, 102, 102));
        driverRemoveBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        driverRemoveBtn.setForeground(new java.awt.Color(255, 255, 255));
        driverRemoveBtn.setText("REMOVE");
        driverRemoveBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                driverRemoveBtnMouseClicked(evt);
            }
        });
        driverRemoveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                driverRemoveBtnActionPerformed(evt);
            }
        });

        jButton16.setText("Refresh");
        jButton16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton16MouseClicked(evt);
            }
        });
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updateDriverPaneLayout = new javax.swing.GroupLayout(updateDriverPane);
        updateDriverPane.setLayout(updateDriverPaneLayout);
        updateDriverPaneLayout.setHorizontalGroup(
            updateDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateDriverPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(updateDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateDriverPaneLayout.createSequentialGroup()
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(updateDriverPaneLayout.createSequentialGroup()
                        .addGap(0, 48, Short.MAX_VALUE)
                        .addGroup(updateDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(updateDriverPaneLayout.createSequentialGroup()
                                .addGroup(updateDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(updateDriverPaneLayout.createSequentialGroup()
                                        .addComponent(driverRemoveBtn)
                                        .addGap(18, 18, 18)
                                        .addComponent(driverUpdateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(updateDriverPaneLayout.createSequentialGroup()
                                        .addGroup(updateDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                                            .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                                            .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                                            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(28, 28, 28)
                                        .addGroup(updateDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_u_medicalIssue, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                            .addComponent(txt_u_licenseExp)
                                            .addComponent(txt_u_licenseIssue)
                                            .addComponent(txt_u_license)
                                            .addComponent(txt_u_doj)
                                            .addComponent(txt_u_dob)
                                            .addComponent(txt_u_contact)
                                            .addComponent(txt_u_address)
                                            .addComponent(txt_u_driverName)
                                            .addComponent(txt_u_assignBus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateDriverPaneLayout.createSequentialGroup()
                                                .addComponent(txt_u_driverId)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(updateDriverSearchBtn)))))
                                .addGap(42, 42, 42)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(25, Short.MAX_VALUE))))
        );
        updateDriverPaneLayout.setVerticalGroup(
            updateDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateDriverPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(updateDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateDriverPaneLayout.createSequentialGroup()
                        .addGroup(updateDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateDriverSearchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(updateDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_u_driverId, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(updateDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(updateDriverPaneLayout.createSequentialGroup()
                                .addComponent(txt_u_driverName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateDriverPaneLayout.createSequentialGroup()
                                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(updateDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_u_address, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(updateDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_u_contact, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(updateDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_u_dob, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(updateDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_u_doj, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(updateDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_u_license, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(updateDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_u_licenseIssue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(updateDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_u_licenseExp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(updateDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(txt_u_assignBus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_u_medicalIssue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton16)
                .addGap(3, 3, 3)
                .addGroup(updateDriverPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driverUpdateBtn)
                    .addComponent(driverRemoveBtn))
                .addGap(480, 480, 480))
        );

        driverDetailsCard.add(updateDriverPane, "card2");

        javax.swing.GroupLayout driverDetailsLayout = new javax.swing.GroupLayout(driverDetails);
        driverDetails.setLayout(driverDetailsLayout);
        driverDetailsLayout.setHorizontalGroup(
            driverDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(driverDetailsCard, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(btnUpdateDriver, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        driverDetailsLayout.setVerticalGroup(
            driverDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, driverDetailsLayout.createSequentialGroup()
                .addComponent(btnUpdateDriver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(driverDetailsCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        middlePanel.add(driverDetails, "card2");

        addBus.setBackground(new java.awt.Color(248, 248, 248));

        btnUpdateDriver1.setBackground(new java.awt.Color(248, 248, 248));

        topAddBusBtnPnl.setBackground(new java.awt.Color(60, 188, 225));

        topAddBusBtnLbl.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        topAddBusBtnLbl.setForeground(new java.awt.Color(255, 255, 255));
        topAddBusBtnLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        topAddBusBtnLbl.setText("Add Bus");
        topAddBusBtnLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        topAddBusBtnLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                topAddBusBtnLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                topAddBusBtnLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                topAddBusBtnLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout topAddBusBtnPnlLayout = new javax.swing.GroupLayout(topAddBusBtnPnl);
        topAddBusBtnPnl.setLayout(topAddBusBtnPnlLayout);
        topAddBusBtnPnlLayout.setHorizontalGroup(
            topAddBusBtnPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topAddBusBtnLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        topAddBusBtnPnlLayout.setVerticalGroup(
            topAddBusBtnPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topAddBusBtnLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        topUpdateBusBtnPnl.setBackground(new java.awt.Color(250, 199, 90));

        topUpdateBusBtnLbl.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        topUpdateBusBtnLbl.setForeground(new java.awt.Color(255, 255, 255));
        topUpdateBusBtnLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        topUpdateBusBtnLbl.setText("Update Bus Details");
        topUpdateBusBtnLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        topUpdateBusBtnLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                topUpdateBusBtnLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                topUpdateBusBtnLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                topUpdateBusBtnLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout topUpdateBusBtnPnlLayout = new javax.swing.GroupLayout(topUpdateBusBtnPnl);
        topUpdateBusBtnPnl.setLayout(topUpdateBusBtnPnlLayout);
        topUpdateBusBtnPnlLayout.setHorizontalGroup(
            topUpdateBusBtnPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topUpdateBusBtnLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        topUpdateBusBtnPnlLayout.setVerticalGroup(
            topUpdateBusBtnPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topUpdateBusBtnLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnUpdateDriver1Layout = new javax.swing.GroupLayout(btnUpdateDriver1);
        btnUpdateDriver1.setLayout(btnUpdateDriver1Layout);
        btnUpdateDriver1Layout.setHorizontalGroup(
            btnUpdateDriver1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnUpdateDriver1Layout.createSequentialGroup()
                .addComponent(topAddBusBtnPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(topUpdateBusBtnPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnUpdateDriver1Layout.setVerticalGroup(
            btnUpdateDriver1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topAddBusBtnPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(topUpdateBusBtnPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        busDetailsCard.setBackground(new java.awt.Color(255, 255, 255));
        busDetailsCard.setLayout(new java.awt.CardLayout());

        addBusDetails.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel17.setText("Add Bus Details");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText("Total Number of Seats");

        jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel21.setText("Bus Type");

        jLabel27.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel27.setText("Owner's Contact Number");

        jLabel28.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel28.setText("Contract Duration in Year (If Any)");

        jLabel29.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel29.setText("Owner Name");

        jLabel31.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel31.setText("Owner's Address");

        txtSeatNum.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtBusOwner.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtBusOwnerContact.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtBusOwnerAddress.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtBusOwnerAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBusOwnerAddressActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel32.setText("Bus License Number");

        txtBusLicense.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtBusFitnessCert.setBackground(new java.awt.Color(255, 255, 255));
        txtBusFitnessCert.setText("Any fitness certificate?");
        txtBusFitnessCert.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtBusFitnessCert.setBorderPainted(true);

        txtBusType.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtBusType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC BUS", "NON-AC BUS" }));
        txtBusType.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        busDetailsAddBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        busDetailsAddBtn.setText("ADD");
        busDetailsAddBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                busDetailsAddBtnMouseClicked(evt);
            }
        });

        txtBusContractDuration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));

        javax.swing.GroupLayout addBusDetailsLayout = new javax.swing.GroupLayout(addBusDetails);
        addBusDetails.setLayout(addBusDetailsLayout);
        addBusDetailsLayout.setHorizontalGroup(
            addBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addBusDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(addBusDetailsLayout.createSequentialGroup()
                .addContainerGap(146, Short.MAX_VALUE)
                .addGroup(addBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(addBusDetailsLayout.createSequentialGroup()
                        .addGroup(addBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBusLicense, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                            .addComponent(txtSeatNum, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                            .addComponent(txtBusOwnerContact, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(addBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addBusDetailsLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtBusFitnessCert, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addBusDetailsLayout.createSequentialGroup()
                                .addGap(131, 131, 131)
                                .addGroup(addBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel21)
                                    .addComponent(txtBusType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                                    .addComponent(txtBusOwner, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(busDetailsAddBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBusContractDuration, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBusOwnerAddress))
                .addContainerGap(147, Short.MAX_VALUE))
        );
        addBusDetailsLayout.setVerticalGroup(
            addBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addBusDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(addBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusLicense, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusType, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSeatNum, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusOwner, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addBusDetailsLayout.createSequentialGroup()
                        .addComponent(txtBusOwnerContact, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBusOwnerAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtBusFitnessCert, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(busDetailsAddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtBusContractDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(805, Short.MAX_VALUE))
        );

        busDetailsCard.add(addBusDetails, "card2");

        updateBusDetails.setBackground(new java.awt.Color(255, 255, 255));

        jLabel37.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel37.setText("Update Bus Details");

        jLabel42.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel42.setText("Total Number of Seats");

        jLabel43.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel43.setText("Bus Type");

        jLabel46.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel46.setText("Owner's Contact Number");

        jLabel47.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel47.setText("Owner Address");

        jLabel48.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel48.setText("Owner Name");

        jLabel49.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel49.setText("Contract Duration in Year (If Any)");

        jLabel50.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel50.setText("Bus Serial Number");

        busUpdateBtn.setBackground(new java.awt.Color(36, 202, 120));
        busUpdateBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        busUpdateBtn.setForeground(new java.awt.Color(255, 255, 255));
        busUpdateBtn.setText("UPDATE");
        busUpdateBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                busUpdateBtnMouseClicked(evt);
            }
        });
        busUpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busUpdateBtnActionPerformed(evt);
            }
        });

        txtUBusSerial.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtUBusType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC BUS", "NON-AC BUS" }));
        txtUBusType.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtUBusSeat.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtUBusName.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtUBusContact.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtUBusAddress.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbl_bus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Serial", "Bus License No.", "Owner Name", "null", "null", "null", "null", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_bus.setGridColor(new java.awt.Color(235, 235, 235));
        tbl_bus.getTableHeader().setReorderingAllowed(false);
        tbl_bus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_busMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_bus);
        if (tbl_bus.getColumnModel().getColumnCount() > 0) {
            tbl_bus.getColumnModel().getColumn(0).setResizable(false);
            tbl_bus.getColumnModel().getColumn(1).setResizable(false);
            tbl_bus.getColumnModel().getColumn(2).setResizable(false);
            tbl_bus.getColumnModel().getColumn(3).setResizable(false);
            tbl_bus.getColumnModel().getColumn(4).setResizable(false);
            tbl_bus.getColumnModel().getColumn(5).setResizable(false);
            tbl_bus.getColumnModel().getColumn(6).setResizable(false);
            tbl_bus.getColumnModel().getColumn(7).setResizable(false);
            tbl_bus.getColumnModel().getColumn(8).setResizable(false);
        }

        txtUBusDuration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        txtUBusDuration.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        busRefreshBtn.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        busRefreshBtn.setText("Refresh");
        busRefreshBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                busRefreshBtnMouseClicked(evt);
            }
        });

        removeBusBtn.setBackground(new java.awt.Color(255, 102, 102));
        removeBusBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        removeBusBtn.setForeground(new java.awt.Color(255, 255, 255));
        removeBusBtn.setText("REMOVE");
        removeBusBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeBusBtnMouseClicked(evt);
            }
        });
        removeBusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBusBtnActionPerformed(evt);
            }
        });

        updateBusSearchBtn.setBackground(new java.awt.Color(102, 204, 255));
        updateBusSearchBtn.setText("Search");
        updateBusSearchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateBusSearchBtnMouseClicked(evt);
            }
        });

        txtUBusFitnessCert.setBackground(new java.awt.Color(255, 255, 255));
        txtUBusFitnessCert.setText("Any fitness certificate?");
        txtUBusFitnessCert.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtUBusFitnessCert.setBorderPainted(true);

        jLabel51.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel51.setText("Bus License Number");

        txtUBusLicense.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout updateBusDetailsLayout = new javax.swing.GroupLayout(updateBusDetails);
        updateBusDetails.setLayout(updateBusDetailsLayout);
        updateBusDetailsLayout.setHorizontalGroup(
            updateBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateBusDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(updateBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, updateBusDetailsLayout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 411, Short.MAX_VALUE))
                    .addGroup(updateBusDetailsLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(updateBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46)
                            .addComponent(jLabel47)
                            .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(updateBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(updateBusDetailsLayout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(updateBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, updateBusDetailsLayout.createSequentialGroup()
                                        .addComponent(removeBusBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(busUpdateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtUBusFitnessCert, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtUBusAddress, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUBusContact, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUBusName, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUBusDuration, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(updateBusDetailsLayout.createSequentialGroup()
                                        .addComponent(txtUBusSerial, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(updateBusSearchBtn))
                                    .addComponent(txtUBusType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtUBusSeat)))
                            .addGroup(updateBusDetailsLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtUBusLicense, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(54, 54, 54)))
                .addGroup(updateBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(busRefreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        updateBusDetailsLayout.setVerticalGroup(
            updateBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateBusDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addGroup(updateBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateBusDetailsLayout.createSequentialGroup()
                        .addGroup(updateBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel50)
                            .addComponent(txtUBusSerial, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateBusSearchBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(updateBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51)
                            .addComponent(txtUBusLicense, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(updateBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUBusType, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(updateBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUBusSeat, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(updateBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUBusName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(updateBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUBusContact, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(updateBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUBusAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(updateBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(updateBusDetailsLayout.createSequentialGroup()
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59))
                            .addGroup(updateBusDetailsLayout.createSequentialGroup()
                                .addComponent(txtUBusDuration)
                                .addGap(14, 14, 14)
                                .addComponent(txtUBusFitnessCert, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(updateBusDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(busUpdateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(removeBusBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(100, 100, 100))))
                    .addGroup(updateBusDetailsLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(busRefreshBtn)
                .addGap(701, 701, 701))
        );

        busDetailsCard.add(updateBusDetails, "card2");

        javax.swing.GroupLayout addBusLayout = new javax.swing.GroupLayout(addBus);
        addBus.setLayout(addBusLayout);
        addBusLayout.setHorizontalGroup(
            addBusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(busDetailsCard, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(btnUpdateDriver1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addBusLayout.setVerticalGroup(
            addBusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addBusLayout.createSequentialGroup()
                .addComponent(btnUpdateDriver1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(busDetailsCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        middlePanel.add(addBus, "card2");

        busScheduling.setBackground(new java.awt.Color(248, 248, 248));

        btnUpdateDriver2.setBackground(new java.awt.Color(248, 248, 248));
        btnUpdateDriver2.setLayout(new java.awt.GridLayout(1, 0));

        addBusSchPnl.setBackground(new java.awt.Color(60, 188, 225));

        addBusSchLbl.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        addBusSchLbl.setForeground(new java.awt.Color(255, 255, 255));
        addBusSchLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addBusSchLbl.setText("Add Bus Schedule");
        addBusSchLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addBusSchLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addBusSchLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addBusSchLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addBusSchLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout addBusSchPnlLayout = new javax.swing.GroupLayout(addBusSchPnl);
        addBusSchPnl.setLayout(addBusSchPnlLayout);
        addBusSchPnlLayout.setHorizontalGroup(
            addBusSchPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addBusSchLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addBusSchPnlLayout.setVerticalGroup(
            addBusSchPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addBusSchLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        btnUpdateDriver2.add(addBusSchPnl);

        addBusRoutePnl.setBackground(new java.awt.Color(250, 199, 90));

        addBusRouteLbl.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        addBusRouteLbl.setForeground(new java.awt.Color(255, 255, 255));
        addBusRouteLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addBusRouteLbl.setText("Add Bus Route");
        addBusRouteLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addBusRouteLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addBusRouteLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addBusRouteLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addBusRouteLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout addBusRoutePnlLayout = new javax.swing.GroupLayout(addBusRoutePnl);
        addBusRoutePnl.setLayout(addBusRoutePnlLayout);
        addBusRoutePnlLayout.setHorizontalGroup(
            addBusRoutePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addBusRouteLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addBusRoutePnlLayout.setVerticalGroup(
            addBusRoutePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addBusRouteLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        btnUpdateDriver2.add(addBusRoutePnl);

        busScheduleCard.setBackground(new java.awt.Color(255, 255, 255));
        busScheduleCard.setLayout(new java.awt.CardLayout());

        addSchedulePane.setBackground(new java.awt.Color(255, 255, 255));

        jLabel40.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel40.setText("Bus Schedule");

        jTable1.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Day", "Route ID", "Bus Number", "Driver ID", "Time (HH)", "Time (MM)", "AM/PM"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
        }

        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText("Select Bus Number");

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setText("Arrival Time (HH:MM:AM)");

        jLabel38.setBackground(new java.awt.Color(255, 255, 255));
        jLabel38.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel38.setText("Select Route");

        jLabel39.setBackground(new java.awt.Color(255, 255, 255));
        jLabel39.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel39.setText("Day");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jSpinner3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jSpinner4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jButton3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton3.setText("ADD");

        jComboBox2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jComboBox4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sun", "Mon", "Tue", "Thu", "Fri", "Sat" }));
        jComboBox5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jButton9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton9.setText("UPDATE");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton10.setText("REMOVE");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Select Driver");

        javax.swing.GroupLayout addSchedulePaneLayout = new javax.swing.GroupLayout(addSchedulePane);
        addSchedulePane.setLayout(addSchedulePaneLayout);
        addSchedulePaneLayout.setHorizontalGroup(
            addSchedulePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addSchedulePaneLayout.createSequentialGroup()
                .addGroup(addSchedulePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, addSchedulePaneLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(addSchedulePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addSchedulePaneLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(addSchedulePaneLayout.createSequentialGroup()
                                .addGroup(addSchedulePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(103, 103, 103)
                                .addGroup(addSchedulePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(addSchedulePaneLayout.createSequentialGroup()
                                        .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSpinner3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox4, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox5, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox11, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, addSchedulePaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel40)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(addSchedulePaneLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(135, 135, 135))
        );
        addSchedulePaneLayout.setVerticalGroup(
            addSchedulePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addSchedulePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(addSchedulePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(addSchedulePaneLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addGroup(addSchedulePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(addSchedulePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(addSchedulePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(addSchedulePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(addSchedulePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        busScheduleCard.add(addSchedulePane, "card2");

        addRoutePane.setBackground(new java.awt.Color(255, 255, 255));

        jLabel41.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel41.setText("Add Route");

        tbl_route.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        tbl_route.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Route ID", "Route Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_route.getTableHeader().setReorderingAllowed(false);
        tbl_route.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_routeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_route);
        if (tbl_route.getColumnModel().getColumnCount() > 0) {
            tbl_route.getColumnModel().getColumn(0).setResizable(false);
            tbl_route.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        jLabel36.setBackground(new java.awt.Color(255, 255, 255));
        jLabel36.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel36.setText("Route Name");

        addRouteBtn.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        addRouteBtn.setText("ADD ROUTE");
        addRouteBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addRouteBtnMouseClicked(evt);
            }
        });

        txtRouteName.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        removeRouteBtn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        removeRouteBtn.setText("REMOVE ROUTE");

        routeRefreshBtn.setText("Refresh");
        routeRefreshBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                routeRefreshBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout addRoutePaneLayout = new javax.swing.GroupLayout(addRoutePane);
        addRoutePane.setLayout(addRoutePaneLayout);
        addRoutePaneLayout.setHorizontalGroup(
            addRoutePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addRoutePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(addRoutePaneLayout.createSequentialGroup()
                .addGroup(addRoutePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(addRoutePaneLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(routeRefreshBtn))
                    .addGroup(addRoutePaneLayout.createSequentialGroup()
                        .addContainerGap(158, Short.MAX_VALUE)
                        .addGroup(addRoutePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(addRoutePaneLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(addRoutePaneLayout.createSequentialGroup()
                                .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addGroup(addRoutePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addRoutePaneLayout.createSequentialGroup()
                                        .addComponent(removeRouteBtn)
                                        .addGap(18, 18, 18)
                                        .addComponent(addRouteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtRouteName, javax.swing.GroupLayout.Alignment.TRAILING))))))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        addRoutePaneLayout.setVerticalGroup(
            addRoutePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addRoutePaneLayout.createSequentialGroup()
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(addRoutePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txtRouteName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(addRoutePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addRouteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeRouteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(routeRefreshBtn)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        busScheduleCard.add(addRoutePane, "card2");

        javax.swing.GroupLayout busSchedulingLayout = new javax.swing.GroupLayout(busScheduling);
        busScheduling.setLayout(busSchedulingLayout);
        busSchedulingLayout.setHorizontalGroup(
            busSchedulingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(busScheduleCard, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(btnUpdateDriver2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        busSchedulingLayout.setVerticalGroup(
            busSchedulingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, busSchedulingLayout.createSequentialGroup()
                .addComponent(btnUpdateDriver2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(busScheduleCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        middlePanel.add(busScheduling, "card2");

        javax.swing.GroupLayout wholePanelLayout = new javax.swing.GroupLayout(wholePanel);
        wholePanel.setLayout(wholePanelLayout);
        wholePanelLayout.setHorizontalGroup(
            wholePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wholePanelLayout.createSequentialGroup()
                .addComponent(sideBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(wholePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(middlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        wholePanelLayout.setVerticalGroup(
            wholePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(wholePanelLayout.createSequentialGroup()
                .addComponent(topBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(middlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addComponent(sideBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wholePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wholePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtDriverAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDriverAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDriverAddressActionPerformed

    private void addDriverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDriverBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addDriverBtnActionPerformed

    private void driverUpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_driverUpdateBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_driverUpdateBtnActionPerformed

    private void busUpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busUpdateBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_busUpdateBtnActionPerformed

    private void txtBusOwnerAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusOwnerAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusOwnerAddressActionPerformed

    private void sideDashboardPnlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sideDashboardPnlMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_sideDashboardPnlMouseClicked

    private void sideBusPnlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sideBusPnlMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_sideBusPnlMouseClicked

    private void sidePaymentLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sidePaymentLblMouseClicked
        // TODO add your handling code here:
        middlePanel.removeAll();
        middlePanel.repaint();
        middlePanel.revalidate();

        middlePanel.add(payment);
        middlePanel.repaint();
        middlePanel.revalidate();
    }//GEN-LAST:event_sidePaymentLblMouseClicked

    private void sideScheduleLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sideScheduleLblMouseClicked
        // TODO add your handling code here:
        middlePanel.removeAll();
        middlePanel.repaint();
        middlePanel.revalidate();

        middlePanel.add(busScheduling);
        middlePanel.repaint();
        middlePanel.revalidate();
    }//GEN-LAST:event_sideScheduleLblMouseClicked

    private void sideDashboardLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sideDashboardLblMouseClicked
        // TODO add your handling code here:
        middlePanel.removeAll();
        middlePanel.repaint();
        middlePanel.revalidate();

        middlePanel.add(dashBoard);
        middlePanel.repaint();
        middlePanel.revalidate();

    }//GEN-LAST:event_sideDashboardLblMouseClicked

    private void sideDriverLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sideDriverLblMouseClicked
        // TODO add your handling code here:
        middlePanel.removeAll();
        middlePanel.repaint();
        middlePanel.revalidate();

        middlePanel.add(driverDetails);
        middlePanel.repaint();
        middlePanel.revalidate();
    }//GEN-LAST:event_sideDriverLblMouseClicked

    private void sideBusLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sideBusLblMouseClicked
        // TODO add your handling code here:
        middlePanel.removeAll();
        middlePanel.repaint();
        middlePanel.revalidate();

        middlePanel.add(addBus);
        middlePanel.repaint();
        middlePanel.revalidate();
    }//GEN-LAST:event_sideBusLblMouseClicked

    private void sideInvoiceLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sideInvoiceLblMouseClicked
        // TODO add your handling code here:
        middlePanel.removeAll();
        middlePanel.repaint();
        middlePanel.revalidate();

        middlePanel.add(invoice);
        middlePanel.repaint();
        middlePanel.revalidate();
    }//GEN-LAST:event_sideInvoiceLblMouseClicked

    private void sideDashboardLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sideDashboardLblMouseEntered
        // TODO add your handling code here:
        sideDashboardPnl.setBackground(Color.decode("#5ACC94"));
    }//GEN-LAST:event_sideDashboardLblMouseEntered

    private void sideDashboardLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sideDashboardLblMouseExited
        // TODO add your handling code here:
        sideDashboardPnl.setBackground(Color.decode("#63E1A3"));
    }//GEN-LAST:event_sideDashboardLblMouseExited

    private void sideDriverLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sideDriverLblMouseEntered
        // TODO add your handling code here:
        sideDriverPnl.setBackground(Color.decode("#5ACC94"));
    }//GEN-LAST:event_sideDriverLblMouseEntered

    private void sideDriverLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sideDriverLblMouseExited
        // TODO add your handling code here:
        sideDriverPnl.setBackground(Color.decode("#63E1A3"));
    }//GEN-LAST:event_sideDriverLblMouseExited

    private void sideBusLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sideBusLblMouseEntered
        // TODO add your handling code here:
        sideBusPnl.setBackground(Color.decode("#5ACC94"));
    }//GEN-LAST:event_sideBusLblMouseEntered

    private void sideBusLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sideBusLblMouseExited
        // TODO add your handling code here:
        sideBusPnl.setBackground(Color.decode("#63E1A3"));
    }//GEN-LAST:event_sideBusLblMouseExited

    private void sideScheduleLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sideScheduleLblMouseEntered
        // TODO add your handling code here:
        sideSchedulePnl.setBackground(Color.decode("#5ACC94"));
    }//GEN-LAST:event_sideScheduleLblMouseEntered

    private void sideScheduleLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sideScheduleLblMouseExited
        // TODO add your handling code here:
        sideSchedulePnl.setBackground(Color.decode("#63E1A3"));
    }//GEN-LAST:event_sideScheduleLblMouseExited

    private void sidePaymentLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sidePaymentLblMouseEntered
        // TODO add your handling code here:
        sidePaymentPnl.setBackground(Color.decode("#5ACC94"));
    }//GEN-LAST:event_sidePaymentLblMouseEntered

    private void sidePaymentLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sidePaymentLblMouseExited
        // TODO add your handling code here:
        sidePaymentPnl.setBackground(Color.decode("#63E1A3"));
    }//GEN-LAST:event_sidePaymentLblMouseExited

    private void sideInvoiceLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sideInvoiceLblMouseEntered
        // TODO add your handling code here:
        sideInvoicePnl.setBackground(Color.decode("#5ACC94"));
    }//GEN-LAST:event_sideInvoiceLblMouseEntered

    private void sideInvoiceLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sideInvoiceLblMouseExited
        // TODO add your handling code here:
        sideInvoicePnl.setBackground(Color.decode("#63E1A3"));
    }//GEN-LAST:event_sideInvoiceLblMouseExited

    private void sideLogoutLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sideLogoutLblMouseEntered
        // TODO add your handling code here:
        sideLogoutPnl.setBackground(Color.decode("#5ACC94"));
    }//GEN-LAST:event_sideLogoutLblMouseEntered

    private void sideLogoutLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sideLogoutLblMouseExited
        // TODO add your handling code here:
        sideLogoutPnl.setBackground(Color.decode("#63E1A3"));
    }//GEN-LAST:event_sideLogoutLblMouseExited

    private void topAddDriverBtnLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topAddDriverBtnLblMouseEntered
        // TODO add your handling code here:
        topAddDriverBtnPnl.setBackground(Color.decode("#38B4D6"));
    }//GEN-LAST:event_topAddDriverBtnLblMouseEntered

    private void topAddDriverBtnLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topAddDriverBtnLblMouseExited
        // TODO add your handling code here:
        topAddDriverBtnPnl.setBackground(Color.decode("#3CBCE1"));
    }//GEN-LAST:event_topAddDriverBtnLblMouseExited

    private void topUpdateDriverBtnLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topUpdateDriverBtnLblMouseEntered
        // TODO add your handling code here:
        topUpdateDriverBtnPnl.setBackground(Color.decode("#F0BF58"));
    }//GEN-LAST:event_topUpdateDriverBtnLblMouseEntered

    private void topUpdateDriverBtnLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topUpdateDriverBtnLblMouseExited
        // TODO add your handling code here:
        topUpdateDriverBtnPnl.setBackground(Color.decode("#FAC75A"));
    }//GEN-LAST:event_topUpdateDriverBtnLblMouseExited

    private void topAddDriverBtnLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topAddDriverBtnLblMouseClicked
        // TODO add your handling code here:
        driverDetailsCard.removeAll();
        driverDetailsCard.repaint();
        driverDetailsCard.revalidate();

        driverDetailsCard.add(addDriverPane);
        driverDetailsCard.repaint();
        driverDetailsCard.revalidate();
    }//GEN-LAST:event_topAddDriverBtnLblMouseClicked

    private void topUpdateDriverBtnLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topUpdateDriverBtnLblMouseClicked
        // TODO add your handling code here:
        driverDetailsCard.removeAll();
        driverDetailsCard.repaint();
        driverDetailsCard.revalidate();

        driverDetailsCard.add(updateDriverPane);
        driverDetailsCard.repaint();
        driverDetailsCard.revalidate();
        driverTableColumnHide();
    }//GEN-LAST:event_topUpdateDriverBtnLblMouseClicked

    private void topAddBusBtnLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topAddBusBtnLblMouseClicked
        // TODO add your handling code here:
        busDetailsCard.removeAll();
        busDetailsCard.repaint();
        busDetailsCard.revalidate();

        busDetailsCard.add(addBusDetails);
        busDetailsCard.repaint();
        busDetailsCard.revalidate();
    }//GEN-LAST:event_topAddBusBtnLblMouseClicked

    private void topUpdateBusBtnLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topUpdateBusBtnLblMouseClicked
        // TODO add your handling code here:
        busDetailsCard.removeAll();
        busDetailsCard.repaint();
        busDetailsCard.revalidate();

        busDetailsCard.add(updateBusDetails);
        busDetailsCard.repaint();
        busDetailsCard.revalidate();

        busTableColumnHide();
    }//GEN-LAST:event_topUpdateBusBtnLblMouseClicked

    private void topAddBusBtnLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topAddBusBtnLblMouseEntered
        // TODO add your handling code here:
        topAddBusBtnPnl.setBackground(Color.decode("#38B4D6"));
    }//GEN-LAST:event_topAddBusBtnLblMouseEntered

    private void topAddBusBtnLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topAddBusBtnLblMouseExited
        // TODO add your handling code here:
        topAddBusBtnPnl.setBackground(Color.decode("#3CBCE1"));
    }//GEN-LAST:event_topAddBusBtnLblMouseExited

    private void topUpdateBusBtnLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topUpdateBusBtnLblMouseEntered
        // TODO add your handling code here:
        topUpdateBusBtnPnl.setBackground(Color.decode("#F0BF58"));
    }//GEN-LAST:event_topUpdateBusBtnLblMouseEntered

    private void topUpdateBusBtnLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topUpdateBusBtnLblMouseExited
        // TODO add your handling code here:
        topUpdateBusBtnPnl.setBackground(Color.decode("#FAC75A"));
    }//GEN-LAST:event_topUpdateBusBtnLblMouseExited

    private void addBusSchLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBusSchLblMouseClicked
        // TODO add your handling code here:
        busScheduleCard.removeAll();
        busScheduleCard.repaint();
        busScheduleCard.revalidate();

        busScheduleCard.add(addSchedulePane);
        busScheduleCard.repaint();
        busScheduleCard.revalidate();
    }//GEN-LAST:event_addBusSchLblMouseClicked

    private void addBusRouteLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBusRouteLblMouseClicked
        // TODO add your handling code here:
        busScheduleCard.removeAll();
        busScheduleCard.repaint();
        busScheduleCard.revalidate();

        busScheduleCard.add(addRoutePane);
        busScheduleCard.repaint();
        busScheduleCard.revalidate();
    }//GEN-LAST:event_addBusRouteLblMouseClicked

    private void addBusSchLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBusSchLblMouseEntered
        // TODO add your handling code here:
        addBusSchPnl.setBackground(Color.decode("#38B4D6"));
    }//GEN-LAST:event_addBusSchLblMouseEntered

    private void addBusSchLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBusSchLblMouseExited
        // TODO add your handling code here:
        addBusSchPnl.setBackground(Color.decode("#3CBCE1"));
    }//GEN-LAST:event_addBusSchLblMouseExited

    private void addBusRouteLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBusRouteLblMouseEntered
        // TODO add your handling code here:
        addBusRoutePnl.setBackground(Color.decode("#F0BF58"));
    }//GEN-LAST:event_addBusRouteLblMouseEntered

    private void addBusRouteLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBusRouteLblMouseExited
        // TODO add your handling code here:
        addBusRoutePnl.setBackground(Color.decode("#FAC75A"));
    }//GEN-LAST:event_addBusRouteLblMouseExited

    private void topPayDriverLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topPayDriverLblMouseClicked
        // TODO add your handling code here:
        paymentCard.removeAll();
        paymentCard.repaint();
        paymentCard.revalidate();

        paymentCard.add(payDriver);
        paymentCard.repaint();
        paymentCard.revalidate();
    }//GEN-LAST:event_topPayDriverLblMouseClicked

    private void topPayBusLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topPayBusLblMouseClicked
        // TODO add your handling code here:
        paymentCard.removeAll();
        paymentCard.repaint();
        paymentCard.revalidate();

        paymentCard.add(payBus);
        paymentCard.repaint();
        paymentCard.revalidate();
    }//GEN-LAST:event_topPayBusLblMouseClicked

    private void topGenerateDriverInvceLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topGenerateDriverInvceLblMouseClicked
        // TODO add your handling code here:
        invoiceCard.removeAll();
        invoiceCard.repaint();
        invoiceCard.revalidate();

        invoiceCard.add(driverInvoice);
        invoiceCard.repaint();
        invoiceCard.revalidate();
    }//GEN-LAST:event_topGenerateDriverInvceLblMouseClicked

    private void topGenerateBusInvceLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topGenerateBusInvceLblMouseClicked
        // TODO add your handling code here:
        invoiceCard.removeAll();
        invoiceCard.repaint();
        invoiceCard.revalidate();

        invoiceCard.add(busInvoice);
        invoiceCard.repaint();
        invoiceCard.revalidate();
    }//GEN-LAST:event_topGenerateBusInvceLblMouseClicked

    private void topGenerateDriverInvceLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topGenerateDriverInvceLblMouseEntered
        // TODO add your handling code here:
        topGenerateDriverInvcePnl.setBackground(Color.decode("#38B4D6"));
    }//GEN-LAST:event_topGenerateDriverInvceLblMouseEntered

    private void topGenerateDriverInvceLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topGenerateDriverInvceLblMouseExited
        // TODO add your handling code here:
        topGenerateDriverInvcePnl.setBackground(Color.decode("#3CBCE1"));
    }//GEN-LAST:event_topGenerateDriverInvceLblMouseExited

    private void topGenerateBusInvceLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topGenerateBusInvceLblMouseEntered
        // TODO add your handling code here:
        topGenerateBusInvcePnl.setBackground(Color.decode("#F0BF58"));
    }//GEN-LAST:event_topGenerateBusInvceLblMouseEntered

    private void topGenerateBusInvceLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topGenerateBusInvceLblMouseExited
        // TODO add your handling code here:
        topGenerateBusInvcePnl.setBackground(Color.decode("#FAC75A"));
    }//GEN-LAST:event_topGenerateBusInvceLblMouseExited

    private void topPayDriverLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topPayDriverLblMouseEntered
        // TODO add your handling code here:
        topPayDriverPnl.setBackground(Color.decode("#38B4D6"));
    }//GEN-LAST:event_topPayDriverLblMouseEntered

    private void topPayDriverLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topPayDriverLblMouseExited
        // TODO add your handling code here:
        topPayDriverPnl.setBackground(Color.decode("#3CBCE1"));
    }//GEN-LAST:event_topPayDriverLblMouseExited

    private void topPayBusLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topPayBusLblMouseEntered
        // TODO add your handling code here:
        topPayBusPnl.setBackground(Color.decode("#FAC75A"));
    }//GEN-LAST:event_topPayBusLblMouseEntered

    private void topPayBusLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topPayBusLblMouseExited
        // TODO add your handling code here:
        topPayBusPnl.setBackground(Color.decode("#F0BF58"));
    }//GEN-LAST:event_topPayBusLblMouseExited

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void sideLogoutLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sideLogoutLblMouseClicked
        // TODO add your handling code here:
        new Login().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_sideLogoutLblMouseClicked

    private void driverRemoveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_driverRemoveBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_driverRemoveBtnActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseClicked
        // TODO add your handling code here:
        try {
            ServerConnect con = new ServerConnect();
            ResultSet rs = con.s.executeQuery("select * from driver");

            driver_tbl_reset();
            while (rs.next()) {
                String driver_id = String.valueOf(rs.getString("driver_id"));
                String driver_name = String.valueOf(rs.getString("driver_name"));
                String address = String.valueOf(rs.getString("address"));
                String contact_num = String.valueOf(rs.getString("contact_num"));
                String dob = String.valueOf(rs.getString("dob"));
                String date_of_joining = String.valueOf(rs.getString("date_of_joining"));
                String license_num = String.valueOf(rs.getString("license_num"));
                String license_issue = String.valueOf(rs.getString("license_issue"));
                String license_exp = String.valueOf(rs.getString("license_exp"));
                String assign_bus = String.valueOf(rs.getString("assign_bus"));
                String medical_issue = String.valueOf(rs.getString("medical_issue"));

                Object[] obj = {driver_id, driver_name, address, contact_num, dob, date_of_joining, license_num, license_issue, license_exp, assign_bus, medical_issue};
                model = (DefaultTableModel) tbl_driver.getModel();
                model.addRow(obj);

            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton16MouseClicked

    private void tbl_driverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_driverMouseClicked
        // TODO add your handling code here:
        int rowNo = tbl_driver.getSelectedRow();
        TableModel model = tbl_driver.getModel();

        txt_u_driverId.setText(model.getValueAt(rowNo, 0).toString());
        txt_u_driverName.setText(model.getValueAt(rowNo, 1).toString());

        txt_u_address.setText(tbl_driver.getModel().getValueAt(rowNo, 2).toString());
        txt_u_contact.setText(tbl_driver.getModel().getValueAt(rowNo, 3).toString());
        txt_u_dob.setText(tbl_driver.getModel().getValueAt(rowNo, 4).toString());
        txt_u_doj.setText(tbl_driver.getModel().getValueAt(rowNo, 5).toString());
        txt_u_license.setText(tbl_driver.getModel().getValueAt(rowNo, 6).toString());
        txt_u_licenseIssue.setText(tbl_driver.getModel().getValueAt(rowNo, 7).toString());
        txt_u_licenseExp.setText(tbl_driver.getModel().getValueAt(rowNo, 8).toString());
        txt_u_assignBus.setSelectedItem(tbl_driver.getModel().getValueAt(rowNo, 9).toString());
        txt_u_medicalIssue.setSelected(Boolean.parseBoolean(tbl_driver.getModel().getValueAt(rowNo, 10).toString()));


    }//GEN-LAST:event_tbl_driverMouseClicked

    private void updateDriverSearchBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateDriverSearchBtnMouseClicked
        // TODO add your handling code here:
        try {
            String searchText = txt_u_driverId.getText();
            //search_driverTable(searchText);

            TableModel model = tbl_driver.getModel();

            txt_u_driverName.setText(model.getValueAt(Integer.parseInt(txt_u_driverId.getText()) - 1, 1).toString());
            txt_u_address.setText(tbl_driver.getModel().getValueAt(Integer.parseInt(txt_u_driverId.getText()) - 1, 2).toString());
            txt_u_contact.setText(tbl_driver.getModel().getValueAt(Integer.parseInt(txt_u_driverId.getText()) - 1, 3).toString());
            txt_u_dob.setText(tbl_driver.getModel().getValueAt(Integer.parseInt(txt_u_driverId.getText()) - 1, 4).toString());
            txt_u_doj.setText(tbl_driver.getModel().getValueAt(Integer.parseInt(txt_u_driverId.getText()) - 1, 5).toString());
            txt_u_license.setText(tbl_driver.getModel().getValueAt(Integer.parseInt(txt_u_driverId.getText()) - 1, 6).toString());
            txt_u_licenseIssue.setText(tbl_driver.getModel().getValueAt(Integer.parseInt(txt_u_driverId.getText()) - 1, 7).toString());
            txt_u_licenseExp.setText(tbl_driver.getModel().getValueAt(Integer.parseInt(txt_u_driverId.getText()) - 1, 8).toString());
            txt_u_assignBus.setSelectedItem(tbl_driver.getModel().getValueAt(Integer.parseInt(txt_u_driverId.getText()) - 1, 9).toString());
            txt_u_medicalIssue.setSelected(Boolean.parseBoolean(tbl_driver.getModel().getValueAt(Integer.parseInt(txt_u_driverId.getText()) - 1, 10).toString()));
        } catch (Exception e) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Sorry! Not Found.");
        }

    }//GEN-LAST:event_updateDriverSearchBtnMouseClicked

    private void addDriverBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addDriverBtnMouseClicked
        // TODO add your handling code here:
        String name = txtDriverName.getText();
        String address = txtDriverAddress.getText();
        String contact = txtDriverContactNo.getText();
        String dob = txtDriverDob.getText();
        String doj = txtDriverJoinDate.getText();
        String license = txtDriverLicenseNum.getText();
        String licenseIssue = txtDriverLicenseIssue.getText();
        String licenseExp = txtDriverLicenseExp.getText();
        int assignBus = dropDriverAssignBusNo.getSelectedIndex() + 1;
        String checkMedical = String.valueOf(tickDriverMedicalIssue.isSelected());

        addDriverFunc(name, address, contact, dob, doj, license, licenseIssue, licenseExp, assignBus, checkMedical);

        txtDriverName.setText("");
        txtDriverAddress.setText("");
        txtDriverContactNo.setText("");
        txtDriverDob.setText("");
        txtDriverJoinDate.setText("");
        txtDriverLicenseNum.setText("");
        txtDriverLicenseIssue.setText("");
        txtDriverLicenseExp.setText("");
        dropDriverAssignBusNo.setSelectedIndex(0);
        tickDriverMedicalIssue.setSelected(false);
    }//GEN-LAST:event_addDriverBtnMouseClicked

    private void driverUpdateBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_driverUpdateBtnMouseClicked
        // TODO add your handling code here:
        int id = Integer.parseInt(txt_u_driverId.getText());
        String name = txt_u_driverName.getText();
        String address = txt_u_address.getText();
        String contact = txt_u_contact.getText();
        String dob = txt_u_dob.getText();
        String doj = txt_u_doj.getText();
        String license = txt_u_license.getText();
        String licenseIssue = txt_u_licenseIssue.getText();
        String licenseExp = txt_u_licenseExp.getText();
        int assignBus = txt_u_assignBus.getSelectedIndex();
        String checkMedical = String.valueOf(txt_u_medicalIssue.isSelected());

        updateDriverFunc(id, name, address, contact, dob, doj, license, licenseIssue, licenseExp, assignBus, checkMedical);

    }//GEN-LAST:event_driverUpdateBtnMouseClicked

    private void driverRemoveBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_driverRemoveBtnMouseClicked
        // TODO add your handling code here:
        int id = Integer.parseInt(txt_u_driverId.getText());
        deleteDriverFunc(id);
    }//GEN-LAST:event_driverRemoveBtnMouseClicked

    private void busDetailsAddBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_busDetailsAddBtnMouseClicked
        // TODO add your handling code here:
        String license = txtBusLicense.getText();
        int type = txtBusType.getSelectedIndex();
        int seat = Integer.parseInt(txtSeatNum.getText());
        String name = txtBusOwner.getText();
        String contact = txtBusOwnerContact.getText();
        int duration = txtBusContractDuration.getSelectedIndex();
        String address = txtBusOwnerAddress.getText();
        String fitness = String.valueOf(txtBusFitnessCert.isSelected());

        addBusDetailsFunc(license, type, seat, name, contact, duration, address, fitness);

        txtBusLicense.setText("");
        txtBusType.setSelectedIndex(0);
        txtSeatNum.setText("");
        txtBusOwner.setText("");
        txtBusOwnerContact.setText("");
        txtBusOwnerAddress.setText("");
        txtBusContractDuration.setSelectedIndex(0);
        txtBusFitnessCert.setSelected(false);

    }//GEN-LAST:event_busDetailsAddBtnMouseClicked

    private void removeBusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBusBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeBusBtnActionPerformed

    private void busRefreshBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_busRefreshBtnMouseClicked
        // TODO add your handling code here:
        try {
            ServerConnect con = new ServerConnect();
            ResultSet rs = con.s.executeQuery("select * from bus");

            bus_tbl_reset();
            while (rs.next()) {
                int serial = rs.getInt("serial");
                String license = String.valueOf(rs.getString("bus_license"));
                int type = rs.getInt("bus_type");
                int seat = rs.getInt("seat_number");
                String owner = rs.getString("owner");
                String contact = rs.getString("owner_contact");
                int duration = rs.getInt("duration");
                String address = rs.getString("address");
                String fitness = rs.getString("fitness_cert");

                Object[] obj = {serial, license, owner, type, seat, contact, duration, address, fitness};

                model = (DefaultTableModel) tbl_bus.getModel();
                model.addRow(obj);

            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_busRefreshBtnMouseClicked

    private void tbl_busMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_busMouseClicked
        // TODO add your handling code here:
        int rowNo = tbl_bus.getSelectedRow();
        TableModel model = tbl_bus.getModel();

        txtUBusSerial.setText(model.getValueAt(rowNo, 0).toString());
        txtUBusLicense.setText(tbl_bus.getModel().getValueAt(rowNo, 1).toString());
        txtUBusName.setText(tbl_bus.getModel().getValueAt(rowNo, 2).toString());
        txtUBusType.setSelectedIndex(Integer.parseInt(tbl_bus.getModel().getValueAt(rowNo, 3).toString()));
        txtUBusSeat.setText(tbl_bus.getModel().getValueAt(rowNo, 4).toString());
        txtUBusContact.setText(tbl_bus.getModel().getValueAt(rowNo, 5).toString());
        txtUBusDuration.setSelectedIndex(Integer.parseInt(tbl_bus.getModel().getValueAt(rowNo, 6).toString()));
        txtUBusAddress.setText(tbl_bus.getModel().getValueAt(rowNo, 7).toString());
        txtUBusFitnessCert.setSelected(Boolean.parseBoolean(tbl_bus.getModel().getValueAt(rowNo, 8).toString()));
    }//GEN-LAST:event_tbl_busMouseClicked

    private void busUpdateBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_busUpdateBtnMouseClicked
        // TODO add your handling code here:

        int serial = Integer.parseInt(txtUBusSerial.getText());
        String license = txtUBusLicense.getText();
        int type = txtUBusType.getSelectedIndex();
        int seat = Integer.parseInt(txtUBusSeat.getText());
        String name = txtUBusName.getText();
        String contact = txtUBusContact.getText();
        String address = txtUBusAddress.getText();
        int duration = txtUBusDuration.getSelectedIndex();
        String checkFitness = String.valueOf(txtUBusFitnessCert.isSelected());

        updateBusFunc(serial, license, type, seat, name, contact, address, duration, checkFitness);

    }//GEN-LAST:event_busUpdateBtnMouseClicked

    private void removeBusBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeBusBtnMouseClicked
        // TODO add your handling code here:
        int serial = Integer.parseInt(txtUBusSerial.getText());
        deleteBusFunc(serial);
    }//GEN-LAST:event_removeBusBtnMouseClicked

    private void updateBusSearchBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateBusSearchBtnMouseClicked
        // TODO add your handling code here:
        try {
            int searchText = Integer.parseInt(txtUBusSerial.getText());
            //search_driverTable(searchText);

            TableModel model = tbl_bus.getModel();

            txtUBusLicense.setText(tbl_bus.getModel().getValueAt(searchText - 1, 1).toString());
            txtUBusName.setText(tbl_bus.getModel().getValueAt(searchText - 1, 2).toString());
            txtUBusType.setSelectedIndex(Integer.parseInt(tbl_bus.getModel().getValueAt(searchText - 1, 3).toString()));
            txtUBusSeat.setText(tbl_bus.getModel().getValueAt(searchText - 1, 4).toString());

            txtUBusContact.setText(tbl_bus.getModel().getValueAt(searchText - 1, 5).toString());
            txtUBusDuration.setSelectedIndex(Integer.parseInt(tbl_bus.getModel().getValueAt(searchText - 1, 6).toString()));
            txtUBusAddress.setText(tbl_bus.getModel().getValueAt(searchText - 1, 7).toString());

            txtUBusFitnessCert.setSelected(Boolean.parseBoolean(tbl_bus.getModel().getValueAt(searchText - 1, 8).toString()));

        } catch (Exception e) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Sorry! Not Found.");
            System.err.println(e);
        }
    }//GEN-LAST:event_updateBusSearchBtnMouseClicked

    private void addRouteBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addRouteBtnMouseClicked
        // TODO add your handling code here:
        String route = txtRouteName.getText();

        try {
            ServerConnect con = new ServerConnect();
            PreparedStatement ps = con.c.prepareStatement("INSERT INTO route(route) VALUES(?)");
            ps.setString(1, route);
            int rowCnt = ps.executeUpdate();
            if (rowCnt == 1) {
                JOptionPane.showMessageDialog(this, "Added Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Insertion Failed!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Insertion Failed!");
        }
        txtRouteName.setText("");
    }//GEN-LAST:event_addRouteBtnMouseClicked

    private void tbl_routeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_routeMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tbl_routeMouseClicked

    private void routeRefreshBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_routeRefreshBtnMouseClicked
        // TODO add your handling code here:
        try {
            ServerConnect con = new ServerConnect();
            ResultSet rs = con.s.executeQuery("select * from route");

            route_tbl_reset();
            while (rs.next()) {
                int route_id = rs.getInt("route_id");
                String route = rs.getString("route");

                Object[] obj = {route_id, route};

                model = (DefaultTableModel) tbl_route.getModel();
                model.addRow(obj);

            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_routeRefreshBtnMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashboardScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addBus;
    private javax.swing.JPanel addBusDetails;
    private javax.swing.JLabel addBusRouteLbl;
    private javax.swing.JPanel addBusRoutePnl;
    private javax.swing.JLabel addBusSchLbl;
    private javax.swing.JPanel addBusSchPnl;
    private javax.swing.JButton addDriverBtn;
    private javax.swing.JPanel addDriverPane;
    private javax.swing.JButton addRouteBtn;
    private javax.swing.JPanel addRoutePane;
    private javax.swing.JPanel addSchedulePane;
    private javax.swing.JPanel btnUpdateDriver;
    private javax.swing.JPanel btnUpdateDriver1;
    private javax.swing.JPanel btnUpdateDriver2;
    private javax.swing.JPanel btnUpdateDriver3;
    private javax.swing.JPanel btnUpdateDriver4;
    private javax.swing.JButton busDetailsAddBtn;
    private javax.swing.JPanel busDetailsCard;
    private javax.swing.JPanel busInvoice;
    private javax.swing.JButton busRefreshBtn;
    private javax.swing.JPanel busScheduleCard;
    private javax.swing.JPanel busScheduling;
    private javax.swing.JButton busUpdateBtn;
    private javax.swing.JPanel dashBoard;
    private javax.swing.JPanel driverDetails;
    private javax.swing.JPanel driverDetailsCard;
    private javax.swing.JPanel driverDetailsCard5;
    private javax.swing.JPanel driverInvoice;
    private javax.swing.JButton driverRemoveBtn;
    private javax.swing.JButton driverUpdateBtn;
    private javax.swing.JComboBox<String> dropDriverAssignBusNo;
    private javax.swing.JPanel invoice;
    private javax.swing.JPanel invoiceCard;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox15;
    private javax.swing.JComboBox<String> jComboBox16;
    private javax.swing.JComboBox<String> jComboBox17;
    private javax.swing.JComboBox<String> jComboBox18;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JPanel middlePanel;
    private javax.swing.JPanel payBus;
    private javax.swing.JPanel payDriver;
    private javax.swing.JPanel payment;
    private javax.swing.JPanel paymentCard;
    private javax.swing.JButton removeBusBtn;
    private javax.swing.JButton removeRouteBtn;
    private javax.swing.JButton routeRefreshBtn;
    private javax.swing.JPanel sideBar;
    private javax.swing.JLabel sideBusLbl;
    private javax.swing.JPanel sideBusPnl;
    private javax.swing.JLabel sideDashboardLbl;
    private javax.swing.JPanel sideDashboardPnl;
    private javax.swing.JLabel sideDriverLbl;
    private javax.swing.JPanel sideDriverPnl;
    private javax.swing.JLabel sideInvoiceLbl;
    private javax.swing.JPanel sideInvoicePnl;
    private javax.swing.JLabel sideLogoutLbl;
    private javax.swing.JPanel sideLogoutPnl;
    private javax.swing.JLabel sidePaymentLbl;
    private javax.swing.JPanel sidePaymentPnl;
    private javax.swing.JLabel sideScheduleLbl;
    private javax.swing.JPanel sideSchedulePnl;
    private javax.swing.JTable tbl_bus;
    private javax.swing.JTable tbl_driver;
    private javax.swing.JTable tbl_route;
    private javax.swing.JCheckBox tickDriverMedicalIssue;
    private javax.swing.JLabel topAddBusBtnLbl;
    private javax.swing.JPanel topAddBusBtnPnl;
    private javax.swing.JLabel topAddDriverBtnLbl;
    private javax.swing.JPanel topAddDriverBtnPnl;
    private javax.swing.JPanel topBar;
    private javax.swing.JLabel topGenerateBusInvceLbl;
    private javax.swing.JPanel topGenerateBusInvcePnl;
    private javax.swing.JLabel topGenerateDriverInvceLbl;
    private javax.swing.JPanel topGenerateDriverInvcePnl;
    private javax.swing.JLabel topPayBusLbl;
    private javax.swing.JPanel topPayBusPnl;
    private javax.swing.JLabel topPayDriverLbl;
    private javax.swing.JPanel topPayDriverPnl;
    private javax.swing.JLabel topUpdateBusBtnLbl;
    private javax.swing.JPanel topUpdateBusBtnPnl;
    private javax.swing.JLabel topUpdateDriverBtnLbl;
    private javax.swing.JPanel topUpdateDriverBtnPnl;
    private javax.swing.JComboBox<String> txtBusContractDuration;
    private javax.swing.JCheckBox txtBusFitnessCert;
    private javax.swing.JTextField txtBusLicense;
    private javax.swing.JTextField txtBusOwner;
    private javax.swing.JTextField txtBusOwnerAddress;
    private javax.swing.JTextField txtBusOwnerContact;
    private javax.swing.JComboBox<String> txtBusType;
    private javax.swing.JTextField txtDriverAddress;
    private javax.swing.JTextField txtDriverContactNo;
    private javax.swing.JTextField txtDriverDob;
    private javax.swing.JTextField txtDriverJoinDate;
    private javax.swing.JTextField txtDriverLicenseExp;
    private javax.swing.JTextField txtDriverLicenseIssue;
    private javax.swing.JTextField txtDriverLicenseNum;
    private javax.swing.JTextField txtDriverName;
    private javax.swing.JTextField txtRouteName;
    private javax.swing.JTextField txtSeatNum;
    private javax.swing.JTextField txtUBusAddress;
    private javax.swing.JTextField txtUBusContact;
    private javax.swing.JComboBox<String> txtUBusDuration;
    private javax.swing.JCheckBox txtUBusFitnessCert;
    private javax.swing.JTextField txtUBusLicense;
    private javax.swing.JTextField txtUBusName;
    private javax.swing.JTextField txtUBusSeat;
    private javax.swing.JTextField txtUBusSerial;
    private javax.swing.JComboBox<String> txtUBusType;
    private javax.swing.JTextField txt_u_address;
    private javax.swing.JComboBox<String> txt_u_assignBus;
    private javax.swing.JTextField txt_u_contact;
    private javax.swing.JTextField txt_u_dob;
    private javax.swing.JTextField txt_u_doj;
    private javax.swing.JTextField txt_u_driverId;
    private javax.swing.JTextField txt_u_driverName;
    private javax.swing.JTextField txt_u_license;
    private javax.swing.JTextField txt_u_licenseExp;
    private javax.swing.JTextField txt_u_licenseIssue;
    private javax.swing.JCheckBox txt_u_medicalIssue;
    private javax.swing.JPanel updateBusDetails;
    private javax.swing.JButton updateBusSearchBtn;
    private javax.swing.JPanel updateDriverPane;
    private javax.swing.JButton updateDriverSearchBtn;
    private javax.swing.JPanel wholePanel;
    // End of variables declaration//GEN-END:variables
}
