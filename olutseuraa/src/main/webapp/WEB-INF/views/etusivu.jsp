<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Olutseuraa - Hoptimus</title>
<link rel="stylesheet" type="text/css"
	href="resources/styles/foundation.css">
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/foundicons/3.0.0/foundation-icons.css"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Anonymous+Pro"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Press+Start+2P"
	rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="resources/styles/apply.css">
</head>

<body>
<c:redirect url="tapahtumat/kaikki"></c:redirect>
<%@ include file="firstpageheader.jsp"%>
 
	<ul id="menu" class="overlay-menu">
		<li><a href="tapahtumat/kaikki">Hae tapahtumia</a></li>
		<li id ="middleitem"><a href="tapahtumat/uusi">Luo tapahtuma</a></li>
		<!-- <li><a href="tapahtumat/1">näytä id:n "1" tapahtuma</a></li>-->
		<li><a href="#">About</a></li>
	</ul>
	





<%@ include file="tapah/footer.jsp"%>
