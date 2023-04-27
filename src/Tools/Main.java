package Tools;

import Tools.Data.Course;
import Tools.Data.Student;
import Tools.Data.Test;
import Tools.IO.CSV;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /**
     * This application allows one to get a list of students absent due to AP test
     * @param args Accepts 2 optional command line args.
     *             1. Location of student course data csv file
     *             2. Location of AP schedule CSV file
     *              If not specified in args, the user will be prompted.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {

            CSV data_file = null;
            if(args.length > 0){
                data_file = new CSV(args[0]);
            }else{
                System.out.println("Enter file path of data CSV: ");
                data_file = new CSV(input.nextLine());
                System.out.println("CSV opened!");
            }

            CSV timings_file = null;
            if(args.length > 1){
                timings_file = new CSV(args[1]);
            }else{
                System.out.println("Enter file path of schedule CSV: ");
                timings_file = new CSV(input.nextLine());
                System.out.println("CSV opened!");
            }

            APData data= new APData(data_file,timings_file);
            System.out.println("Data parsed!");
            String teacher_name = null;
            while (true){
                System.out.println("Enter teacher name: ");
                 teacher_name = input.nextLine();
                 if(!data.getStudentsFromTeacher(teacher_name).isEmpty()) break;
                System.out.println("No students found under this name. Either no students have a course with this teacher, or the name is spelled wrong. ");
            }

            for (Student student: data.getStudentsFromTeacher(teacher_name)) { //Get all students that have teacher
                    for (Course course: student.getCourses() ) { //Get all the courses that student has
                        for (Test test: data.getTests(course.getCourseName())) { //Get list of tests for that course
                            printConflicts(teacher_name, student, course, test);
                        }
                    }
            }

        } catch (FileNotFoundException | IllegalArgumentException e) {
            System.err.println(  e.getMessage());
        }
    }

    /**
     * Print the absences
     * @param teacher_name Name of teacher
     * @param student Student in question
     * @param course Course that test belongs to
     * @param test Test that may cause absences
     */
    private static void printConflicts(String teacher_name, Student student, Course course, Test test) {
        for (Course is_conflicted: student.hasTeacher(teacher_name) ) { //Get possible classes that may be missed
            if(is_conflicted.getBlock() == test.getBlock()){ // Is missed
                System.out.println("Student: " + student.getFirst_name() + " " + student.getLast_name() );
                System.out.println("ID: " + student.getStudent_id());
                System.out.println("Absent: " + is_conflicted.getCourseName() + "   block " + test.getBlock() + " " + test.getDate()  + " for " + course.getCourseName());
                System.out.println("\n");
            }
        }
    }
}
