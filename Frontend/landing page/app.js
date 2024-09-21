
//getting table data

function getTableData() {
  let table = document.querySelector(".table-wrapper tbody");
  let rows = table.querySelectorAll("tr");

  let dates = [];
  let amounts = [];

  rows.forEach(row => {
    let cells = row.querySelectorAll("td");
    let date = cells[1].textContent; 
    let amount = parseFloat(cells[2].textContent.replace('₹', '').trim());

    dates.push(date);
    amounts.push(amount);
  });

  return { dates, amounts };
}


function getPaymentModes() {
  const table = document.getElementById("expense-table-body");
  const rows = table.getElementsByTagName("tr");
  let paymentModes = {};

  for (let i = 0; i < rows.length; i++) {
    const paymentMode = rows[i].getElementsByTagName("td")[4].innerText;
    if (paymentModes[paymentMode]) {
      paymentModes[paymentMode] += 1;
    } else {
      paymentModes[paymentMode] = 1;
    }
  }

  return paymentModes;
}

let tableData = getTableData();

//line chart

function plotLineChart(dates, amounts) {
  var ctx = document.getElementById('expenseLineChart').getContext('2d');
  var chart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: dates, 
      datasets: [{
        label: 'Weekly Expenses',
        data: amounts, 
        borderColor: 'rgba(75, 192, 192, 1)',
        backgroundColor: 'rgba(75, 192, 192, 0.2)',
        fill: true,
        borderWidth: 3
      }]
    },
    options: {
      responsive: true,
      scales: {
        x: {
          title: {
            display: true,
            text: 'Date'
          }
        },
        y: {
          title: {
            display: true,
            text: 'Amount (₹)'
          }
        }
      }
    }
  });
}


plotLineChart(tableData.dates, tableData.amounts);

//doughnut chart

function updateDoughnutChart(chart, data) {
  chart.data.labels = Object.keys(data);
  chart.data.datasets[0].data = Object.values(data);
  chart.update();
}

const ctx = document.getElementById('paymentDoughnutChart').getContext('2d');
    let paymentDoughnutChart = new Chart(ctx, {
      type: 'doughnut',
      data: {
        labels: [], 
        datasets: [{
          label: 'Payment Modes',
          data: [], // Payment mode counts
          backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF'], // Colors for each mode
          hoverOffset: 4
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            position: 'top',
          },
        }
      }
    });

    document.addEventListener('DOMContentLoaded', () => {
      const paymentModeData = getPaymentModes();
      updateDoughnutChart(paymentDoughnutChart, paymentModeData);
    });


//calculate total amount 

function calculateTotalAmount() {

  const tableBody = document.getElementById('expense-table-body');
  const rows = tableBody.getElementsByTagName('tr');

  let total = 0;

  for (let i = 0; i < rows.length; i++) {
    let amountCell = rows[i].getElementsByTagName('td')[2].innerText;
    

    let amount = parseFloat(amountCell.replace('₹', '').trim());


    total += amount;
  }


  document.getElementById('total-amount').innerText = total.toFixed(2);
}

//calling when loaded
window.onload = function() {
  calculateTotalAmount();
};

//glance-sec

document.addEventListener("DOMContentLoaded", function () {
  const glanceSection = document.querySelector(".glance-sec");
  const heroSection = document.querySelector(".hero");

  const observer = new IntersectionObserver((entries) => {
    entries.forEach((entry) => {
      if (entry.isIntersecting) {
        glanceSection.classList.add("visible");
        heroSection.classList.add("with-border");  
        observer.unobserve(glanceSection);  
      }
    });
  }, {
    threshold: 0.1 
  });

  observer.observe(glanceSection);
});


