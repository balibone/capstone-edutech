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

    var map = L.map('venueMap').setView([center.x, center.y], 12);
    map.setMaxBounds([[1.56073, 104.1147], [1.16, 103.502]]);
    zoomSnap: 0.25;
    basemap.addTo(map);

    var pointerIcon = L.icon({ 
        iconUrl: 'images/pointer.png', 
        iconSize: [38, 40], 
        iconAnchor: [22, 94], 
        popupAnchor: [-3, -76] 
    });

    
    if (search_marker) { map.removeLayer(search_marker); }
    search_marker = L.marker([requestVenueLat, requestVenueLong], {draggable: false, icon: pointerIcon}).addTo(map).bindPopup("Event Location: " + requestVenue);
   
   $("a[href='#eventRequest']").on('shown.bs.tab', function(e) {
      map.invalidateSize();
 });
   
});

 