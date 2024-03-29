
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DefaultCaret;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author qq
 */
public class Client extends javax.swing.JFrame {

    static DataInputStream dis;
    static DataOutputStream dos;
    static Socket socket;
    static String tosend;
    static String[] crypt_list_tags = new String[3];

    public Client() {
        initComponents();
        jta_chat.setEditable(false);
        DefaultCaret caret = (DefaultCaret) jta_chat.getCaret();
        caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
        try {
            InetAddress ip = InetAddress.getByName("localhost");
            socket = new Socket(ip, 5056);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String message;
                    while (true) {
                        try {
                            message = dis.readUTF();
                            jta_chat.setText(jta_chat.getText() + "\n" + message);
                        } catch (IOException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String cryptTagAdd(String tosend) {
        for (int i = crypt_list_tags.length - 1; i >= 0; i--) {
            if (crypt_list_tags[i] != null && !crypt_list_tags[i].equals("")) {
                tosend = crypt_list_tags[i] + tosend;
            }
        }
        for (int i = 0; i < crypt_list_tags.length; i++) {
            crypt_list_tags[i] = null;
        }

        return tosend;
    }

    public void toSend() {
        tosend = jtf_text.getText();
        if (jcb_kripCeasar.isSelected()) {
            tosend = Crpt.Ceasar(tosend);
            crypt_list_tags[0] = "#ce/";
        }
        if (jcb_kripPicket.isSelected()) {
            tosend = Crpt.Picket(tosend);
            crypt_list_tags[1] = "#pi/";
        }
        if (jcb_kripPolybius.isSelected()) {
            tosend = Crpt.Polybius(tosend);
            crypt_list_tags[2] = "#po/";
        }       
        tosend = cryptTagAdd(tosend);
        try {
            dos.writeUTF(tosend);
            if (tosend.equals("exit")) {
                dis.close();
                dos.close();
                socket.close();
                System.exit(0);
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jta_chat = new javax.swing.JTextArea();
        jtf_text = new javax.swing.JTextField();
        btn_send = new javax.swing.JButton();
        jtf_username = new javax.swing.JTextField();
        jlbl_username = new javax.swing.JLabel();
        btn_changeName = new javax.swing.JButton();
        jlbl_sifretext = new javax.swing.JLabel();
        jcb_kripCeasar = new javax.swing.JCheckBox();
        jcb_kripPolybius = new javax.swing.JCheckBox();
        jcb_kripPicket = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client");

        jta_chat.setColumns(20);
        jta_chat.setRows(5);
        jScrollPane1.setViewportView(jta_chat);

        jtf_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtf_textKeyPressed(evt);
            }
        });

        btn_send.setText("Gönder");
        btn_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sendActionPerformed(evt);
            }
        });

        jlbl_username.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        jlbl_username.setText("Kullanıcı Adı");

        btn_changeName.setText("Değiştir");
        btn_changeName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_changeNameActionPerformed(evt);
            }
        });

        jlbl_sifretext.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        jlbl_sifretext.setText("Şifrelemeler");

        jcb_kripCeasar.setText("Ceasar");

        jcb_kripPolybius.setText("Polybius");

        jcb_kripPicket.setText("Picket Fence");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jtf_text)
                        .addGap(18, 18, 18)
                        .addComponent(btn_send, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtf_username)
                            .addComponent(jlbl_sifretext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_changeName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbl_username)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jcb_kripCeasar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jcb_kripPolybius, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)))
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jcb_kripPicket)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtf_text)
                    .addComponent(btn_send, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jlbl_username)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtf_username, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_changeName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jlbl_sifretext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcb_kripCeasar)
                            .addComponent(jcb_kripPolybius))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcb_kripPicket)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sendActionPerformed
        toSend();
    }//GEN-LAST:event_btn_sendActionPerformed

    private void jtf_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_textKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            toSend();
        }
    }//GEN-LAST:event_jtf_textKeyPressed

    private void btn_changeNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_changeNameActionPerformed
        tosend = "/name " + jtf_username.getText();
        try {
            dos.writeUTF(tosend);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_changeNameActionPerformed

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
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_changeName;
    private javax.swing.JButton btn_send;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox jcb_kripCeasar;
    private javax.swing.JCheckBox jcb_kripPicket;
    private javax.swing.JCheckBox jcb_kripPolybius;
    private javax.swing.JLabel jlbl_sifretext;
    private javax.swing.JLabel jlbl_username;
    private static javax.swing.JTextArea jta_chat;
    private javax.swing.JTextField jtf_text;
    private javax.swing.JTextField jtf_username;
    // End of variables declaration//GEN-END:variables
}
