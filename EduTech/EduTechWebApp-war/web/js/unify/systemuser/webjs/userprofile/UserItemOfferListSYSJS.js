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
        style: { width: 225, height: 155 },
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
            bootbox.dialog({
                title: '<h5>Confirmation Required</h5>',
                message: "<p>Are you sure that you want to cancel this item offer?</p>",
                closeButton: false,
                buttons: {
                    "Confirm Cancel": {
                        label: "Confirm Cancel",
                        className: 'btn-danger',
                        callback: function () {
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
                    },
                    cancel: {
                        label: "Close",
                        className: 'btn-primary'
                    }
                }
            });
        } else if(tempItemOfferID.indexOf('editItemOfferBtn') >= 0) {
            tempItemOfferID = tempItemOfferID.replace('editItemOfferBtn', '');
            bootbox.dialog({
                title: '<h5>Confirmation Required</h5>',
                message: "<p>Are you sure that you want to edit this item offer?</p>",
                closeButton: false,
                buttons: {
                    "Confirm Edit": {
                        label: "Confirm Edit",
                        className: 'btn-success',
                        callback: function () {
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
                                        $('#failedItemOfferResponse').hide();
                                        $('#successItemOfferResponse').text(returnString.split(';')[0] + returnString.split(';')[4]).show();
                                        $('#itemOfferRow' + $('#itemOfferHiddenID').val() + " td:nth-child(4)").html("<span class='badge badge-warning custom-badge'>" + returnString.split(';')[1] + "</span>");
                                        $('#itemOfferRow' + $('#itemOfferHiddenID').val() + " td:nth-child(3)").html("$" + returnString.split(';')[2] + "<span style='display:none'>;</span><br/>($" + returnString.split(';')[3] + ")");
                                    } else if(returnString.endsWith(".")) {
                                        $('#successItemOfferResponse').hide();
                                        $('#failedItemOfferResponse').text(returnString).show();
                                    }
                                }
                            });
                        }
                    },
                    cancel: {
                        label: "Cancel",
                        className: 'btn-primary'
                    }
                }
            });
        } else if(tempItemOfferID.indexOf('provideTransFeedback') >= 0) {
            tempItemOfferID = tempItemOfferID.replace('provideTransFeedback', '');
            var itemName = $(this).closest('tr').find('.itemName').text();
            $('#hiddenItemID').val(itemName.split(';')[1]);
            $('#hiddenItemOfferID').val(tempItemOfferID);
            $('#feedback-modal').iziModal('open', event);
        }
    });
    
    $('#feedback-modal').on('click', '.userItemOfferListBtn', function() {
        if($('#hiddenRatingReview').val() == "") {
            $('#feedback-modal').iziModal('close');
            bootbox.dialog({
                title: '<h5>Error Occured</h5>',
                message: "<p>Please select a rating for the seller.</p>",
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
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
    $('.marketplaceBtn').click(function (event) { $('#modal-custom').iziModal('open', event); });
});