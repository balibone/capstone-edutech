$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('#userItemOfferTable').DataTable({
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
    
    $('.qtipEditButton').qtip({
        content: { title: { text: 'Edit Item Offer', button: true }, text: $('#editItemOfferTip') },
        position: { at: 'top center', my: 'bottom center' },
        style: { width: 225, height: 150 },
        hide: { event: 'click' },
        show: 'click'
    });
    
    $('.userItemOfferListBtn').click(function () {
        var tempItemOfferID = this.id;
        if (tempItemOfferID.indexOf('editItemOfferPanel') >= 0) {
            tempItemOfferID = tempItemOfferID.replace('editItemOfferPanel', '');
            $('#editItemOffer' + tempItemOfferID).trigger("click");
            $('#previousOfferPrice').text(($(this).closest('tr').find('.offerPriceTD').text()).split(';')[0]);
            $('#itemOfferHiddenID').val(tempItemOfferID);
        } else if(tempItemOfferID.indexOf('cancelItemOffer') >= 0) {
            tempItemOfferID = tempItemOfferID.replace('cancelItemOffer', '');
            var cancelResponse = confirm("Are you sure to cancel this item offer?");
            if (cancelResponse) {
                $.ajax({
                    type: "POST",
                    url: "ProfileSysUser",
                    data: { 
                        itemOfferHiddenID: tempItemOfferID,
                        pageTransit: 'cancelPersonalItemOfferSYS'
                    },
                    success: function(returnString) {
                        if(returnString.endsWith("!")) {
                            $('#successPanel').text(returnString.split(';')[0] + returnString.split(';')[2]).fadeIn(300);
                            $('#itemOfferRow' + tempItemOfferID + " td:nth-child(4)").html("<span class='badge badge-danger custom-badge'>" + returnString.split(';')[1] + "</span>");
                        } else if(returnString.endsWith(".")) {
                            $('#errorPanel').text(returnString).fadeIn(300);
                        }
                    }
                }); 
            }
        } else if(tempItemOfferID.indexOf('editItemOfferBtn') >= 0) {
            tempItemOfferID = tempItemOfferID.replace('editItemOfferBtn', '');
            var editResponse = confirm("Are you sure to edit this item offer?");
            if (editResponse) {
                $.ajax({
                    type: "POST",
                    url: "ProfileSysUser",
                    data: { 
                        itemOfferHiddenID: $('#itemOfferHiddenID').val(),
                        revisedItemOffer: $('#revisedItemOffer').val(),
                        pageTransit: 'editPersonalItemOfferSYS'
                    },
                    success: function(returnString) {
                        if(returnString.endsWith("!")) {
                            $('#successItemOfferResponse').text(returnString.split(';')[0] + returnString.split(';')[4]).show();
                            $('#itemOfferRow' + $('#itemOfferHiddenID').val() + " td:nth-child(4)").html("<span class='badge badge-warning custom-badge'>" + returnString.split(';')[1] + "</span>");
                            $('#itemOfferRow' + $('#itemOfferHiddenID').val() + " td:nth-child(3)").html("$" + returnString.split(';')[2] + "<span style='display:none'>;</span><br/>($" + returnString.split(';')[3] + ")");
                        } else if(returnString.endsWith(".")) {
                            $('#failedItemOfferResponse').text(returnString).show();
                        }
                    }
                }); 
            }
        }
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});