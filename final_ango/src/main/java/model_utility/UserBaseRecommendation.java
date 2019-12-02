package model_utility;

import java.sql.SQLException;

import org.apache.mahout.cf.taste.common.NoSuchUserException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;

public class UserBaseRecommendation {

	private static List<RecommendedItem> recommendations;

	public static List<RecommendedItem> getRecommendCategory(Long user_idx, String weather_type) {
		// TODO Auto-generated method stub

		try {
			MysqlDataSource dbsource = new MysqlDataSource();
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			BasicDataSource ds = (BasicDataSource) envCtx.lookup("jdbc/final_ango");
			dbsource = unwrap(ds);

			DataModel model = new MySQLJDBCDataModel(dbsource, weather_type, "user_idx", "cg_idx", "score", "");

			UserSimilarity similarity = new PearsonCorrelationSimilarity(model, Weighting.WEIGHTED);
//			두 사용자의 선호가 겹쳐지는 아이템의 숫자를 고려하지 않는다.
//			두 사용자의 아이템 선호 중 단지 하나만 겹친다면 계산 방식을 어떻게 정의할지 모르기 때문에 상관관계를 계산할 수 없다.
//			선호값의 열이 모두 일치하는 경우에도 계산할 수 없다.
//			위 문제를 개선할 가중치 처리
			
			
			
			// UserNeighborhood neighborhood = new NearestNUserNeighborhood(5,similarity,model);
			UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.7, similarity, model);
			Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

			recommendations = recommender.recommend(user_idx, 10);

			for (RecommendedItem recommendation : recommendations) {

				System.out.println(recommendation);
			}

			System.out.println("recommend finish");
		} catch (NoSuchUserException e) {
			// TODO: handle exception
			System.out.println("cold start");
			recommendations = null;
			e.printStackTrace();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return recommendations;

	}

	protected static MysqlDataSource unwrap(BasicDataSource ds) throws SQLException {
		MysqlDataSource mysqlDs = new MysqlDataSource();

		mysqlDs.setUser(ds.getUsername());
		mysqlDs.setPassword(ds.getPassword());
		mysqlDs.setURL(ds.getUrl());
		mysqlDs.setCachePreparedStatements(true);
		mysqlDs.setCachePrepStmts(true);
		mysqlDs.setCacheResultSetMetadata(true);
		mysqlDs.setAlwaysSendSetIsolation(false);
		mysqlDs.setElideSetAutoCommits(true);

		return mysqlDs;
	}

}
