<%-- 
    Document   : purchaseHistory
    Created on : 06/06/2021, 23:43:18
    Author     : luans
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/EstiloRevisarPedido.css" type="text/css" rel="stylesheet">
        
         <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="materialize/css/materialize.min.css" media="screen,projection" />
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        
        <title>Histórico Pedido</title>
    </head>
    <body> 
 <%@include file="../menu.jsp" %>
        <h1 style="margin-top:80px;margin-left: 20%;">Histórico de Pedidos</h1>

        <table class="highlight" id="product-listing">
                        <thead>
                            <tr>
                                <th class="tituloTabela">
                                    <p id="prod" class="titulos">Id do Pedido</p>
                                </th>
                                <th class="tituloTabelaQtd">
                                    <p class="titulos">Data</p>
                                </th>
                                <th class="valores">
                                    <p class="titulos">Valor Total</p>
                                </th>
                                <th class="valores">
                                    <p class="titulos">Status</p>
                                </th>
                                <th>
                                    <p>Ver detalhes</p>
                                </th> 
                                

                            </tr>
                        </thead>
                        <c:forEach items="${OrderList}" var="p">
                            <tbody>
                                <tr>
                                    <td id="produto">
                                        <p>${p.purchaseorder_id.substring(0,8)}</p>
                                    </td>
                                    <td id="qtd" class="borda">
                                        <p>${p.diaPedido}</p>
                                    </td>
                                    <td class="preco">
                                        <p>R$ ${Math.round(p.purchaseorder_amount)}</p>
                                    </td>
                                     <td>
                                        <p>${p.purchaseorder_status}</p>
                                    </td>
                                   
                                    <td>
                                        <a href="${pageContext.request.contextPath}/VerDetalhes_Servlet?orderId=${p.purchaseorder_id}"><img id="delete" src="icons/loupe.png"></a>
                                    </td>

                                </tr>

                            </tbody>
                        </c:forEach>
                    </table>
    </body>
</html>

