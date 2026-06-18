package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dto.UserDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String pw = request.getParameter("pw");
		
		// ログイン処理を行う
				UserDAO lDao = new UserDAO();
				UserDTO user = lDao.Login(userName, pw);  //ログイン成功したらUserDTOを返す
				if (user != null) { // ログイン成功したとき
					// セッションスコープにユーザー情報を格納する
					HttpSession session = request.getSession();
					session.setAttribute("user", user);  //user=UserDTOをそのまま保存
				
					response.sendRedirect("home.jsp"); //homeページにとぶ
					
				}else {// ログイン失敗したとき
					request.setAttribute("loginError", "ユーザー名またはパスワードが違います");
					// loginページにフォワードする
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
					dispatcher.forward(request, response);
				}
	}
}
