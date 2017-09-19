<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
	

<html>
<head>
<title>Create a new Athlete Account</title>
<style type="text/css">
.td {
	font-family: 'arial';
	font-size: 12px;
	vertical-align: top;
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
	<form id="logoutForm" method="POST" action="${pageContext.request.contextPath}/logout">
		<security:csrfInput/>
	</form>
	<form name="createAthleteAccountForm" method="POST" action="${pageContext.request.contextPath}/athleteaccountv2/athleteAccount?acAction=create">

		<security:csrfInput/>

		<table align="left" style="padding-left: 300px;">

			<tr>
				<td
					style="font-family: 'arial'; font-size: 12px; font-weight: bold" align="right">
					<input type="button" class="button" value="Logout" onclick="document.getElementById('logoutForm').submit();"/>
				</td>
			</tr>
			<tr>
				<td
					style="font-family: 'arial'; font-size: 16px; font-weight: bold;" align="left">Open Athlete Account</td>
			</tr>
			<tr align="left">
				<td>

					<table class="border" cellpadding="10">
						<tr>
							<td class="td"><b>Account Type (Freemium/Premium)</b></td>
							<td class="td"><input type="text" name="accountType" value="${requestScope.athleteAccountDetails.accountType}" /><font
								style="color: #C11B17;"><c:out value="${requestScope['error.accountType']}" /></font></td>
						</tr>

						<tr>
							<td class="td"><b>Name</b></td>
							<td class="td"><input type="text" name="name" value="${requestScope.athleteAccountDetails.name}" /><font
								style="color: #C11B17;"><c:out value="${requestScope['error.name']}" /></font></td>
						</tr>

						<tr>
							<td class="td"><b>Email</b></td>
							<td class="td"><input type="text" name="email" value="${requestScope.athleteAccountDetails.email}" /><font
								style="color: #C11B17;"><c:out value="${requestScope['error.email']}" /></font></td>
						</tr>

						<tr>
							<td class="td"><b>Primary Activity</b></td>
							<td class="td"><input type="text" name="primaryActivity" value="${requestScope.athleteAccountDetails.primaryActivity}" /><font
								style="color: #C11B17;"><c:out value="${requestScope['error.primaryActivity']}" /></font></td>
						</tr>

					</table>

				</td>
			</tr>
			<tr align="left">
				<td>

					<table style="padding-left: 100px;">
						<tr align="center">
							<td class="td"><input type="submit" value="Save" /> &nbsp;&nbsp;
								<a href="${pageContext.request.contextPath}/athleteaccountv2/athleteAccount/list" style="color: green"><b>Go Back</b></a>
							</td>
						</tr>
					</table>

				</td>
			</tr>

		</table>

	</form>
</body>
</html>
