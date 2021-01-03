$(document).ready(function(){
    $(function(){
        $('#buttonInicio').on('click',function(e){
            console.log("Go to Inicio"); //per fer proves
                    //Cancel the default action (navigation) of the click.
                    e.preventDefault();
            var id = window.location.hash.substring(1);
            var username = sessionStorage.getItem(id);
            window.location.href = 'inicio.html' + '#' + id;
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
            window.location.href = 'index.html' + '#' + id;
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
            window.location.href = 'estadisticas.html' + '#' + id;
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
            window.location.href = 'shop.html' + '#' + id;
            return false;

        })
    });
});