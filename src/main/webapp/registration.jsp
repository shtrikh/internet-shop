<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="loc" var="lang"/>

<html>
<head>
    <title><fmt:message key="registration.title" bundle="${lang}"/></title>
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
<form method="get"
      action="${pageContext.request.contextPath}/registration">
    <table style="margin-left: auto; margin-right: auto">
        <tr>
            <td style="text-align: right"><label for="name"><fmt:message key="name" bundle="${lang}"/></label></td>
            <td><input type="text" name="name" id="name" pattern="^[a-zA-Z\s]+$" size=100></td>

        </tr>
        <tr>
            <td style="text-align: right"><label for="surname"><fmt:message key="surname" bundle="${lang}"/></label></td>
            <td><input type="text" name="surname" id="surname" pattern="^[a-zA-Z\s]+$" size=100></td>

        </tr>
        <tr>
            <td style="text-align: right"><label for="email"><fmt:message key="email" bundle="${lang}"/></label></td>
            <td><input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" name="email" id="email" size=100/></td>


        </tr>
        <tr>
            <td style="text-align: right"><label for="login"><fmt:message key="login" bundle="${lang}"/></label></td>
            <td><input type="text" name="login" id="login" size=100></td>

        </tr>
        <tr>
            <td style="text-align: right"><label for="password"><fmt:message key="password" bundle="${lang}"/></label></td>
            <td><input type="password" name="password" id="password" size=100></td>

        </tr>
        <tr>
            <td></td>
            <td style="text-align: right"><input class="button" type="submit" value="<fmt:message key="sign.up" bundle="${lang}"/>"/></td>
        </tr>

    </table>
</form>
<p><a href="${pageContext.request.contextPath}/login"><fmt:message key="login" bundle="${lang}"/></a></p>
</body>
</html>