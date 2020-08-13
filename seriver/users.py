import lock as c
import pickle

class user :
    def __init__ (self,key):
        f=open('user','rb')
        load=pickle.load(f)
        self.info=c.code.de(load,key)
        self.name=self.info.keys()
        f.close
        self.key=key
    def write(self,i):
        f=open('user','wb')
        pickle.dump(c.code.en(self.info,self.key),f)
        f.close()
        #up in build-in function
    def creat (self,n,p):
        if n in self.name :
            return('Error:somebody has used this name')
        self.info[n]=p
        self.write(self.info)
        return('maybe successful')
    def del_user (self,n):
        if n not in self.name :
            return('Error:nobode named'+str(n))
        self.info.pop(n)
        self.write(self.info)
        return('maybe successful')
    def see_user(self):
        return self.name
    def change_passwd(self,n,p):
        if n not in self.name :
            return('Error:nobode named'+str(n))
        self.info[n]=p
        self.write(self.info)
        return('maybe successful')
    def login (self,n,p):
        if n not in self.name :
            return('Error:nobode named'+str(n))
        elif p != self.info[n] :
            return('invalid password')
        else :
            return(True)
