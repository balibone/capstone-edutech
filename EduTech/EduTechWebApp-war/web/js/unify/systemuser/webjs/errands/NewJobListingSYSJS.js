$(document).ready(function () {
    
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    
    $('#startLocation').keyup(function () {
        var baseHtml = 'https://developers.onemap.sg/commonapi/search?searchVal=';
        var location = $('#startLocation').val();
        document.getElementById("searchResults_start").innerHTML = "";
        $.ajax({
            url: baseHtml + location + "&returnGeom=Y&getAddrDetails=Y",
            method: "GET",
            dataType: "JSON"
        }).done(function (searchResults) {
            var jsonResults = searchResults.results;
            var listOfResults = "<div class='list-group'>";
            if (jsonResults != undefined) {
                for (i = 0; i < jsonResults.length; i++) {
                    var displayResult = "";
                    var displayInput = "";
                    if (jsonResults[i].CATEGORY != "POI") {
                        if (jsonResults[i].BUILDING == "NIL") {
                            displayResult += jsonResults[i].ADDRESS;
                            displayInput = jsonResults[i].ADDRESS;
                        } else {
                            displayResult += jsonResults[i].BUILDING + '<br><label style="font-size:x-small">' + jsonResults[i].ADDRESS + '</label>';
                            displayInput = jsonResults[i].BUILDING;
                        }
                        listOfResults += '<button type="button" class="list-group-item" onclick=\"selectedStartLocation(\'' + jsonResults[i].LATITUDE + '\',\'' + jsonResults[i].LONGTITUDE + '\',\'' + jsonResults[i].ADDRESS + '\',\'' + displayInput + '\');clearResults()\">' + displayResult + '</button>';
                    }
                }
                listOfResults += '</div>';
                document.getElementById("searchResults_start").innerHTML = listOfResults;
            }
        });
    });
    
    $('#endLocation').keyup(function () {
        var baseHtml = 'https://developers.onemap.sg/commonapi/search?searchVal=';
        var location = $('#endLocation').val();
        document.getElementById("searchResults_end").innerHTML = "";
        $.ajax({
            url: baseHtml + location + "&returnGeom=Y&getAddrDetails=Y",
            method: "GET",
            dataType: "JSON"
        }).done(function (searchResults) {
            var jsonResults = searchResults.results;
            var listOfResults = "<div class='list-group'>";
            if (jsonResults != undefined) {
                for (i = 0; i < jsonResults.length; i++) {
                    var displayResult = "";
                    var displayInput = "";
                    if (jsonResults[i].CATEGORY != "POI") {
                        if (jsonResults[i].BUILDING == "NIL") {
                            displayResult += jsonResults[i].ADDRESS;
                            displayInput = jsonResults[i].ADDRESS;
                        } else {
                            displayResult += jsonResults[i].BUILDING + '<br><label style="font-size:x-small">' + jsonResults[i].ADDRESS + '</label>';
                            displayInput = jsonResults[i].BUILDING;
                        }
                        listOfResults += '<button type="button" class="list-group-item" onclick=\"selectedEndLocation(\'' + jsonResults[i].LATITUDE + '\',\'' + jsonResults[i].LONGTITUDE + '\',\'' + jsonResults[i].ADDRESS + '\',\'' + displayInput + '\');clearResults()\">' + displayResult + '</button>';
                    }
                }
                listOfResults += '</div>';
                document.getElementById("searchResults_end").innerHTML = listOfResults;
            }
        });
    });
    
    $('.form-row .card').click(function() {
        $('.form-row .card').removeClass('active');
        $(this).addClass('active');
        
        var categoryID = $(this).attr('id');
        document.getElementById("hiddenCategoryID").value = categoryID;
    });
    
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});

var center = L.bounds([1.56073, 104.11475], [1.16, 103.502]).getCenter();
var map = L.map('mapdiv').setView([center.x, center.y], 12);
var search_marker_start, search_marker_end, icon_popup;
var basemap = L.tileLayer('https://maps-{s}.onemap.sg/v3/Default/{z}/{x}/{y}.png', {
    detectRetina: true,
    maxZoom: 18,
    minZoom: 11,
    id: 'your.mapbox.project.id',
    accessToken: 'your.mapbox.public.access.token'
});
map.setMaxBounds([[1.56073, 104.1147], [1.16, 103.502]]);
basemap.addTo(map);


/* FOR PROFILE PICTURE UPLOAD TO SYSTEM */
function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function () {
        var output = document.getElementById('output-image');
        output.src = reader.result;
    };
    reader.readAsDataURL(event.target.files[0]);
}
/* FOR ONEMAP - CLOSE THE SEARCH RESULT PANEL AFTER SELECTING A RESULT */
function clearResults() {
    document.getElementById("searchResults_start").innerHTML = "";
    document.getElementById("searchResults_end").innerHTML = "";
}
/* FOR ONEMAP - UPDATE LATITUDE AND LONGITUDE FOR SELECTED LOCATION */
function selectedStartLocation(lat, lng, postalAddress, location) {
    document.getElementById("startLocation").value = location;
    document.getElementById("hiddenStartLat").value = lat;
    document.getElementById("hiddenStartLong").value = lng;
    
    if(search_marker_start) { map.removeLayer(search_marker_start); }
    search_marker_start = L.marker([lat, lng], { draggable: false });
    icon_popup = L.popup().setLatLng([lat, lng]).setContent(postalAddress).openOn(map);
}

function selectedEndLocation(lat, lng, postalAddress, location) {
    document.getElementById("endLocation").value = location;
    document.getElementById("hiddenEndLat").value = lat;
    document.getElementById("hiddenEndLong").value = lng;
    
    if(search_marker_end) { map.removeLayer(search_marker_end); }
    search_marker_end = L.marker([lat, lng], { draggable: false });
    icon_popup = L.popup().setLatLng([lat, lng]).setContent(postalAddress).openOn(map);
}

function displayDuration(){
    var select = document.getElementById("jobRateType");
    var selectedValue = select.options[select.selectedIndex].value;
    
    if(selectedValue == "HR"){
        var content = "<label for=\"jobDuration\">Job Duration&nbsp;<span class=\"asterik\">*</span></label>";
        content = content + "<input type=\"number\" class=\"form-control\" name=\"jobDuration\" required=\"required\" />";
        document.getElementById("jobDurationDiv").innerHTML = content;
    }
    
    if(selectedValue == "Fixed"){
        document.getElementById("jobDurationDiv").innerHTML = "";
    }
}



