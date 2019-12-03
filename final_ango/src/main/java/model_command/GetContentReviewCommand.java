package model_command;

import dao.*;
import dto.RequestDTO;
import dto.ResponseDTO;
import dto.UserDTO;
import model_utility.Token;

public class GetContentReviewCommand extends Command {

	private UserDAO userDAO;
	private ResponseDTO server_response;

	public GetContentReviewCommand(UserDAO userDAO) {

		this.userDAO = userDAO;

	}

	@Override
	public ResponseDTO doCommand(RequestDTO client_request) {
		// TODO Auto-generated method stub

		try {

			if (Token.checkExpToken(client_request.getToken())) {

				server_response.setToken_msg("token_not_expired");

				UserDTO user = new UserDTO(Token.getUserID(client_request.getToken()));
				server_response.setUser(user);
				server_response.setReview_list(
						userDAO.getContentReview(client_request.getReview_list().get(0).getCategory_id()));
				if (server_response.getReview_list().size() == 0) {

					server_response.setResponse_msg("GetContentReview_fail");
				} else {

					server_response.setResponse_msg("GetContentReview_success");
				}
			} else {

				server_response.setToken_msg("token_expired");
			}
		} catch (Exception e) {

			e.printStackTrace();
			server_response.setResponse_msg("GetContentReview_fail");
		}

		return server_response;
	}

}
