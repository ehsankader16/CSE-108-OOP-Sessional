#include <iostream>
#include <cstdlib>
using namespace std;

class Stack
{
    Array s;
    int size;
    int top;
    // you are not allowed to add any field; you can only add methods
public:
    // your code
    Stack(){
        top=0;

    }
    Stack(int length){
        size=length;
        top=0;
        s.setLength(size);
    }
    void push(int x)
    {
        if(isFull())
        {
            cout << "Stack is full" << endl;
            exit(0);
        }
        // your code
        s.setElementAt(top,x);
        top++;
    }
    int pop()
    {
        if(isEmpty())
        {
            cout << "Stack is empty" << endl;
            exit(0);
        }
        // your code
        top--;
        return s.getElementAt(top);
    }
    // your code
    int isFull()
    {
        return top==size;
    }
    int isEmpty()
    {
        return top==0;
    }
    void clone(Stack X)
    {
        int i;

        size=X.size;
        s.setLength(size);
        while(!X.isEmpty()){
            push(X.pop());
        }

    }
};
