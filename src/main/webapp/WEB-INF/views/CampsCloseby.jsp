<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<body>

<h2>Camps close to you</h2>
<table style="border: 1px solid black;border-collapse: collapse;">
<tr >
<th style="border: 1px solid black; height: 60px;">Manager</th>
<th style="border: 1px solid black;">Postal code</th>
<th style="border: 1px solid black;">Max children</th>
</tr>
<c:forEach var="camp" items="${campsCloseby}">
	
	<tr>
		    
	<th style="border: 1px solid black;">
<%
boolean admin = Boolean.TRUE == request.getAttribute("admin");

if (admin){ %>
		
				<a href="<c:url value="/summercamp/add/${camp.id}"/>">${camp.manager.name}</a>
			
<%} else { %>
		${camp.manager.name}
<%} %>
</th>
			<th style="border: 1px solid black;">${camp.postalCode}</th>
			<th style="border: 1px solid black;">${camp.maxChildren}</th>
		</tr>

</c:forEach>
	</table>
</body>
</html>
