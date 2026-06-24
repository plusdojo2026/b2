	'use strict';
		
		const btn = document.getElementById("slideBtn");
		const nav = document.getElementById("slideNav"); 	
		
		btn.addEventListener("click", () => {
			//ナビの開閉
			nav.classList.toggle("open");
			
			//画像の入れ替え
			if(btn.src.includes("yajirusi1.png")){
				btn.src = "/b2/img/yajirusi2.png";
				btn.classList.add("onNavi");
			}else{
				btn.src = "/b2/img/yajirusi1.png";
				btn.classList.remove("onNavi");
			}
			
		});		
  
  document.getElementById("leftMenuBtn").onclick = function() {
    const menu = document.querySelector("aside.left-menu");
    menu.style.display = (menu.style.display === "block") ? "none" : "block";
};