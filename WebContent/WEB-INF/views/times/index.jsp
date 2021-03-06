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
        <p style="color:red;font-size:0.8em;">⚠出勤時刻と退勤時刻が一致であれば、勤務時間の計算になりません。</p>
        <table id="times_list">
            <tbody>
                <tr>
                    <th class="times_date">出勤日</th>
                    <th class="times_in">出勤</th>
                    <th class="times_out">退勤</th>
                    <th class="times_worked">労働時間</th>
                    <th class="times_action">操作</th>
                </tr>
                <c:forEach var="times" items="${times}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="times_date"><fmt:formatDate value='${times.in_time}' pattern='yyyy年MM月dd日' /></td>
                        <td class="times_in"><fmt:formatDate value='${times.in_time}' pattern='(dd日)　　HH:mm' /></td>
                        <td class="times_out"><fmt:formatDate value='${times.out_time}' pattern='(dd日)　　HH:mm' /></td>
                        <td class="times_worked">
                            <script>
                                var date1, date2;
                                 date1 = new Date('${times.out_time}');
                                 date2 = new Date('${times.in_time}');
                                var res = Math.abs(date1 - date2) / 1000;
                                 var days = Math.floor(res / 86400);
                                 var hours = Math.floor(res / 3600) % 24;
                                 var minutes = Math.floor(res / 60) % 60;
                                 document.write(days*24+hours+"時間"+minutes+"分");
                             </script>
                        </td>
                         <td class="times_action"><a href="<c:url value='/times/show?id=${times.id}' />">編集</a></td>
                    </tr>

                </c:forEach>
            </tbody>
        </table>
        <div id="pagination">
            （全 ${times_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((times_count - 1) / 10) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/times/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

    </c:param>
</c:import>