package DATA.pakages;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.io.Serializable;
public class setting implements Serializable{
    public settingList lst;
    JButton[] Jb;
    JFrame jf;
    public setting (settingList l){
        lst=l;
    }
    public static void setUIFont(){
        Font font=new Font("Dialog",Font.PLAIN,14);
        UIManager.put("Label.font",new Font("Dialog",Font.PLAIN,16));
        java.util.Enumeration keys = UIManager.getDefaults().keys(); 
        while (keys.hasMoreElements()) {   
         Object key = keys.nextElement();  
         Object value = UIManager.get (key);  
         if (value instanceof javax.swing.plaf.FontUIResource) 
          UIManager.put (key, font);  
         }
         try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          } catch (Exception e) {
            System.out.println("Substance Raven Graphite failed to initialize");
          }  
       }
    public  void build() {
        setUIFont();
        jf = new JFrame(lst.head);
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setResizable(false);
        jf.setIconImage(show.getImage());

        Box pl = new Box(BoxLayout.PAGE_AXIS);
        int many=lst.everyone.size();
        Box[] b=new Box[many];
        Jb=new JButton[many];
        for(int count=0;count<many;count++){
            single s=lst.everyone.get(count);
            b[count]=Box.createHorizontalBox();
            b[count].add(Box.createHorizontalStrut(5));
            b[count].add(new JLabel(s.text+" "));
            b[count].add(Box.createHorizontalGlue());
            switch(s.type){
                case 0:
                    Jb[count]=new JButton("Change(value_now: "+s.V+")");
                    Jb[count].addActionListener(new choose(count));
                    b[count].add(Jb[count]);
                    break;
                case 1:
                    Jb[count]=new JButton("Change(value_now: "+s.V+")");
                    Jb[count].addActionListener(new text(count));
                    b[count].add(Jb[count]);
                    break;
                case 2:
                    Jb[count]=new JButton("Change(value_now: "+s.value[0]+")");
                    Jb[count].addActionListener(new accounts(count));
                    b[count].add(Jb[count]);
                    break;
                case 3:
                    Jb[count]=new JButton("Change(value_now: "+s.TF+")");
                    Jb[count].addActionListener(new tfv(count));
                    b[count].add(Jb[count]);
                    break;
            }
            b[count].add(Box.createHorizontalStrut(40));
            JButton Jb2=new JButton("About");
            Jb2.addActionListener(new showInfo(s.log));
            b[count].add(Jb2);
            pl.add(b[count]);
        }
        
        
        JScrollPane scrollPane = new JScrollPane(
            pl,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        jf.setContentPane(scrollPane);
        jf.setSize(500,400);
        jf.setVisible(true);

    }
    class Father{
        single s;
        int num;
        public Father(int i){
            num=i;
            s=lst.everyone.get(num);
        }
    }
    class choose extends Father implements ActionListener{
        public choose(int i){
            super(i);
        }
        public void actionPerformed(final ActionEvent e){
            String op = (String)JOptionPane.showInputDialog(null,s.log,"Choice",JOptionPane.QUESTION_MESSAGE,null,(Object[])s.value,s.V);
            if(op !=null){s.V=op;}
            lst.everyone.set(num,s);
            Jb[num].setText("Change(value_now: "+s.V+")");
        }
    }
    class text extends Father implements ActionListener{
        public text(int i){
            super(i);
        }
        public void actionPerformed(final ActionEvent e){
            String op = (String)JOptionPane.showInputDialog(null,s.log);
            if(op !=null){s.V=op;}
            lst.everyone.set(num,s);
            Jb[num].setText("Change(value_now: "+s.V+")");
        }
    }
    class accounts extends Father implements ActionListener{
        public accounts(int i){
            super(i);
        }
        public void actionPerformed(final ActionEvent e){
            accountCode acc=new accountCode(jf);
            String[] op=acc.inf;
            if(op !=null){s.value=op;}
            lst.everyone.set(num,s);
            Jb[num].setText("Change(value_now: "+s.value[0]+")");
        }
    }
    class tfv extends Father implements ActionListener{
        public tfv(int i){
            super(i);
        }
        public void actionPerformed(ActionEvent e){
            Integer op=JOptionPane.showConfirmDialog(null, s.log, "Choice",JOptionPane.NO_OPTION);
            if(op !=2 && op !=-1){s.TF=op;}
            lst.everyone.set(num,s);
            Jb[num].setText("Change(value_now: "+s.TF+")");
            }
    }
    class showInfo implements ActionListener{
        String log;
        public showInfo(String log){
            this.log=log;
        }
        public void actionPerformed(ActionEvent e){
            show.m("Detailed",log);
        }
    }
    public static void main(String[] args){
        single s1=new single();
        s1.text="title";
        s1.type=0;
        s1.value=new String[] {"123","qwe","a","122","q","qewqwe","qweqwe","681238te2y"};
        s1.V="123";
        s1.log="just a test";

        single s2=new single();
        s2.text="title2";
        s2.type=3;
        s2.V="1";
        s2.log="just a test2";

        single s3=new single();
        s3.text="proxy";
        s3.type=2;
        s3.value=new String[] {"123","qwe"};
        s3.log="just a test3";

        settingList l=new settingList();
        l.everyone.add(s1);
        l.everyone.add(s2);
        l.everyone.add(s3);
        for(int i=0;i<20;i++){
            l.everyone.add(s1);
        }
        setting st= new setting(l);
        st.build();
    }
}