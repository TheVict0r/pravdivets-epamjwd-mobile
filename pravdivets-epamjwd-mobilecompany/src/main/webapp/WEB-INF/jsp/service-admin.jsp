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
		class="container row align-content-center col-sm-7 mx-auto flex-grow-1 gap-4">
		<span class="display-5 text-left ">${sessionScope.service.name}</span> <span class="display-7">${sessionScope.service.description}</span> <span class="fs-5"><b><fmt:message key="service.price" />
				<fmt:formatNumber type="number" minFractionDigits="2"
					value="${sessionScope.service.tariff/100}" /> <fmt:message
					key="service.rub" /></b></span>
		<div class="d-grid col-6 py-2 mx-start ">
			<a class="btn btn-outline-primary"
				href="${pageContext.request.contextPath}/controller?command=go_to_service_operations_page">
				<fmt:message key="admin.service-operations" />
			</a>
		</div>
		<div class="d-grid col-6 py-2 mx-start ">
			<a class="btn btn-primary"
				href="${pageContext.request.contextPath}/controller?command=go_to_admin_page"><fmt:message
					key="admin.to-admin" /></a>
		</div>
	</div>
	<jsp:include page="components/footer.jsp" />
</body>
</html>