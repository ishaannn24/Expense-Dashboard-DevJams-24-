document.getElementById('submit-btn').addEventListener('click', function() {
  const upi = parseFloat(document.querySelector('.upi input').value) || 0;
  const creditCard = parseFloat(document.querySelector('.credit-card input').value) || 0;
  const debitCard = parseFloat(document.querySelector('.debit-card input').value) || 0;
  const netBanking = parseFloat(document.querySelector('.netbanking input').value) || 0;

  const total = upi + creditCard + debitCard + netBanking;

  const limitDisplay = document.getElementById('limit-disp');
  limitDisplay.innerHTML = `&#8377;${total.toFixed(2)}`;
});
