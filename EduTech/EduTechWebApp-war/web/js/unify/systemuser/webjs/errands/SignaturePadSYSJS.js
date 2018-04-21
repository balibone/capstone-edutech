var canvas, ctx, flag = false,
        prevX = 0,
        currX = 0,
        prevY = 0,
        currY = 0,
        dot_flag = false;

    var x = "black",
        y = 2;
    
    function init() {
        canvas = document.getElementById('can');
        ctx = canvas.getContext("2d");
        w = canvas.width;
        h = canvas.height;
    
        canvas.addEventListener("mousemove", function (e) {
            findxy('move', e)
        }, false);
        canvas.addEventListener("mousedown", function (e) {
            findxy('down', e)
        }, false);
        canvas.addEventListener("mouseup", function (e) {
            findxy('up', e)
        }, false);
        canvas.addEventListener("mouseout", function (e) {
            findxy('out', e)
        }, false);
    }
    
    
    function draw() {
        ctx.beginPath();
        ctx.moveTo(prevX, prevY);
        ctx.lineTo(currX, currY);
        ctx.strokeStyle = x;
        ctx.lineWidth = y;
        ctx.stroke();
        ctx.closePath();
    }
    
    function erase() {
        ctx.clearRect(0, 0, w, h);
        document.getElementById("canvasimg").style.display = "none";
        $("#saveBtn").prop('disabled', true);
    }
    
    
    function findxy(res, e) {
        if (res == 'down') {
            prevX = currX;
            prevY = currY;
            currX = e.clientX - canvas.offsetLeft;
            currY = e.clientY - canvas.offsetTop;
    
            flag = true;
            dot_flag = true;
            if (dot_flag) {
                ctx.beginPath();
                ctx.fillStyle = x;
                ctx.fillRect(currX, currY, 2, 2);
                ctx.closePath();
                dot_flag = false;
            }
        }
        if (res == 'up' || res == "out") {
            flag = false;
        }
        if (res == 'move') {
            if (flag) {
                prevX = currX;
                prevY = currY;
                currX = e.clientX - canvas.offsetLeft;
                currY = e.clientY - canvas.offsetTop;
                draw();
            }
        }
    }
    
$(document).on('click', '#saveBtn', function() {
        
        var dataURL = canvas.toDataURL();
        
        $.ajax({
            type: "POST",
            url: "ErrandsSysUser",
            data: { 
                jobID: $('#hiddenJobID').val(),
                category: $('#hiddenCategory').val(),
                signatureImg: dataURL,
                pageTransit: 'completeJobTransaction'
            },
            success: function(){
                window.parent.location.assign("ErrandsSysUser?pageTransit=goToJobDetailsAfterSign&jobID=" + $('#hiddenJobID').val() + "&category=" + $('#hiddenCategory').val());
            }
        });  
        
 });
 
$('#can').on('mousedown', function(){
    $("#saveBtn").prop('disabled', false);
});

$('#can').on('mouseup', function(){
    $("#saveBtn").prop('disabled', false);
});




