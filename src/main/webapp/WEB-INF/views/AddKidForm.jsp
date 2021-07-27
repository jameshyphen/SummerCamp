<%@taglib prefix = "form" uri="http://www.springframework.org/tags/form" %>

<html>
<body>

<h2>Manager ${camp.manager.name}</h2>
<form:form action="${id}" method="post">
	<% String previousName = ((request.getAttribute("previousName") == null) ? "" : (String) request.getAttribute("previousName")); %>
	<% String previousCodeOne = ((request.getAttribute("previousCodeOne") == null) ? "" : (String) request.getAttribute("previousCodeOne")); %>
	<% String previousCodeTwo = ((request.getAttribute("previousCodeTwo") == null) ? "" : (String) request.getAttribute("previousCodeTwo")); %>
	
	<table style="border: 1px solid black; border-collapse: collapse;">
		<tr >
			<th style="border: 1px solid black;">Name</th>
			<th style="border: 1px solid black;"><input type = "text" name = "name" value="<%=previousName %>" /></th>
			<th style="border: 1px solid black;"><div class="error">${errorName}</div></th>

		</tr>
		<tr>
			<th style="border: 1px solid black;">Code1:</th>
			<th style="border: 1px solid black;"><input type = "text" name = "code1" value="<%=previousCodeOne %>" /></th>
			<th style="border: 1px solid black;"><div class="error">${errorCodeOne}</div></th>
		</tr>
		<tr>
			<th style="border: 1px solid black;">Code2:</th>
			<th style="border: 1px solid black;"><input type = "text" name = "code2" value="<%=previousCodeTwo %>" /></th>
			<th style="border: 1px solid black;"><div class="error">${errorCodeTwo}</div></th>
		</tr>	
	</table>
	<br>
	<input type="submit" value="Sign up" style="border-radius:25px; width: 100px; height: 30px;">
</form:form>
</body>
</html>
