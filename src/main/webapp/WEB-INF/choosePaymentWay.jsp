<%-- 
    Document   : EscolherFormaPagamento
    Created on : 08/05/2021, 14:36:34
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
            <div class="nav-wrapper brown" id="nav-stepper">
                <div class="col s12" id="step">
                    <a href="${pageContext.request.contextPath}/Store_Servlet" class="breadcrumb white-text">Loja</a>                
                    <a href="${pageContext.request.contextPath}/Cart_Servlet?acao=abrirCarrinho" class="breadcrumb white-text">Meu carrinho</a>                  
                    <a href="${pageContext.request.contextPath}/ChooseDeliveryAddress_Servlet" class="breadcrumb white-text">Endereco</a>
                    <a href="${pageContext.request.contextPath}/choosePayment_Servlet" class="breadcrumb white-text">Pagamento</a>
                    <a class="breadcrumb grey-text">Confirma</a>
                </div>
            </div>
        </nav>
        <h1 id="payment-title">Escolha a forma de pagamento</h1>
        <section id="pagamento">
            <a href="${pageContext.request.contextPath}/ChooseDeliveryAddress_Servlet" id="go-back" style="margin-top: 10%;">
                <img src="icons/left-arrow.png">
            </a>

            <c:if test="${hasError eq 'true'}">
                <div class="msg msg-error z-depth-3 scale-transition">
                    <ul>
                        <c:forEach items="${errorList}" var="p">
                            <li>${p}</li>
                            </c:forEach>
                    </ul>
                </div>
            </c:if>
            <ul class="collapsible">
                <li>
                    <div class="collapsible-header"><i class="material-icons">feed</i>Boleto</div>
                    <div class="collapsible-body">
                        <form action="<c:url value="/choosePayment_Servlet"/>" method="POST">
                            <input type="radio" id="boleto" name="boleto" value="boleto" required>



                            <c:choose>
                                <c:when test="${hasDiscount eq 'true'}">
                                    <label for="boleto" value="boleto"class="black-text">de <s>R$ ${Math.ceil(valorTotal+valorFrete)}</s> por R$ ${Math.ceil((valorTotal+valorFrete)-(valorTotal*(discountPercentage/100)))}</label>

                                </c:when>
                                <c:otherwise>
                                    <label for="boleto" value="boleto"class="black-text">1x de ${Math.ceil(valorTotal+valorFrete)}</label>
                                </c:otherwise>

                            </c:choose>





                            <button class="btn waves-effect brown" id="pay-boleto" type="submit" name="action">Pagar com boleto
                                <i class="material-icons right"></i>
                            </button>
                        </form>
                    </div>
                </li>
                <li>
                    <div class="collapsible-header"><i class="material-icons">credit_card</i>Cartão de crédito</div>
                    <div class="collapsible-body">
                        <form action="<c:url value="/choosePayment_Servlet"/>" method="POST">
                            <label>Quantidade de parcelas</label>
                            <select class="browser-default" name="parcelas">

                                
                                
                                <c:choose>
                                <c:when test="${hasDiscount eq 'true'}">
 
                                <option value="${cartaoPag.payment_instalments}" selected>${cartaoPag.payment_instalments}</option>
                                <option value="1">1x de R$ ${Math.ceil(((valorTotal+valorFrete)-(valorTotal*(discountPercentage/100))))}</option>
                                <option value="2">2x de R$ ${Math.ceil(((valorTotal+valorFrete)-(valorTotal*(discountPercentage/100)))/2)}</option>
                                <option value="3">3x de R$ ${Math.ceil(((valorTotal+valorFrete)-(valorTotal*(discountPercentage/100)))/3)}</option>
                                <option value="4">4x de R$ ${Math.ceil(((valorTotal+valorFrete)-(valorTotal*(discountPercentage/100)))/4)}</option>
                                <option value="5">5x de R$ ${Math.ceil(((valorTotal+valorFrete)-(valorTotal*(discountPercentage/100)))/5)}</option>
                                <option value="6">6x de R$ ${Math.ceil(((valorTotal+valorFrete)-(valorTotal*(discountPercentage/100)))/6)}</option>
                                <option value="7">7x de R$ ${Math.ceil(((valorTotal+valorFrete)-(valorTotal*(discountPercentage/100)))/7)}</option>
                                <option value="8">8x de R$ ${Math.ceil(((valorTotal+valorFrete)-(valorTotal*(discountPercentage/100)))/8)}</option>
                                <option value="9">9x de R$ ${Math.ceil(((valorTotal+valorFrete)-(valorTotal*(discountPercentage/100)))/9)}</option>
                                <option value="10">10x de R$ ${Math.ceil(((valorTotal+valorFrete)-(valorTotal*(discountPercentage/100)))/10)}</option>
                                <option value="11">11x de R$ ${Math.ceil(((valorTotal+valorFrete)-(valorTotal*(discountPercentage/100)))/11)}</option>
                                <option value="12">12x de R$ ${Math.ceil(((valorTotal+valorFrete)-(valorTotal*(discountPercentage/100)))/12)}</option>

                                </c:when>
                                <c:otherwise>
                                <option value="${cartaoPag.payment_instalments}" selected>${cartaoPag.payment_instalments}</option>
                                <option value="1">1x de ${Math.ceil((valorTotal+valorFrete))}</option>
                                <option value="2">2x de ${Math.ceil((valorTotal+valorFrete)/2)}</option>
                                <option value="3">3x de ${Math.ceil((valorTotal+valorFrete)/3)}</option>
                                <option value="4">4x de ${Math.ceil((valorTotal+valorFrete)/4)}</option>
                                <option value="5">5x de ${Math.ceil((valorTotal+valorFrete)/5)}</option>
                                <option value="6">6x de ${Math.ceil((valorTotal+valorFrete)/6)}</option>
                                <option value="7">7x de ${Math.ceil((valorTotal+valorFrete)/7)}</option>
                                <option value="8">8x de ${Math.ceil((valorTotal+valorFrete)/8)}</option>
                                <option value="9">9x de ${Math.ceil((valorTotal+valorFrete)/9)}</option>
                                <option value="10">10x de ${Math.ceil((valorTotal+valorFrete)/10)}</option>
                                <option value="11">11x de ${Math.ceil((valorTotal+valorFrete)/11)}</option>
                                <option value="12">12x de ${Math.ceil((valorTotal+valorFrete)/12)}</option>
                                </c:otherwise>

                            </c:choose>

                                
                                
                            </select>


                            <div class="row">
                                <div class="input-field col s6">
                                    <input id="numCartao" type="text" class="validate" name="numCartao" value="${cartaoPag.card_number}">
                                    <label for="numCartao">Número do cartão</label>
                                </div>
                                <div class="input-field col s2">
                                    <input id="cvv" type="text" class="validate" name ="cvv" value="${cartaoPag.cvv}">
                                    <label for="cvv">CVV</label>
                                </div>
                                <div class="input-field col s4">
                                    <input id="exp" type="month" class="exp" name="exp"value="${cartaoPag.exp}" >
                                    <label for="exp">Data exp.</label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="input-field col s8">
                                    <input id="printedName" type="text" class="validate" name="printedName" value="${cartaoPag.printedName}">
                                    <label for="printedName">Nome impresso</label>
                                </div>
                            </div>

                            <button class="btn waves-effect waves-light brown"  id="pay-card" type="submit" name="action">Salvar
                                <i class="material-icons right"></i>
                            </button>

                        </form>
                    </div>
                </li>
            </ul>
            <div id="formCep">
                <c:if test="${hasDiscountError eq 'true'}">
                    <div class="msg msg-error z-depth-3 scale-transition">
                        <ul>
                            <li>${ErrorMessage}</li>
                        </ul>
                    </div>
                </c:if>
                <c:if test="${hasDiscountError eq 'false'}">
                    <div class="msg msg-success z-depth-3 scale-transition">
                        <ul>

                            <li>${ErrorMessage}</li>

                        </ul>
                    </div>
                </c:if>


                <fieldset>
                    <form method="POST" action="${pageContext.request.contextPath}/addDiscount_Servlet">
                        <legend style="margin-left: 43%">Inserir cupom</legend>

                        <input type="text" name="cupom" id="cupom" style="border:black 1px solid;margin-left: 28%;width:45%;text-align: center;">

                        <input id="" class="inputCep" type="submit" value="Submeter" style="margin-left: 44%">


                    </form>
                </fieldset>

            </div>
        </section>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js">


            $(document).ready(function () {
                $('.collapsible').collapsible();
            });
        </script>
        <script>


            $('.button-collapse').sideNav({
                menuWidth: 300, // Default is 300
                edge: 'left', // Choose the horizontal origin
                closeOnClick: false, // Closes side-nav on <a> clicks, useful for Angular/Meteor
                draggable: true // Choose whether you can drag to open on touch screens,
            }
            );

            document.addEventListener('DOMContentLoaded', function () {
                var elems = document.querySelectorAll('.sidenav');
                var instances = M.Sidenav.init(elems);
            });


            $(document).ready(function () {
                $('.slider').slider();
            });







            function ComprarItem() {
                var numeroString = new String(document.querySelector('.itemM').innerHTML);
                numero = Number(numeroString);
                soma = numero + 1;
                Retorno = String(Object(soma));
                document.querySelector('.itemM').innerHTML = Retorno;
                document.querySelector('.itemD').innerHTML = Retorno;
            }

            document.getElementById('comprar');
            comprar.addEventListener('click', function () {
                ComprarItem();
            });



        </script>

    </body>
</html>
