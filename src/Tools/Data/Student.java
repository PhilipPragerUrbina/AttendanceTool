package Tools.Data;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a single student and associated data
 */
public class Student {
    private int student_id;
    private String first_name;
    private String last_name;
    private Set<Course> courses;

    public Student(int student_id, String first_name, String last_name) {
        this.student_id = student_id;
        this.first_name = first_name;
        this.last_name = last_name;
        courses = new HashSet<>();
    }

    /**
     * Add a course to the students list of courses
     * Will be ignored if already in student's list.
     */
    public void addCourse(Course course){
        courses.add(course);
    }

    /**
     * Get all courses the student has with a given teacher
     * @param teacher_name Name of teacher
     */
    public ArrayList<Course> hasTeacher(String teacher_name){
        ArrayList<Course> selected_courses = new ArrayList<>();
        for (Course course: courses) {
            if(course.getTeacher().equals(teacher_name)) selected_courses.add(course);
        }
        return selected_courses;
    }

    public int getStudent_id() {
        return student_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    //Hash and equals use student ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return student_id == student.student_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(student_id);
    }
}
