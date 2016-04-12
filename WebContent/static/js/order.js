function edit(orderId, orderPrice, orderSummary, orderDes, orderStatus){
	
	$("#orderId").val(orderId);
	$("#orderPrice").val(orderPrice);
	$("#orderSummary").val(orderSummary);
	$("#orderDescription").val(orderDes);
	if(orderStatus == "true"){
		$("input[name=orderStatus][value=true]").attr('checked', true);
	}else if(orderStatus == "false"){
		$("input[name=orderStatus][value=false]").attr('checked', true);
	}
}
