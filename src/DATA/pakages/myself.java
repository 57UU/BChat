package DATA.pakages;

import javax.swing.*;
import java.awt.image.*;
import java.awt.*;

public class myself{
    //JButton0 me;
    //JButton1 edit;
    //JButton2 setting;
    //JButton3 about;
    JButton[] allBtn=new JButton[4];
    //JPanel0 mePane=new JPanel();
    //JPanel1 editPane=new JPanel();
    //JPanel2 aboutPanel=new JPanel()
    JPanel[] allPane=new JPanel[4];
    public myself(){
        for(int i=0;i<allPane.length;i++){
            allPane[i]=new JPanel();
        }
        allPane[0].add(new JLabel("About Me"));
        allPane[1].add(new JLabel("Edit page"));
        allPane[3].add(new JLabel(show.getIco()),BorderLayout.WEST);
        allPane[3].add(new JLabel("BChat version:1.0"));
    }
}