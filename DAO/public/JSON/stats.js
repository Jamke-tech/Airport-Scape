$(document).ready(function(){

    var staticUrl = '/gameDSA/user/ranking';


    $.getJSON(staticUrl, function(data){
        $(data).each(function(index,value){
            var user = value.userName;
            var money = value.money;
            var gamesWon = 0;
            var staticUrl2 = "/gameDSA/game/getList/"+user;
            $.getJSON(staticUrl2, function(data){
                $(data).each(function(index,value){
                    var win = value.win;
                    if(win==true){
                        gamesWon = gamesWon+1;
                    }
                });
            })

            var concat ='<tbody><tr><th scope="row">'+(index+1)+"</th><td>"+user+"</td><td>"+money+"</td><td>"+gamesWon+"</td></tr></tbody>";
            $("table").append(concat);

           /*var graphs = [
                {
                      x: ['user'],
                      y: [value.money],
                      type: 'bar'
                }
            ];
            Plotly.newPlot('plotlyChart', data);*/
        });
    })
});

