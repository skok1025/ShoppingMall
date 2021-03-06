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

.no-images {
    height: 50% !important;
}



</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/ejs.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						
						var isEnd = false;
						var listTemplate = new EJS({
							url: '${pageContext.request.contextPath }/assets/js/ejs-templates/list-item.ejs'
						});
						
						
						var fetchList = function(){
							if(isEnd){
								return;
							}
							
							var lastNo = $('#goods-container .goodsitem').last().data('no') || 0;
							
							$.ajax({
								url: "${pageContext.request.contextPath }/api/list/" + lastNo,
								type: "get",
								//contentType: "application/json" //post 방식으로  JSON Type으로 데이터를 보낼 때
								dataType: "json",
								data: "",
								success: function(response){
									
									console.log(response);
									
									// detect end
									if(response.length == 0){
										isEnd = true;
										$("#btn-next").prop("disabled", true);
										return;
									}					
									
									// rendering
									//var html = listTemplate.render(response);
									//var html ="<div>test</div>"
									
									$.each(response, function(i,item){
										
										var imageDiv="";
										if(item.thumbnail != null){
											imageDiv = "<img class='card-img-top img-fluid' src='${pageContext.servletContext.contextPath}/images/"+item.thumbnail+"' alt='Card image cap'>";
										} else{
											imageDiv = "<img class='card-img-top img-fluid' src='${pageContext.servletContext.contextPath}/assets/images/noimage.jpg' alt='Card image cap'>";
										}
										//console.log(item.thumbnail);
										console.log(imageDiv);
										
										var html = 
											"<div data-no='"+item.no+"' class='goodsitem col-md-3' onclick='location.href=\"${pageContext.servletContext.contextPath}/goods/view/"+item.no+"\"'>"+
											imageDiv+
											"<div style='clear: both;'></div>"+
											"<span class='goodsname'>"+item.name+"</span>"+
											"<div style='clear: both; margin: 5px 0px;'></div>"+
											"<span class='price'>"+item.seillingPrice+" 원</span>"+
											"<div class='goodsdetail-box' style='border: 0px solid red'>"+item.detail+"</div>"+
											"<div class='category-box'><span>"+item.bigcategoryName+" >"+item.smallcategoryName+"</span></div>"+
											"</div>";
											

										$("#goods-container").append(html);
									})
									//$("#goods-container").append(html);
								},
								error: function(jqXHR, status, e){
									console.error(status + ":" + e);
								}
							});
						}
						
						
						$(window).scroll(function(){
							var $window = $(this);
							var scrollTop = $window.scrollTop();
							var windowHeight = $window.height();
							var documentHeight = $(document).height();
							if( scrollTop + windowHeight + 5> documentHeight ){
								fetchList();
							}
						});
						
						
						$("#btn-next").click(function(){
							fetchList();
						});
 
						// 최초 리스트 가져오기
						fetchList();
						
						///////////////////////////////////////////////////////////
						var cardCnt = $(".card").length;
						// 4 이하이면 슬라이드 x
						if (cardCnt <= 4) {
							$("#myCarousel")
							.on(
									"slide.bs.carousel",
									function(e) {
										e.preventDefault();
									}
							   );
						} else {
							$("#myCarousel")
							.on(
									"slide.bs.carousel",
									function(e) {
										var $e = $(e.relatedTarget);
										var idx = $e.index();
										var itemsPerSlide = 4;
										var totalItems = $(".carousel-item").length;

										if (idx >= totalItems
												- (itemsPerSlide - 1)) {
											var it = itemsPerSlide
													- (totalItems - idx);
											for (var i = 0; i < it; i++) {
												// append slides to end
												if (e.direction == "left") {
													$(".carousel-item")
															.eq(i)
															.appendTo(
																	".carousel-inner");
												} else {
													$(".carousel-item")
															.eq(0)
															.appendTo(
																	$(this)
																			.find(
																					".carousel-inner"));
												}
											}
										}
									});
						}
						
					});
</script>
<script type="text/javascript">
	
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



					<div class="container-fluid">
						<h1 class="text-center mb-3">${maindisplayCategoryVo.name }</h1>
						<div id="myCarousel" class="carousel slide" data-ride="carousel">
							<div class="carousel-inner row w-100 mx-auto">


								<c:forEach items="${mainDisplayList }" var="mainDisplayVo"
									varStatus="status">
									<div
										class="carousel-item col-md-3 <c:if test='${status.index==0}'>active</c:if>">
										<div class="card" onclick="location.href='${pageContext.servletContext.contextPath}/goods/view/${mainDisplayVo.no}'">
											<c:if test="${mainDisplayVo.thumbnail != ''}">
												<img class="card-img-top img-fluid"
													src="${pageContext.servletContext.contextPath}/images/${mainDisplayVo.thumbnail}"
													alt="Card image cap">
											</c:if>

											<c:if test="${mainDisplayVo.thumbnail eq null }">
												<img class="no-images card-img-top img-fluid"
													src="${pageContext.servletContext.contextPath}/assets/images/noimage.jpg"
													alt="Card image cap">
											</c:if>
											<div class="card-body">
												<h4 class="card-title">${mainDisplayVo.name}
													(
													<fmt:formatNumber value="${mainDisplayVo.seillingPrice}"
														type="currency"></fmt:formatNumber>
													)
												</h4>
												<p class="card-text detailtext">${mainDisplayVo.detail}</p>
												<p class="card-text">
													<small class="text-muted">${mainDisplayVo.bigcategoryName}
														> ${mainDisplayVo.smallcategoryName}</small>
												</p>
											</div>
										</div>
									</div>

								</c:forEach>


							</div>
							<a class="carousel-control-prev" href="#myCarousel" role="button"
								data-slide="prev"> <span class="carousel-control-prev-icon"
								aria-hidden="true"></span> <span class="sr-only">Previous</span>
							</a> <a class="carousel-control-next" href="#myCarousel"
								role="button" data-slide="next"> <span
								class="carousel-control-next-icon" aria-hidden="true"></span> <span
								class="sr-only">Next</span>
							</a>
						</div>
					</div>

					<h1>상품 리스트</h1>

					<div id="goods-container" class=" row mx-auto">

						<%-- 
						<div class="goodsitem col-md-3">
							<img alt="상품 이미지" src="${pageContext.servletContext.contextPath}/assets/images/sample.jpg">
							<div style="clear:both;"></div>
							<span class="goodsname">베이직 심플 슬림핏 셔츠</span>
							<div style="clear: both; margin: 5px 0px;"></div>
							<span class="price">15,000원</span>
							<div class="goodsdetail-box">
								Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi officia illum voluptatem quisquam laborum quo magni laboriosam fugiat sed error dolorum iure commodi a quas beatae magnam necessitatibus incidunt velit.
							</div>
						</div>
						 --%>
<%-- 
						<c:forEach items="${goodslist}" var="goods">
							<div data-no="${goods.no}" class="goodsitem col-md-3" onclick="location.href='${pageContext.servletContext.contextPath}/goods/view/${goods.no}'">
								
								<c:if test="${goods.thumbnail ne null}">
									<img class="card-img-top img-fluid"
										src="${pageContext.servletContext.contextPath}/images/${goods.thumbnail}"
										alt="Card image cap">
								</c:if>

								<c:if test="${goods.thumbnail eq null }">
									<img class="card-img-top img-fluid"
										src="${pageContext.servletContext.contextPath}/assets/images/noimage.jpg"
										alt="Card image cap">
								</c:if>

								<div style="clear: both;"></div>
								<span class="goodsname">${goods.name}</span>
								<div style="clear: both; margin: 5px 0px;"></div>
								<span class="price"><fmt:formatNumber
										value="${goods.seillingPrice}" type="currency"></fmt:formatNumber></span>
								<div class="goodsdetail-box" style="border: 0px solid red">
									${goods.detail}</div>
								<div class="category-box">

									<span>${goods.bigcategoryName} >
										${goods.smallcategoryName}</span>

								</div>

							</div>
						</c:forEach>

 --%>
 
 
					</div>
					<div style="text-align: center;">
 						<button class="btn btn-gradient-primary btn-sm" id="btn-next"><i class="mdi mdi-library-plus"></i>상품 더보기</button>
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
	<jsp:include page="/includes/injectjs_asset.jsp"></jsp:include>
	<!-- endinject -->
	<!-- Custom js for this page-->
	<!-- End custom js for this page-->



</body>

</html>
