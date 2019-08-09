<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<title>Template Site</title>


<jsp:include page="/includes/asset.jsp"></jsp:include>
<%-- <jsp:include page="/includes/notify_asset.jsp"></jsp:include>   --%>
<jsp:include page="/includes/plugincss.jsp"></jsp:include>



<style type="text/css">


.content-wrapper a {
	cursor: pointer;
}

 .content-wrapper a {
	color: #686B72 !important;
}

.paging>a {
	color: cornflowerblue !important;
}

.addbox {
	width: 200px;
	margin: 20px;
}

/* Add */
select{
	font-size: .9em;
}
option{
	font-size: 1em;
}
.inputsize {
	width: 250px;
}
.inputTitle {
	font-size: 2em;
}
.form-control {
	display: inline;
}
#topbox {
	margin-top: 20px;
	height: 700px;
}
#goodsimgs {
	padding: 20px;
	width: 600px;
	height: 700px;
	border: 0px solid;
	float: left;
}
#addbox {
	width: 530px;
	height: 600px;
	border: 0px solid;
	float: left;
}
#bottombox {
	width: 1130px;
	height: 500px;
	border: 0px solid;
}
#mainImg {
	width: auto;
	height: 500px;
	margin-left: 18px;
}
.subimg {
	width: 159px;
	height: 110px;
}
#smallcategorybox {
	width: 150px;
	height: 200px;
	border: 1px solid;
	float: right;
}
#categoryBox {
	border: 1px solid red;
	height: 200px;
}
.lineinput {
	margin-bottom: 20px;
}
#addimgbtn {
	margin: 0px auto;
}
.imgbox {
	width: 550px;
	height: 380px;
	position: absolute;
	border: 0px solid;
}
.imgbox>img {
	/* z-index: -1; */
	position: absolute;
}
.imgbox>label {
	z-index: 0;
	position: absolute;
	bottom: 0px;
}
#goodssubImgs {
	margin-top: 500px;
	height: 145px;
	width: 550px;
	border: 0px solid;
}
.subimgbox{
	float:left;
	width: 159px;
	height: 112px;
	position: relative;
	border: 0px solid red;
	margin: 10px;
}
.subimgbox>img {
	/* z-index: -1; */
	position: absolute;
}
.subimgbox > label{
	z-index: 0;
	position: absolute;
	bottom: 0px;
}
input[type="file"]{
	display: none;
}
label {
 	display: inline-block;
 	padding: .5em .75em; 
 	color: #999; 
 	font-size: inherit; 
 	line-height: normal; 
 	vertical-align: middle; 
 	background-color: #fdfdfd; 
 	cursor: pointer; 
 	border: 1px solid #ebebeb; 
 	border-bottom-color: #e2e2e2; 
 	border-radius: .25em; 
 }
 
.optionspan{
		padding:5px;
		color: white !important;
}

.optionitem{
	margin: 5px;
	/* background-color: black; */
	width: 200px;
}

.mytext-font{
	font-family: BMDOHYEON;
	font-size: 1.5em;
}

.mydetail{
	margin: 20px;
	padding:20px;
	border: 0px solid red;
	min-height: 100px;
}

i{
	font-size: 1.2em !important;
}

button{
	margin-left: 10px;
}

.header-hr{
	border: .3px solid #333;
}

.small-number-input{
	width: 50px;
	color: black;
	font-size: 1.2em;
}

input[type=number]{
	font-size: 2em;
}

input[type=number]::-webkit-inner-spin-button, 
input[type=number]::-webkit-outer-spin-button {  

   opacity: 1;

}



</style>

<script type="text/javascript">
	$(function(){
		<c:if test="${param.addsuccess == 'yes' }">
		if(confirm("장바구니 등록에 성공하셨습니다. 장바구니로 가시겠습니까?")){
			location.href = "${pageContext.servletContext.contextPath}/basket/view";	
		}
		</c:if>	
		var index = 0;
		$("#optionName").change(function(){
			console.log($(this));
			$("#option-span")
			.append("<input name='goodsBasketList["+index+"].goodsDetailNo' type='hidden' value="+$(this).val()+">"+
					"</input><div class='optionitem badge badge-info badge-pill' "+">"+
					"<span class='optionspan'> 옵션명: "+$(this).find("option[value='" + $(this).val() + "']").text()+"</span>"+
					"<input name='goodsBasketList["+index+"].cnt' class='small-number-input' type='number' min='0' value='1'/></div>"+
					"<div style='clear:both;'></div><hr>");
			index++;
		});

	});

	</script>

</head>

<body>

	<input type="text" min="0" value="1"/>
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
						<h1 class=" h1">${vo.name }</h1>
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#">${vo.bigcategoryName  }</a></li>
								<li class="breadcrumb-item active" aria-current="page">${vo.smallcategoryName  }</li>
							</ol>
						</nav>
					</div>
					<!-- 이곳에 내용을 작성  -->

					
					<div class="col-lg-12 grid-margin stretch-card">
						<div class="card">

							<div class="card-body">

									<span class="inputTitle">상품명: </span> <span class="mytext-font goods-name">${vo.name }</span>

									<div id="topbox">
										<div id="goodsimgs">
											<div class="imgbox">
												<img id="mainImg"
													src="${pageContext.servletContext.contextPath }/images/${mainImage}" />
											</div>

											<div id="goodssubImgs">
												<c:forEach items="${subImageList }" var="subimage">
												
												<div class="subimgbox">
													<img id="subImg1" class="subimg"
														src="${pageContext.servletContext.contextPath }/images/${subimage.image}" /> 
												</div>
											
												</c:forEach>
											</div>


										</div>

										<div style="padding: 20px;" id="addbox"
											class="shadow-lg p-3 mb-5 bg-white rounded">
									
											<div class="lineinput">
												<span class="inputTitle">- 판매가격 :</span> <span class="my-font">${vo.seillingPrice }원</span>
											</div>
											<div style="clear: both;"></div>
											
											
											<div class="lineinput">
												<span class="inputTitle">- 제조업자명 :</span><span class="my-font">${vo.manufacturer }</span>
											</div>
											<div style="clear: both;"></div>
											
											<div class="lineinput">
												<span class="inputTitle">- 공급업자명 :</span><span class="my-font">${vo.supplier}</span>
											</div>
											<div style="clear: both;"></div>
											<div class="lineinput">
												<span class="inputTitle">- 제조일 :</span><span class="my-font">${fn:substring(vo.manufacturingDate,0,10) }</span>
											</div>
											<div style="clear: both;"></div>
											<div class="lineinput">
												<span class="inputTitle">- 원산지명 :</span><span class="my-font">${vo.origin }</span>
											</div>
											<div style="clear: both;"></div>
											
											<div id="categoryBox lineinput" style="margin-bottom: 20px;">
												<span class="inputTitle">- 상품분류 :</span> <span class="my-font">${vo.bigcategoryName }</span> > <span class="my-font">${vo.smallcategoryName }</span>  

											</div>
											<div style="clear: both;"></div>
											
							<form method="post" action=
							<sec:authorize access="isAuthenticated()">"${pageContext.servletContext.contextPath }/basket/user/add" </sec:authorize>
							<sec:authorize access="isAnonymous()">"${pageContext.servletContext.contextPath }/basket/nonuser/add"</sec:authorize>
							>
											<input type="hidden" name="goodsNo" value="${vo.no }"></input>
											<c:if test="${not empty goodsDetailList}">
											<div class="lineinput option-box">
												<span class="inputTitle">- 옵션 :</span>
												<select
													id="optionName" name="optionName"
													class="form-control inputsize" >
													<option>옵션 선택</option>
													<c:forEach items="${goodsDetailList }" var="goodsDetailVo">
														<option value="${goodsDetailVo.no }" data-name="${goodsDetailVo.optionName }">
														${goodsDetailVo.optionName } <%-- (재고수량: ${goodsDetailVo.inventoryQty } 개 ) --%>
														</option>
													</c:forEach>
												</select>
												
												<div id="option-span" class="lineinput">
												<hr class="header-hr">
												</div>
												<input type="hidden" id="optionListTxt" name="optionListTxt" value="" />	
											</div>
											</c:if>
						
											
											<div style="clear: both;"></div>
											
											<div class="lineinput">
												<button class="btn btn-lg btn-gradient-dark">
													Buy Now
												</button>	
												<button type="submit" class="btn btn-md btn-outline-info">
												 <i class="mdi mdi-cart-outline"></i>
												</button>
												
											</div>
						</form>
											<div style="clear: both;"></div>
										</div>
									</div>
									<div style="clear: both;"></div>
									<div id="bottombox">
										<span style="font-size: 3em; padding: 30px;">상품의 상세정보 </span>
										<div class="mydetail shadow-lg mytext-font">
											${vo.detail }
										</div>
									</div>
			
					






							</div>
						</div>
					</div>











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
	<%-- <jsp:include page="/includes/injectjs_asset.jsp"></jsp:include> --%>
	<!-- endinject -->
	<!-- Custom js for this page-->
	<!-- End custom js for this page-->



</body>

</html>
