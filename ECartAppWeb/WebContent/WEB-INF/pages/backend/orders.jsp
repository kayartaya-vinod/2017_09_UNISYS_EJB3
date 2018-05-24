<%@ include file="header.txt" %>

<h3>Order list</h3>

<table class="table table-striped table-bordered table-hover table-condensed">
	<thead>
		<tr>
			<td>Sl no</td>
			<td>Order id</td>
			<td>Order date</td>
			<td>Customer</td>
			<td>Current status</td>
			<td>Change to</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${orders}" var="ord" varStatus="status">
		<tr>
			<td>${status.index+1}</td>
			<td>${ord.id}</td>
			<td>${ord.orderDate}</td>
			<td>${ord.customer.name}<br />${ord.customer.email} / ${ord.customer.phone}</td>
			<td>${ord.status}</td>
			<td><form method="POST">
				<input type="hidden" name="order_id" value="${ord.id}"/>
				<select name="status">
					<c:if test="${ord.status!='PENDING'}"><option>PENDING</option></c:if>
					<c:if test="${ord.status!='PROCESSING'}"><option>PROCESSING</option></c:if>
					<c:if test="${ord.status!='DISPATCHED'}"><option>DISPATCHED</option></c:if>
					<c:if test="${ord.status!='DECLINED'}"><option>DECLINED</option></c:if>
				</select>
				<button>Change</button>
			</form></td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="footer.txt" %>