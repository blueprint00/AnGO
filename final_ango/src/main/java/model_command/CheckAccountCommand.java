package model_command;

import dao.*;
import dto.RequestDTO;
import dto.ResponseDTO;

public class CheckAccountCommand extends Command {

	private UserDAO userDAO;
	private ResponseDTO server_response = new ResponseDTO();

	public CheckAccountCommand(UserDAO userDAO) {

		this.userDAO = userDAO;

	}

	@Override
	public ResponseDTO doCommand(RequestDTO client_request) {

		// TODO Auto-generated method stub

		boolean flag;

		try {
//

			flag = userDAO.checkUserID(client_request.getUser().getUser_id());
			if (flag) {
				server_response.setResponse_msg("CheckAccount_success");

			} else {

				server_response.setResponse_msg("CheckAccount_fail");

			}
//			server_response = userDAO.checkUserID(client_request);

		} catch (Exception e) {

			e.printStackTrace();
			server_response.setResponse_msg("CheckAccount_fail");
		}

		return server_response;
	}

}
