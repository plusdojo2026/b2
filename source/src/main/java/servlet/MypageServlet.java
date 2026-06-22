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
		
		//doget（ページ表示）
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// セッションを取得
			HttpSession session = request.getSession();
			// もしもログインしていなかったらログインサーブレットにリダイレクトする
			if (session.getAttribute("user") == null) { //セッションにuser(DTO)があるか確認
				response.sendRedirect("/b2/LoginServlet");
				return;
			}
						
			//セッションからログイン中のユーザーを取得→キャスト
			UserDTO loginUser = (UserDTO) session.getAttribute("user"); //セッションからUserDTOを取得
						
			// DBから最新のユーザー情報を取得してｊｓｐに渡す
			//DAOのインスタンス生成
			UserDAO uDao = new UserDAO();
			//DBから最新のユーザー情報を取得
			UserDTO freshUser = uDao.findById(loginUser.getId());
			//usernameとpwをｊｓｐに渡す
			request.setAttribute("user", freshUser);
						
			//ｍｙｐａｇｅ画面の表示
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp"); // mypageにフォワードする
			dispatcher.forward(request, response);			
		}	
	
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		
		//doPost（編集）
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			// セッションを取得
			HttpSession session = request.getSession();
			// もしもログインしていなかったらログインサーブレットにリダイレクトする
			if (session.getAttribute("user") == null) { //セッションにuser(DTO)があるか確認
				response.sendRedirect("/b2/LoginServlet");
				return;
			}
			
			//セッションからログイン中のユーザーを取得→キャスト
			UserDTO loginUser = (UserDTO) session.getAttribute("user"); //セッションからUserDTOを取得
			
			
			// リクエストパラメータを取得する(フォームから送られた新しいuserNameとpwを取得)
			request.setCharacterEncoding("UTF-8");
			String userName = request.getParameter("userName");
			String pw = request.getParameter("pw");
			
			//DAOのインスタンス生成
			UserDAO uDao = new UserDAO();
			
			//重複チェック
			boolean exists = uDao.existsUser(userName, pw);
			if (exists == true) { // 重複あり
				System.out.println("重複エラー：既に使われている");
				request.setAttribute("mypageError", "このユーザー名とパスワードの組み合わせは既に使われています");
				doGet(request,response);
				return;
			}
			
			//usernameとpwを編集するためのUserDTOを作成
			UserDTO user = new UserDTO();  //ログイン成功したらUserDTOを返す
			user.setId(loginUser.getId()); //どのユーザーを更新するか
			user.setUserName(userName); //更新するもの
			user.setPw(pw); //更新するもの
			
			//DBを編集
			boolean result = uDao.updateUser(user);
			
			//更新成功→セッションの情報も更新
			if(result) { //編集成功
				System.out.println("ユーザー更新成功");
				//セッションのユーザーの情報も更新
				loginUser.setUserName(userName);
				loginUser.setPw(pw);
				session.setAttribute("mypageMessage", "更新が完了しました。");
			}else { //編集失敗
				System.out.println("ユーザー更新失敗");
				session.setAttribute("mypageError", "ユーザー登録に失敗しました。");
			}
			//更新後の画面を再表示
			response.sendRedirect("/b2/MypageServlet");
		}	
	
}
