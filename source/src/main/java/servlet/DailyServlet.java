package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DailyDAO;
import dao.QuestionDAO;
import dto.DailyDTO;
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
		
		//結果表示などに用いるデータ
		double negativeRate, positiveRate, activeIndex;
		
		negativeRate= 12.0;
		positiveRate= 10.0;
		activeIndex= 20.0;

		//各質問の点数とABC項目を配列で整理する
		ArrayList<Integer> point = new ArrayList<>();
		ArrayList<String> emoType = new ArrayList<>();
		
		for (int qGet = 0; qGet < 14; qGet++) {
			
			//質問のポイントを取得して格納
			String qAns = request.getParameter(
				"q_" + (qGet + 1)
			);
			point.add(Integer.parseInt(qAns));

			//質問のABC項目を取得して格納
			String qEmo = request.getParameter(
				"qType"
			);
			emoType.add(qEmo);
		}

		//各項目を合算し、分析する

		//その他のパラメータ
		int dailyId = Integer.parseInt(request.getParameter("dailyId"));
		String freeForm = request.getParameter("freeForm");
		String photo = request.getParameter("photo");
		String positive = request.getParameter("positive");
		int emotion_id = Integer.parseInt(request.getParameter("emotion_id"));
		String updated_at = request.getParameter("updated_at");
		String created_at = request.getParameter("created_at");


		// 登録処理を行う
		DailyDAO dDao = new DailyDAO();
		if (dDao.insert(new DailyDTO(0, user_id, freeForm, photo, positive, emotionId, typeId, negativeRate, positiveRate, activeIndex, updated_at, created_at))) { // 登録成功
			response.sendRedirect(request.getContextPath() + "/WEB-INF/jsp/dailyRev.jsp");
		} else { // 登録失敗
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		    dispatcher.forward(request, response);
		}

	}
}
