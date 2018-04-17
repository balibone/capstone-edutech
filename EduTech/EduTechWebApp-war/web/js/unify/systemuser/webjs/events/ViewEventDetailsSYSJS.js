function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function () {
        var output = document.getElementById('output-image');
        output.src = reader.result;
    };
    reader.readAsDataURL(event.target.files[0]);
}

function rsvpEvent(eventID) {

    if (confirm('Confirm RSVP to event?')) {

        $.get('EventsSysUser?pageTransit=goToRsvpToEventSYS&hiddenEventID=' + eventID + '');

        $('#rsvpEvent-alert').iziModal('open', event);
    }
}

var center = L.bounds([1.56073, 104.11475], [1.16, 103.502]).getCenter();
var map = L.map('mapdiv').setView([center.x, center.y], 12);
var search_marker, dataString;

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

    var tradeLocation = document.getElementById("venueLocation").value;
    var tradeLatitude = document.getElementById("venueLat").value;
    var tradeLongitude = document.getElementById("venueLong").value;

    map.setMaxBounds([[1.56073, 104.1147], [1.16, 103.502]]);
    basemap.addTo(map);

    var pointerIcon = L.icon({
        iconUrl: 'images/pointer.png',
        iconSize: [38, 40],
        iconAnchor: [22, 94],
        popupAnchor: [-3, -76]
    });

    if (search_marker) { map.removeLayer(search_marker); }
    search_marker = L.marker([tradeLatitude, tradeLongitude], {draggable: false, icon: pointerIcon}).addTo(map).bindPopup("Trade Location: " + tradeLocation);        
    
    $("a[href='#venuePane']").on('shown.bs.tab', function(e) {
      map.invalidateSize();
 });
 
 $('#rsvpEvent-alert').iziModal({
        title: 'RSVPed!',
        iconClass: 'fa fa-bookmark',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        width: 400,
        overlayClose: true,
        timeout: 10,
        timeoutProgressbar: true,
        pauseOnHover: false,
        timeoutProgressbarColor: 'rgba(255,255,255,0.5)',
        onClosed: function () {
            window.location.reload(true);
        }
    });
 
});