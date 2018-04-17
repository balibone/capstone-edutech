$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    //$('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('.marketplaceBtn').click(function (event) { $('#modal-custom').iziModal('open', event); });
     
});

function download() {
    html2canvas(document.getElementById('wrapper'), {
        scale: 2,
            onrendered: function(canvas) {
                var imgData = canvas.toDataURL(
                    'image/png',1.0);              
                var pdf = new jsPDF('1', 'px');
                pdf.addImage(imgData, 'PNG',0,0);
                pdf.save('resume-file.pdf');
            }
        });
};
