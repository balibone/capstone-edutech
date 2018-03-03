var itemTransDate, itemID;

$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('#itemTransTable').DataTable({ "responsive": true, "pageLength": 10, "lengthMenu": [10, 20, 30, 50] });
    
    $('#itemTransTable tbody').on('click', 'tr', function(event) {
        var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        itemTransDate = $.trim(rowData[1]);
        itemID = itemTransDate.split(';')[1];
        $('iframe').attr('src', 'MarketplaceSysUser?pageTransit=goToViewItemDetailsInModalSYS&itemID=' + itemID);
        $('#itemDetails-iframe').iziModal('open', event);
    });
    
    $("#itemDetails-iframe").iziModal({
        title: 'Item Listing Details',
        subtitle: 'Detailed information of your item listing',
        iconClass: 'fa fa-cubes',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 650,
        overlayClose: true,
        iframe: true,
        iframeHeight: 450
    });
});