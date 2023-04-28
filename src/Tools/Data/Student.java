package Tools.Data;
import java.util.*;

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
     * Uses a fuzzy search
     * @param teacher_name Name of teacher
     */
    public ArrayList<Course> hasTeacher(String teacher_name){
        ArrayList<Course> selected_courses = new ArrayList<>();
        for (Course course: courses) {
            if(fuzzyCompare(teacher_name, course.getTeacher()) || fuzzyCompare(course.getTeacher(), teacher_name)) selected_courses.add(course);
        }
        return selected_courses;
    }


    /**
     * Provides a smarter comparison that can help prevent mistakes from messing up search
     * Does not care about case
     * Query is split into keywords by common delimiters. This means mistakes in ordering or spacing will not affect outcome.
     * @param query Usually what the user enters.
     * @param target What to compare to.
     * @return True if all keywords are present in target.
     */
    private static boolean fuzzyCompare(String query, String target){
        target = target.toLowerCase();
        query = query.toLowerCase();
        String[] query_terms = query.split("[,\\s:.]");
        for (String term: query_terms) {
            if(!target.contains(term)) return false;
        }
        return true;
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
