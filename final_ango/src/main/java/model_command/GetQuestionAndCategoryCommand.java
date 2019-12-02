package model_command;

import dao.*;
import dto.RequestDTO;
import dto.ResponseDTO;
import model_utility.Token;

public class GetQuestionAndCategoryCommand extends Command {

	private UserDAO userDAO;
	private ResponseDTO server_response;

	public GetQuestionAndCategoryCommand(UserDAO userDAO) {

		this.userDAO = userDAO;

	}

	@Override
	public ResponseDTO doCommand(RequestDTO client_request) {
		// TODO Auto-generated method stub

		try {

			if (Token.checkExpToken(client_request.getToken())) {

				server_response = new ResponseDTO();
				server_response = userDAO.getQuestionAndCategory(client_request);

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
