package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BonusDAO;
import dto.BonusDTO;

/**
 * Servlet implementation class BonusServlet
 */

@WebServlet("/BonusServlet")
public class BonusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//ユーザーID
		int user_id = 1;
		
		//ビンゴの内容を取得
		BonusDAO bonus = new BonusDAO();
		BonusDTO bingo = bonus.selectBingo(user_id);
		
		request.setAttribute("bingo", bingo);
		
		// ログインボーナスページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/bonus.jsp");
		dispatcher.forward(request, response);
	}
}
