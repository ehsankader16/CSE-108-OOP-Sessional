#include <iostream>
#include <cstring>
#include <stdlib.h>
using namespace std;


char* addition(char *s1, char *s2){

    int i,j,k,carry=0;
    char *s3;
    int x=strlen(s1);
    int y=strlen(s2);
    int mx=(x>y?x:y)+1;
    s3=new char[mx+1];
    i=x-1;
    j=y-1;
    k=mx-1;

    for(;i>=0&&j>=0;i--,j--,k--){
        s3[k]=(((s1[i]-'0')+(s2[j]-'0'))+carry)%10+'0';
        carry=(((s1[i]-'0')+(s2[j]-'0'))+carry)/10;
    }
    if(i>=0){
        for(;i>=0;i--,k--){
            s3[k]=(s1[i]-'0'+carry)%10+'0';
            carry=(s1[i]-'0'+carry)/10;
        }
    }
    else if(j>=0){
        for(;j>=0;j--,k--){
            s3[k]=(s2[j]-'0'+carry)%10+'0';
            carry=(s2[j]-'0'+carry)/10;
        }
    }

    s3[0]=carry+'0';

    if(!carry){
        for(k=0;k<mx-1;k++){
            s3[k]=s3[k+1];
        }
        s3[mx-1]='\0';
    }
    s3[mx]='\0';
    return s3;
}

char* substraction(char *s1, char *s2){
    int i,j,k=0,carry=0;

    char *s3;
    int x=strlen(s1);
    int y=strlen(s2);
    s3=new char[x+1];

    for(i=0;i<x-y;i++){
        s3[i]='0';
    }
    for(j=0;j<y;i++,j++){
        s3[i]=s2[j];

    }
    s3[x]='\0';
    for(i=x-1;i>=0;i--){
        s3[i]+=carry;
        if(s1[i]-s3[i]<0){
            s3[i]=10+s1[i]-s3[i]+'0';
            carry=1;
        }
        else{
            s3[i]=s1[i]-s3[i]+'0';
            carry=0;
        }
    }
    for(i=0;i<x-1;i++){
        if(s3[i]!='0')
            break;
        k++;
    }
    for(i=0;i<x-k;i++){
        s3[i]=s3[i+k];
    }
    s3[x-k]='\0';

    return s3;

}


class StringMath
{
    char* p;
public:
// Add necessary constructors and destructors and functions
    StringMath(){
        p=NULL;
    }

    StringMath(char *str){
        if(str[0]=='-'){
            cout<<"StringMath cannot have negative value";
            exit(0);
        }
        p=new char[strlen(str)];
        strcpy(p,str);
    }

    char* getp(){
        return p;
    }

    StringMath(const StringMath &ob){
        delete []p;
        p=new char[strlen(ob.p)];
        strcpy(p,ob.p);
    }

    StringMath operator=(const StringMath &ob){
        delete []p;
        p=new char[strlen(ob.p)];
        strcpy(p,ob.p);
        return *this;
    }

    StringMath operator+(const StringMath &ob){
        return StringMath(addition(p,ob.p));
    }

    StringMath operator+(int n){
        char *s;
        int cnt=0,temp=n;
        while(temp){
            temp=temp/10;
            cnt++;
        }
        s=new char[cnt+1];
        if(n<0){
            n=-n;
            itoa(n,s,10);
            if(!(*this>n)&&!strcmp(p,s)==0){
                cout<<"StringMath object cannot have negative value";
                exit(1);
            }
            return StringMath(substraction(p,s));
        }
        itoa(n,s,10);
        return StringMath(addition(p,s));
    }

    int operator>(StringMath &ob){
        int x=strlen(p);
        int y=strlen(ob.p);
        if(x>y)
            return 1;
        else if(x<y)
            return 0;
        else{
            if(strcmp(p,ob.p)>0)
                return 1;
        }
        return 0;
    }

    int operator>(int n){
        char *s;
        int cnt=0,temp=n;
        while(temp){
            temp=temp/10;
            cnt++;
        }
        s=new char[cnt+1];
        itoa(n,s,10);
        int x=strlen(p);
        int y=cnt;

        if(x>y)
            return 1;
        else if(x<y)
            return 0;
        else{
            if(strcmp(p,s)>0)
                return 1;
        }
        return 0;
    }
    StringMath &operator++(int unused){
        char *s;
        s=new char[strlen(p)+1];
        s=addition(p,"1");
        delete []p;
        p=new char[strlen(s)];
        strcpy(p,s);
        return *this;
    }

    ~StringMath(){
        delete []p;
    }

    friend StringMath operator+(int n,StringMath &ob);

};

StringMath operator+(int n,StringMath &ob){
    char *s;
    int cnt=0,temp=n;
    while(temp){
        temp=temp/10;
        cnt++;
    }
    s=new char[cnt+1];
    if(n<0){
        n=-n;
        itoa(n,s,10);
        if(!(ob>n)&&!strcmp(s,ob.p)==0){
            cout<<"StringMath object cannot have negative value";
            exit(1);
        }
        return StringMath(substraction(ob.p,s));
    }
    itoa(n,s,10);
    return StringMath(addition(ob.p,s));

}




int main()
{
    StringMath S1;
    StringMath S2("123");
    StringMath S3("757");
    StringMath S4("220");
    StringMath S5;
    int n=345;
    S1=S4;
    cout<<"S1: "<<S1.getp()<<"\n";
    cout<<"S4: "<<S4.getp()<<"\n";
    //Print the character string of S1 and S4
    S1=S2+S3+S4;
    cout<<"S1: "<<S1.getp()<<"\n";
    cout<<"S2: "<<S2.getp()<<"\n";
    cout<<"S3: "<<S3.getp()<<"\n";
    cout<<"S4: "<<S4.getp()<<"\n";
    //Print the character string of S1, S2, S3 and S4, where S1 contains the character string: "1100"
    S5=S4=S3;
//    // Print the character string of S5, S4 and S3
    cout<<"S5: "<<S5.getp()<<"\n";
    cout<<"S4: "<<S4.getp()<<"\n";
    cout<<"S3: "<<S3.getp()<<"\n";
    if(S3 > n )
    {
        S5=S3+n;
    }
    // Print the character string of S5, where S5 contains the character string: "1102"
    cout<<"S5: "<<S5.getp()<<"\n";
    S5= n+S2;
//    // Print the character string of S5, where S5 contains the character string: "468"
    cout<<"S5: "<<S5.getp()<<"\n";
    if(S5 > S2)
    {
        S5++; //Assume prefix increment
    }
    // Print the character string of S5, where S5 contains the character string: "469"
    cout<<"S5: "<<S5.getp()<<"\n";
}


