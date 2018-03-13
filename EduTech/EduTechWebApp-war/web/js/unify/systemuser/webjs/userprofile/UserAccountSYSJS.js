$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');

    $('#contentArea').jplist({
        itemsBox: '.list', itemPath: '.list-item', panelPath: '.jplist-search'
    });
    
    $('.myAccountBtn').click(function () {
        var tempBtnID = this.id;
        if (tempBtnID.indexOf('likeItemBtn') >= 0) {
            tempBtnID = tempBtnID.replace('likeItemBtn', '');
            $.ajax({
                type: "POST",
                url: "MarketplaceSysUser",
                data: {
                    itemIDHid: tempBtnID,
                    pageTransit: 'likeItemListingDetails'
                },
                success: function (returnString) {
                    $('#itemNumOfLikes' + tempBtnID).text("");
                    $('#itemNumOfLikes' + tempBtnID).text(returnString);
                    if ($('#likeItemBtn' + tempBtnID).hasClass('likeStatus')) {
                        $('#likeItemBtn' + tempBtnID).removeClass('likeStatus');
                        $('#likeItemBtn' + tempBtnID).addClass('noLikeStatus');
                    } else if ($('#likeItemBtn' + tempBtnID).hasClass('noLikeStatus')) {
                        $('#likeItemBtn' + tempBtnID).removeClass('noLikeStatus');
                        $('#likeItemBtn' + tempBtnID).addClass('likeStatus');
                    }
                }
            });
        } else if (tempBtnID.indexOf('pendingItemOfferBtn') >= 0) {
            tempBtnID = tempBtnID.replace('pendingItemOfferBtn', '');
            window.open('ProfileSysUser?pageTransit=goToPendingItemOfferList&urlitemID=' + tempBtnID, '_self');
        } else if (tempBtnID.indexOf('itemLikersBtn') >= 0) {
            tempBtnID = tempBtnID.replace('itemLikersBtn', '');
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
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});