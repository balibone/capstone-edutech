

function eventDetails(eventRequestID) {

   
        window.location.href = 'EventsSysUser?pageTransit=goToViewRequestedEventDetailsSYS&hiddenEventRequestID=' + eventRequestID + '';
}



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

    $('.card').on('click', function (event) {
        alert(here);
        var itemID = $(this).attr('id');
        if (itemID !== null) {
            $('iframe').attr('src', 'MarketplaceSysUser?pageTransit=goToViewItemDetailsModalSYS&itemID=' + itemID);
            $('#eventCard-iframe').iziModal('open', event);
        } else {
            alert("Item cannot be found. Please refresh the page and try again.");
        }
    });

     


    $('#closeSuccess').click(function () {
        $('#successPanel').fadeOut(300);
    });
    $('#closeError').click(function () {
        $('#errorPanel').fadeOut(300);
    });
});