package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DailyDAO;
import dao.QuestionDAO;
import dto.QuestionDTO;

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
		QuestionDAO qDAO = new QuestionDAO();
		List<QuestionDTO> qList = qDAO.select();
		
		//設問を格納
		request.setAttribute("qList", qList);

		// フォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/daily.jsp");
		dispatcher.forward(request, response);
	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		int dailyId = Integer.parseInt(request.getParameter("dailyId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		String freeForm = request.getParameter("freeForm");
		String photo = request.getParameter("photo");
		int emotion_id = Integer.parseInt(request.getParameter("emotion_id"));
		int type_id = Integer.parseInt(request.getParameter("type_id"));
		double negative= request.getParameter("negative");
		double positive= request.getParameter("positive");
		double activeIndex= request.getParameter("activeIndex");
		int yearWeek = Integer.parseInt(request.getParameter("yearWeek"));


		String updated_at = request.getParameter("updated_at");
		String created_at = request.getParameter("created_at");

				// リクエストパラメータを取得する
		// for(QuestionDTO q : qList) {
		// 	String qAns = request.getParameter(
		// 		"q_" + q.getId();
		// 	);

		// 	int score = Integer.parseInt(answer);
		// }

		// double positiveRate = calcPositiveRate(q1, q2, q3);
		// double negativeRate = calcNegativeRate(q1, q2, q3);
		// double activeIndex = calcActiveIndex(q1, q2, q3);

		// 登録処理を行う
		DailyDAO dDao = new DailyDAO();
		if (dDao.insert()) { // 登録成功
			response.sendRedirect(request.getContextPath() + "/WEB-INF/jsp/dailyRev.jsp");
		} else { // 登録失敗
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		    dispatcher.forward(request, response);
		}

	}
}
