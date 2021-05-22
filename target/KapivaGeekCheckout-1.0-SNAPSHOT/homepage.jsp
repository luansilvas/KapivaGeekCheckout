<%-- 
    Document   : index
    Created on : 02/03/2021, 22:26:42
    Author     : luans
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <link rel="stylesheet" href="css/styleFront.css" type="text/css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="materialize/css/materialize.min.css" media="screen,projection"/>
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>


        <title>Welcome!</title>
    </head>
    <body>
<%@include file="menu.jsp" %>






        <div class="slider" style="margin-top: 70px;">
            <ul class="slides">
                <li>
                    <img src="cover-images/cover-starwars.jpg">
                    <div class="caption center-align">
                        <h3>A maior geek store do país!</h3>
                        <h5 class="light grey-text text-lighten-3">Qualidade e preço de fábrica</h5>
                    </div>
                </li>
                <li>
                    <img src="cover-images/Drax-and-Rocket-Guardians-of-the-Galaxy-and-Avengers-1920x500.png">
                    <div class="caption center-align">
                        <h3>Melhores oportunidades</h3>
                        <h5 class="light grey-text text-lighten-3">Preços camaradas</h5>
                    </div>
                </li>
                <li>
                    <img src="cover-images/spider.jpg">
                    <div class="caption center-align">
                        <h3>Cobrimos oferta!</h3>
                        <h5 class="light grey-text text-lighten-3">Cobrimos o valor da concorrência</h5>
                    </div>
                </li>
                <li>
                    <img src="cover-images/Lord-Of-The-Rings-Fellowship-Of-The-Ring-1920x500.jpeg">
                    <div class="caption center-align">
                        <h3>Variedade dos produtos</h3>
                        <h5 class="light grey-text text-lighten-3">Pesquise o nome da sua série, anime ou filme para
                            conferir</h5>
                    </div>
                </li>
            </ul>
        </div>

        <div class="row" style="margin-top: -20px;">
            <form class="col s12" action="Store_Servlet" method="POST">
                <div class="row" id="search-bar">
                    <div class="input-field col s12">
                        <input id="icon_prefix" type="text" class="validate" name="categoria">
                        <label for="icon_prefix">Pesquise aqui</label>
                        <i class="material-icons prefix">search</i>
                    </div>
                </div>
            </form>
        </div>
        <c:if test="${hasPromocoes eq 'true'}">

            <div class="row">
                <c:forEach items="${promocoes}" var="b">
                    <section class="category-carousel" id="miniaturas">
                        <h3 class="category-title">Bonecos</h3>
                        <div class="category-category-carousel-container">
                            <div class="product-card">
                                <a href="<c:url value="/seeProductDetail?productId=${cm.productId}"/>">
                                    <img class="product-img" src="${b.path_MainImg}">
                                </a>
                                <div class="add-cart-field">
                                    <a href="${pageContext.request.contextPath}/AddCartData_Servlet?productId=${b.productId}" class="fas fa-cart-plus fa-2x"></a>
                                </div>
                                <div class="info">
                                    <a href=""></a>
                                    <a>
                                        <h3 class="">${b.productName}</h3>
                                    </a>
                                    <h5 class="price">${b.price}</h5>
                                    <br>
                                    <div class="stars">
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </section>
                </c:forEach>

            </div>
        </c:if>       
        <c:if test="${hasBonecos eq 'true'}">

            <div class="row">
                <section class="category-carousel" id="miniaturas">
                    <h3 class="category-title">Bonecos</h3>
                    <div class="category-category-carousel-container">
                        <c:forEach items="${bonecos}" var="b">

                            <div class="product-card">
                                <a href="<c:url value="/seeProductDetail?productId=${b.productId}"/>">
                                    <img class="product-img" src="${b.path_MainImg}">
                                </a>

                                <div class="add-cart-field">
                                    <a class="fas fa-cart-plus fa-2x" href="${pageContext.request.contextPath}/AddCartData_Servlet?productId=${b.productId}"></a>
                                </div>
                                <div class="info">
                                    <a href="#">
                                        <a>
                                            <h3 class="">${b.productName}</h3>
                                        </a>
                                        <h5 class="price">${b.price}</h5>
                                        <br>
                                        <div class="stars">
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                        </div>
                                    </a>
                                </div>
                            </div>

                        </c:forEach>
                    </div>
                </section>
            </div>
        </c:if>            
        <c:if test="${hasCM eq 'true'}">
            <div class="row">
                <section class="category-carousel" id="miniaturas">
                    <h3 class="category-title">Camiseta masculina</h3>
                    <div class="category-category-carousel-container">
                        <c:forEach items="${camisetasMasc}" var="cm">

                            <div class="product-card">
                                <a href="Comprar.html">
                                    <img class="product-img" src="${cm.path_MainImg}">
                                </a>                             
                                    <div class="add-cart-field" id="comprar">
                                        <a href="${pageContext.request.contextPath}/AddCartData_Servlet?productId=${cm.productId}"  class="fas fa-cart-plus fa-2x"></a>
                                    </div>
                     

                                <div class="info">

                                    <a>
                                        <h3 class="">${cm.productName}</h3>
                                    </a>
                                    <h5 class="price">${cm.price}</h5>
                                    <br>
                                    <div class="stars">
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>

                                    </div>

                                </div>
                            </div>

                        </c:forEach>
                    </div>
                </section>
            </div>
        </c:if>
        <c:if test="${hasCF eq 'true'}">
            <div class="row">
                <section class="category-carousel" id="camisetas-femininas">
                    <h3 class="category-title">Camiseta Femininas</h3>
                    <div class="category-category-carousel-container">
                        <c:forEach items="${camisetasFem}" var="cf">
                            <div class="product-card">
                                <a href="#">
                                    <img class="product-img" src="${cf.path_MainImg}">
                                </a>
                                <div class="add-cart-field">
                                    <a href="${pageContext.request.contextPath}/AddCartData_Servlet?productId=${cf.productId}"  class="fas fa-cart-plus fa-2x"></a>
                                </div>
                                <div class="info">
                                    <a href="#">
                                        <a>
                                            <h3 class="">${cf.productName}</h3>
                                        </a>
                                        <h5 class="price">${cf.price}</h5>
                                        <br>
                                        <div class="stars">
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </section>
            </div>
        </c:if>

        <c:if test="${hasCanecas eq 'true'}">
            <div class="row">
                <section class="category-carousel" id="camisetas-femininas">
                    <h3 class="category-title">Canecas</h3>
                    <div class="category-category-carousel-container">
                        <c:forEach items="${canecas}" var="c">
                            <div class="product-card">
                                <a href=#">
                                    <img class="product-img" src="${c.path_MainImg}">
                                </a>
                                <div class="add-cart-field">
                                    <a href="${pageContext.request.contextPath}/AddCartData_Servlet?productId=${c.productId}" class="fas fa-cart-plus fa-2x"></a>
                                </div>
                                <div class="info">
                                    <a href="#">
                                        <a>
                                            <h3 class="">${c.productName}</h3>
                                        </a>
                                        <h5 class="price">${c.price}</h5>
                                        <br>
                                        <div class="stars">
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                        </div>
                                    </a>
                                </div>
                            </div> 
                        </c:forEach>
                    </div>
                </section>
            </div>
        </c:if>
        <c:if test="${hasAcessorios eq 'true'}">
            <div class="row">
                <section class="category-carousel" id="camisetas-femininas">
                    <h3 class="category-title">Acessórios</h3>
                    <div class="category-category-carousel-container">
                        <c:forEach items="${acessorios}" var="a">
                            <div class="product-card">
                                <a href="${pageContext.request.contextPath}/AdicionarDadosCarrinho?productId=${cm.productId}"  >
                                    <img class="product-img" src="${a.path_MainImg}">
                                </a>
                                <div class="add-cart-field">
                                    <a href="${pageContext.request.contextPath}/AddCartData_Servlet?productId=${a.productId}" class="fas fa-cart-plus fa-2x"></a>
                                </div>
                                <div class="info">
                                    <a href="#">
                                        <a>
                                            <h3 class="">${a.productName}</h3>
                                        </a>
                                        <h5 class="price">${a.price}</h5>
                                        <br>
                                        <div class="stars">
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>

                                        </div>
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </section>
            </div>
        </c:if>
        <c:if test="${hasVariedades eq 'true'}">
            <div class="row">
                <section class="category-carousel" id="camisetas-femininas">
                    <h3 class="category-title">Acessórios</h3>
                    <div class="category-category-carousel-container">
                        <c:forEach items="${variedades}" var="v">
                            <div class="product-card">
                                <a href="${pageContext.request.contextPath}/AdicionarDadosCarrinho?productId=${cm.productId}"  >
                                    <img class="product-img" src="${v.path_MainImg}">
                                </a>
                                <div class="add-cart-field">
                                    <a href="${pageContext.request.contextPath}/AddCartData_Servlet?productId=${v.productId}" class="fas fa-cart-plus fa-2x"></a>
                                </div>
                                <div class="info">
                                    <a href="#">
                                        <a>
                                            <h3 class="">${v.productName}</h3>
                                        </a>
                                        <h5 class="price">${v.price}</h5>
                                        <br>
                                        <div class="stars">
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </section>
            </div>
        </c:if>


        <c:if test="${hasFiltro eq 'true'}">
            <div class="row">
                <section class="category-carousel" id="camisetas-femininas">
                    <h3 class="category-title">${filtro}</h3>
                    <div class="category-category-carousel-container">
                        <c:forEach items="${resultado}" var="r">

                            <div class="product-card">
                                <a href="${pageContext.request.contextPath}/AdicionarDadosCarrinho?productId=${cm.productId}"  >
                                    <img class="product-img" src="${r.path_MainImg}">
                                </a>
                                <div class="add-cart-field">
                                    <a href="${pageContext.request.contextPath}/AddCartData_Servlet?productId=${r.productId}" class="fas fa-cart-plus fa-2x"></a>
                                </div>
                                <div class="info">
                                    <a href="#">
                                        <a>
                                            <h3 class="">${r.productName}</h3>
                                        </a>
                                        <h5 class="price">${r.price}</h5>
                                        <br>
                                        <div class="stars">
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                        </div>
                                    </a>
                                </div>
                            </div> 
                        </c:forEach>
                    </div>
                </section>
            </div>
        </c:if>

        <c:if test="${hasFiltro eq 'pesquisa'}">
            <div class="row">
                <section class="category-carousel" id="camisetas-femininas">
                    <h3 class="category-title" style="margin-top:45px">Resultados para '${filtro}'</h3>
                    <div class="category-category-carousel-container">
                        <div class="row">
                            <c:forEach items="${resultado}" var="r">

                                <div class="product-card">
                                    <a href="#">
                                        <img class="product-img" src="${r.path_MainImg}">
                                    </a>
                                    <div class="add-cart-field">
                                        <a href="${pageContext.request.contextPath}/AddCartData_Servlet?productId=${r.productId}" class="fas fa-cart-plus fa-2x"></a>
                                    </div>
                                    <div class="info">
                                        <a href="#">
                                            <a>
                                                <h3 class="">${r.productName}</h3>
                                            </a>
                                            <h5 class="price">${r.price}</h5>
                                            <br>
                                            <div class="stars">
                                                <i class="fas fa-star"></i>
                                                <i class="fas fa-star"></i>
                                                <i class="fas fa-star"></i>
                                            </div>
                                        </a>
                                    </div>
                                </div>







                            </c:forEach>
                        </div>
                </section>
            </div>
        </c:if>
        <c:if test="${hasFiltro eq 'naoachouresultado'}">
            <h2>Resultados para '${filtro}'</h2>

            <div class="row">
                <h5>Não achamos nenhum produto nesse nome</h5>

            </div>
        </c:if>

        <footer class="page-footer brown">
            <div class="container">
                <div class="row">
                    <div class="col l6 s12">
                        <h5 class="white-text">Kapiva Geek</h5>
                        <p class="grey-text text-lighten-4">Todos os direitos reservados do uso de imagem.</p>
                    </div>
                    <div class="col l4 offset-l2 s12">

                    </div>
                </div>
            </div>
            <div class="footer-copyright">
                <div class="container">
                    <a class="grey-text text-lighten-4 right" href="#!"> © 2021 Copyright KapivaGeek</a>
                </div>
            </div>
        </footer>


        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script>
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
