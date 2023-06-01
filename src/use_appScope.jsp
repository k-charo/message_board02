<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<!-- アプリケーションスコープから app_name を取り出し、String型にキャストした上で
「use_appScope.jsp」用の変数 app_name に格納している。 -->
<% String app_name = (String)request.getServletContext().getAttribute("app_name"); %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>アプリケーションスコープのテスト</title>
    </head>
    <body>
        <h1><%= app_name %></h1>
    </body>
</html>