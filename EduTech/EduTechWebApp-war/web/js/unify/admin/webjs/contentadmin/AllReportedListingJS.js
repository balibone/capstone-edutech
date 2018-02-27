var rowCategoryName;
$(document).ready(function () {


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
        width: 540,
        overlayClose: true,
        iframe: true,
        iframeHeight: 400
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
        width: 540,
        overlayClose: true,
        iframe: true,
        iframeHeight: 400
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
        width: 540,
        overlayClose: true,
        iframe: true,
        iframeHeight: 400
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
        width: 540,
        overlayClose: true,
        iframe: true
    });

    $('#closeSuccess').click(function () {
        $('#successPanel').fadeOut(300);
    });
    $('#closeError').click(function () {
        $('#errorPanel').fadeOut(300);
    });
});
