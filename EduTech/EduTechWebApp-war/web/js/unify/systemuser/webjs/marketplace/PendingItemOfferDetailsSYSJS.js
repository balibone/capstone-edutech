$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');

    $('#contentArea').jplist({
        itemsBox: '.list', itemPath: '.list-item', panelPath: '.jplist-search'
    });

    $('.qtipAcceptOfferButton').qtip({
        content: {title: {text: 'Accept Item Offer', button: true}, text: $('#acceptOfferTooltip')},
        position: {at: 'top center', my: 'bottom center'},
        style: {width: 225, height: 125},
        hide: {event: 'click'},
        show: 'click'
    });

    $('.qtipNegotiateOfferButton').qtip({
        content: {title: {text: 'Negotiate Item Offer', button: true}, text: $('#negotiateOfferTooltip')},
        position: {at: 'top center', my: 'bottom center'},
        style: {width: 225, height: 125},
        hide: {event: 'click'},
        show: 'click'
    });

    $('.itemOfferBtn').click(function (event) {
        var btnItemOfferID = this.id;
        if (btnItemOfferID.indexOf('acceptOfferPanel') >= 0) {
            btnItemOfferID = btnItemOfferID.replace('acceptOfferPanel', '');
            $('#itemOfferHiddenID').val(btnItemOfferID);
        } else if (btnItemOfferID.indexOf('acceptOfferBtn') >= 0) {
            bootbox.dialog({
                title: '<h5>Confirmation Required</h5>',
                message: "<p>Are you sure that you want to accept this item offer?</p>",
                closeButton: false,
                buttons: {
                    "Confirm Accept": {
                        label: "Confirm Accept",
                        className: 'btn-success',
                        callback: function () {
                            window.open('ProfileSysUser?pageTransit=acceptAnItemOfferSYS&urlItemOfferID=' + $('#itemOfferHiddenID').val() 
                                    + '&itemIDHidden=' + $('#hiddenItemID').val() + '&sellerAcceptComments=' + $('#sellerAcceptComments').val(), '_self');
                        }
                    },
                    cancel: {
                        label: "Cancel",
                        className: 'btn-primary'
                    }
                }
            });
        } else if (btnItemOfferID.indexOf('negotiateOfferPanel') >= 0) {
            btnItemOfferID = btnItemOfferID.replace('negotiateOfferPanel', '');
            $('#itemOfferHiddID').val(btnItemOfferID);
        } else if (btnItemOfferID.indexOf('negotiateOfferBtn') >= 0) {
            bootbox.dialog({
                title: '<h5>Confirmation Required</h5>',
                message: "<p>Are you sure that you want to negotiate this item offer?</p>",
                closeButton: false,
                buttons: {
                    "Confirm Negotiate": {
                        label: "Confirm Negotiate",
                        className: 'btn-success',
                        callback: function () {
                            window.open('ProfileSysUser?pageTransit=negotiateAnItemOfferSYS&urlItemOfferID=' + $('#itemOfferHiddID').val() 
                                    + '&itemIDHidden=' + $('#hiddenItemID').val() + '&sellerNegotiateComments=' + $('#sellerNegotiateComments').val(), '_self');
                        }
                    },
                    cancel: {
                        label: "Cancel",
                        className: 'btn-primary'
                    }
                }
            });
        } else if (btnItemOfferID.indexOf('rejectOfferBtn') >= 0) {
            bootbox.dialog({
                title: '<h5>Confirmation Required</h5>',
                message: "<p>Are you sure that you want to reject this item offer?</p>",
                closeButton: false,
                buttons: {
                    "Confirm Reject": {
                        label: "Confirm Reject",
                        className: 'btn-success',
                        callback: function () {
                            btnItemOfferID = btnItemOfferID.replace('rejectOfferBtn', '');
                            window.open('ProfileSysUser?pageTransit=rejectAnItemOfferSYS&urlItemOfferID=' + btnItemOfferID + '&itemIDHidden=' + $('#hiddenItemID').val(), '_self');
                        }
                    },
                    cancel: {
                        label: "Cancel",
                        className: 'btn-primary'
                    }
                }
            });
        } else if (btnItemOfferID.indexOf('markAsSoldBtn') >= 0) {
            btnItemOfferID = btnItemOfferID.replace('markAsSoldBtn', '');
            bootbox.dialog({
                title: '<h5>Confirmation Required</h5>',
                message: "<p>This item offer shall be marked as completed.<br/>Do you want to mark your item listing as sold or still available?</p>",
                closeButton: false,
                buttons: {
                    "Sold": {
                        label: "Mark Listing As Sold",
                        className: 'btn-success',
                        callback: function () {
                            window.open('ProfileSysUser?pageTransit=completeAnItemOfferSYS&urlItemOfferID=' + btnItemOfferID + 
                                    '&itemIDHidden=' + $('#hiddenItemID').val() + '&itemStatus=Sold', '_self');
                        }
                    },
                    /* "Available": {
                        label: "Mark Listing As Available",
                        className: 'btn-success',
                        callback: function () {
                            window.open('ProfileSysUser?pageTransit=completeAnItemOfferSYS&urlItemOfferID=' + btnItemOfferID + 
                                    '&itemIDHidden=' + $('#hiddenItemID').val() + '&itemStatus=Available', '_self');
                        }
                    }, */
                    cancel: {
                        label: "Cancel",
                        className: 'btn-primary'
                    }
                }
            });
        } else if (btnItemOfferID.indexOf('markAsReopenBtn') >= 0) {
            btnItemOfferID = btnItemOfferID.replace('markAsReopenBtn', '');
            bootbox.dialog({
                title: '<h5>Confirmation Required</h5>',
                message: "<p>This item offer shall be marked as 'Failed', and item listing will be marked as 'Available'.ontinue?</p>",
                closeButton: false,
                buttons: {
                    "Confirm Re-Open": {
                        label: "Confirm Re-Open",
                        className: 'btn-danger',
                        callback: function () {
                            window.open('ProfileSysUser?pageTransit=reopenAnItemOfferSYS&urlItemOfferID=' + btnItemOfferID + 
                                    '&itemIDHidden=' + $('#hiddenItemID').val() + '&itemStatus=Available', '_self');
                        }
                    },
                    cancel: {
                        label: "Cancel",
                        className: 'btn-primary'
                    }
                }
            });
        } else if (btnItemOfferID.indexOf('provideFeedbackPanel') >= 0) {
            btnItemOfferID = btnItemOfferID.replace('provideFeedbackPanel', '');
            $('#hiddenItemOfferID').val(btnItemOfferID);
            $('#feedback-modal').iziModal('open', event);
        }
    });
    
    $('#feedback-modal').on('click', '.itemOfferBtn', function() {
        if($('#hiddenRatingReview').val() == "") {
            $('#feedback-modal').iziModal('close');
            bootbox.dialog({
                title: '<h5>Error Occured</h5>',
                message: "<p>Please select a rating for the buyer.</p>",
                closeButton: false,
                buttons: {
                    cancel: {
                        label: "Ok",
                        className: 'btn-primary'
                    }
                }
            });
        } else {
            location.href = "ProfileSysUser?pageTransit=provideFeedbackSYS&urlItemOfferID=" + $('#hiddenItemOfferID').val() + "&itemIDHidden=" + $('#hiddenItemID').val() + "&ratingReview=" + $('#hiddenRatingReview').val();
        }
    });
    
    $('#feedback-modal').on('click', '.ratingReview', function() {
        var ratingReview = $(this).attr('id');
        if(ratingReview == 'positive') {
            $('#positive').addClass('positive-color');
            $('#neutral').removeClass('neutral-color');
            $('#negative').removeClass('negative-color');
            $('#hiddenRatingReview').val("Positive");
        } else if(ratingReview == 'neutral') {
            $('#positive').removeClass('positive-color');
            $('#neutral').addClass('neutral-color');
            $('#negative').removeClass('negative-color');
            $('#hiddenRatingReview').val("Neutral");
        } else if(ratingReview == 'negative') {
            $('#positive').removeClass('positive-color');
            $('#neutral').removeClass('neutral-color');
            $('#negative').addClass('negative-color');
            $('#hiddenRatingReview').val("Negative");
        }
    });
    
    /* FEEDBACK MODAL CSS */
    $('#feedback-modal').iziModal({
        overlayClose: false,
        overlayColor: 'rgba(0, 0, 0, 0.6)'
    });
    
    $('#feedback-modal').on('click', 'header a', function (event) {
        event.preventDefault();
        var index = $(this).index();
        $(this).addClass('active').siblings('a').removeClass('active');
        $(this).parents('div').find("section").eq(index).removeClass('hide').siblings('section').addClass('hide');

        if ($(this).index() === 0) {
            $('#feedback-modal .iziModal-content .icon-close').css('background', '#ddd');
        } else {
            $('#feedback-modal .iziModal-content .icon-close').attr('style', '');
        }
    });
    
    $(".positive").on("mouseenter", function() {
        $('.icon-positive').addClass('animate-positive');
        $(this).addClass("change-color-positive");
    });
    $(".positive").on("mouseleave", function() {
        $('.icon-positive').removeClass('animate-positive');
        $(this).removeClass("change-color-positive");
    });
    $(".neutral").on("mouseenter", function() {
        $('.icon-neutral').addClass('animate-neutral');
        $(this).addClass("change-color-neutral");
    });
    $(".neutral").on("mouseleave", function() {
        $('.icon-neutral').removeClass('animate-neutral');
        $(this).removeClass("change-color-neutral");
    });
    $(".upload").on("mouseenter", function() {
        $('.icon-negative').addClass('animate-negative');
        $(this).addClass("change-color-negative");
    });
    $(".upload").on("mouseleave", function() {
        $('.icon-negative').removeClass('animate-negative');
        $(this).removeClass("change-color-negative");
    });
    
    $('#closeSuccess').click(function () { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function () { $('#errorPanel').fadeOut(300); });
    $('.marketplaceBtn').click(function (event) { $('#modal-custom').iziModal('open', event); });
});