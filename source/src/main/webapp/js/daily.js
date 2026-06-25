// 質問回答状況
document.addEventListener("DOMContentLoaded", () => {

    const radios =
        document.querySelectorAll(
            '.answer input[type="radio"]'
        );

    const totalQuestions =
        document.querySelectorAll(
            '.question-card'
        ).length;

    function updateProgress(){

        let answered = 0;

        document.querySelectorAll(
            '.question-card'
        ).forEach(card => {

            const checked =
                card.querySelector(
                    'input[type="radio"]:checked'
                );

            if(checked){
                answered++;
            }

        });

        const percent =
            (answered / totalQuestions) * 100;

        document.getElementById(
            "progress-fill"
        ).style.width = percent + "%";

        document.getElementById(
            "answered-count"
        ).textContent = answered;
    }

    radios.forEach(radio => {
        radio.addEventListener(
            "change",
            updateProgress
        );
    });

    updateProgress();

});