<html>
<body>

<h2>Postal code</h2>
${test}
<form action="index" method="post">
	
	<table style="border: 1px solid black;">
		<tr>
			<th>Postal code:</th>
			<th><input type = "text" name = "postalCode" value="8000"></th>
			<% if (request.getParameter("postalCodeError")  != null) { %>
				<th style="color:red;">${postalCodeError}</th>
			<% }%>
		</tr>
	</table>
	<br><br>
	<input type="submit" value="Apply" style="border-radius:25px; width: 100px; height: 30px;">
</form>
</body>
</html>
