package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dto.UserDTO;


	@WebServlet("/MypageServlet")
	public class MypageServlet extends HttpServlet{
		private static final long serialVersionUID = 1L;
		
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// マイページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
			dispatcher.forward(request, response);
		}	
	
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// セッションを取得
			HttpSession session = request.getSession();
			// もしもログインしていなかったらログインサーブレットにリダイレクトする
			if (session.getAttribute("user") == null) {
				response.sendRedirect("/webapp/LoginServlet");
				return;
			}
			
			// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			
			//ログイン中のユーザーを取得
			UserDTO loginUser = (UserDTO) session.getAttribute("user");
			//更新対象のユーザーIDを取り出す
			int myId =loginUser.getId();
	
			//フォームから送られた新しいuserNameとpwを取得
			String userName = request.getParameter("userName");
			String pw = request.getParameter("pw");
			
			
			//重複チェック
			UserDAO uDao = new UserDAO();
			boolean exists = uDao.existsUser(userName, pw);
			
			if (exists) { // 重複あり
				request.setAttribute("mypageError", "このユーザー名とパスワードの組み合わせは既に使われています");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp"); //再度新規登録ページを表示
				dispatcher.forward(request, response);
				return;
			}
			
			//usernameとpwの編集
			UserDTO user = new UserDTO();  //ログイン成功したらUserDTOを返す
			user.setId(myId);
			user.setUserName(userName);
			user.setPw(pw);
			
			
			boolean result = uDao.updateUser(user);
			
			if(result) { //編集成功
				//セッションのユーザーの情報も更新
				loginUser.setUserName(userName);
				loginUser.setPw(pw);
				request.setAttribute("mypageMessage", "更新が完了しました。");
			}else { //編集失敗
				request.setAttribute("mypageError", "ユーザー登録に失敗しました。");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp"); // mypageにフォワードする
			dispatcher.forward(request, response);
		}	
	
}
