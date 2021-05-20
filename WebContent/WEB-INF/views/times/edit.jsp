<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${times != null}">
                <h2>出勤登録id : ${times.id} の編集ページ</h2>
                <form method="POST" action="<c:url value='/times/update' />">
                    <c:import url="_form.jsp" />
                </form>

                <p style="color:red">⚠注意：削除不可</p>
                <p style="color:red;font-size:0.8em;">⚠削除したい場合は出勤時間と退勤時間が同じように設定してください。</p>
                <form method="POST" action="<c:url value='/times/destroy' />">
                    <input type="hidden" name="_token" value="${_token}" />
                </form>

            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/times/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>