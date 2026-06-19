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

		//設問を14問ランダムに選出する
		List<QuestionDTO> questions = questionDao.findRandomQuestions(14);

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
		int dailyId = Integer.parseInt(request.getParameter("dailyId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		int emotionId = Integer.parseInt(request.getParameter("emotionId"));
		String freeForm = request.getParameter("freeForm");
		String photo = request.getParameter("photo");
		String positive= request.getParameter("positive");

		String update_at = request.getParameter("update_at");
		String created_at = request.getParameter("created_at");

		//リクエストパラメータ：設問解答
		int q1 = Integer.parseInt(request.getParameter("q1"));
		int q2 = Integer.parseInt(request.getParameter("q2"));
		int q3 = Integer.parseInt(request.getParameter("q3"));
		int q4 = Integer.parseInt(request.getParameter("q4"));
		int q5 = Integer.parseInt(request.getParameter("q5"));
		int q6 = Integer.parseInt(request.getParameter("q6"));
		int q7 = Integer.parseInt(request.getParameter("q7"));
		int q8 = Integer.parseInt(request.getParameter("q8"));
		int q9 = Integer.parseInt(request.getParameter("q9"));
		int q10 = Integer.parseInt(request.getParameter("q10"));
		int q11 = Integer.parseInt(request.getParameter("q11"));
		int q12 = Integer.parseInt(request.getParameter("q12"));
		int q13 = Integer.parseInt(request.getParameter("q13"));
		int q14 = Integer.parseInt(request.getParameter("q14"));

		// double positiveRate = calcPositiveRate(q1, q2, q3);
		// double negativeRate = calcNegativeRate(q1, q2, q3);
		// double activeIndex = calcActiveIndex(q1, q2, q3);

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
