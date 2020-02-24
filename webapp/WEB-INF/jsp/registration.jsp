<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1 class="mt-2">Registration Page</h1>

    <c:if test="${not empty message}">
        <h1 class="text text-success">${message}</h1>
    </c:if>

    <%--@elvariable id="user" type="com.yabcompany.models.User"--%>
    <form:form method="post" modelAttribute="user">
        <form:label path="name">Name</form:label>
        <form:input path="name"/>
        <form:errors cssClass="text-danger" path="name"/>
        <br>

        <form:label path="email">Email</form:label>
        <form:input type="email" path="email"/>
        <form:errors cssClass="text-danger" path="email"/>
        <br>

        <form:label path="age">Age</form:label>
        <form:input type="number" path="age"/>
        <form:errors cssClass=" text-danger" path="age"/>
        <br>

        <form:label path="password">Password</form:label>
        <form:password path="password" />
        <form:errors cssClass="text-danger" path="password"/>
        <br>

        <input class="btn btn-info" type="submit" value="Submit">
    </form:form>

</div>
</body>
</html>