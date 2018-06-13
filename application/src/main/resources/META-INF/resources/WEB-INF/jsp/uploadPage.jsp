<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>

<br>
<H2>Blockchain Atchive</H2>
<br>

<c:if test="${not empty message}">
    <c:out value="${message}"/>
</c:if>

<div>
    <form method="POST" enctype="multipart/form-data" action="/">
        <table>
            <tr>
                <td>File to upload:</td>
                <td><input type="file" name="file" required="required"/></td>
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
                <c:choose>
                    <c:when test="${empty file.number}">
                        <c:out value="${file.name} is waiting for being saved in Blockchain"/>
                    </c:when>
                    <c:otherwise>
                        <a href="/files/${file.id}">${file.name}</a>
                    </c:otherwise>
                </c:choose>
            </li>
        </c:forEach>
    </ul>
</div>


</body>
</html>
