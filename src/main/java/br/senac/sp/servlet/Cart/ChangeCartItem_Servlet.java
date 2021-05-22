/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet.Cart;

import br.senac.sp.model.Product;
import java.io.IOException;
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
public class ChangeCartItem_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession sessao = request.getSession();
            List<Product> carrinho = (List<Product>) sessao.getAttribute("listaCarrinho");
            int qtdeCarrinho = (int) sessao.getAttribute("qtdeItensCarrinho");
            double valorTotal = (Double) sessao.getAttribute("valorTotal");
            int prodId = Integer.parseInt(request.getParameter("prodId"));
            Product produtoSelecionado = new Product();
            if (request.getParameter("acao").equals("adicionar")) {
                for (Product p : carrinho) {
                    if (p.getProductId() == prodId) {
                        p.setQuantity(p.getQuantity() + 1);
                        p.setTotalPrice(p.getTotalPrice()+p.getPrice());
                        valorTotal = valorTotal + p.getPrice();
                    }
                }
                qtdeCarrinho = +1;
                sessao.removeAttribute("qtdeItensCarrinho");
                sessao.setAttribute("qtdeItensCarrinho", qtdeCarrinho);

            } else if (request.getParameter("acao").equals("diminuir")) {
                for (Product p : carrinho) {
                    if (p.getProductId() == prodId) {

                        p.setQuantity(p.getQuantity() - 1);
                        valorTotal = valorTotal - p.getPrice();
                        p.setTotalPrice(p.getTotalPrice()-p.getPrice());
                        produtoSelecionado = p;
                        qtdeCarrinho -= 1;
                    }
                }
                if (produtoSelecionado.getQuantity() <= 0) {
                    
                    qtdeCarrinho-=howManyProducts(carrinho,prodId);
                    carrinho.remove(produtoSelecionado);
                }
                sessao.removeAttribute("qtdeItensCarrinho");
                sessao.setAttribute("qtdeItensCarrinho", qtdeCarrinho);

            } else if (request.getParameter("acao").equals("deletar")) {
                for (Product p : carrinho) {
                    if (p.getProductId() == prodId) {
                        for (int i = 0; i < p.getQuantity(); i++) {
                            valorTotal = valorTotal - p.getPrice();
                        }
                        produtoSelecionado = p;
                    }
                }
                qtdeCarrinho-=howManyProducts(carrinho,prodId);
                sessao.removeAttribute("qtdeItensCarrinho");
                sessao.setAttribute("qtdeItensCarrinho", qtdeCarrinho);
                carrinho.remove(produtoSelecionado);
                
                if (carrinho.size()==0) {
                    response.sendRedirect(request.getContextPath() + "/ChooseDeliveryAddress_Servlet");
                }
            }
            if (carrinho.size() == 0) {
                sessao.setAttribute("listaCarrinho", null); 
                request.setAttribute("valorCompraFeita", 0.0);
                sessao.removeAttribute("listaCarrinho");
                sessao.setAttribute("qtdeItensCarrinho",0);
                sessao.setAttribute("valorTotal",0.0);
                request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
            } else {
                sessao.removeAttribute("listaCarrinho");
                sessao.setAttribute("listaCarrinho", carrinho);
            }

            valorTotal = Math.round(valorTotal);

            sessao.removeAttribute("valorTotal");
            sessao.setAttribute("valorTotal", valorTotal);
            request.getRequestDispatcher("/WEB-INF/reviewOrder.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
            request.getRequestDispatcher("/UserLogin.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    public static int howManyProducts(List<Product> li, int id) {
        int qtde = 0;
        for (Product p : li) {
            if (p.getProductId() == id) {
                qtde = p.getQuantity();
            }
        }
        return qtde;
    }
}
