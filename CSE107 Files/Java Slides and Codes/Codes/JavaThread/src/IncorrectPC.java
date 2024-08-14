class Buffer {
    int value;
    volatile boolean valueSet = false;

    int consume() {
        while(!valueSet) {

        }
        valueSet = false;
        System.out.println(Thread.currentThread().getName() + " consumes: " + value);
        return value;
    }

    void produce(int value) {
        while(valueSet) {

        }
        this.value = value;
        this.valueSet = true;
        System.out.println(Thread.currentThread().getName() + " produces: " + this.value);
    }
}

class Producer implements Runnable {
    Buffer buffer;

    Producer(Buffer buffer, String name) {
        this.buffer = buffer;
        new Thread(this, name).start();
    }

    public void run() {
        int i = 0;
        while (i < 100) {
            buffer.produce(i++);
        }
    }
}

class Consumer implements Runnable {
    Buffer buffer;

    Consumer(Buffer buffer, String name) {
        this.buffer = buffer;
        new Thread(this, name).start();
    }

    public void run() {
        int i = 0;
        while (i++ < 100) {
            buffer.consume();
        }
    }
}

public class IncorrectPC {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        new Producer(buffer, "Producer");
        new Consumer(buffer, "Consumer");
        System.out.println("Press Control-C to stop.");
    }
}
