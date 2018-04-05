var tabType = document.getElementById('tabType').value;
var tab = $('.nav-tabs li a'); 
var con = $('.tab-content .tab-pane');

for(var i=0;i<con.length;i++) {
    var mm = con[i];
    var selectCon = $(mm).attr('id');
    if(tabType === selectCon) {
        tab.removeClass('active');
        con.siblings().removeClass('show active');
        $(tab[i]).addClass('active');
        $(con[i]).addClass('show active');
    }
}

function newReviewReport(companyID, reviewPoster, reviewID) {
    $('iframe').attr('src', 'VoicesSysUser?pageTransit=goToNewReviewReportSYS&hiddenCompanyID='+ companyID
                                +'&hiddenReviewPoster='+reviewPoster+'&hiddenReviewID='+reviewID);
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
        iframeHeight: 230
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
