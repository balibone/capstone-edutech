$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('#contentArea').jplist({
        itemsBox: '.list', itemPath: '.list-item', panelPath: '.jplist-search'
    });
    
    $('#closeSuccess').click(function () { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function () { $('#errorPanel').fadeOut(300); });
 });


