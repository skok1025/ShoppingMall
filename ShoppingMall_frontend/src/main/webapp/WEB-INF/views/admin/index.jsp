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
		
</style>

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
						<h1 class=" h1">회원 목록</h1>
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#">Admin</a></li>
								<li class="breadcrumb-item active" aria-current="page">회원 목록</li>
							</ol>
						</nav>
					</div>
					<!-- 이곳에 내용을 작성  -->



					<div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
							<form action="${pageContext.servletContext.contextPath }/admin">	
								<div class="form-group">
									<label for="id">검색할 아이디: </label>
									<input id="id" name="id"  placeholder="검색할 아이디"
										type="text" class="form-control form-control-lg" value="" />
								</div>
								<div class="form-group date-form" >
									<label for="orderdateStart">검색할 주문날짜</label>
									<div style="clear: both;"></div>
									<input id="orderdateStart" name="orderdateStart"  placeholder="주문시작 날짜"
										type="date" class="form-control form-control-lg" value="" /> 
								<div style="float: left;"><span>~</span></div>
									<input id="orderdateEnd" name="orderdateEnd"  placeholder="주문끝 날짜"
										type="date" class="form-control form-control-lg" value="" />
								</div>
								<div style="clear: both; margin-bottom: 30px;"></div>			
								<button class="btn-gradient-dark btn-rounded btn-lg" type="submit">검색</button>
							</form>
				</div>
                  <div class="card-body">
                    <h4 class="card-title">회원 정보</h4>
                    
                    <table class="table table-striped">
                      <thead>
                        <tr>
                          <th> User-no </th>
                          <th> User-name (연락처) </th>
                          <th> Gender </th>
                          <th> Birth Date </th>
                          <th> ID </th>
                          <th> Email </th>
                          <th> Address </th>
                          <th> 최근 주문 날짜 </th>
                          <th> 회원 삭제</th>
                        </tr>
                      </thead>
                      <tbody>
                        
                        <c:forEach items="${memberList }" var="memberVo">
                        <tr>
                          <td class="py-1">
                            ${memberVo.no }
                          </td>
                          <td>${memberVo.name} ( ${memberVo.tel } )  </td>
                          <td>${memberVo.gender }  </td>
                          <td>${memberVo.birthDate }  </td>
                          <td>${memberVo.id }  </td>
                          <td>${memberVo.email }  </td>
                          <td>${memberVo.address }  </td>
                  
                          <td>
                          <c:if test="${memberVo.currentOrderDate ne null }">	
                          	${ fn:substring(memberVo.currentOrderDate , 0, 19) }  
                          </c:if>
                          <c:if test="${memberVo.currentOrderDate eq null }">	
                          	<span style="color: tomato !important; ">주문한 기록이 없습니다.</span>  
                          </c:if>
                          </td>
                          
                          <td>
                          	<a class="btn-rounded btn-lg" 
                          	onclick="if(confirm('${memberVo.name}(${memberVo.no }) 회원을 정말 삭제하시겠습니까?')){ location.href='${pageContext.servletContext.contextPath }/admin/memberdelete/${memberVo.no }'}">
                          	<i class="mdi mdi-delete" style="color: red;">
                          	</i></a>
                          </td>

                        </tr>
                        </c:forEach>
                      </tbody>
                    </table>
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
