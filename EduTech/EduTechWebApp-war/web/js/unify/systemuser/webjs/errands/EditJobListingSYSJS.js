var center = L.bounds([1.56073, 104.11475], [1.16, 103.502]).getCenter();
var map = L.map('mapdiv').setView([center.x, center.y], 12);
var search_marker, search_marker_1, icon_popup, search_marker_start, search_marker_end;


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
    
    /* PREPOPULATE DATA */
    var dbStartLocation = document.getElementById("dbStartLocation").value;
    var dbStartLat = document.getElementById("dbStartLat").value;
    var dbStartLong = document.getElementById("dbStartLong").value;
    var dbEndLocation = document.getElementById("dbEndLocation").value;
    var dbEndLat = document.getElementById("dbEndLat").value;
    var dbEndLong = document.getElementById("dbEndLong").value;
    //var dbJobCategoryID = document.getElementById("dbJobCategoryID").value;
    
    //$('.form-row .product-slider-item').find("#" + dbItemCategoryID).addClass("active");
    
    var jobDuration = document.getElementById("hiddenJobDuration").value;
    if(jobDuration === '1.0'){
        document.getElementById("smallSize").checked = true;
    }else if(jobDuration === '2.0'){
        document.getElementById("mediumSize").checked = true;
    }else if(jobDuration === '3.0'){
        document.getElementById("largeSize").checked = true;
    }
    
    var checking = document.getElementById("hiddenChecking");
    if(checking.value ==='true'){
        document.getElementById("checking").checked = true;
    }
    
    var $checkbox = $('input:checkbox[name=checking]');
    if($('#hiddenChecking').val() == 'true') { $checkbox.filter('[value=true]').prop('checked', true); }
    
    map.setMaxBounds([[1.56073, 104.1147], [1.16, 103.502]]);
    basemap.addTo(map);
    
    if (search_marker) { map.removeLayer(search_marker); }
    search_marker = L.marker([dbStartLat, dbStartLong], {draggable: false, icon: pointerIcon}).addTo(map).bindPopup("Start Location: " + dbStartLocation);
    
    if (search_marker_1) { map.removeLayer(search_marker); }
    search_marker_1 = L.marker([dbEndLat, dbEndLong], {draggable: false, icon: pointerIcon}).addTo(map).bindPopup("End Location: " + dbEndLocation);
    
    
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
    
    /*$('.form-row .card').click(function() {
        $('.form-row .card').removeClass('active');
        $(this).addClass('active');
        
        var itemCategoryID = $(this).attr('id');
        document.getElementById("dbItemCategoryID").value = itemCategoryID;
    });*/
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});

/* FOR PROFILE PICTURE UPLOAD TO SYSTEM */
function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function () {
        var output = document.getElementById('output-image');
        output.src = reader.result;
        document.getElementById('imageUploadStatus').value = "Uploaded";
    };
    reader.readAsDataURL(event.target.files[0]);
}

/* FOR ONEMAP - CLOSE THE SEARCH RESULT PANEL AFTER SELECTING A RESULT */
function clearResults() {
    document.getElementById("searchResults").innerHTML = "";
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

function deleteAlert(jobID) {
    var deleteReply = confirm("Are you sure to delete this job?");
    if (deleteReply) { window.open('ErrandsSysUser?pageTransit=deleteJobListingSYS&hiddenJobID=' + jobID, '_parent'); }
}

