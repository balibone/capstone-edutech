/***************************************************************************************
*   Title:          UnifyAdminCommonJS.js
*   Purpose:        COMMON JAVASCRIPT FILE SHARED ACROSS UNIFY ADMIN PORTAL
*                   (STRICTLY FOR USE BY EDUBOX UNIFY ONLY)
*   Modified By:    TAN CHIN WEE WINSTON
*   Date:           19 FEBRUARY 2018
*   Code version:   1.0
*   Availability:   === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/

$(document).ready(function () {
    App.init();
    Plugins.init();
    FormComponents.init()
    
    $('.newsticker').newsTicker({
        row_height: 34,
        max_rows: 2,
        speed: 600,
        direction: 'up',
        duration: 4000,
        autostart: 1,
        pauseOnHover: 0
    });
});

function establishTime() {
    var timeDisplay = document.createTextNode("");
    document.getElementById("clock").appendChild(timeDisplay);
}

function updateTime() {
    var currentTime = new Date();
    var currentHours = currentTime.getHours();
    var currentMinutes = currentTime.getMinutes();
    var currentSeconds = currentTime.getSeconds();

    currentMinutes = (currentMinutes < 10 ? "0" : "") + currentMinutes;
    currentSeconds = (currentSeconds < 10 ? "0" : "") + currentSeconds;

    var timeOfDay = (currentHours < 12) ? "AM" : "PM";
    currentHours = (currentHours > 12) ? currentHours - 12 : currentHours;
    currentHours = (currentHours == 0) ? 12 : currentHours;
    currentHours = (currentHours < 10 ? "0" : "") + currentHours;

    var currentTimeString = currentHours + ":" + currentMinutes + ":" + currentSeconds + " " + timeOfDay;
    document.getElementById("clock").firstChild.nodeValue = currentTimeString;
}