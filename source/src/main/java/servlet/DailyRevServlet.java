package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DailyDAO;
import dto.DailyDTO;

/**
 * Servlet implementation class LoginServlet
 */

@WebServlet("/DailyRevServlet")
public class DailyRevServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//毎日記録の最新一件（当日入力したもの）を取得して格納
		DailyDAO dDao = new DailyDAO();
		DailyDTO todayRev = dDao.select();
		
		request.setAttribute("todayRev", todayRev);

		// フォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/dailyRev.jsp");
		dispatcher.forward(request, response);
	}

}