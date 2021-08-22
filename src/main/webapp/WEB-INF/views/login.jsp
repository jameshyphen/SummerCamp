<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<body onload='document.loginForm.username.focus();'>
		<h1>Spring Security Custom Login Form (Annotation)</h1>
		<div id="login-box">
			<h3>Login with Username and Password</h3>
			<c:if test="${not empty error}">
			<div style="color:red;" class="error">${error}</div>
			</c:if>
				<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>
			<form action='login' method='POST'>
				<table style="border: 1px solid black; border-collapse: collapse">
					<tr style="border: 1px solid black">
						<td style="border: 1px solid black">User:</td>
						<td style="border: 1px solid black"><input type='text' name='username' value=''></td>
					</tr>
					<tr>
						<td style="border: 1px solid black">Password:</td>
						<td style="border: 1px solid black"><input type='password' name='password' /></td>
					</tr>
				</table>
				<br>				
				<br>
				<input style="border-radius:25px; width: 100px; height: 30px;" name="submit" type="submit"
						value="submit" />
				<input  type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			</form>
		</div>
	</body>
</html>