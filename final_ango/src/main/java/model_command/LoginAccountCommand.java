package model_command;

import dao.*;
import dto.RequestDTO;
import dto.ResponseDTO;

public class LoginAccountCommand extends Command {

	private UserDAO userDAO;
	private ResponseDTO server_response;

	public LoginAccountCommand(UserDAO userDAO) {

		this.userDAO = userDAO;

	}

	@Override
	public ResponseDTO doCommand(RequestDTO client_request) {
		// TODO Auto-generated method stub

		try {

			server_response = userDAO.LoginAccount(client_request);

		} catch (Exception e) {

			e.printStackTrace();
			server_response.setResponse_msg("LoginAccount_fail");
		}
		
		return server_response;
	}

}
