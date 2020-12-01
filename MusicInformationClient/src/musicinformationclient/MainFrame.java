/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicinformationclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import musicinformationclient.socket.Images;
import musicinformationclient.socket.SocketClient;

/**
 *
 * @author Administrator
 */
public class MainFrame extends javax.swing.JFrame implements SocketClient.Result, singerPanel.Event,listResultPanel.eventClick,Jpanel1BaiHat.SendBack{

    /**
     * Creates new form MainFrame
     */
    SocketClient client = new SocketClient("127.0.0.1", 5000, this);
    String dataSing1 = "";
    String dataSing2 = "";
    String dataSong1 = "";
    String dataSong2 = "";
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
    public MainFrame() {
        Images img=new Images();
        initComponents(); 
       // URL url = getClass().getResource("/musicinformationclient/img/200.gif");
        
       //Load = new JLabel();
       //Icon icon = new ImageIcon(getClass().getResource( "/musicinformationclient/img/200.gif" ));
       //Load.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musicinformationclient/img/200.gif")));
        //Load = new JLabel(icon);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLoaiTimKiem = new javax.swing.JComboBox<>();
        searchTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        resultPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLoaiTimKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLoaiTimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên ca sĩ", "Tên bài hát" }));

        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Thông tin ca sĩ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(301, 301, 301)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jLoaiTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLoaiTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(searchTextField))
                .addGap(22, 22, 22))
        );

        resultPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout resultPanelLayout = new javax.swing.GroupLayout(resultPanel);
        resultPanel.setLayout(resultPanelLayout);
        resultPanelLayout.setHorizontalGroup(
            resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        resultPanelLayout.setVerticalGroup(
            resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 293, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(resultPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
        btnSearchText();
    }//GEN-LAST:event_searchTextFieldActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
         
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox<String> jLoaiTimKiem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel resultPanel;
    private javax.swing.JTextField searchTextField;
    // End of variables declaration//GEN-END:variables
    private JLabel Load;
    private JPanel Loading;
    private void btnSearchText()
    {
        String loaitk = jLoaiTimKiem.getSelectedItem().toString();
        if (loaitk.contains("Tên ca sĩ") == true) {   //Images load= new Images();

            resultPanel.removeAll();
            System.out.println("1");
            //jpanelLoadDing = load.show_image("https://media2.giphy.com/media/3oEjI6SIIHBdRxXI40/giphy.gif",200,200);
            //jpanelLoad.add(jpanelLoadDing);
            
            String senddata = searchTextField.getText();
            client.send("SINGS###" + senddata);
           
            loadData(dataSing1);
            System.out.println("data Sing "+dataSing1);
            
            

        } else {
            /*JPanel jKhungchuadulieus = new JPanel();
            System.out.println("đi tới đây rồi.");

            String senddata=jnoidungtimkiem.getText();
            client.send("SiNGS###"+senddata);
            System.out.println("data:"+dataSong1);*/
            resultPanel.removeAll();
            String senddata = searchTextField.getText();
            client.send("SONGS###" + senddata);
            loadData(dataSong1);
            //loadData(dataSing1);
            System.out.println("data Song "+ dataSong1);

        }
    }
    public void loadInfoSingData(String inputData)
    {
        //JOptionPane.showMessageDialog(null, inputData, "InfoBox: " + "titleBar", JOptionPane.INFORMATION_MESSAGE);
        resultPanel.removeAll();
        client.send("SINGINFO###"+inputData);
        System.out.println("data"+dataSing2);
        loadData(dataSing2);

        
    }
    private void loadData(String inputString) {
        
        listResultPanel listpanel = new listResultPanel(this,this,this);
        listpanel.initSinger(inputString);
        resultPanel.removeAll();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.add(new JScrollPane(listpanel) );
        resultPanel.validate();
    }

    @Override
    public void send(String data) {
        loadInfoSingData(data);
    }

    @Override
    public void sendClick() {
        loadData(dataSing1);
    }

    @Override
    public void sendBackInfoCasi() {
        loadData(dataSing2);
    }
}
