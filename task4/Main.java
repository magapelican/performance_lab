package task4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.lang.Integer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Должно быть передан один аргумент(путь к файлу)!");
            System.exit(1);
        }

        if (!Files.exists(Paths.get(args[0]))) {
            System.out.println("Неправильный путь к файлу!");
            System.exit(1);
        }

        try {
            List<Integer> nums = createList(args[0]);
            processList(nums);
        }
        catch(Exception e) {
            System.out.println(e);
        }

    }

    private static List<Integer> createList(String path) throws FileNotFoundException, IOException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        List<Integer> nums = new ArrayList<>();

        String line;
        while((line = bufferedReader.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) {
                nums.add(Integer.parseInt(line));
            }
        }
        return nums;
    }

    private static void processList(List<Integer> nums) {
        nums.sort(null);

        int median = nums.get(nums.size() / 2);
        int moves = 0;

        for (Integer i : nums) {
            moves += Math.abs(i - median);
        }

        if (moves > 20) {
            System.out.println("20 ходов недостаточно для приведения всех элементов массива к одному числу");
        }
        else {
            System.out.println("Количество ходов: " + moves);
        }
    }
}