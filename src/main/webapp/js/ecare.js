/**
 * Created by Kolia on 06.07.2015.
 */

function get_contracts() {
    var username=$('#user').text();
    $.get('client_lobby',{user:username, action:"get_contracts"},function(responseText) {
        $('#contracts').html(responseText);
    });
}

function get_current_contract() {
    var username=$('#user').text();

    $.get('client_lobby',{user:username, action:"get_current_contract"},function(responseText) {
        $('#current_contract').text(responseText);
    });
}

function get_options() {
    var username=$('#user').text();

    $.get('client_lobby',{user:username, action:"get_options"},function(responseText) {
        $('#options').html(responseText);
    });
}

function get_tariffs() {
    var username=$('#user').text();

    $.get('client_lobby',{user:username, action:"get_tariffs"},function(responseText) {
        $('#tariffs').html(responseText);
    });
}