/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function showRegister() {
    document.getElementById('loginBut').style.display = 'none';
    document.getElementById('register').style.display = 'none';
    document.getElementById('modify').style.display = 'none';
    document.getElementById('initiatives').style.display = 'none';
    document.getElementById('formContainer').style.display = 'block';
    document.getElementById('loginForm').style.display = 'none';
    document.getElementById('myform').style.display = 'block';
}

function showLogin() {
    document.getElementById('loginBut').style.display = 'none';
    document.getElementById('register').style.display = 'none';
    document.getElementById('modify').style.display = 'none';
    document.getElementById('initiatives').style.display = 'none';
    document.getElementById('formContainer').style.display = 'block';
    document.getElementById('myform').style.display = 'none';
    document.getElementById('loginForm').style.display = 'block';
}

/*Use this function to let user know that
 there is a missmatch in password input.We change
 the type of the input element with id="pass2"
 to 'text' and display an error message.*/
function CheckPassEquality() {
    var pass1 = document.getElementById('pass1').value;
    var pass2 = document.getElementById('pass2').value;

    if (pass1 !== pass2) {
        document.getElementById('pass2').title = "Password Missmatch";
        var message = document.getElementById('pass2');
        document.getElementById('pass2').style.color = "#cc0000";
        message.type = "text";
        message.value = "Password Missmatch";
    }
    checkPassword();
}

/*This function resets the type of input element with id="pass2"
 to 'password'.*/
function ResetPassword() {
    var passVal1 = document.getElementById('pass1').value;
    var passVal2 = document.getElementById('pass2').value;
    if (passVal1 !== passVal2) {
        var pass = document.getElementById('pass2');
        pass.type = 'password';
        pass.value = '';
        pass.style.color = '#595959';
    }
}

function resetField(id) {
    var field = document.getElementById(id);
    field.value = '';
}

var map;
var geocoder;
var geoAddress;
var marker;
/*Google function to initialize our map*/
function initMap() {
    /*Only exists so that we can use the Google APIs script*/
}

/*Create and initialise Google Maps with a marker, both centered
 to the address provided by the user*/
function createMap() {
    geocoder = new google.maps.Geocoder();
    geocoder.geocode({'address': geoAddress}, function (results, status) {
        if (status === 'OK') {
            console.log(results[0].formatted_address);
            map = new google.maps.Map(document.getElementById('map'), {
                zoom: 13,
                center: results[0].geometry.location
            });
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
            if (oldButton !== null) {
                oldButton.outerHTML = "";
                delete oldButton;
            }
        } else {
            alert('Address not found');
        }

    });
}

/*Evaluate the validity of the address provided by the user
 and provide a button that enables Google Maps on our page*/
function evalAddress() {
    var country = document.getElementById('country').value;
    var city = document.getElementById('city').value;
    var address = document.getElementById('address').value;
    if (city !== "") {
        geoAddress = address.concat(",", city, ",", country);
        geocoder = new google.maps.Geocoder();
        geocoder.geocode({'address': geoAddress}, function (results, status) {
            if (status === 'OK') {
                /*remove old button before creating the new one*/
                var oldButton = document.getElementById('mapButton');
                if (oldButton !== null) {
                    oldButton.outerHTML = "";
                    delete oldButton;
                }
                /*delete map*/
                var oldMap = document.getElementById('map');
                console.log(oldMap);
                if (oldMap !== null) {
                    document.getElementById('map').innerHTML = "";
                }
                /*rearange form to 100% width if there is no camera showing*/
                oldCamera = document.getElementById('cameraCont');
                if (oldCamera === null) {
                    formCont = document.getElementById('formContainer');
                    formCont.style.width = "100%";
                }
                /*make new button*/
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
                /*fix button position if form is narrow*/
                formCont = document.getElementById('formContainer');
                if (formCont.style.width === "60%") {
                    newButton.style.right = "100px";
                    newButton.style.width = "20%";
                }
                mainDivElement.appendChild(newButton);
            } else {
                /*give message that we cant find specified address*/
                alert('Address not found');
            }
        });
        checkRestMandatory();
    }
}

function showRadio() {
    /*get rid of previous 'show camera' button*/
    oldButton = document.getElementById('CameraButton');
    if (oldButton !== null) {
        document.getElementById('CameraButton').outerHTML = "";
    }
    /*hide camera and buttons*/
    oldCamera = document.getElementById('cameraCont');
    oldSnap = document.getElementById('snap');
    oldUpload = document.getElementById('upload');
    oldCamera.style.display = "none";
    oldSnap.style.display = "none";
    oldUpload.style.display = "none";
    /*if there is a map in the page dont mess with form.*/
    oldmap = document.getElementById('map');
    if (oldmap === null) {
        formCont = document.getElementById('formContainer');
        formCont.style.width = "100%";
    }
    mainDivElement = document.getElementById('cameraBut');
    newButton = document.createElement('input');
    newButton.setAttribute('id', 'CameraButton');
    newButton.setAttribute('type', 'button');
    newButton.setAttribute('value', "Show Camera");
    newButton.setAttribute('checked', 'false');
    newButton.setAttribute('onclick', 'showCamera()');
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
    /*fix button position if form is narrow*/

    formCont = document.getElementById('formContainer');
    if (formCont.style.width === "60%") {
        newButton.style.right = "100px";
        newButton.style.width = "20%";
    }
    mainDivElement.appendChild(newButton);

    checkUsername();
}

function showCamera() {
    /*get rid of old 'show camera' button*/
    oldButton = document.getElementById('CameraButton');
    if (oldButton !== null) {
        document.getElementById('CameraButton').outerHTML = "";
    }
    /*fix mapButton position*/
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

/*Swotch between visible/non-visible camera*/
function toggleCamera() {
    mainDivElement = document.getElementById('loginCameraCont');
    if (mainDivElement.style.display === "none") {
        mainDivElement.style.display = "block";
        moveCanvas();
    } else {
        mainDivElement.style.display = "none";
    }
}

/*Set unique style properties for 'canvas'*/
function moveCanvas() {
    mainDivElement = document.getElementById('canvas');
    mainDivElement.style.position = "absolute";
    mainDivElement.style.top = "20px";
    mainDivElement.style.left = "500px";
    mainDivElement.style.height = "350px";
    mainDivElement.style.width = "450px";
}

//These globals indicate if the values inside the
//corresponding DOM elements are of valid state in
//order to pass them to register() or updateUSER()
var USERNAME = false;
var EMAIL = false;
var PASS1 = false;
var PASS2 = false;
var NAME = false;
var LASTNAME = false;
var CITY = false;
var PROFESSION = false;
//check if the action is just an update of user's fields
//in checkUsername(), checkEmail().
var isModify = false;
var activeUser = "";
//do the same in checkEmail as in checkUsername
var activeEmail = "";

function getCookie(cname) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ')
            c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0)
            return c.substring(nameEQ.length, c.length);
    }
    return null;
}

function checkUsername() {
    var username = document.getElementById('username').value;
    activeUser = getCookie(username);
    console.log(activeUser);
    //check isModify(user updates his/her information)
    //check if the input from the form matches the user that is already logged in
    //if so, no need to checkUsername() because it is already valid for this user.
    if (isModify && activeUser === username) {
        return;
    }

    USERNAME = false;

    var req = new XMLHttpRequest();

    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            console.log(req.status);
            console.log(req.readyState);
            document.getElementById('username').value = req.responseText;
            USERNAME = true;
        } else if (req.readyState === 4 && req.status !== 200) {
            console.log('Request failed. Returned status of ' + req.status + ': ' + req.responseText);
            console.log(req.readyState);
            document.getElementById('username').value = req.responseText;
        }
    };
    req.open('POST', 'usernameCheck');
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.send('username=' + username);
}

function checkPassword() {
    var pass1 = document.getElementById('pass1').value;
    var pass2 = document.getElementById('pass2').value;

    PASS1 = false;
    PASS2 = false;

    var req = new XMLHttpRequest();


    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            console.log(req.status);
            console.log(req.readyState);
            //write first parameter of response to pass1, second to pass2
            //console.log(req.getResponseHeader("value1"));
            //console.log(req.getResponseHeader("value2"));
            document.getElementById("pass1").value = req.getResponseHeader("value1");
            if (req.getResponseHeader("value1") !== "") {
                document.getElementById("pass2").value = req.getResponseHeader("value2");
                PASS1 = true;
                PASS2 = true;
            }
        } else if (req.readyState === 4 && req.status !== 200) {
            console.log('Request failed. Returned status of ' + req.status + req.responseText);
            console.log(req.readyState);
            document.getElementById('pass1').value = '';
            document.getElementById('pass2').value = '';
        }
    };
    req.open('POST', 'passwordCheck', true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.send('pass1=' + pass1 + '&pass2=' + pass2);
}

function checkEmail() {
    var email = document.getElementById('email').value;
    activeEmail = getCookie(email);

    if (isModify && activeEmail === email) {
        return;
    }

    var req = new XMLHttpRequest();

    EMAIL = false;

    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            console.log(req.status);
            console.log(req.readyState);
            console.log(req.responseText);
            document.getElementById("email").value = req.responseText;
            EMAIL = true;
        } else if (req.readyState === 4 && req.status !== 200) {
            console.log(req.status);
            console.log(req.readyState);
            console.log(req.responseText);
            document.getElementById("email").value = "Invalid Email Addres";
        }
    };
    req.open('POST', 'emailCheck', true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.send('email=' + email);
}

function checkRestMandatory() {
    var name = document.getElementById('name').value;
    var lastName = document.getElementById('lastName').value;
    var city = document.getElementById('city').value;
    var profession = document.getElementById('profession').value;

    NAME = false;
    LASTNAME = false;
    CITY = false;
    PROFESSION = false;

    var req = new XMLHttpRequest();
    //so that we don't put too much overhead to the network
    if (name !== '' && lastName !== '' && city !== '' && profession !== '') {
        req.onreadystatechange = function () {
            if (req.readyState === 4 && req.status === 200) {
                console.log(req.status);
                console.log(req.readyState);
                NAME = true;
                LASTNAME = true;
                CITY = true;
                PROFESSION = true;
            } else if (req.readyState === 4 && req.status !== 200) {
                console.log(req.status);
                console.log(req.readyState);
                document.getElementById('name').value = req.getResponseHeader('name');
                document.getElementById('lastName').value = req.getResponseHeader('lastName');
                document.getElementById('city').value = req.getResponseHeader('city');
                document.getElementById('profession').value = req.getResponseHeader('profession');
            }
        };
        req.open('POST', 'checkRestMandatory', true);
        req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        req.send('name=' + name + '&lastName=' + lastName + '&city=' + city + '&profession=' + profession);
    }
}

function register() {
    if (USERNAME && EMAIL && PASS1 && PASS2 && NAME && LASTNAME && CITY && PROFESSION) {
        var username = document.getElementById('username').value;
        var password = document.getElementById('pass1').value;
        var email = document.getElementById('email').value;
        var name = document.getElementById('name').value;
        var lastName = document.getElementById('lastName').value;
        var city = document.getElementById('city').value;
        var profession = document.getElementById('profession').value;
        var day = document.getElementById('day').value;
        var month = document.getElementById('month').name;
        var year = document.getElementById('year').value;
        var gender = document.querySelector('input[name="gender"]:checked').value;
        var country = document.getElementById('country').value;
        var address = document.getElementById('address').value;
        var interests = document.getElementById('interests').value;
        var genInfo = document.getElementById('genInfo').value;

        var req = new XMLHttpRequest();

        req.onreadystatechange = function () {
            if (req.readyState === 4 && req.status === 200) {
                console.log(req.status);
                console.log(req.readyState);
                console.log(req.getResponseHeader("result"));
                activeUser = req.getResponseHeader("username");
                activeEmail = req.getResponseHeader("email");
                //new
                document.cookie = 'username=' + activeUser;
                document.cookie = 'email=' + activeEmail;
                console.log(activeUser);
                console.log(activeEmail);
                console.log(document.cookie);
                hideForm();
                displayUI(req);
            } else if (req.readyState === 4 && req.status !== 200) {
                console.log(req.status);
                console.log(req.readyState);
                console.log(req.responseText);
                alert('Error in registration');
            }
        };
        req.open('POST', 'register', true);
        req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        req.send('username=' + username + '&password=' + password + '&email=' + email + '&name=' + name + '&lastName=' + lastName + '&city=' + city + '&profession=' + profession + '&day=' + day + '&month=' + month + '&year=' + year + '&gender=' + gender + '&country=' + country + '&address=' + address + '&interests=' + interests + '&genInfo=' + genInfo);
    }
}

function hideForm() {
    document.getElementById('myform').style.display = 'none';
    document.getElementById('map').style.display = 'none';
    document.getElementById('cameraCont').style.display = 'none';
}

function hideField(id) {
    var field = document.getElementById(id);
    field.style.display = 'none';
}

function displayUI(req) {
    //display registration success msg
    container = document.getElementById('container');
    msgContainer = document.createElement('div');
    msgContainer.setAttribute('id', 'msgContainer');
    msgContainer.style.display = 'block';
    container.appendChild(msgContainer);
    confirmMsg = document.createElement('P');
    confirmMsg.setAttribute('id', 'confMsg');
    text = document.createTextNode(req.responseText);
    confirmMsg.appendChild(text);
    okButton = document.createElement('button');
    okButton.setAttribute('id', 'okButton');
    okButton.setAttribute('onclick', 'hideField("msgContainer")');
    butMsg = document.createTextNode('OK');
    okButton.appendChild(butMsg);
    msgContainer.appendChild(confirmMsg);
    msgContainer.appendChild(okButton);

    //display user info
    infoTable = document.createElement('table');
    infoTable.setAttribute('id', 'infoTable');
    container.appendChild(infoTable);

    title = document.createElement('tr');
    infoTable.appendChild(title);
    titleT = document.createElement('th');
    titleV = document.createTextNode("User Info");
    titleT.appendChild(titleV);
    title.appendChild(titleT);

    username = document.createElement('tr');
    infoTable.appendChild(username);
    usernameT = document.createElement('td');
    usernameV = document.createTextNode(req.getResponseHeader("username"));
    usernameT.appendChild(usernameV);
    username.appendChild(usernameT);

    email = document.createElement('tr');
    infoTable.appendChild(email);
    emailT = document.createElement('td');
    emailV = document.createTextNode(req.getResponseHeader("email"));
    emailT.appendChild(emailV);
    email.appendChild(emailT);

    hisname = document.createElement('tr');
    infoTable.appendChild(hisname);
    hisnameT = document.createElement('td');
    hisnameV = document.createTextNode(req.getResponseHeader("name"));
    hisnameT.appendChild(hisnameV);
    hisname.appendChild(hisnameT);

    lastname = document.createElement('tr');
    infoTable.appendChild(lastname);
    lastnameT = document.createElement('td');
    lastnameV = document.createTextNode(req.getResponseHeader("lastname"));
    lastnameT.appendChild(lastnameV);
    lastname.appendChild(lastnameT);

    birthdate = document.createElement('tr');
    infoTable.appendChild(birthdate);
    birthdateT = document.createElement('td');
    birthdateV = document.createTextNode(req.getResponseHeader("birthdate"));
    birthdateT.appendChild(birthdateV);
    birthdate.appendChild(birthdateT);

    country = document.createElement('tr');
    infoTable.appendChild(country);
    countryT = document.createElement('td');
    countryV = document.createTextNode(req.getResponseHeader("country"));
    countryT.appendChild(countryV);
    country.appendChild(countryT);

    city = document.createElement('tr');
    infoTable.appendChild(city);
    cityT = document.createElement('td');
    cityV = document.createTextNode(req.getResponseHeader("city"));
    cityT.appendChild(cityV);
    city.appendChild(cityT);

    address = document.createElement('tr');
    infoTable.appendChild(address);
    addressT = document.createElement('td');
    addressV = document.createTextNode(req.getResponseHeader("address"));
    addressT.appendChild(addressV);
    address.appendChild(addressT);

    profession = document.createElement('tr');
    infoTable.appendChild(profession);
    professionT = document.createElement('td');
    professionV = document.createTextNode(req.getResponseHeader("profession"));
    professionT.appendChild(professionV);
    profession.appendChild(professionT);

    gender = document.createElement('tr');
    infoTable.appendChild(gender);
    genderT = document.createElement('td');
    genderV = document.createTextNode(req.getResponseHeader("gender"));
    genderT.appendChild(genderV);
    gender.appendChild(genderT);

    if (req.getResponseHeader("genInfo") !== "") {
        interests = document.createElement('tr');
        infoTable.appendChild(interests);
        interestsT = document.createElement('td');
        interestsV = document.createTextNode(req.getResponseHeader("interests"));
        interestsT.appendChild(interestsV);
        interests.appendChild(interestsT);
    }

    if (req.getResponseHeader("genInfo") !== "") {
        genInfo = document.createElement('tr');
        infoTable.appendChild(genInfo);
        genInfoT = document.createElement('td');
        genInfoV = document.createTextNode(req.getResponseHeader("genInfo"));
        genInfoT.appendChild(genInfoV);
        genInfo.appendChild(genInfoT);
    }

    document.getElementById('logout').style.display = 'block';
    document.getElementById('modify').style.display = 'block';
    document.getElementById('initiatives').style.display = 'block';
    document.getElementById('showAllUsers').style.display = 'block';
    document.getElementById('loginForm').style.display = 'none';
    document.getElementById('register').style.display = 'none';
    //set visible ta koumpakia ths arxikhs selidas
}

function hideUI() {
    //teerminate these two
    document.getElementById('msgContainer').outerHTML = "";
    document.getElementById('infoTable').outerHTML = "";
    //hide these four
    document.getElementById('modify').style.display = 'none';
    document.getElementById('initiatives').style.display = 'none';
    document.getElementById('logout').style.display = 'none';
    document.getElementById('showAllUsers').style.display = 'none';
    document.getElementById('register').style.display = 'block';
    document.getElementById('loginBut').style.display = 'block';
    userlist = document.getElementById('userlist');
    if (userlist !== null) {
        userlist.outerHTML = '';
    }
}

function login() {
    var username = document.getElementById('loginUsername').value;
    var password = document.getElementById('loginPass').value;

    if (username !== "" && password !== "") {
        var req = new XMLHttpRequest();

        req.onreadystatechange = function () {
            if (req.readyState === 4 && req.status === 200) {
                console.log(req.status);
                console.log(req.readyState);
                console.log(req.getResponseHeader("result"));
                activeUser = req.getResponseHeader("username");
                activeEmail = req.getResponseHeader("email");
                //new
                document.cookie = 'username=' + activeUser;
                document.cookie = 'email=' + activeEmail;
                console.log(activeUser);
                console.log(activeEmail);
                console.log(document.cookie);
                displayUI(req);
            } else if (req.readyState === 4 && req.status !== 200) {
                console.log(req.status);
                console.log(req.readyState);
                console.log(req.responseText);
                //empty password input box 
                document.getElementById('loginPass').value = '';
            }
        };
        req.open('POST', 'login', true);
        req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        req.send('username=' + username + '&password=' + password);
    }
}

function checkActiveSession() {
    var req = new XMLHttpRequest();

    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            console.log(req.status);
            console.log(req.readyState);
            //if success, servlet says we are already logged in so we display UI
            console.log(req.getResponseHeader("result"));
            activeUser = req.getResponseHeader("username");
            activeEmail = req.getResponseHeader("email");
            console.log(activeUser);
            console.log(activeEmail);
            displayUI(req);
        } else if (req.readyState === 4 && req.status !== 200) {
            //else we just normaly display our page
            console.log(req.status);
            console.log(req.readyState);
            console.log(req.getResponseHeader("result"));
        }
    };
    req.open('POST', 'checkState', true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.send();
}

function logout() {
    username = 'user';
    password = 'pass';
    var req = new XMLHttpRequest();

    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            console.log(req.status);
            console.log(req.readyState);
            console.log(req.responseText);
            activeUser = '';
            activeEmail = '';
            //new
            document.cookie = 'username=' + activeUser;
            document.cookie = 'email=' + activeEmail;
            console.log(document.cookie);
            hideUI();
        } else if (req.readyState === 4 && req.status !== 200) {
            console.log(req.status);
            console.log(req.readyState);
            console.log(req.responseText);
        }
    };
    req.open('POST', 'logout', true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.send();
}

function showForm() {
    //we are modifying user's field after this method
    isModify = true;
    console.log("isModify: " + isModify + " activeUser: " + activeUser);
    //hide msgContainer,infoTable,modify,logout,login
    document.getElementById('msgContainer').style.display = 'none';
    document.getElementById('infoTable').style.display = 'none';
    document.getElementById('modify').style.display = 'none';
    document.getElementById('initiatives').style.display = 'none';
    document.getElementById('logout').style.display = 'none';
    document.getElementById('loginBut').style.display = 'none';
    document.getElementById('showAllUsers').style.display = 'none';
    userlist = document.getElementById('userlist');
    if (userlist !== null) {
        userlist.outerHTML = '';
    }
    //show form
    container = document.getElementById('formContainer');
    container.style.display = 'block';
    //hide signup button of original form
    document.getElementById('signup').style.display = 'none';
    //make new ok button for submiting the new fields
    document.getElementById('myform').style.display = 'block';
    okButton = document.createElement('button');
    container.appendChild(okButton);
    okButton.setAttribute('id', 'okModifier');
    okButton.setAttribute('onclick', 'updateUser()');
    okMsg = document.createTextNode('OK');
    okButton.appendChild(okMsg);
    backButton = document.createElement('button');
    container.appendChild(backButton);
    backButton.setAttribute('id', 'backFromModify');
    backButton.setAttribute('onclick', 'restoreUIfromModify()');
    backButton.style.display = 'block';
    backMsg = document.createTextNode('Back');
    backButton.appendChild(backMsg);
}

function restoreUIfromModify() {
    console.log('restoring');
    isModify = false;
    document.getElementById('infoTable').style.display = 'block';
    document.getElementById('modify').style.display = 'block';
    document.getElementById('initiatives').style.display = 'block';
    document.getElementById('logout').style.display = 'block';
    document.getElementById('loginBut').style.display = 'block';
    document.getElementById('showAllUsers').style.display = 'block';
    document.getElementById('formContainer').style.display = 'none';
    document.getElementById('myform').style.display = 'none';
    document.getElementById('okModifier').outerHTML = '';
    document.getElementById('backFromModify').outerHTML = '';
    console.log('restored');
}

function updateUser() {
    //send request to updateUser with parameter update == "update"
    //restore page to UI, both after success and failure
    //hide myform, okModifier
    //show infotable,modify,logout,showAllUsers
    console.log("USERNAME: " + USERNAME + " EMAIL: " + EMAIL);
    if (USERNAME && EMAIL && PASS1 && PASS2 && NAME && LASTNAME && CITY && PROFESSION) {
        var username = document.getElementById('username').value;
        var password = document.getElementById('pass1').value;
        var email = document.getElementById('email').value;
        var name = document.getElementById('name').value;
        var lastName = document.getElementById('lastName').value;
        var city = document.getElementById('city').value;
        var profession = document.getElementById('profession').value;
        var day = document.getElementById('day').value;
        var month = document.getElementById('month').name;
        var year = document.getElementById('year').value;
        var gender = document.querySelector('input[name="gender"]:checked').value;
        var country = document.getElementById('country').value;
        var address = document.getElementById('address').value;
        var interests = document.getElementById('interests').value;
        var genInfo = document.getElementById('genInfo').value;
        var update = "update";

        var req = new XMLHttpRequest();

        req.onreadystatechange = function () {
            if (req.readyState === 4 && req.status === 200) {
                console.log(req.status);
                console.log(req.readyState);
                console.log(req.getResponseHeader("result"));
                activeUser = req.getResponseHeader("newUsername");
                activeEmail = req.getResponseHeader("newEmail");
                console.log('active user =' + activeUser);
                console.log('active email =' + activeEmail);
                hideForm();
                document.getElementById('okModifier').outerHTML = '';
                displayUI(req);
            } else if (req.readyState === 4 && req.status !== 200) {
                console.log(req.status);
                console.log(req.readyState);
                console.log(req.responseText);
            }
        };
        req.open('POST', 'updateUser', true);
        req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        req.send('username=' + username + '&password=' + password + '&email=' + email + '&name=' + name + '&lastName=' + lastName + '&city=' + city + '&profession=' + profession + '&day=' + day + '&month=' + month + '&year=' + year + '&gender=' + gender + '&country=' + country + '&address=' + address + '&interests=' + interests + '&genInfo=' + genInfo);
    }
}

function showUsers() {
    var req = new XMLHttpRequest();

    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            console.log(req.status);
            console.log(req.readyState);
            displayUsersList(req);
        } else if (req.readyState === 4 && req.status !== 200) {
            console.log(req.status);
            console.log(req.readyState);
            console.log(req.responseText);
        }
    };
    req.open('POST', 'getAllUsers', true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.send();
}

function displayUsersList(req) {
    var i = 0;
    if (req.getResponseHeader('user' + i) !== null) {
        userList = document.createElement('ul');
        userList.setAttribute('id', 'userlist');
        while (req.getResponseHeader('user' + i) !== null) {
            name = req.getResponseHeader('user' + i);
            useri = document.createElement('li');
            userList.appendChild(useri);
            useriName = document.createTextNode(name);
            useri.appendChild(useriName);
            document.getElementById('container').appendChild(userList);
            ++i;
        }

    }
}

function showInitiativeUI() {
    var container = document.getElementById('container');
    var initiativeHTML = '<button id="createInitiative" type="button" onclick="createInitiativeForm()">Create</button>\n\
                          <button id="showOwn" type="button" onclick="showOwnInitiatives()">Show Mine</button>\n\
                          <button id="getActive" type="button" onclick="getActiveInitiatives()">Active</button>\n\
                          <button id="getExpired" type="button" onclick="getExpiredInitiatives()">Expired</button>\n\
                          <button id="back" type="button" onclick="showUserUI()">Back</button>';
    container.innerHTML = initiativeHTML;
}

function createInitiativeForm() {
    var container = document.getElementById('container');
    var initiativeFormHTML = '<button id="createInitiative" type="button" onclick="createInitiativeForm()">Create</button>\n\
    <button id="showOwn" type="button" onclick="showOwnInitiatives()">Show Mine</button>\n\
    <button id="getActive" type="button" onclick="getActiveInitiatives()">Active</button>\n\
    <button id="getExpired" type="button" onclick="getExpiredInitiatives()">Expired</button>\n\
    <button id="back" type="button" onclick="showUserUI()">Back</button>\n\
    <div id="formContainer">\n\
        <form id="initiativeForm">\n\
            <label for="Title">Title<br></label>\n\
            <input id="Title" type="text" value="" required><br>\n\
            <label for="Category">Category<br></label>\n\
            <input id="Category" type="text" value="" required><br>\n\
            <label for="Description">Description<br></label>\n\
            <input id="Description" type="textarea" value="" required><br>\n\
            <input id="Submit" type="button" value="Submit" onclick="createInitiative()"></button>\n\
        </form>\n\
    </div>';
    container.innerHTML = initiativeFormHTML;
}

function createInitiative() {
    var title = document.getElementById('Title').value;
    var category = document.getElementById('Category').value;
    var description = document.getElementById('Description').value;
    var creator = activeUser;
    //test
    console.log(creator);

    var req = new XMLHttpRequest();

    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            console.log(req.status);
            console.log(req.readyState);
            var container = document.getElementById('container');
            var activeInitiativesHTML = req.responseText;
            container.innerHTML = activeInitiativesHTML;
        } else if (req.readyState === 4 && req.status !== 200) {
            console.log(req.status);
            console.log(req.readyState);
            console.log(req.responseText);
        }
    };
    req.open('POST', 'createInitiative', true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.send('creator=' + creator + '&title=' + title + '&category=' + category + '&description=' + description);
}

function getActiveInitiatives() {
    var username = activeUser;

    var req = new XMLHttpRequest();

    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            console.log(req.status);
            console.log(req.readyState);
            var container = document.getElementById('container');
            var activeInitiativesHTML = req.responseText;
            container.innerHTML = activeInitiativesHTML;
        } else if (req.readyState === 4 && req.status !== 200) {
            console.log(req.status);
            console.log(req.readyState);
            console.log(req.responseText);
        }
    };
    req.open('POST', 'getActiveInitiatives', true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.send('username=' + username);
}

function showOwnInitiatives() {
    var creator = activeUser;

    var req = new XMLHttpRequest();

    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            console.log(req.status);
            console.log(req.readyState);
            var container = document.getElementById('container');
            var ownInitiativesHTML = req.responseText;
            container.innerHTML = ownInitiativesHTML;
        } else if (req.readyState === 4 && req.status !== 200) {
            console.log(req.status);
            console.log(req.readyState);
            console.log(responseText);
        }
    };
    req.open('POST', 'showMyInitiatives', true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.send('creator=' + creator);
}

function activateInitiative(title, creator, i) {
    var title = title;
    var creator = creator;
    var month = document.getElementById("edate" + i).querySelector("#month").value;
    var year = document.getElementById("edate" + i).querySelector("#year").value;
    var day = document.getElementById("edate" + i).querySelector("#day").value;
    var hour = document.getElementById("edate" + i).querySelector("#hour").value;
    var minute = document.getElementById("edate" + i).querySelector("#minute").value;
    var second = document.getElementById("edate" + i).querySelector("#second").value;
    console.log('expiration date: year/month/day  hour/minute/second = ' + year + '/' + month + '/' + day + '  /' + hour + '/' + minute + '/' + second);
    var req = new XMLHttpRequest();

    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            var container = document.getElementById('container');
            var InitiativesHTML = req.responseText;
            container.innerHTML = InitiativesHTML;
            var activeInitiativesHTML = req.responseText;
            container.innerHTML = activeInitiativesHTML;
        } else if (req.readyState === 4 && req.status !== 200) {
            console.log(req.status);
            console.log(req.readyState);
            console.log(req.responseText);
        }
    };
    req.open('POST', 'activateInitiative', true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.send('creator=' + creator + '&title=' + title + '&day=' + day + '&month=' + month + '&year=' + year + '&hour=' + hour + '&minute=' + minute + '&second=' + second);
}

//keep these for updateInitiative
var currentTitle;
var currentID;

function getInitiativeForUpdate(title, creator, description, category, id){
    currentTitle = title;
    currentID = id;
    var description = description;
    var category = category;
    var container = document.getElementById('container');
    var initiativeFormHTML = '<button id="createInitiative" type="button" onclick="createInitiativeForm()">Create</button>\n\
    <button id="showOwn" type="button" onclick="showOwnInitiatives()">Show Mine</button>\n\
    <button id="getActive" type="button" onclick="getActiveInitiatives()">Active</button>\n\
    <button id="getExpired" type="button" onclick="getExpiredInitiatives()">Expired</button>\n\
    <button id="back" type="button" onclick="showUserUI()">Back</button>\n\
    <div id="formContainer">\n\
        <form id="initiativeForm">\n\
            <label for="Title">Title<br></label>\n\
            <input id="Title" type="text" value="" required><br>\n\
            <label for="Category">Category<br></label>\n\
            <input id="Category" type="text" value="" required><br>\n\
            <label for="Description">Description<br></label>\n\
            <input id="Description" type="textarea" value="" required><br>\n\
            <input id="Submit" type="button" value="Update" onclick="updateInitiative()"></button>\n\
        </form>\n\
    </div>';
    container.innerHTML = initiativeFormHTML;   
}

function updateInitiative(){
    var newTitle = document.getElementById('Title').value;
    var category = document.getElementById('Category').value;
    var description = document.getElementById('Description').value;
    var creator = activeUser;
    
    var oldTitle = currentTitle;
    var id = currentID;
    
    var req = new XMLHttpRequest();
    
    req.onreadystatechange = function(){
        if(req.readyState === 4 && req.status === 200){
            //if success on update current title has the new value
            currentTitle = newTitle;
            console.log(req.status);
            console.log(req.readyState);
            var container = document.getElementById('container');
            var ownInitiativesHTML = req.responseText;
            container.innerHTML = ownInitiativesHTML;
        }else if(req.readyState === 4 && req.status !== 200){
        	console.log(req.status);
            console.log(req.readyState);
            console.log(req.responseText);
        }
    };
    req.open('POST', 'updateInitiative', true);
    req.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    req.send('creator=' + creator + '&title=' + newTitle + '&category=' + category + '&description=' + description + '&id=' + id);
}

function VoteUpdateVote(action, upvotedownvote, title, creator, username) {//action is vote or update
    var req = new XMLHttpRequest();

    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            var container = document.getElementById('container');
            var InitiativesHTML = req.responseText;
            container.innerHTML = InitiativesHTML;
            console.log(req.status);
            console.log(req.readyState);
            console.log(req.responseText);
        } else if (req.readyState === 4 && req.status !== 200) {
            console.log(req.status);
            console.log(req.readyState);
            console.log(req.responseText);
        }
    };
    req.open('POST', 'voteUpdateVote', true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.send('action=' + action + '&upvotedownvote=' + upvotedownvote + '&title=' + title + '&creator=' + creator + '&username=' + username);
}

function inactivateExpiredInitiatives() {
    var req = new XMLHttpRequest();
    
    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            console.log(req.status);
            console.log(req.readyState);
            console.log('Inactivating expired Initiatives...');
            console.log(req.responseText);
            var container = document.getElementById('container');
            var refreshedHTML = req.responseText;
            container.innerHTML = refreshedHTML;
        } else if (req.readyState === 4 && req.status !== 200) {
            console.log(req.status);
            console.log(req.readyState);
            console.log('Inactivation of expired Initiatives failed.');
        }
    };
    req.open('POST', 'inactivateExpiredInitiatives', true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.send();
}

function getExpiredInitiatives() {
    var req = new XMLHttpRequest();
    
    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            console.log(req.status);
            console.log(req.readyState);
            console.log('Rendering expired Initiatives...');
            var container = document.getElementById('container');
            var expiredInitiativesHTML = req.responseText;
            container.innerHTML = expiredInitiativesHTML;
        } else if (req.readyState === 4 && req.status !== 200) {
            console.log(req.status);
            console.log(req.readyState);
            console.log('Rendering of expired Initiatives failed.');
        }
    };
    req.open('POST', 'showResults', true);
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    req.send();
}