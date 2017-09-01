<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery.min.js"></script>
<body>

      ${userSession.username} ，你好！ 你已经成功登陆!

      <br>
      sessionID:<%=session.getId()%>
      <br>
      SessionIP:<%=request.getServerName()%>
      <br>
      SessionPort:<%=request.getServerPort()%>

</body>
</html>