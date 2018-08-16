<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/slider.css">
<script src="js/jquery-3.3.1.js"></script>
<script src="js/myscript.js"></script>
<script src="js/slider.js"></script>
<script src="js/main.js"></script>
<script src="js/click.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="1.js"></script>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<div class="wrapper">
		<header>
			<h1>City Gallery</h1>
		</header>
		<section>
			<nav>
				<ul>
					<li><a href="index.jsp">London</a></li>
					<li><a href="contact.jsp">Paris</a></li>
					<li><a href="modal.jsp">Tokyo</a></li>
				</ul>
			</nav>
		</section>
		<table id="main-container">
			<%@ include file="/WEB-INF/jspf/header.jspf"%>
			<section>
				<div id="qq">
					<%--><form id="login_form" action="controller" method="post">

						<input type="hidden" name="command" value="login" />


						<legend>E-mail</legend>
						<input name="email" required /><br />

						<legend>Password</legend>
						<input type="password" name="password" required /> <a
							href="controller?command=registration">Sign up</a>
					</form>--%>
					<script>
						$(document).ready(function() {

							$('#submit').click(function(event) {
								$.ajax({
									url : 'ActionServlet',
									data : {
										user : $('#user').val()

									},
									success : function(responseText) {
										$('#welcometext').text(responseText);
									}
								});
							});
						});
					</script>
					<form id="form1">
						<h1>AJAX Demo</h1>
						Enter your Name: <input type="text" id="user" /> <input
							type="button" id="submit" value="Ajax Submit" />

						<div id="welcometext"></div>
					</form>
				</div>
				<input type="submit" value="Click Me" id="click_me">
			</section>
		</table>
	</div>

	<%@ include file="/WEB-INF/jspf/footer.jspf"%>

</body>
</html>