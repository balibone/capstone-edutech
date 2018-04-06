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

    $('#makeOfferBtn').qtip({
        content: { title: { text: 'Enter Your Offer Price', button: true }, text: $('#offerTooltip') },
        position: { at: 'top center', my: 'bottom center' },
        style: { width: 250, height: 195 },
        hide: { event: 'click' },
        show: 'click'
    });
    
    $('#qtipItemReportTrigger').qtip({
        content: { title: { text: 'Report Item Listing', button: true }, text: $('#reportItemListingTip') },
        position: { at: 'top center', my: 'right top' },
        style: { width: 250, height: 195 },
        hide: { event: 'click' },
        show: 'click'
    });
    
    $('.itemListingDetailsBtn').click(function() {
        var tempItemDetailsBtn = this.id;
        if (tempItemDetailsBtn.indexOf('sendOfferBtn') >= 0) {
            $.ajax({
                type: "POST",
                url: "MarketplaceSysUser",
                data: { 
                    itemIDHidden: $('#itemIDHidden').val(),
                    usernameHidden: $('#usernameHidden').val(),
                    itemOfferPrice: $('#itemOfferPrice').val(),
                    itemOfferDescription: $('#itemOfferDescription').val(),
                    pageTransit: 'sendItemOfferPrice'
                },
                success: function(returnString) {
                    $('#successOfferResponse').hide();
                    $('#failedOfferResponse').hide();
                    if(returnString.endsWith("!")) { $('#successOfferResponse').text(returnString).show(); }
                    else if(returnString.endsWith(".")) { $('#failedOfferResponse').text(returnString).show(); }
                }
            });
        } else if(tempItemDetailsBtn.indexOf('reportItemListingBtn') >= 0) {
            var itemID = tempItemDetailsBtn.replace('reportItemListingBtn', '');
            $('#qtipItemReportTrigger').trigger("click");
            $('#itemHiddenID').val(itemID);
        } else if (tempItemDetailsBtn.indexOf('sendItemReportBtn') >= 0) {
            $.ajax({
                type: 'POST',
                url: 'MarketplaceSysUser',
                data: { 
                    itemHiddenID: $('#itemHiddenID').val(),
                    itemReportCategory: $('#itemReportCategory').val(),
                    itemReportDescription: $('#itemReportDescription').val(),
                    pageTransit: 'reportItemListing'
                },
                success: function(returnString) {
                    $('#successReportResponse').hide();
                    $('#failedReportResponse').hide();
                    if(returnString.endsWith("!")) { $('#successReportResponse').text(returnString).show(); }
                    else if(returnString.endsWith(".")) { $('#failedReportResponse').text(returnString).show(); }
                }
            });
        } else if(tempItemDetailsBtn.indexOf('likeItemBtn') >= 0) {
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
        }
    });
    
    $('.itemLikes > a').click(function() {
        $('iframe').attr('src', 'MarketplaceSysUser?pageTransit=goToItemLikeList&itemID=' + $('#itemIDHidden').val());
        $('#itemLikeList-iframe').iziModal('open', event);
    });
    
    $("#itemLikeList-iframe").iziModal({
        title: 'Your Item Likers',
        subtitle: 'List of users who like your item',
        iconClass: 'fa fa-heart-o',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 650,
        overlayClose: true,
        iframe: true,
        iframeHeight: 450
    });
});