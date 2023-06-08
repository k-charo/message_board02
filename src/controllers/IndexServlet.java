package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import utils.DBUtil;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public IndexServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();

//      開くページ数を取得（デフォルトは1ページ目）
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) {}

 //     最大件数と開始位置を指定してメッセージを取得
        List<Message> messages = em.createNamedQuery("getAllMessages", Message.class)
                                   .setFirstResult(15 * (page - 1))
                                   .setMaxResults(15)
                                   .getResultList();

//      全件数を取得
        long messages_count = (long)em.createNamedQuery("getMessagesCount", Long.class)
                                      .getSingleResult();

        em.close();

        request.setAttribute("messages", messages);
        request.setAttribute("messages_count", messages_count);     //全件数
        request.setAttribute("page", page);                         //ページ数

//      page=2 とあっても request.getParameter() で取得できるのはString型の "2" です。
//      そこで Integer.parseInt() で文字列から数値に変更しています。
//
//      ただ、page のパラメータが無かったり page=a のように数値ではないものを、利用者が指定する可能性はあります。
//      本来ならしっかりした形でパラメータが数値かどうか調べるべきですが、今回は、数値ではないものを
//      Integer.parseInt に渡すと NumberFormatException という例外が表示されるという性質を使って、
//      try と catch で囲って処理が止まらないようにしています。
//
//      データベースへ問い合わせている部分ですが、setFirstResult(15 * (page - 1)) は
//      「何件目からデータを取得するか（配列と同じ0番目から数えていきます）」、
//      setMaxResults(15) は「データの最大取得件数（今回は15件で固定）」を設定します。
//
//      また、getAllMessages は複数のデータが結果として戻ってくる可能性があるため getResultList() で
//      問い合わせ結果を取得していますが、getMessagesCount は全件数という1つの結果のみが戻ってくるので、
//      getSingleResult() という “1件だけ取得する” という命令を指定しています。
//
//      最後にメッセージデータのリストの他、全件数の数値と表示するページ数の数値もリクエストスコープにセットして
//      index.jsp に送っています。

//      フラッシュメッセージがセッションスコープにセットされていたら
//      リクエストスコープに保存する（セッションスコープからは削除）
        if(request.getSession().getAttribute(("flush")) != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher
                ("/WEB-INF/views/messages/index.jsp");
        rd.forward(request, response);

    }

}
