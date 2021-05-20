<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>出退勤の管理ページ</h2>
        <p><a href="<c:url value='/times/new' />">出退勤登録</a>

        <table id="times_list">
            <tbody>
                <tr>
                    <th class="times_date">出勤日</th>
                    <th class="times_in">出勤</th>
                    <th class="times_out">退勤</th>
                    <th class="times_action">操作</th>
                </tr>
                <c:forEach var="times" items="${times}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="times_date"><fmt:formatDate value='${times.in_time}' pattern='yyyy年MM月dd日' /></td>
                        <td class="times_in"><fmt:formatDate value='${times.in_time}' pattern='(dd日)　　HH:mm' /></td>
                        <td class="times_out"><fmt:formatDate value='${times.out_time}' pattern='(dd日)　　HH:mm' /></td>
                        <td class="times_action"><a href="<c:url value='/times/show?id=${times.id}' />">編集</a></td>
                    </tr>

                </c:forEach>
            </tbody>
        </table>
    </c:param>
</c:import>