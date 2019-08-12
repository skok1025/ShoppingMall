<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	#goods-container{
		border:0px solid black;
		width: 95%;
	}
	
	#goods-container > .goodsitem{
		border: 0px solid blue;
		
		
		height:550px;
		padding: 0 0.75%;
		cursor: pointer;
		
	}
	
	#goods-container > .goodsitem > img{
		height:400px;
		width:100%;
		margin: 10px 0px;
	}
	
	#goods-container > .goodsitem > span{
		font-size: 12px !important;
		font-family: BMDOHYEON;
	}
	
	#goods-container > .goodsitem > .goodsdetail-box{
		overflow:hidden;
		height:50px;
		margin-top:10px;
		font-size: 12px !important;
		color: #999;
	}
	
	
	#goods-container > .goodsitem > .category-box span{
		font-style: italic;
	}
	
	.detailtext{
		height: 50px;
	}
	
	.card{
		cursor: pointer;
	}
	
	.paging > a{
		color: cornflowerblue !important;
	}
	
	.disable-link{
		background-color: gray !important;
		color: white !important;
		cursor: default !important; 
	}
	
	

</style>
<script type="text/javascript">
$(document).ready(function() {
	  $("#myCarousel").on("slide.bs.carousel", function(e) {
	    var $e = $(e.relatedTarget);
	    var idx = $e.index();
	    var itemsPerSlide = 4;
	    var totalItems = $(".carousel-item").length;

	    if (idx >= totalItems - (itemsPerSlide - 1)) {
	      var it = itemsPerSlide - (totalItems - idx);
	      for (var i = 0; i < it; i++) {
	        // append slides to end
	        if (e.direction == "left") {
	          $(".carousel-item")
	            .eq(i)
	            .appendTo(".carousel-inner");
	        } else {
	          $(".carousel-item")
	            .eq(0)
	            .appendTo($(this).find(".carousel-inner"));
	        }
	      }
	    }
	  });
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
					<div class="page-header">
						<h1 class=" h1">${categoryNames.smallcategoryName }</h1>
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#">${categoryNames.bigcategoryName }</a></li>
								<li class="breadcrumb-item active" aria-current="page">${categoryNames.smallcategoryName }</li>
							</ol>
						</nav>
					</div>
					<!-- 이곳에 내용을 작성  -->

						
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
						<c:if test="${empty goodslist}"> 
						<span>진열된 상품이 없습니다.</span>
						</c:if>
						<c:forEach items="${goodslist}" var="goods">
							<div class="goodsitem col-md-3" onclick="location.href='${pageContext.servletContext.contextPath}/goods/view/${goods.no}'">
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
								
								<div style="clear:both;"></div>
								<span class="goodsname">${goods.name}</span>
								<div style="clear: both; margin: 5px 0px;"></div>
								<span class="price"><fmt:formatNumber value="${goods.seillingPrice}" type="currency"></fmt:formatNumber></span>
								<div class="goodsdetail-box" style="border: 0px solid red">
									${goods.detail}
								</div>
								<div class="category-box">
									
									<span>${goods.bigcategoryName} > ${goods.smallcategoryName}</span>
									
								</div>
								
							</div>
						</c:forEach>
						
					
					</div>


					        
            <!-- pager 추가 -->
            <div class="pager">
               <ul>

                  <li><a href="${pageContext.servletContext.contextPath }/goods/category/${smallcategoryNo }?currentPage=${paging.prevPage}">◀</a></li>
					
					<c:forEach 
					begin="${paging.nowStart }" 
					end="${paging.nowEnd }"
					var="index"
					>
					<c:if test="${paging.currentPage eq index }">
						<li class="selected">
						<a href="${pageContext.servletContext.contextPath}/goods/category/${smallcategoryNo }?currentPage=${index }">${index }</a>
						</li>
					</c:if>
					<c:if test="${paging.currentPage ne index and paging.endPage >= index}">
						<li class="paging">
						<a href="${pageContext.servletContext.contextPath}/goods/category/${smallcategoryNo }?currentPage=${index }">${index }</a>
						</li>
					</c:if>
					<c:if test="${paging.endPage < index }">
						<li class="paging">
					
						<a 
						class="disable-link"
						href="${pageContext.servletContext.contextPath}/goods/category/${smallcategoryNo }?currentPage=${paging.endPage }">${index }</a>
						</li>
					</c:if>
					</c:forEach>
            
                     <!-- <li class="selected"><a>1</a></li>
                     <li class="paging"><a>2</a></li>
                     <li class="paging"><a>3</a></li>
                     <li class="paging"><a>4</a></li>
                     <li class="paging"><a>5</a></li> -->
                     

                  <li><a href="${pageContext.servletContext.contextPath }/goods/category/${smallcategoryNo }?currentPage=${paging.nextPage}">▶</a></li>
                 
               </ul>
            </div>
            <!-- pager 추가 -->
            








				</div>
				<!-- content-wrapper ends -->
				
				
				
				<!-- partial:../../partials/_footer.html -->
				<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include> -
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
