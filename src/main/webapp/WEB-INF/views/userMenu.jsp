<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.training.siarhei_baradzionak.domain.constants.ServletConstants" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<c:url value='/pages/style/style.css'/>">
<script language="JavaScript" type="text/javascript" src="/pages/js/script.js"></script>
<title>IssueTracker</title>
</head>
<body>

	<div class="error-message">
		<c:if test="${not empty errorMessage}">
			<c:out value="${errorMessage}"/><hr>
		</c:if>
	</div>
	<div class="main-page">
		<div class="main-header">
			<jsp:include page="/WEB-INF/views/includies/header.jsp" />
		</div>

		<div class="main">

			<h1>User menu</h1>
			<c:choose>
				<c:when test="${user.role.roleName eq 'ADMINISTRATOR' || user.role.roleName eq 'USER'}">
					<div class="user-menu">
						<div class="user-menu-link">
							<p><a href="<c:url value='/pages/editUser.jsp'/>">Edit user</a></p>
							<p><a href="<c:url value='/pages/editPassword.jsp'/>">Change password</a></p>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<p><font color=&quot#AABBCC&quot>Your are in IssueTracker User menu page now<br>
					Please login or go to the <a href="<c:url value='/index.jsp'/>">main page</a>.</font></p>
				</c:otherwise>
			</c:choose>
		</div>
		
		<div class="main-aside">
			<jsp:include page=/WEB-INF/views/includies/aside.jsp" />
		</div>
		<div class="main-footer">
			<jsp:include page="/WEB-INF/views/includies/footer.jsp" />
		</div>
	</div>

</body>
</html>