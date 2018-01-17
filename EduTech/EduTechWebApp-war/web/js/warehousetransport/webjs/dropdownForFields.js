$(document).ready(function () {
    $("#subcategories").change(function () {
        var numInputs = $(this).val();
        $("#inputArea").empty();
        for (var i = 1; i <=     numInputs; i++)
            $("#inputArea").append('<div class="form-group"><label class="control-label col-md-3">Sub-Category '+i+'</label><div class="col-md-4"><input class="form-control" name="sCat'+i+'" /></div></div>');
    });
});