package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BonusDAO;
import dao.UserDAO;
import dto.UserDTO;

	
	/**
	 * Servlet implementation class reviewServlet
	 */

	@WebServlet("/UserServlet")
	public class UserServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

		// ユーザーページ(新規登録画面）にフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user.jsp");
			dispatcher.forward(request, response);
		}
		
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			String userName = request.getParameter("userName");
			String pw = request.getParameter("pw");
			
			//usernameまたはpwが未入力なら登録しない
			if(userName == null || userName.isEmpty() || pw == null || pw.isEmpty()) {
				System.out.println("ユーザーネームまたはpwが未入力");
				request.setAttribute("newUserRegisterror", "ユーザー名とパスワードを入力してください");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user.jsp"); //再度新規登録ページを表示
				dispatcher.forward(request, response);
				return;
			}
			
			//重複チェック
			UserDAO nDao = new UserDAO();
			boolean exists = nDao.existsUser(userName, pw);
			
			if (exists == true) { // 重複あり
				System.out.println("重複あり");
				request.setAttribute("newUserRegisterror", "このidとpwの組み合わせはすでに使用されています。idかpwを変更してください。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user.jsp"); //再度新規登録ページを表示
				dispatcher.forward(request, response);
				return;
			}
			
			//新規登録
			UserDTO user = new UserDTO();  //ログイン成功したらUserDTOを返す
			user.setUserName(userName);
			user.setPw(pw);
			
			int id = nDao.insert(user);
			
			if(id != 0) { //新規登録成功
				System.out.println("新規登録成功");
				HttpSession session = request.getSession();
				session.setAttribute("user_id", id);
				
				BonusDAO bonus = new BonusDAO();
				boolean bingoAdd = false;
				bingoAdd = bonus.createBingo(id);
				System.out.println(bingoAdd);
				
				response.sendRedirect("/b2/LoginServlet"); //loginページにとぶ
				return;
				
			}else { //新規登録失敗
				System.out.println("新規登録失敗");
				request.setAttribute("newRegistError", "ユーザー登録に失敗しました。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user.jsp"); // User（新規登録）ページにフォワードする
				dispatcher.forward(request, response);
				return;
			}
		}	
	}	

