$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    var dbItemCategory = $('#dbItemCategory').val();
    var splitResult = dbItemCategory.split(';');
    $("#itemCategoryDropdown ul").append('<li><span data-path="default">All Item Categories</span></li>');
    splitResult.forEach(function (itemCategoryEntry) {
        $("#itemCategoryDropdown ul").append('<li><span data-path=".' + itemCategoryEntry.replace(" ", "") + '">' + itemCategoryEntry + '</span></li>');
    });
    
    jQuery.fn.jplist.settings = {
        priceSlider: function ($slider) {
            $slider.slider({
                min: 0, max: 1000, range: true, values: [0, 1000]
                , slide: function (event, ui) {
                    $('#min-price').val(ui.values[0]);
                    $('#max-price').val(ui.values[1]);
                }
            });
        },
        priceValues: function ($slider) {
            $('#min-price').val($slider.slider('values', 0));
            $('#max-price').val($slider.slider('values', 1));
        }
    };
    
    $('#contentArea').jplist({
        itemsBox: '.list', itemPath: '.list-item', panelPath: '.jplist-search'
    });
    
    $('.qtipItemReportTrigger').qtip({
        content: { title: { text: 'Report Item Listing', button: true }, text: $('#reportItemListingTip') },
        position: { at: 'top center', my: 'bottom center' },
        style: { width: 250, height: 195 },
        hide: { event: 'click' },
        show: 'click'
    });
    
    $('.itemWishlistBtn').click(function () {
        var tempBtnID = this.id;
        if (tempBtnID.indexOf('likeItemBtn') >= 0) {
            tempBtnID = tempBtnID.replace('likeItemBtn', '');
            $.ajax({
                type: 'POST',
                url: 'MarketplaceSysUser',
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
                    if(returnString > 1) { $('#likeWording' + tempBtnID).text("Likes"); }
                    else { $('#likeWording' + tempBtnID).text("Like"); }
                }
            });
        } else if(tempBtnID.indexOf('reportItemListingBtn') >= 0) {
            tempBtnID = tempBtnID.replace('reportItemListingBtn', '');
            $('#qtipItemReportTrigger' + tempBtnID).trigger("click");
            $('#itemHiddenID').val(tempBtnID);
        }
    });
    
    $('#sendItemReportBtn').click(function() {
        $.ajax({
            type: 'POST',
            url: 'MarketplaceSysUser',
            data: { 
                itemHiddenID: $('#itemHiddenID').val(),
                itemReportCategory: $('#itemReportCategory').val(),
                itemReportDescription: $('#itemReportDescription').val(),
                pageTransit: 'reportItemListing'
            },
            success: function(returnString) {
                $('#successReportResponse').hide();
                $('#failedReportResponse').hide();
                if(returnString.endsWith("!")) { $('#successReportResponse').text(returnString).show(); }
                else if(returnString.endsWith(".")) { $('#failedReportResponse').text(returnString).show(); }
            }
        });
    });
            
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
    $('.marketplaceBtn').click(function (event) { $('#modal-custom').iziModal('open', event); });
});

