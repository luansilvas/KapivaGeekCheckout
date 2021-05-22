/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet.user;

import br.senac.sp.dao.AddressDAO;
import br.senac.sp.dao.CustomerDAO;
import br.senac.sp.model.Address;
import br.senac.sp.model.Customer;
import java.io.IOException;
import java.sql.SQLException;
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
public class UserLogin_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/UserLogin.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String senha = request.getParameter("password");

        try {

            Customer customer = CustomerDAO.validaLogin(username);
            if (customer != null && customer.validarSenha(senha)) {
                List<Address> addr = AddressDAO.getCustomerAddresses(customer.getCustomer_id());
                Address addrFat = AddressDAO.getCustomerIncomeAddresses(customer.getCustomer_id());
                System.out.println(addr.toString());
                HttpSession sessao = request.getSession();
                Address mainAddress = addr.get(0);
                sessao.setAttribute("mainAddress", mainAddress);
                sessao.setAttribute("user", customer);
                sessao.setAttribute("addr", addr);
                sessao.setAttribute("addrFat", addrFat);
                if (sessao.getAttribute("listaCarrinho") == null) {
                    response.sendRedirect(request.getContextPath() + "/Store_Servlet");
                } else {
                    request.setAttribute("user", customer);
                    response.sendRedirect(request.getContextPath() + "/ChooseDeliveryAddress_Servlet");
                }

                System.out.println("validado...");
            } else {
                request.setAttribute("msgErro", "Usuário ou senha Inválidos");
                request.getRequestDispatcher("/UserLogin.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

}
