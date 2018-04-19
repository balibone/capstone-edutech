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

var pointerIcon = L.icon({ 
    iconUrl: 'images/pointer.png', 
    iconSize: [38, 40], 
    iconAnchor: [22, 94], 
    popupAnchor: [-3, -76] 
});

$(document).ready(function () {
   
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    //document.getElementById("selected-category").innerHTML = "testtest";
    map.setMaxBounds([[1.56073, 104.1147], [1.16, 103.502]]);
    basemap.addTo(map);
    
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
    
    $('.category-list .card').click(function() {
        $('.category-list .card').removeClass('active');
        $(this).addClass('active');
        
        var categoryID = $(this).attr('id');
        var categoryName = $(this).attr('name');
        document.getElementById("hiddenCategoryID").value = categoryID;
        document.getElementById("selected-category").innerHTML = categoryName;
        $("#step1").removeAttr("disabled")
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
    
    var dtToday = new Date();
    var month = dtToday.getMonth()+1;
    var day = dtToday.getDate();
    var year = dtToday.getFullYear();

    if(month < 10)
        month = '0' + month.toString();
    if(day < 10)
        day = '0' + day.toString();

    var minDate = year + '-' + month + '-' + day;    
    $('#workDate').attr('min', minDate);
});

$("input[type='text'], input[type='number'], input[type='date'], input[type='time']").on("keyup", function(){
    
    if($(this).val() != ""  && $("input[name='jobDuration']").is(":checked") == true 
            && $("input[name='jobTitle']").val() != "" && $("input[name='workDate']").val()>'1980-01-01'
            && $("input[type='number']").val() >= 0 && $("input[type='time']").val() != ""){
    	
            $("#step2").removeAttr("disabled");
        
    }
});

$("input[type='text']").on("keyup", function(){
    
    if($(this).val() != "" && $("input[name='endLocation']").val() != "" && $("input[name='startLocation']").val() != ""){
    	
            $("#step3").removeAttr("disabled");
        
    }
});

$("input[name='workDate']").keydown(false);
    

var jobRateInput = document.getElementById('jobRate');
var helperInput = document.getElementById('numOfHelpers');

// Listen for input event on numInput.
jobRateInput.onkeydown = function(e) {
    if(!((e.keyCode > 95 && e.keyCode < 106)
      || (e.keyCode > 47 && e.keyCode < 58) 
      || e.keyCode == 8 || e.keyCode === 190)) {
        return false;
    }
};

helperInput.onkeydown = function(e) {
    if(!((e.keyCode > 95 && e.keyCode < 106)
      || (e.keyCode > 48 && e.keyCode < 58) 
      || e.keyCode == 8)) {
        return false;
    }
}



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
    search_marker_start = L.marker([lat, lng], {draggable: false, icon: pointerIcon}).addTo(map).bindPopup("Start Location: " + location);
    
}

function selectedEndLocation(lat, lng, postalAddress, location) {
    
    document.getElementById("endLocation").value = location;
    document.getElementById("hiddenEndLat").value = lat;
    document.getElementById("hiddenEndLong").value = lng;
    
    if(search_marker_end) { map.removeLayer(search_marker_end); }
    search_marker_end = L.marker([lat, lng], {draggable: false, icon: pointerIcon}).addTo(map).bindPopup("End Location: " + location);
}

function displayDuration(){
    var select = document.getElementById("jobRateType");
    var selectedValue = select.options[select.selectedIndex].value;
    
    if(selectedValue == "HR"){
        document.getElementsByName('jobRate')[0].placeholder='Job Rate(S$/hr)';
    }
    
    if(selectedValue == "Fixed"){
         document.getElementsByName('jobRate')[0].placeholder = "Job Rate(S$)";
    }
}



