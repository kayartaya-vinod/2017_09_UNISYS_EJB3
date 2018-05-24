<%@ include file="header.txt" %>

<h3>Please login</h3>

<form class="form-horizontal" method="POST" action="j_security_check">
	<div class="form-group">
		<label for="username" class="col-sm-3 control-label">Username</label>
		<div class="col-sm-8">
			<input type="text" class="form-control" id="username"
				name="j_username" placeholder="Username (required)">
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-3 control-label">Password</label>
		<div class="col-sm-8">
			<input type="password" class="form-control" id="password"
				name="j_password" placeholder="Password (required)">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-3 col-sm-8">
			<button type="submit" class="btn btn-default">Sign in</button>
		</div>
	</div>
</form>

<%@ include file="footer.txt" %>