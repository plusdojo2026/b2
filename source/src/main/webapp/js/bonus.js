
//ナビゲーションボタン
document.getElementById("navbtn").addEventListener("click", function(){
    document.getElementById("navmenu").classList.toggle("hidden");
})




console.log("ビンゴ開始");

const rows = [...document.querySelectorAll("#bingo tr")];
const grid = rows.map(tr => [...tr.querySelectorAll("td")]);

function isChecked(td) {
    return td.dataset.checked === "true";
}

function checkBingo() {
    // 横
    for (let r = 0; r < 5; r++) {
        if (grid[r].every(isChecked)) return true;
    }

    // 縦
    for (let c = 0; c < 5; c++) {
        if (grid.every(row => isChecked(row[c]))) return true;
    }

    // 斜め
    const diag1 = grid.every((row, i) => isChecked(row[i]));
    const diag2 = grid.every((row, i) => isChecked(row[4 - i]));

    return diag1 || diag2;
}

if (checkBingo()) {
    console.log("BINGO!");
}

