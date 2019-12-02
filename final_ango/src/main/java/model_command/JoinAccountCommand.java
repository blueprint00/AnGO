package model_command;

import dao.*;
import dto.RequestDTO;
import dto.ResponseDTO;

public class JoinAccountCommand extends Command {

	private UserDAO userDAO;
	private ResponseDTO server_response;

	public JoinAccountCommand(UserDAO userDAO) {

		this.userDAO = userDAO;

	}

	@Override
	public ResponseDTO doCommand(RequestDTO client_request) {
		// TODO Auto-generated method stub

		try {

			server_response = userDAO.createUserAccount(client_request);

		} catch (Exception e) {

			e.printStackTrace();
			server_response.setResponse_msg("JoinAccount_fail");
		}
		
		return server_response;
	}

}
