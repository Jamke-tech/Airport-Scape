$(document).ready(function(){

    var staticUrl = '/gameDSA/user/ranking';


    $.getJSON(staticUrl, function(data){
        $(data).each(function(index,value){
            var user = value.userName;
            var money = value.money;
            var gamesWon = value.wins;

            var concat ='<tbody><tr><th scope="row">'+(index+1)+"</th><td>"+user+"</td><td>"+money+"</td><td>"+gamesWon+"</td></tr></tbody>";
            $("table").append(concat);

        });
    })
});

