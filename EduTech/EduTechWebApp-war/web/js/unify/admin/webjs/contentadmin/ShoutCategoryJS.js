function editCat(catID) {
    
    $('iframe').attr('src', 'ContentAdmin?pageTransit=goToEditCategory&categoryID=' + catID + '');
    $('#editCat-iframe').iziModal('open', event);
}


$(document).ready(function () {
    $('#newCat').on('click', function () {
        $('iframe').attr('src', 'ContentAdmin?pageTransit=goToNewShoutCategory');
        $('#newCat-iframe').iziModal('open', event);
    });

    $('#newCat-iframe').iziModal({
        title: 'New Shout Category',
        iconClass: 'fa fa-tag',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 350,
        overlayClose: true,
        iframe: true,
        iframeHeight: 200
    });

    
    
    $('#editCat-iframe').iziModal({
        title: 'Edit Shout Category',
        iconClass: 'fa fa-tag',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 350,
        overlayClose: true,
        iframe: true,
        iframeHeight: 250
    });

    $('#closeSuccess').click(function () { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function () { $('#errorPanel').fadeOut(300); });
});