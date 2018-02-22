var center = L.bounds([1.56073, 104.11475], [1.16, 103.502]).getCenter();
var search_marker, icon_popup;

var basemap = L.tileLayer('https://maps-{s}.onemap.sg/v3/Default/{z}/{x}/{y}.png', {
    detectRetina: true,
    maxZoom: 18,
    minZoom: 11,
    id: 'your.mapbox.project.id',
    accessToken: 'your.mapbox.public.access.token'
});

$(document).ready(function () {
    App.init();
    Plugins.init();
    FormComponents.init();
    
    var dbJobStartLocation = document.getElementById("dbJobStartLocation").value;
    var dbJobStartLat = document.getElementById("dbJobStartLat").value;
    var dbJobStartLong = document.getElementById("dbJobStartLong").value;
    
    var map = L.map('errandsMap').setView([center.x, center.y], 12);
    map.setMaxBounds([[1.56073, 104.1147], [1.16, 103.502]]);
    basemap.addTo(map);
    
    if(search_marker) { map.removeLayer(search_marker); }
    search_marker = L.marker([dbJobStartLat, dbJobStartLong], { draggable: false });
    icon_popup = L.popup().setLatLng([dbJobStartLat, dbJobStartLong]).setContent("Job Start Location: " + dbJobStartLocation).openOn(map);
});