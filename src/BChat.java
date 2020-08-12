import DATA.pakages.Net;
import DATA.pakages.accountCode;
import DATA.pakages.setting;
import DATA.pakages.show;
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
        boolean TFinfo=false;
        net=new Net(IP,com);
        while (TFinfo==false){
            accountCode acc=new accountCode(new JFrame());
            String[] loginInfo=acc.inf;
            TFinfo= net.verify(loginInfo);
        }
        show.m("info","Login successfully");
    }
}