<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Home page</title>
</head>
<body>

<h1>List Expenses</h1>


<table style="width:80%" border="1">
  <tr>
     <th>№ Платежа</th>
     <th>Дата</th>
     <th>Получатель</th>
      <th>Сумма</th>
  </tr>
    <c:forEach items="${listE}" var="item">
      <tr>
      <td>${item.num}</td>
      <td>${item.paydate}</td>
      <td>${item.receiver}</td>
      <td>${item.value}</td>
    </tr>
    </c:forEach>
</table></br>
<form action="/ListExpenses_14_15_17" target="_self">
    <button>Start Page</button>
</form></br>
</body>
</html>