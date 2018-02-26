$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');

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
    
    $('.itemCard').on('click', function(event) {
        var itemID = $(this).attr('id');
        if(itemID != null) {
            $('iframe').attr('src', 'MarketplaceSysUser?pageTransit=goToViewItemDetailsModalSYS&itemID=' + itemID);
            $('#itemcard-iframe').iziModal('open', event);
        } else {
            alert("Item cannot be found. Please refresh the page and try again.")
        }
    });
    
    $("#itemcard-iframe").iziModal({
        title: 'Item Details',
        subtitle: 'Item Description, Chat to Buy',
        iconClass: 'fa fa-cubes',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#337AB7',
        width: 525,
        overlayClose: true,
        iframe : true,
        iframeHeight: 425
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});