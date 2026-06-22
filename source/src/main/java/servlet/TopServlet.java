package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	/**
	 * Servlet implementation class TopServlet
	 */

	@WebServlet("/TopServlet")
	public class TopServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
	        dispatcher.forward(request, response);
	    }
			
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {		
		
			// どのボタンが押されたか判定する
	        String isRegister = request.getParameter("register"); // 新規登録ボタン
	        String isLogin = request.getParameter("login");       // ログインボタン

	        /* 新規登録」ボタンが押されたとき */
	        if (isRegister != null) {
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user.jsp");
	            dispatcher.forward(request, response);
	            return; // 処理を終了
	        }

	        /* 「ログイン」ボタンが押されたとき */
	        if (isLogin != null) {
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
	            dispatcher.forward(request, response);
	            return; // 処理を終了
	        }
	    }
	}			