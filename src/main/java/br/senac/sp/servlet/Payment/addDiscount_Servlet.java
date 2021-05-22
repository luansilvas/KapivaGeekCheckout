/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet.Payment;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luans
 */
public class addDiscount_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String discount = request.getParameter("cupom");
        System.out.println("ESSE É O KUPOM EM "+discount);
        if (!discount.equals("KAPIVA40")) {
            request.setAttribute("hasDiscountError","true");
            request.setAttribute("ErrorMessage","O cupom não existe!");
            request.getRequestDispatcher("/WEB-INF/choosePaymentWay.jsp").forward(request, response);
        }else{
            HttpSession sessao = request.getSession();
            if (sessao.getAttribute("hasDiscountError")!=null) {
            request.setAttribute("hasError","true");
            request.setAttribute("ErrorMessage","Cupom já aplicado!");
            request.getRequestDispatcher("/WEB-INF/choosePaymentWay.jsp").forward(request, response);
            }else{
            sessao.setAttribute("hasDiscount","true");
            sessao.setAttribute("discountPercentage",40);     
            request.setAttribute("hasDiscountError","false");
            request.setAttribute("ErrorMessage","Cupom aplicado com sucesso! Selecione a forma de pagamento e confira resumo do pedido!");
            request.getRequestDispatcher("/WEB-INF/choosePaymentWay.jsp").forward(request, response);
            }
            
            
        
        }
        
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
