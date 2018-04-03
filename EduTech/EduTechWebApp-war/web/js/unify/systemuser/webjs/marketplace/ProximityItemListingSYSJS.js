var center = L.bounds([1.56073, 104.11475], [1.16, 103.502]).getCenter();
var map = L.map('mapdiv').setView([center.x, center.y], 12);
var location_marker, proximity_range;
var json_response = {};

/* FOR THE INDIVIDUAL SMALL CIRCLE MARKERS */
var layer_marker;
var json_group = new L.FeatureGroup();

/* FOR THE LEAFLET MAP */
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
    iconAnchor: [19, 40],
    popupAnchor: [-1, -40]
});

$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');

    map.setMaxBounds([[1.56073, 104.1147], [1.16, 103.502]]);
    map.locate({setView: true, watch: true}).on('locationfound', function (e) {
        if (location_marker) {
            map.removeLayer(location_marker);
        }
        location_marker = L.marker([e.latitude, e.longitude], {draggable: false, icon: pointerIcon}).addTo(map).bindPopup("You are currently here.").openPopup();
        proximity_range = L.circle(L.latLng(e.latitude, e.longitude), 1000, {color: 'red', fillColor: '#f03', fillOpacity: 0.2}).addTo(map);
        json_response = jQuery.parseJSON($('#jsonResponse').val());
        // json_response = JSON.stringify(jQuery.parseJSON($('#jsonResponse').val()));

        if (layer_marker) {
            json_group.clearLayers(layer_marker);
        }
        for (i = 0; i < json_response.length; i++) {
            var popupOptions = {maxWidth: 150, maxHeight: 175};
            var popupContent =
                    "<div id='itemListingDIV'><table border='0' id='itemListingContent'>" +
                    "<tr><td rowspan='2'><img class='profilePic' src='uploads/commoninfrastructure/admin/images/" + json_response[i].itemSellerImage + "' /></td></tr>" +
                    "<tr><td><h3 class='profileHeader'>" + json_response[i].itemSellerID + "</h3><time class='profileTime'><i class='fa fa-clock-o' style='margin-right:5px;'></i>" + json_response[i].itemPostedDuration + "</time></td></tr>" +
                    "<tr><td colspan='2'><img style='width:95px;height:95px;margin:7px 0 7px 0;' src='uploads/unify/images/marketplace/item/" + json_response[i].itemImage + "' /></td></tr>" +
                    "<tr><td colspan='2'><strong>" + json_response[i].itemName + "</strong></td></tr>" +
                    "<tr><td colspan='2'><strong style='color:#FF0000;'>$" + json_response[i].itemPrice + "</strong></td></tr>" +
                    "</table></div>";

            var marker_location = new L.LatLng(json_response[i].itemTradeLat, json_response[i].itemTradeLong);
            layer_marker = L.circleMarker(marker_location, {
                radius: 7,
                fillColor: "#984EA3",
                color: "#FFFFFF",
                weight: 1,
                opacity: 1,
                fillOpacity: 0.8
            });
            layer_marker.bindPopup(popupContent, popupOptions);

            layer_marker.on({
                mouseover: function (e) {
                    var layer_marker = e.target;
                    layer_marker.setStyle({
                        radius: 8,
                        fillColor: "#FFFFFF",
                        color: "#000000",
                        weight: 1,
                        opacity: 1,
                        fillOpacity: 1
                    });
                },
                mouseout: function (e) {
                    var layer_marker = e.target;
                    layer_marker.setStyle({
                        radius: 7,
                        fillColor: "#984EA3",
                        color: "#FFFFFF",
                        weight: 1,
                        opacity: 1,
                        fillOpacity: 0.8
                    });
                },
                click: function (e) {
                    var layer_marker = e.target;
                    layer_marker.openPopup();
                }
            });
            var jsonDataDistDiff = getDistanceFromLatLngInKm(e.latitude, e.longitude, json_response[i].itemTradeLat, json_response[i].itemTradeLong);
            if (jsonDataDistDiff < 1) {
                json_group.addLayer(layer_marker);
            }
        }
        map.addLayer(json_group);
    }).on('locationerror', function (e) {
        console.log(e);
        alert("Location access has been denied.");
    });
    basemap.addTo(map);
});

/* COMPUTE DISTANCE(KM) BETWEEN TWO LATITUDE-LONGITUDE POINTS (VIA Haversine FORMULA) */
function getDistanceFromLatLngInKm(lat1, lon1, lat2, lon2) {
    var R = 6371;                                                             // RADIUS OF THE EARTH IN KM
    var dLat = degreeToRadian(lat2 - lat1);
    var dLon = degreeToRadian(lon2 - lon1);
    var a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
            Math.cos(degreeToRadian(lat1)) * Math.cos(degreeToRadian(lat2)) *
            Math.sin(dLon / 2) * Math.sin(dLon / 2);
    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    var d = R * c;                                                            // CONVERT THE DISTANCE TO KM
    return d;
}

function degreeToRadian(deg) {
    return deg * (Math.PI / 180);
}