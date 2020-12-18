package task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main( String[] args ) {
        Average average =new Average();
        System.out.println("Enter 2 whole number:");
        List<Integer> numbers = new ArrayList<>();
        int sum = getSum(numbers);
        System.out.println("the arithmetic mean:"+average.average(sum));
    }

    private static int getSum(List<Integer> numbers) {
        Scanner scanner =new Scanner(System.in);
        for (int i = 1; i <=2 ; i++) {
            numbers.add(scanner.nextInt());
        }
        int sum=0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum;
    }


}
