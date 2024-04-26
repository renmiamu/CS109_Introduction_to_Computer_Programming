
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class CourseManagerTest {

    private CourseManager courseManager;
    private Student student1;
    private Student student2;
    private Student student3;
    private Course course1;
    private Course course2;

    @BeforeEach
    void setUp() {
        courseManager = new CourseManager();
        courseManager.setIfOpen(true);

        student1 = new Student("s001", "student1@example.com", "Student One", 100);
        student2 = new Student("s002", "student2@example.com", "Student Two", 120);
        student3 = new Student("s003", "student3@example.com", "Student Three", 150);

        course1 = new Course("c001", "Course One", 2);
        course2 = new Course("c002", "Course Two", 3);

        courseManager.addStudent(student1);
        courseManager.addStudent(student2);
        courseManager.addStudent(student3);
        courseManager.addCourse(course1);
        courseManager.addCourse(course2);
    }

    @AfterEach
    void tearDown() {
        courseManager = null;
        student1 = null;
        student2 = null;
        student3 = null;
        course1 = null;
        course2 = null;
    }

    @Test
    @DisplayName("test11()")
    void testCanBidOnFullCourse() {
        assertTrue(student1.enrollCourse("c001", 50));
        assertTrue(student2.enrollCourse("c001", 70));
        assertTrue(student3.enrollCourse("c001", 90)); // Student3 makes a higher bid despite the course being "full"

        // Should return true since it's within bidding period
        assertTrue(student1.modifyEnrollCredit("c001", 100)); // Student1 outbids Student3

        courseManager.finalizeEnrollments();

        // Verify that Student1 and Student3 are the ones enrolled after bidding
        assertTrue(course1.getSuccessStudents().contains(student1));
        assertFalse(course1.getSuccessStudents().contains(student2)); // Student2 should be outbid and thus removed
        assertTrue(course1.getSuccessStudents().contains(student3));
    }

    @Test
    @DisplayName("test12()")
    void testCanDecreaseEnrolledCourseBidCredits() {
        assertTrue(student1.enrollCourse("c001", 50));
        assertTrue(student1.modifyEnrollCredit("c001", 30)); // Student1 decreases the bid

        // Verify new credits after decreasing the bid
        assertEquals(30, (int) course1.getCredits().get(course1.getEnrollStudent().indexOf(student1)));
    }

    @Test
    @DisplayName("test13()")
    void testBiddingMoreCanChangeEnrollments() {
        assertTrue(student1.enrollCourse("c001", 40)); // Student One enrolls with 40 credits
        assertTrue(student2.enrollCourse("c001", 60)); // Student Two enrolls with 60 credits
        assertTrue(student3.enrollCourse("c001", 80)); // Now course "c001" is full, but bidding is still possible

        // Student1 increases bid, should be able to replace student2
        assertTrue(student1.modifyEnrollCredit("c001", 90));

        courseManager.finalizeEnrollments();

        assertTrue(course1.getSuccessStudents().contains(student1));
        assertFalse(course1.getSuccessStudents().contains(student2)); // Student2 should be kicked out because they have the lowest bid now
        assertTrue(course1.getSuccessStudents().contains(student3));
    }

    @Test
    @DisplayName("test14()")
    void testOperationsAfterClose() {
        courseManager.finalizeEnrollments();

        assertFalse(courseManager.getIfOpen());
        assertFalse(student1.enrollCourse("c001", 50));
        assertFalse(student1.modifyEnrollCredit("c001", 60));
        assertFalse(student1.dropEnrollCourse("c001"));

        // The setups already ensure that no operations have actually affected the enrolments
    }

    @Test
    @DisplayName("test15()")
    void testFinalEnrollmentListAndStudentCredits() {
        assertTrue(student1.enrollCourse("c001", 50));
        assertTrue(student2.enrollCourse("c001", 60));
        assertTrue(student3.enrollCourse("c001", 80));

        assertTrue(student1.modifyEnrollCredit("c001", 90));
        assertTrue(student2.modifyEnrollCredit("c001", 70));

        student3.dropEnrollCourse("c001"); // Student3 drops, should get a full refund
        assertEquals(150, student3.getCredits());

        courseManager.finalizeEnrollments();

        assertTrue(course1.getSuccessStudents().contains(student1));
        assertTrue(course1.getSuccessStudents().contains(student2));
        assertEquals(100 - 90, student1.getCredits());
        assertEquals(120 - 70, student2.getCredits());
    }

    @Test
    @DisplayName("test16()")
    void testEnrollModifyCreditsAndDropCourses() {
        // Students enroll in courses
        assertTrue(student1.enrollCourse("c001", 50));
        assertTrue(student2.enrollCourse("c001", 70));
        assertTrue(student3.enrollCourse("c002", 100));
        assertEquals(50, student1.getCredits());
        assertEquals(50, student2.getCredits());
        assertEquals(50, student3.getCredits());

        // Students modify their credits for a course
        assertTrue(student1.modifyEnrollCredit("c001", 20));
        assertTrue(student2.modifyEnrollCredit("c001", 60));
        assertEquals(80, student1.getCredits());
        assertEquals(60, student2.getCredits());

        // Student 3 decides to drop course2
        assertTrue(student3.dropEnrollCourse("c002"));
        assertEquals(150, student3.getCredits()); // Student 3 should have all credits refunded

        assertTrue(student3.enrollCourse("c001", 100));
        // Finalize enrollments
        courseManager.finalizeEnrollments();

        // Check the success lists
        assertTrue(course1.getSuccessStudents().contains(student2));
        assertFalse(course1.getSuccessStudents().contains(student1)); // Student 1 has lower credits than Student 2
        assertTrue(course1.getSuccessStudents().size() <= course1.getMaxCapacity());

        // Student3 dropped the course so they should not appear in the success list
        assertFalse(course2.getSuccessStudents().contains(student3));
    }

    @Test
    @DisplayName("test17()")
    void testFinalizingEnrollmentsWithCompetingCredits() {
        // Three students enroll into Course 1, which has a capacity of 2
        assertTrue(student1.enrollCourse("c001", 40)); // Student One enrolls with 40 credits
        assertTrue(student2.enrollCourse("c001", 30)); // Student Two enrolls with 30 credits
        assertTrue(student3.enrollCourse("c001", 60)); // Student Three enrolls with 60 credits

        courseManager.finalizeEnrollments();

        // Only Student Three and Student One should be successful due to their higher bids
        assertTrue(course1.getSuccessStudents().contains(student3));
        assertTrue(course1.getSuccessStudents().contains(student1));
        assertFalse(course1.getSuccessStudents().contains(student2));

        // The capacity should not be exceeded
        assertEquals(2, course1.getSuccessStudents().size());
    }

    @Test
    @DisplayName("test18()")
    void testCreditsRefundAfterDroppingCourses() {
        student1.enrollCourse("c001", 40);
        student2.enrollCourse("c001", 30);
        student3.enrollCourse("c001", 60);

        student2.dropEnrollCourse("c001");
        student3.dropEnrollCourse("c001");

        // Ensure that after dropping the courses, students have all their credits refunded
        assertEquals(120, student2.getCredits());
        assertEquals(150, student3.getCredits());
    }

    @Test
    @DisplayName("test19()")
    void testStudentCoursesWithScoresAfterChanges() {
        // Student1 enrolls in course1 with some credits
        student1.enrollCourse(course1.getCourseID(), 50);

        // Student1 modifies the enrollment credits
        student1.modifyEnrollCredit(course1.getCourseID(), 70);

        // Validate that the list contains updated credits
        List<String> student1CoursesWithScoresBeforeDropping = student1.getCoursesWithScores();
        assertEquals(1, student1CoursesWithScoresBeforeDropping.size());
        assertTrue(student1CoursesWithScoresBeforeDropping.contains("c001: 70"));

        // Then, student1 decides to drop course1
        student1.dropEnrollCourse(course1.getCourseID());

        // Validate that the student no longer has scores associated with course1
        List<String> student1CoursesWithScoresAfterDropping = student1.getCoursesWithScores();
        assertEquals(0, student1CoursesWithScoresAfterDropping.size());
        assertFalse(student1CoursesWithScoresAfterDropping.contains("c001: 70"));

        // To further ensure consistency, let's have student1 enroll in course2 and check scores
        student1.enrollCourse(course2.getCourseID(), 40);
        List<String> student1CoursesWithScoresFinal = student1.getCoursesWithScores();
        assertEquals(1, student1CoursesWithScoresFinal.size());
        assertTrue(student1CoursesWithScoresFinal.contains("c002: 40"));
    }

    @Test
    void test20() {
        student1.enrollCourse("c001", 20);
        student2.enrollCourse("c001", 10);
        student3.enrollCourse("c001", 10);
        // Act
        courseManager.finalizeEnrollments();

        // Assert
        // Should have 0 successful students since all scores are tied and capacity is exceeded.
        assertEquals(1, course1.getSuccessStudents().size());

        // Students should be removed from the enrolled list.
        assertTrue(course1.getSuccessStudents().contains(student1));
        assertFalse(course1.getSuccessStudents().contains(student2));
        assertFalse(course1.getSuccessStudents().contains(student3));
    }

    @Test
    void extraTest3(){
        Student s1 = new Student("s1", "xxx", "A", 40);
        Student s2 = new Student("s2", "xxx", "A", 40);
        Student s3 = new Student("s3", "xxx", "A", 40);
        Student s4 = new Student("s4", "xxx", "A", 40);
        Student s5 = new Student("s5", "xxx", "A", 40);
        Student s6 = new Student("s6", "xxx", "A", 40);
        Course c1 = new Course("c1", "CS111", 4);
        courseManager.addStudent(s1);
        courseManager.addStudent(s2);
        courseManager.addStudent(s3);
        courseManager.addStudent(s4);
        courseManager.addStudent(s5);
        courseManager.addStudent(s6);
        courseManager.addCourse(c1);
        assertTrue(s1.enrollCourse("c1", 15));
        assertTrue(s2.enrollCourse("c1", 15));
        assertTrue(s3.enrollCourse("c1", 10));
        assertTrue(s4.enrollCourse("c1", 10));
        //assertTrue(s5.enrollCourse("c1", 10));
        //assertTrue(s6.enrollCourse("c1",1 ));
        courseManager.finalizeEnrollments();
        assertEquals(4, c1.getSuccessStudents().size());
    }
}

class Course {
    private String courseID;
    private String courseName;
    private int maxCapacity;
    private CourseManager courseManager;
    private ArrayList<Student> enrollStudent;
    private ArrayList<Integer> credits;
    private ArrayList<Student> successStudents;

    public Course(String courseID, String courseName, int maxCapacity) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
        this.courseManager = null;
        this.enrollStudent = new ArrayList<>();
        this.credits = new ArrayList<>();
        this.successStudents = new ArrayList<>();
    }

    public void setEnrollStudent(ArrayList<Student> enrollStudent) {
        this.enrollStudent = enrollStudent;
    }

    public void setCredits(ArrayList<Integer> credits) {
        this.credits = credits;
    }

    public void setCourseManager(CourseManager courseManager) {
        this.courseManager = courseManager;
    }

    public ArrayList<Integer> getCredits() {
        return credits;
    }

    public ArrayList<Student> getEnrollStudent() {
        return enrollStudent;
    }

    public String getCourseID() {
        return courseID;
    }

    public ArrayList<Student> getSuccessStudents() {
        return successStudents;
    }

    public void setSuccessStudents(ArrayList<Student> successStudents) {
        this.successStudents = successStudents;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}


class Student {
    private String studentID;
    private String email;
    private String name;
    private CourseManager courseManager;
    private int credits;
    private ArrayList<Course> enrollCourses;
    private ArrayList<Course> successCourses;

    public Student(String studentID, String email, String name, int credits) {
        this.studentID = studentID;
        this.email = email;
        this.name = name;
        this.courseManager = null;
        this.credits = credits;
        this.enrollCourses = new ArrayList<>();
        this.successCourses = new ArrayList<>();
    }

    public void setCourseManager(CourseManager courseManager) {
        this.courseManager = courseManager;
    }

    public ArrayList<Course> getEnrollCourses() {
        return enrollCourses;
    }

    public boolean enrollCourse(String courseId, int credits) {
        return courseManager.enrollStudentInCourse(this, courseId, credits);
    }

    public void setEnrollCourses(ArrayList<Course> enrollCourses) {
        this.enrollCourses = enrollCourses;
    }

    public boolean modifyEnrollCredit(String courseId, int credits) {
        return courseManager.modifyStudentEnrollmentCredits(this, courseId, credits);
    }

    public boolean dropEnrollCourse(String courseId) {
        return courseManager.dropStudentEnrollmentCourse(this, courseId);
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public ArrayList<Course> getSuccessCourses() {
        return successCourses;
    }

    public void setSuccessCourses(ArrayList<Course> successCourses) {
        this.successCourses = successCourses;
    }

    public ArrayList<String> getCoursesWithScores() {
        return courseManager.getEnrolledCoursesWithCredits(this);
    }

    public String getStudentID() {
        return this.studentID;
    }
}

