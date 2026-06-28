package servlet;

import java.io.IOException;
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
import dto.BonusDTO;
import dto.UserDTO;

/**
 * Servlet implementation class BonusServlet
 */

@WebServlet("/BonusServlet")
public class BonusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		
		//ユーザーID
		
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("user");
		
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
		
		
		// ログインボーナスページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/bonus.jsp");
		dispatcher.forward(request, response);
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("user");
		
		String bC = request.getParameter("bingoCount");
		
		int user_id = loginUser.getId();
		//int user_id = 1;
        
		if(bC != null) {
			int count = Integer.parseInt(bC);
			BonusDAO bonus = new BonusDAO();
			boolean res = bonus.setCount(count,user_id);
			if(res==false){
				System.out.println("ビンゴ数を登録できませんでした");
			}
		}
		
		String fishName = "";
		
		
		//ビンゴの内容を取得
		BonusDAO bonus = new BonusDAO();
		BonusDTO bingo = bonus.selectBingo(user_id);
		
		request.setAttribute("bingo", bingo);
		
		
		int totalBingo = bingo.getBingoCount();	
		
		if(totalBingo != 0) {
			if(totalBingo == 1) {
				fishName = "img/bingo_sbbp.png";
			}
			else if(totalBingo == 1) {
				fishName = "img/bingo_fish.png";
			}
			else if(totalBingo == 2) {
				fishName = "img/fish1.png";
			}
			else if(totalBingo == 3) {
				fishName = "img/fish1.png";
			}
			else if(totalBingo == 4) {
				fishName = "img/fish1.png";
			}
			else if(totalBingo == 5) {
				fishName = "img/fish1.png";
			}
		}

        response.getWriter().write(fishName);
	}
}
