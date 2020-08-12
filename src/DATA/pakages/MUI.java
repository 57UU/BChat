package DATA.pakages;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class MUI {
    public friends frd;
    Box Pa;
    JPanel Pb;
    Box Me;
    Box FriendTab;
    Box GroupTab;
    JPanel talk;
    JTextArea txt=new JTextArea();
    JTextField sendMsg=new JTextField();
    myself ms;
    JFrame jf;
    boolean face=false;
    long myID;
    public static void main(String[] args){
        friend pig=new friend(250);
        pig.name="pig";
        ArrayList<String> temp=new ArrayList<String>();
        temp.add("pig");
        pig.text=temp;
        friends l=new friends(1);
        l.allFrd.add(pig);
        MUI m=new MUI(1);
        m.frd=l;
        m.build();
    }
    public MUI (long myID){
        this.myID=myID;
    } 
    public void build() {
        setting.setUIFont();
        jf = new JFrame("BChat");
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jf.setSize(800,600);
        jf.setIconImage(show.getImage());
        jf.setLocationRelativeTo(null);

        Pa = Box.createVerticalBox();
        Pb=new JPanel(new BorderLayout());
        talk=new JPanel(new BorderLayout());
        jf.getContentPane().add(Pa,BorderLayout.WEST);
        jf.getContentPane().add(Pb,BorderLayout.CENTER);
        final JTabbedPane tabbedPane = new JTabbedPane();
        Pa.add(tabbedPane);

        FriendTab=Box.createVerticalBox();
        GroupTab=Box.createVerticalBox();
        Me=Box.createVerticalBox();

        ms=new myself();
        ms.allBtn[0]=new JButton("About Me");
        ms.allBtn[1]=new JButton("Edit Info");
        ms.allBtn[2]=new JButton("Setting");
        ms.allBtn[3]=new JButton("About");
        
        int count=0;
        for(JButton i:ms.allBtn){
            if(count==2){
                count++;
                continue;
            }
            i.addActionListener(new MeBtnPress(count));
            count++;
        }
        ms.allBtn[2].addActionListener(new bootSetting());
        
        Me.add(ms.allBtn[0]);
        Me.add(ms.allBtn[1]);
        Me.add(ms.allBtn[2]);
        Me.add(ms.allBtn[3]);

        tabbedPane.addTab("Friends", FriendTab);
        tabbedPane.addTab("Groups", GroupTab);
        tabbedPane.addTab("Me", Me);

        count=0;
        for(friend i:frd.allFrd){
            JButton tb=new JButton(i.name);
            tb.addActionListener(new frdBtnPress(count));
            FriendTab.add(tb);
            count++;
        }
        txt.setEditable(false);
        txt.setLineWrap(true); 
        txt.setWrapStyleWord(true);
        txt.setFont(new Font("Dialog",Font.PLAIN,16));
        sendMsg.setFont(new Font("Dialog",Font.PLAIN,16));
        sendMsg.addKeyListener(new enterPressed());
        talk.add(new JScrollPane(txt,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
        talk.add(sendMsg,BorderLayout.SOUTH);
        Pb.add(talk);

        jf.setVisible(true);
    }
    public class enterPressed implements KeyListener{
        public void keyReleased (final KeyEvent e){ }
        public void keyTyped (final KeyEvent e){ }
        public void keyPressed (final KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_ENTER){
                txt.append(sendMsg.getText()+"\n");
                sendMsg.setText("");
            }
        }
    }
    public class bootSetting implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setting.main(new String[] {""});
        }
    }
    public class MeBtnPress implements ActionListener{
        int num;
        public MeBtnPress(int n){
            num=n;
        }
        public void actionPerformed(ActionEvent e){
            face=false;
            Pb.removeAll();
            Pb.add(ms.allPane[num]);
            Pb.updateUI();
            
        }
    }
    public class frdBtnPress implements ActionListener{
        int num;
        public frdBtnPress(int n){
            num=n;
        }
        public void actionPerformed(ActionEvent e){
            if(!face){
                Pb.removeAll();
                Pb.add(talk);
                for(String i:(frd.allFrd.get(num)).text){
                    txt.setText(i);
                }
                Pb.updateUI();
            }
        }
    }
}