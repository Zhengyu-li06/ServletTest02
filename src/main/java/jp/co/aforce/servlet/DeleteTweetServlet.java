package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.aforce.dao.TweetDAO;

@WebServlet("/delete_tweet")
public class DeleteTweetServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String tweetIdStr = request.getParameter("tweetId");

        if (tweetIdStr == null || tweetIdStr.isEmpty()) {
            // tweetIdが存在しない、または空の場合のエラーメッセージ
            request.setAttribute("errorMessage", "削除するツイートが指定されていません。");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        try {
            int tweetId = Integer.parseInt(tweetIdStr);
            TweetDAO tweetDAO = new TweetDAO();
            tweetDAO.deleteTweet(tweetId);
            response.sendRedirect("tweet_list.jsp");
        } catch (NumberFormatException e) {
            // tweetIdが数値に変換できなかった場合のエラーメッセージ
            request.setAttribute("errorMessage", "無効なツイートIDが指定されました。");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // その他のエラー処理
            request.setAttribute("errorMessage", "ツイートの削除中にエラーが発生しました。");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
