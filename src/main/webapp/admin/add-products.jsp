<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="loc" var="lang"/>

<html>
<head>
    <title><fmt:message key="add.products.title" bundle="${lang}"/></title>
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
<form method="post"
      action="${pageContext.request.contextPath}/add-products">
    <table style="margin-left: auto; margin-right: auto">
        <tr>
            <td style="text-align: right"><label for="productName"><fmt:message key="product.name"
                                                                                bundle="${lang}"/></label></td>
            <td><input type="text" name="productName" id="productName" size=100></td>

        </tr>
        <tr>
            <td style="text-align: right"><label for="productColor"><fmt:message key="product.color"
                                                                                 bundle="${lang}"/></label></td>
            <td>
                <select name="productColor" id="productColor">
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
            <td style="text-align: right"><label for="productCategory"><fmt:message key="product.category"
                                                                                    bundle="${lang}"/></label></td>
            <td>
                <select name="productCategory" id="productCategory">
                    <option value="JACKET"><fmt:message key="JACKET" bundle="${lang}"/></option>
                    <option value="SHORTS"><fmt:message key="SHORTS" bundle="${lang}"/></option>
                    <option value="T_SHIRT"><fmt:message key="T_SHIRT" bundle="${lang}"/></option>
                    <option value="TROUSERS"><fmt:message key="TROUSERS" bundle="${lang}"/></option>
                    <option value="HOODIE"><fmt:message key="HOODIE" bundle="${lang}"/></option>
                </select>
            </td>

        </tr>
        <tr>
            <td style="text-align: right"><label for="productSize"><fmt:message key="product.size"
                                                                                bundle="${lang}"/></label></td>
            <td>
                <select name="productSize" id="productSize">
                    <option value="XS">XS</option>
                    <option value="S">S</option>
                    <option value="M">M</option>
                    <option value="L">L</option>
                    <option value="XL">XL</option>
                </select>
            </td>

        </tr>
        <tr>
            <td style="text-align: right"><label for="priceUsd"><fmt:message key="price.usd" bundle="${lang}"/></label>
            </td>
            <td><input type="number" step=0.01 name="priceUsd" id="priceUsd" size=100></td>

        </tr>
        <tr>
            <td style="text-align: right"><label for="priceUah"><fmt:message key="price.uah" bundle="${lang}"/></label>
            </td>
            <td><input type="number" step=0.01 name="priceUah" id="priceUah" size=100></td>


        </tr>

        <tr>
            <td></td>
            <td style="text-align: right"><input class="button" type="submit"
                                                 value="<fmt:message key="add.product" bundle="${lang}"/>"/></td>
        </tr>

    </table>
</form>
</body>
</html>