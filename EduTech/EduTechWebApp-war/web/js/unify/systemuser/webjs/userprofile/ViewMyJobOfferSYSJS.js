$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('#contentArea').jplist({
        itemsBox: '.list', itemPath: '.list-item', panelPath: '.jplist-search'
    });
    
    $('.myAccountBtn').click(function() {
        var tempBtnID = this.id;
        if(tempBtnID.indexOf('pendingItemOfferBtn') >= 0) {
            tempBtnID = tempBtnID.replace('pendingItemOfferBtn', '');
            window.open('ProfileSysUser?pageTransit=goToPendingItemOfferList&urlitemID=' + tempBtnID, '_self');
        } else if (tempBtnID.indexOf('likeItemBtn') >= 0) {
            tempBtnID = tempBtnID.replace('likeItemBtn', '');
            $('iframe').attr('src', 'MarketplaceSysUser?pageTransit=goToItemLikeList&itemID=' + tempBtnID);
            $('#itemLikeList-iframe').iziModal('open', event);
        }
    });
    
    $("#itemLikeList-iframe").iziModal({
        title: 'Your Item Likers',
        subtitle: 'List of users who like your item',
        iconClass: 'fa fa-heart-o',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 650,
        overlayClose: true,
        iframe: true,
        iframeHeight: 450
    });
    
    $('#editOffer').click(function(){
        $.ajax({
            type: "POST",
            url: "ProfileSysUser",
            data: { 
                jobIDHidden: $('#jobIDHidden').val(),
                //usernameHidden: $('#usernameHidden').val(),
                jobOfferPrice: $('#jobOfferPrice').val(),
                hiddenOfferPrice: $('#hiddenOfferPrice').val(),
                jobOfferDescription: $('#jobOfferDescription').val(),
                hiddenOfferDescription: $('#hiddenOfferDescription').val(),
                pageTransit: 'editMyJobOfferSYS'
            },
            success: function(returnString) {
                $('#successOfferResponse').hide();
                $('#failedOfferResponse').hide();
                if(returnString.endsWith("!")) { $('#successOfferResponse').text(returnString).show(); }
                else if(returnString.endsWith(".")) { $('#failedOfferResponse').text(returnString).show(); }
            }
        });
    });
});


