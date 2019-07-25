<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Ooooooooops! - ${uri }</h1>
<p> 죄송합니다.</p>
<p> 
 예외발생 컨트롤러에서 받음<br>
 ================================<br>
 </p>
 <pre style="color:#d00;"> <!-- 개행한 것 그대로 화면에 출력 -->
 ${exception }
</pre>
 

</body>
</html>