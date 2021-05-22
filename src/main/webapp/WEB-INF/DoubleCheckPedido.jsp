<%-- 
    Document   : DoubleCheckPedido
    Created on : 09/05/2021, 10:32:43
    Author     : luans
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrinho</title>
        <link href="css/EstiloRevisarPedido.css" type="text/css" rel="stylesheet">

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style_menu.css">
        <link rel="stylesheet" type="text/css"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.2/css/all.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="materialize/css/materialize.min.css" media="screen,projection" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
        <link rel="stylesheet" type="text/css"
              href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
    </head>
    <body>

        <nav>
            <div class="nav-wrapper brown">
                <div class="col s12" id="step">
                    <a href="${pageContext.request.contextPath}/Home_Servlet" class="breadcrumb white-text">Loja</a>
                    <a href="${pageContext.request.contextPath}/ReviewOrder" class="breadcrumb white-text">Meu carrinho</a>
                    <a href="${pageContext.request.contextPath}/choosePayment_Servlet" class="breadcrumb white-text" disabled>Pagamento</a>
                    <a href="${pageContext.request.contextPath}/EscolherEnderecoEntrega" class="breadcrumb white-text">Endereco</a>
                    <a href="${pageContext.request.contextPath}/FinalizarPedido" class="breadcrumb white-text">Revisar</a>
                </div>
            </div>
        </nav>
        <h1 id="titulo">Revisar o meu Pedido</h1>
        <c:choose >
            <c:when test="${sessionScope.listaCarrinho != null && !sessionScope.itensSelecionados.isEmpty()}">

                <section id="listaCarrinho">
                    <table class="highlight" id="product-listing">
                        <thead>
                            <tr>
                                <th class="tituloTabela">Foto</th>
                                <th class="tituloTabela">Produto</th>
                                <th class="tituloTabelaQtd">Quantidade</th>
                                <th class="valores">Valor Unit</th>

                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${listaCarrinho}" var="p">
                                <tr>
                                    <td><img class = "prod-img"src="${p.path_MainImg}"></td>
                                    <td>${p.productName}</td>
                                    <td>
                                        ${p.quantity}
                                    </td>
                                    <td>R$ ${p.price}</td>


                                    </a>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td></td>
                                <td></td>
                                <td>Frete:</td>
                                <td>R$ 14.0</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td>Total:</td>
                                <td>R$ ${valorTotal+14}</td>
                                <td></td>
                            </tr>


                        </tbody>

                    </table>  
                </section>

            </c:when>
            <c:otherwise>
                <p>Carrinho vazio</p>
            </c:otherwise>
        </c:choose>  
        <div id="endCadastrado">

            <legend class="table-title">Endereco de entrega</legend>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">

                        </th>

                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${deliveryAddress.address_street},${deliveryAddress.address_number} - ${deliveryAddress.address_neighborhood},${deliveryAddress.address_state_abbreviation} - ${addrFat.address_code}</td>
                    </tr>

                </tbody>
            </table>
        </div>
        <c:choose >
            <c:when test="${sessionScope.pagamento.payment_way eq 'Boleto'}">
                <div id="endCadastrado">

                    <legend class="table-title"Pagamento</legend>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Forma de pagamento</th>

                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Valor à vista de ${Math.ceil(valorTotal+14)} no ${sessionScope.pagamento.payment_way}</td>
                            </tr>

                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <div id="endCadastrado">

                    <legend class="table-title"Pagamento</legend>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Forma de pagamento</th>
                                <th scope="col">Parcelas</th>
                                <th scope="col">Valor da parcela</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>${sessionScope.pagamento.payment_way}</td>
                                <td>${sessionScope.pagamento.payment_instalments}</td>
                                <td>${Math.ceil(valorTotal/sessionScope.pagamento.payment_instalments)}</td>
                            </tr>

                        </tbody>
                    </table>
                </div>
                
            </c:otherwise>
        </c:choose>  
                    
                    <a   href="${pageContext.request.contextPath}/GerarComprovante" class="waves-effect waves-light btn-large">Finalizar</a>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script>

    </body>
</html>
