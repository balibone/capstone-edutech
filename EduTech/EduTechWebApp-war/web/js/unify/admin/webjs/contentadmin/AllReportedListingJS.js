var rowCategoryName;
$(document).ready(function () {

    $('#shoutReportList tbody').on('click', 'tr', function (event) {
        var $cell = $(event.target).closest('td');
        if ($cell.index() > 0) {
            var rowData = $(this).children("td").map(function () {
                return $(this).text();
            }).get();
            rowCategoryName = $.trim(rowData[0]);
            $('iframe').attr('src', 'ContentAdmin?pageTransit=goToReportedShoutDetailsFromAllList&shoutView=' + rowCategoryName);
            $('#viewShout-iframe').iziModal('open', event);
        }
    });

    $('#viewShout-iframe').iziModal({
        title: 'Shout Report',
        subtitle: 'Administrator may update report status here',
        iconClass: 'fa fa-wpforms',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#337AB7',
        width: 600,
        overlayClose: true,
        iframe: true,
        iframeHeight: 500
    });
    
    $('#shoutCommentReportList tbody').on('click', 'tr', function (event) {
        var $cell = $(event.target).closest('td');
        if ($cell.index() > 0) {
            var rowData = $(this).children("td").map(function () {
                return $(this).text();
            }).get();
            rowCategoryName = $.trim(rowData[0]);
            $('iframe').attr('src', 'ContentAdmin?pageTransit=goToReportedShoutCommentDetailsFromAllList&shoutView=' + rowCategoryName);
            $('#viewShoutComment-iframe').iziModal('open', event);
        }
    });

    $('#viewShoutComment-iframe').iziModal({
        title: 'Shout Comment Report',
        subtitle: 'Administrator may update report status here',
        iconClass: 'fa fa-wpforms',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#337AB7',
        width: 600,
        overlayClose: true,
        iframe: true,
        iframeHeight: 500
    });

    $('#reportedErrandsList tbody').on('click', 'tr', function (event) {
        var $cell = $(event.target).closest('td');
        if ($cell.index() > 0) {
            var rowData = $(this).children("td").map(function () {
                return $(this).text();
            }).get();
            rowCategoryName = $.trim(rowData[0]);
            $('iframe').attr('src', 'ContentAdmin?pageTransit=goToReportedErrandDetailsFromAllList&errandView=' + rowCategoryName);
            $('#viewErrand-iframe').iziModal('open', event);
        }
    });

    $('#viewErrand-iframe').iziModal({
        title: 'Errand Report',
        subtitle: 'Administrator may update report status here',
        iconClass: 'fa fa-wpforms',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#337AB7',
        width: 600,
        overlayClose: true,
        iframe: true,
        iframeHeight: 500
    });

    $('#reportedErrandsReviewList tbody').on('click', 'tr', function (event) {
        var $cell = $(event.target).closest('td');
        if ($cell.index() > 0) {
            var rowData = $(this).children("td").map(function () {
                return $(this).text();
            }).get();
            rowCategoryName = $.trim(rowData[0]);
            $('iframe').attr('src', 'ContentAdmin?pageTransit=goToReportedErrandReviewDetailsFromAllList&errandReviewView=' + rowCategoryName);
            $('#viewErrandReview-iframe').iziModal('open', event);
        }
    });

    $('#viewErrandReview-iframe').iziModal({
        title: 'Errand Review Report',
        subtitle: 'Administrator may update report status here',
        iconClass: 'fa fa-wpforms',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#337AB7',
        width: 600,
        overlayClose: true,
        iframe: true,
        iframeHeight: 500
    });

    $('#reportedItemsList tbody').on('click', 'tr', function (event) {
        var $cell = $(event.target).closest('td');
        if ($cell.index() > 0) {
            var rowData = $(this).children("td").map(function () {
                return $(this).text();
            }).get();
            rowCategoryName = $.trim(rowData[0]);
            $('iframe').attr('src', 'ContentAdmin?pageTransit=goToReportedMarketplaceDetailsFromAllList&marketplaceView=' + rowCategoryName);
            $('#viewMarketplace-iframe').iziModal('open', event);
        }
    });

    $('#viewMarketplace-iframe').iziModal({
        title: 'Marketplace Report',
        subtitle: 'Administrator may update report status here',
        iconClass: 'fa fa-wpforms',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#337AB7',
        width: 600,
        overlayClose: true,
        iframe: true,
        iframeHeight: 500
    });

    $('#reportedReviewList tbody').on('click', 'tr', function (event) {
        var $cell = $(event.target).closest('td');
        if ($cell.index() > 0) {
            var rowData = $(this).children("td").map(function () {
                return $(this).text();
            }).get();
            rowCategoryName = $.trim(rowData[0]);
            $('iframe').attr('src', 'ContentAdmin?pageTransit=goToReportedReviewDetailsFromAllList&reviewView=' + rowCategoryName);
            $('#viewReview-iframe').iziModal('open', event);
        }
    });

    $('#viewReview-iframe').iziModal({
        title: 'Company Review Report',
        subtitle: 'Administrator may update report status here',
        iconClass: 'fa fa-wpforms',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#337AB7',
        width: 600,
        overlayClose: true,
        iframe: true,
        iframeHeight: 500
    });

    $('#closeSuccess').click(function () {
        $('#successPanel').fadeOut(300);
    });
    $('#closeError').click(function () {
        $('#errorPanel').fadeOut(300);
    });
});
