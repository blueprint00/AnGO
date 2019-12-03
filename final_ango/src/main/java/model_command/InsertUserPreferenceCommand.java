package model_command;

import java.util.ArrayList;

import dao.*;
import dto.RequestDTO;
import dto.ResponseDTO;
import dto.UserDTO;
import model_utility.Token;

public class InsertUserPreferenceCommand extends Command {

	private UserDAO userDAO;
	private ResponseDTO server_response = new ResponseDTO();

	public InsertUserPreferenceCommand(UserDAO userDAO) {

		this.userDAO = userDAO;

	}

	@Override
	public ResponseDTO doCommand(RequestDTO client_request) {
		// TODO Auto-generated method stub

		boolean flag = false;
		Long user_idx = (long) 0;
		ArrayList<Long> cg_idx_list = null;

		try {

			if (Token.checkExpToken(client_request.getToken())) {

				UserDTO user = new UserDTO(Token.getUserID(client_request.getToken()));
				client_request.setUser(user);

				user_idx = userDAO.getUserIndex2(client_request.getUser().getUser_id());
				cg_idx_list = userDAO.getPreferCategoryIndexList2(client_request.getPreference_list());
				flag = userDAO.insertUserPreferenceList(user_idx, cg_idx_list, client_request.getPreference_list());

				if (flag) {
					server_response.setResponse_msg("InsertUserPreference_success");

				} else {

					server_response.setResponse_msg("InsertUserPreference_fail");
				}
				server_response.setToken_msg("token_not_expired");

			} else {

				server_response.setToken_msg("token_expired");
			}

		} catch (Exception e) {

			e.printStackTrace();
			server_response.setResponse_msg("InsertUserPreference_fail");
		}

		return server_response;
	}
}
