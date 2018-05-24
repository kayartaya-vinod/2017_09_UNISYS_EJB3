<%@ include file="header.txt" %>
<h3 class="alert alert-success">Your order summary</h3>

<table class="table">
	<tr>
		<td>Order id</td>
		<td>${order.id}</td>
	</tr>
	<tr>
		<td>Customer name</td>
		<td>${order.customer.name}</td>
	</tr>
	<tr>
		<td>Delivery Address</td>
		<td>${order.customer.address} <br>
			${order.customer.city} <br>
			${order.customer.state}
			${order.customer.country}
		</td>
	</tr>
	<tr>
		<td>Contact details</td>
		<td>${order.customer.email} ${order.customer.phone}</td>
	</tr>
</table>

<p class="lead">Your order contains these products...</p>

<table class="table table-striped table-bordered table-hover">

	<thead>
		<tr>
			<th>Sl no</th>
			<th>Product name</th>
			<th>Quantity</th>
			<th>Unit price</th>
			<th>Amount</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${order.lineItems}" var="item" varStatus="status">
		<tr>
			<td>${status.index + 1}</td>
			<td>
			<img src="assets/images/products/${item.product.picture}" 
				style="height: 75px; width: 75px;" class="img"/>
			${item.product.name}</td>
			<td>${item.quantity}</td>
			<td>${item.unitPrice}</td>
			<td>${item.quantity * item.unitPrice}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<p>A copy of this invoice has been mailed to your email - ${order.customer.email}</p>

<%@ include file="footer.txt" %>











