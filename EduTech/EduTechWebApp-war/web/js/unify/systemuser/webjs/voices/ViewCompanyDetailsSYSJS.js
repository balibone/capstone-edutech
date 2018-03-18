function newReviewReport(companyID, reviewPoster, reviewID) {
    alert(companyID);
    alert(reviewPoster);
    alert(reviewID);
    $('iframe').attr('src', 'VoicesSysUser?pageTransit=goToNewReviewReportSYS&hiddenCompanyID='+ companyID
                                +'&hiddenReviewPoster='+reviewPoster+'&hiddenReviewID='+reviewID);
    $('#newReviewReport-iframe').iziModal('open', event);
    
}

$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('#newReviewReport-iframe').iziModal({
        title: 'Report The Review',
        subtitle: 'Fill in the information of your reporting here',
        iconClass: 'fa fa-tag',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 400,
        overlayClose: true,
        iframe : true,
        iframeHeight: 230
    });
    
    $('#contentArea').jplist({
        itemsBox: '.list', itemPath: '.list-item', panelPath: '.jplist-search'
    });
    
    $('#likeItemBtn').click(function() {
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
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});

/* FOR PROFILE PICTURE UPLOAD TO SYSTEM */
function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function () {
        var output = document.getElementById('output-image');
        output.src = reader.result;
    };
    reader.readAsDataURL(event.target.files[0]);
}
