<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
    
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Register</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
  <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@1,900&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Anton&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Work+Sans:wght@300&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Lexend+Deca:wght@500&display=swap" rel="stylesheet">


</head>
<body id="myPage">
<nav class="navbar navbar-expand-lg navbar-dark primary-color">

  <a class="navbar-brand text-warning" href="/feed"><h1>SocialBuzz</h1></a>

  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#basicExampleNav"
    aria-controls="basicExampleNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="basicExampleNav">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="/feed">Feed
          <span class="sr-only">(current)</span>
        </a>
      </li>
    </ul>
    <ul class="navbar-nav mr-5">
      <li class="nav-item">
        <a class="nav-link" href="/login">Login</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/register">Sign Up</a>
      </li>
    </ul>
    <form class="form-inline">
      <div class="md-form my-0">
        <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
      </div>
    </form>
  </div>
</nav>

<div class="container d-flex justify-content-between">
    <div class="container text-light align-bottom text-center">
        <h1 class="text-center text-light">Register</h1>
        <form:form method="POST" action="/registration" modelAttribute="user">
            <div class="form-group">
                <form:label path="name">Username:</form:label>
                <form:input type="name" path="name"/>
                <form:errors path="name"/>
            </div>
            <div class="form-group">
                <form:label path="email">Email:</form:label>
                <form:input type="email" path="email"/>
                <form:errors path="email"/>
            </div>
            <div class="form-group">
                <form:label path="password">Password:</form:label>
                <form:password path="password"/>
                <form:errors path="email"/>
            </div>
            <div class="form-group">
                <form:label path="passwordConfirmation">Confirm Password:</form:label>
                <form:password path="passwordConfirmation"/>
                <form:errors path="email"/>
            </div>
            <button type="submit" class="btn btn-outline-warning">Register</button>
        </form:form>
    </div>
    <div class="container text-light text-center">
    <h1>Login</h1>
        <p><c:out value="${error}" /></p>
        <div>
            <form method="POST" action="/login">
                <p>
                    <label for="email">Email</label>
                    <input type="text" id="email" name="email"/>
                </p>
                <p>
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password"/>
                </p>
                <button type="submit" class="btn btn-outline-warning">Login</button>
            </form> 
        </div>
    </div>   
</div> 
</body>
</html>