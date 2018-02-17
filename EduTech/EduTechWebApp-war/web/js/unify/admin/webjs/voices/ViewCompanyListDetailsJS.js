/* FOR PROFILE PICTURE UPLOAD TO SYSTEM */
function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function() {
        var output = document.getElementById('output-image');
        output.src = reader.result;
        document.getElementById('imageUploadStatus').value = "Uploaded";
    };
    reader.readAsDataURL(event.target.files[0]);
}

var rowReviewedCompany;
var rowReviewPosterID;
$(document).ready(function() {
    $('#datatable-responsive tbody').on('click', 'tr', function(event) {
        var $cell= $(event.target).closest('td');
        if($cell.index() > 0 && $cell.index() < 4) {
            var rowData = $(this).children("td").map(function() {
                return $(this).text();
            }).get();
            rowReviewedCompany =$.trim(rowData[1]);
            rowReviewPosterID = $.trim(rowData[2]);
            $('iframe').attr('src', 'VoicesAdmin?pageTransit=goToViewReviewListDetails&reviewedCompany=' + rowReviewedCompany + '&reviewPosterID=' + rowReviewPosterID);
            $('#reviewDetails-iframe').iziModal('open', event);
        }
    });
    
    $("#reviewDetails-iframe").iziModal({
        iconColor: '#337AB7',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        width: 540, 
        overlayClose: true,
        overlayColor: 'rgba(0, 0, 0, 0.6)',
        iframe : true,
        iframeHeight: 400,
        history: false,
        navigateCaption: true,
        navigateArrows: false
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
    
});