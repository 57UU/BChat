import DATA.pakages.*;

import javax.swing.JFrame;
public class BChat {
    Net net;
    public static void main(String[] args){
        new BChat().build();
    }
    private void build(){
        setting.setUIFont();
        String IP="127.0.0.1";
        int com=8001;
        net=new Net(IP,com);
        Thread t=new Thread(net);
        t.start();

        while (net.status){
            show.m("info", "Login successfully");
            show.m("info", net.frds.toString());
        }
    }
    class callBack implements ActionHappen{
        public void go(){

        }
    }
}