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
				highlight_link("home_link");
				highlight_link("home_link_heading");
			});
		</script>
	</head>

	<body>
		<jsp:include page="navbar.jsp"/>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span3">
					<jsp:include page="sidebar.jsp" />
				</div>
				<div class="span9">
					<div class="hero-unit">
					<h1>Welcome to SMA!</h1>
					<p>You can use this site to find cheap items on the Steam Marketplace.</p>
					
					<p><a href="#" class="btn btn-primary btn-large">Learn more &raquo;</a></p>
				</div>
				<div class="row-fluid">
					<div class="span4">
						<h2>Under Market Monitor</h2>
						<p>Use the Under Market monitor to be alerted when an item falls a specified percentage under the average market listing price.</p>
						<p><a class="btn" href="#">View details &raquo;</a></p>
					</div><!--/span-->
					<div class="span4">
						<h2>Under Price Monitor</h2>
						<p>Use the Under Price monitor to be alerted when an item falls under a specific price.</p>
						<p><a class="btn" href="#">View details &raquo;</a></p>
					</div><!--/span-->
					<div class="span4">
						<h2>Heading</h2>
						<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
						<p><a class="btn" href="#">View details &raquo;</a></p>
					</div><!--/span-->
				</div><!--/row-->
				<div class="row-fluid">
					<div class="span4">
						<h2>Heading</h2>
						<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
						<p><a class="btn" href="#">View details &raquo;</a></p>
					</div><!--/span-->
					<div class="span4">
						<h2>Heading</h2>
						<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
						<p><a class="btn" href="#">View details &raquo;</a></p>
					</div><!--/span-->
					<div class="span4">
						<h2>Heading</h2>
						<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
						<p><a class="btn" href="#">View details &raquo;</a></p>
					</div><!--/span-->
				</div><!--/row-->
			</div><!--/span-->
		</div><!--/row-->

		<hr>

		<footer>
			<p>&copy; Regen Van Walbeek 2013</p>
		</footer>

		</div><!--/.fluid-container-->
		
	</body>
</html>