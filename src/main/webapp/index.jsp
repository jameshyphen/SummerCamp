<html>
<body>
<%= request.getAttribute("test")%>
<% if (request.getParameter("postalCode") == null) { %>
	<jsp:include page = "PostalCodeInput.jsp" />
<% } %>
</body>
</html>
