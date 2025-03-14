<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// String brand = (String)request.getAttribute("brand");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${ brand } <br/>
	${ bestSeller }
	
	<ul>
		<li> 브랜드 : ${ bestSeller.brand }</li>
		<li> 버거	 : ${ bestSeller.name }</li>
		<li> 가격 : ${ bestSeller.price }</li>
	</ul>
	
	두 개 이상의 Scope에 같은 키값으로 값을 담은 경우 <br/>
	
	<!-- 
		page => request => session => application 순으로 키값을 검색
	 -->
	 
	Scope에 직접 접근하는 방법 <br/>
	requestScope : ${ requestScope.brand } <br/>
	sessionScope : ${ sessionScope.brand } <br/>
	
	만약에 없는 키값 el구문으로 출력하려고하면 어떻게 될까?<br/><br/>
	
	없는 값 : ${ sessionScope.abc }
	
	<hr />
	
	연산은 어디서하는게 제일 좋음?
	1. SQL문 DB단
	2. Java => Service단
	
	<hr />
	
	<h3>1. 산술연산</h3>
	
	<p>
		* EL 구문을 이용한 산술연산 <br/>
		
		big + small = ${ big + small } <br/>
		big - small = ${ big - small } <br/>
		big * small = ${ big * small } <br/>
		big / small = ${ big / small } 또는 ${ big div samll }<br/>
		big % small = ${ big % small } 또는 ${ big mod small }<br/>
	</p>
	
	<h3>2. 논리연산</h3>
	
	<p>
		AND : ${ true && true } 또는 ${ true and true } <br/>
		OR : ${ true || true } 또는 ${ true or true } <br/>
	</p>
	
	비교연산 == 조건식으로 많이 사용됨
	<h4>숫자끼리 비교</h4>
	
	<p>
		big이 small보다 작나? : ${ big < small } 또는 ${ big lt small }<br/>
		big이 small보다 크나? : ${ big gt small } <br/>
		big이 small보다 작거나 같나? : ${ big le small } <br/>
		big이 small보다 크거나 같나? : ${ big ge small } <br/>
	</p>
	
	<br/>
	
	<h4>동등비교</h4>
	
	<p>
		big이 small과 같나? : ${ big == small } 또는 ${ big eq small } <br/>
		big이 10과 같습니까? : ${ big eq 10 } <br/>
		str과 문구가 일치합니까? : ${ str == "좋아하는문구"" } 또는 ${ str eq '좋아하는문구' } <br/>
		big이 10과 일치하지 않는가? : ${ big ne 10 }
	</p>
	
	<h4>비어있는지 체크</h4>
	
	<p>
		bestSeller가 null과 일치합니까? <br/>
		${ bestSeller == null } 또는 ${ bestSeller eq null } 또는 ${ empty bestSeller }<br/>
		
		list가 비어있지 않습니까? <br/>
		${ !empty list }
	</p>
	
	
	
</body>
</html>