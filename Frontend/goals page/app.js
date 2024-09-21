// Initial limit set to 10,000
let currentLimit = 0;
let selectedCategory = 'Overall'; 


function updateLimit(increment) {
  currentLimit += increment ? 100 : -100;
  if (currentLimit < 0) currentLimit = 0; 
  document.querySelector('.range-toggle span').textContent = `₹${currentLimit}`;
}


function selectCategory(category) {
  selectedCategory = category;
  document.querySelector('.selected-category').textContent = `${selectedCategory} limit: ₹${currentLimit}`;
}


document.querySelector('.fa-plus').addEventListener('click', () => updateLimit(true));
document.querySelector('.fa-minus').addEventListener('click', () => updateLimit(false));


document.getElementById('credit-card').addEventListener('click', () => selectCategory('Credit Card'));
document.getElementById('upi').addEventListener('click', () => selectCategory('UPI'));
document.getElementById('overall').addEventListener('click', () => selectCategory('Overall'));
document.getElementById('debit-card').addEventListener('click', () => selectCategory('Debit Card'));
document.getElementById('net-banking').addEventListener('click', () => selectCategory('Net Banking'));
