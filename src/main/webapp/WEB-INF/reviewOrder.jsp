<%-- 
    Document   : revisarPedido
    Created on : 05/05/2021, 23:37:23
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
                    <a href="${pageContext.request.contextPath}/Store_Servlet" class="breadcrumb white-text">Loja</a>                
                    <a href="${pageContext.request.contextPath}/Cart_Servlet?acao=abrirCarrinho" class="breadcrumb white-text">Meu carrinho</a>                  
                    <a href="${pageContext.request.contextPath}/ChooseDeliveryAddress_Servlet" class="breadcrumb white-text">Endereco</a>
                    <a href="${pageContext.request.contextPath}/choosePayment_Servlet" class="breadcrumb white-text">Pagamento</a>                                   
                    <a href="${pageContext.request.contextPath}/ReviewOrder_Servlet" class="breadcrumb white-text">Confirma</a>                 
                </div>
            </div>
        </nav>
        <h1 id="titulo">Revisar o meu Pedido</h1>
        <c:choose >
            <c:when test="${sessionScope.listaCarrinho != null && !sessionScope.itensSelecionados.isEmpty()}">

                <section id="listaCarrinho">
                    <a href="${pageContext.request.contextPath}/choosePayment_Servlet" id="go-back" style="margin-top: 10%;margin-left: 20%;">
                        <img src="icons/left-arrow.png">
                    </a>
                    <table class="highlight" id="product-listing">
                        <thead>
                            <tr>
                                <th class="tituloTabela">Foto</th>
                                <th class="tituloTabela">Produto</th>
                                <th class="tituloTabela">Tamanho</th>
                                <th class="tituloTabelaQtd">Quantidade</th>
                                <th class="valores">Valor Unit</th>
                                <th class="valores">Valor total item</th>
                                <th>Acões</th>

                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${listaCarrinho}" var="p">
                                <tr>
                                    <td><img class = "prod-img"src="${p.path_MainImg}"></td>
                                    <td>${p.productName}</td>


                                    <c:choose>
                                        <c:when test="${p.category eq 'Camiseta masculina' or p.category eq  'Camiseta feminina'}">
                                            <td>    
                                                <select id="uf" name="uf" class="browser-default" class="validate" style="width: 60%;margin-left:30%;position: relative">
                                                    <option value="${pageContext.request.contextPath}/changeItemSize_Servlet?productId=${p.productId}&tamanho=${p.size}&context=WEB-INF/reviewOrder" selected> ${p.size}</option>
                                                    <option value="${pageContext.request.contextPath}/changeItemSize_Servlet?productId=${p.productId}&tamanho=P&context=WEB-INF/reviewOrder">P</option>
                                                    <option value="${pageContext.request.contextPath}/changeItemSize_Servlet?productId=${p.productId}&tamanho=M&context=WEB-INF/reviewOrder">M</option>
                                                    <option value="${pageContext.request.contextPath}/changeItemSize_Servlet?productId=${p.productId}&tamanho=G&context=WEB-INF/reviewOrder">G</option>
                                                </select>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>Tam. Único</td>
                                        </c:otherwise>

                                    </c:choose>

                                    <td>
                                        <a href="<c:url value="/ChangeCartItem_Servlet?prodId=${p.productId}&acao=diminuir"/>"><i><img class="change-quantity" src="icons/minus24px.png"></i></a>
                                                ${p.quantity}
                                        <a  href="<c:url value="/ChangeCartItem_Servlet?prodId=${p.productId}&acao=adicionar"/>"><i><img class="change-quantity" src="icons/add24px.png"></i></a>
                                    </td>
                                    <td>R$ ${Math.ceil(p.price)}</td>

                                    </a>
                                    <td>R$ ${Math.ceil(p.totalPrice)}</td>       
                                    <td>
                                        <a href="<c:url value="/ChangeCartItem_Servlet?prodId=${p.productId}&acao=deletar"/>">
                                            <i><img src="icons/delete.png"></i>
                                    </td>

                                </tr>
                            </c:forEach>

                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td>Frete:</td>
                                <td>R$ ${Math.ceil(valorFrete)}</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <c:choose>
                                    <c:when test="${hasDiscount eq 'true'}">
                                        <td>Total:</td>
                                        <td>de <s>R$ ${Math.ceil(valorTotal+valorFrete)}</s> por R$ ${Math.ceil((valorTotal+valorFrete)-(valorTotal*(discountPercentage/100)))}</td>
                                        <td></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Total:</td>
                                        <td>R$ ${Math.ceil(valorTotal+valorFrete)}</td>
                                        <td></td>
                                    </c:otherwise>

                                </c:choose>

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

            <div class="card"  style="width:60%;margin-left: 20%;">
                <h3>
                    Endereço de entrega
                </h3>
                <h5>
                    ${deliveryAddress.address_street}

                </h5>
                <p>${deliveryAddress.address_street},${deliveryAddress.address_number} - ${deliveryAddress.address_neighborhood},${deliveryAddress.address_state_abbreviation} - ${addrFat.address_code}</p>
            </div> 



        </div>
        <c:choose>
            <c:when test="${sessionScope.pagamento.payment_way eq 'Boleto'}">
                <div id="endCadastrado">


                    <div class="card"  style="width:60%;margin-left: 20%;">
                        <h3>
                            Forma de pagamento
                        </h3>
                        <h5>


                            <c:choose>
                                <c:when test="${hasDiscount eq 'true'}">

                                    Valor à vista de ${Math.ceil((valorTotal+valorFrete)-(valorTotal*(discountPercentage/100)))} no ${sessionScope.pagamento.payment_way}
                                </c:when>
                                <c:otherwise>
                                    Valor à vista de ${Math.ceil(valorTotal+valorFrete)} no ${sessionScope.pagamento.payment_way}
                                </c:otherwise>

                            </c:choose>
                        </h5>            
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

                                    <c:choose>
                                        <c:when test="${hasDiscount eq 'true'}">
                                            <td>${(Math.ceil((valorTotal+valorFrete)-(valorTotal*(discountPercentage/100))/sessionScope.pagamento.payment_instalments))}</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>${Math.ceil(valorTotal+valorFrete)}</td>
                                        </c:otherwise>

                                    </c:choose>
                                </tr>

                            </tbody>
                        </table>
                    </div>

                   
                                        <div class="card"  style="width:60%;margin-left: 20%;">
                        <h3>
                            Forma de pagamento
                        </h3>
                        <h5>


                            <c:choose>
                                <c:when test="${hasDiscount eq 'true'}">

                                    Valor à vista de ${Math.ceil((valorTotal+valorFrete)-(valorTotal*(discountPercentage/100)))} no ${sessionScope.pagamento.payment_way}
                                </c:when>
                                <c:otherwise>
                                    Valor à vista de ${Math.ceil(valorTotal+valorFrete)} no ${sessionScope.pagamento.payment_way}
                                </c:otherwise>

                            </c:choose>
                        </h5>            
                    </div>
                </c:otherwise>
            </c:choose>  
            <a class="btn-large" id="go-address" href="${pageContext.request.contextPath}/FinalizeOrder_Servlet">Finalizar e enviar pedido</a> 


            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script>
            <script>


                $("select").click(function () {
                    var open = $(this).data("isopen");
                    if (open) {
                        window.location.href = $(this).val()
                    }

                    $(this).data("isopen", !open);
                });
            </script>
    </body>
</html>

