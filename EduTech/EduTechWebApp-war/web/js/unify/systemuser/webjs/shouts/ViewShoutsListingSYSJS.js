function viewComment(shoutIDContent) {

    //alert(shoutIDContent)

    if (!shoutIDContent)
        return [];
    var i = shoutIDContent.indexOf(',');
    if (i > 0) {

        //alert(shoutIDContent.substring(0, i));
        //alert(shoutIDContent.substring(i + 1));

        $('iframe').attr('src', 'ShoutsSysUser?pageTransit=goToViewShoutCommentModalSYS&hiddenShoutID=' + shoutIDContent.substring(0, i) + '&hiddentShoutContent=' + shoutIDContent.substring(i + 1) + '');
    } else
        return [shoutIDContent];

    //$('iframe').attr('src', 'ShoutsSysUser?pageTransit=goToViewShoutCommentModalSYS&hiddenShoutID=' + shoutID + '');
    $('#viewComment-iframe').iziModal('open', event);
}

function reportShout(shoutIDContent) {

    if (!shoutIDContent)
        return [];
    var i = shoutIDContent.indexOf(',');
    if (i > 0) {
        //$('iframe').attr('src', 'ShoutsSysUser?pageTransit=goToViewShoutCommentModalSYS&hiddenShoutID=' + shoutIDContent.substring(0, i) + '&hiddentShoutContent=' + shoutIDContent.substring(i + 1) + '');
    } else
        return [shoutIDContent];


    $('iframe').attr('src', 'ShoutsSysUser?pageTransit=goToReportShoutModalSYS&hiddenShoutID=' + shoutIDContent.substring(0, i) + '&hiddentShoutContent=' + shoutIDContent.substring(i + 1) + '');
    $('#reportShout-iframe').iziModal('open', event);
}

function deleteAlert(shoutID) {

    if (confirm('Sure you want to delete your shout?')) {

        $.get('ShoutsSysUser?pageTransit=goToDeleteShoutSYS&hiddenShoutID=' + shoutID + '');

        setTimeout(window.location.reload(true),15);
        //window.location.reload(true);
        //$('#deleteShout-alert').iziModal('open', event);
    }
}

function bookmarkAlert(shoutIDusername) {

    //alert(shoutIDusername)

    if (!shoutIDusername)
        return [];
    var i = shoutIDusername.indexOf(',');
    if (i > 0) {
        //alert("TEST");
        //alert(shoutIDusername.substring(0, i));
        //alert(shoutIDusername.substring(i + 1));
        $.get('ShoutsSysUser?pageTransit=goToSetBookmarkSYS&hiddenShoutID=' + shoutIDusername.substring(0, i) + '&loggedInUsername=' + shoutIDusername.substring(i + 1) + '');
        //return [shoutIDusername.substring(0, i), shoutIDusername.substring(i + 1)];

    } else
        return [shoutIDusername];

    setTimeout(window.location.reload(true),15);
    //window.location.reload(true);
    //$('#bookmark-alert').iziModal('open', event);
}

function unbookmarkAlert(shoutIDusername) {

    //alert(shoutIDusername)

    if (!shoutIDusername)
        return [];
    var i = shoutIDusername.indexOf(',');
    if (i > 0) {
        //alert("TEST");
        //alert(shoutIDusername.substring(0, i));
        //alert(shoutIDusername.substring(i + 1));
        $.get('ShoutsSysUser?pageTransit=goToRemoveBookmarkSYS&hiddenShoutID=' + shoutIDusername.substring(0, i) + '&loggedInUsername=' + shoutIDusername.substring(i + 1) + '');
        //return [shoutIDusername.substring(0, i), shoutIDusername.substring(i + 1)];

    } else
        return [shoutIDusername];

    setTimeout(window.location.reload(true),15);
    //window.location.reload(true);
    //$('#unbookmark-alert').iziModal('open', event);
}

function bookmarkFrame(shoutIDusername) {

    alert(shoutIDusername)

    if (!shoutIDusername)
        return [];
    var i = shoutIDusername.indexOf(',');
    if (i > 0) {
        alert("TEST");
        alert(shoutIDusername.substring(0, i));
        alert(shoutIDusername.substring(i + 1));
        $('iframe').attr('src', 'ShoutsSysUser?pageTransit=goToSetBookmarkSYS&hiddenShoutID=' + shoutIDusername.substring(0, i) + '&loggedInUsername=' + shoutIDusername.substring(i + 1) + '');
        //return [shoutIDusername.substring(0, i), shoutIDusername.substring(i + 1)];

    } else
        return [shoutIDusername];


    //$('#bookmark-iframe').iziModal('open', event);
}

function likeAlert(shoutIDusername) {

    //alert(shoutIDusername)

    if (!shoutIDusername)
        return [];
    var i = shoutIDusername.indexOf(',');
    if (i > 0) {
        //alert("likeShout TEST");
        //alert(shoutIDusername.substring(0, i));
        //alert(shoutIDusername.substring(i + 1));
        //$.get('localhost/myWebbApp/executeInit.jsp');
        //return [shoutIDusername.substring(0, i), shoutIDusername.substring(i + 1)];
        $.get('ShoutsSysUser?pageTransit=goToSetLikeSYS&hiddenShoutID=' + shoutIDusername.substring(0, i) + '&loggedInUsername=' + shoutIDusername.substring(i + 1) + '');

    } else
        return [shoutIDusername];


    //$('#like-alert').iziModal('open', event);

    setTimeout(window.location.reload(true),15);
    //window.location.reload(true);

}

function unlikeAlert(shoutIDusername) {

    //alert(shoutIDusername)

    if (!shoutIDusername)
        return [];
    var i = shoutIDusername.indexOf(',');
    if (i > 0) {
        //alert("likeShout TEST");
        //alert(shoutIDusername.substring(0, i));
        //alert(shoutIDusername.substring(i + 1));
        //$.get('localhost/myWebbApp/executeInit.jsp');
        //return [shoutIDusername.substring(0, i), shoutIDusername.substring(i + 1)];
        $.get('ShoutsSysUser?pageTransit=goToRemoveLikeSYS&hiddenShoutID=' + shoutIDusername.substring(0, i) + '&loggedInUsername=' + shoutIDusername.substring(i + 1) + '');

    } else
        return [shoutIDusername];

    setTimeout(window.location.reload(true),15);
    //window.location.reload(true);
    //$('#unlike-alert').iziModal('open', event);
}

function newShout() {
    $('iframe').attr('src', 'ShoutsSysUser?pageTransit=goToNewShoutModalSYS');
    $('#newShout-iframe').iziModal('open', event);
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

    $('.itemCard').on('click', function (event) {
        var itemID = $(this).attr('id');
        if (itemID != null) {
            $('iframe').attr('src', 'MarketplaceSysUser?pageTransit=goToViewItemDetailsModalSYS&itemID=' + itemID);
            $('#itemcard-iframe').iziModal('open', event);
        } else {
            alert("Item cannot be found. Please refresh the page and try again.")
        }
    });



    $('#viewComment-iframe').iziModal({
        title: 'Comments',
        iconClass: 'fa fa-comments',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#009688',
        width: 500,
        overlayClose: true,
        iframe: true,
        iframeHeight: 500,
        onClosed: function () {
            window.location.reload(true);
        }
    });

    $('#newShout-iframe').iziModal({
        title: 'Create A Shout',
        iconClass: 'fa fa-bullhorn',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#009688',
        width: 500,
        overlayClose: true,
        iframe: true,
        iframeHeight: 500,
    });

    $('#reportShout-iframe').iziModal({
        title: 'Report Shout',
        iconClass: 'fa fa-flag',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#009688',
        width: 500,
        overlayClose: true,
        iframe: true,
        iframeHeight: 450
    });

    $('#bookmark-iframe').iziModal({
        title: 'Bookmark',
        iconClass: 'fa fa-bookmark',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 500,
        overlayClose: true,
        iframe: true,
        iframeHeight: 100
    });

    $('#deleteShout-alert').iziModal({
        title: 'Shout Deleted!',
        iconClass: 'fa fa-trash',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        width: 400,
        overlayClose: true,
        timeout: 10,
        timeoutProgressbar: true,
        pauseOnHover: false,
        timeoutProgressbarColor: 'rgba(255,255,255,0.5)',
        onClosed: function () {
            window.location.reload(true);
        }
    });

    $('#bookmark-alert').iziModal({
        title: 'Shout Bookmarked!',
        iconClass: 'fa fa-bookmark',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        width: 400,
        overlayClose: true,
        timeout: 10,
        timeoutProgressbar: true,
        pauseOnHover: false,
        timeoutProgressbarColor: 'rgba(255,255,255,0.5)',
        onClosed: function () {
            window.location.reload(true);
        }
    });

    $('#unbookmark-alert').iziModal({
        title: 'Shout Unbookmarked!',
        iconClass: 'fa fa-bookmark',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        width: 400,
        overlayClose: true,
        timeout: 10,
        timeoutProgressbar: true,
        pauseOnHover: false,
        timeoutProgressbarColor: 'rgba(255,255,255,0.5)',
        onClosed: function () {
            window.location.reload(true);
        }
    });

    $('#like-alert').iziModal({
        title: 'Shout Liked!',
        iconClass: 'fa fa-bookmark',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        width: 400,
        overlayClose: true,
        timeout: 10,
        timeoutProgressbar: true,
        pauseOnHover: false,
        timeoutProgressbarColor: 'rgba(255,255,255,0.5)',
        onClosed: function () {
            window.location.reload(true);
        }
    });



    $('#unlike-alert').iziModal({
        title: 'Shout Unliked!',
        iconClass: 'fa fa-bookmark',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        width: 400,
        overlayClose: true,
        timeout: 10,
        timeoutProgressbar: true,
        pauseOnHover: false,
        timeoutProgressbarColor: 'rgba(255,255,255,0.5)',
        onClosed: function () {
            window.location.reload(true);
        }
    });


    $('#closeSuccess').click(function () {
        $('#successPanel').fadeOut(300);
    });
    $('#closeError').click(function () {
        $('#errorPanel').fadeOut(300);
    });
});