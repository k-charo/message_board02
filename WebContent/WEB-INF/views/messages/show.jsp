<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/layout/app.jsp">
    <c:param name="content">

        <h2>id : ${message.id} のメッセージ詳細ページ</h2>

        <p>タイトル：<c:out value="${message.title}" /></p>
        <p>メッセージ：<c:out value="${message.content}" /></p>
        <p>作成日時：<fmt:formatDate value="${message.created_at}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
        <p>更新日時：<fmt:formatDate value="${message.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" /></p>

        <p><a href="${pageContext.request.contextPath}/index">一覧に戻る</a></p>

    </c:param>
</c:import>

<%-- 日情報は基本的にオブジェクトの形をしており、単純な文字列ではありません。

そこで <fmt:formatDate> タグで作成日時や更新日時を pattern 属性で指定した
年-月-日 時:分:秒 の形式で表示するようにしています。 --%>