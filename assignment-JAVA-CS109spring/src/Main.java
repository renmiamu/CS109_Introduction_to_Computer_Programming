import java.util.Scanner;
public class Main {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            int cishu = input.nextInt();
            while (cishu-- > 0) {
                int number2 = input.nextInt();  //左端点
                int number1 = input.nextInt();//右端点
                int jilu = 0;//记录个数
                while (number2 <= number1) {
                    int number21 = number2;
                    if (number2 % 7 == 0)
                        jilu += 1;
                    else
                        while (number21 > 0) {
                            int yushu = number21 % 10;
                            if (yushu == 7) {
                                jilu += 1;
                                break;
                            }
                            number21 = number21 / 10;
                        }
                    number2 = number2 + 1;
                }
                System.out.println(jilu);
            }
        }
    }
