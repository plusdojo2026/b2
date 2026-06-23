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
import dto.AnalysisDTO;

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
		QuestionDAO qDao = new QuestionDAO();
		AnalysisDTO result = qDao.analyze(point, emoType);

		int typeId = result.getTypeId();
		double negativeRate = result.getNegativeRate();
		double positiveRate = result.getPositiveRate();
		double activeIndex = result.getActiveIndex();
		double emoBalance = result.getEmoBalance();

		//その他のパラメータ
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		String freeForm = request.getParameter("freeForm");
		String photo = request.getParameter("photo");
		String positive = request.getParameter("positive");
		int emotion_id = Integer.parseInt(request.getParameter("emotion_id"));


		// 登録処理を行う
		DailyDAO dDao = new DailyDAO();
		if (dDao.insert(new DailyDTO(0, user_id, freeForm, photo, positive, emotionId, typeId, negativeRate, positiveRate, activeIndex))) { // 登録成功
			response.sendRedirect(request.getContextPath() + "/WEB-INF/jsp/dailyRev.jsp");
		} else { // 登録失敗
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		    dispatcher.forward(request, response);
		}

	}
}
