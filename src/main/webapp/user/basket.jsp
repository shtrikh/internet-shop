<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="loc" var="lang"/>

<html>
<head>
    <title><fmt:message key="basket.title" bundle="${lang}"/></title>
</head>
<body>
<header>
    <style>
        header{
            background: darkcyan;
        }
    </style>
    <h1><p><a href="${pageContext.request.contextPath}/user/home.jsp"><img src="https://cdn.discordapp.com/attachments/495897588147421186/975447405103308800/logo.png" width="600" height="90"></a></p></h1>
    <c:if test="${sessionScope.lang == 'en'}">
        <a href="?locale=uk"><img src="https://upload.wikimedia.org/wikipedia/commons/4/49/Flag_of_Ukraine.svg" width="30" height="25">  </a>
        </c:if>
        <c:if test="${sessionScope.lang == 'uk'}">
        <a href="?locale=en"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Flag_of_the_United_Kingdom_%282-3%29.svg/1200px-Flag_of_the_United_Kingdom_%282-3%29.svg.png"
                                  width="30" height="25"></a>
        </c:if>
    <a href="${pageContext.request.contextPath}/logout"><fmt:message key="logout" bundle="${lang}"/></a>
</header>


<hr/>
<table style="margin-left: auto; margin-right: auto">
    <thead>
    <tr>
        <th><fmt:message key="order.id" bundle="${lang}"/></th>
        <th><fmt:message key="order.user.id" bundle="${lang}"/></th>
        <th><fmt:message key="order.product.id" bundle="${lang}"/></th>
        <th><fmt:message key="order.status" bundle="${lang}"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.userOrders}" var="order">
        <tr>
            <td>${order.orderId}</td>
            <td>${order.userId}</td>
            <td>${order.productId}</td>
            <td><fmt:message key="${order.status}" bundle="${lang}"/></td>
            <form method="get"
                  action="${pageContext.request.contextPath}/order-delete">

                <td><input type="hidden" name="orderIdDelete" id="orderIdDelete" min=1 value="${order.orderId}"></td>
                <td style="text-align: right"><input class="button" type="submit" value="Delete"/></td>

            </form>
        </tr>
    </c:forEach>
        <tr>
            <td>
                <c:if test="${sessionScope.lang == 'en'}">
                    Total price: ${requestScope.totalPriceUsd}
                </c:if>
                <c:if test="${sessionScope.lang == 'uk'}">
                    Загальна ціна: ${requestScope.totalPriceUah}
                </c:if>
            </td>
        </tr>
    </tbody>





    <form method="get"
          action="${pageContext.request.contextPath}/buy-confirm">

        <td style="text-align: right"><input class="button" type="submit" value="<fmt:message key="confirm.buy" bundle="${lang}"/>"/></td>

    </form>

</table>

<hr/>


</body>
</html>