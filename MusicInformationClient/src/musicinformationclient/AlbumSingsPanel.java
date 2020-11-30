/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicinformationclient;

import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.swing.internal.plaf.basic.resources.basic_it;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import musicinformationclient.socket.WebView;
import org.json.JSONArray;

/**
 *
 * @author Administrator
 */
public class AlbumSingsPanel extends javax.swing.JPanel {


    /**
     * Creates new form AlbumSingsPanel
     */
    public AlbumSingsPanel() {
        initComponents();
    }
    public AlbumSingsPanel(String name, String info,Event event)  {

        System.out.println("đi tới chỗ này 4" + info);
        System.out.println("đi tới chỗ này 4" + name);

        initComponents();
        loadThongTinCaSi(name, info);
        
    }
    public AlbumSingsPanel(JSONArray array) {
        System.out.println("đi tới chỗ này 5");
        initComponents();
        loadAlbum(array);
    }
    public AlbumSingsPanel(String tenAlbum ,JSONArray array , String add) {
        System.out.println("đi tới chỗ này 6");
        initComponents();
        add6Album(tenAlbum,array,add);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTenAlbum = new javax.swing.JLabel();
        jPanelDanhSachAlBum = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabelTenAlbum.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTenAlbum.setText("jLabel1");

        jPanelDanhSachAlBum.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelDanhSachAlBumLayout = new javax.swing.GroupLayout(jPanelDanhSachAlBum);
        jPanelDanhSachAlBum.setLayout(jPanelDanhSachAlBumLayout);
        jPanelDanhSachAlBumLayout.setHorizontalGroup(
            jPanelDanhSachAlBumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelDanhSachAlBumLayout.setVerticalGroup(
            jPanelDanhSachAlBumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 357, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelDanhSachAlBum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTenAlbum)
                .addContainerGap(550, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabelTenAlbum)
                .addGap(5, 5, 5)
                .addComponent(jPanelDanhSachAlBum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelTenAlbum;
    private javax.swing.JPanel jPanelDanhSachAlBum;
    // End of variables declaration//GEN-END:variables
private void loadThongTinCaSi(String name, String info) {
        //jPanelDanhSachAlBum.removeAll();
        String [] tmp = info.split(",");
        JPanel jPanel_tmp_thongtin = new JPanel();

        jPanel_tmp_thongtin.setLayout(new GridLayout(tmp.length+2, 1));
        JLabel  jlabname = new JLabel("Tên ca sĩ:"+name);
        jlabname.setFont(new java.awt.Font("Tahoma", 0, 14));
        jPanel_tmp_thongtin.add(jlabname);
        JLabel [] jlabtt = new JLabel[tmp.length+2];
        System.out.println("legth"+tmp.length);
        for (int i = 0; i < tmp.length; i++) {
            System.out.println("xuat ra "+i+"||"+tmp[i]);
            jlabtt[i]=new JLabel(tmp[i]);
            jlabtt[i].setFont(new java.awt.Font("Tahoma", 0, 14));
            jPanel_tmp_thongtin.add(jlabtt[i]);
        }
        jLabelTenAlbum.setText("Thông tin ca sĩ:");

    jPanelDanhSachAlBum.removeAll();
    jPanelDanhSachAlBum.setLayout(new GridLayout(1,1));
    jPanelDanhSachAlBum.add(jPanel_tmp_thongtin);

    jPanelDanhSachAlBum.validate();
    jPanelDanhSachAlBum.repaint();
    }
    private  void add6Album(String tenAlbum,JSONArray arr, String add)
    {

        int length=6;
        if(arr.length()<6)
        {
            length=arr.length();
        }
        JPanel jPanel_tmp_add_Al=new JPanel();
        jPanel_tmp_add_Al.setSize(400,600);
        jPanel_tmp_add_Al.setLayout(new GridLayout(length,1));

        for(int i=0;i<length;i++)
        { String link = arr.getJSONObject(i).getString("audio");
            JPanel jPanel = new JPanel();
//            jPanel.add(new BaiHatCuaAlBumPanel(arr.getJSONObject(i).getString("audio")));
             System.out.println("out"+arr.getJSONObject(i).getString("audio"));
            JFXPanel jfxPanel = new JFXPanel();

            Platform.runLater(() -> {
                javafx.scene.web.WebView webView = new javafx.scene.web.WebView();
                jfxPanel.setScene(new Scene(webView));
                webView.getEngine().load(link);
            });
            //jPanel.setSize(200,50);
            //jPanel.add(jfxPanel);
            jfxPanel.setSize(200,50);
            jPanel_tmp_add_Al.add(jfxPanel);
        }
        jLabelTenAlbum.setText(tenAlbum);
        jPanelDanhSachAlBum.removeAll();
        jPanelDanhSachAlBum.setLayout(new GridLayout(1,1));
        jPanelDanhSachAlBum.add(jPanel_tmp_add_Al);

        jPanelDanhSachAlBum.validate();
        jPanelDanhSachAlBum.repaint();

    }
private void loadAlbum (JSONArray arr)
{
    JPanel jPanel_tmp_loadALbum =new JPanel();

    int length=6;
    if(arr.length()<6)
    {
        length=arr.length();
    }
    jPanel_tmp_loadALbum.setLayout(new GridLayout(length,1));
    for(int i=0;i<length;i++)
    {

        JPanel jPanel=new AlbumSingsPanel(arr.getJSONObject(i).getString("name"),arr.getJSONObject(i).getJSONArray("songs"),"ok");
        jPanel_tmp_loadALbum.add(jPanel);
    }
    jLabelTenAlbum.setText("Album: ");
    jPanelDanhSachAlBum.removeAll();
    jPanelDanhSachAlBum.setLayout(new GridLayout(1,1));
    jPanelDanhSachAlBum.add(jPanel_tmp_loadALbum);

    jPanelDanhSachAlBum.validate();
    jPanelDanhSachAlBum.repaint();

        
}
    private void loadData() {

    }
}
