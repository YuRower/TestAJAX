<!DOCTYPE html>

<html lang="en">
<html>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/slider.css">

<script src="js/jquery-3.3.1.js"></script>
<script src="js/myscript.js"></script>
<script src="js/slider.js"></script>

<script src="js/main.js"></script>
<script src="js/click.js"></script>
<c:set var="title" value="Registration" />
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
		<div class="push"></div>




		<table id="main-container">

			<%-- HEADER --%>
			<%@ include file="/WEB-INF/jspf/header.jspf"%>
			<%-- HEADER --%>

			<tr>
				<td class="content center">

					<form id="contact_form" action="controller" method="post">

						<input type="hidden" name="command" value="registration" />

						<fieldset>
							<legend>New user registration</legend>
							<input name="email" placeholder="E-mail" type="email" required /><br />
							<input name="password" placeholder="Password" required
								pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,20}$" /><br />
							<input name="name" placeholder="Name" required /><br /> <input
								name="surname" placeholder="Surname" required /><br />
							<li><input type="radio" name="gender" value="male" checked>
								Male<br> <input type="radio" name="gender" value="female">
								Female<br> <input type="radio" name="gender" value="other">
								Other</li>

							<li><select>
									<option value="volvo">Volvo</option>
									<option value="saab">Saab</option>
									<option value="opel">Opel</option>
									<option value="audi">Audi</option>
							</select></li>
							<li><label for="message">Message:</label> <textarea
									name="message" cols="40" rows="6">
        </textarea> <input type="submit" value="Registrate"> <pre>Password format:<br />8 characters minimum.<br />At least 1 small, 1 capital and 1 digit required.<br />Only latin letters allowed.
						</pre>&#9;
						</fieldset>
						<br />
					</form> <br />
				</td>
			</tr>





		</table>


		<button onclick="prev()">Prev</button>
		<img id="slider" src="img/1.jpg" alt="" />
		<button onclick="next()">Next</button>


		<%@ include file="/WEB-INF/jspf/footer.jspf"%>

	</div>

</body>
</html>







