import java.util.Scanner;

public class assignment_3_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        int n = input.nextInt();    //构建m*n的数组
        int[][] land_number = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                land_number[i][j] = input.nextInt();
            }
        }
        //二维数组构建测试通过
        int starter = input.nextInt();
        int k = input.nextInt();
        int mark = 0;   //记录次数
        int x_starter = 0;
        int y_starter = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land_number[i][j] == starter) {
                    x_starter = i;
                    y_starter = j;
                }
            }
        }
        //竖直方向为x，水平方向为y
        int x = 0;  //记录应该是往右下还是左上
        int extra;
        while (mark < k) {
            if (x % 2 == 0) {
                for (int i = 0; i < 2; i++) {
                    if (i == 0) {
                        mark += x + 1;
                        if (x_starter < 0 || x_starter >= m || y_starter < 0 || y_starter >= n) {
                            mark -= (x + 1);
                        }
                        y_starter += x + 1;
                        if ((x_starter  < 0 || x_starter  >= m || y_starter-x-1 < 0 || y_starter-x-1 >= n) && y_starter <n ) {
                            mark += (y_starter + 1);
                        }
                        if ((x_starter  < 0 || x_starter >= m || y_starter-x-1 < 0 || y_starter-x-1 >= n) && y_starter >=n ){
                            mark+=n;
                        }
                        if (x_starter >= 0 && x_starter < m && y_starter - x - 1 >= 0 && y_starter - x - 1 < n && y_starter > n - 1) {
                            mark -= (y_starter - n + 1);
                        }
                        if (mark >= k) {
                            extra = mark - k;
                            y_starter -= extra;
                            break;
                        }
                    } else {
                        mark += x + 1;
                        if (x_starter < 0 || x_starter >= m || y_starter < 0 || y_starter >= n) {
                            mark -= (x + 1);
                        }
                        x_starter += (x + 1);
                        if ((x_starter -x-1< 0 || x_starter - x - 1 >= m || y_starter < 0 || y_starter >= n) && x_starter <m ) {
                            mark += (x_starter + 1);
                        }
                        if ((x_starter - x - 1 < 0 || x_starter - x - 1 >= m || y_starter < 0 || y_starter >= n) && x_starter >=m ){
                            mark+=m;
                        }
                        if (x_starter - x - 1 >= 0 && x_starter - x - 1 < m && y_starter >= 0 && y_starter < n && x_starter > m - 1) {
                            mark -= (x_starter - m + 1);
                        }
                        if (mark >= k) {
                            extra = mark - k;
                            x_starter -= extra;
                            break;
                        }
                    }
                }
            } else {
                for (int i = 0; i < 2; i++) {
                    if (i == 0) {
                        mark += x + 1;
                        if (x_starter < 0 || x_starter >= m || y_starter < 0 || y_starter >= n) {
                            mark -= (x + 1);
                        }
                        y_starter -= (x + 1);
                        if ((x_starter < 0 || x_starter  >= m || y_starter +x+1< 0 || y_starter+x+1 >= n) && y_starter >= 0 ) {
                            mark += (n - y_starter - 1);
                        }
                        if ((x_starter < 0 || x_starter >= m || y_starter+x+1 < 0 || y_starter+x+1 >= n) && y_starter < 0 ){
                            mark+=n;
                        }
                        if (x_starter >= 0 && x_starter < m && y_starter + x + 1 >= 0 && y_starter + x + 1 < n && y_starter < 0) {
                            mark -= y_starter;
                        }
                        if (mark >= k) {
                            extra = mark - k;
                            y_starter += extra;
                            break;
                        }
                    } else {
                        mark += x + 1;
                        if (x_starter < 0 || x_starter >= m || y_starter < 0 || y_starter >= n) {
                            mark -= (x + 1);
                        }
                        x_starter -= (x + 1);
                        if ((x_starter + x + 1 < 0 || x_starter + x + 1 >= m || y_starter < 0 || y_starter >= n) && x_starter >= 0 ) {
                            mark += (m - x_starter - 1);
                        }
                        if ((x_starter + x + 1 < 0 || x_starter + x + 1 >= m || y_starter < 0 || y_starter >= n) && x_starter < 0 ){
                            mark+=m;
                        }
                        if (x_starter + x + 1 >= 0 && x_starter + x + 1 < m && y_starter >= 0 && y_starter < n && x_starter < 0) {
                            mark -= x_starter;
                        }
                        if (mark >= k) {
                            extra = mark - k;
                            x_starter += extra;
                            break;
                        }
                    }
                }
            }
            x += 1;
        }
        int final_point = land_number[x_starter][y_starter];
        System.out.println(final_point);

    }
}
