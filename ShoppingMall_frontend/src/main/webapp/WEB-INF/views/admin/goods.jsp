<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Admin Goods</title>


<jsp:include page="/includes/asset.jsp"></jsp:include>
<%-- <jsp:include page="/includes/notify_asset.jsp"></jsp:include>   --%>
<jsp:include page="/includes/plugincss.jsp"></jsp:include>



<style type="text/css">


</style>
<script type="text/javascript">
	
</script>

<style type="text/css">

	.content-wrapper{
	
		background-color: #ECF0F7 !important;
		color: #686B72 !important;
	}
	
	.content-wrapper a{
		cursor: pointer;
	}
	
	.content-wrapper span, .content-wrapper a{
		color: #686B72 !important;
	}
	
	.paging > a{
		color: cornflowerblue !important;
	}
	
	.addbox{
		width: 200px;
		margin: 20px;
	}
	
	.disable-link{
		background-color: gray !important;
		color: white !important;
		cursor: default !important; 
	}
	
		
</style>

</head>

<body>

	<div class="container-scroller">
		<!-- partial:../../partials/_navbar.html -->
		<jsp:include page="/WEB-INF/views/includes/header_nav.jsp"></jsp:include>

		<!-- partial -->
		<div class="container-fluid page-body-wrapper">
			<!-- partial:../../partials/_sidebar.html -->
			<jsp:include page="/WEB-INF/views/includes/adminsidebar.jsp"></jsp:include> 
			<!-- partial -->
			<div class="main-panel">


				<!-- content-wrapper start -->
				<div class="content-wrapper">
					<div class="page-header">
						<h1 class=" h1">상품 목록</h1>
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#">Admin</a></li>
								<li class="breadcrumb-item active" aria-current="page">상품 목록</li>
							</ol>
						</nav>
					</div>
					<!-- 이곳에 내용을 작성  -->



					<div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                  <div class="addbox">
                  	<button class="btn-rounded btn-lg btn-gradient-primary" onclick="location.href='${pageContext.servletContext.contextPath}/admin/goods/add';">
                  	<i class="mdi mdi-playlist-plus"></i> 상품 등록</button>
                  </div>
                  <div class="card-body">
                    <h4 class="card-title">상품 정보</h4>
                    
                    <table class="table table-striped">
                      <thead>
                        <tr>
                          <th> Goods-no </th>
                          <th> 상품 이미지 </th>
                          <th> 상품명 </th>
                          <th> 판매가격 </th>
                          <th> 진열 여부 </th>
                          <th> 판매 여부 </th>
                          <th> 등록일 </th>
                          <th> 상품 삭제</th>
                        </tr>
                      </thead>
                      <tbody>
                        
                        <c:forEach items="${goodsList }" var="goodsVo">
                        <tr>
                          <td class="py-1">
                            ${goodsVo.no }
                          </td>
                          <td>
                          <img alt="상품 썸네일" 
                          src="${pageContext.servletContext.contextPath }/images/${goodsVo.thumbnail}">  
                          </td>
                          <td>${goodsVo.name }  </td>
                          <td>${goodsVo.seillingPrice }  </td>
                          <td>${goodsVo.displayStatus }  </td>
                          <td>${goodsVo.seillingStatus }  </td>
                          <td>${fn:substring(goodsVo.regdate,0,19) }  </td>
                  
                       
                          <td>
                          	<a class="btn-rounded btn-lg" 
                          	onclick="if(confirm('${goodsVo.name}(${goodsVo.no }) 상품을 정말 삭제하시겠습니까?')){ location.href='${pageContext.servletContext.contextPath }/admin/goodsdelete/${goodsVo.no }'}">
                          	<i class="mdi mdi-delete" style="color: red;">
                          	</i></a>
                          </td>

                        </tr>
                        </c:forEach>
                        
                        
                      </tbody>
                    </table>
                    
            <!-- pager 추가 -->
            <div class="pager">
               <ul>

                  <li><a href="${pageContext.servletContext.contextPath }/admin/goods?currentPage=${paging.prevPage}">◀</a></li>
					
					<c:forEach 
					begin="${paging.nowStart }" 
					end="${paging.nowEnd }"
					var="index"
					>
					<c:if test="${paging.currentPage eq index }">
						<li class="selected">
						<a href="${pageContext.servletContext.contextPath}/admin/goods?currentPage=${index }">${index }</a>
						</li>
					</c:if>
					<c:if test="${paging.currentPage ne index and paging.endPage >= index}">
						<li class="paging">
						<a href="${pageContext.servletContext.contextPath}/admin/goods?currentPage=${index }">${index }</a>
						</li>
					</c:if>
					<c:if test="${paging.endPage < index }">
						<li class="paging">
					
						<a 
						class="disable-link"
						href="${pageContext.servletContext.contextPath}/admin/goods?currentPage=${paging.endPage }">${index }</a>
						</li>
					</c:if>
					</c:forEach>
            
                     <!-- <li class="selected"><a>1</a></li>
                     <li class="paging"><a>2</a></li>
                     <li class="paging"><a>3</a></li>
                     <li class="paging"><a>4</a></li>
                     <li class="paging"><a>5</a></li> -->
                     

                  <li><a href="${pageContext.servletContext.contextPath }/admin/goods?currentPage=${paging.nextPage}">▶</a></li>
                 
               </ul>
            </div>
            <!-- pager 추가 -->
            
            
                  </div>
                </div>
              </div>













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
	<jsp:include page="/includes/injectjs_asset.jsp"></jsp:include>
	<!-- endinject -->
	<!-- Custom js for this page-->
	<!-- End custom js for this page-->



</body>

</html>
