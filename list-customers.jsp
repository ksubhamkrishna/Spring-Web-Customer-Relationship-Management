<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>


<title>List Customers</title>

<!-- reference our style sheet -->

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">

			<!-- put new button: Add Customer -->

			<input type="button" value="Add Customer"
				onclick="window.location.href = 'showFormForAdd'; return false;"
				class="add-button" />

			<!-- shwoFormForAdd is the call for our Spring Controller mapping -->
			<!-- add our html table -->

			<table>
				<tr>

					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>

				</tr>

				<!-- loop over and print our customers -->

				<c:forEach var="tempCustomer" items="${customers}">

					<!-- Construct an "update" link with the customer id -->
					<!--To Create the Link we will make use of the JSTL custom tag for c:url  -->
					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>

					<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>

					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						<td>
							
							<!-- display the update and delete link --> 
							<!-- Adding javascript code to our JSP href section to prompt the user before deleting as onclick confirm displays a confirmation popup dialog-->
							<a href="${updateLink}">Update</a> 
							| 
							<a href="${deleteLink}"
							onclick="if(!(confirm( 'Are you sure you want to delete this customer?'))) return false">Delete</a>
						</td>

					</tr>
				</c:forEach>


			</table>

		</div>

	</div>




</body>

</html>