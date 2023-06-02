package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import utils.DBUtil;

@WebServlet("/show")
public class ShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ShowServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

//	    該当のIDのメッセージ１件のみをデータベースから取得
        Message m = em.find(Message.class, Integer.parseInt(request.getParameter("id")));

        em.close();

//	    メッセージデータをリクエストスコープにセットしてshow.jspを呼び出す
        request.setAttribute("message", m);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/show.jsp");
        rd.forward(request, response);
    }


//	今回は1件のみデータを取得できれば良いので、em.find()メソッドを利用します。
//	IDはURLに追記されている id から取得します。
//
//	たとえば、
//
//	http://localhost:8080/message_board/show?id=1
//	にアクセスした場合は、id が1のメッセージ情報を表示します。
//
//	クエリ・パラメータの id は request.getParameter("id") で取得できますが、
//	注意したいのが request.getParameter() はどのようなデータもString型のデータとして
//	取得するという特徴です。データベースの id は整数値です。そこで Interger.parseInt()
//	メソッドを利用してString型の”1”を整数値の1に変えてから find メソッドの引数にしています。

}
