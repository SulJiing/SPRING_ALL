<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script	src="${pageContext.request.contextPath }/resources/lib/aggrid/ag-grid-community.min.js"></script>
<pre>
	행 정렬 기준 변경 : https://www.ag-grid.com/javascript-data-grid/row-sorting/#custom-sorting
	클라이언트 사이드 페이징 처리 : https://www.ag-grid.com/javascript-data-grid/row-pagination/
	클라이언트 검색 처리 : https://www.ag-grid.com/javascript-data-grid/filtering/
	서버 사이드 페이징 및 검색 처리 :
		https://ag-grid.com/javascript-data-grid/server-side-model-pagination/ 
		https://ag-grid.com/javascript-data-grid/server-side-operations-oracle/
</pre>
<div class="example-header">
	<span>Quick Filter:</span>
	<input type="text" id="filter-text-box" placeholder="Filter..." oninput="onFilterTextBoxChanged()">
</div>
<div id="myGrid" class="ag-theme-quartz-auto-dark w-75" style="height: 500px"></div>
<script>
	//Grid Options: Contains all of the grid configurations
	const gridOptions = {
		getRowId : (params) => params.data.prodId,
		pagination: true,
		includeHiddenColumnsInQuickFilter: true,
		columnDefs: [
		    { field: "prodId", hide:true},
		    { field: "prodName", pinned:"left"},
		    { field: "prodLgu", hide:true },
		    { field: "lprod.lprodNm", headerName:"Lprod" , unSortIcon: true
			      , comparator: (valueA, valueB, nodeA, nodeB, isDescending) => {
			    	  let prodA = nodeA.data;
			    	  let prodB = nodeB.data;
			    	  return prodA.prodLgu.localeCompare(prodB.prodLgu);
			      }
		    },
		    { field: "prodBuyer", hide:true },
		    { field: "buyer.buyerName", headerName:"Buyer", unSortIcon: true
		    	 , comparator: (valueA, valueB, nodeA, nodeB, isDescending) => {
			    	  let prodA = nodeA.data;
			    	  let prodB = nodeB.data;
			    	  return prodA.prodBuyer.localeCompare(prodB.prodBuyer);	
		    	 }
		    },
		    { field: "prodPrice" },
		    { field: "prodMileage" },
		    { field: "prodInsdate", cellDataType:"dateString" },
		]
	};
	
	// Your Javascript code to create the grid
	const myGridElement = document.querySelector('#myGrid');
	const gridApi = agGrid.createGrid(myGridElement, gridOptions);
	
	fetch("case2/json")
		.then(resp=>resp.json())
		.then(json=>gridApi.setGridOption("rowData", json));
	
	function onFilterTextBoxChanged() {
		gridApi.setGridOption(
		    'quickFilterText',
		    document.getElementById('filter-text-box').value
		);
	}
</script>