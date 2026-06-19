package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WeeklyDAO;
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

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");

		// とある週のデータを取得
		WeeklyDAO dao = new WeeklyDAO();
		WeeklyDTO dto = new WeeklyDTO();
		
		//*ここで渡す期間とユーザーIDを指定*振り返り画面作ったあとに要変更！！
		dto.setWeeklyRes("2026-06-08~2026-06-14");
		dto.setUser_id(1);
		
		List<WeeklyDTO> List = dao.select(dto);

		// JSP に渡す
		request.setAttribute("weekList", List);

		// 週間結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/weeklyRev.jsp");
		dispatcher.forward(request, response);
	}
}
