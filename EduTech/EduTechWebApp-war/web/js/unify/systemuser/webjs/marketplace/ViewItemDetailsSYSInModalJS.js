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

    var tradeLocation = document.getElementById("tradeLocation").value;
    var tradeLatitude = document.getElementById("tradeLat").value;
    var tradeLongitude = document.getElementById("tradeLong").value;

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
    
    $('#likeItemBtn').click(function(){
        $.ajax({
            type: "POST",
            url: "MarketplaceSysUser",
            data: { 
                itemIDHid: $('#itemIDHidden').val(),
                usernameHid: $('#usernameHidden').val(),
                pageTransit: 'likeItemListingDetails'
            },
            success: function(returnString) {
                $('.likeCount').text("");
                $('.likeCount').text(returnString);
                if($('#likeItemBtn').hasClass('likeStatus')) {
                    $('#likeItemBtn').removeClass('likeStatus');
                    $('#likeItemBtn').addClass('noLikeStatus');
                } else if($('#likeItemBtn').hasClass('noLikeStatus')) {
                    $('#likeItemBtn').removeClass('noLikeStatus');
                    $('#likeItemBtn').addClass('likeStatus');
                }
                if(returnString > 1) { $('.likeWording').text("Likes"); }
                else { $('.likeWording').text("Like"); }
            }
        });
    });
});