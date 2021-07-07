<html>
<body>

<% if (request.getParameter("postalCode") == null || request.getAttribute("postalCodeError") != null) { %>
	<jsp:include page = "PostalCode.jsp">
  		<jsp:param name="postalCodeError" value="${postalCodeError}" />
	</jsp:include>
<% } %>

</body>
</html>
