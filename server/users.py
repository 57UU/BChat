import lock as c
import pickle

class user :
    def __init__ (self,key):
        f=open('user','rb')
        load=pickle.load(f)
        self.info=c.code.de(load,key)
        f.close
        self.key=key
    def write(self,i):
        f=open('user','wb')
        pickle.dump(c.code.en(self.info,self.key),f)
        f.close()
        #up in build-in function
    def creat (self,n,p):
        if n in self.info.keys() :
            return('Error:somebody has used this name')
        self.info[n]=[p]
        self.info[n].append([])
        self.write(self.info)
        return('maybe successful')
    def del_user (self,n):
        if n not in self.info.keys() :
            return('Error:nobode named'+str(n))
        self.info.pop(n)
        self.write(self.info)
        return('maybe successful')
    def see_user(self):
        return self.info.keys()
    def change_passwd(self,n,p):
        if n not in self.info.keys() :
            return('Error:nobode named'+str(n))
        self.info[n][0]=p
        self.write(self.info)
        return('maybe successful')
    def login (self,n,p):
        if n not in self.info.keys() :
            return('Error:nobode named'+str(n))
        elif p != self.info[n][0] :
            return('invalid password')
        else :
            return(True)
    def getFrd(self,name):
        if name in self.info.keys():
            return(self.info[name][1])
        return(False)
    def addFrd(self,name,frd):
        if (name in self.info.keys()) and (frd in self.info.keys()):
            self.info[name][1].append(frd)
            self.info[frd][1].append(name)
            self.write(self.info)
            return True
        return False

