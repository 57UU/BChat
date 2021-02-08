package Client.DATA.Common;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class SetProxyGUI {
    JFrame jf=new JFrame("Set Proxy");
    Box body=Box.createVerticalBox();
    JTextField IP_area =new JTextField();
    JTextField port_area =new JTextField();
    String[] socketTypes=ProxyPcl.Proxy_Types;
    JComboBox<String> comboBox=new JComboBox<>(socketTypes);
    public SetProxyGUI(ProxyPcl setter){
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        body.add(new JLabel("input IP address"));
        body.add(IP_area);
        IP_area.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    port_area.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        body.add(new JLabel("input com port"));
        body.add(port_area);
        Box typeBox=Box.createHorizontalBox();
        typeBox.add(new JLabel("Type: "));
        typeBox.add(comboBox);
        Beautiful.setBorder(typeBox);
        body.add(typeBox);
        Box buttons=Box.createHorizontalBox();
        JButton yes =new JButton("Set");
        JButton no=new JButton("Cancel");
        buttons.add(yes);
        buttons.add(Box.createHorizontalGlue());
        buttons.add(no);
        body.add(buttons);
        Beautiful.setBorder(body);
        jf.setContentPane(body);
        no.addActionListener((e)->jf.dispose());
        yes.addActionListener(e -> {
            int type=comboBox.getSelectedIndex();
            //System.out.println(type);
            String ip=IP_area.getText();
            String comPort =port_area.getText();
            if(ip.equals("")|| comPort.equals("")){
                Show.floatWindow(jf,"IP or com can't be empty",900);
                return;
            }
            int port;
            try {
                port=Integer.parseInt(comPort);
            }catch (NumberFormatException ignore){
                Show.floatWindow(jf, "wrong format",900);
                return;
            }
            Proxy.Type proxyType = Proxy.Type.DIRECT;
            if(type==ProxyPcl.HTTP){
                proxyType =Proxy.Type.HTTP;
            }if(type==ProxyPcl.socket4||type==ProxyPcl.Socket5){
                proxyType =Proxy.Type.SOCKS;
            }
            //System.out.println(proxyType);
            setter.setProxy(new Proxy(proxyType,new InetSocketAddress(ip,port)));
            jf.dispose();
            Show.floatWindow("set successful","Set proxy successful",800);
        });
        jf.pack();
        Beautiful.setMid(jf);
        Beautiful.setSuitableMinSize(jf);
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        Beautiful.setUIFont();
        new SetProxyGUI(proxy -> {
            System.out.println(proxy.address());
            System.out.println(proxy.type());
        });
    }
}
