package DATA.pakages;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Net implements Runnable{
    Socket socket;
    String IP;
    int com;
    ActionHappen work;
    String inBox;
    //boolean ifConn=false;
    public String[] frds;
    public boolean status=false;
    public static void main(String[] args){    }
    public  Net(String IP,int com){
        this.IP=IP;
        this.com=com;
        int count=0;
        try {
            socket = new Socket(IP,com);
            //socket.setKeepAlive(true);
            //ifConn=true;
        }catch (IOException e){
            show.m("Error","Network Error");
            e.printStackTrace();
            System.exit(0);
        }
    }
    public  void run(){
        StringBuilder sb=new StringBuilder();
        int i=0;
        while(i<10){
            try {
                InputStream is = socket.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(is, StandardCharsets.US_ASCII));
                i=0;
                while (true) {
                    int a;
                    while ((a = in.read()) != 4) {
                        //System.out.println(a);
                        sb.append((char) a);
                    }
                    inBox = sb.toString();
                    //socket.shutdownInput();
                    //is.close();
                    //in.close();
                    switch (inBox) {
                        case "0":
                            socket.close();
                            show.m("ERROR", "Passwd or account Error");
                            break;
                        case "1":
                            status = true;
                            //System.out.println("!!!!!!!!!!!!!");
                            send("2" + (char) 4);
                            frds = inBox.split(String.valueOf(30));
                            work.go();
                            break;
                        case "2":
                            socket.close();
                            show.m("ERROR", "Your account is already online");
                            break;
                    }
                    if (!status){
                        accountCode acc=new accountCode(new JFrame());
                        String[] loginInfo=acc.inf;
                        verify(loginInfo);
                    }
                }
            } catch (IOException e) {
                i++;
                try {
                    socket = new Socket(IP, com);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                System.out.println("Error:Network Error");
            }
        }
        show.m("Error","BadNetwork");
    }
    public void send(String msg){

        try{
            OutputStream os = socket.getOutputStream();//字节输出流

            PrintWriter pw = new PrintWriter(os);//将输出流包装为打印流
            pw.write(msg);
            pw.flush();
            //socket.shutdownOutput();//关闭输出流

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void verify(String[] info){
        String msg="0"+(char)30+info[0]+(char)30+info[1];
        send(msg);
        //return status;
        }
    }
