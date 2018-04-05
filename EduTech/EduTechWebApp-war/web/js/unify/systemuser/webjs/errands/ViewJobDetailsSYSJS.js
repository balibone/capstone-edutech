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

map.setMaxBounds([[1.56073, 104.1147], [1.16, 103.502]]);
basemap.addTo(map);

$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');

    var startLocation = document.getElementById("startLocation").value;
    var startLatitude = document.getElementById("startLat").value;
    var startLongitude = document.getElementById("startLong").value;  
    var endLocation = document.getElementById("endLocation").value;
    var endLatitude = document.getElementById("endLat").value;
    var endLongitude = document.getElementById("endLong").value;

    var pointerIcon = L.icon({
        iconUrl: 'images/pointer.png',
        iconSize: [38, 40],
        iconAnchor: [22, 94],
        popupAnchor: [-3, -76]
    });

    if (search_marker) { map.removeLayer(search_marker); }
    search_marker = L.marker([startLatitude, startLongitude], {draggable: false, icon: pointerIcon}).addTo(map).bindPopup("Start Location: " + startLocation);

    //if (search_marker_end) { map.removeLayer(search_marker_end); }
    search_marker_end = L.marker([endLatitude, endLongitude], {draggable: false, icon: pointerIcon}).addTo(map).bindPopup("End Location: " + endLocation);

    /*$('#makeOfferBtn').qtip({
        content: { title: { text: 'Enter Your Offer Price', button: true }, text: $('#offerTooltip') },
        position: { at: 'top center', my: 'bottom center' },
        style: { width: 250, height: 195 },
        hide: { event: 'click', inactive: 10000 },
        show: 'click'
    });*/
    
    $('#likeList').click(function(){
        $('iframe').attr('src', 'ErrandsSysUser?pageTransit=goToJobLikeList&jobID=' + $('#jobIDHidden').val());
        $('#jobLikeList-iframe').iziModal('open', event);
    });
    
    $("#jobLikeList-iframe").iziModal({
        title: 'Your Job Likers',
        subtitle: 'List of users who like your job',
        iconClass: 'fa fa-heart-o',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 650,
        overlayClose: true,
        iframe: true,
        iframeHeight: 450
    });
    
    var priceType = document.getElementById("priceType").value;
    if(priceType==='Fixed'){
        document.getElementById("offerPriceType").innerHTML = "(S$/Fixed Rate)";
    }else if(priceType==='HR'){
        document.getElementById("offerPriceType").innerHTML = "(S$/hour)";
    }
    
    /*$('#likeJobBtn').click(function(){
        
        $.ajax({
            type: "POST",
            url: "ErrandsSysUser",
            data: { 
                jobIDHid: $('#jobIDHidden').val(),
                usernameHid: $('#usernameHidden').val(),
                pageTransit: 'likeJobListingDetails'
            },
            success: function(returnString) {
                $('.likeCount').text("");
                $('.likeCount').text(returnString);
                if($('#likeJobBtn').hasClass('likeStatus')) {
                    $('#likeJobBtn').removeClass('likeStatus');
                    $('#likeJobBtn').addClass('noLikeStatus');
                } else if($('#likeJobBtn').hasClass('noLikeStatus')) {
                    $('#likeJobBtn').removeClass('noLikeStatus');
                    $('#likeJobBtn').addClass('likeStatus');
                }
                if(returnString > 1) { $('.likeWording').text("Likes"); }
                else { $('.likeWording').text("Like"); }
            }
        });
    });*/
    
    $('#sendOfferBtn').click(function(){
        $.ajax({
            type: "POST",
            url: "ErrandsSysUser",
            data: { 
                jobIDHidden: $('#jobIDHidden').val(),
                usernameHidden: $('#usernameHidden').val(),
                jobOfferPrice: $('#jobOfferPrice').val(),
                jobOfferDescription: $('#jobOfferDescription').val(),
                pageTransit: 'sendJobOfferPrice'
            },
            success: function(returnString) {
                $('#successOfferResponse').hide();
                $('#failedOfferResponse').hide();
                if(returnString.endsWith("!")) { $('#successOfferResponse').text(returnString).show(); }
                else if(returnString.endsWith(".")) { $('#failedOfferResponse').text(returnString).show(); }
            }
        });
    });
    
    
    $('#reportJobBtn').click(function(){
        $.ajax({
            type: "POST",
            url: "ErrandsSysUser",
            data: { 
                jobID: $('#jobIDHidden').val(),
                username: $('#usernameHidden').val(),
                reportReason: $('#reportReason').val(),
                pageTransit: 'reportJobListing'
            },
            success: function(returnString) {
               alert(returnString);
            }
        });
    });
 });     

$(document).on('click', '#likeJobBtn', function() {
        $.ajax({
            type: "POST",
            url: "ErrandsSysUser",
            data: { 
                jobIDHid: $('#jobIDHidden').val(),
                usernameHid: $('#usernameHidden').val(),
                pageTransit: 'likeJobListingDetails'
            },
            success: function(returnString) {
                $('.likeCount').text("");
                $('.likeCount').text(returnString);
                if($('#likeJobBtn').hasClass('likeStatus')) {
                    $('#likeJobBtn').removeClass('likeStatus');
                    $('#likeJobBtn').addClass('noLikeStatus');
                } else if($('#likeJobBtn').hasClass('noLikeStatus')) {
                    $('#likeJobBtn').removeClass('noLikeStatus');
                    $('#likeJobBtn').addClass('likeStatus');
                }
                if(returnString > 1) { $('.likeWording').text("Likes"); }
                else { $('.likeWording').text("Like"); }
            }
        });    
 });   


/*function deleteAlert(jobID, username) {
    var deleteReply = confirm("Are you sure to delete this job?");
    if (deleteReply) { window.open('ErrandsSysUser?pageTransit=deleteJobListingSYS&hiddenJobID=' + jobID + 'username=' + username, '_parent'); }
};*/

function otherReason(){
    var select = document.getElementById("reportReason");
    var selectedValue = select.options[select.selectedIndex].value;
    
    var innerContent = '<input type=\"text\" class=\"form-control\" name=\"otherReason\" size=\"65\" placeholder=\" Please specify the reason.*\" />';
    
    if(selectedValue == "others"){
        document.getElementById('otherReason').innerHTML = innerContent;
    }else{
        document.getElementById('otherReason').innerHTML = "";
    }
};


