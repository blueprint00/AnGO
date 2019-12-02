package model_command;

import dao.*;
import dto.RequestDTO;
import dto.ResponseDTO;

public class CheckAccountCommand extends Command {

	private UserDAO userDAO;
	private ResponseDTO server_response;

	public CheckAccountCommand(UserDAO userDAO) {

		this.userDAO = userDAO;

	}

	@Override
	public ResponseDTO doCommand(RequestDTO client_request) {
		// TODO Auto-generated method stub

		try {

			server_response = userDAO.checkUserID(client_request);

		} catch (Exception e) {

			e.printStackTrace();
			server_response.setResponse_msg("CheckAccount_fail");
		}
		
		return server_response;
	}

}
