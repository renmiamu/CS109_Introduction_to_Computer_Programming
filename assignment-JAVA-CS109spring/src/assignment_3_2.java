import java.util.Scanner;

public class assignment_3_2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String initial = input.nextLine();
        String[] seperate = initial.split(" ");
        String initial_number = seperate[0];
        String k = seperate[1];
        int k1 = Integer.parseInt(k);
        int yushu = initial_number.length() % k1;   //不确定这里用int行不行
        long sum1 = 0;
        //余数正确
        for (int i = initial_number.length() - 1; i > initial_number.length() - yushu - 1; i--) {
            char number = initial_number.charAt(i);
            String number_string = Character.toString(number);
            long number_int = Long.parseLong(number_string);
            sum1 = sum1 * 10 + number_int;
        }
        //sum1正确
        for (int i = initial_number.length() - yushu - 1; i > 0; i = i - k1) {
            long sum2 = 0;
            for (int j = 0; j < k1; j++) {
                char number1 = initial_number.charAt(i - j);
                String number1_string = Character.toString(number1);
                long number1_int = Long.parseLong(number1_string);
                sum2 = sum2 * 10 + number1_int;
            }
            sum1 += sum2;
        }
        System.out.println(sum1);
    }
}
