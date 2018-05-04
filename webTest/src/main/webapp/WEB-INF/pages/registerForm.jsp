<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: yyu.abcft
  Date: 2018/1/26
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>register</title>
</head>
<body>
    <h3>注册页面</h3>
    <form:form modelAttribute="user" action="login" method="post">
        <table>
            <tr>
                <td><label>登录名：</label></td>
                <td><form:input path="loginname"/></td>
                <td><form:errors path="loginname" cssStyle="color:red"/></td>
            </tr>
            <tr>
                <td><label>密码：</label></td>
                <td><form:input path="password"/></td>
                <td><form:errors path="password" cssStyle="color:red"/></td>
            </tr>
            <tr>
                <td><label>用户名：</label></td>
                <td><form:input path="username"/></td>
                <td><form:errors path="username" cssStyle="color:red"/></td>
            </tr>
            <tr>
                <td><label>年龄：</label></td>
                <td><form:input path="age"/></td>
                <td><form:errors path="age" cssStyle="color:red"/></td>
            </tr>
            <tr>
                <td><label>邮箱：</label></td>
                <td><form:input path="email"/></td>
                <td><form:errors path="email" cssStyle="color:red"/></td>
            </tr>
            <tr>
                <td><label>生日：</label></td>
                <td><form:input path="birthday"/></td>
                <td><form:errors path="birthday" cssStyle="color:red"/></td>
            </tr>
            <tr>
                <td><label>电话：</label></td>
                <td><form:input path="phone"/></td>
                <td><form:errors path="phone" cssStyle="color:red"/></td>
            </tr>
            <tr>
                <td><input type="submit" id="submit" value="提交"></td>
            </tr>
        </table>
    </form:form>
</body>
</html>
