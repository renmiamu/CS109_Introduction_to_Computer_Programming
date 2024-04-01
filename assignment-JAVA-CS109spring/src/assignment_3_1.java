import java.util.Scanner;

public class assignment_3_1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        String[][] initialNumber = new String[n][3];
        for (int i = 0; i < n; i++) {
            String information = input.nextLine();
            String[] array_information = information.split(",");
            initialNumber[i] = array_information;
        }
        //数据初始化
        int m = 0;  //course 数目
        for (int i = 0; i < n; i++) {
            if (initialNumber[i][0].equals("C")) {
                m += 1;
            }
        }
        int a = 0;
        String [][] array_course = new String [m][3];
        for (int i = 0; i < n; i++) {
            if (initialNumber[i][0].equals("C")) {
                array_course[a] = initialNumber[i];
                a+=1;
            }
        }
        //将课程信息单独提出来呈一个列表
        String [] middle = new String[3];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m-i-1; j++) {
                int s1 = Integer.parseInt(array_course[j][2].trim());
                int s2 = Integer.parseInt(array_course[j+1][2].trim());
                if (s1<s2) {
                    middle = array_course[j];
                    array_course[j]=array_course[j+1];
                    array_course[j+1]=middle;
                }
            }
        }
        //人数从大到小排序
        int b = 0; //theory 数目
        for (int i = 0; i < m; i++) {
            if (array_course[i][1].equals("theory")) {
                b += 1;
            }
        }

        int c = 0;
        String [][] theory = new String[b][3];
        for (int i = 0; i < m; i++) {
            if (array_course[i][1].equals("theory")) {
                theory[c] = array_course[i];
                c += 1;
            }
        }
        //构建theory_course数组，人数从大到小
        int d = 0; //lab 数目
        for (int i = 0; i < m; i++) {
            if (array_course[i][1].equals("lab")) {
                d += 1;
            }
        }

        int e = 0;
        String [][] lab = new String[d][3];
        for (int i = 0; i < m; i++) {
            if(array_course[i][1].equals("lab")) {
                lab[e] = array_course[i];
                e += 1;
            }
        }
        //构建lab_course数组，人数从大到小
        int f = 0; //art 数目
        for (int i = 0; i < m; i++) {
            if (array_course[i][1].equals("art")) {
                f += 1;
            }
        }

        int g = 0;
        String [][] art = new String[f][3];
        for (int i = 0; i < m; i++) {
            if(array_course[i][1].equals("art")) {
                art[g] = array_course[i];
                g += 1;
            }
        }
        //构建lab_course数组，人数从大到小
        int people_theory;
        int x = 0;   //记录是否每一个课程都有对应的课程
        for (int i = 0; i < b; i++) {
            people_theory = Integer.parseInt(theory[i][2].trim());
            for (int j = 0; j < n; j++) {
                int people_theory_R=Integer.parseInt(initialNumber[j][2].trim());
                if (initialNumber[j][0].equals("R") && initialNumber[j][1].equals("theory") && people_theory_R>=people_theory){
                    x+=1;
                    initialNumber[j][2]="0";
                    break;
                }
            }
        }
        boolean flag1 = true;
        if (x!=b){
            flag1 = false;
        }
        //检测theory课程

        int people_lab;
        int y = 0;   //记录是否每一个课程都有对应的课程
        for (int i = 0; i < d; i++) {
            people_lab = Integer.parseInt(lab[i][2].trim());
            for (int j = 0; j < n; j++) {
                int people_lab_R=Integer.parseInt(initialNumber[j][2].trim());
                if (initialNumber[j][0].equals("R") && initialNumber[j][1].equals("lab") && people_lab_R>=people_lab){
                    y+=1;
                    initialNumber[j][2]="0";
                    break;
                }
            }
        }


        boolean flag2 = true;
        if (y!=d){
            flag2 = false;
        }
        //检测lab课程
        int people_art;
        int z = 0;   //记录是否每一个课程都有对应的课程
        for (int i = 0; i < f; i++) {
            people_art = Integer.parseInt(art[i][2].trim());
            for (int j = 0; j < n; j++) {
                int people_art_R=Integer.parseInt(initialNumber[j][2].trim());
                if (initialNumber[j][0].equals("R") && initialNumber[j][1].equals("art") && people_art_R>=people_art){
                    z+=1;
                    initialNumber[j][2]="0";
                    break;
                }
            }
        }
        boolean flag3 = true;
        if (z!=f){
            flag3 = false;
        }
        if (flag1 && flag2 && flag3){
            System.out.print("Yes");
        }
        else{
            System.out.print("No");
        }
    }
}
