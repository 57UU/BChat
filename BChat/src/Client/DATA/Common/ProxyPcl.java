package Client.DATA.Common;

import java.net.Proxy;

public interface ProxyPcl {
    String[] Proxy_Types ={"HTTP","socket4","socket5"};
    int HTTP=0;
    int socket4=1;
    int Socket5=2;
    void setProxy(Proxy proxy);
}
