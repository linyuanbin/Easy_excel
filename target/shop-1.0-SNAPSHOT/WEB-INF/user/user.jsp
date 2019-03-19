<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yuanbin.lin
  Date: 2018/11/13
  Time: 下午7:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>users</title>
</head>
<body>
<c:forEach var="user"   items="${users}">
    ${user.username}
</c:forEach>
</body>
</html>
