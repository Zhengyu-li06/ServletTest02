package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.Tweet;

public class TweetDAO extends DAO {

	
	public List<Tweet> getAllTweets() throws Exception {
		List<Tweet> tweets = new ArrayList<>();
		Connection con = getConnection();
		
		String sql = "SELECT id, content, posted_at, author FROM tweets ORDER BY posted_at DESC";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String content = rs.getString("content");
			String postedAt = rs.getString("posted_at");
			String author = rs.getString("author");
			Tweet tweet = new Tweet(id, content, postedAt, author);
			tweets.add(tweet);
		}

		st.close();
		con.close();
		return tweets;
	}

	
	public void addTweet(String content, String author) throws Exception {
		Connection con = getConnection();
		String sql = "INSERT INTO tweets (content, author) VALUES (?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, content);
		st.setString(2, author);
		st.executeUpdate();

		st.close();
		con.close();

	}




public void deleteTweet(int tweetId) throws Exception {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
        connection = getConnection();
        String sql = "DELETE FROM tweets WHERE id = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, tweetId);
        preparedStatement.executeUpdate();
    } finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
}