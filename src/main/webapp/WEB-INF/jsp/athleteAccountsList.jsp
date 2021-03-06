<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>


<html>
	<head>
		<title>Athlete Account List</title>
		<style type="text/css">
			.border {
				border-width: 1px;
				border-style: solid;
				border-collapse: collapse;
			}

			.td,.th {
				border: 1px solid;
				font-family: 'arial';
				font-size: 12px;
			}

			.a {
				font-family: 'arial';
				font-size: 12px;
			}
			.button {
			     background:none!important;
			     border:none; 
			     padding:0!important;
			     font: inherit;
			     color: blue;
			     border-bottom:1px solid #444; 
			     cursor: pointer;
			}
		</style>
	</head>
	<body>

	<security:authorize access="isAuthenticated()">
		authenticated as <security:authentication property="principal.username"/>
	</security:authorize>

		<c:out value="${successfulRegistrationFlashMessage}"/>
		<form id="logoutForm" method="POST" action="${pageContext.request.contextPath}/athleteaccountv2/logout" >
			<security:csrfInput/>
		</form>


		<form name="athleteAccountsList" method="POST" action="${pageContext.request.contextPath}/athleteaccountv2/athleteAccount?acAction=createACForm" >
			<security:csrfInput/>

			<table align="left" style="padding-left: 300px;" >
				
				<tr>
					<td style="font-family: 'arial'; font-size: 12px; font-weight: bold" align="right">
						<input type="button" class="button" value="logout" onclick="document.getElementById('logoutForm').submit();" />
						<p>Username:
							<security:authentication property="principal.username" />
						</p>
					</td>
				</tr>

				<tr>
					<td style="font-family: arial; font-size: 16px; font-weight: bold;">Athlete Account List
					</td>
				</tr>

				<tr>
					<td style="font-family: 'arial'; font-size: 12px; font-weight: normal;">&nbsp;<c:out value="${param.msg}"/></td>
				</tr>

				<tr>
					<td>

						<table class="border" cellpadding="10">
							<tr bgcolor="#99CCFF">
								<th class="th">ID</th>
								<security:authorize access="hasRole('ROLE_ADMIN')">
									<th class="th">Account Type</th>
								</security:authorize>
								<th class="th">Firstname</th>
								<th class="th">Lastname</th>
								<th class="th">Email</th>
								<th class="th">Primary Activity</th>
								<th class="th">Action</th>
							</tr>

							<c:forEach items="${acdList}" var="athleteAccount" >

								<tr>
									<td class="td"><c:out value="${athleteAccount.id}" /></td>
									<security:authorize access="hasRole('ROLE_ADMIN')">
										<td class="td"><c:out value="${athleteAccount.accountType}" /></td>
									</security:authorize>
									<td class="td"><c:out value="${athleteAccount.firstName}" /></td>
									<td class="td"><c:out value="${athleteAccount.lastName}" /></td>
									<td class="td"><c:out value="${athleteAccount.email}" /></td>
									<td class="td"><c:out value="${athleteAccount.primaryActivity}" /></td>

									<td class="td">
									<security:authorize access="hasRole('ROLE_ATHLETE')">

										<a href="${pageContext.request.contextPath}/athleteaccountv2/athleteAccount?acAction=view&athleteAccountId=${athleteAccount.id}" style="color: green">Edit</a>&nbsp;&nbsp;

										<a href="${pageContext.request.contextPath}/athleteaccountv2/athleteAccount?acAction=provideAccessToAdmin&athleteAccountId=${athleteAccount.id}" style="color: green">Provide Access To Admin</a>
									</security:authorize>

									<security:authorize access="hasRole('ROLE_ADMIN')" >
										<a href="${pageContext.request.contextPath}/athleteaccountv2/athleteAccount?acAction=close&athleteAccountId=${athleteAccount.id}" style="color: green">Close</a>&nbsp;&nbsp;
									</security:authorize>
									</td>

								</tr>

							</c:forEach>
						</table>

					</td>
				</tr>

				<tr align="center">
					<td>
						<input type="submit" value="Create new Athlete Account" />
					</td>
				</tr>

			</table>

		</form>

	</body>
</html>
