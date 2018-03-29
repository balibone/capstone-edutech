$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});



/* FOR PROFILE PICTURE UPLOAD TO SYSTEM */
function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function () {
        var output = document.getElementById('output-image');
        output.src = reader.result;
    };
    reader.readAsDataURL(event.target.files[0]);
}

function addWorkExprRow() {
    document.getElementById("workExprTable").insertRow(-1).innerHTML = '<tr><td><input type="text" class="form-control" name="workTitle[]" /></td>\n\
                                                                            <td><input type="text" class="form-control" name="workCompany[]" /></td>\n\
                                                                            <td><input type="text" class="form-control" name="workPeriod[]" /></td>\n\
                                                                            <td><textarea class="form-control" name="workDescription[]" rows="1"></textarea></td></tr>';
}

function addEduExprRow() {
    document.getElementById("eduExprTable").insertRow(-1).innerHTML = '<tr><td><input type="text" class="form-control" name="schoolName[]" /></td>\n\
                                                                            <td><input type="text" class="form-control" name="schoolDegree[]" /></td>\n\
                                                                            <td><input type="text" class="form-control" name="schoolMajor[]" /></td>\n\
                                                                            <td><input type="text" class="form-control" name="schoolPeriod[]" /></td></tr>';
}

function addProjectExprRow() {
    var block_to_insert = document.createElement( 'div' );
    block_to_insert.innerHTML = '<div class="form-group col-2"></div>\n\
                                 <div class="form-group col-8"><label for="projectTitle">Project Title&nbsp;</label><input type="text" class="form-control" name="projectTitle[]" />\n\
                                                               <label for="projectDes">Project Description&nbsp;</label><textarea type="text" class="form-control" name="projectDes[]" rows="3"></textarea></div>';
    block_to_insert.className = 'form-group row';
    
    var container_block = document.getElementById("projectExprTable");
    container_block.appendChild(block_to_insert);
}

function addSkillSetRow() {
    document.getElementById("skillSetTable").insertRow(-1).innerHTML = '<tr><td><input type="text" class="form-control" name="skillName[]" /></td>\n\
                                                                            <td><select class="form-control" name="skillLevel[]">\n\
                                                                                    <option value="" default>-- Select Skill Level --</option>\n\
                                                                                    <option value="Beginner">Beginner</option>\n\
                                                                                    <option value="Intermediate">Intermediate</option>\n\
                                                                                    <option value="Advanced">Advanced</option></select></td>';
}

function createWorkExprList() {
    var workTableLength = $("input[name^='workTitle']").length;
    var workTitleArr = $("input[name^='workTitle']");
    var workCompArr = $("input[name^='workCompany']");
    var workPeriodArr = $("input[name^='workPeriod']");
    var workDesArr = $("textarea[name^='workDescription']");
    var workExprList = new Array();
    
    for(var i=0;i<workTableLength;i++) {
        var workExpr = new Array();
        workExpr[0] = workTitleArr[i].value;
        workExpr[1] = workCompArr[i].value;
        workExpr[2] = workPeriodArr[i].value;
        workExpr[3] = workDesArr[i].value;
        workExprList.push(workExpr);
    }
    
    var eduTableLength = $("input[name^='schoolName']").length;
    var schoolNameArr = $("input[name^='schoolName']");
    var schoolDegreeArr = $("input[name^='schoolDegree']");
    var schoolMajorArr = $("input[name^='schoolMajor");
    var schoolPeriodArr = $("input[name^='schoolPeriod']");
    var eduExprList = new Array();
    
    
    for(var i=0;i<eduTableLength;i++) {
        var eduExpr = new Array();
        eduExpr[0] = schoolNameArr[i].value;
        eduExpr[1] = schoolDegreeArr[i].value;
        eduExpr[2] = schoolMajorArr[i].value;
        eduExpr[3] = schoolPeriodArr[i].value;
        eduExprList.push(eduExpr);
    }
    
    var proTableLength = $("input[name^='projectTitle']").length;
    var projectTitleArr = $("input[name^='projectTitle']");
    var projectDesArr = $("textarea[name^='projectDes']");
    var proExprList = new Array();
    
    for(var i=0;i<proTableLength;i++) {
        var proExpr = new Array();
        proExpr[0] = projectTitleArr[i].value;
        proExpr[1] = projectDesArr[i].value;
        proExprList.push(proExpr);
    }
    
    var skillTableLength = $("input[name^='projectTitle']").length;
    var skillNameArr = $("input[name^='skillName']");
    var skillLevelArr = $("select[name^='skillLevel']");
    var skillList = new Array();
    
    for(var i=0;i<skillTableLength;i++) {
        var skill = new Array();
        skill[0] = skillNameArr[i].value;
        skill[1] = skillLevelArr[i].value;
        skillList.push(skill);
    }
    
    document.getElementById("workExprList").value = workExprList;
    document.getElementById("eduExprList").value = eduExprList;
    document.getElementById("proExprList").value = proExprList;
    document.getElementById("skillList").value = skillList;
    document.resumeForm.submit();
    
}
