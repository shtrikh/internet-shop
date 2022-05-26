<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="loc" var="lang"/>

<html>
<head>
    <title><fmt:message key="added.product.title" bundle="${lang}"/></title>
</head>
<body>
<header>
    <style>
        header{
            background: darkcyan;
        }
    </style>
    <h1><p><a href="${pageContext.request.contextPath}/admin/home.jsp"><img src="https://cdn.discordapp.com/attachments/495897588147421186/975447405103308800/logo.png" width="600" height="90"></a></p></h1>
    <c:if test="${sessionScope.lang == 'en'}">
        <a href="?locale=uk"><img src="https://upload.wikimedia.org/wikipedia/commons/4/49/Flag_of_Ukraine.svg" width="30" height="25">  </a>
    </c:if>
    <c:if test="${sessionScope.lang == 'uk'}">
        <a href="?locale=en"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Flag_of_the_United_Kingdom_%282-3%29.svg/1200px-Flag_of_the_United_Kingdom_%282-3%29.svg.png"
                                  width="30" height="25"></a>
    </c:if>
    <a href="${pageContext.request.contextPath}/logout"><fmt:message key="logout" bundle="${lang}"/></a>
</header>

<table style="margin-left: auto; margin-right: auto">
    <thead>
    <tr>
        <th><fmt:message key="product.table.name" bundle="${lang}"/></th>
        <th><fmt:message key="product.table.color" bundle="${lang}"/></th>
        <th><fmt:message key="product.table.category" bundle="${lang}"/></th>
        <th><fmt:message key="product.table.size" bundle="${lang}"/></th>
        <th><fmt:message key="product.table.price" bundle="${lang}"/></th>
        <th><fmt:message key="product.table.added.time" bundle="${lang}"/></th>
    </tr>
    </thead>
    <tbody>
        <tr>
            <td>${requestScope.addedProduct.name}</td>
            <td><fmt:message key="${requestScope.addedProduct.color}" bundle="${lang}"/></td>
            <td><fmt:message key="${requestScope.addedProduct.category}" bundle="${lang}"/></td>
            <td>${requestScope.addedProduct.size}</td>
            <c:if test="${sessionScope.lang == 'en'}">
                <td>${requestScope.addedProduct.price}</td>
            </c:if>
            <c:if test="${sessionScope.lang == 'uk'}">
                <td>${requestScope.addedProduct.priceUah}</td>
            </c:if>
            <td>${requestScope.addedProduct.addedTime}</td>
            <form method="get"
                  action="${pageContext.request.contextPath}/delete-product">

                <td><input type="hidden" name="productIdDelete" id="productIdDelete" min=1 value="${requestScope.addedProduct.id}"></td>
                <td style="text-align: right"><input class="button" type="submit" value="<fmt:message key="cancel" bundle="${lang}"/>"/></td>

            </form>
            <form method="get" action="${pageContext.request.contextPath}/admin/home.jsp">
                <td><button type="submit"><fmt:message key="add" bundle="${lang}"/></button></td>
            </form>
        </tr>
    </tbody>
</table>

</body>
</html>