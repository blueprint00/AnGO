package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import dto.PreferDTO;
import dto.QuestionDTO;
import dto.RequestDTO;
import dto.ResponseDTO;
import dto.ReviewDTO;
import dto.UserDTO;
import model_utility.UserBaseRecommendation;
import model_utility.Token;

public class UserDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	private ResponseDTO response;
	int sql_flag = 0;

	public ResponseDTO checkUserID(RequestDTO request) {

		try {

			response = new ResponseDTO();
			conn = DBManager.getInstance().getConnection();

			String sql = "select count(*) from user where userId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, request.getUser().getUser_id());
			rs = pstmt.executeQuery();
			if (rs.next() && rs.getInt(1) > 0) {

				response.setResponse_msg("CheckAccount_fail");
			} else {

				response.setResponse_msg("CheckAccount_success");
			}

		} catch (Exception e) {

			e.printStackTrace();
			response.setResponse_msg("CheckAccount_fail");

		} finally {

			try {
				DBManager.getInstance().close(conn, pstmt, pstmt, rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return response;
	}

	public ResponseDTO createUserAccount(RequestDTO request) {

		try {

			response = new ResponseDTO();
			conn = DBManager.getInstance().getConnection();

			String sql = "INSERT INTO user(userId, userPw, userNm, availability) VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, request.getUser().getUser_id());
			pstmt.setString(2, request.getUser().getUser_pw());
			pstmt.setString(3, request.getUser().getUser_nm());
			pstmt.setInt(4, 0);

			sql_flag = pstmt.executeUpdate();
			if (sql_flag > 0) {

				response.setResponse_msg("JoinAccount_success");
				response.setToken(Token.createToken(request.getUser().getUser_id()));
			} else {

				response.setResponse_msg("JoinAccount_fail");
			}

		} catch (Exception e) {

			response.setResponse_msg("JoinAccount_fail");
			e.printStackTrace();

		} finally {

			try {
				DBManager.getInstance().close(conn, pstmt, pstmt, rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return response;
	}

	public ResponseDTO LoginAccount(RequestDTO requset) {

		try {
			response = new ResponseDTO();
			conn = DBManager.getInstance().getConnection();

			String sql = "select count(*), userId, availability from user where userId =? and userPw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, requset.getUser().getUser_id());
			pstmt.setString(2, requset.getUser().getUser_pw());
			rs = pstmt.executeQuery();
			if (rs.next() && rs.getInt(1) > 0) {

				response.setResponse_msg("LoginAccount_success");
				response.setToken(Token.createToken(requset.getUser().getUser_id()));
				response.setAvailability(rs.getInt("availability"));

			} else {

				response.setResponse_msg("LoginAccount_fail");
			}

		} catch (Exception e) {

			response.setResponse_msg("LoginAccount_fail");
			e.printStackTrace();
		} finally {

			try {
				DBManager.getInstance().close(conn, pstmt, pstmt, rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return response;
	}

	public Long getUserIndex(String user_id, Connection con) {

		Long user_idx = null;

		try {

			this.conn = con;
			String sql = "select count(*), user_idx from user where userId= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if (rs.next() && rs.getInt(1) > 0) {

				user_idx = rs.getLong("user_idx");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return user_idx;
	}

	public ArrayList<Long> getPreferCategoryIndexList(ArrayList<PreferDTO> prefer_list, Connection con) {

		ArrayList<Long> cg_idx_list = new ArrayList<Long>();

		try {

			// this.conn = DBManager.getInstance().getConnection();

			this.conn = con;
			String sql = "select count(*),cg_idx from activity_cg where cg_id= ?";
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < prefer_list.size(); i++) {

				pstmt.setString(1, prefer_list.get(i).getCg_id());
				rs = pstmt.executeQuery();
				if (rs.next() && rs.getInt(1) > 0) {

					cg_idx_list.add(rs.getLong("cg_idx"));
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return cg_idx_list;
	}

	public ArrayList<Long> getReviewCategoryIndexList(ArrayList<ReviewDTO> review_list, Connection con) {

		ArrayList<Long> cg_idx_list = new ArrayList<Long>();

		try {

			this.conn = con;
			String sql = "select count(*),cg_idx from activity_cg where cg_id= ?";
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < review_list.size(); i++) {

				pstmt.setString(1, review_list.get(i).getCategory_id());
				rs = pstmt.executeQuery();
				if (rs.next() && rs.getInt(1) > 0) {

					cg_idx_list.add(rs.getLong("cg_idx"));
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return cg_idx_list;
	}

	public ResponseDTO insertUserPreferenceList(RequestDTO request) throws SQLException {

		Long user_idx = null;
		ArrayList<Long> cg_idx_list = new ArrayList<Long>();
		int cnt = 0;

		try {

			response = new ResponseDTO();

			conn = DBManager.getInstance().getConnection();
			conn.setAutoCommit(false);

			user_idx = getUserIndex(request.getUser().getUser_id(), conn);
			cg_idx_list = getPreferCategoryIndexList(request.getPreference_list(), conn);

			for (int i = 0; i < request.getPreference_list().size(); i++) {

				String sql = "insert into " + request.getPreference_list().get(i).getQuuestion_type() + " value(?,?,?)";

				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, user_idx);
				pstmt.setLong(2, cg_idx_list.get(i));
				pstmt.setFloat(3, request.getPreference_list().get(i).getUser_score());
				sql_flag = pstmt.executeUpdate();

				if (sql_flag > 0) {
					cnt++;
					System.out.println(cnt + ": insert user score : " + user_idx + " " + cg_idx_list.get(i) + " "
							+ request.getPreference_list().get(i).getUser_score() + " success");
				}
			}

			sql_flag = 0;
			String sql2 = "update user set availability = 1 where userId = ?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, request.getUser().getUser_id());
			sql_flag = pstmt.executeUpdate();

			if (sql_flag > 0 && cnt == request.getPreference_list().size()) {

				System.out.println("update availability success");
				conn.commit();
				conn.setAutoCommit(true);
				response.setResponse_msg("InsertUserPreference_success");

			} else {

				System.out.println("rollback");
				conn.rollback();
				response.setResponse_msg("InsertUserPreference_fail");

			}

		} catch (Exception e) {

			if (conn != null) {

				e.printStackTrace();
				System.out.println("rollback");
				conn.rollback();
				response.setResponse_msg("InsertUserPreference_fail");

			}

		} finally {

			try {
				DBManager.getInstance().close(conn, pstmt, pstmt, rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return response;
	}

	public ArrayList<PreferDTO> getRandomCategory(Connection con) {

		ArrayList<String> cg_id_list = new ArrayList<String>();
		ArrayList<String> cg_nm_list = new ArrayList<String>();
		ArrayList<PreferDTO> randome_category_list = new ArrayList<PreferDTO>();
		int randomNum[] = new int[10];

		try {

			this.conn = con;
			String sql = "select cg_id, cg_name from activity_cg";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				cg_id_list.add(rs.getString("cg_id"));
				cg_nm_list.add(rs.getString("cg_name"));

			}
			for (int i = 0; i < randomNum.length; i++) {
				randomNum[i] = (int) (Math.random() * cg_id_list.size() + 1);
				for (int j = 0; j < i; j++) {
					if (randomNum[i] == randomNum[j]) {
						// 같은 수가 존재한다면 다시 랜덤 수 생성
						i--;
					}
				}
			}

			for (int i = 0; i < randomNum.length; i++) {

				PreferDTO preferVO = new PreferDTO();
				preferVO.setCg_id(cg_id_list.get(randomNum[i]));
				preferVO.setCategory_nm(cg_nm_list.get(randomNum[i]));
				randome_category_list.add(preferVO);
			}

		} catch (

		Exception e) {

			e.printStackTrace();
		}
		return randome_category_list;

	}

	public ResponseDTO getUserRecommendCategory(RequestDTO request) {

		ArrayList<PreferDTO> prefer_list = new ArrayList<PreferDTO>();
		List<RecommendedItem> recommend_list = null;

		try {

			response = new ResponseDTO();
			conn = DBManager.getInstance().getConnection();

			recommend_list = UserBaseRecommendation.getRecommendCategory(
					getUserIndex(request.getUser().getUser_id(), conn), request.getWeather_type());

			if (recommend_list == null) {
				System.out.println("recommend is null");
				response.setPrefer_list(getRandomCategory(conn));

			} else if (recommend_list.size() == 0) {

				System.out.println("recommend size is 0");
				response.setPrefer_list(getRandomCategory(conn));
			}

			else {

				String sql = "select cg_id, cg_name from activity_cg where cg_idx =?";
				pstmt = conn.prepareStatement(sql);

				for (RecommendedItem recommendation : recommend_list) {

					pstmt.setLong(1, recommendation.getItemID());
					rs = pstmt.executeQuery();
					rs.next();
					PreferDTO prefer = new PreferDTO();
					prefer.setCg_id(rs.getString("cg_id"));
					prefer.setCategory_nm(rs.getString("cg_name"));
					prefer_list.add(prefer);

				}
				response.setPrefer_list(prefer_list);

			}

			response.setResponse_msg("RecommendCategory_success");

		} catch (Exception e) {

			response.setResponse_msg("RecommendCategory_fail");
			e.printStackTrace();
		} finally {

			try {
				DBManager.getInstance().close(conn, pstmt, pstmt, rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return response;

	}

	public ResponseDTO getQuestionAndCategory(RequestDTO request) {

		try {

			conn = DBManager.getInstance().getConnection();
			response = new ResponseDTO();
			response.setPrefer_list(getPreferVO_list(conn));
			response.setQuestion_list(getQuestionVO_list(conn));
			response.setResponse_msg("GetQuestionAndCategory_success");

		} catch (Exception e) {

			e.printStackTrace();
			response.setResponse_msg("GetQuestionAndCategory_fail");

		} finally {

			try {
				DBManager.getInstance().close(conn, pstmt, pstmt, rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return response;
	}

	public ArrayList<PreferDTO> getPreferVO_list(Connection con) {

		ArrayList<PreferDTO> prefer_list = new ArrayList<PreferDTO>();

		try {

//			conn = DBManager.getInstance().getConnection();
			this.conn = con;
			String sql = "select cg_id,cg_name from activity_cg ";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				PreferDTO prefer = new PreferDTO();
				prefer.setCg_id(rs.getString("cg_id"));
				prefer.setCategory_nm(rs.getString("cg_name"));
				prefer_list.add(prefer);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return prefer_list;

	}

	public ArrayList<QuestionDTO> getQuestionVO_list(Connection con) {

		ArrayList<QuestionDTO> question_list = new ArrayList<QuestionDTO>();

		try {

//			conn = DBManager.getInstance().getConnection();
			this.conn = con;
			String sql = "select weather_type,text from question";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				QuestionDTO question = new QuestionDTO();
				question.setWeather_type(rs.getString("weather_type"));
				question.setQuestion_text(rs.getString("text"));
				question_list.add(question);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return question_list;

	}

	public boolean checkIfUserScoreExist(Long user_idx, Long cg_idx, String weather_type, Connection con) {

		boolean b_check = false;

		try {

			this.conn = con;

			String sql = "select count(*) from " + weather_type + " where user_idx = ? and cg_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, user_idx);
			pstmt.setLong(2, cg_idx);
			rs = pstmt.executeQuery();

			if (rs.next() && rs.getInt(1) > 0) {

				b_check = true;
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return b_check;
	}

	public int getUserReviewCnt(RequestDTO request, Connection con) {

		int review_cnt = 0;

		try {

			this.conn = con;
			String sql = "select count(*) from review where user_id =? and content_id = ? and weather_type =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, request.getUser().getUser_id());
			pstmt.setString(2, request.getReview_list().get(0).getContent_id());
			pstmt.setString(3, request.getWeather_type());

			rs = pstmt.executeQuery();
			if (rs.next()) {

				review_cnt = rs.getInt(1);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return review_cnt;
	}

	public ResponseDTO insertUserReview(RequestDTO request) throws SQLException {

		boolean userScoreExist = false;
		String checkSQL = null;
		Long user_idx = null;
		ArrayList<Long> cg_idx_list = new ArrayList<Long>();
		int review_cnt = 0;

		try {

			response = new ResponseDTO();

			conn = DBManager.getInstance().getConnection();
			conn.setAutoCommit(false);

			user_idx = getUserIndex(request.getUser().getUser_id(), conn);
			cg_idx_list = getReviewCategoryIndexList(request.getReview_list(), conn);
			userScoreExist = checkIfUserScoreExist(user_idx, cg_idx_list.get(0), request.getWeather_type(), conn);
			review_cnt = getUserReviewCnt(request, conn);

			System.out.println("user_idx :" + user_idx);
			System.out.println("cg_idx : " + cg_idx_list.get(0));
			System.out.println("cnt : " + review_cnt);
			System.out.println("flag : " + userScoreExist);
			if (userScoreExist) {

				if (review_cnt == 0) {

					String update_sql_1 = "update " + request.getWeather_type()
							+ " set score = ? where user_idx = ? and cg_idx = ?";
					pstmt = conn.prepareStatement(update_sql_1);
					System.out.println("score : " + request.getReview_list().get(0).getReview_score());
					pstmt.setFloat(1, request.getReview_list().get(0).getReview_score());
					pstmt.setLong(2, user_idx);
					pstmt.setLong(3, cg_idx_list.get(0));
					checkSQL = "update 0";

				} else {

					String update_sql = "update " + request.getWeather_type()
							+ " set score = ( (score * ?) + ? ) / ? where user_idx = ? and cg_idx = ?";
					pstmt = conn.prepareStatement(update_sql);
					pstmt.setInt(1, review_cnt);
					pstmt.setFloat(2, request.getReview_list().get(0).getReview_score());
					pstmt.setInt(3, review_cnt + 1);
					pstmt.setLong(4, user_idx);
					pstmt.setLong(5, cg_idx_list.get(0));
					checkSQL = "update ";
				}

			} else {

				String insert_sql = "insert into " + request.getWeather_type() + " value(?,?,?)";
				pstmt = conn.prepareStatement(insert_sql);
				pstmt.setLong(1, user_idx);
				pstmt.setLong(2, cg_idx_list.get(0));
				pstmt.setFloat(3, request.getReview_list().get(0).getReview_score());
				checkSQL = "insert";
			}

			sql_flag = pstmt.executeUpdate();

			if (sql_flag > 0) {

				System.out.println(checkSQL + " user score success");
			} else {

				System.out.println(checkSQL + " user score fail");
			}

			String sql = "insert into review(user_id, content_id, review_text, score, time, cg_id, weather_type, title ) value(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, request.getUser().getUser_id());
			pstmt.setString(2, request.getReview_list().get(0).getContent_id());
			pstmt.setString(3, request.getReview_list().get(0).getReview_text());
			pstmt.setFloat(4, request.getReview_list().get(0).getReview_score());
			java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(LocalDateTime.now());
			pstmt.setTimestamp(5, timestamp);
			pstmt.setString(6, request.getReview_list().get(0).getCategory_id());
			pstmt.setString(7, request.getWeather_type());
			pstmt.setString(8, request.getReview_list().get(0).getTitle());

			sql_flag = 0;
			sql_flag = pstmt.executeUpdate();

			if (sql_flag > 0) {

				System.out.println("insert review success");
				response.setResponse_msg("InsertUserReview_success");
				conn.commit();
				conn.setAutoCommit(true);

			} else {

				System.out.println("rollback");
				conn.rollback();
				response.setResponse_msg("InsertUserReview_fail");

			}

		} catch (Exception e) {

			e.printStackTrace();
			response.setResponse_msg("InsertUserReview_fail");
			System.out.println("rollback");
			conn.rollback();

		} finally {

			try {
				DBManager.getInstance().close(conn, pstmt, pstmt, rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return response;

	}

	public ResponseDTO DeleteUserReview(RequestDTO request) throws SQLException {

		ArrayList<ReviewDTO> review_list = new ArrayList<ReviewDTO>();
		Long user_idx = null;
		ArrayList<Long> cg_idx_list = new ArrayList<Long>();

		int review_cnt = 0;

		try {

			response = new ResponseDTO();
			review_list = request.getReview_list();

			conn = DBManager.getInstance().getConnection();
			conn.setAutoCommit(false);

			user_idx = getUserIndex(request.getUser().getUser_id(), conn);
			cg_idx_list = getReviewCategoryIndexList(request.getReview_list(), conn);

			int cnt = 0;
			for (int i = 0; i < review_list.size(); i++) {
				String sql = "select count(*), review_id from review where user_id= ? and content_id = ? and weather_type = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, request.getUser().getUser_id());
				pstmt.setString(2, review_list.get(i).getContent_id());
				pstmt.setString(3, review_list.get(i).getReview_type());

				rs = pstmt.executeQuery();

				if (rs.next()) {

					review_cnt = rs.getInt(1);
					System.out.println("cnt : " + review_cnt);
					System.out.println(review_list.get(i));

				}

				if (review_cnt == 1) {

					String update_sql = "update " + review_list.get(i).getReview_type()
							+ " set score = 0 where user_idx = ? and cg_idx = ?";
					pstmt = conn.prepareStatement(update_sql);
					pstmt.setLong(1, user_idx);
					pstmt.setLong(2, cg_idx_list.get(i));
					System.out.println("update 0");

				} else {

					String update_sql = "update " + review_list.get(i).getReview_type()
							+ " set score = ( (score * ?) - ? ) / ? where user_idx = ? and cg_idx = ?";
					pstmt = conn.prepareStatement(update_sql);
					pstmt.setInt(1, review_cnt);
					pstmt.setFloat(2, review_list.get(i).getReview_score());
					pstmt.setInt(3, review_cnt - 1);
					pstmt.setLong(4, user_idx);
					pstmt.setLong(5, cg_idx_list.get(i));
					System.out.println("update");

				}

				sql_flag = pstmt.executeUpdate();

				if (sql_flag > 0) {

					System.out.println("update user score o");
				} else {

					System.out.println("update user score x");
				}

				String sql2 = "delete from review where review_id = ? ";
				pstmt = conn.prepareStatement(sql2);

				pstmt.setLong(1, review_list.get(i).getReview_idx());
				sql_flag = pstmt.executeUpdate();

				if (sql_flag > 0) {

					System.out.println("delete review success");

				} else {
					System.out.println("delete review fail");

				}
				cnt++;

			} // for

			if (cnt == review_list.size()) {

				conn.commit();
				conn.setAutoCommit(true);
				response.setResponse_msg("DeleteUserReview_success");
			} else {

				System.out.println("rollback");
				conn.rollback();
				response.setResponse_msg("DeleteUserReview_fail");
			}

		} catch (Exception e) {

			if (conn != null) {

				e.printStackTrace();
				System.out.println("rollback");
				conn.rollback();
				response.setResponse_msg("DeleteUserReview_fail");

			}
		} finally {

			try {
				DBManager.getInstance().close(conn, pstmt, pstmt, rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return response;

	}

	public ResponseDTO getUserReview(RequestDTO request) {

		ArrayList<ReviewDTO> review_list = new ArrayList<ReviewDTO>();

		try {

			response = new ResponseDTO();
			conn = DBManager.getInstance().getConnection();

			String sql = "select * from review where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, request.getUser().getUser_id());
			rs = pstmt.executeQuery();

			while (rs.next()) {

				ReviewDTO review = new ReviewDTO();
				review.setReview_id(rs.getString("user_id"));
				review.setContent_id(rs.getString("content_id"));
				review.setReview_text(rs.getString("review_text"));
				review.setReview_score(rs.getFloat("score"));
				SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				review.setTime(sdf.format(rs.getTimestamp("time")));
				review.setTitle(rs.getString("title"));
				review.setReview_type(rs.getString("weather_type"));
				review.setCategory_id(rs.getString("cg_id"));
				review.setReview_idx(rs.getLong("review_id"));
				review_list.add(review);

			}

			if (review_list.size() == 0) {

				response.setResponse_msg("GetUserReview_fail");

			} else {

				response.setUser(request.getUser());
				response.setReview_list(review_list);
				response.setResponse_msg("GetUserReview_success");

			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				DBManager.getInstance().close(conn, pstmt, pstmt, rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return response;

	}

	public ResponseDTO getContentReview(RequestDTO request) {

		ArrayList<ReviewDTO> review_list = new ArrayList<ReviewDTO>();

		try {

			response = new ResponseDTO();
			conn = DBManager.getInstance().getConnection();

			String sql = "select user_id, review_text, score, time from review where content_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, request.getReview_list().get(0).getContent_id());
			rs = pstmt.executeQuery();

			while (rs.next()) {

				ReviewDTO review = new ReviewDTO();
				review.setReview_id(rs.getString("user_id"));
				review.setReview_text(rs.getString("review_text"));
				SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				review.setTime(sdf.format(rs.getTimestamp("time")));
				review.setReview_score(rs.getFloat("score"));
				review_list.add(review);

			}

			if (review_list.size() == 0) {

				response.setResponse_msg("GetContentReview_fail");

			} else {
				response.setReview_list(review_list);
				response.setResponse_msg("GetContentReview_success");

			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				DBManager.getInstance().close(conn, pstmt, pstmt, rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return response;

	}

	public void InsertUser_dummy() {

		try {
			conn = DBManager.getInstance().getConnection();

			for (int i = 0; i < 100; i++) {

				String id = "userID_" + i;
				String pw = "password_" + i;
				String nm = "userName_" + i;

				String sql = "insert into user(userId, userPw, userNm,availability) values(?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				pstmt.setString(3, nm);
				pstmt.setInt(4, 1);
				pstmt.executeUpdate();
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				DBManager.getInstance().close(conn, pstmt, pstmt, rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void InsertUserScore_dummy() {

		try {
			conn = DBManager.getInstance().getConnection();

			String sql = "select cg_idx from activity_cg";
			stmt = conn.createStatement();

			ArrayList<Long> cg_list = new ArrayList<>();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {

				cg_list.add(rs.getLong("cg_idx"));

			}
			System.out.println(cg_list.toString());

			String sql2 = "select user_idx from user";
			ArrayList<Long> user_list = new ArrayList<>();
			rs = stmt.executeQuery(sql2);
			while (rs.next()) {

				user_list.add(rs.getLong("user_idx"));

			}

			String sql3 = "select weather_type from question";
			ArrayList<String> weather_list = new ArrayList<>();
			rs = stmt.executeQuery(sql3);
			while (rs.next()) {

				weather_list.add(rs.getString("weather_type"));

			}

			// String sql3 = "insert into user_score2 value(?,?,?,?)";

			for (int i = 0; i < 5000; i++) {

				int nUser = (int) (Math.random() * user_list.size());
				int nWeather = (int) (Math.random() * weather_list.size());
				int nCg = (int) (Math.random() * cg_list.size());
				double f_score = (Math.random() * 5);
				f_score = Math.round(f_score * 10) / 10.0;
				// String w_id = "type_" + nQ;

				// System.out.println(w_id);
				System.out.println(weather_list.get(nWeather));
				System.out.println(user_list.get(nUser));
				System.out.println(cg_list.get(nCg));
				System.out.println(f_score);

				String sql4 = "insert into " + weather_list.get(nWeather) + " value(?,?,?)";
				// String sql5 = "insert into type_0 value(?,?,?)";
				pstmt = conn.prepareStatement(sql4);
				pstmt.setLong(1, user_list.get(nUser));
				pstmt.setLong(2, cg_list.get(nCg));
				pstmt.setFloat(3, (float) f_score);
				pstmt.executeUpdate();
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				DBManager.getInstance().close(conn, pstmt, pstmt, rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}

	}
}
