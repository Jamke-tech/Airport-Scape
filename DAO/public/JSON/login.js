$(document).ready(function(){
    console.log("ok loaded")
    localStorage = window.localStorage;
});
//prova commit
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
                "userName": username.value,
                "password": password.value,
            }
        //Prova
        console.log(logInData);

        $.ajax({
                type: "POST",
                url: "/gameDSA/user/login", //A definir cuando se haga bien el servicio
                data: JSON.stringify(logInData),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function(data) {
                    console.log("success");
                    console.log(data.status);

                    localStorage.setItem("user",data.username);
                    //var  userNameStored = sessionStorage.getItem("user");
                    //document.getElementById("navbarDropdown").innerHTML = data.userName;
                    window.location.pathname = "/inicio.html";

                },
                complete: function(data) {
                    if(data.status == 401){
                        alert("Wrong password or username");

                    }
                    else if (data.status == 404){
                        alert("User not registered");
                    }
                    else if (data.status != 200){
                        alert("CONNECTION ERROR ");
                    }
                }
            })
    }

    })
})

