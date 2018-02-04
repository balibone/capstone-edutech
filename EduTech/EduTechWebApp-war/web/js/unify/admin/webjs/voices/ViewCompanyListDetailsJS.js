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
        if($cell.index() > 0) {
            var rowData = $(this).children("td").map(function() {
                return $(this).text();
            }).get();
            rowReviewedCompany =$.trim(rowData[1]);
            rowReviewPosterID = $.trim(rowData[2]);
            $('iframe').attr('src', 'VoicesAdmin?pageTransit=goToViewReviewListDetails&reviewedCompany=' + rowReviewedCompany + '&reviewPosterID=' + rowReviewPosterID);
            $('#modal-iframe').iziModal('open', event);
        }
    });
    
    $("#modal-iframe").iziModal({
        title: 'Review Details',
        subtitle: 'Administrator may delete this review here',
        iconClass: 'fa fa-cubes',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#337AB7',
        width: 760,
        overlayClose: true,
        iframe : true,
        iframeHeight: 525
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
    
});