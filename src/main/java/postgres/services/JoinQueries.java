package postgres.services;

import postgres.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinQueries {
    private final Connection dbConnection;

    public JoinQueries(DatabaseConnection connection) {
        dbConnection = connection.connect();
    }

    public void getInstructorCourseDetails() {
        try {
            String sql = "SELECT id, instructor_name, course_names " +
                    "FROM instructor_course_details "; // Replace with your view name

            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String fullName = resultSet.getString("instructor_name");
                String courseNames = resultSet.getString("course_names");

                // Print the results
                System.out.println("Instructor Name: " + fullName);
                System.out.println("Course Names: " + courseNames);
                System.out.println();
            }

            // Close the resources
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve instructor and course details", e);
        }
    }


    public void getStudentCourseDetails() {
        try {
            String sql = "SELECT course_name, start_date, STRING_AGG(student_names, ', ') AS student_names " +
                    "FROM course_student " +
                    "GROUP BY course_name, start_date";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                String startDate = resultSet.getString("start_date");
                String studentNames = resultSet.getString("student_names");

                // Print the results
                System.out.println("Course Name: " + courseName);
                System.out.println("Start Date: " + startDate);
                System.out.println("Student Names: " + studentNames);
                System.out.println();
            }

            // Close the resources
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve student and course details", e);
        }
    }

    public void getInstructorStudentDetails() {
        try {
            String sql = "SELECT instructor_name, course_name, STRING_AGG(student_names, ', ') AS student_names " +
                    "FROM instructor_course_student " +
                    "GROUP BY instructor_name, course_name";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String instructorName = resultSet.getString("instructor_name");
                String courseName = resultSet.getString("course_name");
                String studentNames = resultSet.getString("student_names");

                // Print the results
                System.out.println("Instructor Name: " + instructorName);
                System.out.println("Course Name: " + courseName);
                System.out.println("Student Names: " + studentNames);
                System.out.println();
            }

            // Close the resources
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve instructor and student details", e);
        }
    }


    public void getStudentsEnrolledInIntermediateCourses() {
        try {
            String sql = "SELECT ic.course_name AS course_name, " +
                    "STRING_AGG(ic.student_names, ', ') AS student_names " +
                    "FROM intermediate_course_students ic " + // Replace with your view name
                    "GROUP BY ic.course_name";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                String studentNames = resultSet.getString("student_names");

                System.out.println("Course Name: " + courseName);
                System.out.println("Student Names: " + studentNames);
                System.out.println();
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve intermediate courses and students", e);
        }
    }
}
