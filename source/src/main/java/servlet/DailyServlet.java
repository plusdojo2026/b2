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
import javax.servlet.http.HttpSession;

import dao.DailyDAO;
import dao.QuestionDAO;
import dto.AnalysisDTO;
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
		HttpSession session = request.getSession(false);


		//各質問の点数とABC項目を配列で整理する
		ArrayList<Integer> point = new ArrayList<>();
		ArrayList<Integer> emoType = new ArrayList<>();
		
		for (int qGet = 1; qGet <= 14; qGet++) {
			
			//質問のポイントを取得して格納
			String qAns = request.getParameter(
				"q_" + (qGet)
			);
			point.add(Integer.parseInt(qAns));

			//質問のABC項目を取得して格納
			String qEmo = request.getParameter(
				"qType_" + (qGet)
			);
			emoType.add(Integer.parseInt(qEmo));
		}
		
		
		

		// int type_id = result.getType_id();
		// double negativeRate = result.getNegativeRate();
		// double positiveRate = result.getPositiveRate();
		// double activeIndex = result.getActiveIndex();
		// int emoBalance = result.getEmoBalance();

		//その他のパラメータ
		// int user_id = (Integer)session.getAttribute("user_id");
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		String freeForm = request.getParameter("freeForm");
		String photo = request.getParameter("photo");
		String positive = request.getParameter("positive");
		int emotion_id = Integer.parseInt(request.getParameter("emotion_id"));

		//各項目を合算し、分析する
		QuestionDAO qDao = new QuestionDAO();
		AnalysisDTO result = qDao.analyze(point, emoType);
		DailyDTO daily = new DailyDTO(
			0,
			user_id,
			freeForm,
			photo,
			positive,
			emotion_id,
			result.getTypeId(),
			result.getNegativeRate(),
			result.getPositiveRate(),
			result.getActiveIndex(),
			0
		);

		// 登録処理を行う
		DailyDAO dDao = new DailyDAO();
		if (dDao.insert(daily)) { // 登録成功
			response.sendRedirect(request.getContextPath() + "/DailyRevServlet");
		} else { // 登録失敗
			response.sendRedirect(request.getContextPath() + "/DailyServlet");
		}

	}
}
