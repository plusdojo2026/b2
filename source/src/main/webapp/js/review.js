 /*Weeklyのグラフ */
 /*Weeklyのグラフ */
function createLineChart(labels, positiveData, negativeData) {
  var ctx = document.getElementById("myLineChart");

  new Chart(ctx, {
    type: 'line',
    data: {
      labels: labels,
      datasets: [
        {
          label: 'ポジティブ率',
          data: positiveData,
          borderColor: "rgba(255,0,0,1)",
          backgroundColor: "rgba(0,0,0,0)"
        },
        {
          label: 'ネガティブ率',
          data: negativeData,
          borderColor: "rgba(0,0,255,1)",
          backgroundColor: "rgba(0,0,0,0)"
        }
      ]
    },
    options: {
      title: {
        display: true,
        text: 'ポジティブ率とネガティブ率の遷移'
      },
      responsive: true,
      maintainAspectRatio: false,
      scales: {
        yAxes: [{
          ticks: {
            suggestedMax: 100,
            suggestedMin: 0,
            stepSize: 10,
            callback: function(value){
              return value + '%'
            }}}]}}});}


window.addEventListener("DOMContentLoaded", function() {

  let chartCreated = false;

  const tabWeek = document.getElementById("tab-week");

  tabWeek.addEventListener("change", function() {
    if (this.checked && !chartCreated) {
      createLineChart(labels, positive, negative);
      chartCreated = true;
    }
  });

});
