import java.util.ArrayList;

public class CourseManager {
    private ArrayList<Course> courses;
    private ArrayList<Student> students;
    private boolean ifOpen;

    public CourseManager() {
        this.ifOpen = true;
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }
    //constructer

    //以下均为method

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
// getter for courses

    public void setIfOpen(Boolean ifOpen) {
        this.ifOpen = ifOpen;
    }
// setter for ifOpen

    public boolean getIfOpen() {
        return ifOpen;
    }


// getter for ifOpen

    public void addCourse(Course course) {
        if (course.getMaxCapacity()>0) {
            courses.add(course);
            course.setCourseManager(this);
        }
    }
// Register a course. Add a course object to courses and set the courseManager of the course object to this manager. It is guaranteed that all courseIDs are unique.

    public void addStudent(Student student) {
        if (student.getCredits()>0) {
            students.add(student);
            student.setCourseManager(this);
        }
    }
// Register a course. Add a student object to students and set the courseManager of the student object to this manager. It is guaranteed that all studentIDs are unique.

    /**
     * Attempts to enroll a Student in a Course.
     * Enrollment will only be successful if the course exists, the student has not already enrolled, the credits is greater than 0, and they have enough credits to bid.
     * If successful, the student's credits will be reduced by the amount bid on the course. The corresponding list in Student and Course should be updated.
     * Only available when ifOpen is true. Return false if ifOpen is false.
     *
     * @return boolean  Returns true if student enroll this course successfully; otherwise, it returns false.
     */
    public boolean enrollStudentInCourse(Student student, String courseId, int credits) {
        boolean flag1 = false;
        boolean flag2 = true;
        boolean flag3 = false;
        boolean flag4 = this.getIfOpen();
        boolean flag5 = false;
        if (!flag4){
            return false;
        }
        int count = 0;
        int location1 = 0;
        int location2 = 0;
        for (Course course : courses) {
            if (course.getCourseID().equals(courseId)) {
                flag1 = true;
                location1 = count;
                ArrayList<Student> student1 = course.getEnrollStudent();
                for (int i = 0; i < student1.size(); i++) {
                    if (student1.get(i).getStudentID().equals(student.getStudentID())) {         //getenrollstudent里面存的是ID还是name？
                        location2 = i;
                        flag2 = false;
                    }
                }
            }
            count+=1;
        }
        if (student.getCredits() >= (credits) && credits > 0 && flag1 && flag2) {
            flag3 = true;
        }
        if (flag1 && flag2 && flag3 && flag4) {
            int initial = student.getCredits();
            student.setCredits(student.getCredits() - credits);       //更新学生的credit
            int creditttt = student.getCredits();
            student.getEnrollCourses().add(courses.get(location1));
            student.setEnrollCourses(student.getEnrollCourses());                            //更新学生课程
            courses.get(location1).getEnrollStudent().add(student);
            courses.get(location1).setEnrollStudent(courses.get(location1).getEnrollStudent());        //更新课程列表
            courses.get(location1).getCredits().add(credits);
            courses.get(location1).setCredits(courses.get(location1).getCredits());                  //更新课程credit
            flag5 = true;
        }
        return (flag5);
    }

    /**
     * Modifies the number of credits a student has bid on an already enrolled course.
     * The modification will only be successful if the course exists, the student is currently enrolled in the course,and the new bid is within the student's available credits. This can be used to increase or decrease the bid.
     * On a successful bid modification, the student's credits will be updated to reflect the new bid amount. The corresponding list in Student and Course should be updated.
     * Only available when ifOpen is true. Return false if ifOpen is false.
     *
     * @return boolean  Returns true if the bid modification was successful; otherwise, it returns false.
     */
    public boolean modifyStudentEnrollmentCredits(Student student, String courseId, int credits) {
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = this.getIfOpen();
        boolean flag5 = false;
        if (!flag4){
            return false;
        }
        int count = 0;
        int location1 = 0;
        int location2 = 0;
        for (Course course : courses) {
            if (course.getCourseID().equals(courseId)) {
                flag1 = true;
                location1 = count;
                ArrayList<Student> student1 = course.getEnrollStudent();
                for (int i = 0; i < student1.size(); i++) {
                    if (student1.get(i).getStudentID().equals(student.getStudentID())) {         //getenrollstudent里面存的是ID还是name？
                        location2 = i;
                        flag2 = true;
                    }
                }
            }
            count+=1;
        }
        int credit1 = courses.get(location1).getCredits().get(location2);//取出前一次的credit
        int credittt = student.getCredits();
        if (student.getCredits() + credit1 - credits >= 0 && flag1 && flag2) {
            flag3 = true;
        }
        if (flag1 && flag2 && flag3 && flag4) {
            int initial = student.getCredits();
            student.setCredits(student.getCredits() + credit1 - credits);         //更新学生的credit
            int final_credit = student.getCredits();
            //无需更新学生课程信息，本身就已经添加
            //无需更新课程列表，本身就已经添加
            courses.get(location1).getCredits().remove(location2);               //移除原有credit
            courses.get(location1).getCredits().add(location2, credits);
            courses.get(location1).setCredits(courses.get(location1).getCredits());    //在原有位置添加新的credits
            flag5 = true;
        }
        return (flag5);
    }

    /**
     * Drops a student's enrollment from a specific course.
     * The drop will succeed only if the course exists, and the student is currently enrolled in it.
     * If ifOpen is true, upon a successful drop, any credits the student had bid for this course will be refunded in full. The corresponding list in Student and Course should be updated.
     * Only available when ifOpen is true. Return false if ifOpen is false.
     *
     * @return boolean  Returns true if the student successfully drops the course; otherwise, it returns false.
     */
    public boolean dropStudentEnrollmentCourse(Student student, String courseId) {
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = this.getIfOpen();
        boolean flag4 = false;
        if(!flag3){
            return false;
        }
        int count = 0;
        int location1 = 0;
        int location2 = 0;
        int location3 = 0;
        for (Course course : courses) {
            if (course.getCourseID().equals(courseId)) {
                flag1 = true;
                location1 = count;
                ArrayList<Student> student1 = course.getEnrollStudent();
                ArrayList<Course> course1 = student.getEnrollCourses();
                for (int i = 0; i < student1.size(); i++) {
                    if (student1.get(i).getStudentID().equals(student.getStudentID())) {         //getenrollstudent里面存的是ID还是name？
                        location2 = i;
                        flag2 = true;
                    }
                }
                for (int i = 0; i < course1.size(); i++) {
                    if (course1.get(i).getCourseID().equals(courseId)) {
                        location3 = i;                           //检测该课程在学生已选课程中的位置
                    }
                }
            }
            count+=1;
        }
        int credit1 = courses.get(location1).getCredits().get(location2);      //取出前一次的credit
        if (flag1 && flag2 && flag3) {
            int initial = student.getCredits();
            student.setCredits(student.getCredits() + credit1);             //返还积分
            int final_credit = student.getCredits();
            student.getEnrollCourses().remove(location3);
            student.setEnrollCourses(student.getEnrollCourses());         //从学生的课程列表中删除
            courses.get(location1).getEnrollStudent().remove(location2);     //将该学生从课程列表中删除
            courses.get(location1).getCredits().remove(location2); //将该学生投递的积分从课程中删除
            int size = courses.get(location1).getCredits().size();
            flag4 = true;
        }
        return (flag4);
    }


    /**
     * Completes the course registration process. Change ifOpen to false.
     * This method resolves which students get into each course based on their bids and the course capacities. Students with higher bids are prioritized. The corresponding list in Student and Course should be updated.
     * Only successStudents in class Course and successCourses in class Student need to be updated.
     */
    public void finalizeEnrollments() {
        this.setIfOpen(false);
        for (int i = 0; i < courses.size(); i++) {
            int maxCapacity = courses.get(i).getMaxCapacity();
            ArrayList<Integer> credit_list = courses.get(i).getCredits();
            ArrayList<Student> student_list = courses.get(i).getEnrollStudent();
            int enroll_number = credit_list.size();
            //冒泡排序从大到小
            for (int j = 0; j < enroll_number; j++) {
                for (int k = 0; k < enroll_number - j-1; k++) {
                    if (credit_list.get(k) < credit_list.get(k + 1)) {
                        int temp = credit_list.get(k);
                        credit_list.set(k, credit_list.get(k + 1));
                        credit_list.set(k + 1, temp);
                        Student temp1 = student_list.get(k);
                        student_list.set(k, student_list.get(k + 1));
                        student_list.set(k + 1, temp1);
                    }
                }
            }
            int count = 0;
            int result = 0;
            for (int j = 0; j < enroll_number; j++) {
                int number1 = credit_list.get(j);
                int same_number = 0;
                for (int k = j + 1; k < enroll_number; k++) {
                    if (number1 == credit_list.get(k)) {
                        same_number += 1;
                    }
                }
                if (count + 1 + same_number > maxCapacity) {
                    result = count;
                    break;
                } else if (count + 1 +same_number == maxCapacity) {
                    result = count +1 +same_number;
                    break;
                } else {
                    count = count + 1 + same_number;
                }
            }
            ArrayList<Student> successstudent_list = new ArrayList<>();
            int q = 0;
            while (result > 0 && q < student_list.size()) {
                successstudent_list.add(student_list.get(q));
                ArrayList<Course> SUCCESS_COURSES = student_list.get(q).getSuccessCourses();
                SUCCESS_COURSES.add(courses.get(i));
                student_list.get(q).setSuccessCourses(SUCCESS_COURSES);           //成功选上的的学生添加课程
                result -= 1;
                q += 1;
            }
            courses.get(i).setSuccessStudents(successstudent_list);//成功选上课程的学生列表
        }
    }

    /**
     * Retrieves a list of courses with associated credits for a given student.
     * Each String in the list includes the course ID and the points bid by the
     * student in enrollCourses, follow the format: "courseID: enrollmentCredits"
     * (without quotes).
     * Only available when ifOpen is true. Return null if ifOpen is false.
     *
     * @return A list of Strings, each representing the courses and the respective
     * credits the student enrolled.
     */
    public ArrayList<String> getEnrolledCoursesWithCredits(Student student) {
        ArrayList<Course> success_class_list = student.getEnrollCourses();
        ArrayList<String> information = new ArrayList<>();
        if (!this.ifOpen){
            return null;
        }
        for (int i = 0; i < success_class_list.size(); i++) {
            String course_id = success_class_list.get(i).getCourseID();
            StringBuilder bid_credit_string = new StringBuilder();
            for (int j = 0; j < courses.size(); j++) {
                if(courses.get(j).getCourseID().equals(course_id)){
                    ArrayList<Student> success_student_list = courses.get(j).getEnrollStudent();
                    for (int k = 0; k < success_student_list.size(); k++) {
                        if (success_student_list.get(k).getStudentID().equals(student.getStudentID())){
                            int bid_credit = courses.get(j).getCredits().get(k);
                            String bid_credit_string1 = Integer.toString(bid_credit);
                            bid_credit_string.append(bid_credit_string1);
                            break;
                        }
                    }
                    break;
                }
            }
            String context = course_id + ": "+ bid_credit_string;
            information.add(context);
        }
        return information;
    }
}
