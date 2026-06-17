package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuickDAO;
import dto.QuickDTO;
import dto.review;

/**
 * Servlet implementation class LoginServlet
 */

@WebServlet("/QuickServlet")
public class QuickServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/quick.jsp");
		dispatcher.forward(request, response);
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

		// リクエストパラメータを取得する    
	    request.setCharacterEncoding("UTF-8");
	    String event = request.getParameter("event");
	    String belief = request.getParameter("belief");
	    String resultTxt = request.getParameter("result");
	    String reframe = request.getParameter("reframe");
	    String txtFree = request.getParameter("txtFree");
	    int emotionId = Integer.parseInt(request.getParameter("emotion_id"));

	    // DTO に詰める
	    QuickDTO dto = new QuickDTO();
	    dto.setEvent(event);
	    dto.setBelief(belief);
	    dto.setResult(resultTxt);
	    dto.setReframe(reframe);
	    dto.setTxtFree(txtFree);
	    dto.setEmotion_id(emotionId);

	    // 登録処理
	    QuickDAO dao = new QuickDAO();
	    if (dao.insert(dto)) {
	        request.setAttribute("result",
	            new review("登録成功☆彡", "レコードを登録したよ。", "/MenuServlet"));
	    } else { // 登録失敗
	        request.setAttribute("result",
	            new review("登録失敗！", "レコードを登録できませんでした。", "/MenuServlet"));
	    }

	    // 結果ページにフォワード
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
	    dispatcher.forward(request, response);
	}
}