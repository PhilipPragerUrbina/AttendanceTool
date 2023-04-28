package Tools.Data;

/**
 * Represents and absence
 */
public class Absent {
    private final Student student;
    private final Course reason;
    private final Course missed;
    private final Test test;

    /**
     * Mark a student as missing a course
     * @param student Student missing course
     * @param missed Course that will be missed
     * @param reason Course that test belongs to
     * @param test Reason for missing
     */
    public Absent(Student student, Course reason, Course missed, Test test) {
        this.student = student;
        this.reason = reason;
        this.missed = missed;
        this.test = test;
    }

    @Override
    public String toString() {
        String out = "";
        out+= "Student: " + student.getFirst_name() + " " + student.getLast_name() + "\n";
        out += "ID: " + student.getStudent_id() + "\n";
        out += "Absent: " + missed.getCourseName() + "   block " + test.getBlock() + " " + test.getDate()  + " for " + reason.getCourseName() + "\n";
        return out;
    }

    public Student getStudent() {
        return student;
    }

    public Course getReason() {
        return reason;
    }

    public Course getMissed() {
        return missed;
    }

    public Test getTest() {
        return test;
    }
}
