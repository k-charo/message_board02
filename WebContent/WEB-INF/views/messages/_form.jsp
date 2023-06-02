<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<label for="title">タイトル</label><br />
<input type="text" name="title" id="title" value="${message.title}" />
<br /><br />

<label for="content_msg">メッセージ</label><br />
<input type="text" name="content" id="content_msg" value="${message.content}" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>


<%-- タイトルとメッセージのテキストボックスに value="${message.title}" のような記述を入れました。

リクエストスコープの message オブジェクトからデータを参照して、入力内容の初期値として表示する
ようにしています。このあと作成する edit や、入力値エラーがあってフォームのページを再度表示する
際に役立ちます。

ただし、リクエストスコープに message が入っていなければエラーが表示されます。NewServlet で
「おまじない」として request.setAttribute("message", new Message()); を記述したのは、
画面表示時のエラー回避のため、とりあえず “文字数0のデータ” をフォームに渡すためです。 --%>