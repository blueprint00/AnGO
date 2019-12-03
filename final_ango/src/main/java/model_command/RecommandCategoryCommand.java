package model_command;

import java.util.List;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import dao.*;
import dto.RequestDTO;
import dto.ResponseDTO;
import dto.UserDTO;
import model_utility.Token;
import model_utility.UserBaseRecommendation;

public class RecommandCategoryCommand extends Command {

	private UserDAO userDAO;
	private ResponseDTO server_response = new ResponseDTO();

	public RecommandCategoryCommand(UserDAO userDAO) {

		this.userDAO = userDAO;

	}

	@Override
	public ResponseDTO doCommand(RequestDTO client_request) {
		// TODO Auto-generated method stub

		List<RecommendedItem> recommend_list = null;
		Long user_idx = (long) 0;

		try {

			if (Token.checkExpToken(client_request.getToken())) {

				server_response.setToken_msg("token_not_expired");

				user_idx = userDAO.getUserIndex2(Token.getUserID(client_request.getToken()));

				recommend_list = UserBaseRecommendation.getRecommendCategory(user_idx,
						client_request.getWeather_type());

				if (recommend_list == null) {
					System.out.println("recommend is null");
					server_response.setPrefer_list(userDAO.getRandomCategory());

				} else if (recommend_list.size() == 0) {

					System.out.println("recommend size is 0");
					server_response.setPrefer_list(userDAO.getRandomCategory());
				} else {

					server_response.setPrefer_list(userDAO.getUserRecommendCategory(recommend_list));
				}
				server_response.setResponse_msg("RecommendCategory_success");

			} else {

				server_response.setToken_msg("token_expired");

			}

		} catch (Exception e) {

			e.printStackTrace();
			server_response.setResponse_msg("RecommendCategory_fail");
		}

		return server_response;
	}
}
