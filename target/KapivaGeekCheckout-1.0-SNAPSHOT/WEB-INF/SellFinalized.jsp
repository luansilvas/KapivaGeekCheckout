<%-- 
    Document   : SellFinalized
    Created on : 19/05/2021, 22:37:55
    Author     : luans
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

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

        <%@include file="../menu.jsp" %>
        <div class="container">
            <div class="card" style="margin-top: 20%;background-color: green;color: white">



                <h1 class="thanks" style="text-align: center;margin-top:10%">Obrigado!</h1>

                <h5 class="thanks" style="text-align: center;">Acompanhe sua compra de valor R$${Math.ceil(valorCompraFeita)} pelo código <b><u>${sessionScope.codPedido}</u></b></h5>

                <a href="${pageContext.request.contextPath}/Store_Servlet" id="go-back" style="margin-top: 10%;text-align: center;">
                    Continuar comprando
                </a>
            </div>
        </div>
    </body>
</html>
