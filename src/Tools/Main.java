package Tools;

import Tools.Data.Course;
import Tools.Data.Student;
import Tools.Data.Test;
import Tools.IO.CSV;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Optional command line args. Data file then schedule file.
        CSV data_file = null;
        CSV timings_file = null;
        if(args.length == 2){
            try {
                data_file = new CSV(args[0]);
                timings_file = new CSV(args[1]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        Scanner input = new Scanner(System.in);
        try {
            if(args.length != 2) {
                System.out.println("Enter file path of data CSV: ");
                data_file = new CSV(input.nextLine());
                System.out.println("CSV opened!");
                System.out.println("Enter file path of schedule CSV: ");
                timings_file = new CSV(input.nextLine());
                System.out.println("CSV opened!");
            }
            APData data= new APData(data_file,timings_file);
            System.out.println("Data parsed!");
            String teacher_name = null;
            while (true){
                System.out.println("Enter teacher name as given in CSV: ");
                 teacher_name = input.nextLine();
                 if(!data.getStudentsFromTeacher(teacher_name).isEmpty()) break;
                System.out.println("No students found under this name. Either no students have a course with this teacher, or the name is spelled wrong. ");
            }
            for (Student student: data.getStudentsFromTeacher(teacher_name)) {
                    for (Course course: student.getCourses() ) {
                        for (Test test: data.getTests(course.getCourseName())) {
                            for (Course is_conflicted: student.hasTeacher(teacher_name) ) {
                                if(is_conflicted.getBlock() == test.getBlock()){
                                    System.out.println("Student: " + student.getFirst_name() + " " + student.getLast_name() );
                                    System.out.println("ID: " + student.getStudent_id());
                                    System.out.println("Absent: " + is_conflicted.getCourseName() + "   block " + test.getBlock() + " " + test.getDate()  + " for " + course.getCourseName());
                                    System.out.println("\n");
                                }
                            }
                        }
                    }
            }

        } catch (FileNotFoundException | IllegalArgumentException e) {
            System.err.println(  e.getMessage());
        }
    }
}
