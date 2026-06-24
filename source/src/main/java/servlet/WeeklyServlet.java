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
 * Servlet implementation class WeeklyServlet
 */

@WebServlet("/WeeklyServlet")
public class WeeklyServlet extends HttpServlet {
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
		String weeklyRes = request.getParameter("weeklyRes");

		// とある週のデータを取得
		WeeklyDAO dao = new WeeklyDAO();
		WeeklyDTO dto = new WeeklyDTO();

		// テスト用毎日記録ダミー登録。後でDailyの方に入れてもらう（insertの後ろ）
		//WeeklyDAO wDao = new WeeklyDAO();
		//wDao.aggregate(daily);
		//更新の方もあるならそっちにも追加しとくこと

		dto.setWeeklyRes(weeklyRes);
		dto.setUser_id(userId);

		List<WeeklyDTO> List = dao.select(dto);

		// JSP に渡す
		request.setAttribute("weekList", List);

		// 週間結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/weeklyRev.jsp");
		dispatcher.forward(request, response);
	}
}
