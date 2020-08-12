package DATA.pakages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
public class Net {
    Socket socket;
    String IP;
    int com;
    public static void main(String args[]){
    }
    public  Net(String IP,int com){
        this.IP=IP;
        this.com=com;
    }
    public String interactive(String msg)throws IOException{
        StringBuffer sb=null;
        OutputStream os=socket.getOutputStream();//字节输出流
        
        PrintWriter pw=new PrintWriter(os);//将输出流包装为打印流
        pw.write(msg);
        pw.flush();
        socket.shutdownOutput();//关闭输出流
        InputStream is=socket.getInputStream();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(is,"ascii"));
        sb=new StringBuffer();
        int a;
        while((a= in.read()) != 4){
            System.out.println(a);
            sb.append((char)a);
        }
        socket.shutdownInput();
        is.close();
        in.close();
        return(sb.toString());
    }
    public boolean verify(String[] info){
        String msg="0"+(char)30+info[0]+(char)30+info[1];
        boolean status=false;
        try{
            socket = new Socket(IP,com);
            String inBox=interactive(msg);
            
            switch(inBox){
                case "0":
                    socket.close();
                    show.m("ERROR","Passwd or account Error");
                    break;
                case "1":
                    status=true;
                    break;
                case "2":
                    socket.close();
                    show.m("ERROR","Your account is alread online");
                    break;
            }
        }catch(IOException e){
            System.out.println("Network Error");
            e.printStackTrace();
            System.exit(0);
        }
        return status;
    }
}