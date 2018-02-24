/***************************************************************************************
*   Title:          UnifyAdminBaseJS.js
*   Purpose:        UNIFY ADMIN BASE JAVASCRIPT FILE (STRICTLY FOR USE BY EDUBOX UNIFY ONLY)
*   Modified By:    TAN CHIN WEE WINSTON
*   Date:           19 FEBRUARY 2018
*   Code version:   1.0
*   Availability:   === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/

"use strict"; 
$(document).ready(function() {
    $(".row-bg-toggle").click(function(a) {
        a.preventDefault(); $(".row.row-bg").each(function() {
            $(this).slideToggle(200);
        });
    }); 
    $("#sparkline-bar").sparkline("html", {type:"bar", height:"35px", zeroAxis:false, barColor:App.getLayoutColorCode("red")}); 
    $("#sparkline-bar2").sparkline("html", {type:"bar", height:"35px", zeroAxis:false, barColor:App.getLayoutColorCode("green")}); 
    $("#sparkline-bar3").sparkline("html", {type:"bar", height:"35px", zeroAxis:false, barColor:App.getLayoutColorCode("blue")}); 
    $("#sparkline-bar4").sparkline("html", {type:"bar", height:"35px", zeroAxis:false, barColor:App.getLayoutColorCode("yellow")}); 
});