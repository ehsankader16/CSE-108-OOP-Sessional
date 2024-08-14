
#include<iostream>
#define UNDEFINED 0
#define LINE 1
#define RECTANGLE 2
#define CUBE 3

using namespace std;


class Shape
{
    int type;
public:
    Shape()
    {
        this->type = UNDEFINED;
    }
    Shape(int type)
    {
        this->type = type;
        //cout<<"In Shape with Type "<<type<<endl;
    }

    virtual int area()
    {
        //cout<<"In Base Class, Overload this function in derived class"<<endl;
        cout<<"Shape.area() is called\n";
        return -1;
    }

    virtual int volume()
    {
       // cout<<"In Base Class, Overload this function in derived class"<<endl;
        cout<<"Shape.volume() is called\n";
        return -1;
    }

    int get_type(){ return type; }
    void set_type(int type){ this->type = type; }

    ~Shape()
    {
        cout<<"Shape object destructed \n";
    }
};


class Line: public Shape
{
public:
    int length;
    Line(int type, int length):Shape(type)
    {
        this->length = length;
        //cout<<"In Line with length "<<length<<endl;
    }

    ///override area() and volume()
    int area()
    {
        cout<<"Line.area() is called\n";
        return 0;
    }

    int volume()
    {
        cout<<"Line.volume() is called\n";
        return 0;
    }
    int get_length()
    {
        return length;
    }

    ~Line()
    {
        cout<<"Line object destructed \n";
    }
    ///setter getter if required

};

class Rectangle: private Line
{
    int width;
public:
    Rectangle(int type, int length, int width):Line(type,length)
    {
        this->width = width;
        //cout<<"In Rectangle with width"<<width<<endl;
    }

    ///override area() and volume()
    int area()
    {
        cout<<"Rectangle.area() is called\n";
        return length*width;
    }

    int volume()
    {
        cout<<"Rectangle.volume() is called\n";
        return 0;
    }
    int get_length()
    {
        return length;
    }

    int get_width()
    {
        return width;
    }

    ~Rectangle()
    {
        cout<<"Rectangle object destructed \n";
    }
    ///setter getter if required

};

class Cube: private Rectangle
{
    int height;
public:
    Cube():Rectangle(3,0,0)
    {
        height = 0;
        /// set length and width
    }

    Cube(int type, int length, int width, int height):Rectangle(type,length,width)
    {
        this->height = height;
    }

    ///override area() and volume()
    int area()
    {
        cout<<"Cube.area() is called\n";
        return (2*get_length()*get_width())+(2*get_width()*height)+(2*height*get_length());
    }

    int volume()
    {
        cout<<"Cube.volume() is called\n";
        return get_length()*get_width()*height;
    }

    ///setter getter if required
    ~Cube()
    {
        cout<<"Cube object destructed \n";
    }

};


int main()
{
    Shape s;
    cout<<s.area()<<"\n";
    ///In Base Class, Overload this function in derived class
    cout<<s.volume()<<"\n";
    ///In Base Class, Overload this function in derived class

    Line l(LINE, 5);
    ///Implement area and volume function
    cout<<l.area()<<"\n";
    ///Area is 0
    cout<<l.volume()<<"\n";
    ///Volume is 0

    Rectangle r(RECTANGLE, 5, 2);
    ///Notice that access modifier is private
    cout<<r.area()<<"\n";
    ///Area is 10
    cout<<r.volume()<<"\n";
    ///Volume is 0

    Cube c(CUBE, 5, 2, 4);
    ///You can not access length due to private modifier in rectangle
    ///write necessary methods in rectangle to access length
    cout<<c.area()<<"\n";
    ///Area is 76
    cout<<c.volume()<<"\n";
    ///Volume is 40

    ///Demonstration of Virtual Function in Shape
    Shape *s1;
    s1 = &l;
    cout<<s1->area()<<"\n";
    ///Area is 0
    cout<<s1->volume()<<"\n";
    ///Volume is 0


    Shape* s2;
    s2 = &l;
    cout<<s2->area()<<"\n";
    ///Area is 0
    cout<<s2->volume()<<"\n";
    ///Volume is 0

    ///write destructor function and observe (by printing something) the order of constructor and destructor call

    return 0;
}

