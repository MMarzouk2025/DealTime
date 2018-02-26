<%@page import="java.util.ArrayList"%>
<%@page import="com.deal.base.model.Customer"%>
<jsp:useBean id="customer" class="com.deal.base.model.Customer"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DealTime</title>
    </head>
    <body>
        <h1>Administration</h1>
        <table>
            <tr>
                <td>gfsdgdgdfg</td>
                <td>dfgdfgfdg</td>
                <td>dfgdfgd</td>
            </tr>
            <% out.print(((ArrayList<Customer>)request.getAttribute("customers")).size()); %>
            <c:forEach items="${customers}" var="customer">
                <tr>
                    <td>${cust.getCustEmail()}</td>
                    <td>${customer.getCustFirstName()}</td>
                    <td>${customer.getCustLastName()}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
