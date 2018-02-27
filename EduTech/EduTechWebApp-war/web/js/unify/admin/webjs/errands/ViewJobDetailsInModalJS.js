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
    
    var dbJobEndLocation = document.getElementById("dbJobEndLocation").value;
    var dbJobEndLat = document.getElementById("dbJobEndLat").value;
    var dbJobEndLong = document.getElementById("dbJobEndLong").value;
    
    var pointerIcon = L.icon({
        iconUrl: 'images/pointer.png',

        iconSize:     [38, 40], // size of the icon
        iconAnchor:   [22, 94], // point of the icon which will correspond to marker's location
        popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
});
    
    var map = L.map('errandsMap').setView([center.x, center.y], 11);
    map.setMaxBounds([[1.56073, 104.1147], [1.16, 103.502]]);
    basemap.addTo(map);
    
    if(search_marker) { map.removeLayer(search_marker); }
    search_marker = L.marker([dbJobStartLat, dbJobStartLong], { draggable: false, icon: pointerIcon }).addTo(map).bindPopup("Start Location: " + dbJobStartLocation);
    //icon_popup = L.popup().setLatLng([dbJobStartLat, dbJobStartLong]).setContent("Job Start Location: " + dbJobStartLocation).addTo(map);
    
    search_marker_1 = L.marker([dbJobEndLat, dbJobEndLong], { draggable: false, icon: pointerIcon }).addTo(map).bindPopup("End Location: " + dbJobEndLocation);
    //end_icon_popup = L.popup().setLatLng([dbJobEndLat, dbJobEndLong]).setContent("Job End Location: " + dbJobEndLocation).addTo(map);
});