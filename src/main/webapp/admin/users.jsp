<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>




<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="loc" var="lang"/>


<html>
<head>

    <title><fmt:message key="users.title" bundle="${lang}"/></title>

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


<hr/>
<table style="margin-left: auto; margin-right: auto">
    <thead>
    <tr>
        <th><fmt:message key="name" bundle="${lang}"/></th>
        <th><fmt:message key="surname" bundle="${lang}"/></th>
        <th><fmt:message key="login" bundle="${lang}"/></th>
        <th><fmt:message key="email" bundle="${lang}"/></th>
        <th><fmt:message key="role" bundle="${lang}"/></th>
        <th><fmt:message key="banned" bundle="${lang}"/></th>

    </tr>
    </thead>

        <tbody>
        <c:forEach items="${requestScope.allUsers}" var="users">
        <tr>
            <td>${users.name}</td>
            <td>${users.surname}</td>
            <td>${users.login}</td>
            <td>${users.email}</td>
            <td><fmt:message key="${users.role}" bundle="${lang}"/></td>
            <td><fmt:message key="${users.ban}" bundle="${lang}"/></td>
            <form method="get"
                  action="${pageContext.request.contextPath}/ban">

                <td><input type="hidden" name="userId" id="userId" min=1 value="${users.id}"></td>
                <td><input type="hidden" name="banned" id="banned" value="${users.ban}"></td>
                <td style="text-align: right"><input class="button" type="submit" value="<fmt:message key="ban" bundle="${lang}"/>"/></td>

            </form>
            <form method="get"
                  action="${pageContext.request.contextPath}/promote">

                <td><input type="hidden" name="userIdPromote" id="userIdPromote" min=1 value="${users.id}"></td>
                <td style="text-align: right"><input class="button" type="submit" value="<fmt:message key="promote" bundle="${lang}"/>"/></td>

            </form>


        </tr>
        </c:forEach>
        </tbody>

</table>

<hr/>
</body>



</html>
