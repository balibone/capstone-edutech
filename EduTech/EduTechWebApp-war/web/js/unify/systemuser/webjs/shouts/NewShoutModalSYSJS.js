
$(document).ready(function () {

    App.init();
    Plugins.init();
    FormComponents.init();

    var shoutCat = $('#dbShoutCat').val();
    var splitResult = shoutCat.split(';');
    splitResult.forEach(function (shoutCat) {
        $('#shoutCat').append($('<option>', {value: shoutCat, text: shoutCat}));
    });

});