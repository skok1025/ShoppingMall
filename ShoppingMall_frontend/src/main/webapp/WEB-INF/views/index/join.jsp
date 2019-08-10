<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>SK Mall</title>
  <jsp:include page="/includes/asset.jsp"></jsp:include>
<%-- <jsp:include page="/includes/notify_asset.jsp"></jsp:include>   --%>
<jsp:include page="/includes/plugincss.jsp"></jsp:include>


<script type="text/javascript">

	$(document).ready(function(){
		
		var isIdNotExist = false;
		
		$("input").keyup(function(){
			if($("#name").val() != "" && $("#id").val() != "" && $("#tel").val() != "" 
				&& $("#password").val() != "" && $("#email").val() != "" && $("#gender").val() != ""
					&& isIdNotExist ){
				$("#registerbt").removeAttr("disabled");
			}
		});
		
		$("#id").keyup(function(){
			
			
			$.ajax({
				type: "get",
				async: true, // true (비동기), false(동기)
				url: "${pageContext.servletContext.contextPath}/customer/checkid",
				data: "id="+$("#id").val(), // QueryString Get 방식으로 전송되던 형식 동일
				success:function(result){
					console.log(result);
					if(result>=1){
						$("#registerbt").attr("disabled","disabled");
						$("#idcheck").text("이미 사용중인 아이디 입니다.");	
						$("#idcheck").css({
							color:"tomato",
							"font-weight":"bold"
						});
						isIdNotExist = false;
					} else{
						//if($("#pwcheck").text()=="일치"){
						//	if($("#emchecktxt").text()== "인증 성공!!"){
						//		$("#registerbt").removeAttr("disabled");	
						//	}
						//}
						$("#idcheck").text("사용 가능한 아이디입니다!!");
						$("#idcheck").css({
							color:"cornflowerblue",
							"font-weight":"bold"
						});
						isIdNotExist = true;
					}
				},
				error:function(a,b,c){
					console.log(a+b+c);
				}
			})
			
		});
		
		

		$("#codecheckbt").click(function(){
			//alert($("#realcode").val()); // Real Code
			//alert($("#emcheck").val()); // 내가 입력한 코드
			
			if($("#realcode").val()== $("#emcheck").val()){
				$("#emchecktxt").attr("color","cornflowerblue");
				$("#emchecktxt").text("인증 성공!!");
				$("#codecheckbt").attr("disabled","false");
				$("#checkembt").attr("disabled","false");
				
			} else{
				$("#emchecktxt").attr("color","tomato");
				$("#emchecktxt").text("인증 코드를 확인하세요.");
			}
		});
		
		$("#checkembt").click(function(){
			//alert("click");
			
			$.ajax({
				type:"post",
				url:"${pageContext.servletContext.contextPath}/customer/checkemail",
				data:"to="+$("#email").val(),
				success:function(result){
					console.log(result);
					alert('메세지를 전송했습니다. 인증코드를 확인해주세요.');
					$("#realcode").val(result);
				},
				error:function(a,b,c){
					console.log(a+b+c);				
				}
			});
		});
		
		
		
		
		
	});


</script>

<style type="text/css">

	.form-group > input {
		margin-bottom: 10px;
	}
	
	

</style>

</head>

<body>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
      <div class="content-wrapper d-flex align-items-center auth">
        <div class="row w-100">
          <div class="col-lg-4 mx-auto">
            <div class="auth-form-light text-left p-5">
              <div class="brand-logo">
                <img src="${pageContext.servletContext.contextPath }/assets/images/cafe24.png">
              </div>
              <h4>New here?</h4>
              <h6 class="font-weight-light">Signing up is easy. It only takes a few steps</h6>
              <form:form 
              modelAttribute="memberVo"
              class="pt-3" 
              method="post" 
              action="${pageContext.servletContext.contextPath }/customer/join">
                <div class="form-group">
                  <form:input type="text" class="form-control form-control-lg" id="name" name="name" placeholder="사용자 이름" path="name"/>
                  <form:errors path="name" />
                </div>
                <div class="form-group">
                  <form:input type="text" class="form-control form-control-lg" id="id" name="id" placeholder="사용자 아이디" path="id"/>
                  <span id="idcheck"></span>
                  <div style="clear:both;"></div>
                  <form:errors path="id" />
                </div>
                <div class="form-group">
                  <form:input type="tel" class="form-control form-control-lg" id="tel" name="tel" placeholder="연락처" value="" path="tel"/>
                </div>
                <div class="form-group" >
                  <form:input type="email" class="form-control form-control-lg" id="email" name="email" placeholder="Email" path="email" />
               	  <input type="button" id="checkembt" class="btn btn-gradient-primary btn-sm" value="이메일 인증"/> 
               	  <form:errors path="email" />
               	  <input type="text" id="emcheck" class="form-control" placeholder="인증번호 입력"/>
               	  <input type="button" id="codecheckbt"  class="btn btn-gradient-primary btn-sm" value="인증하기"/>
               	  <span id="emchecktxt"></span>
               	  <input type="hidden" id="realcode"/>
                </div>
                <div class="form-group">
                  <form:input type="password" class="form-control form-control-lg" id="password" name="password" placeholder="Password" value="" path="password"/>
               	  <form:errors path="password" />
                </div>
                
                <div class="form-group">
                  <form:select class="form-control form-control-lg" id="gender" name="gender" path="gender">
                    <option>성별</option>
                    <option value="m">Male</option>
                    <option value="f">Female</option>
                    
                  </form:select>
                </div>
               
               <div class="form-group">
                  <form:input type="text" class="form-control form-control-lg" id="address" name="address" placeholder="address" value="" path="address"/>
                </div>
               <div class="form-group">
                  <form:input type="date" class="form-control form-control-lg" id="birthdate" name="birthDate" placeholder="Birth Date" path="birthDate"/>
                </div>
                
                <div class="mb-4">
                  <div class="form-check">
                    <label class="form-check-label text-muted">
                      <input type="checkbox" class="form-check-input">
                      I agree to all Terms & Conditions
                    </label>
                  </div>
                </div>
                <div class="mt-3">
                  <button type="submit" class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn" id="registerbt" disabled="disabled">SIGN UP</button>
                </div>
                <div class="text-center mt-4 font-weight-light">
                  Already have an account? <a href="${pageContext.servletContext.contextPath}/login" class="text-primary" >Login</a>
                </div>
              </form:form>
            </div>
          </div>
        </div>
      </div>
      <!-- content-wrapper ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->
 <!-- container-scroller -->
  <jsp:include page="/includes/pluginjs.jsp"></jsp:include>
	<!-- endinject -->
	<!-- Plugin js for this page-->
	<!-- End plugin js for this page-->
	<!-- inject:js -->
	<jsp:include page="/includes/injectjs_asset.jsp"></jsp:include>
  <!-- endinject -->
</body>

</html>
