<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/layout/app.jsp">
    <c:param name="content">
        <h2>id : ${message.id} のメッセージ編集ページ</h2>

        <form method="POST" action="${pageContext.request.contextPath}/update">
            <c:import url="_form.jsp" />
        </form>

        <p><a href="${pageContext.request.contextPath}/index">一覧に戻る</a></p>
        <p><a href="#" onclick="confirmDestroy();">このメッセージを削除する</a></p>
        <form method="POST" action="${pageContext.request.contextPath }/destroy">
            <input type="hidden" name="_token" value="${_token }" />
        </form>
        <script>
        function confirmDestroy() {
            if(confirm("本当に削除してよろしいですか？")) {
                document.forms[1].submit();
            }
        }
        </script>

<!-- 削除用に /update とは別のフォームを用意しています。
     このフォームはJavaScriptで確認のウィンドウを表示した上で
    「OK」がクリックされたらフォームを送信するようにしています。 -->
    </c:param>
</c:import>

<%-- _form.jsp で <input type="text" name="title" value="${message.title}" />
のように記述したため、value="${message.title}" のおかげでデータベースに保存されていた
メッセージやタイトルが初期値としてテキストボックスに格納されます。 --%>