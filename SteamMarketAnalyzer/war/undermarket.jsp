<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Steam Market Analyzer</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">

		<!-- Styles -->
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<style type="text/css">
			body {
				padding-top: 60px;
				padding-bottom: 40px;
			}
		  	.sidebar-nav {
				padding: 9px 0;
			}
		
			@media (max-width: 980px) {
				/* Enable use of floated navbar text */
				.navbar-text.pull-right {
					float: none;
					padding-left: 5px;
					padding-right: 5px;	
				}
		  	}
		</style>
		<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
		
		<!-- Scripts -->
		<script type="text/javascript" src="js/jquery-2.0.1.min.js"></script>
		<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/sma_util.js" ></script>
		<script type="text/javascript">
			$(document).ready(function(){
				highlight_sidebar("undermarket_link");
			});
		</script>
	</head>

	<body>
		<jsp:include page="navbar.jsp"/>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span3">
					<jsp:include page="sidebar.jsp" />
				</div><!--/span-->
				<div class="span9">
					<h1>Under Market Monitor</h1>
					<p>Under Construction: Monitors when specific items fall x% under average list price.</p>
				</div>
			</div>
			<hr>
			<footer>
				<p>&copy; Regen Van Walbeek 2013</p>
			</footer>
		</div>

	</body>
</html>