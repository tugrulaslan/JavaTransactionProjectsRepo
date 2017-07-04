<%--
  Created by IntelliJ IDEA.
  User: Tugrul
  Date: 7/4/2017
  Time: 7:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Employee List</title>
</head>
<body>
<sf:form method="POST" modelAttribute="newEmployee">
    <table>
        <tr>
            <td><sf:label path="name">Name:</sf:label></td>
            <td><sf:input path="name"/></td>
        </tr>
        <tr>
            <td><sf:label path="department">Department:</sf:label></td>
            <td><sf:input path="department"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Add"/></td>
        </tr>
    </table>
</sf:form>
<h2>Employee List</h2>
<table>
    <tr>
        <td>Name</td>
        <td>Department</td>
    </tr>
    <c:forEach var="employee" items="${list}">
        <tr>
            <td><c:out value="${employee.name}"/></td>
            <td><c:out value="${employee.department}"/></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
