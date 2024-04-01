<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script	src="${pageContext.request.contextPath }/resources/lib/aggrid/ag-grid-community.min.js"></script>
<h4>Row Editing</h4>
<pre>
	row edit : https://ag-grid.com/javascript-data-grid/cell-editing-full-row/
	데이터 트랜잭션 처리 : https://ag-grid.com/javascript-data-grid/data-update-transactions/
</pre>
<div id="myGrid" class="ag-theme-quartz-auto-dark w-75" style="height: 500px"></div>
<span>
	<button onclick="dataClear();" class="btn btn-danger">CLEAR</button>
	<button onclick="dataLoad();" class="btn btn-primary">RELOAD</button>
</span>
<script>
	//Grid Options: Contains all of the grid configurations
	const gridOptions = {
		defaultColDef: {
		    flex: 1,
		    minWidth: 130,
		    editable: true
  	    }, 
		editType : "fullRow",
		getRowId : (params) => params.data.buyerId,
		columnDefs: [
			{field : "buyerId", pinned:"left", editable:false}, 
			{field : "buyerName", editable:false}, 
			{field : "buyerLgu", editable:false}, 
			{field : "buyerBank"}, 
			{field : "buyerBankno"}, 
			{field : "buyerBankname"}, 
			{field : "buyerZip"}, 
			{field : "buyerAdd1"}, 
			{field : "buyerAdd2"}, 
			{field : "buyerComtel"}, 
			{field : "buyerFax"}, 
			{field : "buyerMail"}, 
			{field : "buyerCharger"}, 
			{field : "buyerTelext"} 
		]
	};
	
	// Your Javascript code to create the grid
	const myGridElement = document.querySelector('#myGrid');
	const gridApi = agGrid.createGrid(myGridElement, gridOptions);
	gridApi.addEventListener("rowValueChanged", (event)=>{
		console.log(event);
		let targetBuyer = event.data;
		fetch("case3", {
			method:"put",
			headers:{
				"content-type":"application/json",
				"accept":"application/json"
			}, 
			body:JSON.stringify(targetBuyer)
		}).then(resp=>resp.json())
		.then(json=>gridApi.applyTransaction({ update: [json] }));
	});
	
	const dataLoad = ()=>{
		fetch("case3/json", {method:"get", headers:{"accept":"application/json"}})
			.then(resp=>resp.json())
			.then(json=>gridApi.setGridOption("rowData", json));
	};
	const dataClear = ()=>{
		const rowData = [];
		gridApi.forEachNode(function (node) {
		  rowData.push(node.data);
		});
		const res = gridApi.applyTransaction({remove: rowData});
	};
	
	document.addEventListener("DOMContentLoaded", dataLoad);
</script>