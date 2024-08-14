import java.util.Arrays;
import java.util.Random;

class ParallelMax implements Runnable {
    private int[] numbers;
    private int start;
    private int end;
    private int max;

    public ParallelMax(int[] numbers,int start,int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    public int getMax() {
        return max;
    }

    @Override
    public void run() {
        max = numbers[start];
        for(int i = start;i < end;i++){
            if(numbers[i] > max)
                max = numbers[i];
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int [] numbers = new int[1000];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt();
        }
        ParallelMax [] parallelMax = new ParallelMax[5];
        Thread[] threads = new Thread[5];
        int max = numbers[0];
        int range = numbers.length/5;
        for(int i = 0;i < 5;i++){
            parallelMax[i] = new ParallelMax(numbers,i*range,(i+1)*range);
            threads[i] = new Thread(parallelMax[i]);
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
                if(parallelMax[i].getMax() > max)
                    max = parallelMax[i].getMax();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("The maximum number in the array is "+max);
        // your code here
    }
}