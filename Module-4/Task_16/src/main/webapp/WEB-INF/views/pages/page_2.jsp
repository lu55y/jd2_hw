<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring MVC</title>
</head>
<body>
<h2>Person Form</h2>

<form action="/Task_16/page_2/add" method="post">
<div class="mb-3">
    <label class="form-label">Person Name</label>
    <input type="text" class="form-control" id="f_name" aria-describedby="f_nameHelp" name="firstName">
    <div id="f_nameHelp" class="form-text">Enter your name.</div>
</div>
<div class="mb-3">
    <label class="form-label">Person Name</label>
    <input type="text" class="form-control" id="f_secondName" aria-describedby="secondNameHelp" name="lastName">
    <div id="secondNameHelp" class="form-text">Enter your second name.</div>
</div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>

</body>
</html>