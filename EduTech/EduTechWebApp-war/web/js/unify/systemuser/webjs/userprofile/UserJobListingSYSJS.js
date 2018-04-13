$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('#contentArea').jplist({
        itemsBox: '.list', itemPath: '.list-item', panelPath: '.jplist-search'
    });
    
    /*var dbJobCategory = $('#dbJobCategory').val();
    var splitResult = dbJobCategory.split(',');
    $("#categoryList").append('<li class="active"><span data-path="default">All Job Categories</span></li>');
    splitResult.forEach(function (jobCategoryEntry) {
        var category = jobCategoryEntry;
        category = category.replace("[", "");
        category = category.replace("]", "");
        
        jobCategoryEntry = jobCategoryEntry.replace("[", "");
        jobCategoryEntry = jobCategoryEntry.replace("]", "");
        jobCategoryEntry.replace(/ /g, '');
        
        jobCategoryEntry = jobCategoryEntry.replace(new RegExp(' ', 'g'), '');
        $("#categoryList").append('<li><span data-path=".' + jobCategoryEntry.replace(" ", "") + '">' + category + '</span></li>');
    });*/
    
    $('#closeSuccess').click(function () { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function () { $('#errorPanel').fadeOut(300); });
 });


