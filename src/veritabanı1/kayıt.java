/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veritabanı1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ziyaÇ
 */
public class kayıt implements ActionListener {

    private Connection conn;
    private Statement st;
    private Statement st2;

    static class kayıt_sayfası {

        JFrame frame1 = new JFrame();
        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("E-posta");  //e posta
        JLabel label2 = new JLabel("Şifre");  //şifre
        JLabel label3 = new JLabel("Favori Program Türü 1");  //program türü 1
        JLabel label4 = new JLabel("Favori Program Türü 2");  //program türü 2
        JLabel label5 = new JLabel("Favori Program Türü 3");  //program türü 3
        JLabel label6 = new JLabel("Ad soyad");
        JLabel label7 = new JLabel("Doğum tarihi");
        JLabel label8 = new JLabel("Tavsiye ettiğimiz filmler");
        JLabel label9 = new JLabel(); // favori1
        JLabel label10 = new JLabel(); // favori2
        JLabel label11 = new JLabel();  //favori3
        JLabel label12 = new JLabel();   //sorgu sonuc
        JLabel label13 = new JLabel();  //sorgu sonuc
        JLabel label14 = new JLabel();   //sorgu sonuc

        JTextField text1 = new JTextField();        //ad soyadın alınacağı field
        JTextField text2 = new JTextField();           //şifre alınacak field 
        JTextField text3 = new JTextField();            //e posta alınacak field
        JButton kayıt_ol = new JButton("Hesabı oluştur");
        JTextField text4 = new JTextField();             //doğum tarihi alınan field
        JTextField text5 = new JTextField();          //fav1
        JTextField text6 = new JTextField();            //fav2
        JTextField text7 = new JTextField();             //fav3

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("asd");
        kayıt_sayfası k1 = new kayıt_sayfası();
        k1.panel1.setLayout(null);
        k1.frame1.setSize(1600, 900);
        k1.frame1.setResizable(false);
        k1.panel1.setBackground(Color.BLACK);
        k1.frame1.add(k1.panel1);
        k1.label1.setBounds(740, 90, 150, 16);
        k1.label1.setForeground(Color.yellow);
        k1.label2.setBounds(760, 60, 60, 16);
        k1.label2.setForeground(Color.yellow);
        k1.label3.setBounds(660, 150, 150, 16);
        k1.label3.setForeground(Color.yellow);
        k1.label4.setBounds(660, 180, 150, 16);
        k1.label4.setForeground(Color.yellow);
        k1.label5.setBounds(660, 210, 150, 16);
        k1.label5.setForeground(Color.yellow);
        k1.label6.setBounds(740, 30, 100, 16);
        k1.label6.setForeground(Color.yellow);
        k1.label7.setBounds(710, 124, 100, 10);
        k1.label7.setForeground(Color.yellow);
        k1.label8.setBounds(830, 280, 350, 15);
        k1.label8.setForeground(Color.yellow);
        k1.label8.setBounds(780, 310, 300, 15);
        k1.label8.setForeground(Color.yellow);
        k1.label9.setBounds(650, 330, 230, 15);
        k1.label9.setForeground(Color.yellow);
        k1.label10.setBounds(650, 350, 230, 15);
        k1.label10.setForeground(Color.yellow);
        k1.label11.setBounds(650, 370, 230, 15);
        k1.label11.setForeground(Color.yellow);
        k1.label12.setBounds(800, 330, 230, 15);
        k1.label12.setForeground(Color.yellow);
        k1.label12.setBackground(Color.black);
        k1.label13.setBounds(800, 350, 230, 15);
        k1.label13.setBackground(Color.black);
        k1.label13.setForeground(Color.yellow);
        k1.label14.setBounds(800, 370, 230, 15);
        k1.label14.setForeground(Color.yellow);
        k1.label14.setBackground(Color.black);

        k1.text1.setBounds(800, 30, 145, 17);
        k1.text1.setBackground(Color.BLACK);
        k1.text1.setForeground(Color.yellow);
        k1.text2.setBackground(Color.BLACK);
        k1.text2.setForeground(Color.yellow);
        k1.text2.setBounds(800, 60, 145, 17);
        k1.text3.setBackground(Color.BLACK);
        k1.text3.setForeground(Color.yellow);
        k1.text3.setBounds(800, 90, 145, 17);
        k1.text4.setBounds(800, 120, 145, 17);
        k1.text4.setForeground(Color.YELLOW);
        k1.text4.setBackground(Color.black);
        k1.text5.setBounds(800, 150, 145, 17);
        k1.text5.setForeground(Color.YELLOW);
        k1.text5.setBackground(Color.black);
        k1.text6.setBounds(800, 180, 145, 17);
        k1.text6.setForeground(Color.yellow);
        k1.text7.setBounds(800, 210, 145, 17);
        k1.text7.setBackground(Color.black);
        k1.text7.setForeground(Color.yellow);
        k1.text6.setForeground(Color.YELLOW);
        k1.text6.setBackground(Color.black);

        k1.panel1.add(k1.label1);
        k1.panel1.add(k1.label2);
        k1.panel1.add(k1.label3);
        k1.panel1.add(k1.label4);
        k1.panel1.add(k1.label5);
        k1.panel1.add(k1.label6);
        k1.panel1.add(k1.label7);
        k1.panel1.add(k1.text1);
        k1.panel1.add(k1.text2);
        k1.panel1.add(k1.text3);
        k1.panel1.add(k1.text4);
        k1.panel1.add(k1.text5);
        k1.panel1.add(k1.text6);
        k1.panel1.add(k1.text7);
        k1.panel1.add(k1.label8);
        k1.panel1.add(k1.label9);
        k1.panel1.add(k1.label10);
        k1.panel1.add(k1.label11);
        k1.panel1.add(k1.label12);
        k1.panel1.add(k1.label13);
        k1.panel1.add(k1.label14);

        k1.kayıt_ol.setBounds(800, 240, 150, 17);
        k1.kayıt_ol.setForeground(Color.yellow);
        k1.kayıt_ol.setBackground(Color.black);
        k1.panel1.add(k1.kayıt_ol);
        k1.frame1.setVisible(true);

        k1.label8.setVisible(false);
        k1.label9.setVisible(false);
        k1.label10.setVisible(false);
        k1.label11.setVisible(false);
        k1.label12.setVisible(false);
        k1.label13.setVisible(false);
        k1.label14.setVisible(false);

        k1.frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        k1.kayıt_ol.addActionListener((ActionEvent e1) -> {   // buton işlevi için anonim sınıf kullandık..

            String e_posta, ad_soyad, sifre, fav1, fav2, fav3, dogum_tarihi;
            ad_soyad = k1.text1.getText();
            sifre = k1.text2.getText();
            e_posta = k1.text3.getText();
            fav1 = k1.text5.getText();
            fav2 = k1.text6.getText();
            fav3 = k1.text7.getText();
            dogum_tarihi = k1.text4.getText();
            k1.label9.setText(fav1);
            k1.label10.setText(fav2);
            k1.label11.setText(fav3);

            try {
                String url = "jdbc:sqlite:src/hesaplar/kou-flix.db";
                conn = DriverManager.getConnection(url);
                st = conn.createStatement();
            } catch (SQLException ex) {
                System.out.println("database'e bağlanılamadı");
            }

            try {
                Statement st = conn.createStatement();
                String sorgu = "Insert into kullanici (eposta,sifre,favori1,favori2,favori3,dogum_tarihi) VALUES(" + "'" + e_posta + "'," + "'" + sifre + "'," + "'" + dogum_tarihi + "'," + "'" + fav2 + "'," + "'" + fav3 + "'," + "'" + fav1 + "')";
                st.executeUpdate(sorgu);
            } catch (SQLException ex) {
                Logger.getLogger(kayıt.class.getName()).log(Level.SEVERE, null, ex);
            }
            k1.label8.setVisible(true);
            k1.label9.setVisible(true);
            k1.label10.setVisible(true);
            k1.label11.setVisible(true);

            String url = "jdbc:sqlite:src/hesaplar/kou-flix.db";

            try {
                conn = DriverManager.getConnection(url);
                st = conn.createStatement();
                st2 = conn.createStatement();
                String sorgu = "Select isim, max(puan) from Programlar where tur like '%" + fav1 + "%'";
                String sorgu2 = "Select isim,max(puan) from Programlar where puan < (Select MAX(puan) FROM Programlar) AND tur like '%" + fav1 + "%'";

                // String sorgu2 = "
                ResultSet rst1, rst2;
                String label12_s = "";
                String son_hal;
                //String label_s=""; 
                rst1 = st.executeQuery(sorgu);
                rst2 = st2.executeQuery(sorgu2);

                label12_s = rst1.getString("isim");
                String ehe = rst2.getString("isim");
                son_hal = label12_s + "-" + ehe;

                k1.label12.setText(son_hal);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                conn = DriverManager.getConnection(url);
                st = conn.createStatement();
                String sorgu = "Select isim, max(puan) from Programlar where tur like '%" + fav2 + "%'";
                String sorgu2 = "Select isim,max(puan) from Programlar where puan < (Select MAX(puan) FROM Programlar) AND tur like '%" + fav2 + "%'";

                String label13_s = "";

                ResultSet rst3, rst4;

                String son_hal;
                //String label_s=""; 
                rst3 = st.executeQuery(sorgu);
                rst4 = st2.executeQuery(sorgu2);

                label13_s = rst3.getString("isim");
                String ehe = rst4.getString("isim");
                son_hal = label13_s + "-" + ehe;
                k1.label13.setText(son_hal);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                conn = DriverManager.getConnection(url);
                st = conn.createStatement();

                String sorgu = "Select isim, max(puan) from Programlar where tur like '%" + fav3 + "%'";
                String sorgu2 = "Select isim,max(puan) from Programlar where puan < (Select MAX(puan) FROM Programlar) AND tur like '%" + fav3 + "%'";
                ResultSet rst1;
                String label14_s = "";
                rst1 = st.executeQuery(sorgu);
                ResultSet rst3, rst4;
                String son_hal;
                rst3 = st.executeQuery(sorgu);
                rst4 = st2.executeQuery(sorgu2);
                label14_s = rst3.getString("isim");
                String ehe = rst4.getString("isim");
                son_hal = label14_s + "-" + ehe;
                k1.label14.setText(son_hal);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            k1.label12.setVisible(true);
            k1.label13.setVisible(true);
            k1.label14.setVisible(true);

        });
    }
}
