
//ナビゲーションボタン
const nav = document.getElementById("navmenu");
const btn = document.getElementById("navbtn");

btn.addEventListener("click", () => {
    if (nav.classList.contains("open")) {
        nav.classList.remove("open");
        nav.classList.add("close");
    } else {
        nav.classList.remove("close");
        nav.classList.add("open");
    }
});


