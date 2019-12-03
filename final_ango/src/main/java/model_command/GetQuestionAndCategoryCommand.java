package model_command;

import dao.*;
import dto.RequestDTO;
import dto.ResponseDTO;
import model_utility.Token;

public class GetQuestionAndCategoryCommand extends Command {

	private UserDAO userDAO;
	private ResponseDTO server_response = new ResponseDTO();;

	public GetQuestionAndCategoryCommand(UserDAO userDAO) {

		this.userDAO = userDAO;

	}

	@Override
	public ResponseDTO doCommand(RequestDTO client_request) {
		// TODO Auto-generated method stub

		try {

			if (Token.checkExpToken(client_request.getToken())) {

				server_response.setToken_msg("token_not_expired");

				server_response.setPrefer_list(userDAO.getCategoryList());
				server_response.setQuestion_list(userDAO.getQuestionList());
				if (server_response.getPrefer_list() != null && server_response.getQuestion_list() != null) {

					server_response.setResponse_msg("GetQuestionAndCategory_success");
				} else {

					server_response.setResponse_msg("GetQuestionAndCategory_fail");
				}

			} else {

				server_response.setToken_msg("token_expired");

			}

		} catch (Exception e) {

			e.printStackTrace();
			server_response.setResponse_msg("GetQuestionAndCategory_fail");
		}

		return server_response;
	}

}
