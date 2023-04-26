package Tools.Data;

import java.util.Objects;

/**
 * Represents information associated with AP course
 * Immutable
 */
public class Course {
    private final String teacher;
    private final String course_name;
    private final int block;

    public Course(String teacher, String course_name, int block) {
        this.teacher = teacher;
        this.course_name = course_name;
        this.block = block;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getCourseName() {
        return course_name;
    }

    public int getBlock() {
        return block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return block == course.block && Objects.equals(teacher, course.teacher) && Objects.equals(course_name, course.course_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacher, course_name, block);
    }
}
