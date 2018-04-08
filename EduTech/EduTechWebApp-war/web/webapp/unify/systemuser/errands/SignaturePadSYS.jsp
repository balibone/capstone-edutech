<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Unify Errands - Signature Pad</title>
        
        <!-- CASCADING STYLESHEET -->
        
        
    </head>
    <body onload="init()">
        
        <canvas id="can" width="400" height="300" style="position:absolute; top:10%; left:10%; border:2px solid;"></canvas>
        
        <div style="position:absolute;top:10%;left:85%;">Eraser</div>
        <div style="position:absolute;top:15%;left:87%;width:15px;height:15px;background:white;border:2px solid;" id="white" onclick="color(this)"></div>
        
        <button type="button" id="btn" onclick="save()" style="position:absolute; top:80%; left:10%;">Save</button>
        <button type="button" id="clr" onclick="erase()" style="position:absolute; top:80%; left:20%;">Clear</button>
        
        <img id="canvasimg" style="position:absolute; top:90%; left:10%; display:none;">
    
    
    <script src="js/unify/systemuser/webjs/errands/SignaturePadSYSJS.js" type="text/javascript"></script>
    </body>
</html>