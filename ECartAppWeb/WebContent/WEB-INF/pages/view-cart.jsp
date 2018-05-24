<%@ include file="header.txt" %>
<h3 class="alert alert-info">Your cart content...</h3>
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
		<c:forEach items="${sessionScope.cart}" var="entry" varStatus="status">
		<tr>
			<td>${status.index + 1}</td>
			<td>
			<img src="assets/images/products/${entry.value.product.picture}" 
				style="height: 75px; width: 75px;" class="img"/>
			${entry.value.product.name}</td>
			<td>${entry.value.quantity}</td>
			<td>${entry.value.unitPrice}</td>
			<td>${entry.value.quantity * entry.value.unitPrice}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<div>
<a href="place-order" class="btn btn-primary pull-right">Place order</a>
</div>
<%@ include file="footer.txt" %>











