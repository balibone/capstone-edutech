var itemName, itemTransDate, itemID;
var jobTitle, jobTransDate, jobID;
var reviewDate, reviewCompanyID;

$(document).ready(function() {
    $('#userItemList tbody').on('click', 'tr', function(event) {
        var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        itemName = $.trim(rowData[1]);
        itemID = itemName.split(';')[1];
        $('iframe').attr('src', 'MarketplaceAdmin?pageTransit=goToViewItemListingDetails&itemID=' + itemID);
        $('#itemListingDetails-iframe').iziModal('open', event);
    });
    
    $('#userItemTransactionList tbody').on('click', 'tr', function(event) {
        var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        itemTransDate = $.trim(rowData[0]);
        itemID = itemName.split(';')[1];
        $('iframe').attr('src', 'MarketplaceAdmin?pageTransit=goToViewItemListingDetails&itemID=' + itemID);
        $('#itemListingDetails-iframe').iziModal('open', event);
    });
    
    $("#itemListingDetails-iframe").iziModal({
        title: 'Item Listing Details',
        subtitle: 'Administrator may deactivate this item listing here',
        iconClass: 'fa fa-cubes',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 650,
        overlayClose: true,
        iframe : true,
        iframeHeight: 450
    });
    
    $('#userErrandsList tbody').on('click', 'tr', function() {
        var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        jobTitle = $.trim(rowData[1]);
        jobID = jobTitle.split(';')[1];
        $('iframe').attr('src', 'ErrandsAdmin?pageTransit=goToViewJobDetails&jobID=' + jobID);
        $('#jobListingDetails-iframe').iziModal('open', event);
    });
    
    $('#userErrandsTransactionList tbody').on('click', 'tr', function() {
        var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        jobTransDate = $.trim(rowData[0]);
        jobID = jobTransDate.split(';')[1];
        $('iframe').attr('src', 'ErrandsAdmin?pageTransit=goToViewJobDetails&jobID=' + jobID);
        $('#jobListingDetails-iframe').iziModal('open', event);
    });
    
    $("#jobListingDetails-iframe").iziModal({
        title: 'Job Listing Details',
        subtitle: 'Administrator may delete this job listing here',
        iconClass: 'fa fa-cubes',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 650,
        overlayClose: true,
        iframe : true,
        iframeHeight: 450
    });
    
    $('#userCompanyReviewsList tbody').on('click', 'tr', function() {
        var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        reviewDate = $.trim(rowData[0]);
        reviewCompanyID = reviewDate.split(';')[1];
        $('iframe').attr('src', 'ErrandsAdmin?pageTransit=goToViewJobDetails&jobID=' + jobID);
        $('#companyDetails-iframe').iziModal('open', event);
    });
    
    $("#companyDetails-iframe").iziModal({
        title: 'Job Listing Details',
        subtitle: 'Administrator may delete this job listing here',
        iconClass: 'fa fa-cubes',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 650,
        overlayClose: true,
        iframe : true,
        iframeHeight: 450
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});