<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="loc" var="lang"/>

<html>
<head>
    <title><fmt:message key="edit.product.title" bundle="${lang}"/></title>
</head>
<body>
<header>
    <style>
        header {
            background: darkcyan;
        }
    </style>
    <h1><p><a href="${pageContext.request.contextPath}/admin/home.jsp"><img
            src="https://cdn.discordapp.com/attachments/495897588147421186/975447405103308800/logo.png" width="600"
            height="90"></a></p></h1>
    <c:if test="${sessionScope.lang == 'en'}">
        <a href="?locale=uk"><img src="https://upload.wikimedia.org/wikipedia/commons/4/49/Flag_of_Ukraine.svg"
                                  width="30" height="25"> </a>
    </c:if>
    <c:if test="${sessionScope.lang == 'uk'}">
        <a href="?locale=en"><img
                src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Flag_of_the_United_Kingdom_%282-3%29.svg/1200px-Flag_of_the_United_Kingdom_%282-3%29.svg.png"
                width="30" height="25"></a>
    </c:if>
    <a href="${pageContext.request.contextPath}/logout"><fmt:message key="logout" bundle="${lang}"/></a>
</header>

<table class="table">
    <thead>
    <tr>
        <th><fmt:message key="product.table.name" bundle="${lang}"/></th>
        <th><fmt:message key="product.table.color" bundle="${lang}"/></th>
        <th><fmt:message key="product.table.category" bundle="${lang}"/></th>
        <th><fmt:message key="product.table.size" bundle="${lang}"/></th>
        <th><fmt:message key="product.table.price" bundle="${lang}"/></th>
        <th><fmt:message key="product.table.price.uah" bundle="${lang}"/></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${requestScope.updateSelected.name}</td>
        <td><fmt:message key="${requestScope.updateSelected.color}" bundle="${lang}"/></td>
        <td><fmt:message key="${requestScope.updateSelected.category}" bundle="${lang}"/></td>
        <td>${requestScope.updateSelected.size}</td>
        <td>${requestScope.updateSelected.price}</td>
        <td>${requestScope.updateSelected.priceUah}</td>
    </tr>
    </tbody>
</table>

<form method="post"
      action="${pageContext.request.contextPath}/edit">
    <table style="margin-left: auto; margin-right: auto">
        <tr>
            <td><input type="hidden" name="updateId" id="updateId" value="${requestScope.updateSelected.id}"></td>
        </tr>
        <tr>
            <td style="text-align: right"><label for="updateName"><fmt:message key="product.name"
                                                                               bundle="${lang}"/></label></td>
            <td><input type="text" name="updateName" id="updateName" size=100></td>

        </tr>
        <tr>
            <td style="text-align: right"><label for="updateColor"><fmt:message key="product.color"
                                                                                bundle="${lang}"/></label>
                <select name="updateColor" id="updateColor">
                    <option value="">Don`t change</option>
                    <option value="black"><fmt:message key="black" bundle="${lang}"/></option>
                    <option value="blue"><fmt:message key="blue" bundle="${lang}"/></option>
                    <option value="white"><fmt:message key="white" bundle="${lang}"/></option>
                    <option value="orange"><fmt:message key="orange" bundle="${lang}"/></option>
                    <option value="red"><fmt:message key="red" bundle="${lang}"/></option>
                    <option value="pink"><fmt:message key="pink" bundle="${lang}"/></option>
                    <option value="yellow"><fmt:message key="yellow" bundle="${lang}"/></option>
                </select>
            </td>

        </tr>
        <tr>
            <td style="text-align: right"><label for="updateCategory"><fmt:message key="product.category"
                                                                                   bundle="${lang}"/></label>
                <select name="updateCategory" id="updateCategory">
                    <option value="">Don`t change</option>
                    <option value="JACKET"><fmt:message key="JACKET" bundle="${lang}"/></option>
                    <option value="SHORTS"><fmt:message key="SHORTS" bundle="${lang}"/></option>
                    <option value="T_SHIRT"><fmt:message key="T_SHIRT" bundle="${lang}"/></option>
                    <option value="TROUSERS"><fmt:message key="TROUSERS" bundle="${lang}"/></option>
                    <option value="HOODIE"><fmt:message key="HOODIE" bundle="${lang}"/></option>
                </select>
            </td>

        </tr>
        <tr>
            <td style="text-align: right"><label for="updateSize"><fmt:message key="product.size"
                                                                               bundle="${lang}"/></label>
                <select name="updateSize" id="updateSize">
                    <option value="">Don`t change</option>
                    <option value="XS">XS</option>
                    <option value="S">S</option>
                    <option value="M">M</option>
                    <option value="L">L</option>
                    <option value="XL">XL</option>
                </select>
            </td>

        </tr>
        <tr>
            <td style="text-align: right"><label for="updatePrice"><fmt:message key="price.usd"
                                                                                bundle="${lang}"/></label>
            </td>
            <td><input type="number" step=0.01 name="updatePrice" id="updatePrice" size=100 value="0"></td>

        </tr>
        <tr>
            <td style="text-align: right"><label for="updatePriceUah"><fmt:message key="price.uah"
                                                                                   bundle="${lang}"/></label>
            </td>
            <td><input type="number" step=0.01 name="updatePriceUah" id="updatePriceUah" size=100 value="0"></td>


        </tr>

        <tr>
            <td></td>
            <td style="text-align: right"><input class="button" type="submit"
                                                 value="<fmt:message key="edit" bundle="${lang}"/>"/></td>
        </tr>

    </table>
</form>
</body>
</html>