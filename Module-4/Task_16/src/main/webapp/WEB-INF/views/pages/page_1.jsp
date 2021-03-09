<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Person</title>
</head>
<body>
<h1>Persons table</h1>
<!-- Promo product list -->
    <table class="table">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col"></th>
          <th scope="col">Name</th>
          <th scope="col">Second Name</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${employeeList}" var="employeeList">
        <tr>
          <th scope="row">#</th>
          <td>${employeeList.id}</td>
          <td>${employeeList.firstName}</td>
          <td>${employeeList.lastName}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
</body>
</html>