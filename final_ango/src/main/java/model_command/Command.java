package model_command;

import dto.RequestDTO;
import dto.ResponseDTO;

public abstract class Command {

	
	public abstract ResponseDTO doCommand(RequestDTO client_request);

	public void printVO(RequestDTO client_request, ResponseDTO server_response) {

		System.out.println(client_request.toString());
		System.out.println(server_response.toString());
	}

}
