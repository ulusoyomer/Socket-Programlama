
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.jar.Attributes;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author qq
 */
public class ClientHandler extends Thread {

    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
    static ArrayList<ClientHandler> client_list;
    String client_name;
    static JTextArea jta_chat;
    static JTextArea jta_cryptChat;

    public ClientHandler(Socket socket, String name, ArrayList<ClientHandler> client_list, JTextArea textArea, JTextArea jta_cryptChat) throws IOException {
        this.socket = socket;
        client_name = name;
        this.client_list = client_list;
        jta_chat = textArea;
        this.jta_cryptChat = jta_cryptChat;
        init();
    }

    public void init() throws IOException {
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public DataInputStream getDis() {
        return dis;
    }

    public void setDis(DataInputStream dis) {
        this.dis = dis;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }

    public static ArrayList<ClientHandler> getClient_list() {
        return client_list;
    }

    public static void setClient_list(ArrayList<ClientHandler> client_list) {
        ClientHandler.client_list = client_list;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public void Close() throws IOException {
        dis.close();
        dos.close();
        socket.close();
        client_list.remove(this);
    }

    @Override
    public void run() {
        for (int i = 0; i < client_list.size(); i++) {
            if (client_list.get(i) != null) {
                try {
                    client_list.get(i).getDos().writeUTF(client_name + " Baglandı\n");
                } catch (IOException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String received;
                String sifrelimesaj = null;
                while (true) {
                    try {
                        received = dis.readUTF();
                        if (received.contains("#po/")) {
                            if (sifrelimesaj == null) {
                                sifrelimesaj = received;
                            }
                            String[] metin = received.split("#po/");
                            String temp = Crpt.encrypt_Polybius(metin[1]);
                            received = metin[0] + temp;
                            System.out.println(received);
                        }
                        if (received.contains("#pi/")) {
                            if (sifrelimesaj == null) {
                                sifrelimesaj = received;
                            }
                            String[] metin = received.split("#pi/");
                            String temp = Crpt.encrypt_Picket(metin[1]);
                            received = metin[0] + temp;
                        }
                        if (received.contains("#ce/")) {
                            if (sifrelimesaj == null) {
                                sifrelimesaj = received;
                            }
                            String[] metin = received.split("#ce/");
                            String temp = Crpt.encrypt_Ceasar(metin[1]);
                            received = metin[0] + temp;
                        }

                        if (received.equals("exit")) {
                            break;
                        } else if (received.contains("/name")) {
                            String temp = client_name;
                            client_name = received.substring(6);
                            received = temp + " kullanıcısı ismini " + received.substring(6) + " olarak degiştirdi";
                        }
                        jta_chat.setText(jta_chat.getText() + "\n" + client_name + ": " + received);
                        if (sifrelimesaj != null) {
                            jta_cryptChat.setText(jta_cryptChat.getText() + "\n" + client_name + ": " + sifrelimesaj);
                            sifrelimesaj = null;
                        }
                        for (int i = 0; i < client_list.size(); i++) {
                            if (client_list.get(i) != null) {
                                try {
                                    client_list.get(i).getDos().writeUTF(client_name + ": " + received);
                                } catch (IOException ex) {
                                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    } catch (IOException ex) {
                        try {
                            Close();
                        } catch (IOException ex1) {
                            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
                }

            }
        }).start();
    }

}
