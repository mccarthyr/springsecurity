<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<html>
	<head>
		<title>Login</title>
		<style type="text/css">
		.td {
			font-family: 'arial';
			font-size: 14px;
			vertical-align: top;
		}
		</style>
	</head>
	<body>

		<c:out value="${registrationErrorFlashMessage}"/>

		<form:form name="register" method="POST" action="${pageContext.request.contextPath}/athleteaccountv2/register/user?acAction=register" modelAttribute="user">
			<security:csrfInput />
			<table align="left" style="padding-left: 300px;">
				
				<tr align="left">
					<td>
						<table class="border" cellpadding="10">
							<tr>
								<td style="font-family: 'arial'; font-size: 12px; font-weight: normal; color: red;"><c:out value="${param.exceptionMsg}"/></b></td>
							</tr>

							<tr>
								<td class="td"><b>Password:</b></td>
								<td class="td"><form:input type="password" path="password" value="" /></td>
							</tr>

							<tr>
								<td class="td"><b>Confirm Password:</b></td>
								<td class="td"><form:input type="password" path="confirmPassword" value="" /></td> <!-- NOT USING THE FORM OR PATH SYNTAX ON THIS ONE AS NOT MAPPING IT TO THE BACKING OBJECT... -->
									
									<font style="color: #C11B17;">
										<form:errors path="confirmPassword" /> 
										<!-- Automatically set by Binding Validation data in the RegistrationController registerNewUserAccount() method -->
									</font>
							
							</tr>
							
							<tr>
								<td class="td"><b>Firstname:</b></td>
								<td class="td"><form:input type="text" path="firstName" value="" />
									<font style="color: #C11B17;">
										<form:errors path="firstName" />
									</font>
								</td>
							</tr>
							<tr>
								<td class="td"><b>Lastname:</b></td>
								<td class="td"><form:input type="text" path="lastName" value="" />
									<font style="color: #C11B17;"><form:errors path="lastName" /></font>
								</td>
							</tr>
							<tr>
								<td class="td"><b>Email:</b></td>
								<td class="td"><form:input type="text" path="email" value="" />
									<font style="color: #C11B17;">
										<!-- <c:out value="${requestScope['error.emailAlreadyExists']}" /> -->
										<form:errors path="email" /> 
										<!-- Automatically set by Binding Validation data in the RegistrationController registerNewUserAccount() method -->
									</font>
								</td>
							</tr>
							<tr>
								<td class="td"><b>Account Type:</b></td>
								<td class="td"><form:input type="text" path="accountType" value="" />
									<font style="color: #C11B17;">
										<form:errors path="accountType" />
									</font>
								</td>
							</tr>
							<tr>
								<td class="td"><b>Primary Activity:</b></td>
								<td class="td"><form:input type="text" path="primaryActivity" value="" />
									<font style="color: #C11B17;">
										<form:errors path="primaryActivity" />
									</font>
								</td>
							</tr>

						</table>
					</td>
				</tr>
				
				<tr align="left">
					<td>
						<table style="padding-left: 100px;">
							<tr align="center">
								<td class="td"><input type="submit" value="Register" /></td>
							</tr>
						</table>
					</td>
				</tr>

			</table>
		</form:form>
	</body>
</html>


