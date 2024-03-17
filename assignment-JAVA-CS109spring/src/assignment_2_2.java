import java.util.Scanner;

public class assignment_2_2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] movie_list= new int[1000];
        int qi_number = input.nextInt();
        for (int i = 0; i < qi_number; i++) {
            int qi_movie = input.nextInt();
            movie_list[qi_movie-1]+=1;
        }
        int one_friend_number = input.nextInt();
        for (int i = 0; i < one_friend_number; i++) {
            int one_movie = input.nextInt();
            movie_list[one_movie-1]+=1;
        }
        int two_friend_number = input.nextInt();
        for (int i = 0; i < two_friend_number; i++) {
            int two_movie = input.nextInt();
            movie_list[two_movie-1]+=1;
        }
        int total = 0;
        for (int i = 0; i < 1000; i++) {
            if (movie_list[i]==3)
                total+=1;
        }
        System.out.print(total);
    }
}
