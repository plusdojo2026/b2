package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BonusDAO;
import dao.TipsDAO;
import dao.UserDAO;
import dao.WeeklyDAO;
import dto.BonusDTO;
import dto.TipsDTO;
import dto.UserDTO;
import dto.WeeklyDTO;


	@WebServlet("/HomeServlet")
	public class HomeServlet extends HttpServlet{
		private static final long serialVersionUID = 1L;
		
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			// セッションを取得
			HttpSession session = request.getSession();
			// もしもログインしていなかったらログインサーブレットにリダイレクトする
			if (session.getAttribute("user") == null) { //セッションにuser(DTO)があるか確認
				response.sendRedirect("/b2/LoginServlet");
				return;
			}
			
			
			//時間帯に合わせたメッセージ
			LocalTime now = LocalTime.now();
			String greeting;
			
			if(now.isBefore(LocalTime.of(11,0))){
				greeting = "おはようございます！今日も１日がんばりましょう💪";
			}else if(now.isBefore(LocalTime.of(14,0))){
				greeting = "そろそろお昼時ですね。休憩はしっかりとれていますか？";
			}else if(now.isBefore(LocalTime.of(17,0))){
				greeting = "こんにちは！午後も自分のペースでがんばりましょう";
			}else {
				greeting = "こんばんは！今日も一日お疲れさまでした🌙";
			}
			request.setAttribute("greeting", greeting);
			
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
			//LocalDate today = LocalDate.of(2026, 6, 24);
						
			//ログインチェック
			UserDAO uDao = new UserDAO();
			
			// テスト
			System.out.println("===ログインテスト ===");
			System.out.println("DB updated_at (raw): " + lastloginStr);
			System.out.println("parsed lastLogin: " + lastLogin);
			System.out.println("today: " + today);
			System.out.println("lastLogin.plusDays(1): " + lastLogin.plusDays(1));
			
			
			//ビンゴリセット処理
			if(loginUser.getCurrentPos() >= 26) {
				uDao.loginPosUpdate(loginUser.getId(), 0);
				BonusDAO bonus2 = new BonusDAO();
				boolean bonusRes = bonus2.bingoReset(loginUser.getId());
				if(bonusRes) {
					System.out.println("ビンゴリセット出来ませんでした");
				}
			}
			
						
			if(lastLogin.isEqual(today)) {
			//今日ログイン済み何もしない
				System.out.println("条件1：今日すでにログイン済みor初回ログイン時");		
				
			}else if(lastLogin.plusDays(1).isEqual(today)) {
				//昨日ログイン　→　連続ログイン継続
				System.out.println("条件2：昨日ログイン（連続ログイン継続）");
				uDao.processLoginStreak(loginUser.getId());//通算ログイン(データ)
				
				//ビンゴの登録
				int pos = loginUser.getCurrentPos();
								
				BonusDAO bonus2 = new BonusDAO();
				boolean bonusRes = bonus2.bingoLogin(pos,loginUser.getId());
				if(!bonusRes) {
					System.out.println("ビンゴの登録に失敗");
				}else {
					//posの更新
					uDao.loginPosUpdate(loginUser.getId(), pos);
				}
							
			//連続ログインが途切れた時
			}else {
				System.out.println("→ 条件3：連続ログイン途切れ（深度 -30）");
				
				//ログインしていなかった日数を取得(ビンゴに使用)
				BonusDAO bonus2 = new BonusDAO();
				int notLogin = bonus2.selectNotlogin(loginUser.getId());
				
				uDao.processLoginBreak(loginUser.getId());//通算ログイン(データ)	
				
				//ビンゴの登録
				int pos = loginUser.getCurrentPos();
				
				//ログインできなかった日をビンゴテーブルに登録
				while(notLogin-1 > 0 ) {
					uDao.loginPosUpdate(loginUser.getId(), pos);
					notLogin--;
					pos++;
				}
				
				boolean bonusRes = bonus2.bingoLogin(pos,loginUser.getId());
				if(!bonusRes) {
					System.out.println("ビンゴの登録に失敗");
				}
				else {
					//posの更新
					uDao.loginPosUpdate(loginUser.getId(), pos);
				}
				
			}System.out.println("===================");
			
				//最終ログイン日を今日に更新
				uDao.updatedLogin(loginUser.getId(),today.toString());
				
				//DBの最新情報をセッションに反映
				UserDTO fresh = uDao.findById(loginUser.getId());
				session.setAttribute("user", fresh);
				
				
				//ランダムtips
				TipsDAO tDao = new TipsDAO();
				TipsDTO randomTip = tDao.Rondom();
				//jspに渡す
				request.setAttribute("randomTip",randomTip);
				
				//週別データに追加
				UserDTO user = (UserDTO) session.getAttribute("user");
				int userId = user.getId();
				WeeklyDAO dao = new WeeklyDAO();
				List<WeeklyDTO> List = dao.latestSelect(userId);
				request.setAttribute("weekData", List);
				
				
				
				
				//魚の表示
				int user_id = loginUser.getId();
				
				//ビンゴの内容を取得
				BonusDAO bonus = new BonusDAO();
				BonusDTO bingo = bonus.selectBingo(user_id);
				
				request.setAttribute("bingo", bingo);
				
				int totalBingo = 0;
				totalBingo = bingo.getBingoCount();
				
				//魚(水深)処理
				String fish1="",fish2="",fish3="",fish4="",fish5="";
				List <String> fishlist = new ArrayList<>();
				int depth = loginUser.getDepthCurrent();
				
				if(depth <= 600) {
					fish1= "img/fish1.png";
					fishlist.add(fish1);
					if(depth > 200) {
						fish2= "img/fish2.png";
						fishlist.add(fish2);
					}
					if(depth > 300) {
						fish3= "img/fish3.png";
						fishlist.add(fish3);
					}
					if(depth > 400) {
						fish4= "img/fish4.png";
						fishlist.add(fish4);
					}
					if(depth > 500) {
						fish5= "img/fish5.png";
						fishlist.add(fish5);
					}
				}
				else if(depth <= 1100) {
					fish1= "img/fish6.png";
					fishlist.add(fish1);
					if(depth > 700) {
						fish2= "img/fish7.png";
						fishlist.add(fish2);
					}
					if(depth > 800) {
						fish3= "img/fish8.png";
						fishlist.add(fish3);
					}
					if(depth > 900) {
						fish4= "img/fish9.png";
						fishlist.add(fish4);
					}
					if(depth > 1000) {
						fish5= "";
						fishlist.add(fish5);
					}
				}
				else if(depth <= 1600) {
					fish1= "img/fish1.png";
					fishlist.add(fish1);
					if(depth > 1200) {
						fish2= "";
						fishlist.add(fish2);
					}
					if(depth > 1300) {
						fish3= "";
						fishlist.add(fish3);
					}
					if(depth > 1400) {
						fish4= "";
						fishlist.add(fish4);
					}
					if(depth > 1500) {
						fish5= "";
						fishlist.add(fish5);
					}
				}
				else {
					fish1= "img/fish1.png";
					fishlist.add(fish1);
					if(depth > 1700) {
						fish2= "";
						fishlist.add(fish2);
					}
					if(depth > 1800) {
						fish3= "";
						fishlist.add(fish3);
					}
					if(depth > 1900) {
						fish4= "";
						fishlist.add(fish4);
					}
					if(depth > 2000) {
						fish5= "";
						fishlist.add(fish5);
					}
				}
				
				
				if(totalBingo != 0) {
					if(totalBingo >= 1) {
						fishlist.add("img/bingo_sbbp.png");
					}
					if(totalBingo >= 2) {
						fishlist.add("img/bingo_fish.png");
					}
					if(totalBingo >= 3) {
						fishlist.add("img/koi.png");
					}
					if(totalBingo >= 4) {
						fishlist.add("img/koi.png");
					}
					if(totalBingo >= 5) {
						fishlist.add("img/koi.png");
					}
				}
				
				
				System.out.println("フィッシュリスト"+fishlist);
				List<String> shuffled = new ArrayList<>(fishlist); // この後ランダムに並べ替えられる、list のコピー
				Collections.shuffle(shuffled);
				int end = Math.min(shuffled.size(), 4);
				List<String> newlist = shuffled.subList(0, end);
				System.out.println("シャッフル後"+shuffled);

				
				request.setAttribute("fishlist", newlist);
				request.setAttribute("depth", depth);
				
				
				
				
				//ログイン記録を画面表示
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
				dispatcher.forward(request, response);
				
			
		}	

		
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doGet(request,response);	
		}
	}
