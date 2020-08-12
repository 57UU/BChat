package DATA.pakages;

import java.util.ArrayList;
import java.util.*;
import java.io.*;
public  class file{
    public static boolean write(String s,Object obj){
        try {
            FileOutputStream out = new FileOutputStream(".\\DATA\\"+s);
            ObjectOutputStream objOut=new ObjectOutputStream(out);
            objOut.writeObject(obj);
            objOut.flush();
            objOut.close();
            System.out.println("write object success!");
            return true;
        } catch (IOException e) {
            
            e.printStackTrace();
            return false;//mean failed
        }
    }
    public static Object read(String s){
        Object temp=null;
        try {
            File file =new File(".\\DATA\\"+s);
            FileInputStream in = new FileInputStream(file);
            ObjectInputStream objIn=new ObjectInputStream(in);
            temp=objIn.readObject();
            objIn.close();
            System.out.println("read object success!");
            
        } catch (IOException e) {
            System.out.println("read object failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }
}