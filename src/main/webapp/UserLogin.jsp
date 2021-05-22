<%-- 
    Document   : UserLogin
    Created on : 19/05/2021, 00:53:44
    Author     : luans
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styleLoginUsr.css">
        <title>Login Usuário</title>
    </head>
    <body class="">
        <section id="container" class="container-fluid">
            <div id="logo" class="">
                <img src="images/kapiva(logo).png">
            </div>
            <div id="loginusr">
                <c:if test="${msgErro!= null}">
                    <span class="msgErro"><c:out value="${msgErro}"/></span>
                </c:if>
                <form method="post" action="${pageContext.request.contextPath}/UserLogin_Servlet" >
                    <div id="usuario" class="form">
                        <label>Usuário:</label>
                        <input type="text" name="username"/>
                    </div>
                    <div id="senha" >
                        <label>Senha:</label>
                        <input type="password" name="password"/>
                    </div>
                    <div id="botoes">
                        <input type="submit" value="Entrar" id="sub">
                        <input type="reset" value="Cancelar">
                    </div>
                </form>
                    <div id="loginCadastro">
                        <p style="text-align: center">Se ainda não é cadastrado <a href="${pageContext.request.contextPath}/RegisterCustomer_Servlet">Cadastre-se</a></p>
                </div>
            </div>

        </section>
    </body>
</html>
