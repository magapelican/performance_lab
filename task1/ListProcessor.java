package task1;

import java.util.ArrayList;
import java.util.List;

public class ListProcessor implements Runnable{

    private int n;
    private int m;
    private StringBuilder stringBuilder;

    public ListProcessor(int n, int m, StringBuilder stringBuilder) {
        this.n = n;
        this.m = m;
        this.stringBuilder = stringBuilder;
    }
    
    public void run() {
        int i = 1;
    
        while (true) {
            stringBuilder.append(i);
            i = next_i(i);
            if (i == 1) break;
        }
    }

    private int next_i(int i) {
        return 1 + (i + m - 2) % n;
    }
}
