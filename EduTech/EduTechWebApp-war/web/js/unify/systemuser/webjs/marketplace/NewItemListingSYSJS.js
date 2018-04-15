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

$(document).ready(function () {
    map.setMaxBounds([[1.56073, 104.1147], [1.16, 103.502]]);
    basemap.addTo(map);

    $("a[href='#address']").on('shown.bs.tab', function(e) {
        map.invalidateSize(e);
    });
    
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
    
    $('.choice').click(function() {
        var categoryID = $(this).attr('id');
        document.getElementById('hiddenCategoryID').value = categoryID;
        document.getElementById('checkedValue').value = 'Checked';
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
    
    $('.owl-carousel').owlCarousel({
        loop: false,
        margin: 10,
        nav: true,
        responsive: {
            0: {items: 1},
            600: {items: 3},
            1000: {items: 5}
        }
    });
});

/* FOR ONEMAP - CLOSE THE SEARCH RESULT PANEL AFTER SELECTING A RESULT */
function clearResults() {
    document.getElementById("searchResults").innerHTML = "";
}
/* FOR ONEMAP - UPDATE LATITUDE AND LONGITUDE FOR SELECTED LOCATION */
function selectedLocation(lat, lng, postalAddress, location) {
    document.getElementById("tradeLocation").value = location;
    document.getElementById("hiddenTradeLat").value = lat;
    document.getElementById("hiddenTradeLong").value = lng;
    
    if(search_marker) { map.removeLayer(search_marker); }
    search_marker = L.marker([lat, lng], { draggable: false });
    icon_popup = L.popup().setLatLng([lat, lng]).setContent(postalAddress).openOn(map);
}