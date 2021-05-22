/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet.Cart;

import br.senac.sp.model.Address;
import br.senac.sp.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Normalizer;
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
public class Cart_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        List<Product> listaCarrinho = (List<Product>) sessao.getAttribute("listaCarrinho");
        int qtdeCarrinho = (int)sessao.getAttribute("qtdeItensCarrinho");
        
        double valorTotal = 0;
        String acao = request.getParameter("acao");

        if (acao.equals("abrirCarrinho")) {
            if (sessao.getAttribute("listaCarrinho") != null) {

                valorTotal = valorTotal(listaCarrinho);
                sessao.setAttribute("valorTotal", valorTotal);

            }

        } 
        else if (acao.equals("adicionar")) {

            if (sessao.getAttribute("listaCarrinho") == null) {
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
            }

            int id = Integer.parseInt(request.getParameter("productId"));
            Product p = findProduct(id, listaCarrinho);
            int qtd = p.getQuantity();
            
            qtdeCarrinho+=1;

            listaCarrinho = addQuantidade(listaCarrinho, id, qtd);
            valorTotal = valorTotal(listaCarrinho);
            sessao.removeAttribute("qtdeItensCarrinho");
            sessao.setAttribute("qtdeItensCarrinho",qtdeCarrinho);
          
            sessao.setAttribute("valorTotal", valorTotal);

        } else if (acao.equals("excluir")) {
            int prodId = Integer.parseInt(request.getParameter("productId"));
               
            qtdeCarrinho-=contarQtdeProduto(listaCarrinho,prodId);
            if (qtdeCarrinho<0) qtdeCarrinho=0;  
            listaCarrinho.remove(findProduct(prodId, listaCarrinho));
            
            sessao.removeAttribute("qtdeItensCarrinho");
            sessao.setAttribute("qtdeItensCarrinho",qtdeCarrinho);
            valorTotal = valorTotal(listaCarrinho);
            sessao.setAttribute("valorTotal", valorTotal);

        } else if (acao.equals("subtrair")) {

            if (sessao.getAttribute("listaCarrinho") == null) {
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
            }

            int id = Integer.parseInt(request.getParameter("productId"));
            Product p = findProduct(id, listaCarrinho);
            int qtd = p.getQuantity();
            if (qtdeCarrinho<0) qtdeCarrinho=0;  
            if (qtd > 1) {
                qtdeCarrinho-=1;
                listaCarrinho = subQuantidade(listaCarrinho, id, qtd);
            }

            sessao.removeAttribute("qtdeItensCarrinho");
            sessao.setAttribute("qtdeItensCarrinho",qtdeCarrinho);
            valorTotal = valorTotal(listaCarrinho);
            sessao.setAttribute("valorTotal", valorTotal);
        }

        request.getRequestDispatcher("/cart.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String cep = request.getParameter("cep");
        String ruaStr = request.getParameter("rua");
        String bairooStr = request.getParameter("bairro");
        String cidadeStr = request.getParameter("cidade");
        String ufStr = request.getParameter("uf");
        
        Address addr = new Address();
        addr.setAddress_code(Normalizer.normalize(cep, Normalizer.Form.NFD));
        addr.setAddress_street(Normalizer.normalize(ruaStr, Normalizer.Form.NFD));
        addr.setAddress_state_abbreviation(Normalizer.normalize(ufStr, Normalizer.Form.NFD));
        addr.setAddress_neighborhood(Normalizer.normalize(bairooStr, Normalizer.Form.NFD));
        addr.setAddress_complement(Normalizer.normalize(cidadeStr, Normalizer.Form.NFD));
        
        
        HttpSession sessao = request.getSession();
        sessao.setAttribute("estimateDays", getRandomNumber());
        sessao.setAttribute("deliveryAddr", addr);
        double valorTotal = (double) sessao.getAttribute("valorTotal");
        sessao.setAttribute("shippingPrice", valorTotal * 0.02);
        
        request.setAttribute("addre", addr);

               
        request.setAttribute("valorCarrinho", valorTotal * 0.02);
         request.getRequestDispatcher("/cart.jsp").forward(request, response);
        
    }
    

    public double valorTotal(List<Product> li) {
        double valorTotal = 0;
        for (Product p : li) {
            valorTotal += (p.getPrice() * p.getQuantity());
        }

        return valorTotal;
    }
    
    public Product findProduct(int id, List<Product> li) {

        for (Product p : li) {
            if (p.getProductId() == id) {
                return p;
            }
        }
        return null;
    }

    public static List<Product> addQuantidade(List<Product> li, int id, int quantidade) {

        for (Product p : li) {
            if (p.getProductId() == id) {
                System.out.println("ACHEI");
                p.setQuantity(quantidade + 1);
                System.out.println("QTDE ATUAL"+p.getQuantity());
                p.setTotalPrice(p.getPrice()*(quantidade+1));
            }
        }
        return li;

    }

    public static List<Product> subQuantidade(List<Product> li, int id, int quantidade) {
        for (Product p : li) {
            if (p.getProductId() == id) {
                p.setQuantity(quantidade - 1);
                p.setTotalPrice(p.getTotalPrice() - p.getPrice());
            }
        }
        return li;
    }
    
        public static int contarQtdeProduto(List<Product> li, int id) {
        int qtde =0;
        for (Product p : li) {
            if (p.getProductId() == id) {
                qtde = p.getQuantity();
            }
        }
        return qtde;
    }
    
    public int getRandomNumber() {
    return (int) ((Math.random() * (10-1)) + 1);
}
    
    
    public static int geraValorCEP(){
      return  (int) (1 + Math.random() * 20);
    }
    
}
