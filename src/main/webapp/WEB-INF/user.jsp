<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body>
<h2>Hello World!</h2>

<c:forEach items="${list}" var="user">

    id ： ${user.id}

    name： ${user.username}

    密码 ： ${user.password}
</c:forEach>
</body>
</html>
