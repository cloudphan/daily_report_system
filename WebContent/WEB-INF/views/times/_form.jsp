<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" />
            <br />
        </c:forEach>

    </div>
</c:if>
<label for="in_time">出勤時間</label><br />
<input type="datetime-local" name="in_time" value="<fmt:formatDate value='${times.in_time}' pattern='yyyy-mm-dd hh:mm:ss[.fffffffff]' />" />
<br /><br />
<label for="out_time">退勤時間</label><br />
<input type="datetime-local" name="out_time" value="<fmt:formatDate value='${times.out_time}' pattern='yyyy-mm-dd hh:mm:ss[.fffffffff]' />" />
<br /><br />
<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>


