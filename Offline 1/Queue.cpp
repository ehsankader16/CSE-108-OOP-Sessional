#include <iostream>
#include <cstdlib>
using namespace std;

class Queue
{
    Array s;
    int size;
    int front;
    int rear;
    // you are not allowed to add any field; you can only add methods
public:
    // your code
    Queue(int length)
    {
        size=length;
        front=0;
        rear=0;
        s.setLength(size);
    }
    Queue()
    {
        front=0;
        rear=0;
    }
    void enqueue(int x)
    {

        if(isFull())
        {
            cout << "Queue is full" << endl;
            exit(0);
        }
        // your code
        s.setElementAt(rear,x);
        rear++;
    }
    int dequeue()
    {
        if(isEmpty())
        {
            cout << "Queue is empty" << endl;
            exit(0);
        }
        // your code
        front++;
        return  s.getElementAt(front-1);
    }
    // your code
    int isFull()
    {
        return (rear-front)==size;
    }
    int isEmpty()
    {
        return front==rear;
    }
    void clone(Queue Q)
    {
        int i;
        size=Q.size;
        s.setLength(size);
        while(!Q.isEmpty()){
            enqueue(Q.dequeue());
        }

    }

};
