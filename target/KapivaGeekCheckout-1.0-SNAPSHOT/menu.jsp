<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="pt-br">
    <head>
        <title>Kapiva Geek - A maior loja geek do país</title>

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
        <nav class="nav-wrapper white" id="menu-navbar">
            <div class="nav-wrapper">
                <a href="<c:url value="/Store_Servlet"/>" class="brand-logo" id="title"><img src="icons/kapiva(logo).png" alt="" id="logo-icon">Kapiva
                    Geek</a>
                <a href="#" data-target="slide-out" class="sidenav-trigger"><i class="material-icons"
                                                                               id="menu-icon">menu</i></a>
                <a href="Comprar.html" data-target="slide-out" id="menu-quantity-link" class="itemM">0</a>
                <a href="carrinho.html" data-target="slide-out" id="menu-cart-link"><i class="material-icons"
                                                                                       id="menu-cart-icon">shopping_cart</i></a>
                <a href="#icon_prefix" data-target="slide-out" id="menu-search-link"><i class="material-icons"
                                                                                        id="menu-search-icon">search</i></a>



                <ul class="left hide-on-med-and-down" id="categories">
                    <li><a href="<c:url value="/Store_Servlet?categoria=Boneco"/>"><img src="" alt="" id="universe-icon">Personagens</a></li>
                    <li><a href="<c:url value="/Store_Servlet?categoria=Camiseta+masculina"/>"><img src="" alt="" id="acessories-icon">Camisetas</a></li>
                    <li><a href="<c:url value="/Store_Servlet?categoria=Acessorio"/>"><img src="" alt="" id="universe-icon">Acessórios</a></li>
                    <li><a href="<c:url value="/Store_Servlet?categoria=Caneca"/>"><img src="" alt="" id="cart-icon">Canecas</a></li>
                    <li><a href="<c:url value="/Store_Servlet?categoria=variedade"/>"><img src="" alt="" id="user-icon">Utilidades</a></li>

                </ul>

                <form action="" method="post" id="search-topbar" action="Home_Servlet">
                    <div class="input-field">
                        <input id="#search" type="search" name="categoria">
                        <label class="label-icon" for="search"><i class="material-icons" style="color: black;"
                        id="search-topbar-label">search</i></label>
                    </div>
                </form>


                <ul class="right hide-on-med-and-down" id="options">

                    <li><a href=""><img src="icons/heart.png" alt="" id="universe-icon"></a></li>

                    

                     <c:choose>
                            <c:when test="${sessionScope.qtdeItensCarrinho != null}}">
                            <li class="itemD" id="itemD-quantity">0</li>
                            </c:when>
                            <c:otherwise>
                            
                            <li><a href="${pageContext.request.contextPath}/Cart_Servlet?acao=abrirCarrinho"><img src="icons/shopping-cart.png" alt="" id="cart-icon"></a></li>
                            <li class="itemD" id="itemD-quantity">${sessionScope.qtdeItensCarrinho}</li>
                        </c:otherwise>

                    </c:choose>
                            
                            
                            
                        <c:choose>
                            <c:when test="${sessionScope.user != null}">
                            <li><a href="<c:url value="/OrderHistory_Servlet" />"><img src="icons/user.png" id="user-icon">${user.getCustomer_name()}</a></li>
                            <li><a href="${pageContext.request.contextPath}/UserLogout"><img src="icons/logout.png" id="user-icon"></a></li>
                                </c:when>
                                <c:otherwise>
                            <li><a href="<c:url value="/UserLogin_Servlet" />"><img src="icons/user.png" id="user-icon">Login</a></li>

                        </c:otherwise>

                    </c:choose>



                </ul>
            </div>

        </nav>


        <ul id="slide-out" class="sidenav">
            <li>
                <div class="user-view">
                    <div class="background">
                        <img src="img/kapivaradormindo.jpg" alt="">
                    </div>




                    <c:choose>
                        <c:when test="${sessionScope.user != null}">
                            <a href="<c:url value="/alterRegister_Costumer" />"><span class="black-text email">${user.getCustomer_name()}</span></a>
                            </c:when>
                            <c:otherwise>

                            <a href="#"><span class="black-text name">Faça login</span></a>
                            <a href="#"><span class="black-text email">ou cadastra-se</span></a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </li>
            <li><a class="subheader">Navegue por categorias</a></li>
            <li><a href="#miniaturas"><i class="material-icons">smart_toy</i>Miniaturas</a></li>
            <li><a class="waves-effect" href="#camisetas"> <i class="material-icons">checkroom</i>Camisetas</a></li>
            <li><a class="waves-effect" href="#canecas"><i class="material-icons">local_cafe</i>Canecas</a></li>
            <li><a class="waves-effect" href="#acessorios"><i class="material-icons">luggage</i>Acessórios</a></li>
            <li><a class="waves-effect" href="#utilidades"><i class="material-icons">public</i>Variedades</a></li>
        </ul>
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