<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null }">
            <div id="flush_success">
                <c:out value="${flush }"></c:out>
            </div>
        </c:if>
        <h2>メッセージ一覧</h2>
        <ul>
            <c:forEach var="message" items="${messages}">
                <li>
                    <a href="${pageContext.request.contextPath}/show?id=${message.id}">
                        <c:out value="${message.id}" />
                    </a>
                    ：<c:out value="${message.title}"></c:out> &gt; <c:out value="${message.content}" />
                </li>
            </c:forEach>
        </ul>

        <p><a href="${pageContext.request.contextPath}/new">新規メッセージの投稿</a></p>

    </c:param>
</c:import>

<%-- <c:import> を使うことで、url 属性に指定したファイルの内容をその位置で読み込むことができる。
<c:param name="content"> と書いたタグの中の記述内容が、app.jsp の ${param.content}
のところに当てはまる。

<c:out value="${pageContext.request.contextPath}" />と記述することで、その部分が
自動的に /message_board というコンテキストパスの文字列に置き換わる。コンテキストパスの
設定を変更してもJSPファイルに修正が必要なくなるので、コンテキストパスは固定の文字列より
上記の書き方をオススメします。

なお、<c:url> タグを使ってURLの指定を行っても自動で /message_board のコンテキストパス
の文字列が挿入されます。たとえば、
<a href="${pageContext.request.contextPath}/new">新規メッセージの投稿</a>
これは以下のように記載しても同じ内容になります。
<a href="<c:url value='/new' />">新規メッセージの投稿</a> --%>