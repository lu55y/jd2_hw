<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Task 15</title>
</head>
<body>

<h2>List Expenses Task 15</h2>
<%@page import="it.academy.data.ListExpenses" %>
<%@page import="java.util.*" %>
<%List<ListExpenses>listE= (List<ListExpenses>) request.getAttribute("listE");
for (ListExpenses lExpenses: listE) {
    out.println("<p>num= " + lExpenses.getNum() + "   paydate= " + lExpenses.getPaydate() + "   receiver= " + lExpenses.getReceiver() + "   value= " + lExpenses.getValue() + "</p>");
            }%>

<form action="/ListExpenses_14_15_17" target="_self">
<button>Start Page</button>
</form></br>
</form>
</body>
</html>