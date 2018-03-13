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
    
    $('#newCompanyRequest').on('click', function() {
        $('iframe').attr('src', 'VoicesSysUser?pageTransit=goToNewCompanyRequestSYS&hiddenUsername='+ $('#username').val());
        $('#newCompanyRequest-iframe').iziModal('open', event);
    });
    
    $('#newCompanyRequest-iframe').iziModal({
        title: 'Request for New Company',
        subtitle: 'Fill in the information of the requested company here',
        iconClass: 'fa fa-tag',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 500,
        overlayClose: true,
        iframe : true,
        iframeHeight: 325
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});