<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<div class="container">
    <br>
    <H2>Blockchain Archive</H2>
    <br>

    <c:if test="${not empty message}">
        <c:out value="${message}"/>
    </c:if>

    <div>

        <form method="POST" enctype="multipart/form-data" action="/">
            <div class="form-group">
                <label for="file">File to upload:</label>
                <br>
                <input type="file" class="form-control-file" id="file" required="required" name="file">
            </div>
            <br>
            <button type="submit" class="btn btn-primary">Upload</button>
            <br>
            <br>
        </form>

    </div>


    <div>
        <h3>Uploaded files</h3>
        <ul class="list-group">
            <c:forEach items="${files}" var="file">
                <li class="list-group-item">
                    <c:choose>
                        <c:when test="${empty file.blockNumber}">
                            <c:out value="${file.name} is waiting for being saved in Blockchain"/>
                        </c:when>
                        <c:otherwise>
                            <a href="/files/${file.id}">${file.name}</a>
                        </c:otherwise>
                    </c:choose>
                </li>
            </c:forEach>
        </ul>
        <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
        <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </div>
</div>
</body>
</html>
