package model_command;

import dao.UserDAO;
import dto.RequestDTO;
import dto.ResponseDTO;
import dto.UserDTO;
import model_utility.Token;

public class GetUserReviewCommand extends Command {

	private UserDAO userDAO;
	private ResponseDTO server_response = new ResponseDTO();

	public GetUserReviewCommand(UserDAO userDAO) {

		this.userDAO = userDAO;

	}

	@Override
	public ResponseDTO doCommand(RequestDTO client_request) {
		// TODO Auto-generated method stub
		try {

			if (Token.checkExpToken(client_request.getToken())) {

				UserDTO user = new UserDTO(Token.getUserID(client_request.getToken()));
				server_response.setUser(user);
				server_response.setToken_msg("token_not_expired");
				server_response.setReview_list(userDAO.getUserReview(Token.getUserID(client_request.getToken())));
				if (server_response.getReview_list().size() == 0) {

					server_response.setResponse_msg("GetUserReview_fail");
				} else {

					server_response.setResponse_msg("GetUserReview_success");
				}
			} else {

				server_response.setToken_msg("token_expired");
			}
		} catch (Exception e) {

			e.printStackTrace();
			server_response.setResponse_msg("GetUserReview_fail");
		}

		return server_response;
	}

}
