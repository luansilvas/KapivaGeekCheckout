/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet.Order;

import br.senac.sp.dao.OrderDAO;
import br.senac.sp.model.Customer;
import br.senac.sp.model.Order;
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
public class OrderHistory_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        Customer user = (Customer) sessao.getAttribute("user");
        
        List<Order> orders = OrderDAO.getOrders(user.getCustomer_id());
        request.setAttribute("OrderList", orders);
        
        request.getRequestDispatcher("/WEB-INF/purchaseHistory.jsp").forward(request, response);
        
    }    


}
