<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Prescriptions</title>
</head>
<body>
<table>
    <c:forEach var="prescriptions" items="${simpleinfo}" >
        <tr> <td>${movie}</td> </tr>
    </c:forEach>
</table>
</body>
</html>