/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet.Cart;

import br.senac.sp.model.Product;
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
public class changeItemSize_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        String tamanho = request.getParameter("tamanho");
        int productId=Integer.parseInt(request.getParameter("productId"));
        String context=request.getParameter("context");
        
        HttpSession sessao = request.getSession();
        List<Product> listaCarrinho = (List<Product>) sessao.getAttribute("listaCarrinho");
        
            for (Product p : listaCarrinho) {
                if (p.getProductId()==productId) {
                    p.setSize(tamanho);
                    System.out.println("NOVO TAMANHO: "+tamanho);
                }
            }
        sessao.removeAttribute("listaCarrinho");
        sessao.setAttribute("listaCarrinho",listaCarrinho);
            
            
                    request.getRequestDispatcher(context+".jsp").forward(request, response);
        }catch(Exception e){
            System.out.println("Exceção: "+e);
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
