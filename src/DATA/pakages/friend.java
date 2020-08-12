package DATA.pakages;

import java.util.ArrayList;
import java.io.Serializable;
public class friend implements Serializable{
    public String name;
    public long ID; 
    ArrayList<String> f=new ArrayList<String>();
    public ArrayList<String> text;
    int f_now;//the address of file in list
    String dirPath;
    public friend(long friendID){
        dirPath="/History"+friendID; 
    }
    public boolean older(){
        if (f_now< f.size()){
            f_now++;
            text=(ArrayList<String>)file.read(f.get(f_now));
            return true;
        }
        return false;
    }
    public boolean earlier(){
        if(f_now>f.size()){
            f_now--;
            text=(ArrayList<String>)file.read(f.get(f_now));
            return true;
        }
        return false;
    }
    public void new_msg(String s){
        text.add(s);
    }
}