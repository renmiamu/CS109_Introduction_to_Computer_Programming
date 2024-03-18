import java.util.Scanner;

public class assignment_2_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int balloon_number = input.nextInt();
        int m = input.nextInt();
        int n = input.nextInt();
        int[][] array_big = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                array_big[i][j] = input.nextInt();
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (array_big[i][j] == 1 && i == 0 && array_big[i + 1][j] != 1) {
                    array_big[i + 1][j] = 2;
                } else if (array_big[i][j] == 1 && i == m - 1 && array_big[i - 1][j] != 1) {
                    array_big[i - 1][j] = 2;
                } else if (array_big[i][j] == 1 && i > 0 && i < m - 1) {
                    if (array_big[i - 1][j] != 1) {
                        array_big[i - 1][j] = 2;
                    }
                    if (array_big[i + 1][j] != 1) {
                        array_big[i + 1][j] = 2;
                    }
                }
            }
        }
        int available = 0;
        int jishu = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (array_big[j][i] == 1 || array_big[j][i] == 2) {
                    if (jishu % 2 == 0)
                        available += jishu / 2;
                    else
                        available += (jishu + 1) / 2;
                    jishu = 0;
                }
                if (array_big[j][i] == 0)
                    jishu += 1;
                if (j == m - 1) {
                    if (jishu % 2 == 0)
                        available += jishu / 2;
                    else
                        available += (jishu + 1) / 2;
                    jishu = 0;
                }
            }
        }
        if (available >= balloon_number)
            System.out.print("True");
        else
            System.out.print("False");
    }
}
