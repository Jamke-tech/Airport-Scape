$(document).ready(function(){
    console.log("ok loaded")
});

$(function(){
    $('#botonBuy1').on('click',function(e){
    console.log("Buy1 clicked"); //per fer proves
            //Cancel the default action (navigation) of the click.
            e.preventDefault();

    var idObject = 1;
    var username = document.getElementById("userName")
    var purchaseData = {
                    "idObject": 1,
                    "userName": username.value,
                }

       $.ajax({
               type: "PUT",
               url: "/gameDSA/object/buy/{userName}/{idObject}", //A definir cuando se haga bien el servicio
               data: JSON.stringify(logInData),
               contentType: "application/json; charset=utf-8",
               dataType: "",
               success: function(data) {
                   console.log("success");
                   console.log(data.status)
               },
               complete: function(data) {
                   if(data.status == 406){
                      alert("You don't have enough money");
                   }
                   else if (data.status != 200){
                       alert("Good purchase!");
                   }
                   else if (data.status != 503){
                       alert("Database down!");
                   }
               }
       })
    }

    })
})