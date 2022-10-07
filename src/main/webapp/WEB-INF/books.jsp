<%--
  Created by IntelliJ IDEA.
  User: nisrinekane
  Date: 10/6/22
  Time: 4:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/styles.css"/>
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<html>
<head>
    <title>Book Club</title>
</head>
<body>
    <h1>Welcome, <c:out value="${user.name}" />!</h1>
    <h4>Books from everyone's shelves:</h4>

    <a href="/logout" class="btn btn-danger books-btn">Logout</a>
    <a href="/books/new" class="btn btn-primary books-btn">Add a book to my shelf</a>

    <table class="table table-striped mt-5">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Title</th>
            <th scope="col">Author Name</th>
            <th scope="col">Posted By</th>
            <th scope="col">Options</th>
        </tr>
        </thead>
        <tbody>
        <!-- loop over all the ninjas to show the details as in the wireframe! -->
        <c:forEach items="${books}" var="book">
            <tr>
                <td><c:out value="${book.id}"/></td>
                <td><a href="/books/${book.id}"><c:out value="${book.title}" /></a></td>
                <td><c:out value="${book.authorName}" /></td>
                <td><c:out value="${book.user.name}" /></td>
                <td><a class="btn btn-warning" href="/books/${book.id}/edit" >edit</a></td>
                <td>
                    <form action="/books/${book.id}/delete" method="post">
                        <input type="hidden" name="_method" value="delete">
                        <input type="submit" value="Delete" class="btn btn-danger">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</body>
</html>
