var rowCategoryName;
$(document).ready(function() {
    $('#newJobCategory').on('click', function() {
        $('iframe').attr('src', 'ErrandsAdmin?pageTransit=goToCreateNewJobCategory&categoryType=errands');
        $('#newJobCategory-iframe').iziModal('open', event);
    });
    
    $('#newJobCategory-iframe').iziModal({
        title: 'New Job Category',
        subtitle: 'Fill in the information of the new category here',
        iconClass: 'fa fa-cubes',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#337AB7',
        width: 525,
        overlayClose: true,
        iframe : true,
        iframeHeight: 325
    });
    
    $('#datatable-responsive tbody').on('click', 'tr', function(event) {
        var $cell= $(event.target).closest('td');
        if($cell.index() > 0) {
            var rowData = $(this).children("td").map(function() {
                return $(this).text();
            }).get();
            rowCategoryID = $.trim(rowData[1]);
            $('iframe').attr('src', 'ErrandsAdmin?pageTransit=goToViewJobCategoryDetails&urlCategoryID=' + rowCategoryID);
            $('#editJobCategory-iframe').iziModal('open', event);
        }
    });
    
    $('#editJobCategory-iframe').iziModal({
        title: 'Edit Job Category',
        subtitle: 'Administrator may deactivate this category here',
        iconClass: 'fa fa-cubes',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#337AB7',
        width: 540,
        overlayClose: true,
        iframe : true,
        iframeHeight: 350
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});