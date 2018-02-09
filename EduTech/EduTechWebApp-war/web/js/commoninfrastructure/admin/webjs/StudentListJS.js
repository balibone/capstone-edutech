/*$(function(){
    $('#myModal').modal({
        keyboard:false,
        backdrop:'static',
        show:false
    }).on('show.bs.modal',function(e){//run this function when modal is shown
        //get data-id from clicked element (table row)
        var studentId = e.relatedTarget.dataset.id;
        //change content of modal. 'this' refers to the element with id #studentModal
        $('#modalTitle').text("Student Details");
        $.ajax({
            cache: false,
            type: 'GET',
            url: 'backend.php',
            data: 'EID=' + essayId,
            success: function(data) {
                $modal.find('.edit-content').html(data);
            }
        });
    });
});
/*
var rowCategoryName;
$(document).ready(function() {
    $('#newItemCategory').on('click', function() {
        $('iframe').attr('src', 'MarketplaceAdmin?pageTransit=goToNewItemCategory&categoryType=marketplace');
        $('#newItemCategory-iframe').iziModal('open', event);
    });
    
    $('#newItemCategory-iframe').iziModal({
        title: 'New Item Category',
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
            rowCategoryName = $.trim(rowData[1]);
            $('iframe').attr('src', 'MarketplaceAdmin?pageTransit=goToViewItemCategoryDetails&urlCategoryName=' + rowCategoryName + '&urlCategoryType=marketplace');
            $('#editItemCategory-iframe').iziModal('open', event);
        }
    });
    
    $('#editItemCategory-iframe').iziModal({
        title: 'Edit Item Category',
        subtitle: 'Administrator may deactivate this item here',
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
*/