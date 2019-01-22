<form action="/" method="post">
	<div class="container">
		<%
			String valido = (String) request.getAttribute("valido");
			if (valido != null) {

				out.print(valido);
				out.println();
			}
		%>
		<p>
			<label for="uname"><b>Username</b></label> <input type="text"
				placeholder="Enter Username" name="uname" required>
		<p>
			<label for="psw"><b>Password</b></label> <input type="password"
				placeholder="Enter Password" name="psw" required value=<%%>>
		<p>
			<button type="submit">Login</button>
	</div>
</form>