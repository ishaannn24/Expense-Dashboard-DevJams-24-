// Mock data for weekly, monthly, and yearly expenses
const expenseData = {
  weekly: [
    { date: "20/09", amount: 500, category: "Food", paymentMode: "UPI" },
    { date: "21/09", amount: 500, category: "Travel", paymentMode: "Credit Card" },
    { date: "22/09", amount: 1000, category: "Grocery", paymentMode: "UPI" },
    { date: "23/09", amount: 1500, category: "Travel", paymentMode: "Debit Card" },
    { date: "24/09", amount: 200, category: "Food", paymentMode: "UPI" },
    { date: "25/09", amount: 600, category: "Shopping", paymentMode: "Credit Card" },
    { date: "26/09", amount: 800, category: "Travel", paymentMode: "Debit Card" }
  ],
  monthly: [
    { month: "January", amount: 200000, category: "Rent", paymentMode: "UPI" },
    { month: "February", amount: 35000, category: "Food", paymentMode: "Debit Card" },
    { month: "March", amount: 47000, category: "Shopping", paymentMode: "Credit Card" }
  ],
  yearly: [
    { year: "2021", amount: 1500000, category: "Rent", paymentMode: "UPI" },
    { year: "2022", amount: 2000000, category: "Utilities", paymentMode: "UPI" },
    { year: "2023", amount: 5000000, category: "Travel", paymentMode: "Credit Card" },
    { year: "2024", amount: 7000000, category: "Car Repair", paymentMode: "Debit Card" }
  ]
};

let expenseLineChart;
let paymentDoughnutChart;

function updateTableData(data) {
  const tableBody = document.getElementById("expense-table-body");
  tableBody.innerHTML = "";

  data.forEach((item, index) => {
    const row = `
      <tr>
        <td>${index + 1}</td>
        <td>${item.month ? item.month : item.year ? item.year : item.date}</td>
        <td>&#8377; ${item.amount}</td>
        <td>${item.category}</td>
        <td>${item.paymentMode}</td>
      </tr>`;
    tableBody.innerHTML += row;
  });

  const tableData = getTableData();
  plotLineChart(tableData.dates, tableData.amounts);
  updateDoughnutChart(getSelectedDoughnutData());

  // Update table headers based on the selected option
  const headerDate = document.getElementById("header-date");
  if (data[0].month) {
    headerDate.textContent = "Month";
  } else if (data[0].year) {
    headerDate.textContent = "Year";
  } else {
    headerDate.textContent = "Date";
  }
}

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

function getCategoryData() {
  const table = document.getElementById("expense-table-body");
  const rows = table.getElementsByTagName("tr");
  let categories = {};

  for (let i = 0; i < rows.length; i++) {
    const category = rows[i].getElementsByTagName("td")[3].innerText;
    const amount = parseFloat(rows[i].getElementsByTagName("td")[2].innerText.replace('₹', '').trim());

    if (categories[category]) {
      categories[category] += amount;
    } else {
      categories[category] = amount;
    }
  }

  return categories;
}

function plotLineChart(dates, amounts) {
  const ctx = document.getElementById("expenseLineChart").getContext("2d");

  if (expenseLineChart) {
    expenseLineChart.destroy();
  }

  expenseLineChart = new Chart(ctx, {
    type: "line",
    data: {
      labels: dates,
      datasets: [{
        label: "Expenses",
        data: amounts,
        borderColor: "rgba(75, 192, 192, 1)",
        backgroundColor: "rgba(75, 192, 192, 0.2)",
        fill: true,
        borderWidth: 3
      }]
    },
    options: {
      responsive: true,
      scales: {
        x: { title: { display: true, text: "Date / Month / Year" } },
        y: { title: { display: true, text: "Amount (₹)" } }
      }
    }
  });
}

function updateDoughnutChart(data) {
  const ctx = document.getElementById("paymentDoughnutChart").getContext("2d");

  if (paymentDoughnutChart) {
    paymentDoughnutChart.destroy();
  }

  paymentDoughnutChart = new Chart(ctx, {
    type: "doughnut",
    data: {
      labels: Object.keys(data),
      datasets: [{
        label: "Payment Modes / Categories",
        data: Object.values(data),
        backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF'],
        hoverOffset: 4
      }]
    },
    options: {
      responsive: true,
      plugins: {
        legend: { position: "top" }
      }
    }
  });
}

function getSelectedDoughnutData() {
  const doughnutOption = document.getElementById("doughnutSelect").value;

  if (doughnutOption === "Payment Mode") {
    return getPaymentModes();
  } else if (doughnutOption === "Category") {
    return getCategoryData();
  }
}

document.getElementById("statementSelect").addEventListener("change", function () {
  const selectedOption = this.value.toLowerCase().split(" ")[0];

  if (expenseData[selectedOption]) {
    updateTableData(expenseData[selectedOption]);
  }
});

document.getElementById("doughnutSelect").addEventListener("change", function () {
  updateDoughnutChart(getSelectedDoughnutData());
});

document.addEventListener("DOMContentLoaded", () => {
  updateTableData(expenseData.weekly);
});
