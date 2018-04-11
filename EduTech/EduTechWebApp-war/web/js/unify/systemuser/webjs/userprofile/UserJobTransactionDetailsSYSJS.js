var center = L.bounds([1.56073, 104.11475], [1.16, 103.502]).getCenter();
var map = L.map('mapdiv').setView([center.x, center.y], 12);
var search_marker, search_marker_1;

var basemap = L.tileLayer('https://maps-{s}.onemap.sg/v3/Default/{z}/{x}/{y}.png', {
    detectRetina: true,
    maxZoom: 18,
    minZoom: 11,
    id: 'your.mapbox.project.id',
    accessToken: 'your.mapbox.public.access.token'
});

$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    var startLocation = document.getElementById("startLocation").value;
    var startLatitude = document.getElementById("startLat").value;
    var startLongitude = document.getElementById("startLong").value;
    
    var endLocation = document.getElementById("endLocation").value;
    var endLatitude = document.getElementById("endLat").value;
    var endLongitude = document.getElementById("endLong").value;

    map.setMaxBounds([[1.56073, 104.1147], [1.16, 103.502]]);
    basemap.addTo(map);

    var pointerIcon = L.icon({
        iconUrl: 'images/pointer.png',
        iconSize: [38, 40],
        iconAnchor: [22, 94],
        popupAnchor: [-3, -76]
    });

    if (search_marker) { map.removeLayer(search_marker); }
    search_marker = L.marker([startLatitude, startLongitude], {draggable: false, icon: pointerIcon}).addTo(map).bindPopup("Start Location: " + startLocation);
    
    if (search_marker_1) { map.removeLayer(search_marker_1); }
    search_marker_1 = L.marker([endLatitude, endLongitude], {draggable: false, icon: pointerIcon}).addTo(map).bindPopup("End Location: " + endLocation);
    
    $('.marketplaceBtn').click(function (event) { $('#modal-custom').iziModal('open', event); });
});

