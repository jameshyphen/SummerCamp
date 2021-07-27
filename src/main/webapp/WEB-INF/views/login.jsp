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
			<div class="error">${error}</div>
			</c:if>
				<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>
			<form action='login' method='POST'>
				<table>
					<tr>
						<td>User:</td>
						<td><input type='text' name='username' value=''></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type='password' name='password' /></td>
					</tr>
					<tr>
						<td colspan='2'><input name="submit" type="submit"
						value="submit" /></td>
					</tr>
				</table>
				<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			</form>
		</div>
	</body>
</html>