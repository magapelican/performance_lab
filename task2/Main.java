package task2;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
    
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[1]))) {

            Eclipse eclipse = Eclipse.creaEclipse(args[0]);
            String line;
            while((line = bufferedReader.readLine()) != null)  {
                String[] strNumbers = line.split(" ");
                int x = Integer.parseInt(strNumbers[0]);
                int y = Integer.parseInt(strNumbers[1]);

                int result = eclipse.pointPosition(x, y);
                System.out.println(result);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
