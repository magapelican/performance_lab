package task2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Должно быть передано два аргумента(пути к файлам)!");
            System.exit(1);
        }

        if (!Files.exists(Paths.get(args[0])) || !Files.exists(Paths.get(args[1]))) {
            System.out.println("Неправильный путь к файлу!");
            System.exit(1);
        }

        File file = new File(args[1]);
    
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            Eclipse eclipse = Eclipse.createEclipse(args[0]);
            System.out.printf("%.0f %.0f - координаты центра\n%.0f %.0f - координаты радиуса\n", eclipse.getH(), eclipse.getK(), eclipse.getA(), eclipse.getB());
            String line;
            int count = 0;
            while((line = bufferedReader.readLine()) != null)  {
                if (!line.matches("-?\\d+(?:\\.\\d+)?\\s-?\\d+(?:\\.\\d+)?")) {
                    System.out.printf("Неправильный формат! Строка должна состоять из двух чисел и пробела между ними!(Файл - %s)\n", file.getName());
                    continue;
                }
                String[] strNumbers = line.split(" ");
                float x = Integer.parseInt(strNumbers[0]);
                float y = Integer.parseInt(strNumbers[1]);

                int result = eclipse.getPointPosition(x, y);
                System.out.println(result);

                if (count++ > 100) {
                    System.out.println("Превышен лимит в 100 точек!");
                    break;
                }
            }

            if (count == 0) System.out.println("Пустой файл с координатами!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

class Eclipse {
    private float h;
    private float k;
    private float a;
    private float b;

    private Eclipse(float h, float k, float a, float b) {
        this.h = h;
        this.k = k;
        this.a = a;
        this.b = b;
    }

    public int getPointPosition(float x, float y) {
        double summ = Math.pow((x - h), 2) / Math.pow(a, 2) + Math.pow((y - k), 2) / Math.pow(b, 2);

        if (summ == 1) return 0;
        else if (summ > 1) return 2;
        else return 1;

    }

    public static Eclipse createEclipse(String path) {
        File file = new File(path);

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            float[] numbers = new float[4];
            for (int i = 0; i < 2; i++) {
                String line = bufferedReader.readLine();
                if (!line.matches("-?\\d+(?:\\.\\d+)?\\s-?\\d+(?:\\.\\d+)?")) {
                    System.out.printf("Неправильный формат! Строка должна состоять из двух чисел и пробела между ними!(Файл - %s)\n",file.getName());
                    System.exit(1);
                }
                String[] strNumbers = line.split(" ");

                numbers[i * 2] = Float.parseFloat(strNumbers[0]);
                numbers[(i * 2) + 1] = Float.parseFloat(strNumbers[1]);
            }

            return new Eclipse(numbers[0], numbers[1], numbers[2], numbers[3]);

        } catch (Exception e) {
            throw new RuntimeException("Ошибка при чтении файла", e);
        }
    }

    public float getH() {
        return h;
    }

    public float getK() {
        return k;
    }

    public float getA() {
        return a;
    }

    public float getB() {
        return b;
    }

    
}
