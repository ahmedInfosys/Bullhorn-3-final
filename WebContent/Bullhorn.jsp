<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/additional-methods.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/additional-methods.min.js"></script>
<title>Comments area</title>
<style>h1{background: black; text:white}</style>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div>
      <ul class="nav navbar-nav">
      	<li><h1><b>${welcome}</b></h1></li>
      </ul>
  <div class="col-sm-2 col-sm-offset-10">
            <div class="account-wall">
                <form action= "SignIn.jsp" class="form-signin">
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                </form>
            </div>
         </div>
            
              <div class="account-wall col-sm-2 col-sm-offset-10">
                <form action= "SignUp" class="form-signin">
                <button class="btn btn-lg btn-primary btn-block" formaction="/Bullhorm/SignUp.jsp"style="background-color:#CC66FF" type="submit">Create Account</button>
               </form>
            </div>
      
    </div>
    
    
  </nav>

  ${comments}
  
  <div class="container">
	<form role="form" method="post" action="put_comment">
		<div ">
			<textarea maxlength="5" class="form-control" rows="4" cols="200" name="comment" placeholder="comment"></textarea><br>
			</div>
			<div class="form-group col-sm-4 col-sm-offset-8">
			<button type="submit" value = "submit" class= "button btn-primary form-control "><span class="glyphicon glyphicon-comment"></span> Comment</button>
		</div>
	</form>
</div>
  
</body>
</html>