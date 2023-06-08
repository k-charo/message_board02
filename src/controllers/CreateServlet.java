package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import utils.DBUtil;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();
            em.getTransaction().begin();

            Message m = new Message();

            String title = request.getParameter("title");
            m.setTitle(title);

            String content = request.getParameter("content");
            m.setContent(content);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            m.setCreated_at(currentTime);
            m.setUpdated_at(currentTime);

            em.persist(m);
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "登録が完了しました。");
            em.close();

            response.sendRedirect(request.getContextPath() + "/index");


//   ここでは、
//
//   if(_token != null && _token.equals(request.getSession().getId())) { ... }
//
//   でCSRF対策のチェックを行っています。_token に値がセットされていなかったりセッション
//   IDと値が異なったりしたらデータの登録ができないようにしています。
//
//   ここのチェックがtrueにならないのは、意図しない不正なページ遷移によって /create へ
//   アクセスされた場合です。悪意のあるネット利用者が勝手に投稿できないようにするための
//   対策です。
//
//   また、データの保存部分について改めて説明しますが、id はMySQLの auto_increment の
//   採番に任せ、title と content はフォームから入力された内容をセットします。 created_at
//   と updated_at は、
//
//   Timestamp currentTime = new Timestamp(System.currentTimeMillis());
//
//   このように記述することで現在日時の情報を持つ日付型のオブジェクトを取得できます
//   （Javaでは日時情報もオブジェクトで管理します）。
//
//   そのオブジェクトを2つのカラムにセットします。必要な情報をセットした Message クラスの
//   オブジェクトを persist メソッドを使ってデータベースにセーブします。
//   commit を忘れないようにしてください。
//
//   そして、データベースへの保存が完了したら、indexページへリダイレクト（遷移）させるように
//   しています。

//   /create、/update、/destroy の各サーブレットで、データベースに対する処理が完了したとき
//   にフラッシュメッセージをセットします。しかし、そこから /index へリダイレクトし、さらに
//   index.jsp を呼び出すという複数の遷移が発生するため、リクエストスコープにフラッシュメッセージ
//   をセットすると途中で削除されてしまいます。
//
//   そこで、フラッシュメッセージをセッションスコープに保存し、index.jsp を呼び出したときに
//   セッションスコープから取り出して表示するようにします。

        }
    }

}
