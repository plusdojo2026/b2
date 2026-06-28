	'use strict';

	document.querySelectorAll('.bingo_table td').forEach(td => {
	    td.addEventListener('pointerenter', () => {
	        td.classList.add('roll');
	        td.addEventListener('animationend', () => {
	            td.classList.remove('roll');
	        }, { once: true });
	    });
	});





		
		const btn = document.getElementById("slideBtn");
		const nav = document.getElementById("slideNav"); 	
		
		btn.addEventListener("click", () => {
			//ナビの開閉
			nav.classList.toggle("open");
			
			//ナビの開閉
			 document.body.classList.toggle("nav-open");
			 
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
			if(window.innerWidth >= 765)retrun;
			
    		const menu = document.querySelector("aside.left-menu");
    		if (menu.style.display === "block") {
        		menu.style.display = "none";
    		}else {
       			menu.style.display = "block";
    		}
		};
