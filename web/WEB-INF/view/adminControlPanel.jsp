
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DealTime</title>
    </head>
    <body>
        <h1>Administration</h1>
        <table>
            <c:forEach items="${requestScope.customers}" var="customer">
                <tr>
                    <td>${customer.getCustEmail()}</td>
                    <td>${customer.getCustFirstName()}</td>
                    <td>${customer.getCustLastName()}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
