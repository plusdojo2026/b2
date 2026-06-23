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
		int user_id = 1;
		
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("user");
		
		
		//魚(水深)処理
		String fish1="",fish2="",fish3="",fish4="",fish5="";
		List <String> fishlist = new ArrayList<>();
		int depth = loginUser.getDepthCurrent();
		
		if(depth > 100) {
			if(depth < 200) {
				fish1= "img/fish1.png";
			}
			if(depth < 300) {
				fish2= "";
			}
			if(depth < 400) {
				fish3= "";
			}
			if(depth < 500) {
				fish4= "";
			}
			if(depth < 600) {
				fish5= "";
			}
		}
		else if(depth > 600) {
			if(depth < 700) {
				fish1= "";
			}
			if(depth < 800) {
				fish2= "";
			}
			if(depth < 900) {
				fish3= "";
			}
			if(depth < 1000) {
				fish4= "";
			}
			if(depth < 1100) {
				fish5= "";
			}
		}
		else if(depth > 1100) {
			if(depth < 1200) {
				fish1= "";
			}
			if(depth < 1300) {
				fish2= "";
			}
			if(depth < 1400) {
				fish3= "";
			}
			if(depth < 1500) {
				fish4= "";
			}
			if(depth < 1600) {
				fish5= "";
			}
		}
		else if(depth > 1600) {
			if(depth < 1700) {
				fish1= "";
			}
			if(depth < 1800) {
				fish2= "";
			}
			if(depth < 1900) {
				fish3= "";
			}
			if(depth < 2000) {
				fish4= "";
			}
			if(depth < 2100) {
				fish5= "";
			}
		}

		
		fishlist.add(fish1);
		fishlist.add(fish2);
		fishlist.add(fish3);
		fishlist.add(fish4);
		fishlist.add(fish5);
		
		request.setAttribute("fishlist", fishlist);
		
		//ビンゴの内容を取得
		BonusDAO bonus = new BonusDAO();
		BonusDTO bingo = bonus.selectBingo(user_id);
		
		request.setAttribute("bingo", bingo);
		
		
		// ログインボーナスページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/bonus.jsp");
		dispatcher.forward(request, response);
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bC = request.getParameter("bingoCount");
        
        
		if(bC != null) {
			int count = Integer.parseInt(bC);
			int user_id = 1;
			BonusDAO bonus = new BonusDAO();
			boolean res = bonus.setCount(count,user_id);
			if(res==false){
				System.out.println("ビンゴ数を登録できませんでした");
				}
			}

        response.getWriter().write("OK");
	}
}
