/*Use this function to let user know that
there is a missmatch in password input.We change
the type of the input element with id="pass2"
to 'text' and display an error message.*/
function CheckPassEquality(){
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
  /*var uluru = {lat: 35.321288, lng: 25.164185};
  map = new google.maps.Map(document.getElementById('map'), {
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
function createMap() {
    alert(geoAddress);
    geocoder = new google.maps.Geocoder();
    geocoder.geocode( { 'address': geoAddress}, function(results,status){
      if (status == 'OK'){
        alert('status OK');
        alert(results[0].formatted_address);
        console.log(results[0].formatted_address);
        map = new google.maps.Map(document.getElementById('map'), {
          zoom: 13,
          center: results[0].geometry.location
        });
        //map.setCenter(results[0].geometry.location);
        marker = new google.maps.Marker({
            map: map,
            position: results[0].geometry.location
        });
        console.log(marker.position);

        formCont = document.getElementById('formContainer');
        mapCont = document.getElementById('map');
        formCont.style.width = "60%";
        formCont.style.position = "absolute";
        formCont.style.left = "0";
        mapCont.style.width = "40%";
        mapCont.style.position = "absolute";
        mapCont.style.right = "0";
        mapCont.style.bottom = "100px";
        mapCont.style.margin = '5px 0';
        var oldButton = document.getElementById('mapButton');
        if(oldButton != null){
          oldButton.outerHTML = "";
          delete oldButton;
        }
      }else {
        alert('ton hpiame');
      }
    });
}
//make button.
function evalAddress() {
  var country = document.getElementById('country').value;
  var city = document.getElementById('city').value;
  var address = document.getElementById('address').value;
  if( city !="" ){
    geoAddress = address.concat("," ,city, "," , country);
    geocoder = new google.maps.Geocoder();
    geocoder.geocode( { 'address': geoAddress}, function(results,status){
      if (status == 'OK'){
        alert('STATUS OK');
        //remove old button before creating the new one.WORKS
        //also need to get rid of map in this case
        var oldButton = document.getElementById('mapButton');
        if(oldButton != null){
          oldButton.outerHTML = "";
          delete oldButton;
        }
        //delete map
        var oldMap = document.getElementById('map');
        console.log(oldMap);
        if(oldMap != null){
          document.getElementById('map').innerHTML = "";
        }
        //rearange form to 100% width if there is no camera showing
        oldCamera = document.getElementById('cameraCont');
        if(oldCamera === null){
          formCont = document.getElementById('formContainer');
          formCont.style.width = "100%";
        }
        //make new button
        mainDivElement = document.getElementById('mapBut');
        newButton = document.createElement('input');
        newButton.setAttribute('id', 'mapButton');
        newButton.setAttribute("type", "button");
        newButton.setAttribute('value', 'Show on Map');
        newButton.setAttribute('onclick', 'createMap()');
        newButton.style.position = "absolute";
        newButton.style.bottom = "367px";
        newButton.style.right = "245px";
        newButton.style.width = '15%';
        newButton.style.padding = '12px 20px';
        newButton.style.margin = '8px 0';
        newButton.style.outline = '0';
        newButton.style.border = '1px solid #ff6600';
        newButton.style.borderRadius = '7px';
        newButton.style.bozSizing = 'border-box';
        newButton.style.backgroundColor = '#ff6600';
        newButton.style.color = '#d9d9d9';
        newButton.style.fontSize = '19px';
        //fix button position if form is narrow
        formCont = document.getElementById('formContainer');
        if(formCont.style.width === "60%"){
          newButton.style.right = "100px";
          newButton.style.width = "20%"
        }
        mainDivElement.appendChild(newButton);
      }else {
        //give message that we cant find specified address
        alert('ton hpiame');
      }
    });
  }
}

function showRadio(){
  //na ta ksanadw.Otan exei xarth paei to koumpi pio aristera
  //get rid of previous 'show camera' button
  oldButton = document.getElementById('CameraButton');
  if(oldButton != null){
    document.getElementById('CameraButton').outerHTML = "";
  }
  //hide camera and buttons
  oldCamera = document.getElementById('cameraCont');
  oldSnap = document.getElementById('snap');
  oldUpload = document.getElementById('upload');
  oldCamera.style.display = "none";
  oldSnap.style.display = "none";
  oldUpload.style.display = "none";
  //if there is a map in the page dont mess with form.
  oldmap = document.getElementById('map');
  if(oldmap === null){
    formCont = document.getElementById('formContainer');
    formCont.style.width = "100%";
  }
  mainDivElement = document.getElementById('cameraBut');
  newButton = document.createElement('input');
  newButton.setAttribute('id', 'CameraButton');
  newButton.setAttribute('type', 'button');
  newButton.setAttribute('value', "Show Camera");
  newButton.setAttribute('checked', 'false');
  newButton.setAttribute('onclick' , 'showCamera()');
  newButton.style.position = "absolute";
  newButton.style.top = "19px";
  newButton.style.right = "245px";
  newButton.style.width = '15%';
  newButton.style.padding = '12px 20px';
  newButton.style.margin = '8px 0';
  newButton.style.outline = '0';
  newButton.style.border = '1px solid #ff6600';
  newButton.style.borderRadius = '7px';
  newButton.style.bozSizing = 'border-box';
  newButton.style.backgroundColor = '#ff6600';
  newButton.style.color = '#d9d9d9';
  newButton.style.fontSize = '19px';
  //fix button position if form is narrow
  formCont = document.getElementById('formContainer');
  if(formCont.style.width === "60%"){
    newButton.style.right = "100px";
    newButton.style.width = "20%"
  }
  mainDivElement.appendChild(newButton);
}

function showCamera(){
  //get rid of old 'show camera' button
  oldButton = document.getElementById('CameraButton');
  if(oldButton != null){
    document.getElementById('CameraButton').outerHTML = "";
  }
  //fix mapButton position
  formCont = document.getElementById('formContainer');
  formCont.style.width = "60%";
  mainDivElement = document.getElementById('cameraCont');
  oldSnap = document.getElementById('snap');
  oldUpload = document.getElementById('upload');
  mainDivElement.style.display = "inline-block";
  mainDivElement.style.width = "40%";
  mainDivElement.style.height = "440px";
  mainDivElement.style.position = "absolute";
  mainDivElement.style.right = "0";
  oldSnap.style.display = "inline-block";
  oldUpload.style.display = "inline-block";
  faceRec.init();
}
