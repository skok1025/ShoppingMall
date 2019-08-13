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
	float: left;
	margin: 50px;
}

#input-box > input{
	padding: 10px !important;
}

</style>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
function openZipSearch() {
	new daum.Postcode({
		oncomplete: function(data) {
			$('[name=receiverPostcode]').val(data.zonecode); // 우편번호 (5자리)
			$('[name=addr1]').val(data.address);
			$('[name=addr2]').val(data.buildingName);
		}
	}).open();
}

$(document).ready(function(){
	$("#samebox").change(function(){
        if($("#samebox").is(":checked")){
            //alert("체크박스 체크했음!");
            $("#orderName").val("${userName}");
            $("#orderTel").val("${userTel}");
        }else{
            //alert("체크박스 체크 해제!");
            $("#orderName").val("");
            $("#orderTel").val("");
        }
    });	
	
	$("#samebox2").change(function(){
        if($("#samebox2").is(":checked")){
            //alert("체크박스 체크했음!");
            $("#receiverName").val("${userName}");
            $("#receiverTel1").val("${userTel}");
            $("#receiverTel2").val("${userTel}");
        }else{
            //alert("체크박스 체크 해제!");
            $("#receiverName").val("");
            $("#receiverTel1").val("");
            $("#receiverTel2").val("");
        }
    });	
		
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
					
					<!-- 
					<div class="page-header">
						<h1 class=" h1">Title</h1>
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#">UI Elements</a></li>
								<li class="breadcrumb-item active" aria-current="page">Buttons</li>
							</ol>
						</nav>
					</div>
					 -->
					
					<!-- 이곳에 내용을 작성  -->

					<h1>주문 상품 리스트</h1>

<form action="${pageContext.servletContext.contextPath }/order/proceed" method="post">
					<table class="table table-striped">
					
					<thead>
						<tr>
							<th>no</th>
							<th>상품 이미지</th>
							<th>상품명 (상품옵션)</th>
							<th>수량</th>
							<th>상품가격</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${basketItemList }" var="vo" varStatus="status">
						<tr>
						
							<td>${status.count }</td>
							<td><img src="${pageContext.servletContext.contextPath }/images/${vo.thumbnail}" alt="상품이미지" /></td>
							<td>${vo.goodsName }( ${vo.optionName })</td>
							<td>
							<input type="hidden" class="goodsDetailNo" value="${vo.goodsDetailNo}" name="orderGoodsList[${status.index }].goodsDetailNo"/>
							<input class="form-control" type="number" id="cnt" value="${vo.cnt }" name="orderGoodsList[${status.index }].cnt"/>
							<input type="hidden" value="${vo.price }" name="orderGoodsList[${status.index }].sailingPrice" />
							<td><fmt:formatNumber value="${vo.price }" type="currency"></fmt:formatNumber></td>
							
						</tr>						
						</c:forEach>
						
						
					</tbody>
					
					
					
					</table>
					
					<hr />
					<div id="input-box">
						<div style="float: left;">
							<h1>주문자 정보</h1>
						</div>
						<div style="float: right;">
							<label for="samebox">회원정보와 동일</label>
							<input id="samebox" type="checkbox" />
						</div>
						<div style="clear: both;"></div>
						<div class="form-group">
							<label for="orderName">주문자명</label>
							<input type="text" class="form-control form-lg" id="orderName" name="orderName"/>
						</div>
						
						<div class="form-group">
							<label for="orderTel">주문자 연락처</label>
							<input type="tel" class="form-control form-lg" id="orderTel" name="orderTel"/>
						</div>
						<sec:authorize access="isAnonymous()">
							<div class="form-group">
								<label for="password">비밀번호 (비회원 확인용)</label>
								<input type="password" class="form-control form-lg" id="password" name="password"/>
							</div>
						</sec:authorize>
					</div>
					
					<div id="input-box">
						<div style="float: left;">
							<h1>수령자 정보</h1>
						</div>
						<div style="float: right;">
							<label for="samebox2">회원정보와 동일</label>
							<input id="samebox2" type="checkbox" />
						</div>
						<div style="clear: both;"></div>
						<div class="form-group">
							<label for="receiverName">수령자명</label>
							<input type="text" class="form-control form-lg" id="receiverName" name="receiverName"/>
						</div>
						<div class="form-group">
							<label for="receiverTel1">수령인 연락처1</label>
							<input type="text" class="form-control form-lg" id="receiverTel1" name="receiverTel1"/>
						</div>
						<div class="form-group">
							<label for="receiverTel2">수령인 연락처2</label>
							<input type="text" class="form-control form-lg" id="receiverTel2" name="receiverTel2"/>
						</div>

						
					</div>
					
					
					<div id="input-box">
						<div style="float: left;">
							<h1>배송지 정보</h1>
						</div>
						
						<div style="clear: both;"></div>
						<div class="form-group">
							<label for="receiverPostcode">수령지 우편번호</label>
							<div style="clear: both;">
								<input type="text" readonly required class="form-control form-lg" id="receiverPostcode" name="receiverPostcode" style="float: left; width: 320px;"/>
								<input type="button" class="btn btn-gradient-primary btn-sm " value="검색" onclick="openZipSearch()" style="float: right;"/>
							</div>
							<div style="clear: both;"></div>
							<label for="addr1">주소</label>
							<input type="text" required class="form-control form-lg" id="addr1" name="addr1" readonly="readonly"/>							
							<label for="addr2">상세</label>
							<input type="text" required class="form-control form-lg" id="addr2" name="addr2" />
							
							<label for="message">배송 메세지 (기사님에게)</label>
							<input type="text" class="form-control form-lg" id="message" name="message" />
							
						</div>


						
					</div>
					
					
					<div style="clear: both;"></div>
					
					<hr />
					<div class="total-price">
						<span>총 구매금액: <fmt:formatNumber value="${totalPrice }" type="currency"></fmt:formatNumber></span>
					</div>
					<hr />


					<div class="btns-box">
						
						<button type="submit" class="btn btn-gradient-primary btn-bg">주문하기</button>
						
					</div>

</form>


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
