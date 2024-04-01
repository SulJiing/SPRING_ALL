<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<link href="${pageContext.request.contextPath }/resources/lib/fontawesome-free-6.5.1-web/css/all.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/resources/lib/fancytree/skin-awesome/ui.fancytree.css" rel="stylesheet">
<script src="${pageContext.request.contextPath }/resources/lib/fancytree/jquery.fancytree-all-deps.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/lib/fancytree/modules/jquery.fancytree.glyph.js"></script>

<style type="text/css">
.fancytree-container {
  font-size: large !important;
}
.fancytree-icon {
  margin-right: 5px !important;
}
</style>
<!-- Initialize the tree when page is loaded -->
<script type="text/javascript">
  $(function(){  // on page load
    $("#tree").fancytree({
    	extensions: ["glyph"],
    	glyph:{
   	      preset: "awesome5",
   	      map: {
   	    	folder: "fa-solid fa-building",
   	      	folderOpen: "fa-solid fa-building-circle-arrow-right",
   	      	doc: "fa-solid fa-tablet"
   	      }
   	    }, 
   	 	selectMode:1,
   	 	dblclick:function(event, data){
   	    	if(!data.node.folder){
   	    		location.href="${pageContext.request.contextPath }/prod/prodView.do?what="+data.node.key;
   	    	}
   	    	return data.node.folder;
   	    }
    });
  
  	var tree = $.ui.fancytree.getTree("#tree");
  });
</script>
<pre>
	레퍼런스 문서 : https://github.com/mar10/fancytree/wiki
	API 문서 : https://wwwendt.de/tech/fancytree/doc/jsdoc/index.html
	설치 : https://github.com/mar10/fancytree/wiki#embed-fancytree-on-a-web-page
		(아이콘 사용을 위해 fontawsome 라이브러리 필요 , https://fontawesome.com/start)
	트리 데이터 : https://github.com/mar10/fancytree/wiki/TutorialLoadData
	아이콘 변경 : https://github.com/mar10/fancytree/wiki/ExtGlyph
	더블클릭 이벤트 처리 : https://github.com/mar10/fancytree/wiki/TutorialEvents
	처리 가능한 이벤트 종류 : https://wwwendt.de/tech/fancytree/doc/jsdoc/global.html#FancytreeEvents
</pre>
<div id="tree">
	<ul id="treeData" style="display: none;">
		<c:forEach items="${buyerList }" var="buyer">
			<li id="${buyer.buyerId }" class="folder">${buyer.buyerName }
				<c:if test="${not empty buyer.prodList }">
					<ul>
						<c:forEach items="${buyer.prodList }" var="prod">
							<li id="${prod.prodId }" class="file">${prod.prodName }</li>
						</c:forEach>
					</ul>
				</c:if>
			</li>
		</c:forEach>
	  </ul>
</div>
