class X
{
    int a = 10;

    void print() {
        System.out.println("X");
        System.out.println(a);
    }
    class Y {
        int a = 20;

        void print() {
            System.out.println("Y");
            System.out.println(a);
            System.out.println(X.this.a);
        }
        class Z {
            int a = 30;

            void print() {
                System.out.println("Z");
                System.out.println(a);
                System.out.println(Y.this.a);
                System.out.println(X.this.a);
            }
        }
    }
}

public class InnerClassDemo4 {
    public static void main(String[] args) {
        X x = new X();
        x.print();
        X.Y y = x.new Y();
        y.print();
        X.Y.Z z = y.new Z();
        z.print();
    }
}
