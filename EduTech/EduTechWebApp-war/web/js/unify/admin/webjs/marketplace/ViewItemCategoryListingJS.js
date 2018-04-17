var itemCategoryName, itemCategoryID;
$(document).ready(function() {
    $('#newItemCategory').on('click', function() {
        $('iframe').attr('src', 'MarketplaceAdmin?pageTransit=goToNewItemCategory');
        $('#newItemCategory-iframe').iziModal('open', event);
    });
    
    $('#newItemCategory-iframe').iziModal({
        title: 'New Item Category',
        subtitle: 'Fill in the information of the new item category here',
        iconClass: 'fa fa-tag',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 600,
        overlayClose: true,
        iframe : true,
        iframeHeight: 325
    });
    
    $('#itemCategoryListing tbody').on('click', 'tr', function(event) {
        var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        itemCategoryName = $.trim(rowData[1]);
        itemCategoryID = itemCategoryName.split(';')[1];
        $('iframe').attr('src', 'MarketplaceAdmin?pageTransit=goToViewItemCategoryDetails&itemCategoryID=' + itemCategoryID);
        $('#editItemCategory-iframe').iziModal('open', event);
    });
    
    $('#editItemCategory-iframe').iziModal({
        title: 'Edit Item Category Details',
        subtitle: 'Administrator may deactivate this item category here',
        iconClass: 'fa fa-tag',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 650,
        overlayClose: true,
        iframe : true,
        iframeHeight: 475,
        onClosed: function () { window.location.reload(true); }
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});