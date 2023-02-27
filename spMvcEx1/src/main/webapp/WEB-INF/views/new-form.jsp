<%-- WEB-INF 밑의 jsp 파일들은 직적 호출될 수 없고, Servlet Controller를
통해서만 호출될 수 있다.--> WAS 서버의 룰이므로 그대로 적용되어야 한다. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save] -->
<form action="save" method="post">
    username: <input type="text" name="username"/>
    age: <input type="text" name="age"/>
    <button type="submit">전송</button>
</form>

</body>
</html>
