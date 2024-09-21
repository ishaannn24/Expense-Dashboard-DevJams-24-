function addPaymentMode() {
  const category = prompt("Enter the payment mode category (e.g., UPI, Credit Card):");
  const details = prompt("Enter the payment details (e.g., 889188xxxx@oksbi):");

  if (category && details) {
    
    const historyContainer = document.querySelector(".connect-history");

    const newHistory = document.createElement("div");
    newHistory.classList.add("history");

    
    const categoryDiv = document.createElement("div");
    categoryDiv.classList.add("connect-category");
    categoryDiv.innerHTML = `<h4>${category}</h4>`;

   
    const detailsDiv = document.createElement("div");
    detailsDiv.classList.add("connect-cred");
    detailsDiv.innerHTML = `<span>${details}</span>`;

   
    const editDelDiv = document.createElement("div");
    editDelDiv.classList.add("connect-edit-del");
    editDelDiv.innerHTML = `
      <i class="fa-solid fa-pen" onclick="editPaymentMode(this)"></i>
      <i class="fa-solid fa-trash" onclick="deletePaymentMode(this)"></i>
    `;

    
    newHistory.appendChild(categoryDiv);
    newHistory.appendChild(detailsDiv);
    newHistory.appendChild(editDelDiv);

    
    historyContainer.appendChild(newHistory);
  }
}


function editPaymentMode(element) {
  const historyItem = element.closest('.history');
  const category = historyItem.querySelector('.connect-category h4').innerText;
  const details = historyItem.querySelector('.connect-cred span').innerText;
  const newCategory = prompt("Edit the category:", category);
  const newDetails = prompt("Edit the payment details:", details);

  if (newCategory && newDetails) {
  
    historyItem.querySelector('.connect-category h4').innerText = newCategory;
    historyItem.querySelector('.connect-cred span').innerText = newDetails;
  }
}


function deletePaymentMode(element) {
  const historyItem = element.closest('.history');
  historyItem.remove();
}

document.querySelector('.connect-button').addEventListener('click', addPaymentMode);