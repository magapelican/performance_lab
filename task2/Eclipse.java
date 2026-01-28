package task2;

import java.io.BufferedReader;
import java.io.FileReader;

public class Eclipse {
    private int h;
    private int k;
    private int a;
    private int b;

    private Eclipse(int h, int k, int a, int b) {
        this.h = h;
        this.k = k;
        this.a = a;
        this.b = b;
    }

    public int pointPosition(int x, int y) {
        double summ = Math.pow((x - h), 2) / Math.pow(a, 2) + Math.pow((y - k), 2) / Math.pow(b, 2);

        if (summ == 1) return 0;
        else if (summ > 1) return 2;
        else return 1;

    }

    public static Eclipse creaEclipse(String path) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            int[] numbers = new int[4];
            for (int i = 0; i < 2; i++) {
                String line = bufferedReader.readLine();
                String[] strNumbers = line.split(" ");

                numbers[i * 2] = Integer.parseInt(strNumbers[0]);
                numbers[(i * 2) + 1] = Integer.parseInt(strNumbers[1]);
            }

            return new Eclipse(numbers[0], numbers[1], numbers[2], numbers[3]);

        } catch (Exception e) {
            throw new RuntimeException("Ошибка при чтении файла", e);
        }
    }
}
