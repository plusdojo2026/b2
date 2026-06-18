package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.QuickDTO;


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
		// 簡易記録ページにフォワードする
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
	    QuickDTO qdto = new QuickDTO();
	    qdto.setEvent(event);
	    qdto.setBelief(belief);
	    qdto.setResult(resultTxt);
	    qdto.setReframe(reframe);
	    qdto.setTxtFree(txtFree);
	    qdto.setEmotion_id(emotionId);
	    
	    //テスト用本番はセッションスコープ
	    int user_id = 1;
	    
	    //ポップアップを成功と表示

	    // 結果ページにフォワード
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/review.jsp");
	    dispatcher.forward(request, response);
	}
}