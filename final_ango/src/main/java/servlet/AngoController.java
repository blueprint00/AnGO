package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.google.gson.Gson;

import dao.UserDAO;
import dto.RequestDTO;
import dto.ResponseDTO;
import model_command.*;

/**
 * Servlet implementation class AngoController
 */
@WebServlet("/Dispacher2")
public class AngoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDTO client_request = null;
	private ResponseDTO server_response = null;
	private Gson gson = new Gson();
	private String client_json = null;
	private UserDAO userDAO = null;
	private String command;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AngoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		String body = null;
		// String command = null;
		JSONParser parser = new JSONParser();
		PrintWriter out = null;

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		try {

			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
			body = stringBuilder.toString();

			client_json = ((JSONObject) parser.parse(body)).toString();
			client_request = gson.fromJson(client_json, RequestDTO.class);
			command = client_request.getRequest_msg();

			userDAO = new UserDAO();
			switch (command) {
			case "CheckAccount":

				CheckAccountCommand checkAccountCommand = new CheckAccountCommand(userDAO);
				server_response = checkAccountCommand.doCommand(client_request);
				break;

			case "JoinAccount":

				JoinAccountCommand joinAccountCommand = new JoinAccountCommand(userDAO);
				server_response = joinAccountCommand.doCommand(client_request);
				break;

			case "LoginAccount":

				LoginAccountCommand loginAccountCommand = new LoginAccountCommand(userDAO);
				server_response = loginAccountCommand.doCommand(client_request);
				break;

			case "GetQuestionAndCategory":

				GetQuestionAndCategoryCommand getQuestionAndCategoryCommand = new GetQuestionAndCategoryCommand(
						userDAO);
				server_response = getQuestionAndCategoryCommand.doCommand(client_request);
				break;

			case "InsertUserPreference":

				InsertUserPreferenceCommand insertUserPreferecCommand = new InsertUserPreferenceCommand(userDAO);
				server_response = insertUserPreferecCommand.doCommand(client_request);
				break;

			case "RecommendCategory":

				RecommandCategoryCommand recommandCategoryCommand = new RecommandCategoryCommand(userDAO);
				server_response = recommandCategoryCommand.doCommand(client_request);
				break;

			case "GetContentReview":
				GetContentReviewCommand getUserReviewCommand = new GetContentReviewCommand(userDAO);
				server_response = getUserReviewCommand.doCommand(client_request);
				break;

			case "InsertUserReview":
				InsertUserReviewCommand insertUserReviewCommand = new InsertUserReviewCommand(userDAO);
				server_response = insertUserReviewCommand.doCommand(client_request);
				break;

			case "GetUserReview":

				GetUserReviewCommand getUserReview2 = new GetUserReviewCommand(userDAO);
				server_response = getUserReview2.doCommand(client_request);
				break;

			case "DeleteUserReview":

				DeleteUserReview deleteUserReview = new DeleteUserReview(userDAO);
				server_response = deleteUserReview.doCommand(client_request);
				break;

			default:
				System.out.println("request_msg not match : " + command);
				break;
			}

			out = response.getWriter();
			out.println(gson.toJson(server_response));
			out.flush();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		printMsg();

	}

	public void printMsg() {

		System.out.println(
				"----------------------------------------------------------------------------------------------------------------------------------------------------------");

		System.out.println("dispacher2 : " + new Date());
		System.out.println("command : " + command);
		System.out.println("input : " + client_json);
		System.out.println(client_request.toString());
		System.out.println(server_response.toString());
		System.out.println("output : " + gson.toJson(server_response));
		System.out.println(
				"----------------------------------------------------------------------------------------------------------------------------------------------------------");

	}

}
