/*Use this function to let user know that
there is a missmatch in password input.We change
the type of the input element with id="pass2"
to 'text' and display an error message.*/
function CheckPassEquality(){
  'use strict';
  var pass1 = document.getElementById('pass1').value;
  var pass2 = document.getElementById('pass2').value;
  if(pass1 != pass2){
    document.getElementById('pass2').title = "Password Missmatch";
    var message = document.getElementById('pass2');
    document.getElementById('pass2').style.color = "#cc0000";
    message.type = "text";
    message.value = "Password Missmatch";
  }
}
/*This function resets the type of input element with id="pass2"
to 'password'.*/
function ResetPassword(){
  'use strict'
  var passVal1 = document.getElementById('pass1').value;
  var passVal2 = document.getElementById('pass2').value;
  if(passVal1 != passVal2){
    var pass = document.getElementById('pass2');
    pass.type = "password";
    pass.value = "";
    pass.style.color = "#595959";
  }
}

var map;
var geocoder;
/*Google function to initialize our map.Change it so that it
only shows map when user presses button*/
function initMap() {
  var uluru = {lat: 35.321288, lng: 25.164185};
  map = new google.maps.Map(document.getElementById('map'), {
    zoom: 6,
    center: uluru
  });
  var marker = new google.maps.Marker({
    position: uluru,
    map: map
  });
}



//
function evalAddress() {
  var country = document.getElementById('country').value;
  var city = document.getElementById('city').value;
  var address = document.getElementById('address').value;
  var geoAddress;
  if( (city !="") && (address !="") ){
    geoAddress = address.concat("," ,city, "," , country);
    alert(geoAddress);
    geocoder = new google.maps.Geocoder();
    geocoder.geocode( { 'address': geoAddress}, function(results,status){
      if (status == 'OK'){
        alert('status OK');
        alert(results[0].formatted_address);
        map.setCenter(results[0].geometry.location);
        var marker = new google.maps.Marker({
            map: map,
            position: results[0].geometry.location
        });
      }else {
        alert('ton hpiame');
      }
    });
  }
  //alert('valid address');
}
