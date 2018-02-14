var rowItemName;
var rowSellerID;
$(document).ready(function() {
    $('#datatable-responsive tbody').on('click', 'tr', function(event) {
        var $cell= $(event.target).closest('td');
        if($cell.index() > 0 && $cell.index() < 5) {
            var rowData = $(this).children("td").map(function() {
                return $(this).text();
            }).get();
            rowJobId = $.trim(rowData[0]);
            //rowSellerID = $.trim(rowData[3]);
            $('iframe').attr('src', 'ErrandsAdmin?pageTransit=goToViewJobDetailsInCategory&jobID=' + rowJobId);
            $('#modal-iframe').iziModal('open', event);
        }
    });
    
    $("#modal-iframe").iziModal({
        //title: 'Job Details',
        //subtitle: 'Administrator may delete this job listing here',
        //iconClass: 'fa fa-cubes',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#79ce6f',
        width: 700,
        overlayClose: true,
        iframe : true,
        iframeHeight: 405
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});