<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>SK Mall</title>


<jsp:include page="/includes/asset.jsp"></jsp:include>
<%-- <jsp:include page="/includes/notify_asset.jsp"></jsp:include>   --%>
<jsp:include page="/includes/plugincss.jsp"></jsp:include>



<style type="text/css">
#goods-container {
	border: 0px solid black;
	width: 95%;
}

#goods-container>.goodsitem {
	border: 0px solid blue;
	height: 550px;
	padding: 0 0.75%;
	cursor: pointer;
}

#goods-container>.goodsitem>img {
	height: 400px;
	width: 100%;
	margin: 10px 0px;
}

#goods-container>.goodsitem>span {
	font-size: 12px !important;
	font-family: BMDOHYEON;
}

#goods-container>.goodsitem>.goodsdetail-box {
	overflow: hidden;
	height: 50px;
	margin-top: 10px;
	font-size: 12px !important;
	color: #999;
}

#goods-container>.goodsitem>.category-box span {
	font-style: italic;
}

.detailtext {
	height: 50px;
}

.card {
	cursor: pointer;
}

.total-price {
	text-align: right;
}

input[type=number]::-webkit-inner-spin-button, 
input[type=number]::-webkit-outer-spin-button {  

   opacity: 1;

}

#cnt{
	width: 75px;
}

.btns-box{
text-align: center;
}

#input-box{
	border: 0px solid red;
	width: 400px;
	margin: 50px;
}


</style>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	
		
});
</script>

</head>

<body>

	<div class="container-scroller">
		<!-- partial:../../partials/_navbar.html -->
		<jsp:include page="/WEB-INF/views/includes/header_nav.jsp"></jsp:include>

		<!-- partial -->
		<div class="container-fluid page-body-wrapper">
			<!-- partial:../../partials/_sidebar.html -->
			<jsp:include page="/WEB-INF/views/includes/sidebar.jsp"></jsp:include>
			<!-- partial -->
			<div class="main-panel">


				<!-- content-wrapper start -->
				<div class="content-wrapper">
					
					
					<div class="page-header">
						<h1 class=" h1">주문 리스트</h1>
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#">UI Elements</a></li>
								<li class="breadcrumb-item active" aria-current="page">Buttons</li>
							</ol>
						</nav>
					</div>
				
					
					<table class="table table-striped">
					
					<thead>
						<tr>
							<th>주문 코드</th>
							<th>상품 이미지</th>
							<th>상품명 (상품옵션)</th>
							<th>수량</th>
							<th>상품가격</th>
							<th>송장번호</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${list }" var="vo" varStatus="status">
						<tr>
						
							<td>${vo.orderCode }</td>
							<td><img src="${pageContext.servletContext.contextPath }/images/${vo.thumbnail}" alt="상품이미지" /></td>
							<td>${vo.goodsName }( ${vo.optionName })</td>
							<td>${vo.orderCnt }</td>
							<td><fmt:formatNumber value="${vo.sailingPrice }" type="currency"></fmt:formatNumber></td>
							<td>${vo.invoiceCode }</td>
						</tr>						
						</c:forEach>
						
						
					</tbody>
					
					
					
					</table>
					
					
					
					<hr />


				</div>
				<!-- content-wrapper ends -->



				<!-- partial:../../partials/_footer.html -->
				<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
				-
				<!-- partial -->
			</div>
			<!-- main-panel ends -->
		</div>
		<!-- page-body-wrapper ends -->
	</div>
	<!-- container-scroller -->
	<!-- plugins:js : if you take notification, stop this-->
	<jsp:include page="/includes/pluginjs.jsp"></jsp:include>
	<!-- endinject -->
	<!-- Plugin js for this page-->
	<!-- End plugin js for this page-->
	<!-- inject:js -->
	<jsp:include page="/includes/injectjs_asset.jsp"></jsp:include>
	<!-- endinject -->
	<!-- Custom js for this page-->
	<!-- End custom js for this page-->



</body>

</html>
