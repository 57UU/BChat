package DATA.pakages;

import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.FileInputStream;
public class show{
    static ImageIcon icon;
    static boolean ifBuff=false;
    static Image image;
    public static void init(){
        try{
            icon=new ImageIcon("block.png");
            image = Toolkit.getDefaultToolkit().getImage("block.png");
        }catch(Exception e){
            e.printStackTrace();
        }
        ifBuff=true;
    }
    public static void m(String t,String msg){
        JOptionPane.showMessageDialog(null, msg,t,JOptionPane.INFORMATION_MESSAGE);
    }
    public static void main(String[] args){
        m("title","test,test,test,test,test,test,test.");
    }
    
    public static ImageIcon getIco(){
        if(!ifBuff){
            init();
        }
        return(icon);
    }
    public static Image getImage(){
        if(!ifBuff){
            init();
        }
        return(image);
    }
}