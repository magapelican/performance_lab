package task1;

import java.lang.StringBuilder;
import java.lang.Integer;


public class Main {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Аргументов должно быть 4!");
            System.exit(1);
        }

        int n = parse(args[0]);
        int m = parse(args[1]);

        typeArray(n, m, 1);
        String path1 = getPath(n, m);

        n = parse(args[2]);
        m = parse(args[3]);

        typeArray(n, m, 2);
        String path2 = getPath(n, m);

        System.out.printf("Общий путь: %s", path1 + path2);
        
    }

    private static int parse(String value) {
        int i = 0;
        
        try {
            i = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.out.println("Аргумент должен быть int!");
            System.exit(1);
        }

        return i;
    }

    private static void typeArray(int n, int m, int arrayNumber) {
        System.out.printf("Массив %d: n = %d, m = %d\n", arrayNumber, n, m);
        System.out.print("Круговой массив: ");
        for (int i = 1; i <= n; i++) {
            System.out.print(i);
        }
        System.out.println();
    }

    private static String getPath(int n, int m) {
        int i = 1;
        int step = 0;
        StringBuilder str = new StringBuilder();

        str.append(i);

        System.out.print("Интервалы: ");

        while (true) {
            System.out.print(i);
            if (step++ == m - 1) {
                if (i == 1) break;
                System.out.print(", ");
                step = 0;
                str.append(i);
                continue;
            }

            i = (i + 1) % (n + 1) == 0 ? 1 : i + 1;
        }

        String path = str.toString();
        System.out.printf(". Полученный путь: %s\n", path);

        return path;
    }

}
