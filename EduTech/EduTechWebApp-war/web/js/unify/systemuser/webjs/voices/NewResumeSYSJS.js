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
    var row_1 = document.getElementById("workExprTable").insertRow(-1);
    row_1.className = 'workExpr';
    row_1.innerHTML = '<tr class="workExpr">\n\
                            <td valign="middle"><b><center>Company: </center></b></td>\n\
                            <td valign="middle"><input type="text" class="form-control" name="award[]" style="margin-right: 10px; width: 100%"/></td>\n\
                            <td></td>\n\
                            <td></td></tr>';
    
    var row_2 = document.getElementById("workExprTable").insertRow(-1);
    row_2.className = 'workExpr';
    row_2.innerHTML = '<tr class="workExpr">\n\
                            <td valign="middle"><b><center>Position: </center></b></td>\n\
                            <td valign="middle"><input type="text" class="form-control" name="award[]" style="margin-right: 10px; width: 80%"/></td>\n\
                            <td valign="middle"><b><center>Period: </center></b></td>\n\
                            <td valign="middle"><input type="text" class="form-control" name="award[]" style="margin-right: 10px; width: 50%"/></td></tr>';
    
    var row_3 = document.getElementById("workExprTable").insertRow(-1);
    row_3.className = 'workExpr';
    row_3.innerHTML = '<tr class="workExpr">\n\
                            <td valign="middle"><b><center>Description: </center></b></td>\n\
                            <td valign="middle"><textarea type="text" class="form-control" name="projectDes[]" rows="2" style="width: 180%"></textarea></td>\n\
                            <td></td>\n\
                            <td></td></tr>';
}

function addEduExprRow() {
    var row_1 = document.getElementById("eduExprTable").insertRow(-1);
    row_1.className = 'eduExpr';
    row_1.innerHTML = '<tr class="eduExpr">\n\
                        <td valign="middle"><b><center>School Name: </center></b></td>\n\
                        <td valign="middle"><input type="text" class="form-control" name="schoolName[]" style="margin-right: 10px; width: 80%"/></td>\n\
                        <td valign="middle"><b><center>Period: </center></b></td>\n\
                        <td valign="middle"><input type="text" class="form-control" name="schoolPeriod[]" style="margin-right: 10px; width: 50%"/></td>\n\
                        <td></td></tr>';
    
    var row_2 = document.getElementById("eduExprTable").insertRow(-1);
    row_2.className = 'eduExpr';
    row_2.innerHTML = '<tr bgcolor="#D9DEE4" style="padding: 10px">\n\
                        <td valign="middle"><b><center>Degree: </center></b></td>\n\
                        <td valign="middle"><input type="text" class="form-control" name="schoolDegree[]" style="margin-right: 10px; width: 80%"/></td>\n\
                        <td valign="middle"><b><center>Major: </center></b></td>\n\
                        <td valign="middle"><input type="text" class="form-control" name="schoolMajor[]" style="margin-right: 10px; width: 90%"/></td>\n\
                        <td></td></tr>';
}

function addProExprRow() {
    var row_1 = document.getElementById("proExprTable").insertRow(-1);
    row_1.className = 'proExpr';
    row_1.innerHTML = '<tr class="proExpr">\n\
                            <td valign="middle"><b><center>Project Title: </center></b></td>\n\
                            <td valign="middle"><input type="text" class="form-control" name="projectTitle[]" style="margin-right: 10px; width: 80%"/></td>\n\
                            <td></td></tr>';
    
    var row_2 = document.getElementById("proExprTable").insertRow(-1);
    row_2.className = 'proExpr';
    row_2.innerHTML = '<tr class="proExpr">\n\
                            <td valign="middle"><b><center>Description: </center></b></td>\n\
                            <td valign="middle"><textarea type="text" class="form-control" name="projectDes[]" rows="2"></textarea></td>\n\
                            <td></td></tr>';
}

function addSkillSetRow() {
    var row_1 = document.getElementById("skillTable").insertRow(-1);
    row_1.className = 'skill';
    row_1.innerHTML = '<tr class="skill">\n\
                            <td><input type="text" class="form-control" name="proSkillName[]" /></td>\n\
                            <td><select class="form-control" name="proSkillLevel[]">\n\
                                <option value="" default>-- Select Skill Level --</option>\n\
                                <option value="Beginner">Beginner</option>\n\
                                <option value="Intermediate">Intermediate</option>\n\
                                <option value="Advanced">Advanced</option>\n\
                                </select></td>\n\
                            <td><input type="text" class="form-control" name="perSkillName[]" /></td>\n\
                            <td><select class="form-control" name="perSkillLevel[]">\n\
                                <option value="" default>-- Select Skill Level --</option>\n\
                                <option value="Beginner">Beginner</option>\n\
                                <option value="Intermediate">Intermediate</option>\n\
                                <option value="Advanced">Advanced</option>\n\
                                </select></td>\n\
                            <td></td></tr>';
}

function addAwardRow() {
    var row_1 = document.getElementById("awardTable").insertRow(-1);
    row_1.className = 'award';
    row_1.innerHTML = '<tr class="award">\n\
                            <td valign="middle"><b><center>Achievement: </center></b></td>\n\
                            <td valign="middle"><input type="text" class="form-control" name="award[]" style="margin-right: 10px;"/></td>\n\
                            <td></td></tr>';
    
}

function addReferenceRow() {
    var row_1 = document.getElementById("referenceTable").insertRow(-1);
    row_1.className = 'reference';
    row_1.innerHTML = '<tr class="reference">\n\
                            <td valign="middle"><b><center>Referee Name: </center></b></td>\n\
                            <td valign="middle"><input type="text" class="form-control" name="award[]" style="margin-right: 10px; width: 100%"/></td>\n\
                            <td></td>\n\
                            <td></td></tr>';
    
    var row_2 = document.getElementById("referenceTable").insertRow(-1);
    row_2.className = 'reference';
    row_2.innerHTML = '<tr class="reference">\n\
                            <td valign="middle"><b><center>Position: </center></b></td>\n\
                            <td valign="middle"><input type="text" class="form-control" name="award[]" style="margin-right: 10px; width: 80%"/></td>\n\
                            <td valign="middle"><b><center>Company: </center></b></td>\n\
                            <td valign="middle"><input type="text" class="form-control" name="award[]" style="margin-right: 10px; width: 80%"/></td></tr>';
    
    var row_3 = document.getElementById("referenceTable").insertRow(-1);
    row_3.className = 'reference';
    row_3.innerHTML = '<tr class="reference">\n\
                            <td valign="middle"><b><center>Reference: </center></b></td>\n\
                            <td valign="middle"><textarea type="text" class="form-control" name="projectDes[]" rows="2" style="width: 220%"></textarea></td>\n\
                            <td></td>\n\
                            <td></td></tr>';
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
