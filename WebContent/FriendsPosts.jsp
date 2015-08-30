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
<title>People posts</title>
<style>h1{background: black; text:white}</style>
</head>
<body background="http://www.freewallpaperfullhd.com/wp-content/uploads/2015/02/Nature-Wallpaper-HD-1920x1080.jpg">

<nav class="navbar navbar-default" >
	<ul class="nav navbar-nav navbar-left">
	   <li role="presentation" >Welcome <b>${welcome}</b></li>
	   <li role="presentation"><a href="/Bullhorm/user_profile">Profile</a></li>
	   <li role="presentation"><a href="/Bullhorm/MyPosts">My posts</a></li>
	   <li role="presentation"><a href="/Bullhorm/Friends_posts">People posts</a></li>
	   <li role="presentation"><a href="/Bullhorm/Search">Search posts</a></li>
	</ul>
	<ul class="nav navbar-nav navbar-right">
		<li role="presentation" >${sign_in_out}</li>
    </ul>
</nav> 

  ${Friends_posts}

</body>
</html>