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
			// Update the UI
			$(document).ready(function(){
				highlight_link("settings_link");
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
					<h1>Settings</h1>
					Note: These settings don't actually do anything yet. Just laying out ideas for what needs to be implemented.
					<br />
					Alert me when:<br />
					An item is listed at <input type="text" value="20" />% under average lowest Steam Market listing. <br /> (Note: any use doing this individually for each item?) <br /><br />
					An item is at <input type="text" value="2.50" /> under the average lowest Steam Market listing. <br /> (Note: this should be item dependent. ex: key under $2.00)<br /><br />
					<input type="checkbox" /> Play a sound when a new cheap item is found<br />
					TODO taking suggestions on other settings. 					
				</div>
			</div>
			<hr>
			<footer>
				<p>&copy; Regen Van Walbeek 2013</p>
			</footer>
		</div>

	</body>
</html>