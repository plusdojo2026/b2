	'use strict';
		
		const btn = document.getElementById("slideBtn");
		const nav = document.getElementById("slideNav"); 	
		
		btn.addEventListener("click", () => {
			//ナビの開閉
			nav.classList.toggle("open");
			
			//画像の入れ替え
			if(btn.src.includes("Snail.png")){
				btn.src = "/b2/img/memdaco.png";
				btn.classList.add("onNavi");
			}else{
				btn.src = "/b2/img/Snail.png";
				btn.classList.remove("onNavi");
			}
		});		
  