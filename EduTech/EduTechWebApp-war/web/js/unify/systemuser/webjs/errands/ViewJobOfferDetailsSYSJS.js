$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('#offer-table').DataTable();
   
   $('#contentArea').jplist({
        itemsBox: '.list', itemPath: '.list-item', panelPath: '.jplist-search'
    });

});

function negotiateBtn(n){
    
        
        var message = document.getElementById("negotiateContent"+n).value;
        var offerID = document.getElementById("offerID"+n).value;
        var jobID = document.getElementById("jobID"+n).value;
        var username = document.getElementById("username"+n).value;
        
        window.location = "ErrandsSysUser?pageTransit=negotiateJobOffer&offerID=" + offerID + "&username=" + username + "&jobID=" + jobID + "&message=" + message;
};
