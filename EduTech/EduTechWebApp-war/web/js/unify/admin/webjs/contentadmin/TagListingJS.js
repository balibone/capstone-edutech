$(document).ready(function () {
    $('#newTag').on('click', function () {
        $('iframe').attr('src', 'ContentAdmin?pageTransit=goToNewTag');
        $('#newTag-iframe').iziModal('open', event);
    });

    $('#newTag-iframe').iziModal({
        title: 'New Tag',
        iconClass: 'fa fa-tag',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 350,
        overlayClose: true,
        iframe: true,
        iframeHeight: 200
    });

    $('button').on('click', function () {
        var tagID = this.id;
        if (tagID.includes("Edit")) {
            var tagEditID = tagID.replace("tagItemEdit", "");
            $('iframe').attr('src', 'ContentAdmin?pageTransit=goToEditTag&tagID=' + tagEditID + '');
            $('#editTag-iframe').iziModal('open', event);
        } else if (tagID.includes("Delete")) {
            if (window.confirm("Confirm deletion of tag?")) {
                var tagDeleteID = tagID.replace("tagItemDelete", "");
                $(location).attr('href', 'ContentAdmin?pageTransit=deleteTag&tagID=' + tagDeleteID + '');
            }
        }
    });
    
    $('#editTag-iframe').iziModal({
        title: 'Edit Tag',
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