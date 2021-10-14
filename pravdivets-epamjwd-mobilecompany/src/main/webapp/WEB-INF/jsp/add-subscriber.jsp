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
<body>

	<jsp:include page="components/header.jsp" />


	<div class="container">
		<div class="row justify-content-center">
			<h3>Новый абонент</h3>
			<form method="post" action=/controller?command=add_abonent>
				<table>
					<tr>
						<td>Фамилия:</td>
						<td><input type="text" name="last_name" required></td>
					</tr>
					<tr>
						<td>Имя:</td>
						<td><input type="text" name="first_name" required></td>
					</tr>
					<tr>
						<td>Отчество:</td>
						<td><input type="text" name="middle_name" required></td>
					</tr>
					<tr>
						<td>Серия и номер паспорта:</td>
						<td><input type="text" name="passport_number" required></td>
					</tr>
					<tr>
					<tr>
						<td>Домашний адрес:</td>
						<td><input type="text" name="home_address" required></td>
					</tr>
					<tr>
					<tr>
						<td>e-mail:</td>
						<td><input type="text" name="email" required></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Добавить"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	</br>
	</br>
	<jsp:include page="components/footer.jsp"/>
</body>
</html>