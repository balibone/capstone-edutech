
function newReviewReport(companyID, reviewPoster, reviewID, reporter) {
    $('iframe').attr('src', 'VoicesSysUser?pageTransit=goToNewReviewReportSYS&hiddenCompanyID='+ companyID
                                +'&hiddenReviewPoster='+reviewPoster+'&hiddenReviewID='+reviewID+'&hiddenReviewReporter='+reporter);
    $('#newReviewReport-iframe').iziModal('open', event);
    
}

function likeItemBtn(reviewID) {
    $.ajax({
            type: "POST",
            url: "VoicesSysUser",
            data: { 
                reviewIDHid: reviewID,
                usernameHid: $('#username').val(),
                pageTransit: 'likeReviewListingSYS'
            },
            success: function(returnString) {
                var likeCount = 'likeCount-'+reviewID;
                $('.'+likeCount).text(")");
                $('.'+likeCount).text(returnString+")");
                var itemID = "likeItemBtn-" + reviewID;
                
                if($('#'+itemID).hasClass('likeStatus')) {
                    $('#'+itemID).removeClass('likeStatus');
                    $('#'+itemID).addClass('noLikeStatus');
                } else if($('#'+itemID).hasClass('noLikeStatus')) {
                    $('#'+itemID).removeClass('noLikeStatus');
                    $('#'+itemID).addClass('likeStatus');
                }
            }
        });
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
        iframeHeight: 230,
        onClosed: function () { window.location.reload(true); }
    });
    
    $('#contentArea').jplist({
        itemsBox: '.list', itemPath: '.list-item', panelPath: '.jplist-search'
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


