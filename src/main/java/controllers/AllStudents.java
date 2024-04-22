package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Student;

@WebServlet("/all-students")
public class AllStudents extends HttpServlet {
    StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();

        if ("ok".equals(session.getAttribute("add-student")))
            out.print("<p>Student added successfully</p>");

        if ("failed".equals(session.getAttribute("add-student")))
            out.print("<p>Failed to add Student</p>");

        if ("ok".equals(session.getAttribute("delete-student")))
            out.print("<p>Student deleted successfully</p>");

        if ("failed".equals(session.getAttribute("delete-student")))
            out.print("<p>Failed to delete Student</p>");

        if ("ok".equals(session.getAttribute("update-student")))
            out.print("<p>Student updated successfully</p>");

        if ("failed".equals(session.getAttribute("update-student")))
            out.print("<p>Failed to update Student</p>");

        session.removeAttribute("update-student");
        session.removeAttribute("delete-student");
        session.removeAttribute("add-student");

        List<Student> students = studentDAO.allStudents();
        out.println("<table border='2'>");
        out.println("<tr>");
        out.println("<th>Id</th>");
        out.println("<th>Name</th>");
        out.println("<th>Email</th>");
        out.println("<th>Phone</th>");
        out.println("<th>Password</th>");
        out.println("<th>Action</th>");
        out.println("</tr>");

        for (int i = 0; i < students.size(); i++) {
            out.println("<tr>");
            out.println("<td>" + students.get(i).getId() + "</td>");
            out.println("<td>" + students.get(i).getName() + "</td>");
            out.println("<td>" + students.get(i).getEmail() + "</td>");
            out.println("<td>" + students.get(i).getPhone() + "</td>");
            out.println("<td>" + students.get(i).getPassword() + "</td>");
            out.println("<td>");
            out.println("<a href='delete-student?id=" + students.get(i) + "'>delete </a>");
            out.println("<a href='update-student?id=" + students.get(i) + "'>update </a>");
            out.println("</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.close();
    }
}
