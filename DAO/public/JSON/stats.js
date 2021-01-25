$(document).ready(function(){

    var staticUrl = '/gameDSA/user/ranking';

    $.getJSON(staticUrl, function(data){
        $(data).each(function(index,value){
            var user = value.userName;
            var concat ='<tbody><tr><th scope="row">'+(index+1)+"</th><td>"+user+"</td><td>"+value.money+"</td><td>"+"..."+"</td></tr></tbody>";
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

