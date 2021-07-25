<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
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
.content-wrapper {
	background-color: #ECF0F7 !important;
	color: #686B72 !important;
}

.content-wrapper a {
	cursor: pointer;
}

.content-wrapper span, .content-wrapper a {
	color: #686B72 !important;
}

.form-group>input {
	border: 1px solid gray;
}

.form-group {
	width: 300px;
	/* float: left; */
}

.form-group>input {
	margin: 0 10px;
}

.date-form {
	width: 700px;
	border: 0px solid red;
}

.date-form>input {
	width: 300px;
	float: left;
	padding: 10px;
	margin: 0px 10px;
}

.date-form>input:last-child {
	float: left;
}

.paging>a {
	color: cornflowerblue !important;
}

.disable-link {
	background-color: gray !important;
	color: white !important;
	cursor: default !important;
}

.form-bottom-gap {
	padding-bottom: 20px;
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
						<h1 class=" h1">쿠폰 생성</h1>
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#">Admin</a></li>
								<li class="breadcrumb-item active" aria-current="page">쿠폰
									생성</li>
							</ol>
						</nav>
					</div>

                    <form action="${pageContext.servletContext.contextPath }/admin/coupon/create" method="post">
						<div class="col-lg-12 grid-margin stretch-card">
							<div class="card">
	
								<div class="card-body">
									<h4 class="card-title">쿠폰 정보</h4>
									<div style="padding: 20px;" id="addbox"
										class="shadow-lg p-3 mb-5 bg-white rounded">
	
										<div class="lineinput form-bottom-gap">
											<span class="inputTitle">- 쿠폰명 :</span><input id="name"
												name="name" type="text" class="form-control inputsize"
												placeholder="필수사항" required="required">
										</div>
										<div style="clear: both;"></div>
	
										<div id="categoryBox lineinput" style="margin-bottom: 20px;">
											<span class="inputTitle">- 쿠폰 할인 타입 :</span> <select
												name="sale_type" id="sale_type" class="form-control"
												style="width: 150px;">
												<option value="P">퍼센트 할인</option>
												<option value="W">금액 할인</option>
											</select>
										</div>
	
										<div style="clear: both;"></div>
	
	
										<div class="lineinput form-bottom-gap">
											<span class="inputTitle">- 쿠폰 할인값 :</span><input
												id="sale_value" name="sale_value" type="text"
												class="form-control inputsize" placeholder="필수사항"
												required="required">
										</div>
	
										<div style="clear: both;"></div>
	
										<div class="lineinput option-box">
											<button type="submit" id="couponAddBtn"
												class="btn-gradient-primary btn-sm btn-rounded">Add
											</button>
										</div>
	
									</div>
								</div>
							</div>
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
