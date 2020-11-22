package musicinformationclient;

import musicinformationclient.socket.Images;
import musicinformationclient.socket.SocketClient;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;
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
    String dataSing1="";
    String dataSing2="";
    String dataSong1="";
    String dataSong2="";
    String requestCuaHai="";
    JFrame jFrame = new JFrame();
    @Override
    public void result(String mes) {

        System.out.println("mess là"+mes);
        String[] tmp = mes.split("###");
        if(tmp[0].contains("RESULTSEARCHCASI"))
        {
            dataSing1=mes;

        }
        if(tmp[0].contains("RESULTSEARCHBAIHAT"))
        {
            dataSong1=mes;

        }
        if(tmp[0].contains("RESULTINFOCASI"))
        {
            dataSing2=mes;

        }
        if(tmp[0].contains("RESULTINFOBAIHAT"))
        {
            dataSing2=mes;

        }

    }

    @Override
    public void closed() {

    }
    public static void main(String[] args)
    {
        ok oke= new ok();
        oke.init();
    }
    public void OpenUI()
    {

        jKhungpanel = new javax.swing.JPanel();
        jTieude = new javax.swing.JLabel();
        jLoaitimkiem = new javax.swing.JComboBox<>();
        jnoidungtimkiem = new javax.swing.JTextField();
        jbuttonTimkiem = new javax.swing.JButton();
        jKhungchuadulieu = new javax.swing.JPanel();
        jKhungchuadulieu.setBackground(Color.yellow);

        jpanelLoad = new JPanel();
        jpanelLoad.setBackground(Color.red);
        jpanelLoad.setSize(300,400);
        jFrame.add(jpanelLoad);
        jpanelLoad.setVisible(false);
        jlabDscasi = new javax.swing.JLabel();

        jFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jKhungpanel.setBackground(new java.awt.Color(255, 255, 255));

        jTieude.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jTieude.setText("Tìm kiếm thông tin bài hát");

        jLoaitimkiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLoaitimkiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên ca sĩ", "Bài hát" }));

        jbuttonTimkiem.setBackground(new java.awt.Color(255, 255, 255));
        //jbuttonTimkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/magnifying-glass.png"))); // NOI18N
        jbuttonTimkiem.setText("Tìm kiếm");
        jbuttonTimkiem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                jButton1ActionPerformed(e);
            }
        });





        jScrollPane = new JScrollPane(jKhungchuadulieu);
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
                                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
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
                                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(300, 300, 300))
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
private void jButton1ActionPerformed(java.awt.event.ActionEvent e)
{

    jKhungchuadulieu.removeAll();
    String loaitk= jLoaitimkiem.getSelectedItem().toString();
    if(loaitk.contains("Tên ca sĩ") == true)
    {   //Images load= new Images();


        //jpanelLoadDing = load.show_image("https://media2.giphy.com/media/3oEjI6SIIHBdRxXI40/giphy.gif",200,200);
        //jpanelLoad.add(jpanelLoadDing);


        show_danh_sach_ca_si();
    }
    else
        {
            /*JPanel jKhungchuadulieus = new JPanel();
            System.out.println("đi tới đây rồi.");

            String senddata=jnoidungtimkiem.getText();
            client.send("SiNGS###"+senddata);
            System.out.println("data:"+dataSong1);*/
            show_danh_sach_bai_hat();


    }




}
    private void jbtnXemThongTinActionPerformed(java.awt.event.ActionEvent e,int i)
    {
        String data = jlabValInfo[i].getText();
        String [] tmp=data.split("=");
        client.send("SINGINFO###"+tmp[1]);
        String [] datas =dataSing2.split("###");
        JSONObject obj = new JSONObject(datas[1]);

        String Success= obj.getString("success");
        if(Success.contains("no") == true )
        {JSONArray arr=obj.getJSONArray("data");
            if(arr.length() ==0)
            {
                System.out.println("Dữ liệu đang được cập nhật thử lại sau ạ.");
            }
            else {
                System.out.println("list nè "+dataSing2);
            }

        }
        if(Success.contains("yes") == true) {
            System.out.println("list tim kiếm là" + dataSing2);
        }
        System.out.println("info ca sĩ"+dataSing2);
    }

    private void jbtnXemThongTinBaiHatActionPerformed(java.awt.event.ActionEvent e,int i)
    {
        String data = jlabValInfo[i].getText();
        String [] tmp=data.split("=");
        client.send("SONGINFO###"+tmp[1]);
        System.out.println("info ca sĩ"+dataSong2);
    }
    public void show_danh_sach_ca_si()
    {

        Images img=new Images();
        JPanel jKhungchuadulieus = new JPanel();
        System.out.println("đi tới đây rồi.");

        String senddata=jnoidungtimkiem.getText();
        client.send("SINGS###"+senddata);
        System.out.println("data:"+dataSing1);
        String [] tmp = dataSing1.split("###");
        JSONArray arr = new JSONArray(tmp[1]);
        jKhungchuadulieus.setLayout(new GridLayout(arr.length()+1,1));
        // hiển thị dữ liệu
        jlabDscasi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlabDscasi.setText("Danh sách "+jLoaitimkiem.getSelectedItem().toString()+":");
        jKhungchuadulieus.setBackground(Color.red);

        jkhungchuadscasi = new JPanel[arr.length()+1];
        jlabTencasi = new JLabel[arr.length()+1];
        jlabValTenCaSi = new JLabel[arr.length()+1];
        jlabInfo = new JLabel[arr.length()+1];
        jlabValInfo = new JLabel[arr.length()+1];
        jbtnXemThongTin = new JButton[arr.length()+1];
        jpanelImage=new JPanel[arr.length()+1];
        jpanelImg = new JPanel[arr.length()+1];
        jKhungchuadulieus.add(jlabDscasi);
        for(int i=0;i<arr.length();i++)
        {
            String name = arr.getJSONObject(i).getString("name");

            String info = arr.getJSONObject(i).getString("info");
            String anhcasi= arr.getJSONObject(i).getString("thumbnail");
            jlabTencasi[i] = new JLabel();
            jlabValTenCaSi[i] = new JLabel();
            jlabInfo[i] = new JLabel();
            jlabValInfo[i] = new JLabel();
            jbtnXemThongTin[i] = new JButton();
            jpanelImage[i] = new JPanel();
            int tam=i;
            jbtnXemThongTin[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jbtnXemThongTinActionPerformed(e,tam);
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
            jpanelImg[i]=img.show_image(anhcasi,100,100);
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
    public void show_danh_sach_bai_hat()
    {
        Images img=new Images();
        JPanel jKhungchuadulieus = new JPanel();
        System.out.println("đi tới đây rồi.");

        String senddata=jnoidungtimkiem.getText();
        client.send("SONGS###"+senddata);
        System.out.println("data:"+dataSong1);
        String [] tmp = dataSong1.split("###");
        JSONArray arr = new JSONArray(tmp[1]);
        jKhungchuadulieus.setLayout(new GridLayout(arr.length()+1,1));
        // hiển thị dữ liệu
        jlabDscasi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlabDscasi.setText("Danh sách "+jLoaitimkiem.getSelectedItem().toString()+":");
        jKhungchuadulieus.setBackground(Color.red);

        jkhungchuadscasi = new JPanel[arr.length()+1];
        jlabTenBaiHat = new JLabel[arr.length()+1];
        jlabValTenBaiHat = new JLabel[arr.length()+1];
        jTencasi = new JLabel[arr.length()+1];
        jValTenCaSi = new JLabel[arr.length()+1];
        jlabInfo = new JLabel[arr.length()+1];
        jlabValInfo = new JLabel[arr.length()+1];
        jbtnXemThongTin = new JButton[arr.length()+1];
        jpanelImage=new JPanel[arr.length()+1];
        jpanelImg = new JPanel[arr.length()+1];
        jKhungchuadulieus.add(jlabDscasi);
        for(int i=0;i<arr.length();i++)
        {
            String tenbaihat = arr.getJSONObject(i).getString("title");
            JSONArray tencasis=arr.getJSONObject(i).getJSONArray("artists");
            String tencasi = "";
            String anhcasi="";
            for(int j=0;j<tencasis.length();j++)
            {
                tencasi+= tencasis.getJSONObject(j).getString("name")+"  ";

                anhcasi= tencasis.getJSONObject(j).getString("thumbnail");
            }
            String info = "";
            jlabTenBaiHat[i] = new JLabel();
            jlabValTenBaiHat[i] = new JLabel();
            jTencasi[i] = new JLabel();
            jValTenCaSi[i] = new JLabel();
            jlabInfo[i] = new JLabel();
            jlabValInfo[i] = new JLabel();
            jbtnXemThongTin[i] = new JButton();
            jpanelImage[i] = new JPanel();

            int tam=i;
            jbtnXemThongTin[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jbtnXemThongTinBaiHatActionPerformed(e,tam);
                }
            });
            jlabTenBaiHat[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jlabTenBaiHat[i].setText("Tên bài hát:");

            jlabValTenBaiHat[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jlabValTenBaiHat[i].setText(tenbaihat);

            jlabInfo[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jlabInfo[i].setText("Info:");

            jlabValInfo[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jlabValInfo[i].setText("Info ghi ở đây.");

            jbtnXemThongTin[i].setBackground(new java.awt.Color(255, 255, 255));
            jbtnXemThongTin[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jbtnXemThongTin[i].setText("Xem Thông Tin");

            jTencasi[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jTencasi[i].setText("Tên ca sĩ");

            jValTenCaSi[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jValTenCaSi[i].setText(tencasi);
            jpanelImg[i]=img.show_image(anhcasi,100,100);
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
    public void init ()
    {
        OpenUI();

        jFrame.setSize(1200,600);
        jFrame.setVisible(true);
    }
    private javax.swing.JPanel jKhungchuadulieu;
    private javax.swing.JPanel jKhungpanel;
    private javax.swing.JComboBox<String> jLoaitimkiem;
    private javax.swing.JLabel jTieude;
    private javax.swing.JButton [] jbtnXemThongTin;
    private javax.swing.JButton jbuttonTimkiem;
    private javax.swing.JPanel [] jkhungchuadscasi;
    private javax.swing.JLabel jlabDscasi;
    private javax.swing.JLabel [] jlabInfo;
    private javax.swing.JLabel [] jlabTencasi;
    private javax.swing.JLabel [] jlabValInfo;
    private javax.swing.JLabel [] jlabValTenCaSi;
    private javax.swing.JTextField jnoidungtimkiem;
    private  JScrollPane jScrollPane;
    private JLabel [] jlabTenBaiHat;
    private JLabel [] jlabValTenBaiHat;
    private JLabel [] jTencasi;
    private JLabel [] jValTenCaSi;
    private JPanel [] jpanelImage;
    private JPanel [] jpanelImg;
    private JPanel jpanelLoadDing;
    private JPanel jpanelLoad;
    private JFrame jFrame2;
}
