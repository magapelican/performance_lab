package task1;

import java.lang.StringBuilder;
import java.lang.Integer;


public class Main {
    public static void main(String[] args) {
        StringBuilder path1 = new StringBuilder();
        StringBuilder path2 = new StringBuilder();

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
    
        ListProcessor listProcessor1 = new ListProcessor(n, m, path1);
        
        n = Integer.parseInt(args[2]);
        m = Integer.parseInt(args[3]);


        ListProcessor listProcessor2 = new ListProcessor(n, m, path2);


        Thread thread1 = new Thread(listProcessor1);
        Thread thread2 = new Thread(listProcessor2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } 
        catch(Exception e) {
            System.out.println(e);
        }

        System.out.println(path1.toString() + path2.toString());    
    }
}
