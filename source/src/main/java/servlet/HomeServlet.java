package servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dto.UserDTO;



	@WebServlet("/HomeServlet")
	public class HomeServlet extends HttpServlet{
		private static final long serialVersionUID = 1L;
		
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// ホームページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
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
			if (session.getAttribute("user") == null) { //セッションにuser(DTO)があるか確認
				response.sendRedirect("/webapp/LoginServlet");
				return;
			}
			
			//ログイン中のユーザーを取得→キャスト
			UserDTO loginUser = (UserDTO) session.getAttribute("user"); //セッションからUserDTOを取得
			
			// リクエストパラメータを取得する(フォームから送られた新しいuserNameとpwを取得)
			request.setCharacterEncoding("UTF-8");
			
			//セッションから最終ログイン日時を取得
			String lastloginStr = loginUser.getUpdated_at();  
			String lastloginDate = lastloginStr.substring(0,10); //0~10文字目を取得
			LocalDate lastLogin = LocalDate.parse(lastloginDate);
			
			//今日の日付を取得
			LocalDate today = LocalDate.now();
			
			//ログインチェック
			UserDAO uDao = new UserDAO();
			
			if(lastLogin.isEqual(today)) {
				//今日ログイン済み何もしない
			}else if(lastLogin.plusDays(1).isEqual(today)) {
				//昨日ログイン　→　連続ログイン継続
				uDao.updateTotalLoginDays(loginUser.getId());//通算ログイン(データ)
				uDao.loginStreak(loginUser.getId());//連続ログイン(データ)
				uDao.addDepth10(loginUser.getId());//深度+10(データ)
				
				//セッションの情報も更新
				loginUser.setDaysTotalLogin(loginUser.getDaysTotalLogin() +1); //通算ログイン(画面)
				loginUser.setLoginStreak(loginUser.getLoginStreak() +1); //連続ログイン(画面)
				loginUser.setDepthCurrent(loginUser.getDepthCurrent() +10); //深度+10(画面)
				
				//連続ログインが途切れた時
			}else {
				uDao.updateTotalLoginDays(loginUser.getId());//通算ログイン(データ)
				uDao.loginStreakReset(loginUser.getId());//連続ログインリセット(データ)
				uDao.decreaseDepth1(loginUser.getId());//深度-30(データ)
				
				//セッションのユーザーの情報も更新
				loginUser.setDaysTotalLogin(loginUser.getDaysTotalLogin() +1);
				loginUser.setLoginStreak(1);//連続ログイン(画面)
				
				int newDepth = loginUser.getDepthCurrent()-30; 
				if(newDepth < 0) { newDepth = 0; //深度がマイナスにならない
				}
				loginUser.setDepthCurrent(newDepth);//深度-30(画面)
				
			}
			//最終ログイン日を今日に更新
			uDao.updatedLogin(loginUser.getId(),today.toString());
			loginUser.setUpdated_at(today.toString()); //最終ログイン日(画面)
		}	
}
