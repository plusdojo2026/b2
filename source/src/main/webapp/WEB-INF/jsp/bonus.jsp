<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<div id="bonusView"></div>
    <h1 id="hitbingo">ビンゴ！</h1>
    <img id="bingoimg" src="${getfish}">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" type="text/css" href="css/bonus.css">
        <title>ログインボーナス</title>
    </head>


    <body>
        	<aside> <%--右側にあるナビ --%>
				<img src ="/b2/img/yajirusi1.png" class ="pic" id="slideBtn"> 
				<div id="slideNav">
					<nav>
						<ul>
							<li><a href="/b2/QuickServlet"><b>簡易記録</b></a></li>
							<li><a href="/b2/DailyServlet"><b>毎日記録</b></a></li>
							<li><a href="/b2/ReviewServlet"><b>振り返り</b></a></li>
							<li><a href="/b2/BonusServlet"><b>ボーナス</b></a></li>
							<li><a href="/b2/MypageServlet"><b>マイページ</b></a></li>
						</ul>
					</nav>
					<div class="homereview">
						<p>本日の入力：</p>
						<ul class ="icon-list">
							<li><img src="/b2/img/happa2.png"></li>
							<li><img src="/b2/img/kinyuu2.png"></li>
							<li><img src="/b2/img/syasinn2.png"></li>
							<li><img src="/b2/img/sakana2.png"></li>
						</ul>
						<button onclick="location.href='/b2/DailyServlet';">記録する</button>
					</div>
					
					<div class="slidehomereview">
					<p>週間レポート</p>
					        <c:if test="${not empty weekData}">
							    <c:set var="slideE" value="${weekData[0]}" />
							</c:if>
							
							<div class="weekly-box">
							    <div class="chart-container">
							        <p class="weekly-value">
							            ポジティブ率：
							            <fmt:formatNumber value="${slideE.avgPositive}" maxFractionDigits="1" />%
							        </p>
							    </div>
							</div>
							
							<div class="info-area">
							    <c:if test="${not empty slideE}">
							        週の期間：<c:out value="${slideE.weeklyRes}" /><br>
							    </c:if>
							</div>
					            <button onclick="location.href='/b2/ReviewServlet';">VIEW ALL</button>
					        
					</div>
					<button onclick="location.href='/b2/TopServlet';">アプリについて</button>
				</div>		
			</aside>
        <div class="flexbox">
            <div class="leftScreen">
            </div>

            <div class="rightScreen">
            <div class="title-back">
            	<div class="bonus-title"><h1>ログインボーナス</h1></div>
            </div>
            <hr>
            	<div class="bingo-field">
	                <div class="title">BINGO</div>
	
	
	                <table id="bingo" class="bingo_table">
	                    <tr>
	                        <td data-checked="${bingo.day1}" style="background-color: ${bingo.day1 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day1 == true ? 'img/memdaco.png' : 'img/Snail.png'};"><span class="backnumber">1</span></td>
	                        <td data-checked="${bingo.day2}" style="background-color: ${bingo.day2 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day2 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                        <td data-checked="${bingo.day3}" style="background-color: ${bingo.day3 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day3 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                        <td data-checked="${bingo.day4}" style="background-color: ${bingo.day4 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day4 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                        <td data-checked="${bingo.day5}" style="background-color: ${bingo.day5 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day5 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                    </tr>
	                    <tr>
	                        <td data-checked="${bingo.day6}" style="background-color: ${bingo.day6 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day6 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                        <td data-checked="${bingo.day7}" style="background-color: ${bingo.day7 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day7 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                        <td data-checked="${bingo.day8}" style="background-color: ${bingo.day8 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day8 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                        <td data-checked="${bingo.day9}" style="background-color: ${bingo.day9 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day9 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                        <td data-checked="${bingo.day10}" style="background-color: ${bingo.day10 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day10 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                    </tr>
	                    <tr>
	                        <td data-checked="${bingo.day11}" style="background-color: ${bingo.day11 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day11 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                        <td data-checked="${bingo.day12}" style="background-color: ${bingo.day12 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day12 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                        <td data-checked="${bingo.day13}" style="background-color: ${bingo.day13 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day13 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                        <td data-checked="${bingo.day14}" style="background-color: ${bingo.day14 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day14 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                        <td data-checked="${bingo.day15}" style="background-color: ${bingo.day15 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day15 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                    </tr>
	                    <tr>
	                        <td data-checked="${bingo.day16}" style="background-color: ${bingo.day16 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day16 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                        <td data-checked="${bingo.day17}" style="background-color: ${bingo.day17 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day17 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                        <td data-checked="${bingo.day18}" style="background-color: ${bingo.day18 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day18 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                        <td data-checked="${bingo.day19}" style="background-color: ${bingo.day19 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day19 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                        <td data-checked="${bingo.day20}" style="background-color: ${bingo.day20 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day20 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                    </tr>
	                    <tr>
	                        <td data-checked="${bingo.day21}" style="background-color: ${bingo.day21 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day21 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                        <td data-checked="${bingo.day22}" style="background-color: ${bingo.day22 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day22 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                        <td data-checked="${bingo.day23}" style="background-color: ${bingo.day23 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day23 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                        <td data-checked="${bingo.day24}" style="background-color: ${bingo.day24 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day24 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                        <td data-checked="${bingo.day25}" style="background-color: ${bingo.day25 == true ? 'red' : 'rgba(165, 255, 255, 0.744)'};"><img src="${bingo.day25 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
	                    </tr>
	                </table>
				</div>

                <br><br>
				
				<div class="depthView${depth <= 600 ? '1' : depth <= 1100 ? '2' : depth <= 1600 ? '3' : '4'} }">
                	<div class="title depth">水深チェック</div>
                	<hr>
                		<div class="depth-current">
                    		<h2>現在の水深<span class="depth-length">${depth}<span class="m">　M</span></span></h2>
                		</div>
					<c:forEach var="fish" items="${requestScope.fishlist}" varStatus="s">
					    <div><img class="fish${s.index} }" src="${fish}"></div>
					</c:forEach>
				</div>

            </div>
        </div>
        <script src="js/bonus.js"></script>
    </body>
    
    <script> 
		const hitview = document.getElementById('bonusView');
		const hittext = document.getElementById('hitbingo');
		const hitimg = document.getElementById('bingoimg');
	
		hitview.style.display = "none";
		hittext.style.display = "none";
		hitimg.style.display = "none";
    
    
    
	    console.log("ビンゴ開始");
	
	    const rows = [...document.querySelectorAll("#bingo tr")];
	    const grid = rows.map(tr => [...tr.querySelectorAll("td")]);
	
	    function isChecked(td) {
	        return td.dataset.checked === "true";
	    }
	    
	    //ビンゴ演出用
	    function HitBingo(){
	    	const hitview = document.getElementById('bonusView');
	    	const hittext = document.getElementById('hitbingo');
	    	const hitimg = document.getElementById('bingoimg');

	    	hitview.style.display = "none";
	    	hittext.style.display = "none";
	    	hitimg.style.display = "none";
	    }
	
	    function checkBingo() {
	    	let count=0;
	        // 横
	        for (let r = 0; r < 5; r++) {
	            if (grid[r].every(isChecked)) count++;
	        }
	
	        // 縦
	        for (let c = 0; c < 5; c++) {
	            if (grid.every(row => isChecked(row[c]))) count++;
	        }
	
	        // 斜め
	        const diag1 = grid.every((row, i) => isChecked(row[i]));
	        const diag2 = grid.every((row, i) => isChecked(row[4 - i]));
	
	        if(diag1){
	    		count++;
	    	}
	    	if(diag2){
	    		count++;
	    	}
	    	
	    	return count;
	    }
	
	    let beforebingo = "${bingo.bingoCount}";
	    beforebingo = Number(beforebingo);
	    let bingo = checkBingo();
	    console.log("Bingo="+beforebingo);
	    
	    console.log("現在のビンゴ数"+bingo);
	    if (bingo > beforebingo) {
	    	
	        console.log(bingo+"BINGO!");
	        fetch("${pageContext.request.contextPath}/BonusServlet", {
	            method: "POST",
	            headers: {
	                "Content-Type": "application/x-www-form-urlencoded"
	            },
	            body: "bingoCount=" + encodeURIComponent(bingo)
	        })
	        .then(res => res.text())
	        .then(result => {
	        	//ビンゴで取得した画像のパスをsrcに入れる
	        	const img = document.getElementById("bingoimg");
	            img.src = result;
	            console.log("サーブレットからの返答:", result);
	        });	
	        
	        //ビンゴ演出
	    	const hitview = document.getElementById('bonusView');
	    	const hittext = document.getElementById('hitbingo');
	    	const hitimg = document.getElementById('bingoimg');

	    	hitview.style.display = "";
	    	hittext.style.display = "";
	    	hitimg.style.display = "";
	    	
	    	
	    	window.setTimeout(HitBingo, 5500);
	    }
    </script>
</html>