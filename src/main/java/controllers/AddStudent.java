package controllers;

import java.io.IOException;

import dao.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Student;

@WebServlet("/add-student")
public class AddStudent extends HttpServlet {

    StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = new Student();
        student.setEmail(req.getParameter("email"));
        student.setName(req.getParameter("name"));
        student.setPassword(req.getParameter("password"));
        student.setPhone(req.getParameter("phone"));
        // student.setPhone(req.getParameter("phone"));

        System.out.println(student);
        boolean result = studentDAO.addStudent(student);

        if (result)
            req.getSession().setAttribute("add-student", "ok");

        else
            req.getSession().setAttribute("add-student", "failed");

        resp.sendRedirect("all-students");

    }
}
