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

.add_sm_category {
	width: 87%;
	display: inline;
}

.btn-icon {
    zoom:0.6;
}

.add_sm_category_box {
    margin-top: 10px;
}

/* 현재 카테고리 리스트 */

.big_category {
    font-size: 1.5em;
    margin-top: 20px;
}

.small_category {
	padding-left: 50px;
	width: 230px;
	border: 1px;
}


/* 점 없애기  */
ol{
   list-style:none;
}

.sm_category_delete_box{
    float: right;
    
}

.big_category_button_box > * {
	padding: 2px !important;
}

.category-add-form {
    width: 120px;
    margin: 10px auto;
}



</style>

<script type="text/javascript">

<c:if test='${param.addsuccess eq "yes"}'>
	alert("카테고리가 추가되었습니다.");
</c:if>
   

    $(document).ready(function() {
    	// 2차 카테고리 추가 버튼
    	$("#sm-category_add").click(function(e){
    		e.preventDefault();
    		var sm_category_html = '<div class="add_sm_category_box">' +
				'<input id="small_category" name="smallCategoryName[]" class="form-control form-control-sm add_sm_category" type="text" />' +
					'<button type="button" class="btn btn-gradient-danger btn-rounded btn-icon sm-category-del">' +
                        '<i class="mdi mdi-minus"></i>' +
                    '</button>' +
                '</div>';
            $("#sm-category-list").append(sm_category_html);
    	});
    	
    	$(".btn-add-category").click(function(e){
    		var no = $(this).data('no');
    		var big_category_box = $(this).parent().parent();
    		
    		var input_category = "<input type='text' class='form-control category-add-form' placeholder='카테고리입력' data-no='" + no +"'>";
    		big_category_box.append(input_category);
    	    	
    	});
    	
    	// 2차 카테고리 등록 form
    	$(document).on("keydown",".category-add-form",function(e){
    		
    		// 엔터버튼 누르면 카테고리 추가
    		if (e.keyCode == 13) {
    			var no = $(this).data('no');
    			var name = $(this).val();
    			
    			$.ajax({
					url: "${pageContext.request.contextPath }/admin/api/smallcategory?no=" + no +"&name=" + name,
					type: "get",
					//contentType: "application/json" //post 방식으로  JSON Type으로 데이터를 보낼 때
					dataType: "json",
					data: "",
					success: function(response){
						location.reload();
					},
					error: function(jqXHR, status, e){
						console.error(status + ":" + e);
					}
				});
			}
    	});
    	
    	// 2차 카테고리 편집 form
    	$(document).on("keydown",".category-edit-form",function(e){
    		
    		// 엔터버튼 누르면 카테고리 추가
    		if (e.keyCode == 13) {
    			var no = $(this).data('no');
    			var name = $(this).val();
    			
    			$.ajax({
					url: "${pageContext.request.contextPath }/admin/api/smallcategory/edit?no=" + no +"&name=" + name,
					type: "get",
					//contentType: "application/json" //post 방식으로  JSON Type으로 데이터를 보낼 때
					dataType: "json",
					data: "",
					success: function(response){
						location.reload();
					},
					error: function(jqXHR, status, e){
						console.error(status + ":" + e);
					}
				});
			}
    	});
  
    	// 2차 카테고리 삭제 버튼
    	$(document).on("click",".sm-category-del",function(){
    		$(this).parent().remove();
    	});
    	
    	$(".small_category").click(function(){
    		if ($(this).children('input').length > 0) return;
    		var input_text = "<input class='form-control category-edit-form' value='" + $(this).data("name") + "' data-no=" + $(this).data("no") + ">";
    		$(this).children().remove();
    		$(this).append(input_text);
    		
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
			<jsp:include page="/WEB-INF/views/includes/adminsidebar.jsp"></jsp:include>
			<!-- partial -->
			<div class="main-panel">


				<!-- content-wrapper start -->
				<div class="content-wrapper">
					<div class="page-header">
						<h1 class=" h1">상품 카테고리 추가</h1>
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#">Admin</a></li>
								<li class="breadcrumb-item active" aria-current="page">상품 카테고리 추가</li>
							</ol>
						</nav>
					</div>
					<!-- 이곳에 내용을 작성  -->


					<div class="col-lg-4 grid-margin stretch-card">
						<div class="card">
							<div class="card-body">
								<h3>현재 카테고리 리스트</h3>
								<hr />
								
								
								    <c:forEach items="${categoryList}" var="categoryItem">
								    <div id="big_category_box-{$categoryItem.no}">
								        <span class="big_category">${categoryItem.name}</span>
								        <div class="big_category_button_box">
									                <a class="btn-rounded btn-lg" onclick="if(confirm('${categoryItem.name} 카테고리를 정말 삭제하시겠습니까? (하위카테고리 포함)')){ location.href='${pageContext.servletContext.contextPath }/admin/bigcategory/delete/${categoryItem.no }'}">
					                          	        <i class="mdi mdi-delete" style="color: red;"></i>
					                          	    </a>
					                          	    <a class="btn-rounded btn-lg btn-add-category" title="카테고리 추가" data-no="${categoryItem.no}">
					                          	        <i class="mdi mdi-note-plus" style="color: blue;"></i>
					                          	    </a>
									    </div>
								        <c:forEach items="${categoryItem.smallCategoryList}" var="smCategoryItem">
								            <div class="small_category" data-no="${smCategoryItem.no}" data-name="${smCategoryItem.name}">
								                <span>- ${smCategoryItem.name}</span> 
									            <div class="sm_category_delete_box">
									                <a class="btn-rounded btn-lg" onclick="if(confirm('${categoryItem.name} > ${smCategoryItem.name} 카테고리를 정말 삭제하시겠습니까?')){ location.href='${pageContext.servletContext.contextPath }/admin/smallcategory/delete/${smCategoryItem.no }'}">
					                          	        <i class="mdi mdi-delete" style="color: red;"></i>
					                          	    </a>
									            </div>
									            <div style="clear: both;"></div>
				                          	</div>
								           
								        </c:forEach>
					                </div>
								    </c:forEach>
								
								
							</div>
							<div class="card-body"></div>
						</div>
					</div>
					
					<form action="${pageContext.servletContext.contextPath }/admin/addcategory" method="post">
					
					<div class="col-lg-4 grid-margin stretch-card">
						<div class="card">
							<div class="card-body">
							    <label for="big_category">추가할 1차 카테고리명</label>
								<input id="big_category" name="bigCategoryName" class="form-control form-control-lg" type="text" />

								
							</div>
							<div class="card-body"></div>
						</div>
					</div>
					
					
					<div class="col-lg-4 grid-margin stretch-card">
						<div class="card">
							<div id="sm-category-list" class="card-body">
								<label for="small_category">추가할 2차 카테고리명</label> 
								<button id="sm-category_add" type="button" class="btn btn-gradient-primary btn-rounded btn-icon ">
                                    <i class="mdi mdi-plus"></i>
                                </button>
								<input id="small_category" name="smallCategoryName[]" class="form-control form-control-sm" type="text" placeholder="미입력시, (미분류)"/>
								
							</div>
							
						</div>
					</div>
					
					<div class="col-lg-8 grid-margin stretch-card">
						<div class="card">
							<button type="submit" class="btn btn-gradient-primary ">카테고리 추가하기</button>
							
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
