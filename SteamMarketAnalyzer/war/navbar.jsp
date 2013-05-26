<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- Attributes: user, loginURL, logoutURL -->
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container-fluid">
			<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="brand" href="/">Steam Market Analyzer</a>
			<div class="nav-collapse collapse">
				<c:choose>
					<c:when test="${ user == null }">
						<p class="navbar-text pull-right"><a href="/signup" class="navbar-link">Sign Up</a> | <a href="${ loginURL }" class="navbar-link">Log in</a></p>
					</c:when>
					<c:otherwise>
						<p class="navbar-text pull-right">${fn:escapeXml(user.email)} | <a href="${logoutURL}" class="navbar-link">Logout</a></p>
					</c:otherwise>
				</c:choose>
				<ul class="nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
					<li><a href="#contact">Contact</a></li>
				</ul>
			</div><!--/.nav-collapse -->
    	</div>
	</div>
</div>