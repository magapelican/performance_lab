package task4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.lang.Integer;

public class Main {
    public static void main(String[] args) {

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

        String line = bufferedReader.readLine();
        List<Integer> nums = new ArrayList<>();
        String[] stringNumbers = line.split(",");

        for (String strNumber : stringNumbers) {
            strNumber = strNumber.trim();
            if (!strNumber.isEmpty()) nums.add(Integer.parseInt(strNumber));
        }

        return nums;
    }

    private static void processList(List<Integer> nums) {
        int summ = 0;
        for (Integer i : nums) summ += i;

        int mean = summ / nums.size();
        int moves = 0;

        for (Integer i : nums) {
            if (moves > 20) {
                System.out.println("20 ходов недостаточно для приведения всез элементов массива к одному числу");
                return;
            }

            if (i > mean) moves += i - mean;
            else moves += mean - i;
        }

        System.out.println("Количество ходов: " + moves);

    }
}