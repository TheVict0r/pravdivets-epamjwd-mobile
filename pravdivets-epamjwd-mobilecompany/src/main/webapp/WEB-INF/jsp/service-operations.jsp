<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale
	value="${sessionScope.session_locale != null ? sessionScope.session_locale : 'ru'}" />
<fmt:setBundle basename="language" />
<!DOCTYPE html>
<html lang="${sessionScope.session_locale}">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="pic/mobile.ico" />
<title>mobile</title>
</head>
<body class="d-flex flex-column min-vh-100 bg-light">

	<jsp:include page="components/header.jsp" />

	<div
		class="container col-sm-12 col-md-10 col-lg-8 col-xl-8 fw-light py-2 flex-grow-1">

		<div class="display-5 text-start py-4 flex-grow-1">
			<fmt:message key="service-operations.title" />
		</div>
		<div>
			<h4 class="mb-2">
				<fmt:message key="service-operations.edit-lead" />
			</h4>
		</div>

			<c:forEach var="service" items="${requestScope.all_services}">

		<div>${service.name} - ЭДИТ</div>
		 

		</c:forEach>

		<div>
			<h4 class="py-2">
				<fmt:message key="service-operations.add" />
			</h4>
		</div>
		<div class="d-grid col-1 py-1 mb-3 mx-start ">
			<a class="btn btn-outline-dark"
				href="${pageContext.request.contextPath}/controller?command=go_to_add_service_page">
				<fmt:message key="admin.add" />
			</a>
		</div>
		<div class="d-grid col-5 py-5 mx-start ">
			<a class="btn btn-primary"
				href="${pageContext.request.contextPath}/controller?command=go_to_admin_page"><fmt:message
					key="admin.to-admin" /></a>
		</div>
	</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>