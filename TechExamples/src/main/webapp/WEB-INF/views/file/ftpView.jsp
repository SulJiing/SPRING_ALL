<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="row mt-3">
	<pre>
		FTP 서버의 활용 : FTP client 모듈 구현시 commons-net 라이브러리 활용 방법과 FTP client 객체 풀링 방법(kr.or.ddit.ftp.**)
		appInfo.properties , ftp-context.xml 확인
		이벤트 처리를 비롯해 jQuery 를 사용하고 있음.
	</pre>
	<div class="prsArea">
		
	</div>
	<div class="col-6" id="treeArea" data-source="${pageContext.request.contextPath}/file/ftp"></div>
	<div class="col-2">
		<input id="downBtn" type="button" value="download!" class="btn btn-primary"
			data-action="${pageContext.request.contextPath}/file/ftp/download"
		/>
	</div>
</div>
<link href="${pageContext.request.contextPath }/resources/lib/fancytree/skin-win8/ui.fancytree.css" rel="stylesheet">
<script src="${pageContext.request.contextPath }/resources/lib/fancytree/jquery.fancytree-all-deps.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/app/file/ftpView.js"></script>
