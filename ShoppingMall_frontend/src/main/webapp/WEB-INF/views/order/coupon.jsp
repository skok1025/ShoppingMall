<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

.total-price {
	text-align: right;
}

input[type=number]::-webkit-inner-spin-button, input[type=number]::-webkit-outer-spin-button
	{
	opacity: 1;
}

#cnt {
	width: 75px;
}

.btns-box {
	text-align: center;
}

#input-box {
	border: 0px solid red;
	width: 400px;
	float: left;
	margin: 50px;
}

#input-box>input {
	padding: 10px !important;
}
</style>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
	String.format = function() {
		let args = arguments;
		return args[0].replace(/{(\d+)}/g, function(match, num) {
			num = Number(num) + 1;
			return typeof (args[num]) != undefined ? args[num] : match;
		});
	}

	function applyCoupon() {
		var fBaseOrderPrice = parseFloat($("#baseOrderPrice", opener.document)
				.val());
		var aSelectCouponInfo = $("#select_coupon_info").val().split("|");

		var iCouponNo = aSelectCouponInfo[0];
		var fSaleValue = parseFloat(aSelectCouponInfo[1]);
		var sSaleType = aSelectCouponInfo[2];

		var fResultPrice;
		var sSaleTypeDisp;
		var sSaleCalcInfo;

		if (sSaleType == "W") {
			fResultPrice = fBaseOrderPrice - fSaleValue;
			sSaleTypeDisp = "원";
			sSaleCalcInfo = 
				String.format(
						"{0} - {1}",
						fBaseOrderPrice.toLocaleString(),
						fSaleValue.toLocaleString()
				);
		} else {
			fResultPrice = fBaseOrderPrice
					- (fBaseOrderPrice * (fSaleValue / 100));
			sSaleTypeDisp = "%";
			
			sSaleCalcInfo = 
				String.format(
						"{0} - ({1} x {2}%)",
						fBaseOrderPrice.toLocaleString(),
						fBaseOrderPrice.toLocaleString(),
						fSaleValue.toLocaleString()
				);
		}

		if (fResultPrice < 0)
			fResultPrice = 0;

		var sSaleInfo = 
			String.format(
				"{0} = ￦{1} ({2} {3} 할인)",
				sSaleCalcInfo,
				fResultPrice.toLocaleString(), 
				fSaleValue.toLocaleString(), 
				sSaleTypeDisp
			);

		$("#saleOrderPrice", opener.document).val(fResultPrice);
		$("#orderPriceDisp", opener.document).text("총 결제금액: " + sSaleInfo);
		$("#orderCalcInfo", opener.document).val(sSaleInfo);
		$("#applyCouponNo", opener.document).val(iCouponNo);
		window.close();
	}
</script>

</head>

<body>

	<div class="container-scroller">

		<!-- partial -->
		<div>
			<!-- partial:../../partials/_sidebar.html -->
			<!-- partial -->
			<div class="main-panel">


				<!-- content-wrapper start -->
				<div class="content-wrapper">
					<div id="btns-box">
						<div style="float: left;">
							<h1>쿠폰 정보</h1>
						</div>

						<div style="clear: both;"></div>
						<div class="form-group">
							<label for="select_coupon">쿠폰 선택</label> <select
								id="select_coupon_info" name="select_coupon_info"
								class="form-control">
								<option>적용할 쿠폰을 선택해주세요.</option>
								<c:forEach items="${memberCouponList}" var="coupon">
									<option
										value="${coupon.no}|${coupon.sale_value}|${coupon.sale_type}">${coupon.name}
										(${coupon.sale_value}
										<c:if test="${coupon.sale_type eq 'P'}">%</c:if><c:if
											test="${coupon.sale_type eq 'W'}">원</c:if> 할인)
									</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div style="clear: both;"></div>

					<hr />

					<div class="btns-box">

						<button type="button" class="btn btn-gradient-primary btn-bg"
							onclick="applyCoupon();">쿠폰 적용하기</button>

					</div>


				</div>
				<!-- content-wrapper ends -->



				<!-- partial:../../partials/_footer.html -->
				<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>

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
