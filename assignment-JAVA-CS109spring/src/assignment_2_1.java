import java.util.Scanner;

public class assignment_2_1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int question_number = input.nextInt();
        Double[] question_score = new Double[question_number];
        int[] qi_answer = new int[question_number];
        int[] competitor_answer = new int[question_number];
        float qi_final = 0;
        float competitor_final = 0;
        for (int i = 0; i < question_number; i++) {
            question_score[i] = input.nextDouble();
        }
        for (int i = 0; i < question_number; i++) {
            qi_answer[i] = input.nextInt();
        }
        for (int i = 0; i < question_number; i++) {
            competitor_answer[i] = input.nextInt();
        }
        for (int j = 0; j < question_number; j++) {
            if (qi_answer[j] == 2)
                qi_final += question_score[j];
            else if (qi_answer[j] == 1)
                qi_final -= question_score[j];
            else if (qi_answer[j] == 0)
                qi_final -= question_score[j] / 2;
        }
        for (int j = 0; j < question_number; j++) {
            if (competitor_answer[j] == 2)
                competitor_final += question_score[j];
            else if (competitor_answer[j] == 1)
                competitor_final -= question_score[j];
            else if (competitor_answer[j] == 0)
                competitor_final -= question_score[j] / 2;
        }
        if (qi_final > competitor_final)
            System.out.print("Qi won");
        else if(qi_final==competitor_final)
            System.out.print("Qi need another round");
        else if(qi_final<competitor_final)
            System.out.print("Qi lost");
    }
}
