package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.aforce.dao.TweetDAO;

@WebServlet("/new_tweet")
public class NewTweetServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String content = request.getParameter("content");
		String author = request.getParameter("author");
		
		if (author.length() > 255) {
           
            request.setAttribute("errorMessage", "名前が長すぎます。再入力してください。");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

		
		
		TweetDAO tweetDAO = new TweetDAO();
		try {
			tweetDAO.addTweet(content, author);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("tweet_list");
	}
}
