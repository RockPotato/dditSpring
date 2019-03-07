<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h1 class="page-header">전체 사용자 리스트(tiles)</h1>
<!-- userList 정보를 화면에 출력하는 로직 작성  -->
<%
	List<UserVO> list = (List<UserVO>) request.getAttribute("userList");
	request.setAttribute("list", request.getAttribute("userList"));
%>

<div class="table-responsive">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>#</th>
				<th>사용자 아이디</th>
				<th>사용자 이름</th>
				<th>별명</th>
				<th>등록일시</th>
			</tr>
		</thead>
		<tbody>
			<%-- <%
						for (int i = 0; i < list.size(); i++) {
							out.print("<tr class=\"userTr\" data-userid=\""+list.get(i).getUserId()+"\">");
							out.print("<td>" + (i + 1) + "</td>");
							out.print("<td>" + list.get(i).getUserId() + "</td>");
							out.print("<td>" + list.get(i).getUserNm() + "</td>");
							out.print("<td>" + "-" + "</td>");
							out.print("<td>" + list.get(i).getReg_dt_fmt() + "</td>");
							out.print("</tr>");
						}
					%> --%>
			<c:forEach items="${list}" var="user">
				<tr class="userTr">
					<td>1</td>
					<td>${user.userId}</td>
					<td>${user.userNm}</td>
					<td>-</td>
					<td><fmt:formatDate value="${user.reg_dt}"
							pattern="yyyy/MM/dd"></fmt:formatDate></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script>
	//문서 로딩이 완료된 이후 이벤트 등록
	$(document).ready(function() {
		console.log("document ready");

		// 사용자 tr 태그 클릭시 이벤트 핸들러
		/* $(".userTr").click(function(){
			
		}); */
		$(".userTr").on("click", function() {
			var index = $(".userTr").rowIndex;
			var id = $(this).children()[1].innerHTML;
			var userId = $(this).data("userid");

			// /user
			// 1. document
			//	document.location="/user?userId="+userId;

			// 2. form
			$("#userId").val(userId);
			/* $("#frm").attr("action","/userAllList"); */
			$("#frm").submit();

		});
	});
</script>
<form id="frm" action="${cp }/user/user" method="get">
	<input type="hidden" id="userId" name="userId" />
</form>
