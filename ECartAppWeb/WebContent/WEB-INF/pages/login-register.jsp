<%@include file="header.txt"%>
<h3 class="text-center">If you are an existing customer, please login here:</h3>
<form id="f1" class="form-horizontal" method="POST" onsubmit="return validateLogin()">
<input type="hidden" name="action" value="login">
	<div class="form-group">
		<label for="email" class="col-sm-3 control-label">Email *</label>
		<div class="col-sm-8">
			<input type="email" class="form-control" id="email"
				name="email" placeholder="Email (required)">
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-3 control-label">Password *</label>
		<div class="col-sm-8">
			<input type="password" class="form-control" id="password"
				name="password" placeholder="Password (required)">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-3 col-sm-8">
			<button type="submit" class="btn btn-default">Sign in</button>
			<span style="color: red;">${loginError}</span>
		</div>
	</div>
</form>

<h3 class="text-center">If you are a new customer, please register here:</h3>
<form id="f2" class="form-horizontal" method="POST" onsubmit="return validateRegistration()">
<input type="hidden" name="action" value="register" >
	<div class="form-group">
		<label for="name" class="col-sm-3 control-label">Name *</label>
		<div class="col-sm-8">
			<input type="text" class="form-control" id="name"
				name="name" placeholder="Name (required)">
		</div>
	</div>
	<div class="form-group">
		<label for="phone" class="col-sm-3 control-label">Phone number *</label>
		<div class="col-sm-8">
			<input type="text" class="form-control" id="phone"
				name="phone" placeholder="Phone number (required)">
		</div>
	</div>
	<div class="form-group">
		<label for="email" class="col-sm-3 control-label">Email *</label>
		<div class="col-sm-8">
			<input type="email" class="form-control" id="email"
				name="email" placeholder="Email (required)">
		</div>
	</div>
	<div class="form-group">
		<label for="address" class="col-sm-3 control-label">Address</label>
		<div class="col-sm-8">
			<input type="text" class="form-control" id="address"
				name="address" placeholder="Address">
		</div>
	</div>
	<div class="form-group">
		<label for="city" class="col-sm-3 control-label">City</label>
		<div class="col-sm-8">
			<input type="text" class="form-control" id="city"
				name="city" placeholder="City">
		</div>
	</div>
	<div class="form-group">
		<label for="state" class="col-sm-3 control-label">State</label>
		<div class="col-sm-8">
			<input type="text" class="form-control" id="state"
				name="state" placeholder="State">
		</div>
	</div>
	<div class="form-group">
		<label for="country" class="col-sm-3 control-label">Country</label>
		<div class="col-sm-8">
			<input type="text" class="form-control" id="country"
				name="country" placeholder="Country">
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-3 control-label">Password *</label>
		<div class="col-sm-8">
			<input type="password" class="form-control" id="password"
				name="password" placeholder="Password (required)">
		</div>
	</div>
	<div class="form-group">
		<label for="c_password" class="col-sm-3 control-label">Confirm password</label>
		<div class="col-sm-8">
			<input type="password" class="form-control" id="c_password"
				name="c_password" placeholder="Confirm password (must match the above password)">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-3 col-sm-8">
			<button type="submit" class="btn btn-default">Register and login</button>
		</div>
	</div>
</form>
<script type="text/javascript">
	function validateLogin(){
		
		var email = $("#f1 #email").val().trim();
		var password = $("#f1 #password").val().trim();
		
		var missingFields = [];
		if(!email){
			missingFields.push("email");
		}
		if(!password){
			missingFields.push("password");
		}
		if(missingFields.length>0){
			swal("Missing fields!", missingFields.join(), "error")
			return false;
		}
		
		return true;
		
	}
	function validateRegistration(){
		var name = $("#f2 #name").val().trim();
		var email = $("#f2 #email").val().trim();
		var phone = $("#f2 #phone").val().trim();
		var password = $("#f2 #password").val().trim();
		var c_password = $("#f2 #c_password").val().trim();
		
		var missingFields = [];


		if(!name){
			missingFields.push("name");
		}
		if(!email){
			missingFields.push("email");
		}
		if(!phone){
			missingFields.push("phone");
		}
		if(!password){
			missingFields.push("password");
		}

		if(missingFields.length>0){
			swal("Missing fields!", missingFields.join(), "error")
			return false;
		}
		if(password != c_password){
			swal("Passwords don't match", "", "error")
			return false;
		}
		
		return true;
	}
</script>
<%@include file="footer.txt"%>