$(document).ready(function(){
    console.log("ok loaded")
});

$(function(){
    $('#buttonBuy1').on('click',function(e){
    console.log("Buy1 clicked"); //per fer proves
            //Cancel the default action (navigation) of the click.
            e.preventDefault();

    var username = document.getElementById("userName")

       $.ajax({
               type: "PUT",
               url: "/gameDSA/object/buy/{userName}/1", //A definir cuando se haga bien el servicio
               data: {username.value : "userName"}
               success: function(data) {
                   console.log("success");
                   console.log(data.status)
               },
               complete: function(data) {
                   if(data.status == 406){
                      alert("You don't have enough money!");
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

$(document).ready(function(){
    console.log("ok loaded")
});

$(function(){
    $('#buttonBuy2').on('click',function(e){
    console.log("Buy1 clicked"); //per fer proves
            //Cancel the default action (navigation) of the click.
            e.preventDefault();

    var username = document.getElementById("userName")

       $.ajax({
               type: "PUT",
               url: "/gameDSA/object/buy/{userName}/2", //A definir cuando se haga bien el servicio
               data: { username.value : "userName" }
               success: function(data) {
                   console.log("success");
                   console.log(data.status)
               },
               complete: function(data) {
                   if(data.status == 406){
                      alert("You don't have enough money!");
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