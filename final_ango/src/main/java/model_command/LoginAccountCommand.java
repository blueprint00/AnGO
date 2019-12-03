package model_command;

import dao.*;
import dto.RequestDTO;
import dto.ResponseDTO;
import model_utility.Token;

public class LoginAccountCommand extends Command {

	private UserDAO userDAO;
	private ResponseDTO server_response = new ResponseDTO();

	public LoginAccountCommand(UserDAO userDAO) {

		this.userDAO = userDAO;

	}

	@Override
	public ResponseDTO doCommand(RequestDTO client_request) {

		boolean flag;
		// TODO Auto-generated method stub

		try {

			flag = userDAO.LoginAccount(client_request);
			if (flag) {
				server_response.setResponse_msg("LoginAccount_success");
				server_response.setToken(Token.createToken(client_request.getUser().getUser_id()));
				server_response.setAvailability(userDAO.getUserAvailability(client_request.getUser().getUser_id()));

			} else {
				server_response.setResponse_msg("LoginAccount_fail");

			}

		} catch (Exception e) {

			e.printStackTrace();
			server_response.setResponse_msg("LoginAccount_fail");
		}

		return server_response;
	}

}
