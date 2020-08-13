import select
import socket
from users import user
import threading

onlineSituate={}
u=user(123)
for i in u.see_user():
    onlineSituate[i]=False

def resolve(mag):
    try:
        mag=mag.split('\x1e')
        #print(mag)
        return mag
    except IOError:
        return False

def keepConn(connect,name):
    try:
        while 1:
            szBuf=connect.recv(1024)
            if(szBuf!=b""):
                result=resolve(str(szBuf,"ascii"))
                if result[0]=="1":
                    pass
                elif result==True:
                    connect.sendall(b"1")
                elif result==False:
                    connect.sendall(b"0")
    except IOError:
        connect.close()
        onlineSituate[name]=False
        print(name,"is offline")


try:
    sock=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
    print("create socket succ!")

    sock.bind(('localhost',8001))
    print('bind socket succ!')

    sock.listen(5)
    print('listen succ!')

except:
    print("init socket error!")

while True:
    print(onlineSituate)
    conn,addr=sock.accept()
    print(addr)

    conn.settimeout(30)
    szBuf=conn.recv(1024)
    msg=str(szBuf,"ascii")
    print("recv:"+msg)
    loginInfo=resolve(msg)
    if u.login(loginInfo[1],loginInfo[2])==True :
        if onlineSituate.get(loginInfo[1])==False:
            onlineSituate[loginInfo[1]]=True
            t=threading.Thread(target=keepConn,args=(conn,loginInfo[1]))
            t.setDaemon(True)
            t.start()
            print("add thread succ:", loginInfo[1])
            conn.sendall(b"1")
            conn.sendall(b'\x04')
        else:
            conn.sendall(b"2")
            conn.sendall(b'\x04')
    else:
        conn.sendall(b"0")
        conn.sendall(b'\x04')
    #conn.close()
'''
sock = socket.socket()
sock.bind(('127.0.0.1', 789))
sock.setblocking(False)
sock.listen()


inputs = [sock, ]

while True:
    r_list, w_list, e_list = select.select(inputs, [], [], 1)
    for event in r_list:
        if event == sock:
            print("新的客户端连接")
            new_sock, addr = event.accept()
            inputs.append(new_sock)
        else:
            data = event.recv(1024)
            if data:
                print("接收到客户端信息")
                print(data)
                event.send(b'\x31')
            else:
                print("客户端断开连接")
                inputs.remove(event)
'''
