<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${times != null}">
                <h2>id : ${times.id} の編集ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>社員番号</th>
                            <td><c:out value="${times.employee.code}" /></td>
                        </tr>
                        <tr>
                            <th>出勤日</th>
                            <td>
                                <fmt:formatDate value="${times.in_time}" pattern="yyyy年MM月dd日" />
                            </td>
                        </tr>
                        <tr>
                            <th>出勤時間</th>
                            <td><fmt:formatDate value="${times.in_time}" pattern="(dd日)　　HH:mm" /></td>
                        </tr>
                        <tr>
                            <th>退勤時間</th>
                            <td><fmt:formatDate value="${times.out_time}" pattern="(dd日)　　HH:mm" /></td>
                        </tr>

                    </tbody>
                </table>

                <p><a href="<c:url value='/times/edit?id=${times.id}' />">内容を編集する</a></p>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/times/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>