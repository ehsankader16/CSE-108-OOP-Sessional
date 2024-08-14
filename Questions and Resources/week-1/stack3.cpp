#include <stdio.h>

#define SIZE 10

struct stack
{
    char s[SIZE]; // holds the stack
    int top; // index of top of stack
    void init()
    {
        top = 0;
    }

    // Push a character
    void push(char c)
    {
        if(top == SIZE)
        {
            printf("Stack is full");
            return;
        }
        s[top++] = c;
    }

    // Pop a character
    char pop()
    {
        if(top == 0)
        {
            printf("Stack is empty");
            return 0; // return null on empty stack
        }
        return s[--top];
    }

    int isEmpty()
    {
        return top == 0;
    }

};





int main(void)
{
	struct stack s1;

	s1.init();
	s1.push('a');
	s1.push('b');
	s1.push('c');


    while(!s1.isEmpty())
	{
		printf("Pop stack: %c\n", s1.pop());
	}

	return 0;
}
