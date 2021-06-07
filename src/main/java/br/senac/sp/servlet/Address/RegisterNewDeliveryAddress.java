/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet.Address;

import br.senac.sp.dao.AddressDAO;
import br.senac.sp.model.Address;
import br.senac.sp.model.Customer;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.CustomerDataValidation;

/**
 *
 * @author luans
 */
public class RegisterNewDeliveryAddress extends HttpServlet {
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        HttpSession sessao = request.getSession();
        String complement = " ";
        String title = " ";
        if (request.getParameter("titulo") != null) {
            request.getParameter("titulo");
        }
        String street = request.getParameter("street");
        String cep = request.getParameter("cep");
        String number = request.getParameter("number");
        if (request.getParameter("complement") != null) {
            complement = request.getParameter("complement");
        }

        String neighborhood = request.getParameter("neighborhood");
        String uf = request.getParameter("uf");

        CustomerDataValidation customerData = new CustomerDataValidation();
        List<String> errorList = customerData.validaAtualizaEndereco(street, cep, uf, number, neighborhood, complement, cep, street);
        Customer user = (Customer) sessao.getAttribute("user");

        Address address = new Address(user.getCustomer_id(), title, street, cep, uf, number, neighborhood, complement, "Entrega", "Ativo");
        if (errorList.size() == 0) {
            try {
                int addressId = AddressDAO.addAddressReturnId(address);
                address.setAddress_id(addressId);
                double valorTotal = (double) sessao.getAttribute("valorTotal");
                sessao.setAttribute("valorFrete", valorTotal*0.04);
                sessao.setAttribute("deliveryAddress", address);
                request.setAttribute("deliveryAddr", address);
                if (sessao.getAttribute("pagamento")!=null) {
                request.getRequestDispatcher("/WEB-INF/reviewOrder.jsp").forward(request, response);
                }
                request.getRequestDispatcher("/WEB-INF/choosePaymentWay.jsp").forward(request, response);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            request.setAttribute("hasError","true");
            request.setAttribute("errorList", errorList);
            request.setAttribute("deliveryAddr", address);
            request.getRequestDispatcher("/WEB-INF/chooseDeliveryAddress.jsp").forward(request, response);
        }
        }catch(Exception e){
        request.getRequestDispatcher("/WEB-INF/UserLogin.jsp").forward(request, response);
        }
    }

}
