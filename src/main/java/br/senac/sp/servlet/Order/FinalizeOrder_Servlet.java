/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet.Order;

import br.senac.sp.dao.CartDAO;
import br.senac.sp.dao.OrderDAO;
import br.senac.sp.dao.PaymentDAO;
import br.senac.sp.model.Address;
import br.senac.sp.model.Customer;
import br.senac.sp.model.Order;
import br.senac.sp.model.Payment;
import br.senac.sp.model.Product;
import br.senac.sp.model.ProductOrder;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luans
 */
public class FinalizeOrder_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession sessao = request.getSession();
            UUID codPedido = UUID.randomUUID();
            sessao.setAttribute("codPedido", codPedido);

            Payment formaPagamento = (Payment) sessao.getAttribute("pagamento");
            int codPagamento = PaymentDAO.addPayment(formaPagamento);
            Customer usuario = (Customer) sessao.getAttribute("user");
            Address addr = (Address) sessao.getAttribute("deliveryAddress");
            List<Product> listaProdutos = (List<Product>) sessao.getAttribute("listaCarrinho");
            double vlrTotal = (Double) sessao.getAttribute("valorTotal");
            vlrTotal += (Double) sessao.getAttribute("valorFrete");
            if (sessao.getAttribute("hasDiscount") != null&&sessao.getAttribute("discountPercentage")!=null) {
                System.out.println("Has discount");
                vlrTotal = Math.ceil(vlrTotal - (vlrTotal * 0.4));
            }

            Order order = new Order(codPedido.toString(), (Double) sessao.getAttribute("valorTotal"), usuario.getCustomer_id(), codPagamento, "Aguardando pagamento", addr.getAddress_id());
            if (OrderDAO.addOrder(order)) {
                for (Product p : listaProdutos) {
                    ProductOrder po = new ProductOrder(codPedido.toString(), p.getProductId(), p.getQuantity(), p.getPrice());
                    CartDAO.addProductOrder(po);
                }

            }

            request.setAttribute("valorCompraFeita", vlrTotal);
            sessao.removeAttribute("hasDiscount");
            sessao.removeAttribute("discountPercentage");
            sessao.removeAttribute("listaCarrinho");
            sessao.setAttribute("qtdeItensCarrinho", 0);
            sessao.setAttribute("valorTotal", 0.0);
            request.getRequestDispatcher("/WEB-INF/SellFinalized.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("PEGUEI UMA EXCECAO" + e);
            request.getRequestDispatcher("/WEB-INF/SellWithError.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/SellFinalized.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
