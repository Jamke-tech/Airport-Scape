$(document).ready(function(){

    var userName = document.getElementById("userNameNav")
    var userUrl = '/gameDSA/user/{user}';


    $.getJSON(userUrl, function(data){
        $(data).each(function(index,value){
            var user = value.userName;
            var name = value.name;
            var surname = value.surname;
            var mail = value.email
            var imageUrl = value.url
            $("#userName").text(user);
            $("#name").text("Name:" + name);
            $("#surname").text("Surname:" + surname);
            $("#userEmail").text("Email address:" + mail);
            $("#userImage").attr({'src': imageUrl,'alt':''});