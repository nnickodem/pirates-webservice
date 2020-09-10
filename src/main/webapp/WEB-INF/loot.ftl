<!DOCTYPE html>
<html>
   <script src="/resources/js/Chart.js"></script>
   <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
   <head>
      <title>Home</title>
   </head>
   <body style="background-color:Black;">
      <#include "header.html">
      <div class="row justify-content-md-center">
        <input id="bossNameText" class="form-control w-25 d-inline" type="text" placeholder="Boss name here . . .">
        <button class="btn btn-primary" onclick="loadNewBoss()">Submit</button>
      </div>
      <canvas id="myChart" width="400" height="400"></canvas>
   </body>
</html>
<script>
    var myChart;
    function createGraph(rarities, counts) {
       var ctx = document.getElementById('myChart').getContext('2d');
       myChart = new Chart(ctx, {
           type: 'bar',
           data: {
               labels: rarities,
               datasets: [{
                   label: '# of Drops',
                   data: counts,
                   backgroundColor: [
                       'rgba(255, 99, 132, 0.2)',
                       'rgba(54, 162, 235, 0.2)',
                       'rgba(255, 206, 86, 0.2)',
                       'rgba(75, 192, 192, 0.2)',
                       'rgba(153, 102, 255, 0.2)',
                       'rgba(255, 159, 64, 0.2)'
                   ],
                   borderColor: [
                       'rgba(255, 99, 132, 1)',
                       'rgba(54, 162, 235, 1)',
                       'rgba(255, 206, 86, 1)',
                       'rgba(75, 192, 192, 1)',
                       'rgba(153, 102, 255, 1)',
                       'rgba(255, 159, 64, 1)'
                   ],
                   borderWidth: 1
               }]
           },
           options: {
               scales: {
                   yAxes: [{
                       ticks: {
                           beginAtZero: true
                       }
                   }]
               }
           }
       });
    }

    function addData(label, data) {
        myChart.data.datasets[0].data = data;
        myChart.update();
    }

    function removeData() {
        myChart.data.datasets.forEach((dataset) => {
            dataset.data.pop();
        });
        myChart.update();
    }

    function getLootByBossName(bossName) {
      axios.get('/bossLoot?bossName=' + bossName)
        .then((response) => {
            if(!myChart) {
              createGraph(response.data.rarities, response.data.counts);
            } else {
              removeData();
              addData(response.data.rarities, response.data.counts);
            }
      });
    }

    function loadNewBoss() {
      getLootByBossName(document.getElementById("bossNameText").value);
    }
</script>
