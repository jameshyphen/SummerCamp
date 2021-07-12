<html>
<body>

<% String postalCode = request.getParameter("postalCode");
request.setAttribute("previousPostalCode", postalCode);
if (postalCode == null || request.getAttribute("postalCodeError") != null) { %>
	<jsp:include page = "PostalCode.jsp">
  		<jsp:param name="postalCodeError" value="${postalCodeError}" />
  		<jsp:param name="previousPostalCode" value="${previousPostalCode}" />
	</jsp:include>
<% } %>

</body>
</html>
