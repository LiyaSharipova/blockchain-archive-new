<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>

<c:if test="${empty message}">
    <c:out value="${message}"/>
</c:if>

<div>
    <form method="POST" enctype="multipart/form-data" action="/upload-file">
        <table>
            <tr>
                <td>File to upload:</td>
                <td><input type="file" name="file"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Upload"/></td>
            </tr>
        </table>
    </form>
</div>


<div>
    <ul>
        <c:forEach items="${files}" var="file">
            <li>
                <a href="/files/${file.id}">${file.name}</a>
            </li>
        </c:forEach>
    </ul>
</div>


</body>
</html>
