<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Unify Errands - Signature Pad</title>
        
        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/systemuser/weblayout/errands/SignaturePadSYSCSS.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css">
    </head>
    <body onload="init()">
        
        <canvas id="can" width="400" height="300"></canvas>
        
        <div style="position:absolute;top:10%;left:85%;">Eraser</div>
        <div id="white" onclick="color(this)"></div>
        
        <button type="button" class="btn btn-primary" id="saveBtn" style="position:absolute; top:80%; left:30%;">Complete</button>
        <button type="button" class="btn btn-warning text-white" id="clr" onclick="erase()" style="position:absolute; top:80%; left:50%;">Clear</button>
        <input type="hidden" id="hiddenJobID" value="<%= request.getParameter("jobID")%>">
        <input type="hidden" id="hiddenUsername" value="<%= request.getParameter("username")%>">
        <input type="hidden" id="hiddenCategory" value="<%= request.getParameter("category")%>">
        <img id="canvasimg" style="position:absolute; top:90%; left:10%; display:none;">
    
    <script src="js/unify/systemuser/basejs/jquery-v3.2.1.min.js" type="text/javascript"></script>
    <script src="js/unify/systemuser/webjs/errands/SignaturePadSYSJS.js" type="text/javascript"></script>
    <script src="js/unify/systemuser/basejs/bootstrap-v4.min.js" type="text/javascript"></script>
    </body>
</html>