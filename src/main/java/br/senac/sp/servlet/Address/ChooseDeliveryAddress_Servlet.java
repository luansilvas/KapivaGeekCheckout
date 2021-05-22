/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet.Address;

import br.senac.sp.model.Address;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luans
 */
public class ChooseDeliveryAddress_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao = request.getSession();

        if (sessao.getAttribute("user") == null) {
            request.getRequestDispatcher("/UserLogin.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/chooseDeliveryAddress.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("user") == null) {
            request.getRequestDispatcher("/WEB-INF/UserLogin.jsp").forward(request, response);
        }
        if (request.getParameter("end") != null) {
            int idEndereco = Integer.parseInt(request.getParameter("end"));

            List<Address> addresses = (List<Address>) sessao.getAttribute("addr");
            Address enderecoEscolhido = null;

            for (Address a : addresses) {
                if (a.getAddress_id() == idEndereco) {
                    enderecoEscolhido = a;
                }
            }
            sessao.setAttribute("deliveryAddress", enderecoEscolhido);
            double valorTotal = (double) sessao.getAttribute("valorTotal");
            sessao.setAttribute("valorFrete", valorTotal * 0.02);
            request.getRequestDispatcher("/WEB-INF/choosePaymentWay.jsp").forward(request, response);

        } else {
            request.getRequestDispatcher("/WEB-INF/choosePaymentWay.jsp").forward(request, response);
        }

    }

}
