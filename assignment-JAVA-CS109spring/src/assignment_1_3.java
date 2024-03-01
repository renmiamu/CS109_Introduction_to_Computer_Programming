import java.util.Scanner;
public class assignment_1_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cishu = input.nextInt();//测试数据数
        while (cishu-- > 0) {
            int start_year = input.nextInt();
            int start_month = input.nextInt();
            int start_day = input.nextInt();
            int end_year = input.nextInt();
            int end_month = input.nextInt();
            int end_day = input.nextInt();
            int middle = start_year; //遍历中间的年份
            int time = 0; //时间
            int minus = 0;//开始年份到1月1日的时间
            int plus = 0;//结束年份到1月1日的时间
            while (middle < end_year) {
                if (middle % 13 == 0)
                    time += 917;
                else
                    time += 916;//计算1月1日之间的时间
                middle += 1;
            }
            minus = 61 * (start_month - 1) + start_day;
            if (start_month > 5)
                minus += 1;
            if (start_month > 3)
                if (start_year % 13 == 0) {
                    minus += 1;
                }
            plus = 61 * (end_month - 1) + end_day;
            if (end_month > 5)
                plus += 1;
            if (end_month > 3)
                if (end_year % 13 == 0) {
                    plus += 1;
                }
            int result = time - minus + plus;
            System.out.println(result);
        }
    }
}