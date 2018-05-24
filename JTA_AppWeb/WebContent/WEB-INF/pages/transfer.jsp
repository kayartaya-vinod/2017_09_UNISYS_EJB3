<%@ include file="header.jspf"%>

<form name="f1" method="POST" onsubmit="return validate()" class="form-horizontal">
	<div class="row">
		<label class="col-sm-3">Select the FROM account number: </label>
		<div class="col-sm-9">
			<select name="from_account_number">
				<c:forEach items="${accounts}" var="ac">
					<option value="${ac.accountNumber}">${ac.accountNumber} - ${ac.customer}</option>
				</c:forEach>
			</select>
		</div>
	</div>


	<div class="row">
		<label class="col-sm-3">Select the TO account number: </label>
		<div class="col-sm-9">
			<select name="to_account_number">
				<c:forEach items="${accounts}" var="ac">
					<option value="${ac.accountNumber}">${ac.accountNumber} - ${ac.customer}</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="row">
		<label class="col-sm-3"> Enter the amount to transfer: </label>
		<div class="col-sm-9">
			<input type="text" name="amount" value="5500" />
		</div>
	</div>


	<div class="row">
		<div class="col-sm-9 col-sm-offset-3">
		<button class="btn btn-primary">Submit</button>
		</div>
	</div>
</form>

<h3>Account details:</h3>
<table class="table table-bordered table-striped">
	<tr>
		<td>Account no</td>
		<td>Customer</td>
		<td>Balance</td>
	</tr>
	<c:forEach items="${accounts}" var="ac" varStatus="status">
		<tr>
			<td>${ac.accountNumber}</td>
			<td>${ac.customer}</td>
			<td>${ac.balance}</td>
		</tr>
	</c:forEach>
</table>
<c:if test="${not empty err }">
	<h3 style="color:red">ERROR: ${err}</h3>
</c:if>

<h3>Transaction records:</h3>
<c:if test="${fn:length(records)>0}">
	<table class="table table-bordered table-striped">
		<tr>
			<td>Sl no</td>
			<td>Date/time</td>
			<td>Description</td>
		</tr>
		<c:forEach items="${records}" var="rec" varStatus="status">
			<tr>
				<td>${status.index+1}</td>
				<td>${rec.transactionDate}</td>
				<td>${rec.description}</td>
			</tr>
		</c:forEach>
	</table>
</c:if>

<script>
	function validate() {
		var fac = document.f1.from_account_number.value;
		var tac = document.f1.to_account_number.value;
		if (fac == tac) {
			alert("Please select different account numbers");
			return false;
		}
		var amt = document.f1.amount.value;

		if (!amt || isNaN(amt) || parseFloat(amt) <= 0) {
			alert("Amount must be a number more than 0");
			return false;
		}
		return true;
	}
</script>
<%@ include file="footer.jspf"%>