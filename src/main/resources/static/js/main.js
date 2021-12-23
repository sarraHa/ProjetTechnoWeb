function verificationFormulaire() {  
   username = document.getElementById("username").value;
   password = document.getElementById("password").value;
   passwordConfirm = document.getElementById("confirm").value;
   if(password != passwordConfirm){
       document.getElementById("passwordError").style.display = "inline";
   }else if(password.trim()== "" || passwordConfirm.trim() == "" || username.trim() == ""){
       document.getElementById("emptyForm").style.display = "inline";
   }else{
       document.getElementById("registrationForm").submit();
   }
}

function inputError(){
   document.getElementById("passwordError").style.display = "none";
   document.getElementById("emptyForm").style.display = "none";
}

