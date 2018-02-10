$(document).ready(function () {
    $("#unifyPageNAV").load("webapp/unify/systemuser/masterpage/PageNavigation.jsp");
    $("#unifyFooter").load("webapp/unify/systemuser/masterpage/PageFooter.jsp");

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
        var itemID = $('#itemIDHidden').val();
        alert(itemID);
        $('iframe').attr('src', 'MarketplaceSysUser?pageTransit=goToViewItemDetailsModalSYS&itemID=' + itemID);
        $('#itemcard-iframe').iziModal('open', event);
    });
    
    $("#itemcard-iframe").iziModal({
        title: 'Item Details',
        subtitle: 'Administrator may deactivate this item here',
        iconClass: 'fa fa-cubes',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#337AB7',
        width: 900,
        overlayClose: true,
        iframe : true,
        iframeHeight: 525
    });
});