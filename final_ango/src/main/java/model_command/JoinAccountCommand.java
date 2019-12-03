package model_command;

import dao.*;
import dto.RequestDTO;
import dto.ResponseDTO;
import model_utility.Token;

public class JoinAccountCommand extends Command {

	private UserDAO userDAO;
	private ResponseDTO server_response = new ResponseDTO();

	public JoinAccountCommand(UserDAO userDAO) {

		this.userDAO = userDAO;

	}

	@Override
	public ResponseDTO doCommand(RequestDTO client_request) {
		// TODO Auto-generated method stub

		boolean flag;

		try {

			flag = userDAO.createUserAccount(client_request);
			if (flag) {
				server_response.setResponse_msg("JoinAccount_success");
				server_response.setToken(Token.createToken(client_request.getUser().getUser_id()));

			} else {

				server_response.setResponse_msg("JoinAccount_fail");

			}

		} catch (Exception e) {

			e.printStackTrace();
			server_response.setResponse_msg("JoinAccount_fail");
		}

		return server_response;
	}

}
