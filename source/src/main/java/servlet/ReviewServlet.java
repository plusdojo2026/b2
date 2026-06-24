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

import dao.WeeklyDAO;
import dto.UserDTO;
import dto.WeeklyDTO;

/**
 * Servlet implementation class reviewServlet
 */

@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
				HttpSession session = request.getSession();
				UserDTO user = (UserDTO) session.getAttribute("user");
				if (user == null) {
					response.sendRedirect("LoginServlet");
					return;
				}
				int userId = user.getId();
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");

		//Daily用処理
		
		
		// Weekly用処理
		int weekPage = 1;
		if (request.getParameter("weekPage") != null) {
			weekPage = Integer.parseInt(request.getParameter("weekPage"));
		}
		WeeklyDAO dao = new WeeklyDAO();
		// データ取得
		List<WeeklyDTO> weekList = dao.selectAll(userId, weekPage);
		List<WeeklyDTO> weekGraph = dao.latestSelect(userId);
		// 総件数
		int totalCount = dao.countAll(userId);
		// ページ計算
		int limit = 5;
		int totalWeekPage = (int) Math.ceil((double) totalCount / limit);
		
		// quick用処理
		

		// JSP に渡す
		request.setAttribute("weekList", weekList);
		request.setAttribute("weekPage", weekPage);
		request.setAttribute("totalWeekPage", totalWeekPage);
		request.setAttribute("latestWeek", weekGraph);

		// 振り返りページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/review.jsp");
		dispatcher.forward(request, response);
	}
}	

