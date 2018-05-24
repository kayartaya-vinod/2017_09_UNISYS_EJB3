<%@ include file="header.txt" %>

<h3>${caption} (${fn:length(products)} items)</h3>

<div class="row">
	<c:forEach items="${products}" var="p">
		<div class="col-md-4">
			<div style="border: 1px solid #aaa; border-radius: 5px; padding: 10px;">
				<img class="img img-thumbnail img-responsive"
					src="assets/images/products/${p.picture}" />
					
				<p>${p.description}</p>
				<p>${p.quantityPerUnit}</p>
				<a href="add-to-cart?id=${p.id}&price=${p.unitPrice}" 
					class="btn btn-block btn-primary">
					<h3>Rs.${p.unitPrice} 
					<span class="fa fa-shopping-cart"></span></h3>
				</a>
			</div>
		</div>
	</c:forEach>
</div>

<%@ include file="footer.txt" %>