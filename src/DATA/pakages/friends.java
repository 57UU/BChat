package DATA.pakages;

import java.io.Serializable;
import java.util.ArrayList;
public class friends implements Serializable{
    public ArrayList<friend> allFrd=new ArrayList<friend>();
    long userID;
    public friends(long ID){
        userID=ID;
    }
}