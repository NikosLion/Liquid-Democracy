/*Use this function to let user know that
there is a missmatch in password input.We change
the type of the input element with id="pass2"
to 'text' and display an error message.*/
function CheckPassEquality(){
  'use strict';
  var pass1 = document.getElementById('pass1').value;
  var pass2 = document.getElementById('pass2').value;
  if(pass1 != pass2){
    document.getElementById('pass2').title = "Password Missmatch";
    document.getElementById('pass2').style.color = "#cc0000";
    var message = document.getElementById('pass2');
    message.type = "text";
    message.value = "Password Missmatch";
  }
}
/*This function resets the type of input element with id="pass2"
to 'password'.*/
function ResetPassword(){
  var passVal1 = document.getElementById('pass1').value;
  var passVal2 = document.getElementById('pass2').value;
  if(passVal1 != passVal2){
    var pass = document.getElementById('pass2');
    pass.type = "password";
    pass.value = "";
    pass.style.color = "#595959";
  }
}
