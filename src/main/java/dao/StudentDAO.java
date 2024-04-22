package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Student;

public class StudentDAO {
    private final String INSERT_QUERY = "INSERT INTO users(name, email, phone, password) VALUES(?, ?, ?, ?)";
    private final String SELECT_QUERY = "SELECT * FROM users";
    private final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
    private final String SELECT_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    private final String UPDATE_QUERY = "UPDATE users SET name=?, email=?, phone=?, password=? WHERE id = ?";

    public boolean addStudent(Student student) {
        try {
            Connection connection = DatabaseUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getPhone());
            statement.setString(4, student.getPassword());

            int rows = statement.executeUpdate();

            statement.close();
            connection.close();

            if (rows > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

    public List<Student> allStudents() {
        List<Student> students = new ArrayList<>();

        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SELECT_QUERY);
            while (rs.next()) {
                Student student = new Student();

                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));

                students.add(student);
            }

            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return students;

    }

    public boolean deleteStudent(int id) {
        try {
            Connection connection = DatabaseUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, id);

            int rows = statement.executeUpdate();

            statement.close();
            connection.close();

            if (rows > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

    public Student getStudent(int id) {
        Student student = null;
        try {
            Connection connection = DatabaseUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery(SELECT_QUERY);
            while (rs.next()) {
                student = new Student();

                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));
            }

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return student;
    }

    public boolean updateStudent(Student student) {
        try {
            Connection connection = DatabaseUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getPhone());
            statement.setString(4, student.getPassword());
            statement.setInt(5, student.getId());

            int rows = statement.executeUpdate();

            statement.close();
            connection.close();

            if (rows > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

}
