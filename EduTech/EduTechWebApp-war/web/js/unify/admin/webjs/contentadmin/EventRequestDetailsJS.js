var center = L.bounds([1.56073, 104.11475], [1.16, 103.502]).getCenter();
var search_marker;

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
    
    var requestVenue = document.getElementById("requestVenue").value;
    var requestVenueLat = document.getElementById("requestVenueLat").value;
    var requestVenueLong = document.getElementById("requestVenueLong").value;
    
    
    var pointerIcon = L.icon({
        iconUrl: 'images/pointer.png',

        iconSize:     [38, 40], // size of the icon
        iconAnchor:   [22, 94], // point of the icon which will correspond to marker's location
        popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
});
    
    var map = L.map('venueMap').setView([center.x, center.y], 11);
    map.setMaxBounds([[1.56073, 104.1147], [1.16, 103.502]]);
    basemap.addTo(map);
    
    if (search_marker) { map.removeLayer(search_marker); }
    search_marker = L.marker([requestVenueLat, requestVenueLong], { draggable: false, icon: pointerIcon }).addTo(map).bindPopup("Event Location: " + requestVenue);
    
    
    
    
});