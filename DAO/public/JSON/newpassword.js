$(document).ready(function(){
    console.log("ok loaded");

});
$(function(){
    var params = window.location.search;
    console.log(params);
    const urlParams = new URLSearchParams(params);
    const user = urlParams.get('user');
    console.log(user);
    var urlget = "http://eetacdsa0.upc.es:8080/gameDSA/user/" + user;
    console.log(urlget);
    $.get(urlget, function(data, status){

        $('#boton').on('click',function(e){
        console.log("Button clicked"); //per fer proves
            //Cancel the default action (navigation) of the click.
            e.preventDefault();

        var password1 = document.getElementById("textPassword");
        var password2 = document.getElementById("checkPassword");
        var emptyField = 0;

    //If any field is empty, do a shake
        if(password1.value.trim() == '' || password2.value.trim() == '' ){
            if(password1.value.trim() == '') {
                console.log("vacio 1"); //per fer proves
                emptyField = 1;
                $(".form-box1").effect("shake");
            }
            if(password2.value.trim() == '') {
                emptyField = 1;
                $(".form-box2").effect("shake");
            }
        }
        if (emptyField == 0){
            if (password1.value==(password2.value)){
                var updatedUser = { "id": data.id, "userName": data.userName, "password": password1.value, "name": data.name, "surname": data.surname, "money": data.money, "mail": data.mail};
                //Prova
                console.log(updatedUser);

                $.ajax({
                        type: "PUT",
                        url: "/gameDSA/user/edit",
                        data: JSON.stringify(updatedUser),
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        success: function(data) {
                            console.log("success");
                            console.log(data.status);

                            /*sessionStorage.setItem(data.id, data.userName);
                            var  userNameStored = sessionStorage.getItem(data.id);
                            document.getElementById("navbarDropdown").innerHTML = userNameStored;*/
                        },
                        complete: function(data) {
                            if(data.status == 400){
                                alert("There's a problem with the data");
                            }
                            else if (data.status == 503){
                                alert("User not registered");
                            }
                            else if (data.status == 200){
                                alert("Password changed successfully!");
                            }
                            else if (data.status != 200){
                                alert("CONNECTION ERROR ");
                            }
                        }
                    })
                }
                else{
                    alert("Password missmatch!");
                }
            }
        })
    })
})