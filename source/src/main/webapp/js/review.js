 /*Weeklyのグラフ */
function createLineChart(labels, positiveData, negativeData) {
  var ctx = document.getElementById("myLineChart");

  new Chart(ctx, {
    type: 'line',
    data: {
      labels: labels,
      datasets: [{
          label: 'ポジティブ率',
          data: positiveData,
          borderColor: "rgba(255,0,0,1)",
          backgroundColor: "rgba(0,0,0,0)"
        },{
          label: 'ネガティブ率',
          data: negativeData,
          borderColor: "rgba(0,0,255,1)",
          backgroundColor: "rgba(0,0,0,0)"
        }]},
    options: {title: {display: false,},legend: {display: false},
    responsive: true,
    maintainAspectRatio: false,
    scales: {yAxes: [{ticks: {
            max: 100,
            min: 0,
            stepSize: 10,
            callback: function(value){
              return value + '%'
    }}}]}}});}

/*タブ切り替え時にグラフを生成*/
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
 /*Weeklyのグラフ終わり */