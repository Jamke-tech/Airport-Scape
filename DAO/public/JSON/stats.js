$(document).ready(function(){

    var staticUrl = '/gameDSA/user/ranking';

    $.getJSON(staticUrl, function(data){
        $(data).each(function(index,value){
            var user = value.userName;
            var concat ='<tr class="user"><td>'+(index+1)+"</td><td>"+user+"</td><td>"+value.money+"</td><td>"+"..."+"</td></tr>";
            $("table").append(concat);
        });
    })
});