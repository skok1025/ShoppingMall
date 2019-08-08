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
<title>Admin Goods</title>


<%-- <jsp:include page="/includes/asset.jsp"></jsp:include> --%>
<%-- <jsp:include page="/includes/notify_asset.jsp"></jsp:include>   --%>
<jsp:include page="/includes/plugincss.jsp"></jsp:include>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath }/assets/css/bootstrap/bootstrap-3.3.2.min.css"
	id="bootstrap-css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath }/assets/css/style.css"> 
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery.min.js"></script> 

<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath }/assets/css/plugin/materialdesignicons.min.css">
<link rel="shortcut icon" href="${pageContext.servletContext.contextPath }/assets/images/favicon.png" />

<style type="text/css">

	.option-box > #optionName{
		width: 100px;
	}
	.option-box > #optionQty{
		width: 135px;
	}
	

</style>

<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">


<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
<script src="${pageContext.servletContext.contextPath }/assets/js/bootstrap.bundle.min.js"></script> 

<script type="text/javascript">
$(function() {	
	
	$("#bigcategory").change(function(){
		
		$.ajax({
			type:"get",
			url:"${pageContext.servletContext.contextPath}/admin/goods/getsmallcategory",
			data:"bigCategoryNo="+$("#bigcategory").val(),
			dataType:"json",
			success : function(result){
				console.log(result);
				$("#smallcategory").html("");
				$(result).each(function(index,item){
					var op = "<option value='"+item.no+"'>"+item.name+"</option>";
					$("#smallcategory").append(op);
				});
			},
			error: function(a,b,c){
				console.log(a+b+c);
			}
		});
	});
	
	
	$("#addimgbtn").on("change",handleImgFileSelect);
	$("#addsubimgbtn1").on("change",handleImgFileSelect1);
	$("#addsubimgbtn2").on("change",handleImgFileSelect2);
	$("#addsubimgbtn3").on("change",handleImgFileSelect3);
	
	
	$("#optionAddBtn").click(function(){
		
		var color = new Array("badge-info","badge-primary","badge-success","badge-danger");
		var optionListTxt = $("#optionListTxt").val();
		$("#optionListTxt").val(optionListTxt+","+$("#optionName").val()+";;"+$("#optionQty").val())
		$("#option-span").append("<div class='optionitem badge "+randomItem(color)+" badge-pill'><span class='optionspan'> 옵션명: "+$("#optionName").val()+", 재고수량: "+$("#optionQty").val()+"</span></div>");
	});
	
	
	
	$('#summernote').summernote({
        height: 300,                 // set editor height
        minHeight: null,             // set minimum height of editor
        maxHeight: null,             // set maximum height of editor
        focus: true                  // set focus to editable area after initializing summernote
	});
	
	
});









function handleImgFileSelect(e){
      var files = e.target.files;
      var fileArr = Array.prototype.slice.call(files);
      
      fileArr.forEach(function(f){
         if(!f.type.match("image.*")){
            alert("확장자는 이미지 확장자만 가능합니다.");
            return;
         }
         
         sel_file = f;
         
         var reader = new FileReader();
         reader.onload = function(e){
            $("#mainImg").attr("src",e.target.result);
         }
         reader.readAsDataURL(f);
      });
}

function handleImgFileSelect1(e){
      var files = e.target.files;
      var fileArr = Array.prototype.slice.call(files);
      
      fileArr.forEach(function(f){
         if(!f.type.match("image.*")){
            alert("확장자는 이미지 확장자만 가능합니다.");
            return;
         }
         
         sel_file = f;
         
         var reader = new FileReader();
         reader.onload = function(e){
            $("#subImg1").attr("src",e.target.result);
         }
         reader.readAsDataURL(f);
      });
   }

function handleImgFileSelect2(e){
      var files = e.target.files;
      var fileArr = Array.prototype.slice.call(files);
      
      fileArr.forEach(function(f){
         if(!f.type.match("image.*")){
            alert("확장자는 이미지 확장자만 가능합니다.");
            return;
         }
         
         sel_file = f;
         
         var reader = new FileReader();
         reader.onload = function(e){
            $("#subImg2").attr("src",e.target.result);
         }
         reader.readAsDataURL(f);
      });
   }

function handleImgFileSelect3(e){
      var files = e.target.files;
      var fileArr = Array.prototype.slice.call(files);
      
      fileArr.forEach(function(f){
         if(!f.type.match("image.*")){
            alert("확장자는 이미지 확장자만 가능합니다.");
            return;
         }
         
         sel_file = f;
         
         var reader = new FileReader();
         reader.onload = function(e){
            $("#subImg3").attr("src",e.target.result);
         }
         reader.readAsDataURL(f);
      });
   }
   
//주어진 배열에서 요소 1개를 랜덤하게 골라 반환하는 함수
function randomItem(a) {
  return a[Math.floor(Math.random() * a.length)];
}

</script>

<style type="text/css">
.content-wrapper {
	background-color: #ECF0F7 !important;
	color: #686B72 !important;
}

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
	height: 450px;
	border: 0px solid;
}
#mainImg {
	width: auto;
	height: 380px;
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
	margin-top: 400px;
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

.popover-content{ /* 일단 안보이게  */
	display: none;
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
						<h1 class=" h1">상품 등록</h1>
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#">Admin</a></li>
								<li class="breadcrumb-item">상품 목록</li>
								<li class="breadcrumb-item active" aria-current="page">상품
									등록</li>
							</ol>
						</nav>
					</div>
					<!-- 이곳에 내용을 작성  -->



					<div class="col-lg-12 grid-margin stretch-card">
						<div class="card">

							<div class="card-body">

								<form 
									action="${pageContext.servletContext.contextPath }/admin/goods/add" 
									method="post"
									enctype="multipart/form-data"
									>

									<span class="inputTitle">상품명: </span><input type="text"
										name="name" id="name" placeholder="제목을 입력하세요."
										style="width: 500px;" class="form-control" />

									<div id="topbox">
										<div id="goodsimgs">
											<div class="imgbox">
												<img id="mainImg"
													src="${pageContext.servletContext.contextPath }/assets/images/mainnoimage.png" /> <label
													for="addimgbtn" class="teamblack"><i
													class="mdi mdi-briefcase-upload"
													style="font-size: 2em; margin-left: 33px;"></i> <br /> 사진
													추가하기</label> <input type="file" id="addimgbtn" name="mainattach">


											</div>

											<div id="goodssubImgs">
												<div class="subimgbox">
													<img id="subImg1" class="subimg"
														src="${pageContext.servletContext.contextPath }/assets/images/noimage.jpg" /> <input
														type="file" id="addsubimgbtn1" name="subattach1">
													<label class="teamblack" for="addsubimgbtn1"><i
														class="mdi mdi-briefcase-upload" style="font-size: 1em;"></i></label>

												</div>
												<div class="subimgbox">
													<img id="subImg2" class="subimg"
														src="${pageContext.servletContext.contextPath }/assets/images/noimage.jpg" /> <input
														type="file" id="addsubimgbtn2" name="subattach2">
													<label class="teamblack" for="addsubimgbtn2"><i
														class="mdi mdi-briefcase-upload" style="font-size: 1em;"></i></label>

												</div>
												<div class="subimgbox">
													<img id="subImg3" class="subimg"
														src="${pageContext.servletContext.contextPath }/assets/images/noimage.jpg" /> <input
														type="file" id="addsubimgbtn3" name="subattach3">
													<label class="teamblack" for="addsubimgbtn3"><i
														class="mdi mdi-briefcase-upload" style="font-size: 1em;"></i></label>

												</div>




											</div>


										</div>

										<div style="padding: 20px;" id="addbox"
											class="shadow-lg p-3 mb-5 bg-white rounded">
									
											<div class="lineinput">
												<span class="inputTitle">- 판매가격 :</span> <input
													style="margin-left: 50px;" type="number"
													class="form-control inputsize" id="seillingPrice" name="seillingPrice">원
											</div>
											<div style="clear: both;"></div>
											
											<div id="categoryBox lineinput" style="margin-bottom: 20px;">
												<span class="inputTitle">- 진열여부 :</span> <select
													name="displayStatus" id="displayStatus" class="form-control"
													style="width: 150px;">
													<option value="y">y</option>
													<option value="n">n</option>

												</select>

											</div>
											
											<div style="clear: both;"></div>
											<div id="categoryBox lineinput" style="margin-bottom: 20px;">
												<span class="inputTitle">- 판매여부 :</span> <select
													name="seillingStatus" id="seillingStatus" class="form-control"
													style="width: 150px;">
													<option value="y">y</option>
													<option value="n">n</option>

												</select>

											</div>
											<div style="clear: both;"></div>
											
											
											<div class="lineinput">
												<span class="inputTitle">- 제조업자명 :</span><input
													id="manufacturer" name="manufacturer" type="text"
													class="form-control inputsize" placeholder="선택사항">
											</div>
											<div style="clear: both;"></div>
											
											<div class="lineinput">
												<span class="inputTitle">- 공급업자명 :</span><input
													id="supplier" name="supplier" type="text"
													class="form-control inputsize" placeholder="선택사항">
											</div>
											<div style="clear: both;"></div>
											<div class="lineinput">
												<span class="inputTitle">- 제조일 :</span><input
													id="manufacturingDate" name="manufacturingDate" type="date"
													class="form-control inputsize">
											</div>
											<div style="clear: both;"></div>
											<div class="lineinput">
												<span class="inputTitle">- 원산지명 :</span><input
													id="origin" name="origin" type="text"
													class="form-control inputsize" placeholder="선택사항">
											</div>
											<div style="clear: both;"></div>
											
											<div id="categoryBox lineinput" style="margin-bottom: 20px;">
												<span class="inputTitle">- 상품분류 :</span> <select
													name="bigcategory" id="bigcategory" class="form-control"
													style="width: 150px;">
													<option>1차 카테고리 선택</option>
													<c:forEach items="${blist}" var="dto" varStatus="status">
														<option value="${dto.no}" >${dto.name }</option>
													</c:forEach>
													
												</select> > <select name="smallcategoryNo" id="smallcategory"
													class="form-control" style="width: 150px;">
													<c:forEach items="${smallbasicList}" var="smalldto" varStatus="status2">
														<option value="${smalldto.no}" >${smalldto.category}</option>
													</c:forEach>
												</select>

											</div>
											<div style="clear: both;"></div>
											
											<div class="lineinput option-box">
												<span class="inputTitle">- 옵션추가 :</span><input
													id="optionName" name="optionName" type="text"
													class="form-control inputsize" placeholder="옵션명">
													<input
													id="optionQty" name="optionQty" type="number"
													class="form-control inputsize" placeholder="옵션 재고수량">
													<button type="button" id="optionAddBtn" class="btn-gradient-primary btn-sm btn-rounded">
													Add
													</button>
											</div>
											<div id="option-span" class="lineinput">
											</div>
											<input type="hidden" id="optionListTxt" name="optionListTxt" value="" />
											<div style="clear: both;"></div>
										</div>
									</div>
									<div style="clear: both;"></div>
									<div id="bottombox" >
										<span style="font-size: 3em; padding: 30px;">상품 리스트에 보여줄 상세정보 </span>
										<textarea style="padding: 20px; font-size: 1.2em;" cols="30"
											rows="10" class="form-control" id="viewdetail" name="viewdetail"></textarea>
										
										
										 											
									</div>
									
									<div id="bottombox">
										<span style="font-size: 3em; padding: 30px;">상품의 상세정보 </span>
										<!-- <textarea style="padding: 20px; font-size: 1.2em;" cols="30"
											rows="10" class="form-control" id="detail" name="detail"></textarea>
										 -->
										 <textarea name="detail" id="summernote"></textarea>
										 											
									</div>

									<div style="width: 1130px; text-align: center;">
										<input type="submit" class="btn btn-default teamblack"
											value="상품등록" />
									</div>

								</form>





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
	<%-- <jsp:include page="/includes/pluginjs.jsp"></jsp:include> --%>
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
