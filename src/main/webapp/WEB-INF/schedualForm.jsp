 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
</head>
<body>
    <h1>Register</h1>
    <div>
         <form:form method="POST" action="/registration" modelAttribute="user">
            <p>
                <form:label path="name">Name:</form:label>
                <form:input type="name" path="name"/>
                <form:errors path="name"/>
                
            </p>
            <p>
                <form:label path="email">Email:</form:label>
                <form:input type="email" path="email"/>
                <form:errors path="email"/>
                
            </p>
            <p>
                <form:label path="password">Password:</form:label>
                <form:password path="password"/>
                <form:errors path="password"/>
            
            </p>
            <p>
                <form:label path="passwordConfirmation">Password Confirmation:</form:label>
                <form:password path="passwordConfirmation"/>
                <form:errors path="passwordConfirmation"/>
            
            </p>
            <input type="submit" value="Register!"/>
        </form:form>
    </div> 
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
            <input type="submit" value="Login!"/>
        </form> 
    </div>     
</body>
</html>