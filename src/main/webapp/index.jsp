<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="loc" var="lang"/>

<html>
<head>
    <title><fmt:message key="home.title" bundle="${lang}"/></title>

</head>
<body>
<header>
    <style>
        header{
            background: darkcyan;
        }
    </style>

    <h1><p><a href="${pageContext.request.contextPath}/index.jsp"><img src="https://cdn.discordapp.com/attachments/495897588147421186/975447405103308800/logo.png" width="600" height="90"></a></p></h1>

    <c:if test="${sessionScope.lang == 'en'}">
        <a href="?locale=uk"><img src="https://upload.wikimedia.org/wikipedia/commons/4/49/Flag_of_Ukraine.svg" width="30" height="25">  </a>
    </c:if>
    <c:if test="${sessionScope.lang == 'uk'}">
        <a href="?locale=en"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Flag_of_the_United_Kingdom_%282-3%29.svg/1200px-Flag_of_the_United_Kingdom_%282-3%29.svg.png"
                                  width="30" height="25"></a>
    </c:if>
</header>


<p><a href="${pageContext.request.contextPath}/login.jsp"><fmt:message key="login" bundle="${lang}"/></a></p>
<p><a href="${pageContext.request.contextPath}/registration.jsp"><fmt:message key="sign.up" bundle="${lang}"/></a></p>
<hr/>

<form method="get"
      action="${pageContext.request.contextPath}/set-param">
    <table style="margin-left: auto; margin-right: auto">
        <tr>
            <td style="text-align: center"><label for="pageSize"><fmt:message key="pageSize" bundle="${lang}"/></label>
            </td>
            <td style="text-align: center">
                <input type="radio" name="pageSize" id="pageSize" value=5 checked>
                <label for="pageSize">5</label>
                <input type="radio" name="pageSize" id="pageSize2" value=10>
                <label for="pageSize">10</label>
            </td>
        </tr>
        <tr>
            <td style="text-align: center"><label for="sortBy"><fmt:message key="sortBy" bundle="${lang}"/></label>
            <td>
                <select name="sortBy" id="sortBy">
                    <option value="">-</option>
                    <option value="name"><fmt:message key="fap.name" bundle="${lang}"/></option>
                    <option value="price"><fmt:message key="fap.price" bundle="${lang}"/></option>
                    <option value="addedTime"><fmt:message key="fap.newer" bundle="${lang}"/></option>
                </select>
            </td>
        </tr>
        <tr>
            <td style="text-align: center"><label for="sortOrder"><fmt:message key="sortOrder" bundle="${lang}"/></label>
            <td>
                <input type="radio" name="sortOrder" id="sortOrder" value=ASC checked>
                <label for="sortOrder">↓</label>
                <input type="radio" name="sortOrder" id="sortOrder2" value=DESC>
                <label for="sortOrder">↑</label>
            </td>
        </tr>

        <tr>
            <td style="text-align: center"><label for="filterByColumn"><fmt:message key="filterBy" bundle="${lang}"/></label>
            <td>
                <select name="filterByColumn" id="filterByColumn">
                    <option value="">-</option>
                    <option value="color"><fmt:message key="fap.color" bundle="${lang}"/></option>
                    <option value="category"><fmt:message key="fap.category" bundle="${lang}"/></option>
                    <option value="size"><fmt:message key="fap.size" bundle="${lang}"/></option>
                </select>
            </td>
        </tr>

        <tr>
            <td style="text-align: center"><label for="filterByValue"><fmt:message key="value" bundle="${lang}"/></label>
                <td>
                <select name="filterByValue" id="filterByValue">
                    <optgroup label="Color">
                        <option value="black"><fmt:message key="black" bundle="${lang}"/></option>
                        <option value="blue"><fmt:message key="blue" bundle="${lang}"/></option>
                        <option value="white"><fmt:message key="white" bundle="${lang}"/></option>
                        <option value="orange"><fmt:message key="orange" bundle="${lang}"/></option>
                        <option value="red"><fmt:message key="red" bundle="${lang}"/></option>
                        <option value="pink"><fmt:message key="pink" bundle="${lang}"/></option>
                        <option value="yellow"><fmt:message key="yellow" bundle="${lang}"/></option>
                    </optgroup>
                    <optgroup label="Category">
                        <option value="JACKET"><fmt:message key="JACKET" bundle="${lang}"/></option>
                        <option value="SHORTS"><fmt:message key="SHORTS" bundle="${lang}"/></option>
                        <option value="T_SHIRT"><fmt:message key="T_SHIRT" bundle="${lang}"/></option>
                        <option value="TROUSERS"><fmt:message key="TROUSERS" bundle="${lang}"/></option>
                        <option value="HOODIE"><fmt:message key="HOODIE" bundle="${lang}"/></option>
                    </optgroup>
                    <optgroup label="Size">
                        <option value="XS">XS</option>
                        <option value="S">S</option>
                        <option value="M">M</option>
                        <option value="L">L</option>
                        <option value="XL">XL</option>
                    </optgroup>
                </select>
            </td>
        </tr>

        <tr>
            <td><input type="hidden" name="pageNumber" id="pageNumber" value=1></td>
        </tr>

        <tr>
            <td style="text-align: center"><input class="button" type="submit" value="<fmt:message key="find" bundle="${lang}"/>"/></td>
        </tr>

        <tr>
            <form method="get"
                  action="${pageContext.request.contextPath}/previous-page">
                <td>
                    <a style="text-align: center"><input class="button" type="submit" value="←"/></a>
                </td>
            </form>
            <form method="get"
                  action="${pageContext.request.contextPath}/next-page">
                <td>
                    <a style="text-align: center"><input class="button" type="submit" value="→"/></a>
                </td>
            </form>
        </tr>


    </table>

</form>


<hr/>
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
    <c:forEach items="${requestScope.allProducts}" var="product">
        <tr>
            <td>${product.name}</td>
            <td><fmt:message key="${product.color}" bundle="${lang}"/></td>
            <td><fmt:message key="${product.category}" bundle="${lang}"/></td>
            <td>${product.size}</td>
            <c:if test="${sessionScope.lang == 'en'}">
                <td>${product.price}</td>
            </c:if>
            <c:if test="${sessionScope.lang == 'uk'}">
                <td>${product.priceUah}</td>
            </c:if>
            <td>${product.addedTime}</td>
            <form method="get"
                  action="${pageContext.request.contextPath}/registration.jsp">

                <td style="text-align: right"><input class="button" type="submit" value="Buy"/></td>

            </form>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
