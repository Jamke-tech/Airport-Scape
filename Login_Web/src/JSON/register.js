$(document).ready(function(){
    console.log("ok loaded")
});

$(function(){
    $('#botonCrear').on('click',function(e){
    console.log("clickado"); //per fer proves
        //Cancel the default action (navigation) of the click.
        e.preventDefault();

    var name = document.getElementById("textName");
    var surname = document.getElementById("textSurname");
    var username = document.getElementById("textUsername");
    var password = document.getElementById("textPassword");
    var email = document.getElementById("textEmail");
    var emptyField = 0;
//If any field is empty, do a shake
    if(name.value.trim() == '' || surname.value.trim() == '' || username.value.trim() == '' || password.value.trim() == '' || email.value.trim() == ''){
        if(name.value.trim() == '') {
            console.log("vacio 1"); //per fer proves
            emptyField = 1;
            $(".form-box1").effect("shake");
        }
        if(surname.value.trim() == '') {
            emptyField = 1;
            $(".form-box2").effect("shake");
        }
        if(username.value.trim() == '') {
            emptyField = 1;
            $(".form-box3").effect("shake");
        }
        if(password.value.trim() == '') {
            emptyField = 1;
            $(".form-box4").effect("shake");
        }
        if(email.value.trim() == '') {
            emptyField = 1;
            $(".form-box5").effect("shake");
        }
    }

    if (emptyField == 0){
        var UserData = {
                "Name": name.value,
                "Surname": surname.value,
                "Username": username.value,
                "Password": password.value,
                "Email": email.value
            }
    }


//Prova
    console.log(UserData);
 /*
    $.ajax({
        type: "POST",
        url: //"http://localhost:8080...."
        data: JSON.stringify(UserData),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        .
        .
        .
    })
    */
    })

})