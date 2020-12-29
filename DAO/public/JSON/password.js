$(document).ready(function(){
    console.log("ok loaded")
});

$(function(){
    $('#sendemail').on('click',function(e){
    console.log("button clicked"); //per fer proves
        //Cancel the default action (navigation) of the click.
        e.preventDefault();

    var email = document.getElementById("mail");
    var emptyField = 0;

//If any field is empty, do a shake
    if(email.value.trim() == ''){
        console.log("vacio 1"); //per fer proves
        emptyField = 1;
        $(".form-box").effect("shake");
    }
    if (emptyField == 0){
        var email = {
                "mail": email.value
            }
        //Prova
        console.log(email);

        $.ajax({
                type: "POST",
                url: "https://localhost:8080/gameDSA/user/changepass", //A definir cuando se haga bien el servicio
                data: JSON.stringify(email),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function(data) {
                    console.log("success");
                    console.log(data.status);
                    alert("Check your email account!")
                },
                complete: function(data) {
                    if(data.status == 400){
                        alert("Wrong mail");
                    }
                    else if (data.status != 200){
                        alert("CONNECTION ERROR ");
                    }
                }
            })
    }

    })
})