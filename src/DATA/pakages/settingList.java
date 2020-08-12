package DATA.pakages;

import java.util.ArrayList;
import java.io.Serializable;
public class settingList implements Serializable{
    public final short CHOICE = 0;
    public final short TEXT=1;
    public final short ACCOUNT=2;
    public final short BOOLVALUE=3;
    public String head="Setting";
    public ArrayList<single> everyone=new ArrayList<single>();
}
