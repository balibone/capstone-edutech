var itemName, itemID, itemTransID;

$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('#itemTransTable').DataTable({
        'dom': 'Bfrtip',
        'responsive': true, 
        'pageLength': 10,
        'buttons': [
            {
                extend: 'collection',
                text: 'Export Options',
                autoClose: true,
                buttons: [
                    'copy', 'csv', 'excel', 'pdf', 'print'
                ]
            }
        ]
    });
    
    $('#itemTransTable tbody').on('click', 'tr', function() {
        var rowData = $(this).children('td').map(function() {
            return $(this).text();
        }).get();
        itemName = $.trim(rowData[4]);
        itemID = itemName.split(';')[1];
        itemTransID = itemName.split(';')[2];
        window.location.href = 'ProfileSysUser?pageTransit=goToMarketplaceTransDetailsSYS&itemID=' + itemID + '&itemTransID=' + itemTransID;
    });
    
    $("#itemDetails-iframe").iziModal({
        title: 'Item Listing Details',
        subtitle: 'Detailed information of your item listing',
        iconClass: 'fa fa-cubes',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 725,
        overlayClose: true,
        iframe: true,
        iframeHeight: 450
    });
    
    $('.marketplaceBtn').click(function (event) { $('#modal-custom').iziModal('open', event); });
});