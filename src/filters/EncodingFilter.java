package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class EncodingFilter implements Filter {

/*  public EncodingFilter() はコンストラクタ
    （フィルタのインスタンスが生成される際に実行される）。*/
    public EncodingFilter() {
    }

//  destroy() には「（フィルタの処理が不要になったため）フィルタを破棄する」
//  というときの処理を定義します。。
    public void destroy() {
    }

//	doFilter() はフィルタとしての実行内容を定義するメソッドです。
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//  最初から chain.doFilter(request, response); という1行が書かれているが、
//  これより前に記述するか後に記述するかで動作が変わる。
//
//  これより前に書いた処理は、サーブレットが処理を実行する前にフィルタの処理が実行される。
//  逆に、これより後に書いた処理は、サーブレットが処理を実行した後にフィルタの処理が実行される。
//  文字エンコードの設定はサーブレットの処理より「前」に処理したいので、以下のように記述する。

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

}
