package model_command;

import dao.UserDAO;
import dto.RequestDTO;
import dto.ResponseDTO;
import dto.UserDTO;
import model_utility.Token;

public class GetUserReviewCommand extends Command{
	
	private UserDAO userDAO;
	private ResponseDTO server_response;

	public GetUserReviewCommand(UserDAO userDAO) {

		this.userDAO = userDAO;

	}

	@Override
	public ResponseDTO doCommand(RequestDTO client_request) {
		// TODO Auto-generated method stub

		try {

			if (Token.checkExpToken(client_request.getToken())) {

				UserDTO user = new UserDTO(Token.getUserID(client_request.getToken()));
				client_request.setUser(user);
				server_response = userDAO.getUserReview(client_request);

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
