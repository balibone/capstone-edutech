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
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    var tradeLocation = document.getElementById("tradeLocation").value;
    var tradeLatitude = document.getElementById("tradeLat").value;
    var tradeLongitude = document.getElementById("tradeLong").value;

    map.setMaxBounds([[1.56073, 104.1147], [1.16, 103.502]]);
    basemap.addTo(map);
    
    if(search_marker) { map.removeLayer(search_marker); }
    search_marker = L.marker([tradeLatitude, tradeLongitude], { draggable: false });
    icon_popup = L.popup().setLatLng([tradeLatitude, tradeLongitude]).setContent("Meetup Location: " + tradeLocation).openOn(map);
});