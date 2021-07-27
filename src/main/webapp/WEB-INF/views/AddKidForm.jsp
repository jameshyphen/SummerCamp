<%@taglib prefix = "form" uri="http://www.springframework.org/tags/form" %>

<html>
<body>

<h2>Manager ${manager.name}</h2>
<form:form action="add/${id}" method="post">
	
	<table style="border: 1px solid black; border-collapse: collapse;">
		<tr >
			<th style="border: 1px solid black;">Name</th>
			<th style="border: 1px solid black;">Code1:</th>
			<th style="border: 1px solid black;">Code2:</th>
		</tr>
		<tr>
			<% String previousName = ((request.getAttribute("previousName") == null) ? "" : (String) request.getAttribute("previousName")); %>
			<% String previousCodeOne = ((request.getAttribute("previousCodeOne") == null) ? "" : (String) request.getAttribute("previousCodeOne")); %>
			<% String previousCodeTwo = ((request.getAttribute("previousCodeTwo") == null) ? "" : (String) request.getAttribute("previousCodeTwo")); %>
		
			<th style="border: 1px solid black;"><input type = "text" name = "postalCode" value="<%=previousName %>" /></th>
			<th style="border: 1px solid black;"><input type = "text" name = "postalCode" value="<%=previousCodeOne %>" /></th>
			<th style="border: 1px solid black;"><input type = "text" name = "postalCode" value="<%=previousCodeTwo %>" /></th>
		</tr>
		<tr>
			<th style="border: 1px solid black;"><div class="error">${errorName}</div></th>
			<th style="border: 1px solid black;"><div class="error">${errorCodeOne}</div></th>
			<th style="border: 1px solid black;"><div class="error">${errorCodeTwo}</div></th>
		</tr>
	</table>
</form:form>
</body>
</html>
