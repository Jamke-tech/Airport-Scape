$(document).ready(function(){
    console.log("ok loaded")
});

$(function(){
    var i = 1;
    for (i = 1; i = 10 ; i++){
        $('#buttonBuy'+ i).on('click',function(e){
        console.log("Buy clicked"); //per fer proves
                //Cancel the default action (navigation) of the click.
                e.preventDefault();
        var id = window.location.hash.substring(1);
        var userName = sessionStorage.getItem(id);


           $.ajax({
                   type: "PUT",
                   url: "/gameDSA/object/buy/{userName}/{i}", //A definir cuando se haga bien el servicio
                   data: {username.value : "userName", i:"i"}
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
}
})

$(document).ready(function(){
    $(function(){
        $('#buttonInicio').on('click',function(e){
            console.log("Go to Inicio"); //per fer proves
                    //Cancel the default action (navigation) of the click.
                    e.preventDefault();
            var id = window.location.hash.substring(1);
            var username = sessionStorage.getItem(id);
            document.location.href = 'inicio.html' + '#' + id;
            return false;
        })
    });
});

$(document).ready(function(){
    $(function(){
        $('#buttonLogIn').on('click',function(e){
            console.log("Go to LogIn");

            e.preventDefault();
            var id = window.location.hash.substring(1);
            if (id = null){
                id = 0;
            }
            var username = sessionStorage.getItem(id);
            document.location.href = 'index.html' + '#' + id;
            return false;

        })
    });
});

$(document).ready(function(){
    $(function(){
        $('#buttonStatics').on('click',function(e){
            console.log("Go to statistics");
                    e.preventDefault();
            var id = window.location.hash.substring(1);
            var username = sessionStorage.getItem(id);
            document.location.href = 'estadisticas.html' + '#' + id;
            return false;
        })
    });
});

$(document).ready(function(){
    $(function(){
        $('#buttonShop').on('click',function(e){
            console.log("Go to shop"); //per fer proves
                    //Cancel the default action (navigation) of the click.
                    e.preventDefault();
            var id = window.location.hash.substring(1);
            var username = sessionStorage.getItem(id);
            document.location.href = 'shop.html' + '#' + id;
            return false;

        })
    });
});
