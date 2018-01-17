$(document).ready(function() {
    $('.section-nav > li > a').click(function(e) {
        e.preventDefault();
        var active_tab_selector = $('.section-nav > li.active > a').attr('href');
        
        var actived_nav = $('.section-nav > li.active');
        actived_nav.removeClass('active');

        /* ADD 'active' CSS INTO CLICKED NAVIGATION */
        $(this).parents('li').addClass('active');

        /* HIDE DISPLAYING TAB CONTENT */
        $(active_tab_selector).removeClass('active');
        $(active_tab_selector).addClass('hide');

        /* SHOW TARGET TAB CONTENT */
        var target_tab_selector = $(this).attr('href');
        $(target_tab_selector).removeClass('hide');
        $(target_tab_selector).addClass('active');
    });
    
    var countryOptions = {
        url: "resources/countries.json",
        getValue: "name",
        list: {	
            match: {
                enabled: true
            }
        },
        theme: "square"
    };
    $("#contactBillingCountry").easyAutocomplete(countryOptions);
    $("#contactShippingCountry").easyAutocomplete(countryOptions);
    
    $("#closeSuccess").click(function() { $('#successPanel').fadeOut(300); });
    $("#closeError").click(function() { $('#errorPanel').fadeOut(300); });
    
    $("#customerRadio").click(function () { $(".supplierInfo").hide(); });
    $("#supplierRadio").click(function () { $(".supplierInfo").show(); });
});

function copyBillingAdd() {
    $('#contactShippingAttn').val($('#contactBillingAttn').val());
    $('#contactShippingAddress').val($('#contactBillingAddress').val());
    $('#contactShippingCity').val($('#contactBillingCity').val());
    $('#contactShippingState').val($('#contactBillingState').val());
    $('#contactShippingZipCode').val($('#contactBillingZipCode').val());
    $('#contactShippingCountry').val($('#contactBillingCountry').val());
    $('#contactShippingFax').val($('#contactBillingFax').val());
    $('#contactShippingPhone').val($('#contactBillingPhone').val());
}