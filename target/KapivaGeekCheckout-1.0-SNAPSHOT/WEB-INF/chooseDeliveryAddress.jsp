<%-- 
    Document   : escolherEnderecoEntrega
    Created on : 09/05/2021, 09:41:36
    Author     : luans
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrinho</title>
        <link href="css/EstiloRevisarPedido.css" type="text/css" rel="stylesheet">

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

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
            <div class="nav-wrapper brown" id="nav-stepper">
                <div class="col s12" id="step">
                    <a href="${pageContext.request.contextPath}/Store_Servlet" class="breadcrumb white-text">Loja</a>                
                    <a href="${pageContext.request.contextPath}/Cart_Servlet?acao=abrirCarrinho" class="breadcrumb white-text">Meu carrinho</a>                  
                    <a href="${pageContext.request.contextPath}/ChooseDeliveryAddress_Servlet" class="breadcrumb white-text">Endereco</a>
                    <a class="breadcrumb grey-text">Pagamento</a>                   
                    <a class="breadcrumb grey-text">Confirma</a>
                </div>
            </div>
        </nav>
        <div id="endCadastrado">
                                <a href="${pageContext.request.contextPath}/Cart_Servlet?acao=abrirCarrinho" id="go-back" style="margin-top: 10%;">
                        <img src="icons/left-arrow.png">
                    </a>
            <form action="<c:url value="/ChooseDeliveryAddress_Servlet"/>" method="POST">
                <table class="table" style="border: none">
                    <thead>
                        <tr>
                            <th>Endereços cadastrados</th>
                            <th>Frete</th>

                        </tr>
                    </thead>
                    <tbody>


                        <tr>
                            <td>
                                <input type="radio" id="${mainAddress.address_code}" name="end" value="${mainAddress.address_id}" required>
                                <label for="${mainAddress.address_code}" class="radiobutton-label">
                                    <b>${mainAddress.address_title}</b>
                                    <br>
                                    ${mainAddress.address_street},${mainAddress.address_number} - ${mainAddress.address_neighborhood},${mainAddress.address_state_abbreviation} - ${mainAddress.address_code}
                                </label>
                            </td>
                            <td>R$ ${Math.ceil(valorTotal*0.02)} em 5 dias úteis<td>

                                    

                        </tr>


                    </tbody>
                </table>
                <button class="btn waves-effect waves-light brown" type="submit" name="action" id="send-button">Prosseguir
                    <i class="material-icons right"></i>
                </button>

            </form>

            <h3 id="other-option" style="margin-top: 5%;">ou cadastre um novo</h3> 

            <form class="formCadastro" method="POST" action="<c:url value="/RegisterNewDeliveryAddress"/>" novalidate class="form">
                <c:if test="${hasError eq 'true'}">
                    <div class="msg msg-error z-depth-3 scale-transition">
                        <ul>
                        <c:forEach items="${errorList}" var="p">
                            <li>${p}</li>
                        </c:forEach>
                            </ul>
                    </div>
                </c:if>


                    <div class="row">
                        <div class="input-field col s6">
                            <input type="text" id="tit" name="titulo"value="${deliveryAddr.address_title}" class="validate" required>
                            <label for="tit">Identificação</label>
                        </div>


                        <div class="input-field col s6">
                            <input type="text" name="cep" id="cep" value="${deliveryAddr.address_code}" pattern="\d{8}"class="validate" required>
                            <label for="cep">CEP</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s4">

                            <input type="text" placeholder="Logradouro" name="street" id="logradouro" value="${deliveryAddr.address_street}"readonly>
                            <label for="logradouro">Logradouro</label>
                        </div>
                        <div class="input-field col s4">
                            <input type="text" placeholder="Número" name="number" id="numero" value="${deliveryAddr.address_number}" required>
                            <label for="numero">Numero</label>
                        </div>
                            <div class="row">
                        <div class="input-field col s4">  
                            <input type="text" placeholder="Bairro" name="neighborhood" id="bairro" value="${deliveryAddr.address_neighborhood}" readonly>
                            <label for="bairro">Bairro</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6">
                            <input type="text" name="complement" id="complemento" value="${deliveryAddr.address_complement}"">
                            <label for="complemento">Complemento</label>
                        </div>
                        <div class="input-field col s6">

                            <select id="uf" name="uf" class="browser-default" readonly="readonly" tabindex="-1" aria-disabled="true" class="validate">
                                <option value="${deliveryAddr.address_state_abbreviation}">${deliveryAddr.address_state_abbreviation}</option>
                                <option value="AC">Ac</option>
                                <option value="AL">AL</option>
                                <option value="AP">AP</option>
                                <option value="AM">AM</option>
                                <option value="BA">BH</option>
                                <option value="CE">CE</option>
                                <option value="DF">DF</option>
                                <option value="ES">ES</option>
                                <option value="GO">GO</option>
                                <option value="MA">MA</option>
                                <option value="MT">MT</option>
                                <option value="MS">MS</option>
                                <option value="MG">MG</option>
                                <option value="PA">PA</option>
                                <option value="PB">PB</option>
                                <option value="PR">PR</option>
                                <option value="PE">PE</option>
                                <option value="PI">PI</option>
                                <option value="RJ">RJ</option>
                                <option value="RN">RN</option>
                                <option value="RS">RS</option>
                                <option value="RO">RO</option>
                                <option value="RR">RR</option>
                                <option value="SC">SC</option>
                                <option value="SP">SP</option>
                                <option value="SE">SE</option>
                                <option value="TO">TO</option>
                            </select>
                        </div>

                    </div>
                    <input type="text" name="customerId" value="${deliveryAddr.customer_customer_id}" style="display:none">
                    <h5 id="valor-estimado"></h5>
                    
                    
                    <button class="btn waves-effect waves-light brown" type="submit" name="action" id="send-button">Cadastrar
                        <i class="material-icons right"></i>
                    </button>







            </form>

            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js">

            </script>

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
                            document.getElementById("valor-estimado").innerHTML = "valor estimado para frete: R$"+${Math.ceil((valorTotal*0.04))} +" entrega em até 9 dias úteis";
                            

                        }
                    });
                });

            </script>
    </body>
</html>
