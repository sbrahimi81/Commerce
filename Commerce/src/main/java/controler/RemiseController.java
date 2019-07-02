/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOException;
import model.DataSourceFactory;
import model.RemiseDAO;
import model.RemiseEntity;

/**
 *
 * @author sbrahimi
 */
public class RemiseController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            RemiseDAO dao = new RemiseDAO(DataSourceFactory.getDataSource());
            List<RemiseEntity> rows = dao.getAllDiscounts();
            request.setAttribute("rows", rows);

        } catch (DAOException ex) {
            request.setAttribute("error", true);
        }

        request.getRequestDispatcher("RemiseView.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            RemiseDAO dao = new RemiseDAO(DataSourceFactory.getDataSource());
            switch (request.getParameter("action")) {
                case "ADD":
                    try {

                        String code = request.getParameter("add-code");
                        float taux = Float.parseFloat(request.getParameter("add-taux"));
                        if (taux >= 0 && taux <= 100 && code.length() == 1 && Pattern.compile("[A-Z]").matcher(code).find()) {

                            dao.addDiscount(code, taux);
                            request.setAttribute("message", "La remise a bien été ajoutée.");

                        } else {

                            request.setAttribute("error", "Le code doit être compris dans [A-Z]. Le taux doit être compris entre 0 et 100%.");

                        }

                    } catch (NumberFormatException ex) {
                        request.setAttribute("error", ex.toString());
                    }

                    break;

                case "DELETE":
                    try {

                        dao.deleteDiscount(request.getParameter("code"));
                        request.setAttribute("message", "La remise a bien été supprimée.");

                    } catch (NumberFormatException ex) {
                        request.setAttribute("error", ex.toString());
                    }

                    break;

                case "UPDATE":

                    break;

            }

        } catch (SQLException ex) {

            request.setAttribute("error", ex.toString());

        }

        this.doGet(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
