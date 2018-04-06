var center = L.bounds([1.56073, 104.11475], [1.16, 103.502]).getCenter();
var map = L.map('mapdiv').setView([center.x, center.y], 12);
var search_marker, icon_popup;

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
    var dbTradeLocation = document.getElementById("dbTradeLocation").value;
    var dbTradeLat = document.getElementById("dbTradeLat").value;
    var dbTradeLong = document.getElementById("dbTradeLong").value;
    var dbItemCategoryID = document.getElementById("dbItemCategoryID").value;
    
    $('.form-row .assocItemCategory').find("#" + dbItemCategoryID).addClass("active");
    
    var $radios = $('input:radio[name=itemCondition]');
    if($('#dbItemCondition').val() == 'New') { $radios.filter('[value=New]').prop('checked', true); }
    else if($('#dbItemCondition').val() == 'Used') { $radios.filter('[value=Used]').prop('checked', true); }
    
    map.setMaxBounds([[1.56073, 104.1147], [1.16, 103.502]]);
    basemap.addTo(map);
    
    if (search_marker) { map.removeLayer(search_marker); }
    search_marker = L.marker([dbTradeLat, dbTradeLong], {draggable: false, icon: pointerIcon}).addTo(map).bindPopup("Trade Location: " + dbTradeLocation);
    
    $('#tradeLocation').keyup(function () {
        var baseHtml = 'https://developers.onemap.sg/commonapi/search?searchVal=';
        var location = $('#tradeLocation').val();
        document.getElementById("searchResults").innerHTML = "";
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
                        listOfResults += '<button type="button" class="list-group-item" onclick=\"selectedLocation(\'' + jsonResults[i].LATITUDE + '\',\'' + jsonResults[i].LONGTITUDE + '\',\'' + jsonResults[i].ADDRESS + '\',\'' + displayInput + '\');clearResults()\">' + displayResult + '</button>';
                    }
                }
                listOfResults += '</div>';
                document.getElementById("searchResults").innerHTML = listOfResults;
            }
        });
    });
    
    $('.form-row .card').click(function() {
        $('.form-row .card').removeClass('active');
        $(this).addClass('active');
        
        var itemCategoryID = $(this).attr('id');
        document.getElementById("dbItemCategoryID").value = itemCategoryID;
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
    alert("TESTING");
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
function selectedLocation(lat, lng, postalAddress, location) {
    document.getElementById("tradeLocation").value = location;
    document.getElementById("dbTradeLat").value = lat;
    document.getElementById("dbTradeLong").value = lng
    
    if (search_marker) { map.removeLayer(search_marker); }
    search_marker = L.marker([lat, lng], {draggable: false, icon: pointerIcon}).addTo(map).bindPopup("Trade Location: " + postalAddress);
}

function deleteAlert(itemID) {
    bootbox.dialog({
        title: '<h5>Confirmation Required</h5>',
        message: "<p>Are you sure that you want this item to be deleted?</p>",
        closeButton: false,
        buttons: {
            "Confirm Delete": {
                label: "Confirm Delete",
                className: 'btn-danger',
                callback: function () {
                    window.open('MarketplaceSysUser?pageTransit=deleteItemListingSYS&hiddenItemID=' + itemID, '_parent');
                }
            },
            cancel: {
                label: "Close",
                className: 'btn-theme'
            }
        }
    });
}