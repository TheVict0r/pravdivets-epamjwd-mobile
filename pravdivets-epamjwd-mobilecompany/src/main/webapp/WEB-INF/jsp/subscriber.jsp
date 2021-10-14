<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="pic/mobile.ico" />
<title>mobile</title>
</head>
<body class="d-flex flex-column min-vh-100 bg-light">

	<jsp:include page="components/header.jsp" />
	
	<div class="container text-center mx-auto flex-grow-1">
		<span class="fs-6 fw-bold text-secondary ">Информация об абоненте:</span>
	</div>



		<div class="container col-sm-12 col-md-10 col-lg-9 col-xl-7 fs-6 fw-light flex-grow-1">
			<table class="table ">
				<tr>
					<td>Номер телефона:</td>
					<td>${requestScope.phone_number_format}</td>
					<td><a class="login btn btn-outline-primary btn-sm" href="#">Изменить</a></td>
				</tr>
				<tr>
					<td>ФИО абонента:</td>
					<td>${requestScope.subscriber.firstName} ${requestScope.subscriber.middleName} ${requestScope.subscriber.lastName}</td>
					<td><a class="login btn btn-outline-primary btn-sm" href="#">Изменить</a></td>
				</tr>
				<tr>
					<td>Номер паспорта:</td>
					<td>${requestScope.subscriber.passportNumber}</td>
					<td><a class="login btn btn-outline-primary btn-sm" href="#">Изменить</a></td>
				</tr>
				<tr>
					<td>Тарифный план: </td>
					<td>${requestScope.subscriber.tariffPlan}</td>
					<td><a class="login btn btn-outline-primary btn-sm" href="#">Изменить</a></td>
				</tr>
				<tr>
					<td>Домашний адрес:</td>
					<td>${requestScope.subscriber.homeAddress}</td>
					<td><a class="login btn btn-outline-primary btn-sm" href="#">Изменить</a></td>
				</tr>
				<tr>
					<td>e-mail:</td>
					<td>${requestScope.subscriber.email}</td>
					<td><a class="login btn btn-outline-primary btn-sm" href="#">Изменить</a></td>
				</tr>
				<tr>
					<td>Дата заключения договора:</td>
					<td>${requestScope.subscriber.contractDate}</td>
				</tr>
				<tr>
					<td>Лицевой счет №:</td>
					<td>${requestScope.subscriber.id}</td>
				</tr>
				<tr>
					<td>Текущий баланс:</td>
					<td>${requestScope.subscriber.checkingAccountAmount/100} руб.</td>
				</tr>
				<tr>
					<td>Статус:</td>
					<td>${requestScope.subscriber.status.statusName}</td>
				</tr>
				<tr>
					<td>Дата выставления статуса:</td>
					<td>${requestScope.subscriber.statusDate}</td>
				</tr>
			</table>
		</div>
		


	<jsp:include page="components/footer.jsp" />
</body>
</html>