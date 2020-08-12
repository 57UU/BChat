package DATA.pakages;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
public class accountCode {
    JPasswordField passwordText;
    JTextField userText;
    public String[]  inf;
    JDialog frame;
    public accountCode(JFrame jf) {    
        //frame.setIconImage(null);
        frame = new JDialog(jf,"Account",true);
        // Setting the width and height of frame
        frame.setSize(290, 140);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        final JPanel panel = new JPanel();    
        frame.add(panel);
 
        panel.setLayout(null);

        final JLabel userLabel = new JLabel("Account:");
 
        userLabel.setBounds(10,10,80,25);
        panel.add(userLabel);
 
        userText = new JTextField(20);
        userText.setBounds(100,10,170,25);
        panel.add(userText);
        userText.addKeyListener(new turnPasswd());

 
        final JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10,40,80,25);
        panel.add(passwordLabel);
 
        passwordText = new JPasswordField(20);
        passwordText.setBounds(100,40,170,25);
        panel.add(passwordText);
        passwordText.addKeyListener(new turnLogin());

        final JButton loginButton = new JButton("login");
        loginButton.setBounds(190, 70, 80, 25);
        panel.add(loginButton);
        loginButton.addActionListener(new Press());
        final int windowWidth = frame.getWidth();
        final int windowHeight = frame.getHeight();
        final Toolkit kit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = kit.getScreenSize();
        final int screenWidth = screenSize.width;
        final int screenHeight = screenSize.height;
        frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
        frame.setVisible(true);

    }
    public class turnLogin implements KeyListener{
        public void keyReleased (final KeyEvent e){ }
        public void keyTyped (final KeyEvent e){ }
        public void keyPressed (final KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_ENTER){
                new Press().actionPerformed(new ActionEvent(0,0,""));
            }
        }
    }
    public class turnPasswd implements KeyListener {
        public void keyReleased (final KeyEvent e){   }
        public void keyTyped (final KeyEvent e){   }
        public void keyPressed (final KeyEvent e) { 
            if (e.getKeyCode()==KeyEvent.VK_ENTER){
                passwordText.requestFocus();
            }
         }
    }
    public class Press implements ActionListener {
        public void actionPerformed(final ActionEvent e){
            inf=new String[] {userText.getText(),new String(passwordText.getPassword())};
            frame.dispose();
        }
    }
}