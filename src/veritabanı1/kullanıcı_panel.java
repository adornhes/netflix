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
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class kullanıcı_panel {

    private String secilen;
    private String k_film;

    class user_panel {

        JFrame frame1 = new JFrame();
        JPanel panel1 = new JPanel();
        JLabel main_label = new JLabel();
        JTextField text1 = new JTextField();  //arama anahtar kelimesinin yazılacağı field
        JLabel label1 = new JLabel("Aramak istediğininz içeriğin adını veya türünü giriniz:");
        JButton buton1 = new JButton("Ara");

        JLabel label3 = new JLabel();   //sonucun gösterileceği etiket
        JLabel label4 = new JLabel("Kaç dk izlediniz:");
        JLabel label5 = new JLabel("Kaçınci bölümde kaldınız:");

        JLabel label7 = new JLabel("İzlediğiniz Tarih:");
        JLabel label8 = new JLabel("Puan(1-10):");
        JLabel label9 = new JLabel();             //kalan dakika yazdırma
        JLabel label10 = new JLabel();           //puan yazdıran etiket
        JLabel label11 = new JLabel();   //bolum sayısı etiket
        JLabel label12 = new JLabel();   // son program etiketi  // bölüm uzunluğu etiket

        JTextArea text2 = new JTextArea();
        JTextField text3 = new JTextField();    //kalinan süreyi alan field
        JTextField text4 = new JTextField();    // kalinana bölümü alan field
        JTextField text5 = new JTextField();     //tarih 
        JTextField text6 = new JTextField();     //puan
        JButton buton2 = new JButton("İzle");
        JButton buton3 = new JButton("Kaydet");
        JButton buton4 = new JButton("Bitir");
        JComboBox combo1 = new JComboBox();      //kullanıcının arama sonucundan istediği programı seçmeyi amaçlar

    }

    public kullanıcı_panel() {                   //classımızın constructor'ı işlemlerin gerçekleşeceği yer
        user_panel u1 = new user_panel();

        u1.buton4.setBounds(770, 140, 80, 20);
        u1.buton4.setBackground(Color.black);
        u1.buton4.setForeground(Color.yellow);
        u1.label10.setBounds(550, 80, 600, 60);
        u1.label10.setForeground(Color.yellow);
        u1.label11.setBounds(655, 117, 300, 20);
        u1.label11.setForeground(Color.yellow);
        u1.label12.setBounds(655, 97, 300, 20);
        u1.label12.setForeground(Color.yellow);

        u1.frame1.setSize(1600, 900);
        u1.panel1.setSize(1600, 900);
        u1.frame1.setLayout(null);
        u1.panel1.setLayout(null);
        u1.frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        u1.frame1.setResizable(false);
        u1.panel1.setBackground(Color.gray);
        u1.frame1.add(u1.panel1);
        u1.panel1.add(u1.label9);
        u1.panel1.add(u1.label10);
        u1.panel1.add(u1.buton4);
        u1.panel1.add(u1.label11);
        u1.panel1.add(u1.label12);
        u1.label9.setVisible(false);
        u1.label10.setVisible(false);
        u1.buton4.setVisible(false);
        int id2 = Veritabanı1.getId();

        String url = "jdbc:sqlite:src/hesaplar/kou-flix.db";
        int f_sure = 0; //s2 sorgudan gelen bu int'e atilacak.

        int f_sure2 = 0; //s3 sorgudan gelen bu int'e atilacak.
        try {
            Connection conn;
            Statement st;
            conn = DriverManager.getConnection(url);
            st = conn.createStatement();

            String s1 = "Select kalinan FROM KullaniciProgram where id=" + id2 + ""; // sorgudan gelen stringe atilacak.

            ResultSet srt = st.executeQuery(s1);

            while (srt.next()) {
                k_film = srt.getString("kalinan");
            }

            String s2 = "Select sure from Programlar where isim=" + "'" + k_film + "'"; //sorgudan geleni int'e aticaz.
            ResultSet srt2 = st.executeQuery(s2);
            while (srt2.next()) {
                f_sure = srt2.getInt("sure");
            }

            String s3 = "Select izlenme_suresi FROM KullaniciProgram where id=" + id2 + ""; //sorgudan gelen inte atilacak.
            ResultSet srt3 = st.executeQuery(s3);
            while (srt3.next()) {
                f_sure2 = srt.getInt("izlenme_suresi");
            }

            srt.close();
            st.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        if (f_sure > f_sure2) {

            int dakika = f_sure - f_sure2;  //filmde inci dakikada kaldiniz bilgisi yazilacak.
            String s = Integer.toString(dakika);
            s = s + "'inci dakidada kaldınız";

            u1.buton4.setVisible(true);

            try {

                int yardımcı = 0;
                String s5 = " Select verilen_puan from KullaniciProgram where id=" + id2;// --> inte atilacak. --> ekrana bu filme şu puanı vermiştiniz yazdirilacak.
                Connection conn = DriverManager.getConnection(url);
                Statement st = conn.createStatement();
                ResultSet rst = st.executeQuery(s5);
                while (rst.next()) {
                    yardımcı = rst.getInt("verilen_puan");

                }
                String s2 = Integer.toString(yardımcı);
                String son_hal = s2 + " puan verdiğiniz " + k_film + " adlı filmi " + dakika + ". dakikadan izlemeye devam edebilirsiniz,iyi seyirler";
                u1.label10.setText(son_hal);
                u1.label10.setVisible(true);
                rst.close();
                st.close();
                conn.close();

            } catch (SQLException ex) {
                Logger.getLogger(kullanıcı_panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

  
        u1.label1.setBounds(460, 50, 300, 15);
        u1.label1.setForeground(Color.YELLOW);
        u1.label1.setBackground(Color.BLACK);
        u1.label4.setBounds(450, 100, 200, 15);   // dakika
        u1.label4.setBackground(Color.black);
        u1.label4.setForeground(Color.yellow);
        u1.label5.setBounds(400, 120, 200, 15);    // bolum
        u1.label5.setBackground(Color.black);
        u1.label5.setForeground(Color.yellow);

        u1.label7.setBounds(455, 140, 90, 15);
        u1.label7.setForeground(Color.yellow);
        u1.label8.setBounds(480, 160, 120, 15);
        u1.label8.setForeground(Color.yellow);

        //gerekli textfieldlar
        u1.text1.setBounds(760, 50, 300, 17);
        u1.text1.setForeground(Color.yellow);
        u1.text1.setBackground(Color.BLACK);

        u1.text3.setBounds(550, 100, 100, 15);   // dakika
        u1.text3.setBackground(Color.black);
        u1.text3.setForeground(Color.yellow);
        u1.text4.setBounds(550, 120, 100, 15);   //bolum
        u1.text5.setBounds(550, 140, 100, 15);   //tarih
        u1.text6.setBounds(550, 160, 100, 15);  //puan
        u1.text4.setBackground(Color.BLACK);
        u1.text4.setForeground(Color.yellow);
        //gerekli butonlar

        u1.buton1.setBounds(1080, 50, 75, 15);
        u1.buton1.setBackground(Color.BLACK);
        u1.buton1.setForeground(Color.YELLOW);

        u1.buton3.setBounds(560, 180, 80, 15);
        u1.buton3.setForeground(Color.yellow);
        u1.buton3.setBackground(Color.black);

        u1.panel1.add(u1.label1);
        u1.panel1.add(u1.text1);
        u1.panel1.add(u1.text2);
        u1.panel1.add(u1.text2);
        u1.panel1.add(u1.text3);
        u1.panel1.add(u1.text4);

        u1.panel1.add(u1.buton1);
        u1.panel1.add(u1.buton2);
        u1.panel1.add(u1.label4);
        u1.panel1.add(u1.label5);
        u1.panel1.add(u1.buton3);

        u1.panel1.add(u1.text5);
        u1.panel1.add(u1.text6);
        u1.panel1.add(u1.label7);
        u1.panel1.add(u1.label8);
        u1.text5.setVisible(false);
        u1.text6.setVisible(false);
        u1.label7.setVisible(false);
        u1.label8.setVisible(false);
        u1.text4.setVisible(false);
        u1.label4.setVisible(false);
        u1.text3.setVisible(false);
        u1.frame1.setVisible(true);
        u1.label5.setVisible(false);
        u1.buton3.setVisible(false);

        u1.buton4.addActionListener((ActionEvent e3) -> {               //BİTİR TUSU

            String sorgu1 = "UPDATE KullaniciProgram SET izlenenler=izlenenler||' '||'" + k_film + "' where id=" + id2;

            String sorgu2 = "UPDATE KullaniciProgram SET kalinan = null  where id =" + id2;
            String sorgu3 = "UPDATE KullaniciProgram SET izlenme_suresi = null where id=" + id2;
            String sorgu4 = "UPDATE KullaniciProgram SET verilen_puan = null where id=" + id2;
            String sorgu5 = "UPDATE KullaniciProgram SET izlenme_tarihi = null where id=" + id2;
            String sorgu6 = "UPDATE KullaniciProgram SET kalinan_bolum = null where id=" + id2;

            try {

                Connection conn = DriverManager.getConnection(url);
                Statement st = conn.createStatement();

                try {
                    st.executeQuery(sorgu1);

                    st.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

                try {
                    Statement st2 = conn.createStatement();
                    st2.executeQuery(sorgu2);
                    st2.close();

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

                try {
                    Statement st3 = conn.createStatement();
                    st3.executeQuery(sorgu3);
                    st3.close();

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                try {
                    Statement st4 = conn.createStatement();
                    st4.execute(sorgu4);
                    st4.close();

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                try {
                    Statement st5 = conn.createStatement();
                    st5.execute(sorgu5);
                    st5.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                try {
                    Statement st6 = conn.createStatement();
                    st6.execute(sorgu6);
                    st6.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

                conn.close();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            u1.label10.setVisible(false);
            u1.buton4.setVisible(false);

        });

        u1.buton1.addActionListener((ActionEvent e) -> {
            String arama;

            u1.combo1.removeAllItems();
            arama = u1.text1.getText();
            u1.label10.setVisible(false);
            u1.buton4.setVisible(false);
            try {

                Connection conn = DriverManager.getConnection(url);
                Statement st = conn.createStatement();
                ResultSet srt;
                String sorgu = "SELECT isim,tur FROM Programlar WHERE isim like'%" + arama + "%'OR tur like '%" + arama + "%'";

                srt = st.executeQuery(sorgu);
                int sayac = 0;
                LinkedList<String> film = new LinkedList<>();
                while (srt.next()) {
                    film.add(srt.getString("isim"));
                    sayac++;
                }
                String filmler[] = film.toArray(new String[film.size()]);
                for (int k = 0; k < filmler.length; k++) {
                    u1.combo1.addItem(filmler[k]);
                }

                u1.combo1.setBounds(100, 100, 200, 20);

                u1.panel1.add(u1.combo1);
                u1.combo1.setVisible(true);
                u1.buton2.setBounds(310, 100, 60, 20);
                u1.buton2.setBackground(Color.black);
                u1.buton2.setForeground(Color.yellow);
                u1.panel1.add(u1.buton2);
                u1.frame1.setVisible(true);
                u1.buton2.setVisible(true);

                srt.close();
                st.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        });
        u1.buton2.addActionListener((ActionEvent e1) -> {        //izle butonu işlevi
            secilen = (String) u1.combo1.getSelectedItem();
            //sonucları int'e atilacak.
            try {
                Connection conn = DriverManager.getConnection(url);
                Statement st = conn.createStatement();
                String s1 = "Select bolum_sayisi from Programlar where isim='" + secilen + "'";
                ResultSet rst = st.executeQuery(s1);
                String a = "Bölüm sayısı " + Integer.toString(rst.getInt("bolum_sayisi"));

                u1.label11.setText(a);

                u1.label11.setVisible(true);
                rst.close();
                st.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(kullanıcı_panel.class.getName()).log(Level.SEVERE, null, ex);
            }
            String s2 = "Select sure from Programlar where isim='" + secilen + "'";
            ResultSet rst2;
            try {
                Connection conn = DriverManager.getConnection(url);
                Statement st = conn.createStatement();
                rst2 = st.executeQuery(s2);
                String b = "Programın süresi " + Integer.toString(rst2.getInt("sure"));
                b = b + "dk";
                u1.label12.setText(b);
                u1.label12.setVisible(true);
                rst2.close();
                st.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(kullanıcı_panel.class.getName()).log(Level.SEVERE, null, ex);
            }

            u1.buton3.setVisible(true);
            u1.label5.setVisible(true);
            u1.label4.setVisible(true);
            u1.text3.setVisible(true);
            u1.text4.setVisible(true);
            u1.text5.setVisible(true);
            u1.text6.setVisible(true);
            u1.label8.setVisible(true);
            u1.label7.setVisible(true);

        });

        u1.buton3.addActionListener((ActionEvent e3) -> {
            // String url = "jdbc:sqlite:src/hesaplar/kou-flix.db";

            int kalinan_sure = Integer.parseInt(u1.text3.getText());
            int puan = Integer.parseInt(u1.text6.getText());
            int kalinan_bolum = Integer.parseInt(u1.text4.getText());
            int gecici_id = Veritabanı1.getId();
            String tarih = u1.text5.getText();
            String gecici = secilen;
            int sorgu_sonuc = 0;
            String yardımcı = " ";
            int id_sorgu = Veritabanı1.getId();
            try {
                Connection conn = DriverManager.getConnection(url);
                Statement st = conn.createStatement();
                ResultSet rst = null;

                String sorgu = "SELECT count(id)  FROM KullaniciProgram WHERE id=" + id_sorgu;
                rst = st.executeQuery(sorgu);
                sorgu_sonuc = rst.getInt("count(id)");

                rst.close();
                st.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            if (sorgu_sonuc > 0) {
                String sorgu = "UPDATE KullaniciProgram SET izlenme_tarihi='" + tarih + "',izlenme_suresi=" + kalinan_sure + " ,kalinan='" + gecici + "',kalinan_bolum =" + kalinan_bolum + ",verilen_puan=" + puan + " WHERE id=" + gecici_id;

                try {
                    Connection conn = DriverManager.getConnection(url);
                    Statement st = conn.createStatement();
                    st.executeUpdate(sorgu);
                    st.close();
                    conn.close();

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

                u1.combo1.setVisible(false);
                u1.label4.setVisible(false);
                u1.label5.setVisible(false);
                u1.label7.setVisible(false);
                u1.label8.setVisible(false);
                u1.text3.setVisible(false);
                u1.text4.setVisible(false);
                u1.text5.setVisible(false);
                u1.text6.setVisible(false);
                u1.label12.setVisible(false);
                u1.label11.setVisible(false);
                u1.buton2.setVisible(false);
                u1.buton3.setVisible(false);
            } else {
                try {
                    Connection conn = DriverManager.getConnection(url);
                    Statement st = conn.createStatement();
                    System.out.println("çekpoint1");
                    conn = DriverManager.getConnection(url);
                    st = conn.createStatement();

                    //  int kalinan_sure = Integer.parseInt(u1.text3.getText());
                    //int kalinan_bolum = Integer.parseInt(u1.text4.getText());
                    //  System.out.println("kbölüm " + kalinan_bolum);
                    //    int puan = Integer.parseInt(u1.text6.getText());
                    //System.out.println("puan " + puan);
                    //  int gecici_id = Veritabanı1.getId();
                    //String gecici = secilen;
                    //  String sorgu = "INSERT INTO KullaniciProgram VALUES("+"'"+gecici_id+"',"+"'"+tarih+"',"+"'"+kalinan_sure+"',"+"'"+secilen+"',"+"'"+kalinan_bolum+"',"+"'"+puan+"',"+"'"+gecici+"'"+")";
                    String sorgu2 = "INSERT INTO KullaniciProgram (id,izlenme_tarihi,izlenme_suresi,kalinan,kalinan_bolum,verilen_puan,izlenenler)"
                            + "VALUES(" + gecici_id + ", '" + tarih + "', " + kalinan_sure + ",'" + gecici + "'," + kalinan_bolum + "," + puan + ",'" + yardımcı + "')";
                    //+ "Insert INTO KullaniciProgram (id,izlenme_tarihi,izlenme_suresi,kalinan,kalinan_bolum,verilen_puan,izlenen_filmler) VALUES(" + "'" + gecici_id + "'," + "'" + tarih + "'," + "'" + kalinan_sure + "'," + "'" + gecici + "'," + "'" + kalinan_bolum + "'," + "'" + puan + "'," + null + "')";

                    // rst = st2.executeQuery(sorgu);
                    st.execute(sorgu2);
                    // System.out.println("88888888888888888");

                    st.close();
                    conn.close();

                } catch (SQLException ex) {
                    System.out.println("Database'e bağlanılamadı kaydetasdasdasdasd**");
                }
                u1.combo1.setVisible(false);
                u1.label4.setVisible(false);
                u1.label5.setVisible(false);
                u1.label7.setVisible(false);
                u1.label8.setVisible(false);
                u1.text3.setVisible(false);
                u1.text4.setVisible(false);
                u1.text5.setVisible(false);
                u1.text6.setVisible(false);
                u1.label12.setVisible(false);
                u1.label11.setVisible(false);
                u1.buton2.setVisible(false);
                u1.buton3.setVisible(false);
            }

        });

    }

}
