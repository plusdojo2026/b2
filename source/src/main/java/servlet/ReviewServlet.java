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
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		//リクエストで取得。後で
		int page =1;

		// とある週のデータを取得
		WeeklyDAO dao = new WeeklyDAO();

		//*ここで渡す期間とユーザーIDを指定*振り返り画面作ったあとに要変更！！
		List<WeeklyDTO> List = dao.selectAll(1, 1);
		
		// JSP に渡す
		request.setAttribute("weekList", List);
		request.setAttribute("currentPage", page);


	// 振り返りページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/review.jsp");
		dispatcher.forward(request, response);
	}
}	

