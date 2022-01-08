<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
    
<!DOCTYPE html>
<html>
<head>
     <meta charset="UTF-8">
  <title>Home</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

  <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
  
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Lexend+Deca:wght@500&display=swap" rel="stylesheet">
</head>




<body id="myPage" class="text-light">


<nav class="navbar navbar-expand-lg navbar-dark primary-color">

  <a class="navbar-brand text-warning" href="/feed"><h1>SocialBuzz</h1></a>

  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#basicExampleNav"
    aria-controls="basicExampleNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="basicExampleNav">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="/feed"><h5>The Buzz</h5>
          <span class="sr-only">(current)</span>
        </a>
      </li>
    </ul>
      <ul class="navbar-nav mr-5">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <h5>Settings</h5>
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="#">Action</a>
            <a class="dropdown-item" href="#">Another action</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">Something else here</a>
          </div>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/logout"><h5>Logout</h5></a>
        </li>
      </ul>
  </div>
</nav>

<div class="container" style="margin-top:30px">
    <div class="row">
    <div class="col-sm-4 ">
        <h2>Welcome <c:out value="${user.name}"/></h2>
        <h4>joined <c:out value="${user.createdAt}"/></h4>
    </div>
    <div class="col-sm-8 border border-warning">
        <button type="button" class="btn btn-outline-warning" data-toggle="modal" data-target="#buzzModal">
        +New Buzz
        </button> 
        <h2 class="text-center">Posts</h2>
        <c:forEach items="${user.buzzes}" var="buzz">
          <div class="container-fluid d-flex flex-column justify-content-center bg-warning m-2 border border-light">
            <div class="container">
              <div class="container d-flex justify-content-between">
                <h5><c:out value="${buzz.user.name}"/></h5>
                <button type="link" class="close" aria-label="Close" data-toggle="modal" data-target="#confirmModal">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="container d-flex">
                <h4 class="text-center"><c:out value="${buzz.buzz}"/></h4>
              </div>
            </div>
          </div>
        </c:forEach>
    </div>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="buzzModal" tabindex="-1" role="dialog" aria-labelledby="buzzModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content bg-dark">
      <div class="modal-header bg-warning">
        <h5 class="modal-title text-dark " id="buzzModalLabel">Post new buzz</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body bg-dark">
        <form:form method="POST" action="/myProfile/post" modelAttribute="buzz">
          <div class="md-form amber-textarea active-amber-textarea-2">
            <form:textarea id="form16" class="md-textarea form-control" rows="3" path="buzz"></form:textarea>
            <label for="form16">Tell a funny buzz..</label>
          </div>  
      </div>
          <div class="modal-footer bg-warning">
            <button type="submit" class="btn btn-outline-dark">Post Buzz</button>
          </div>
        </form:form> 
    </div>
  </div>
</div>

<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content bg-dark">
      <div class="modal-header bg-warning">
        <h5 class="modal-title text-dark " id="confirmModalLabel">Delete Buzz</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body bg-dark">
        <h1>Are you sure?</h1>
      </div>
      <div class="modal-footer bg-warning">
        <button type="submit" class="btn btn-outline-dark">Delete Buzz</button>
        <button type="button" class="btn btn-outline-dark" data-dismiss="modal" aria-label="Close">Close</button>
      </div>
    </div>
  </div>
</div>
</body>
<script>
$('#myModal').modal(options)

</script>
</html>