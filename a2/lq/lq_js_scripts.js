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
var geoAddress;
var marker;
/*Google function to initialize our map.Change it so that it
only shows map when user presses button*/
function initMap() {
  var uluru = {lat: 35.321288, lng: 25.164185};
  /*map = new google.maps.Map(document.getElementById('map'), {
    zoom: 6,
    center: uluru
  });
  marker = new google.maps.Marker({
    position: uluru,
    map: map
  });*/
}


//na dinei to koumpi.Sto koumpi onclick() na emfanizetai o xarths
//RENAME TO BE THE FUNCTION TO MAKE THE MAP APPEAR WITH THE MARKER
function evalAddress() {
  var country = document.getElementById('country').value;
  var city = document.getElementById('city').value;
  var address = document.getElementById('address').value;
  if( (city !="") && (address !="") ){
    geoAddress = address.concat("," ,city, "," , country);
    alert(geoAddress);
    geocoder = new google.maps.Geocoder();
    geocoder.geocode( { 'address': geoAddress}, function(results,status){
      if (status == 'OK'){
        alert('status OK');
        alert(results[0].formatted_address);
        map = new google.maps.Map(document.getElementById('map'), {
          zoom: 6,
          center: results[0].geometry.location
        });
        marker = new google.maps.Marker({
            map: map,
            position: results[0].geometry.location
        });
      }else {
        alert('ton hpiame');
      }
    });
  }
}
//make button.RENAME TO BE THE NEW EVALADDRESS
function testObjectGeocoder() {
  var country = document.getElementById('country').value;
  var city = document.getElementById('city').value;
  var address = document.getElementById('address').value;
  if( (city !="") && (address !="") ){
    geoAddress = address.concat("," ,city, "," , country);
    geocoder = new google.maps.Geocoder();
    geocoder.geocode( { 'address': geoAddress}, function(results,status){
      if (status == 'OK'){
        alert('STATUS OK');
        center = results[0].geometry.location;
        marker =
        //make new button
        mainDivElement = document.getElementById('container');
        newButton = document.createElement('input');
        newButton.setAttribute('id', 'mapButton');
        newButton.setAttribute("type", "button");
        newButton.setAttribute('value', 'Show on Map');
        newButton.style.display = 'inline-block';
        newButton.style.width = '15%';
        newButton.style.padding = '12px 20px';
        newButton.style.border = '1px solid #ff6600';
        newButton.style.borderRadius = '7px';
        newButton.style.backgroundColor = '#ff6600';
        newButton.style.color = '#d9d9d9';
        newButton.style.fontSize = '19px';
        mainDivElement.appendChild(newButton);
        //instead of evalAddress, make new function to create map
        //and point to geocoder location
        newButton.setAttribute('onclick', 'evalAddress()');
      }else {
        //give message that we cant find specified address
        alert('ton hpiame');
      }
    });
  }
}
