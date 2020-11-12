<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/12/2020
  Time: 9:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>
<body>
<form method="post" action="/products?action=edit">
    <fieldset>
        <legend>Edit</legend>
        <table>
            <tr>
                <td>id</td>
                <td><input type="text" name="id" value="${requestScope["products"].getId()}"></td>
            </tr>
            <tr>
                <td>Name</td>
                <td><input type="text" name="name" value="${requestScope["products"].getName()}"></td>
            </tr>
            <tr>
                <td>Price</td>
                <td><input type="text" name="price" value="${requestScope["products"].getPrice()}"></td>
            </tr>
            <tr>
                <td>Quantity</td>
                <td><input type="text" name="quantity" value="${requestScope["products"].getQuantity()}"></td>
            </tr>
            <tr>
                <td>Color</td>
                <td><input type="text" name="color" value="${requestScope["products"].getColor()}"></td>
            </tr>
            <tr>
                <td>description</td>
                <td><input type="text" name="description" value="${requestScope["products"].getDescription()}"></td>
            </tr>
            <tr>
                <td>Category</td>
<%--                <td><input type="text" name="category" value="${requestScope["products"].getCategory().}"></td>--%>
                <td>
                <select name="category">
                    <c:forEach items='${requestScope["categorys"]}' var="category">
                        <%--                                <option value="${category.getId()}"><c:out value="${province.getName()}"></c:out></option>--%>
                        <option value="${category.getId()}">${category.getName()}</option>
                    </c:forEach>
                </select>
                </td>
            </tr>
            <tr>
                <td><a href="/products">back to home</a></td>
                <td><input type="submit" value="Update"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
