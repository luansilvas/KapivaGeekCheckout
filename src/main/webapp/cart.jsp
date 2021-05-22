<%-- 
    Document   : cart
    Created on : 18/05/2021, 19:38:08
    Author     : luans
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Meu carrinho</title>

        <link href="css/EstiloRevisarPedido.css" type="text/css" rel="stylesheet">

        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="materialize/css/materialize.min.css" media="screen,projection" />
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/icon?family=Material+Icons">


        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    </head>

    <body>

        <nav>
            <div class="nav-wrapper brown" id="nav-stepper">
                <div class="col s12" id="step">
                    <a href="${pageContext.request.contextPath}/Store_Servlet" class="breadcrumb white-text">Loja</a>                
                    <a href="${pageContext.request.contextPath}/Cart_Servlet?acao=abrirCarrinho" class="breadcrumb white-text">Meu carrinho</a>                  
                    <a class="breadcrumb grey-text">Endereco</a>
                    <a class="breadcrumb grey-text">Pagamento</a>
                    <a class="breadcrumb grey-text">Confirma</a>
                </div>
            </div>
        </nav>
        <c:choose >
            <c:when test="${sessionScope.listaCarrinho != null && !sessionScope.listaCarrinho.isEmpty()}">

                <section id="listaCarrinho">

                    <table border="none"  id="product-listing" class="centered">
                        <thead>
                            <tr style="background-color: white;border: white;text-align:center">
                                <th class="tituloTabela">
                                    <p id="prod" class="titulos">Produto</p>
                                </th>
                                <th class="tituloTabelaQtd">
                                    <p class="titulos">Tamanho</p>
                                </th>
                                <th class="tituloTabelaQtd">
                                    <p class="titulos">Quantidade</p>
                                </th>
                                <th class="valores">
                                    <p class="titulos">Valor Unit.</p>
                                </th>
                                <th>
                                    <p>Subtotal</p>
                                </th>
                                <th>
                                    <p></p>
                                </th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listaCarrinho}" var="p">

                                <tr style="background-color: white;border: white">
                                    <td id="produto">
                                        <img class = "prod-img" src="${p.path_MainImg}">
                                        <p>${p.productName}</p>
                                    </td>
                                    <td id="tamanho">

                            <c:choose>
                            <c:when test="${p.category eq 'Camiseta masculina' or p.category eq  'Camiseta feminina'}">
                                <select id="uf" name="uf" class="browser-default" class="validate" style="width: 60%;margin-left:30%;position: relative">
                                <option value="${pageContext.request.contextPath}/changeItemSize_Servlet?productId=${p.productId}&tamanho=${p.size}&context=cart" selected> ${p.size}</option>
                                <option value="${pageContext.request.contextPath}/changeItemSize_Servlet?productId=${p.productId}&tamanho=P&context=cart">P</option>
                                <option value="${pageContext.request.contextPath}/changeItemSize_Servlet?productId=${p.productId}&tamanho=M&context=cart">M</option>
                                <option value="${pageContext.request.contextPath}/changeItemSize_Servlet?productId=${p.productId}&tamanho=G&context=cart">G</option>
                            </select>

                            </c:when>
                            <c:otherwise>
                                <p>Tam. Único</p>
                        </c:otherwise>

                    </c:choose>
   

                                    <td id="qtd" class="borda">
                                        <div id="input">
                                            <a  href="${pageContext.request.contextPath}/Cart_Servlet?productId=${p.productId}&acao=subtrair" ><img class="change-quantity" src="icons/minus24px.png"></a>
                                                ${p.quantity}
                                            <a href="${pageContext.request.contextPath}/Cart_Servlet?productId=${p.productId}&acao=adicionar" ><img class="change-quantity" src="icons/add24px.png"></a>
                                        </div>
                                    </td>
                                    <td class="preco">
                                        <p>R$ ${Math.ceil(p.price)}</p>
                                    </td>
                                    <td>
                                        <p>${Math.ceil(p.totalPrice)}</p>

                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/Cart_Servlet?productId=${p.productId}&acao=excluir" ><img id="delete" src="icons/delete.png"></a>
                                    </td>

                                </tr>


                            </c:forEach>
                            <tr style="background-color: white;border: white">
                                <td id="produto">

                                </td>
                                <td id="qtd" class="borda">

                                </td>
                                <td id="qtd" class="borda">

                                </td>
                                <td class="preco">
                                    Soma:
                                </td>
                                <td>
                                    ${Math.ceil(valorTotal)}
                                </td>
                                <td>
                                </td>

                            </tr>

                        </tbody>
                    </table>
                    <div id="formCep">
                        <fieldset>
                            <form method="POST" action="${pageContext.request.contextPath}/Cart_Servlet">
                                <legend style="margin-left: 44%">Calcular frete</legend>

                                <input type="text" name="cep" id="cep" style="border:black 1px solid;margin-left: 30%;text-align: center;">
                                <input type="hidden" id="logradouro" name="rua">
                                <input type="hidden" id="bairro" name="bairro">
                                <input type="hidden" id="cidade" name="cidade">
                                <input type="hidden" id="uf" name="uf">

                                <input id="" class="inputCep" type="submit" value="Calcular frete" style="margin-left: 43%">


                            </form>
                        </fieldset>

                    </div>
                    <c:if test="${addre != null}">

                        <div id="endereco">
                            <span><c:out value="${addre.address_street}, ${addre.address_neighborhood}, ${addre.address_state_abbreviation}" /></span>
                        </div>
                    </c:if>


                    <h3 id="valorTotal"  >Valor total: R$ ${Math.ceil(valorTotal)}
                        <c:if test="${addre != null}" >
                            + Frete R$ ${Math.ceil(valorTotal*0.04)}
                            <p>Dias úteis: ${estimateDays}<p>
                        </c:if>


                    </h3>
                        
                    <div id="botaoFinalizar">
                        <a id="finalizar" class="waves-effect waves-light btn-large brown" href="${pageContext.request.contextPath}/ChooseDeliveryAddress_Servlet">Prosseguir</a>

                    </div>
                </section>

            </c:when>
            <c:otherwise>
                <div id="vazio">
                    <p>Carrinho está Vazio</p>
                </div>
            </c:otherwise>
        </c:choose>  
        <a id="continuar" href="${pageContext.request.contextPath}/Store_Servlet"><img id="voltarCompra" src="icons/undo.png">Continuar comprando</a>


        <script>

            $("#cep").focusout(function () {
                $.ajax({
                    url: 'https://viacep.com.br/ws/' + $(this).val() + '/json/unicode/',
                    dataType: 'json',
                    success: function (resposta) {
                        $("#logradouro").val(resposta.logradouro);
                        $("#complemento").val(resposta.complemento);
                        $("#bairro").val(resposta.bairro);
                        $("#cidade").val(resposta.localidade);
                        $("#uf").val(resposta.uf);
                        $("#numero").focus();
                    }
                });
            });
            
            
            
            $("select").click(function() {
  var open = $(this).data("isopen");
  if(open) {
    window.location.href = $(this).val()
  }

  $(this).data("isopen", !open);
});
        </script>
    </body>
</html>
