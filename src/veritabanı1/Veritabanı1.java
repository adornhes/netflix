/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veritabanı1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author ziyaÇ
 */
public class Veritabanı1 {

    private static int id;

    static class homePage {

        JFrame frame = new JFrame();   // panel eklenecek pencere
        JPanel panel = new JPanel();   // bileşen eklenecek panel

        JLabel label1 = new JLabel("E-posta:");
        JLabel label2 = new JLabel("Şifre:");
        JLabel label3 = new JLabel();
        JLabel label4 = new JLabel("Giriş başarısız");

        JTextField text1 = new JTextField();
        JPasswordField text2 = new JPasswordField();
        JButton girişb = new JButton("Giriş");
        JButton kayıtb = new JButton("Kayıt ol");

    }

    public static void main(String[] args) {
        new homePage();
        homePage h1 = new homePage();

        h1.frame.setSize(1600, 900);

        h1.frame.setResizable(false);
        h1.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        h1.frame.add(h1.panel);
        h1.panel.setLayout(null);
        h1.label1.setBounds(635, 20, 80, 30);
        h1.label2.setBounds(650, 38, 80, 30);
        h1.label1.setForeground(Color.yellow);
        h1.label2.setForeground(Color.YELLOW);
        h1.label3.setBounds(0, 0, 1600, 900);
        h1.label4.setBounds(717, 98, 150, 20);
        h1.label4.setBackground(Color.black);
        h1.label4.setForeground(Color.yellow);
        h1.text1.setBounds(690, 26, 145, 20);
        h1.text2.setBounds(690, 46, 145, 20);
        h1.label3.setIcon(new ImageIcon("src/p1.jpg"));
        h1.label3.add(h1.label1);
        h1.label3.add(h1.label2);
        h1.panel.add(h1.label3);
        h1.panel.add(h1.text1);
        h1.panel.add(h1.text2);
        h1.panel.add(h1.label4);
        h1.girişb.setBounds(670, 75, 80, 20);
        h1.girişb.setForeground(Color.white);
        h1.girişb.setBackground(Color.black);
        h1.kayıtb.setBackground(Color.black);
        h1.kayıtb.setBounds(752, 75, 80, 20);
        h1.kayıtb.setForeground(Color.white);
        h1.kayıtb.addActionListener(new kayıt());
        h1.label3.add(h1.girişb);
        h1.label3.add(h1.kayıtb);
        h1.label3.add(h1.label4);
        h1.label4.setVisible(false);
        h1.frame.setVisible(true);

        h1.girişb.addActionListener((ActionEvent a) -> {    //giriş butonu için anonim sınıf oluşturduk..
            String kullanıcı_adı = "", sifre = "";
            kullanıcı_adı = h1.text1.getText();
            sifre = h1.text2.getText();
            try {                                      //sorgu için try catch
                String url1 = "select ID FROM kullanici  where eposta='" + kullanıcı_adı + "' and sifre='" + sifre + "'";
                String url2 = "jdbc:sqlite:src/hesaplar/kou-flix.db";
                Connection conn;
                conn = DriverManager.getConnection(url2);
                Statement st = conn.createStatement();
                ResultSet rst = st.executeQuery(url1);

                id = rst.getInt("ID");
                
                rst.close();
            } catch (SQLException ex) {

                 System.out.println(ex.getMessage());
            }

            String url = "select count(ID) as giris FROM kullanici  where eposta='" + kullanıcı_adı + "' and sifre='" + sifre + "'";
            try {                                      //sorgu için try catch

                String url1 = "jdbc:sqlite:src/hesaplar/kou-flix.db";
                Connection conn;
                conn = DriverManager.getConnection(url1);
                Statement st = conn.createStatement();
                ResultSet rst = st.executeQuery(url);
                int i;
                i = 0;                                        //e posta şifre match kontolu yapar ....
               
                while (rst.next()) {
                    if (rst.getInt("giris") == 1) {
                        h1.label4.setVisible(false);
                        i++;
                        kullanıcı_panel y = new kullanıcı_panel();
                        break;
                    }
                }
                if (i == 0) {
                    h1.label4.setVisible(true);
                }
                rst.close();
                st.close();
                conn.close();
            } catch (SQLException ex) {

                  System.out.println(ex.getMessage());
            }

        });

    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Veritabanı1.id = id;
    }

}
