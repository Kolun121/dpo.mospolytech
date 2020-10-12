var today = new Date();
var currentMonth = today.getMonth();
var currentYear = today.getFullYear();
var selectYear = document.getElementById("year");
var selectMonth = document.getElementById("month");

var months = ["Янв", "Фев", "Мар", "Апр", "Май", "Июнь", "Июль", "Авг", "Сен", "Окт", "Ноя", "Дек"];

var monthAndYear = document.getElementById("monthAndYear");
showCalendar(currentMonth, currentYear);


function next() {
    currentYear = (currentMonth === 11) ? currentYear + 1 : currentYear;
    currentMonth = (currentMonth + 1) % 12;
    showCalendar(currentMonth, currentYear);
}

function previous() {
    currentYear = (currentMonth === 0) ? currentYear - 1 : currentYear;
    currentMonth = (currentMonth === 0) ? 11 : currentMonth - 1;
    showCalendar(currentMonth, currentYear);
}

function jump() {
    currentYear = parseInt(selectYear.value);
    currentMonth = parseInt(selectMonth.value);
    showCalendar(currentMonth, currentYear);
}

function showCalendar(month, year) {

    var firstDay = ((new Date(year, month)).getDay()-1);
    if(firstDay == -1){
        firstDay = 6;
    }
    var daysInMonth = 32 - new Date(year, month, 32).getDate();

    var tbl = document.getElementById("calendar-body"); // body of the calendar

    // Очистка ячеек
    tbl.innerHTML = "";

    // Заполнение ячееек
    monthAndYear.innerHTML = months[month] + " " + year;
    selectYear.value = year;
    selectMonth.value = month;

    // creating all cells
    var date = 1;
    var day = 1;
    for (var i = 0; i < 6; i++) {
        // creates a table row
        var row = document.createElement("tr");

        //creating individual cells, filing them up with data.
        for (var j = 0; j < 7; j++) {
            if (i === 0 && j < firstDay) {
                var cell = document.createElement("td");        
                var cellText = document.createTextNode("");
                cell.appendChild(cellText);
                row.appendChild(cell);
            }
            else if (date > daysInMonth) {
                break;
            }

            else {
                var cell = document.createElement("td");
                var button = document.createElement("button");
                button.id = day;
                button.onclick = function(){
                    filter_date(event, month, year);
                };
                var buttonText = document.createTextNode(date);                
                var cellText = document.createTextNode(date);
                if (date === today.getDate() && year === today.getFullYear() && month === today.getMonth()) {
                    cell.classList.add("bg-info");
                } // color today's date
                button.appendChild(buttonText);
                cell.appendChild(button);
                row.appendChild(cell);
//                cell.appendChild(cellText);
//                row.appendChild(cell);
                date++;
                day++;
            }


        }

        tbl.appendChild(row); 
    }

}
function filter_date(event, month, year){
    var day = event.target.id;
    month = month + 1;
    elem = document.getElementsByClassName('selectedCalendarButton');
    if(elem.length == 1){
        elem[0].classList.remove('selectedCalendarButton');
    };
    event.target.classList.add('selectedCalendarButton');
    $('.filter_loading').html('<div id="loading" style=""></div>');
    
    var formData = new FormData();
    
    formData.append('day', day);
    formData.append('month', month);
    formData.append('year', year);

    $.ajax({
    url: '/calendar/ajaxCalendarFilter/',
    type: 'POST',
    data: formData,
    cache: false,
    processData: false,
    contentType: false,
    beforeSend: function(){

    },
    success: function(data){
        console.log(data);
        //console.log($('.filter-data'));
        $('.filter_loading').html(data);           
    }
    });
}


