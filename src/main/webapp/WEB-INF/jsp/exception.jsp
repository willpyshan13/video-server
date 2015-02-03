<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <title>异常处理页面</title>
</head>
<body>
<div class="row-fluid sortable">
<% Exception ex = (Exception) request.getAttribute("exception");%>
<h3>Exception:<%=ex.getMessage()%></h3>
</div>
</body>
</html>