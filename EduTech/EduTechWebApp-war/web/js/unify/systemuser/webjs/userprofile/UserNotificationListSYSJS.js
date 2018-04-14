$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('.mailinbox tbody input:checkbox').click(function () {
        $(this).parents('tr').toggleClass('selected', $(this).prop('checked'));
    });
    
    $('.mailinbox tbody').on('click', 'tr', function() {
        var msgType, msgContent, msgContentID;
        var $cell= $(event.target).closest('td');
        if($cell.index() > 0 && $cell.index() < 4) {
            var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        
        msgType = $.trim(rowData[2]);
        msgContent = $.trim(rowData[3]);
        msgContentID = msgContent.split(';')[1];
        
        if(msgType.indexOf('Marketplace (Item Offer)') >= 0) {
            window.open('ProfileSysUser?pageTransit=goToPendingItemOfferDetailsSYS&urlitemID=' + msgContentID, '_self');
        } else if (msgType.indexOf('Marketplace (My Item Offer)') >= 0) {
            window.open('ProfileSysUser?pageTransit=goToMyBuyerOfferListSYS', '_self');
        } else if (msgType.indexOf('Marketplace (Item Like)') >= 0) {
            window.open('MarketplaceSysUser?pageTransit=goToMsgViewItemDetailsSYS&itemHidID=' + msgContentID, '_self');
        } else if (msgType.indexOf('Errands') >= 0) {
            window.open('ProfileSysUser?pageTransit=', '_self');
        } else if (msgType.indexOf('Voices') >= 0) {
            //window.open('ProfileSysUser?pageTransit=', '_self');
        }
        }
    });
    
    $('.marketplaceBtn').click(function (event) { $('#modal-custom').iziModal('open', event); });
    
    $('#notificationTable').DataTable({
        'dom': 'Bfrtip',
        'responsive': true, 
        'pageLength': 10,
        'buttons': [
                    'copy', 'csv', 'excel', 'pdf', 'print'
        ]
    });
});

function checkAll() {
    
    var rows = document.getElementById("notificationTable").getElementsByTagName("tbody")[0].getElementsByTagName('tr').length;
    var checkbox = document.getElementById("notificationTable").getElementsByTagName("tbody")[0].getElementsByTagName('input');
    
    for(var i=0;i<rows;i++) {
        if(checkbox[i].type==='checkbox'&&checkbox[i].checked===false){
            checkbox[i].checked=true;
            $('#'+i).addClass('checked');
        } else if(checkbox[i].type==='checkbox'&&checkbox[i].checked===true){
            checkbox[i].checked=false;
            $('#'+i).removeClass('checked');
        }
    }
}

function readMessage(sequenceID) {
    var markBtn = document.getElementById('markBtn');
    markBtn.disabled = false;
    if($('#'+sequenceID).hasClass('checked')) { $('#'+sequenceID).removeClass('checked'); }
    else { $('#'+sequenceID).addClass('checked'); }
}

function markRead() {
    var rows = document.getElementById("notificationTable").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
    var notificationList = new Array();
    for(var i=0;i<rows;i++) {
        if($('#'+i).hasClass('checked')) {
            $.ajax({
                type: "POST",
                url: "ProfileSysUser",
                data: { 
                    msgContentID: document.getElementById('msgContentID-'+i).innerHTML.substring(1),
                    msgSenderID: document.getElementById('msgSenderID-'+i).innerHTML,
                    pageTransit: 'markNotificationSYS'
                },
                success: function(returnString) {
                    
                }
            });
            $('input[id=' + i + ']').remove();
            $('label[for=' + i + ']').remove();
        }
    }
    document.getElementById("notificationList").value = notificationList;
    //document.notificationForm.submit();
}