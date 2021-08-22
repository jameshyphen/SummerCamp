<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><html>
<body>
<% 
String message = request.getParameter("message"); 
request.setAttribute("message", message);

String error = request.getParameter("error"); 
request.setAttribute("error", error);

%>
<c:if test="${not empty message}">
	<div class="msg">${message}</div>
</c:if>
<c:if test="${not empty error}">
	<div style="color:red;" class="error">${error}</div>
</c:if>
<% String postalCode = request.getParameter("postalCode");
request.setAttribute("previousPostalCode", postalCode);
if (postalCode == null || request.getAttribute("postalCodeError") != null) { %>
	<jsp:include page = "PostalCode.jsp">
  		<jsp:param name="postalCodeError" value="${postalCodeError}" />
  		<jsp:param name="previousPostalCode" value="${previousPostalCode}" />
	</jsp:include>
<% } else { %>

<jsp:include page="CampsCloseby.jsp" flush="true">
   <jsp:param name="dist" value="${campsCloseby}" />
   <jsp:param name="usr" value="${admin}" />
   <jsp:param name="units" value="SI" />
</jsp:include>



<% }%>
<form action='logout' method='post'>
	<input type="submit" value="Log out" style="border-radius:25px; width: 100px; height: 30px;">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

</body>
</html>
