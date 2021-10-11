<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'ru'}" />
<fmt:setBundle basename="language" />

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
	<div class="row align-content-center mx-auto flex-grow-1">

		<form method="POST" action="controller?command=calculator">

			<h4><fmt:message key="calculator.calls.within.network" /></h4>
			<div >
				<input class="form-check-input" type="radio" name="within_network" id="radios1" required="required" value="10"> 
				<label class="form-check-label" for="radios1"> 0-20 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="within_network" id="radios2" required="required" value="30"> 
				<label class="form-check-label" for="radios2"> 21-40 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="within_network" id="radios3" required="required" value="50"> 
				<label class="form-check-label" for="radios3"> 41-60 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="within_network" id="radios4" required="required" value="75"> 
				<label class="form-check-label" for="radios4"> 61-90 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="within_network" id="radios5" required="required" value="105"> 
				<label class="form-check-label" for="radios5"> 91-120 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="within_network" id="radios6" required="required" value="150"> 
				<label class="form-check-label" for="radios6"><fmt:message key="calculator.more.than" /> 120 <fmt:message key="calculator.min" /> </label>
			</div>
			
			<h4><fmt:message key="calculator.calls.other.networks" /></h4>
			<div >
				<input class="form-check-input" type="radio" name="other_networks" id="radios1" required="required" value="10"> 
				<label class="form-check-label" for="radios1"> 0-20 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="other_networks" id="radios2" required="required" value="30"> 
				<label class="form-check-label" for="radios2"> 21-40 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="other_networks" id="radios3" required="required" value="50"> 
				<label class="form-check-label" for="radios3"> 41-60 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="other_networks" id="radios4" required="required" value="75"> 
				<label class="form-check-label" for="radios4"> 61-90 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="other_networks" id="radios5" required="required" value="105"> 
				<label class="form-check-label" for="radios5"> 91-120 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="other_networks" id="radios6" required="required" value="150"> 
				<label class="form-check-label" for="radios6"><fmt:message key="calculator.more.than" /> 120 <fmt:message key="calculator.min" /> </label>
			</div>
			
			<h4><fmt:message key="calculator.calls.abroad" /></h4>
			<div >
				<input class="form-check-input" type="radio" name="abroad" id="radios1" required="required" value="10"> 
				<label class="form-check-label" for="radios1"> 0-20 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="abroad" id="radios2" required="required" value="30"> 
				<label class="form-check-label" for="radios2"> 21-40 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="abroad" id="radios3" required="required" value="50"> 
				<label class="form-check-label" for="radios3"> 41-60 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="abroad" id="radios4" required="required" value="75"> 
				<label class="form-check-label" for="radios4"> 61-90 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="abroad" id="radios5" required="required" value="105"> 
				<label class="form-check-label" for="radios5"> 91-120 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="abroad" id="radios6" required="required" value="150"> 
				<label class="form-check-label" for="radios6"><fmt:message key="calculator.more.than" /> 120 <fmt:message key="calculator.min" /> </label>
			</div>
			
			<h4><fmt:message key="calculator.calls.videocall" /></h4>
			<div >
				<input class="form-check-input" type="radio" name="videocall" id="radios1" required="required" value="10"> 
				<label class="form-check-label" for="radios1"> 0-20 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="videocall" id="radios2" required="required" value="30"> 
				<label class="form-check-label" for="radios2"> 21-40 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="videocall" id="radios3" required="required" value="50"> 
				<label class="form-check-label" for="radios3"> 41-60 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="videocall" id="radios4" required="required" value="75"> 
				<label class="form-check-label" for="radios4"> 61-90 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="videocall" id="radios5" required="required" value="105"> 
				<label class="form-check-label" for="radios5"> 91-120 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="videocall" id="radios6" required="required" value="150"> 
				<label class="form-check-label" for="radios6"><fmt:message key="calculator.more.than" /> 120 <fmt:message key="calculator.min" /> </label>
			</div>
			
			<h4><fmt:message key="calculator.sms" /></h4>
			<div >
				<input class="form-check-input" type="radio" name="sms" id="radios1" required="required" value="10"> 
				<label class="form-check-label" for="radios1"> 0-20 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="sms" id="radios2" required="required" value="30"> 
				<label class="form-check-label" for="radios2"> 21-40 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="sms" id="radios3" required="required" value="50"> 
				<label class="form-check-label" for="radios3"> 41-60 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="sms" id="radios4" required="required" value="75"> 
				<label class="form-check-label" for="radios4"> 61-90 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="sms" id="radios5" required="required" value="105"> 
				<label class="form-check-label" for="radios5"> 91-120 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="sms" id="radios6" required="required" value="150"> 
				<label class="form-check-label" for="radios6"><fmt:message key="calculator.more.than" /> 120 <fmt:message key="calculator.min" /> </label>
			</div>
			
			<h4><fmt:message key="calculator.mms" /></h4>
			<div >
				<input class="form-check-input" type="radio" name="mms" id="radios1" required="required" value="10"> 
				<label class="form-check-label" for="radios1"> 0-20 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="mms" id="radios2" required="required" value="30"> 
				<label class="form-check-label" for="radios2"> 21-40 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="mms" id="radios3" required="required" value="50"> 
				<label class="form-check-label" for="radios3"> 41-60 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="mms" id="radios4" required="required" value="75"> 
				<label class="form-check-label" for="radios4"> 61-90 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="mms" id="radios5" required="required" value="105"> 
				<label class="form-check-label" for="radios5"> 91-120 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="mms" id="radios6" required="required" value="150"> 
				<label class="form-check-label" for="radios6"><fmt:message key="calculator.more.than" /> 120 <fmt:message key="calculator.min" /> </label>
			</div>
			
			<h4><fmt:message key="calculator.internet" /></h4>
			<div >
				<input class="form-check-input" type="radio" name="internet" id="radios1" required="required" value="10"> 
				<label class="form-check-label" for="radios1"> 0-20 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="internet" id="radios2" required="required" value="30"> 
				<label class="form-check-label" for="radios2"> 21-40 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="internet" id="radios3" required="required" value="50"> 
				<label class="form-check-label" for="radios3"> 41-60 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="internet" id="radios4" required="required" value="75"> 
				<label class="form-check-label" for="radios4"> 61-90 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="internet" id="radios5" required="required" value="105"> 
				<label class="form-check-label" for="radios5"> 91-120 <fmt:message key="calculator.min" /></label>
				<input class="form-check-input" type="radio" name="internet" id="radios6" required="required" value="150"> 
				<label class="form-check-label" for="radios6"><fmt:message key="calculator.more.than" /> 120 <fmt:message key="calculator.min" /> </label>
			</div>
			
			<button type="submit" class="btn btn-outline-primary btn-sm">Предложить тариф</button>
		</form>




	</div>

	<jsp:include page="components/footer.jsp" />


</body>
</html>