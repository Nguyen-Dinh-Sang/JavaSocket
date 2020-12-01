package musicinformationclient;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import musicinformationclient.socket.Images;
import musicinformationclient.socket.SocketClient;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import musicinformationclient.socket.loadDing;
import org.json.JSONArray;
import org.json.JSONObject;

public class ok implements SocketClient.Result {
    SocketClient client = new SocketClient("127.0.0.1", 5000, this);
    String dataSing1 = "";
    String dataSing2 = "";
    String dataSong1 = "";
    String dataSong2 = "";
    String requestCuaHai = "";
    JFrame jFrame = new JFrame();

    @Override
    public void result(String mes) {

        System.out.println("mess là" + mes);
        String[] tmp = mes.split("###");
        if (tmp[0].contains("RESULTSEARCHCASI")) {
            dataSing1 = mes;

        }
        if (tmp[0].contains("RESULTSEARCHBAIHAT")) {
            dataSong1 = mes;

        }
        if (tmp[0].contains("RESULTINFOCASI")) {
            dataSing2 = mes;

        }
        if (tmp[0].contains("RESULTINFOBAIHAT")) {
            dataSong2 = mes;

        }

    }

    @Override
    public void closed() {

    }

    public static void main(String[] args) {
        ok oke = new ok();
        oke.init();
    }

    public void OpenUI() {

        jKhungpanel = new javax.swing.JPanel();
        jTieude = new javax.swing.JLabel();
        jLoaitimkiem = new javax.swing.JComboBox<>();
        jnoidungtimkiem = new javax.swing.JTextField();
        jbuttonTimkiem = new javax.swing.JButton();
        jKhungchuadulieu = new javax.swing.JPanel();
        getjKhungchuadulieu_tmp = new JPanel();

//        jpanelLoad = new JPanel();
//        jpanelLoad.setBackground(Color.red);
//        jpanelLoad.setSize(300,400);
//        jFrame.add(jpanelLoad);
//        jpanelLoad.setVisible(false);
        jlabDscasi = new javax.swing.JLabel();

        jFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jKhungpanel.setBackground(new java.awt.Color(255, 255, 255));

        jTieude.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jTieude.setText("Tìm kiếm thông tin bài hát");

        jLoaitimkiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLoaitimkiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Tên ca sĩ", "Bài hát"}));

        jbuttonTimkiem.setBackground(new java.awt.Color(255, 255, 255));
        //jbuttonTimkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/magnifying-glass.png"))); // NOI18N
        jbuttonTimkiem.setText("Tìm kiếm");

        jbuttonTimkiem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                jButton1ActionPerformed(e);
            }
        });
        load = new JLabel();
        load.setText("okejksdjlakfhlkạkljàhfạkl");
        load.setBackground(Color.red);
        load.setLocation(600, 600);
        load.setVisible(false);
        jScrollPane = new JScrollPane(jKhungchuadulieu);
        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(getjKhungchuadulieu_tmp);
        getjKhungchuadulieu_tmp.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(load)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(load)
        );
        javax.swing.GroupLayout jKhungpanelLayout = new javax.swing.GroupLayout(jKhungpanel);
        jKhungpanel.setLayout(jKhungpanelLayout);
        jKhungpanelLayout.setHorizontalGroup(
                jKhungpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jKhungpanelLayout.createSequentialGroup()
                                .addGroup(jKhungpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jKhungpanelLayout.createSequentialGroup()
                                                .addGap(355, 355, 355)
                                                .addComponent(jTieude, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jKhungpanelLayout.createSequentialGroup()
                                                .addGap(239, 239, 239)
                                                .addComponent(jLoaitimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jnoidungtimkiem)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbuttonTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(300, 300, 300))
                        .addGroup(jKhungpanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(getjKhungchuadulieu_tmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap()
                        )
        );
        jKhungpanelLayout.setVerticalGroup(
                jKhungpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jKhungpanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jTieude, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jKhungpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jbuttonTimkiem, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                        .addComponent(jLoaitimkiem)
                                        .addComponent(jnoidungtimkiem))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(getjKhungchuadulieu_tmp, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(300, 300, 300)
                        )
        );
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(jFrame.getContentPane());
        jFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jKhungpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jKhungpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 614, Short.MAX_VALUE))
        );


        jFrame.pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent e) {

        String loaitk = jLoaitimkiem.getSelectedItem().toString();
        if (loaitk.contains("Tên ca sĩ") == true) {   //Images load= new Images();
            load.setVisible(true);
            jScrollPane.setVisible(false);
            getjKhungchuadulieu_tmp.invalidate();
            System.out.println("1");
            //jpanelLoadDing = load.show_image("https://media2.giphy.com/media/3oEjI6SIIHBdRxXI40/giphy.gif",200,200);
            //jpanelLoad.add(jpanelLoadDing);

            String senddata = jnoidungtimkiem.getText();
            client.send("SINGS###" + senddata);
            show_danh_sach_ca_si(dataSing1);
            jScrollPane.setVisible(true);
            load.setVisible(false);
            getjKhungchuadulieu_tmp.invalidate();

        } else {
            /*JPanel jKhungchuadulieus = new JPanel();
            System.out.println("đi tới đây rồi.");

            String senddata=jnoidungtimkiem.getText();
            client.send("SiNGS###"+senddata);
            System.out.println("data:"+dataSong1);*/
            String senddata = jnoidungtimkiem.getText();
            client.send("SONGS###" + senddata);
            show_danh_sach_bai_hat(dataSong1);


        }


    }

    // button click xem thông tin
    private void jbtnXemThongTinActionPerformed(java.awt.event.ActionEvent e, int i) {
        String data = jlabValInfo[i].getText();
        String[] tmp = data.split("=");
        client.send("SINGINFO###" + tmp[1]);
        requestCuaHai = tmp[1];
        String[] datas = dataSing2.split("###");
        JSONObject obj = new JSONObject(datas[1]);

        String Success = obj.getString("success");
        if (Success.contains("no") == true) {
            JSONArray arr = obj.getJSONArray("data");
            if (arr.length() == 0) {
                JOptionPane.showMessageDialog(null, "infoMessage", "InfoBox: " + "titleBar", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Dữ liệu đang được cập nhật thử lại sau ạ.");
            } else {
                show_danh_sach_ca_si_tim_kiem(obj);
                System.out.println("list nè " + dataSing2);
            }

        }
        if (Success.contains("yes") == true) {
            System.out.println("list tim kiếm là" + dataSing2);
        }
        System.out.println("info ca sĩ" + dataSing2);
    }

    private void jbtnXemThongTinBaiHatActionPerformed(java.awt.event.ActionEvent e, int i) {
        String data = jlabValInfo[i].getText();
        
        client.send("SONGINFO###" + data);
        String[] datas = dataSong2.split("###");
        JSONObject obj = new JSONObject(datas[1]);

        String Success = obj.getString("success");
        if (Success.contains("yes")) {
            JSONObject arr = obj.getJSONObject("data");
            if (arr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Không tim thấy thông tin.", "InfoBox: " + "titleBar", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Không tim thấy thông tin.");
            } else {
                showThongTinBaiHat(dataSong2);
                System.out.println("info ca sĩ :" + dataSong2);
            }

        } else {

        }

    }

    // show tìm kiếm trong danh sách
    private void jbtnXemThongTinTimKiemActionPerformed(java.awt.event.ActionEvent e, int i) {
        String dataRQ = jlabValTenCaSi[i].getText();
        String request = "";
        try {
            request = requestCuaHai + "&wiki=" + URLEncoder.encode(dataRQ, "UTF-8");
        } catch (IOException exception) {
            System.out.println(exception);
        }
        client.send("SINGINFO###" + request);
        if (dataSing2.contains("RESULTINFOCASI###") == true) {
            String[] datas = dataSing2.split("###");
            JSONObject obj = new JSONObject(datas[1]);
            String Success = obj.getString("success");
            if (Success.contains("no") == true) {
                System.out.println("Không tìm thấy thông tin ca sĩ rồi...");
            } else {
                System.out.println("Có tìm thấy thông tin nè" + " " + dataSing2);
            }
        } else {
            JOptionPane.showMessageDialog(null, "infoMessage", "InfoBox: " + "titleBar", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Dữ liệu đang được cập nhật...");
        }


    }

    // show danh sách
    public void show_danh_sach_ca_si(String data) {
        jKhungchuadulieu.removeAll();
        Images img = new Images();
        JPanel jKhungchuadulieus = new JPanel();
        System.out.println("đi tới đây rồi.");


        System.out.println("data:" + data);
        String[] tmp = data.split("###");
        JSONArray arr = new JSONArray(tmp[1]);
        jKhungchuadulieus.setLayout(new GridLayout(arr.length() + 1, 1));
        // hiển thị dữ liệu
        jlabDscasi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlabDscasi.setText("Danh sách " + jLoaitimkiem.getSelectedItem().toString() + ":");
        jKhungchuadulieus.setBackground(Color.red);

        jkhungchuadscasi = new JPanel[arr.length() + 1];
        jlabTencasi = new JLabel[arr.length() + 1];
        jlabValTenCaSi = new JLabel[arr.length() + 1];
        jlabInfo = new JLabel[arr.length() + 1];
        jlabValInfo = new JLabel[arr.length() + 1];
        jbtnXemThongTin = new JButton[arr.length() + 1];
        jpanelImage = new JPanel[arr.length() + 1];
        jpanelImg = new JPanel[arr.length() + 1];
        jKhungchuadulieus.add(jlabDscasi);
        for (int i = 0; i < arr.length(); i++) {
            String name = arr.getJSONObject(i).getString("name");

            String info = arr.getJSONObject(i).getString("info");
            String anhcasi = arr.getJSONObject(i).getString("thumbnail");
            jlabTencasi[i] = new JLabel();
            jlabValTenCaSi[i] = new JLabel();
            jlabInfo[i] = new JLabel();
            jlabValInfo[i] = new JLabel();
            jbtnXemThongTin[i] = new JButton();
            jpanelImage[i] = new JPanel();
            int tam = i;
            jbtnXemThongTin[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jbtnXemThongTinActionPerformed(e, tam);
                }
            });
            jlabTencasi[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jlabTencasi[i].setText("Tên ca sĩ:");

            jlabValTenCaSi[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jlabValTenCaSi[i].setText(name);

            jlabInfo[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jlabInfo[i].setText("Info:");

            jlabValInfo[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jlabValInfo[i].setText(info);

            jbtnXemThongTin[i].setBackground(new java.awt.Color(255, 255, 255));
            jbtnXemThongTin[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jbtnXemThongTin[i].setText("Xem Thông Tin");
            jpanelImg[i] = img.show_image(anhcasi, 100, 100);
            jpanelImage[i].add(jpanelImg[i]);
            javax.swing.GroupLayout jpanelImageLayout = new javax.swing.GroupLayout(jpanelImage[i]);
            jpanelImage[i].setLayout(jpanelImageLayout);
            jpanelImageLayout.setHorizontalGroup(
                    jpanelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 100, Short.MAX_VALUE)
            );
            jpanelImageLayout.setVerticalGroup(
                    jpanelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 100, Short.MAX_VALUE)
            );
            jkhungchuadscasi[i] = new JPanel();

            javax.swing.GroupLayout jkhungchuadscasiLayout;
            jkhungchuadscasiLayout = new GroupLayout(jkhungchuadscasi[i]);
            jkhungchuadscasi[i].setLayout(jkhungchuadscasiLayout);
            jkhungchuadscasiLayout.setHorizontalGroup(
                    jkhungchuadscasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jkhungchuadscasiLayout.createSequentialGroup()
                                    .addComponent(jpanelImage[i], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(41, 41, 41)
                                    .addGroup(jkhungchuadscasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jkhungchuadscasiLayout.createSequentialGroup()
                                                    .addComponent(jlabTencasi[i])
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jlabValTenCaSi[i]))
                                            .addGroup(jkhungchuadscasiLayout.createSequentialGroup()
                                                    .addComponent(jlabInfo[i])
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jlabValInfo[i]))

                                            .addComponent(jbtnXemThongTin[i]))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jkhungchuadscasiLayout.setVerticalGroup(
                    jkhungchuadscasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jkhungchuadscasiLayout.createSequentialGroup()
                                    .addGroup(jkhungchuadscasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jkhungchuadscasiLayout.createSequentialGroup()
                                                    .addGroup(jkhungchuadscasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jlabTencasi[i])
                                                            .addComponent(jlabValTenCaSi[i]))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(jkhungchuadscasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jlabInfo[i])
                                                            .addComponent(jlabValInfo[i]))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)


                                                    .addComponent(jbtnXemThongTin[i])
                                                    .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jkhungchuadscasiLayout.createSequentialGroup()
                                                    .addGap(0, 0, Short.MAX_VALUE)
                                                    .addComponent(jpanelImage[i], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap())
            );
            jKhungchuadulieus.add(jkhungchuadscasi[i]);
        }
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(jKhungchuadulieu);
        jKhungchuadulieu.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jKhungchuadulieus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jKhungchuadulieus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 614, Short.MAX_VALUE))
        );
    }

    public void show_danh_sach_ca_si_tim_kiem(JSONObject obj) {
        jKhungchuadulieu.removeAll();
        System.out.println("đi đến show dscs tim kiem");
        Images img = new Images();
        JPanel jKhungchuadulieus = new JPanel();
        System.out.println("đi tới đây rồi.");


        System.out.println("data:" + dataSing1);

        JSONArray arr = obj.getJSONArray("data");
        jKhungchuadulieus.setLayout(new GridLayout(arr.length() + 1, 1));
        // hiển thị dữ liệu
        jlabDscasi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlabDscasi.setText("Danh sách " + jLoaitimkiem.getSelectedItem().toString() + ":");
        //jKhungchuadulieus.setBackground(Color.red);

        jkhungchuadscasi = new JPanel[arr.length() + 1];
        jlabTencasi = new JLabel[arr.length() + 1];
        jlabValTenCaSi = new JLabel[arr.length() + 1];
        jlabInfo = new JLabel[arr.length() + 1];
        jlabValInfo = new JLabel[arr.length() + 1];
        jntnXemThongTinCaSi = new JButton[arr.length() + 1];
        jpanelImage = new JPanel[arr.length() + 1];
        jpanelImg = new JPanel[arr.length() + 1];
        jKhungchuadulieus.add(jlabDscasi);
        for (int i = 0; i < arr.length(); i++) {
            String name = arr.getString(i);


            jlabTencasi[i] = new JLabel();
            jlabValTenCaSi[i] = new JLabel();
            jlabInfo[i] = new JLabel();
            jlabValInfo[i] = new JLabel();
            jntnXemThongTinCaSi[i] = new JButton();
            jpanelImage[i] = new JPanel();
            int tam = i;
            jntnXemThongTinCaSi[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jbtnXemThongTinTimKiemActionPerformed(e, tam);
                }
            });
            jlabTencasi[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jlabTencasi[i].setText("Tên ca sĩ:");

            jlabValTenCaSi[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jlabValTenCaSi[i].setText(name);

            jlabInfo[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jlabInfo[i].setText("");

            jlabValInfo[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jlabValInfo[i].setText("");

            jntnXemThongTinCaSi[i].setBackground(new java.awt.Color(255, 255, 255));
            jntnXemThongTinCaSi[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jntnXemThongTinCaSi[i].setText("Xem Thông Tin");

            jpanelImage[i] = new JPanel();
            javax.swing.GroupLayout jpanelImageLayout = new javax.swing.GroupLayout(jpanelImage[i]);
            jpanelImage[i].setLayout(jpanelImageLayout);
            jpanelImageLayout.setHorizontalGroup(
                    jpanelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 100, Short.MAX_VALUE)
            );
            jpanelImageLayout.setVerticalGroup(
                    jpanelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 100, Short.MAX_VALUE)
            );
            jkhungchuadscasi[i] = new JPanel();

            javax.swing.GroupLayout jkhungchuadscasiLayout;
            jkhungchuadscasiLayout = new GroupLayout(jkhungchuadscasi[i]);
            jkhungchuadscasi[i].setLayout(jkhungchuadscasiLayout);
            jkhungchuadscasiLayout.setHorizontalGroup(
                    jkhungchuadscasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jkhungchuadscasiLayout.createSequentialGroup()
                                    .addComponent(jpanelImage[i], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(41, 41, 41)
                                    .addGroup(jkhungchuadscasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jkhungchuadscasiLayout.createSequentialGroup()
                                                    .addComponent(jlabTencasi[i])
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jlabValTenCaSi[i]))
                                            .addGroup(jkhungchuadscasiLayout.createSequentialGroup()
                                                    .addComponent(jlabInfo[i])
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jlabValInfo[i]))

                                            .addComponent(jntnXemThongTinCaSi[i]))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jkhungchuadscasiLayout.setVerticalGroup(
                    jkhungchuadscasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jkhungchuadscasiLayout.createSequentialGroup()
                                    .addGroup(jkhungchuadscasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jkhungchuadscasiLayout.createSequentialGroup()
                                                    .addGroup(jkhungchuadscasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jlabTencasi[i])
                                                            .addComponent(jlabValTenCaSi[i]))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(jkhungchuadscasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jlabInfo[i])
                                                            .addComponent(jlabValInfo[i]))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)


                                                    .addComponent(jntnXemThongTinCaSi[i])
                                                    .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jkhungchuadscasiLayout.createSequentialGroup()
                                                    .addGap(0, 0, Short.MAX_VALUE)
                                                    .addComponent(jpanelImage[i], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap())
            );
            jKhungchuadulieus.add(jkhungchuadscasi[i]);
        }
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(jKhungchuadulieu);
        jKhungchuadulieu.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jKhungchuadulieus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jKhungchuadulieus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 614, Short.MAX_VALUE))
        );
    }

    public void show_danh_sach_bai_hat(String data) {
        jKhungchuadulieu.removeAll();
        Images img = new Images();
        JPanel jKhungchuadulieus = new JPanel();
        System.out.println("đi tới đây rồi.");


        System.out.println("data:" + data);
        String[] tmp = data.split("###");
        JSONArray arr = new JSONArray(tmp[1]);
        jKhungchuadulieus.setLayout(new GridLayout(arr.length() + 1, 1));
        // hiển thị dữ liệu
        jlabDscasi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlabDscasi.setText("Danh sách " + jLoaitimkiem.getSelectedItem().toString() + ":");


        jkhungchuadscasi = new JPanel[arr.length() + 1];
        jlabTenBaiHat = new JLabel[arr.length() + 1];
        jlabValTenBaiHat = new JLabel[arr.length() + 1];
        jTencasi = new JLabel[arr.length() + 1];
        jValTenCaSi = new JLabel[arr.length() + 1];
        jlabInfo = new JLabel[arr.length() + 1];
        jlabValInfo = new JLabel[arr.length() + 1];
        jbtnXemThongTin = new JButton[arr.length() + 1];
        jpanelImage = new JPanel[arr.length() + 1];
        jpanelImg = new JPanel[arr.length() + 1];
        jKhungchuadulieus.add(jlabDscasi);
        for (int i = 0; i < arr.length(); i++) {
            String tenbaihat = arr.getJSONObject(i).getString("name");

            String tencasi = arr.getJSONObject(i).getString("artists_names");
            String anhcasi = arr.getJSONObject(i).getString("thumbnail");
            String infocasi = arr.getJSONObject(i).getString("info");
            jlabTenBaiHat[i] = new JLabel();
            jlabValTenBaiHat[i] = new JLabel();
            jTencasi[i] = new JLabel();
            jValTenCaSi[i] = new JLabel();
            jlabInfo[i] = new JLabel();
            jlabValInfo[i] = new JLabel();
            jbtnXemThongTin[i] = new JButton();
            jpanelImage[i] = new JPanel();

            int tam = i;
            jbtnXemThongTin[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jbtnXemThongTinBaiHatActionPerformed(e, tam);
                }
            });
            jlabTenBaiHat[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jlabTenBaiHat[i].setText("Tên bài hát:");

            jlabValTenBaiHat[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jlabValTenBaiHat[i].setText(tenbaihat);

            jlabInfo[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jlabInfo[i].setText("Info:");
            jlabInfo[i].setVisible(false);
            jlabValInfo[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jlabValInfo[i].setText(infocasi);
            jlabValInfo[i].setVisible(false);
            jbtnXemThongTin[i].setBackground(new java.awt.Color(255, 255, 255));
            jbtnXemThongTin[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jbtnXemThongTin[i].setText("Xem Thông Tin");

            jTencasi[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jTencasi[i].setText("Tên ca sĩ:");

            jValTenCaSi[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jValTenCaSi[i].setText(tencasi);
            jpanelImg[i] = img.show_image(anhcasi, 100, 100);
            jpanelImage[i].add(jpanelImg[i]);
            javax.swing.GroupLayout jpanelImageLayout = new javax.swing.GroupLayout(jpanelImage[i]);
            jpanelImage[i].setLayout(jpanelImageLayout);
            jpanelImageLayout.setHorizontalGroup(
                    jpanelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 100, Short.MAX_VALUE)
            );
            jpanelImageLayout.setVerticalGroup(
                    jpanelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 100, Short.MAX_VALUE)
            );
            jkhungchuadscasi[i] = new JPanel();
            javax.swing.GroupLayout jkhungchuadscasiLayout = new javax.swing.GroupLayout(jkhungchuadscasi[i]);
            jkhungchuadscasi[i].setLayout(jkhungchuadscasiLayout);
            jkhungchuadscasiLayout.setHorizontalGroup(
                    jkhungchuadscasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jkhungchuadscasiLayout.createSequentialGroup()
                                    .addComponent(jpanelImage[i], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(41, 41, 41)
                                    .addGroup(jkhungchuadscasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jkhungchuadscasiLayout.createSequentialGroup()
                                                    .addComponent(jlabTenBaiHat[i])
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jlabValTenBaiHat[i]))
                                            .addGroup(jkhungchuadscasiLayout.createSequentialGroup()
                                                    .addComponent(jlabInfo[i])
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jlabValInfo[i]))
                                            .addGroup(jkhungchuadscasiLayout.createSequentialGroup()
                                                    .addComponent(jTencasi[i])
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jValTenCaSi[i]))
                                            .addComponent(jbtnXemThongTin[i]))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jkhungchuadscasiLayout.setVerticalGroup(
                    jkhungchuadscasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jkhungchuadscasiLayout.createSequentialGroup()
                                    .addGroup(jkhungchuadscasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jkhungchuadscasiLayout.createSequentialGroup()
                                                    .addGroup(jkhungchuadscasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jlabTenBaiHat[i])
                                                            .addComponent(jlabValTenBaiHat[i]))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(jkhungchuadscasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jlabInfo[i])
                                                            .addComponent(jlabValInfo[i]))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(jkhungchuadscasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jTencasi[i])
                                                            .addComponent(jValTenCaSi[i]))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jbtnXemThongTin[i])
                                                    .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jkhungchuadscasiLayout.createSequentialGroup()
                                                    .addGap(0, 0, Short.MAX_VALUE)
                                                    .addComponent(jpanelImage[i], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap())
            );
            jKhungchuadulieus.add(jkhungchuadscasi[i]);
        }
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(jKhungchuadulieu);
        jKhungchuadulieu.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jKhungchuadulieus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jKhungchuadulieus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 614, Short.MAX_VALUE))
        );
    }

    public void showThongTinBaiHat(String data) {
        Images img = new Images();
        jKhungchuadulieu.removeAll();
        System.out.println("data:" + data);
        String[] tmp = data.split("###");
        JSONObject obj = new JSONObject(tmp[1]).getJSONObject("data");
        String TT_Tenbaihat = obj.getString("name");
        String TT_Audio = obj.getString("id");
        String TT_Loibaihat = obj.getString("lyrics");
        String TT_Tencasi = obj.getString("singerName");
        //String TT_anh = obj.getString("singerThumbnail");
        //String TT_Tennhacsi = obj.getString("composerName");
        jlabTT = new javax.swing.JLabel();
        jlabTT_TenBaiHat = new javax.swing.JLabel();
        jalbTT_Val_TenBaiHat = new javax.swing.JLabel();
        jlabTT_Ten_Ca_Si = new javax.swing.JLabel();
        jlabTT_Val_Ten_Ca_Si = new javax.swing.JLabel();
        jlabTT_Ten_Nhac_Si = new javax.swing.JLabel();
        jlabTT_Val_Ten_Nhac_Si = new javax.swing.JLabel();
        jlabTT_Audio = new javax.swing.JLabel();
        jpanelTT_Val_Audio = new javax.swing.JPanel();
        jlabTT_Loi_Bai_Hat = new javax.swing.JLabel();
        jpanelTT_Loi_Bai_Hat = new javax.swing.JPanel();
        jpanelTT_Image = new javax.swing.JPanel();
        jbtnTT_Back = new javax.swing.JButton();


        jlabTT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlabTT.setText("Thông tin bài hát:");

        jlabTT_TenBaiHat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlabTT_TenBaiHat.setText("Tên bài hát:");

        jalbTT_Val_TenBaiHat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jalbTT_Val_TenBaiHat.setText(TT_Tenbaihat);

        jlabTT_Ten_Ca_Si.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlabTT_Ten_Ca_Si.setText("Tên ca sĩ :");

        jlabTT_Val_Ten_Ca_Si.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlabTT_Val_Ten_Ca_Si.setText(TT_Tencasi);

        jlabTT_Ten_Nhac_Si.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlabTT_Ten_Nhac_Si.setText("Tên nhạc sĩ:");

        jlabTT_Val_Ten_Nhac_Si.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        //jlabTT_Val_Ten_Nhac_Si.setText(TT_Tennhacsi);

        jlabTT_Audio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlabTT_Audio.setText("Audio:");

        JFXPanel jfxPanel = new JFXPanel();

        Platform.runLater(() -> {
            WebView webView = new WebView();
            jfxPanel.setScene(new Scene(webView));
            webView.getEngine().load(TT_Audio);
        });
        JPanel pnt= new JPanel();
        javax.swing.GroupLayout jpanelTT_Val_AudioLayout = new javax.swing.GroupLayout(jpanelTT_Val_Audio);
        jpanelTT_Val_Audio.setLayout(jpanelTT_Val_AudioLayout);
        jpanelTT_Val_AudioLayout.setHorizontalGroup(
                jpanelTT_Val_AudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 452, Short.MAX_VALUE)
                        .addComponent(jfxPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );
        jpanelTT_Val_AudioLayout.setVerticalGroup(
                jpanelTT_Val_AudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 45, Short.MAX_VALUE)
                        .addComponent(jfxPanel,javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );

        jlabTT_Loi_Bai_Hat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlabTT_Loi_Bai_Hat.setText("Lời bài hát:");
        String[] TT_Loibaihat_tmp = TT_Loibaihat.split(",");
        System.out.println(TT_Loibaihat_tmp.length);
        jPanelTT_Loibaihat_tmp = new JPanel();

        jPanelTT_Loibaihat_tmp.setLayout(new GridLayout(TT_Loibaihat_tmp.length, 1));
        jLabelTT_Loibaihat_tmp = new JLabel[TT_Loibaihat_tmp.length];
        for (int i = 0; i < TT_Loibaihat_tmp.length; i++) {
            jLabelTT_Loibaihat_tmp[i] = new JLabel(TT_Loibaihat_tmp[i]);
            jLabelTT_Loibaihat_tmp[i].setFont(new java.awt.Font("Tahoma", 0, 14));
            jPanelTT_Loibaihat_tmp.add(jLabelTT_Loibaihat_tmp[i]);
        }


        jpanelTT_Loi_Bai_Hat.add(jPanelTT_Loibaihat_tmp);


        javax.swing.GroupLayout jpanelTT_Loi_Bai_HatLayout = new javax.swing.GroupLayout(jpanelTT_Loi_Bai_Hat);
        jpanelTT_Loi_Bai_Hat.setLayout(jpanelTT_Loi_Bai_HatLayout);
        jpanelTT_Loi_Bai_HatLayout.setHorizontalGroup(
                jpanelTT_Loi_Bai_HatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanelTT_Loibaihat_tmp)
        );
        jpanelTT_Loi_Bai_HatLayout.setVerticalGroup(
                jpanelTT_Loi_Bai_HatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 258, Short.MAX_VALUE)
                        .addComponent(jPanelTT_Loibaihat_tmp)
        );
        //jScrollPaneTT_Loibaihat = new JScrollPane(jpanelTT_Loi_Bai_Hat);
        //jpanelTT_Image_tmp = img.show_image(TT_anh, 110, 110);
        //jpanelTT_Image.add(jpanelTT_Image_tmp);
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jpanelTT_Image);
        jpanelTT_Image.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 123, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 112, Short.MAX_VALUE)
        );

        jbtnTT_Back.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbtnTT_Back.setText("Back");
        jbtnTT_Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show_danh_sach_bai_hat(dataSong1);
            }
        });
        javax.swing.GroupLayout jKhungchuadulieuLayout = new javax.swing.GroupLayout(jKhungchuadulieu);
        jKhungchuadulieu.setLayout(jKhungchuadulieuLayout);
        jKhungchuadulieuLayout.setHorizontalGroup(
                jKhungchuadulieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jKhungchuadulieuLayout.createSequentialGroup()
                                .addGroup(jKhungchuadulieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jKhungchuadulieuLayout.createSequentialGroup()
                                                .addGap(52, 52, 52)
                                                .addComponent(jpanelTT_Loi_Bai_Hat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jKhungchuadulieuLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jKhungchuadulieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jKhungchuadulieuLayout.createSequentialGroup()
                                                                .addComponent(jlabTT_Audio)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jpanelTT_Val_Audio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jKhungchuadulieuLayout.createSequentialGroup()
                                                                .addComponent(jpanelTT_Image, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jKhungchuadulieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jKhungchuadulieuLayout.createSequentialGroup()
                                                                                .addComponent(jlabTT_Ten_Ca_Si)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jlabTT_Val_Ten_Ca_Si))
                                                                        .addGroup(jKhungchuadulieuLayout.createSequentialGroup()
                                                                                .addComponent(jlabTT_TenBaiHat)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jalbTT_Val_TenBaiHat))
                                                                        .addGroup(jKhungchuadulieuLayout.createSequentialGroup()
                                                                                .addComponent(jlabTT_Ten_Nhac_Si)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jlabTT_Val_Ten_Nhac_Si))))
                                                        .addComponent(jlabTT_Loi_Bai_Hat)
                                                        .addComponent(jlabTT))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(jKhungchuadulieuLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jbtnTT_Back)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jKhungchuadulieuLayout.setVerticalGroup(
                jKhungchuadulieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jKhungchuadulieuLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbtnTT_Back)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlabTT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jKhungchuadulieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jKhungchuadulieuLayout.createSequentialGroup()
                                                .addGroup(jKhungchuadulieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jlabTT_TenBaiHat)
                                                        .addComponent(jalbTT_Val_TenBaiHat))
                                                .addGap(18, 18, 18)
                                                .addGroup(jKhungchuadulieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jlabTT_Ten_Ca_Si)
                                                        .addComponent(jlabTT_Val_Ten_Ca_Si))
                                                .addGap(18, 18, 18)
                                                .addGroup(jKhungchuadulieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jlabTT_Ten_Nhac_Si)
                                                        .addComponent(jlabTT_Val_Ten_Nhac_Si)))
                                        .addComponent(jpanelTT_Image, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jKhungchuadulieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jpanelTT_Val_Audio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlabTT_Audio))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlabTT_Loi_Bai_Hat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jpanelTT_Loi_Bai_Hat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
    }

    public void init() {
        OpenUI();

        jFrame.setSize(1200, 600);
        jFrame.setVisible(true);
    }

    private javax.swing.JPanel jKhungchuadulieu;
    private javax.swing.JPanel jKhungpanel;
    private javax.swing.JComboBox<String> jLoaitimkiem;
    private javax.swing.JLabel jTieude;
    private javax.swing.JButton[] jbtnXemThongTin;
    private JButton[] jntnXemThongTinCaSi;
    private javax.swing.JButton jbuttonTimkiem;
    private javax.swing.JPanel[] jkhungchuadscasi;
    private javax.swing.JLabel jlabDscasi;
    private javax.swing.JLabel[] jlabInfo;
    private javax.swing.JLabel[] jlabTencasi;
    private javax.swing.JLabel[] jlabValInfo;
    private javax.swing.JLabel[] jlabValTenCaSi;
    private javax.swing.JTextField jnoidungtimkiem;
    private JScrollPane jScrollPane;
    private JLabel[] jlabTenBaiHat;
    private JLabel[] jlabValTenBaiHat;
    private JLabel[] jTencasi;
    private JLabel[] jValTenCaSi;
    private JPanel[] jpanelImage;
    private JPanel[] jpanelImg;
    private JPanel jpanelLoadDing;
    private JPanel jpanelLoad;
    private JFrame jFrame2;
    private javax.swing.JLabel jalbTT_Val_TenBaiHat;
    private javax.swing.JButton jbtnTT_Back;
    private javax.swing.JLabel jlabTT;
    private javax.swing.JLabel jlabTT_Audio;
    private javax.swing.JLabel jlabTT_Loi_Bai_Hat;
    private javax.swing.JLabel jlabTT_TenBaiHat;
    private javax.swing.JLabel jlabTT_Ten_Ca_Si;
    private javax.swing.JLabel jlabTT_Ten_Nhac_Si;
    private javax.swing.JLabel jlabTT_Val_Ten_Ca_Si;
    private javax.swing.JLabel jlabTT_Val_Ten_Nhac_Si;
    private javax.swing.JPanel jpanelTT_Loi_Bai_Hat;
    private javax.swing.JPanel jpanelTT_Val_Audio;
    private JPanel jpanelTT_Image;
    private JPanel jpanelTT_Image_tmp;
    private JPanel jPanelTT_Loibaihat_tmp;
    private JScrollPane jScrollPaneTT_Loibaihat;
    private JLabel[] jLabelTT_Loibaihat_tmp;
    private JLabel load;
    private JPanel getjKhungchuadulieu_tmp;
}
