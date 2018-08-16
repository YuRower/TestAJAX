<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript" src="jquery-1.8.1.js"></script>
    <script type="text/javascript">
        // обработчик события загрузки страницы.
        window.onload = function () {

            $("#loader").hide();

            $("button").click(function () {
                $("#loader").show();
                var dataToSend = "";

                dataToSend += "login=" + $("#login").val();
                dataToSend += "&pass=" + $("#password").val();

                $.ajax({
                    type: "POST",
                    url: "Controller",

                    // данные, которые будут отправлятся на сервер с запросом
                    data: dataToSend,

                    success: function (data) {
                        $("#output").text(data);
                        $("#loader").hide();
                    }
                });
            });
        }
    </script>
</head>
<table id="main-container">

			

			<section>
				<div id="qq">
					<form id="login_form" action="controller" method="post">

						<input type="hidden" name="command" value="login" />


						<legend>E-mail</legend>
						<input name="email" required /><br />

						<legend>Password</legend>
						<input type="password" name="password" required /> <a
						href="controller?command=registration">Sign up</a>

				<input type="submit" value="Click Me" id="loader">>

					</form>
				</div>

				<input type="submit" value="Click Me" id="loader">>

			</section>




		</table>

</html>
