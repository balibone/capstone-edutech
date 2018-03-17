$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('#newReviewReport').on('click', function() {
        $('iframe').attr('src', 'VoicesSysUser?pageTransit=goToNewReviewReportSYS&hiddenCompanyID='+ $('#companyID').val()
                                +'&hiddenReviewPoster='+$('#reviewPoster').val()+'&hiddenReviewID='+$('#reviewID').val());
        $('#newReviewReport-iframe').iziModal('open', event);
    });
    
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
