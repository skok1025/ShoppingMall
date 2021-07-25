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
<title>Admin Main</title>


<jsp:include page="/includes/asset.jsp"></jsp:include>
<%-- <jsp:include page="/includes/notify_asset.jsp"></jsp:include>   --%>
<jsp:include page="/includes/plugincss.jsp"></jsp:include>



<style type="text/css">


</style>
<script type="text/javascript">
	if('${param.issue_request}' == 'yes') {
		alert("쿠폰 전체발급 요청완료되었습니다. 전체 발급 결과는 쿠폰 상세보기 탭에서 확인이 가능합니다.");
	}
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
	
	.form-group > input{
		border:1px solid gray;
	}
	
	.form-group{
		width: 300px;
		/* float: left; */
	}
	
	.form-group > input{
		margin: 0 10px;
	}
	
	.date-form{
		width:700px;
		border: 0px solid red;
	}
	
	.date-form > input{
		width:300px;
		float:left;
		padding: 10px;
		margin:0px 10px; 
	}
	
	.date-form > input:last-child{
		float: left; 
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
						<h1 class=" h1">쿠폰 목록</h1>
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#">Admin</a></li>
								<li class="breadcrumb-item active" aria-current="page">쿠폰 목록</li>
							</ol>
						</nav>
					</div>
					<!-- 이곳에 내용을 작성  -->



					<div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">			
					<button class="btn-gradient-dark btn-rounded btn-lg" type="submit" onclick="location.href='${pageContext.servletContext.contextPath }/admin/coupon/create'">쿠폰생성</button>
				  </div>
                  <div class="card-body">
                    <h4 class="card-title">쿠폰 정보</h4>
                    
                    <table class="table table-striped">
                      <thead>
                        <tr>
                          <th> 번호 </th>
                          <th> 쿠폰명 </th>
                          <th> 할인정보  </th>
                          <th> 발급 </th>
                          <th> 상세보기 </th>
                        </tr>
                      </thead>
                      <tbody>
                        
                        <c:forEach items="${couponInfoList }" var="vo">
                        <tr>
                          <td class="py-1">${vo.info_no }</td>
                          <td>${vo.name }  </td>
                          <td>
                          	${vo.sale_value } 
                          	<c:if test='${vo.sale_type eq "P"}'>%</c:if> 
                          	<c:if test='${vo.sale_type eq "W"}'>원</c:if>  
                          	할인
                          </td>
                          <td>  
                          <form id="issue_form" action="${pageContext.servletContext.contextPath }/admin/coupon/issue" method="post">
                              <button class="btn-gradient-dark btn-rounded btn-sm" type="button" 
                          onclick="if(confirm('정말 전체회원 발급을 진행하시겠습니까?')) document.getElementById('issue_form').submit();">
                                                    전체회원 쿠폰발급</button> 
                              <input type="hidden" name="info_no" value="${vo.info_no }">
                              <input type="hidden" name="name" value="${vo.name }">
                              <input type="hidden" name="sale_type" value="${vo.sale_type }">
                              <input type="hidden" name="sale_value" value="${vo.sale_value }">
                          </form>
                         
                          </td>
                          <td> 구현예정  </td>
                        </tr>
                        </c:forEach>
                
                  
                      </tbody>
                    </table>
                    
                    
                    <!-- pager 추가 -->
            <div class="pager">
               <ul>

                  <li><a href="${pageContext.servletContext.contextPath }/admin?currentPage=${paging.prevPage}">◀</a></li>
					
					<c:forEach 
					begin="${paging.nowStart }" 
					end="${paging.nowEnd }"
					var="index"
					>
					<c:if test="${paging.currentPage eq index }">
						<li class="selected">
						<a href="${pageContext.servletContext.contextPath}/admin?currentPage=${index }">${index }</a>
						</li>
					</c:if>
					<c:if test="${paging.currentPage ne index and paging.endPage >= index}">
						<li class="paging">
						<a href="${pageContext.servletContext.contextPath}/admin?currentPage=${index }">${index }</a>
						</li>
					</c:if>
					<c:if test="${paging.endPage < index }">
						<li class="paging">
					
						<a 
						class="disable-link"
						href="${pageContext.servletContext.contextPath}/admin?currentPage=${paging.endPage }">${index }</a>
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
