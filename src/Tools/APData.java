package Tools;

import Tools.Data.Test;
import Tools.Data.Course;
import Tools.Data.Student;
import Tools.IO.CSV;

import java.util.*;

/**
 * API for querying data
 */
public class APData {

    private final HashMap<Integer, Student> students = new HashMap<>(); //ID is key
    private final ArrayList<Test> tests = new ArrayList<>() ;

    /**
     * Parse data from CSV file
     * @param data_file File containing data in CSV format
     *                  Must have the following columns in order: student id, last name, first name, course, teacher, block
     *                  Expects first line to contain the column names
     * @param timings_file File containing CSV data for AP schedule
     *                     Must have the following columns in order: Course name, date, block missed
     *                     Multiple entries can have the same course name
     *                     Expects first line to contain the column names
     * @throws  IllegalArgumentException data file is not valid
     */
    public APData(CSV data_file, CSV timings_file) throws IllegalArgumentException{
       String[] first_line = data_file.getNextRecord();
       //todo validate column names
        while(true){
            String[] line = data_file.getNextRecord();
            if(line == null) break;
            int student_id = Integer.parseInt(line[0].trim());
            if(!students.containsKey(student_id)){ //Add student if they do not exist yet in the database
                students.put(student_id, new Student(student_id, line[2].trim(), line[1].trim()) );
            }
            students.get(student_id).addCourse(new Course(line[4].trim(), line[3].trim(), Integer.parseInt(line[5].trim()))); //Add course to student
        }

        first_line = timings_file.getNextRecord();
        //todo validate column names
        while(true){
            String[] line = timings_file.getNextRecord();
            if(line == null) break;
           tests.add(new Test(Integer.parseInt(line[2].trim()), line[1].trim(), line[0].trim()));
        }

    }

    /**
     * Get student info
     * @param student_id ID of student
     * @return Student or null if not found
     */
    public Student getStudent(int student_id){
        return students.get(student_id);
    }

    /**
     * Get all students that have a given teacher
     * @param teacher_name Name of teacher
     */
    public ArrayList<Student> getStudentsFromTeacher(String teacher_name){
        ArrayList<Student> students_with_teacher = new ArrayList<>();
        for (Student student: students.values()) {
           if(!student.hasTeacher(teacher_name).isEmpty()){
               students_with_teacher.add(student);
           }
        }
        return students_with_teacher;
    }

    /**
     * Get list of tests matching course_name
     */
    ArrayList<Test> getTests(String course_name){
        ArrayList<Test> found_tests = new ArrayList<>();
        for (Test test : tests) {
            if(test.getCourse_name().equals(course_name)){
                found_tests.add(test);
            }
        }
        return found_tests;
    }
    public Collection<Student> getStudents(){
        return students.values();
    }
}
