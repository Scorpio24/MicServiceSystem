<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: yyu.abcft
  Date: 2018/1/27
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册成功！</title>
</head>
<body>
登录名：${requestScope.user.loginname}<br/>
密码：${requestScope.user.password}<br/>
用户名：${requestScope.user.username}<br/>
年龄：${requestScope.user.age}<br/>
邮箱：${requestScope.user.email}<br/>
生日：<fmt:formatDate value="${requestScope.user.birthday}" pattern="yyyy年MM月dd日"/><br/>
电话：${requestScope.user.phone}<br/>
</body>
</html>
