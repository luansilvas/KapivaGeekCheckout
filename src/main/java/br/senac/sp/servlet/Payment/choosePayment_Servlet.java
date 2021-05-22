/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet.Payment;

import br.senac.sp.model.Card;
import br.senac.sp.model.Payment;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.CreditCardValidation;

/**
 *
 * @author luans
 */
public class choosePayment_Servlet extends HttpServlet {
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/choosePaymentWay.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        HttpSession sessao = request.getSession();
        Payment p = null;
        Card c = null;
        if (request.getParameter("boleto") != null) {
            p = new Payment("Boleto", 1, "pending");
            sessao.setAttribute("pagamento", p);
            request.getRequestDispatcher("/WEB-INF/reviewOrder.jsp").forward(request, response);

        } else {
            String card_number = request.getParameter("numCartao");
            String cvv = request.getParameter("cvv");
            String exp = request.getParameter("exp");
            String printedName = request.getParameter("printedName");
            int payment_instalments = Integer.parseInt(request.getParameter("parcelas"));

            c = new Card(card_number, cvv, exp, printedName, "CCredito", payment_instalments, "pending");
            CreditCardValidation cv = new CreditCardValidation();

            
            try {
                List<String> errorList = cv.CheckCCData(c);
                if (errorList.size() == 0) {

                    p = new Payment("Cartão", payment_instalments, "pending");
                    System.out.println("");

                    sessao.setAttribute("pagamento", p);
                    sessao.setAttribute("cartaoPag", c);
                    System.out.println((Payment) sessao.getAttribute("payment"));

                    request.getRequestDispatcher("/WEB-INF/reviewOrder.jsp").forward(request, response);

                } else {
                    request.setAttribute("hasError", "true");
                    request.setAttribute("errorList", errorList);
                    request.getRequestDispatcher("/WEB-INF/choosePaymentWay.jsp").forward(request, response);

                }
            } catch (Exception e) {
                request.setAttribute("hasError", "true");
                ArrayList<String> errorList = new ArrayList();
                errorList.add("Verifique os valores digitados");
                
                request.setAttribute("errorList", errorList);

                System.out.println(e.getMessage());
                request.getRequestDispatcher("/WEB-INF/choosePaymentWay.jsp").forward(request, response);
            }
        }
        }catch(Exception e){
        request.setAttribute("hasError", "true");
                ArrayList<String> errorList = new ArrayList();
                errorList.add("Verifique os valores digitados");
                
                request.setAttribute("errorList", errorList);

                System.out.println(e.getMessage());
                request.getRequestDispatcher("/WEB-INF/choosePaymentWay.jsp").forward(request, response);
        }
    }

}
