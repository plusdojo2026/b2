package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */

@WebServlet("/DailyServlet")
public class DailyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// フォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/daily.jsp");
		dispatcher.forward(request, response);
	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログイン確認、してなければログイン画面へ
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/namecard/LoginServlet");
			return;
		}

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String emotion_id = request.getParameter("emotion_id");
		int emotion_id = Integer.parseInt(request.getParameter("emotion_id"));
		String photo = request.getParameter("photo");
		String positive= request.getParameter("positive");

		// 登録処理を行う
		DailyDAO dDao = new DailyDAO();
		if (dDao.insert()) { // 登録成功
			response.sendRedirect(request.getContextPath() + "/WEB-INF/jsp/dailyRev.jsp");
		} else { // 登録失敗
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp");
		    dispatcher.forward(request, response);
		}

	}
}
