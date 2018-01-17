$(document).ready(function() {
    $("#newCompositeQuantity").keyup(function() {
        var tableItemNameArr = [], tableQtyArr = [];
        var iterateArrIndex = 0;
        var errorStatus = false;
        var errorText = "Quantity exceeded for";
        var valForCompute = $("input#newCompositeQuantity").val() - $("input#compositeQuantity").val();
        
        if(valForCompute > 0) {
            $('#assocItemInventoryTable tbody tr td:nth-child(2)').each(function(){
                tableItemNameArr.push($(this).text());
            });
            $('#assocItemInventoryTable tbody tr td:nth-child(4)').each(function(){
                tableQtyArr.push($(this).text());
            });
            $(".setLeftUL").find("li").each(function() {
                var newQty = (($(this).text()).substr(1, ($(this).text()).indexOf('-')-1))*valForCompute;
                if(newQty > tableQtyArr[iterateArrIndex]) {
                    errorText += " " + tableItemNameArr[iterateArrIndex] + ",";
                    errorStatus = true;
                }
                iterateArrIndex++;
            });
            if(errorText.slice(-1) == ",") { errorText = errorText.substr(0, errorText.length-1); } 
            if(errorStatus == true) {
                $('.errorMessage').text(errorText);
                $('#updateCompositeQty').prop('disabled', true);
                $('.errorMessage').show();
            }
            else {
                $('.errorMessage').text("");
                $('#updateCompositeQty').prop('disabled', false);
                $('.errorMessage').hide();
            }
        }
    });
});