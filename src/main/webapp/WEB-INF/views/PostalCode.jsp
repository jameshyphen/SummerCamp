<%@taglib prefix = "form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>

<h2>Postal code</h2>
<form:form action="summercamp" method="post">
	
	<table style="border: 1px solid black;">
		<tr>
			<th>Postal code:</th>
			<% String previousPostalCode = ((request.getAttribute("previousPostalCode") == null) ? "8000" : (String) request.getAttribute("previousPostalCode")); %>
			<th><input type = "text" name = "postalCode" value="<%=previousPostalCode %>" /></th>
			<% if (request.getParameter("postalCodeError")  != null) { %>
				<th style="color:red;">${postalCodeError}</th>
			<% }%>
		</tr>
	</table>
	<br><br>
	<input type="submit" value="Apply" style="border-radius:25px; width: 100px; height: 30px;">
</form:form>
</body>
</html>
