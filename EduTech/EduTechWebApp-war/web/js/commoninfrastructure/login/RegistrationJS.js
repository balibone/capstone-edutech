function showPassword1() {
    var x = document.getElementById("password1");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}

$(function(){
    /*START OF PASSWORD VALIDATION*/
    var myInput = document.getElementById("password1");
    var letter = document.getElementById("letter");
    var capital = document.getElementById("capital");
    var number = document.getElementById("number");
    var length = document.getElementById("length");
    // When the user clicks on the password field, show the passwordHint box
    myInput.onfocus = function() {
        document.getElementById("passwordHint").style.display = "block";
    };
    
    // When the user clicks outside of the password field, hide the passwordHint box
    myInput.onblur = function() {
        document.getElementById("passwordHint").style.display = "none";
    };
    
    // When the user starts to type something inside the password field
    myInput.onkeyup = function() {
        // Validate lowercase letters
        var lowerCaseLetters = /[a-z]/g;
        if(myInput.value.match(lowerCaseLetters)) { 
            letter.classList.remove("invalid");
            letter.classList.add("valid");
        } else {
            letter.classList.remove("valid");
            letter.classList.add("invalid");
        }
        
        // Validate capital letters
        var upperCaseLetters = /[A-Z]/g;
        if(myInput.value.match(upperCaseLetters)) { 
            capital.classList.remove("invalid");
            capital.classList.add("valid");
        } else {
            capital.classList.remove("valid");
            capital.classList.add("invalid");
        }
        
        // Validate numbers
        var numbers = /[0-9]/g;
        if(myInput.value.match(numbers)) { 
            number.classList.remove("invalid");
            number.classList.add("valid");
        } else {
            number.classList.remove("valid");
            number.classList.add("invalid");
        }
        
        // Validate length
        if(myInput.value.length >= 8) {
            length.classList.remove("invalid");
            length.classList.add("valid");
        } else {
            length.classList.remove("valid");
            length.classList.add("invalid");
        }
    };
    /*END OF PASSWORD VALIDATION*/
    
    /* Validation on form submit */
    $("#newUser").on("submit", function(event){
        var username = document.getElementById("username");
        //if username length is shorter than 5, prevent form submission.
        if(username.value.length < 5){
            event.preventDefault();
            alert("Invalid username. Please make sure your username is at least 5 characters long.");
        }
        
        //if any of the criterias are not met, prevent form submission.
        if(letter.classList.contains("invalid") || capital.classList.contains("invalid") || number.classList.contains("invalid") || length.classList.contains("invalid")){
            event.preventDefault();
            alert("Invalid password. Please make sure your password is a alphanumeric, contains 1 lowercase character, contains 1 uppercase character and is at least 8 characters long.");
        }
    });
});