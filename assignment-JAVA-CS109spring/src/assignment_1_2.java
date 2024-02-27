import java.util.Scanner;
public class assignment_1_2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cishu = input.nextInt();//检测次数
        while (cishu-->0){
            int number = input.nextInt();
            int power = 0;
            int result =0;
            while (number >0){
                int yushu = number%7;
                result += yushu*(int)Math.pow(10,power);
                power+=1;
                number=number/7;
            }
        System.out.println(result);
        }
    }
}
