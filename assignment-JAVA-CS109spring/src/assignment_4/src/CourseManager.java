import java.util.ArrayList;

public class CourseManager {
    private ArrayList<Course> courses;
    private ArrayList<Student> students;
    private boolean ifOpen;

    public CourseManager() {
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
        ifOpen = this.ifOpen;
    }
// setter for ifOpen

    public boolean getIfOpen() {
        return getIfOpen();
    }
// getter for ifOpen

    public void addCourse(Course course) {
        courses.add(course);
    }
// Register a course. Add a course object to courses and set the courseManager of the course object to this manager. It is guaranteed that all courseIDs are unique.

    public void addStudent(Student student) {
        students.add(student);
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
        int count = 0;
        int location1 = 0;
        for (Course course : courses) {
            if (course.getCourseID().equals(courseId)) {
                flag1 = true;
                location1=0;
            }
            if (course.getEnrollStudent().equals(student)) {
                flag2 = false;
            }
        }
        if (student.getCredits() >= (credits) && credits > 0) {
            flag3 = true;
        }
        if (flag1 && flag2 && flag3 && flag4){
            student.setCredits(student.getCredits()-credits);       //更新学生的credit
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
        int count = 0;
        int location1 = 0;
        for (Course course : courses) {
            count += 1;
            if (course.getCourseID().equals(courseId)) {
                flag1 = true;
                location1 = count;
            }
            if (course.getEnrollStudent().equals(student)) {
                flag2 = true;
            }
        }
        int credit1 = courses.get(location1).getCredits()
        if (student.getCredits()- credits > 0) {
            flag3 = true;
        }
        if (flag1 && flag2 && flag3 && flag4){

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
    public boolean dropStudentEnrollmentCourse(Student student, String courseId){

    }

    /**
     * Completes the course registration process. Change ifOpen to false.
     * This method resolves which students get into each course based on their bids and the course capacities. Students with higher bids are prioritized. The corresponding list in Student and Course should be updated.
     * Only successStudents in class Course and successCourses in class Student need to be updated.
     */
    public void finalizeEnrollments()

    /**
     * Retrieves a list of courses with associated credits for a given student.
     * Each String in the list includes the course ID and the points bid by the
     student in enrollCourses, follow the format: "courseID: enrollmentCredits"
     (without quotes).
     * Only available when ifOpen is true. Return null if ifOpen is false.
     * @return A list of Strings, each representing the courses and the respective
    credits the student enrolled.
     */
    public ArrayList<String> getEnrolledCoursesWithCredits(Student student)

}
