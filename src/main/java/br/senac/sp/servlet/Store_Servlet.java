/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet;

import br.senac.sp.dao.ProductDAO;
import br.senac.sp.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luans
 */
public class Store_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try{
        String categoria = request.getParameter("categoria");

        if (categoria.equals("all")) {
            ProductDAO dao = new ProductDAO();
            List<Product> bonecos = dao.findProductByCategory("Boneco");
            List<Product> camisetasMasc = dao.findProductByCategory("Camiseta masculina"); System.out.println(camisetasMasc.get(0).getProductId());
            List<Product> camisetasFem = dao.findProductByCategory("Camiseta Feminina");
            List<Product> canecas = dao.findProductByCategory("Caneca");
            List<Product> acessorios = dao.findProductByCategory("Acessorio");
            List<Product> variedades = dao.findProductByCategory("Variedade");
            List<Product> resultado = new ArrayList();
            String hasBonecos = "", hasCM = "", hasCF = "", hasCaneca = "", hasAcessorio = "", hasVariedades = "", hasFiltro = "",filtro="";
            if (bonecos.size() > 0) {
                hasBonecos = "true";
            }
            if (camisetasMasc.size() > 0) {
                hasCM = "true";

            }
            if (camisetasFem.size() > 0) {
                hasCF = "true";
            }
            if (canecas.size() > 0) {
                hasCaneca = "true";
            }
            if (acessorios.size() > 0) {
                hasAcessorio = "true";
            }
            if (variedades.size() > 0) {
                hasVariedades = "true";
            }
            request.setAttribute("hasFiltro", hasFiltro);
            request.setAttribute("filtro", filtro);
            request.setAttribute("resultado", resultado);
            request.setAttribute("hasBonecos", hasBonecos);
            request.setAttribute("hasCM", hasCM);
            request.setAttribute("hasCF", hasCF);
            request.setAttribute("hasCanecas", hasCaneca);
            request.setAttribute("hasAcessorios", hasAcessorio);
            request.setAttribute("hasVariedades", hasVariedades);
            request.setAttribute("bonecos", bonecos);
            request.setAttribute("camisetasMasc", camisetasMasc);
            request.setAttribute("camisetasFem", camisetasFem);
            request.setAttribute("canecas", canecas);
            request.setAttribute("acessorios", acessorios);
            request.setAttribute("variedades", variedades);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/homepage.jsp");
            dispatcher.forward(request, response);
        } else {
            ProductDAO dao = new ProductDAO();
            System.out.println(">>>>>>>"+categoria);
            List<Product> bonecos = new ArrayList();
            List<Product> camisetasMasc = new ArrayList();
            List<Product> camisetasFem = new ArrayList();
            List<Product> canecas = new ArrayList();
            List<Product> acessorios = new ArrayList();
            List<Product> variedades = new ArrayList();
            String hasBonecos = "", hasCM = "", hasCF = "", hasCaneca = "", hasAcessorio = "", hasVariedades = "", filtro = "", hasFiltro = "";
            List<Product> resultado = dao.findProductByCategory(categoria);
            if (resultado.size() > 0) {
                hasFiltro = "true";
                filtro = categoria;
            }
            request.setAttribute("filtro", filtro);
            request.setAttribute("hasFiltro", hasFiltro);
            request.setAttribute("resultado", resultado);
            request.setAttribute("hasBonecos", hasBonecos);
            request.setAttribute("hasCM", hasCM);
            request.setAttribute("hasCF", hasCF);
            request.setAttribute("hasCanecas", hasCaneca);
            request.setAttribute("hasAcessorios", hasAcessorio);
            request.setAttribute("hasVariedades", hasVariedades);
            request.setAttribute("bonecos", bonecos);
            request.setAttribute("camisetasMasc", camisetasMasc);
            request.setAttribute("camisetasFem", camisetasFem);
            request.setAttribute("canecas", canecas);
            request.setAttribute("acessorios", acessorios);
            request.setAttribute("variedades", variedades);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/homepage.jsp");
            dispatcher.forward(request, response);

        }
        }catch(Exception e){
        ProductDAO dao = new ProductDAO();

            List<Product> bonecos = dao.findProductByCategory("Boneco");
            List<Product> camisetasMasc = dao.findProductByCategory("Camiseta masculina");
            List<Product> camisetasFem = dao.findProductByCategory("Camiseta Feminina");
            List<Product> canecas = dao.findProductByCategory("Caneca");
            List<Product> acessorios = dao.findProductByCategory("Acessorio");
            List<Product> variedades = dao.findProductByCategory("Variedade");
            List<Product> resultado = new ArrayList();
            String hasBonecos = "", hasCM = "", hasCF = "", hasCaneca = "", hasAcessorio = "", hasVariedades = "", hasFiltro = "",filtro="";
            if (bonecos.size() > 0) {
                hasBonecos = "true";
            }
            if (camisetasMasc.size() > 0) {
                hasCM = "true";

            }
            if (camisetasFem.size() > 0) {
                hasCF = "true";
            }
            if (canecas.size() > 0) {
                hasCaneca = "true";
            }
            if (acessorios.size() > 0) {
                hasAcessorio = "true";
            }
            if (variedades.size() > 0) {
                hasVariedades = "true";
            }

            request.setAttribute("hasFiltro", hasFiltro);
            request.setAttribute("filtro", filtro);
            request.setAttribute("resultado", resultado);
            request.setAttribute("hasBonecos", hasBonecos);
            request.setAttribute("hasCM", hasCM);
            request.setAttribute("hasCF", hasCF);
            request.setAttribute("hasCanecas", hasCaneca);
            request.setAttribute("hasAcessorios", hasAcessorio);
            request.setAttribute("hasVariedades", hasVariedades);
            request.setAttribute("bonecos", bonecos);
            request.setAttribute("camisetasMasc", camisetasMasc);
            request.setAttribute("camisetasFem", camisetasFem);
            request.setAttribute("canecas", canecas);
            request.setAttribute("acessorios", acessorios);
            request.setAttribute("variedades", variedades);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/homepage.jsp");
            dispatcher.forward(request, response);
            
            
            
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String categoria = request.getParameter("categoria");
        ProductDAO dao = new ProductDAO();
            System.out.println(">>>>>>>"+categoria);
            List<Product> bonecos = new ArrayList();
            List<Product> camisetasMasc = new ArrayList();
            List<Product> camisetasFem = new ArrayList();
            List<Product> canecas = new ArrayList();
            List<Product> acessorios = new ArrayList();
            List<Product> variedades = new ArrayList();
            String hasBonecos = "", hasCM = "", hasCF = "", hasCaneca = "", hasAcessorio = "", hasVariedades = "", filtro = "", hasFiltro = "";
            List<Product> resultado = dao.findProductBySearch(categoria);
            if (resultado.size() > 0) {
                hasFiltro = "pesquisa";
                filtro = categoria;
            }else{
            hasFiltro = "naoachouresultado";
            filtro = categoria;
            }
            
            request.setAttribute("filtro", filtro);
            request.setAttribute("hasFiltro", hasFiltro);
            request.setAttribute("resultado", resultado);
            request.setAttribute("hasBonecos", hasBonecos);
            request.setAttribute("hasCM", hasCM);
            request.setAttribute("hasCF", hasCF);
            request.setAttribute("hasCanecas", hasCaneca);
            request.setAttribute("hasAcessorios", hasAcessorio);
            request.setAttribute("hasVariedades", hasVariedades);
            request.setAttribute("bonecos", bonecos);
            request.setAttribute("camisetasMasc", camisetasMasc);
            request.setAttribute("camisetasFem", camisetasFem);
            request.setAttribute("canecas", canecas);
            request.setAttribute("acessorios", acessorios);
            request.setAttribute("variedades", variedades);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/homepage.jsp");
            dispatcher.forward(request, response);

    }

}
