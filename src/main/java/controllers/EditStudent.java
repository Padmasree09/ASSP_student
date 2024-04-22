package controllers;

import java.io.IOException;

import dao.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Student;

@WebServlet("/update-student")
public class EditStudent extends HttpServlet {
    StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Student student = studentDAO.getStudent(id);

        req.setAttribute("student", student);
        req.getRequestDispatcher("edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = new Student();
        student.setEmail(req.getParameter("email"));
        student.setName(req.getParameter("name"));
        student.setPassword(req.getParameter("password"));
        student.setPhone(req.getParameter("phone"));
        student.setId(Integer.parseInt(req.getParameter("id")));

        System.out.println(student);
        boolean result = studentDAO.updateStudent(student);

        if(result) 
            req.getSession().setAttribute("update-student", "ok");
        
        else
            req.getSession().setAttribute("update-student", "failed");

        resp.sendRedirect("all-students");
    }
}
