<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script	src="${pageContext.request.contextPath }/resources/lib/aggrid/ag-grid-community.min.js"></script>
<pre>
	설치 : https://www.ag-grid.com/javascript-data-grid/download/
	7가지 그리드 케이스 : https://www.ag-grid.com/javascript-data-grid/deep-dive/
	스타일 지정 : https://ag-grid.com/javascript-data-grid/cell-styles/
	프린트 설정 : https://ag-grid.com/javascript-data-grid/printing/
</pre>
<div id="myGrid" class="ag-theme-quartz-auto-dark w-75" style="height: 500px"></div>
<span>
<button class="btn btn-primary" onclick="onBtPrint()">PRINT</button>
</span>
<script>
	//Grid Options: Contains all of the grid configurations
	const gridOptions = {
		defaultColDef: {
	        sortable: false
	    },
	    getRowId : (params) => params.data.memId,
		columnDefs: [
		    { field: "memId", sortable: true, pinned: "left", cellClass:"text-center"},
		    { field: "memName" },
		    { field: "memHp" },
		    { field: "memMail" },
		    { field: "memAdd1" },
		    { field: "memMileage", sortable:true, sortingOrder: ['asc', 'desc'] },
		    { field: "memBir", sortable:true, sortingOrder: ['desc', 'asc'] },
		    { field: "memMemorialday", sortable:true, sortingOrder: ['desc', 'asc'] },
		    { field: "memDelete", headerName:"탈퇴여부" }
		]
	};
	
	// Your Javascript code to create the grid
	const myGridElement = document.querySelector('#myGrid');
	const gridApi = agGrid.createGrid(myGridElement, gridOptions);
	
	fetch("case1/json")
		.then(resp=>resp.json())
		.then(json=>gridApi.setGridOption("rowData", json));
	
	function onBtPrint() {
	  setPrinterFriendly(gridApi);
	  setTimeout(() => {
	    print();
	    setNormal(gridApi);
	  }, 2000);
	}
	
	function setPrinterFriendly(api) {
	  api.setGridOption('domLayout', 'print');
	}
	
	function setNormal(api) {
	  api.setGridOption('domLayout', undefined);
	}
</script>