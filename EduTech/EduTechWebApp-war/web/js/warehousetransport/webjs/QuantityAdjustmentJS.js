var rowCounter = 1;
var rowSelected;
var itemOptions = {
    url: "SGMapleStore",
    type : "GET",
    listLocation: "itemDetails",
    getValue: "itemName",
    list: {	
        match: {
            enabled: true
        },
        onClickEvent: function() {
            if(rowSelected[rowSelected.length -1] != 'e') {
                var rowIndex = rowSelected[rowSelected.length -1];
                $('#itemSKU' + rowIndex).val($('#itemName' + rowIndex).getSelectedItemData().itemSKU).trigger("change");
                $('#itemQuantityAvail' + rowIndex).val($('#itemName' + rowIndex).getSelectedItemData().itemQuantityAvail).trigger("change");
            }
            else {
                $('#itemSKU').val($('#itemName').getSelectedItemData().itemSKU).trigger("change");
                $('#itemQuantityAvail').val($('#itemName').getSelectedItemData().itemQuantityAvail).trigger("change");
            }
        }
    },
    theme: "square"
};
    
function addNewRow() {
    var htmlInsert = "<tr class='line-item new-line-item'><td class='line-item-column item-img'></td><td class='line-item-column item-details'><input type='text' style='margin-top: 4px;' placeholder='Type to select an item' class='form-control' id='itemName" + rowCounter + "' name='itemName' onclick='javascript: loadFieldID(this);' /></td><td class='line-item-column item-qty text-muted'><input type='text' readonly='readonly' class='displayField' size='15' id='itemSKU" + rowCounter + "' name='itemSKU' /></td><td class='line-item-column item-qty text-muted'><input type='text' readonly='readonly' class='displayField' size='15' id='itemQuantityAvail" + rowCounter + "' name='itemQuantityAvail' /></td><td class='line-item-column item-qty'><input type='text' style='margin-top: 4px;' placeholder='e.g. +10, -10' class='form-control' name='itemQuantityAdjust' /><div class='item-actions-container'><div style='position: absolute; right: -40px; top: -20px;'><span class='active' style='font-size: 15px; cursor: pointer;'><i class='fa fa-window-close'></i></span></div></div></td></tr>";
    $('#recordTable').append(htmlInsert);
    $('#itemName' + rowCounter).easyAutocomplete(itemOptions);
    rowCounter++;
}
function loadFieldID(obj) { rowSelected = obj.id; }

$(document).ready(function(){
    $('#recordTable').on('click', '.active', function(){
        $(this).parent().parent().parent().parent().remove();
    });
    $('#itemName').easyAutocomplete(itemOptions);
    $("#closeSuccess").click(function() { $('#successPanel').fadeOut(300); });
    $("#closeError").click(function() { $('#errorPanel').fadeOut(300); });
});