package controllers;

import java.io.IOException;

import dao.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete-student")
public class DeleteStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        StudentDAO studentDAO = new StudentDAO();

        boolean res = studentDAO.deleteStudent(id);

        if(res) 
            req.getSession().setAttribute("delete-student", "ok");
        
        else
            req.getSession().setAttribute("delete-student", "failed");

        resp.sendRedirect("all-students");
    }
}
