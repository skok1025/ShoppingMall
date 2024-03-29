<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="sidebar sidebar-offcanvas" id="sidebar">
				<ul class="nav">
					<li class="nav-item nav-profile"><a href="#" class="nav-link">
							<div class="nav-profile-image">
								<img src="${pageContext.servletContext.contextPath }/assets/images/faces/face1.jpg" alt="profile"> <span
									class="login-status online"></span>
								<!--change to offline or busy as needed-->
							</div>
							<div class="nav-profile-text d-flex flex-column">
								<span class="font-weight-bold mb-2">David Grey. H</span> <span
									class="text-secondary text-small">Project Manager</span>
							</div> <i class="mdi mdi-bookmark-check text-success nav-profile-badge"></i>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.servletContext.contextPath}/admin/"> <span class="menu-title">Home</span>
							<i class="mdi mdi-home menu-icon"></i>
					</a>
					</li>
					
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.servletContext.contextPath}/admin/goods"> <span class="menu-title">Goods</span>
							<i class="mdi mdi-weight menu-icon"></i>
					</a>
					</li>

					<li class="nav-item"><a class="nav-link"
						href="${pageContext.servletContext.contextPath}/admin/order"> <span class="menu-title">Order</span>
							<i class="mdi mdi-cart menu-icon"></i>
					</a>
					</li>
					
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.servletContext.contextPath}/admin/addcategory"> <span class="menu-title">Add Category</span>
							<i class="mdi mdi-menu menu-icon"></i>
					</a>
					</li>
					
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.servletContext.contextPath}/admin/coupon"> <span class="menu-title">Coupon</span>
							<i class="mdi mdi-ticket menu-icon"></i>
					</a>
					</li>
					
				</ul>
			</nav>