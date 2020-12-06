$(document).ready(function(){
    console.log("ok loaded")
});

$(function(){
    $('#botonLogIn').on('click',function(e){
    console.log("LogIn clicked"); //per fer proves
        //Cancel the default action (navigation) of the click.
        e.preventDefault();

    var username = document.getElementById("textName");
    var password = document.getElementById("textPassword");
    var emptyField = 0;

//If any field is empty, do a shake
    if(username.value.trim() == '' || password.value.trim() == '' ){
        if(username.value.trim() == '') {
            console.log("vacio 1"); //per fer proves
            emptyField = 1;
            $(".form-box1").effect("shake");
        }
        if(password.value.trim() == '') {
            emptyField = 1;
            $(".form-box2").effect("shake");
        }
    }
    if (emptyField == 0){
        var logInData = {
                "Username": username.value,
                "Password": password.value,
            }
    }

//Prova
    console.log(logInData);
    })
})