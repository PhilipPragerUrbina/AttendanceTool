package Tools.Data;

/**
 * Represents an AP test time during which students will be absent
 * Immutable
 */
public class Test {
    private final int block;
    private final String date;
    private final String course_name;

    public Test(int block, String date, String course_name) {
        this.block = block;
        this.date = date;
        this.course_name = course_name;
    }


    public String getCourse_name() {
        return course_name;
    }

    public int getBlock() {
        return block;
    }

    public String getDate() {
        return date;
    }
}
