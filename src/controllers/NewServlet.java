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

@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public NewServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

//		Messageのインスタンスを生成
        Message m = new Message();

//		mの各フィールドにデータを代入
        String title = "taro";
        m.setTitle(title);

        String content = "hello";
        m.setContent(content);

//      現在の日時を取得
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        m.setCreated_at(currentTime);
        m.setUpdated_at(currentTime);

//		データベースに保存
        em.persist(m);
        em.getTransaction().commit();

//		自動採番されたIDの値を表示
        response.getWriter().append(Integer.valueOf(m.getId()).toString());

        em.close();

//	    Message m = new Message(); で Message のインスタンスを生成した後、
//	    自動採番されるID以外のフィールドに値をセットし、em.persist(m); で
//	    DBに保存します。em.getTransaction().commit(); はデータの新規登録を
//	    確定（コミット）させる命令です。
    }

}
