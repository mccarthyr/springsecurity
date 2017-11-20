<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
	<head>
		<title>Successful New Account Registration</title>
	</head>
	<!-- ****** NOTE NOTE NOTE: JUST SHOWING PASSWORD HERE FOR WORK FLOW TESTING TO CAN
															SEE ENCODER IN ACTION WHEN PUT THAT IN PLACE, WILL BE REMOVED FOR FINAL DEMO ****** -->

	<body>
		<h2>Successful New Account Registration</h2>
		<p>Your new account has been successfully registered with the following details:</p>
		<table>
			<tr>
				<td>Username: </td>
				<td><c:out value="${user.username}" /></td>
			</tr>
			<tr>
				<td>Password: </td>
				<td><c:out value="${user.password}" /></td>
			</tr>
			<tr>
				<td>Firstname: </td>
				<td><c:out value="${user.firstName}" /></td>
			</tr>
			<tr>
				<td>Lastname: </td>
				<td><c:out value="${user.lastName}" /></td>
			</tr>
			<tr>
				<td>Email: </td>
				<td><c:out value="${user.email}" /></td>
			</tr>
		</table>
	</body>

</html>


<!--
<c:forEach items="${userForms}" var="userForm">     
   <c:out value="${userForm.name}"/>
   <c:out value="${userForm.address}"/>
</c:forEach>
-->