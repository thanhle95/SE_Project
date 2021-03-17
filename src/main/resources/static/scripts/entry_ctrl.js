window.onload = function(){
    populateYears();
}

function populateYears() {
    //
    let yearSelect = document.getElementById("entry_year");
    // get the current year as a number
    let date = new Date();
    let year = date.getFullYear();

    // Make this year, and the 100 years before it available in the year <select>
    for(let i = 0; i <= 100; i++) {
        let option = document.createElement('option');
        option.textContent = year-i;
        yearSelect.appendChild(option);
    }
}