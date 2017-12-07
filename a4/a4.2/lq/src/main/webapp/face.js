/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

'use strict';

/*
    Author: Panagiotis Papadakos papadako@csd.uoc.gr

    For the needs of the hy359 2017 course
    University of Crete

*/

/*  face recognition that is based on faceplusplus service */
var faceRec = (function () {
  var faceToken = new String();
  // Object that holds anything related with the facetPlusPlus REST API Service
  var faceAPI = {
    apiKey: 'l2jNgKbk1HXSR4vMzNygHXx2g8c_xT9c',
    apiSecret: '2T6XdZt4EYw-I7OhmZ6g1wtECl81e_Ip',
    app: 'hy359',
    // Detect
    // https://console.faceplusplus.com/documents/5679127
    detect: 'https://api-us.faceplusplus.com/facepp/v3/detect',  // POST
    // Set User ID
    // https://console.faceplusplus.com/documents/6329500
    setuserId: 'https://api-us.faceplusplus.com/facepp/v3/face/setuserid', // POST
    // Get User ID
    // https://console.faceplusplus.com/documents/6329496
    getDetail: 'https://api-us.faceplusplus.com/facepp/v3/face/getdetail', // POST
    // addFace
    // https://console.faceplusplus.com/documents/6329371
    addFace: 'https://api-us.faceplusplus.com/facepp/v3/faceset/addface', // POST
    // Search
    // https://console.faceplusplus.com/documents/5681455
    search: 'https://api-us.faceplusplus.com/facepp/v3/search', // POST
    // Create set of faces
    // https://console.faceplusplus.com/documents/6329329
    create: 'https://api-us.faceplusplus.com/facepp/v3/faceset/create', // POST
    // update
    // https://console.faceplusplus.com/documents/6329383
    update: 'https://api-us.faceplusplus.com/facepp/v3/faceset/update', // POST
    // removeface
    // https://console.faceplusplus.com/documents/6329376
    removeFace: 'https://api-us.faceplusplus.com/facepp/v3/faceset/removeface', // POST
  };

  // Object that holds anything related with the state of our append
  // Currently it only holds if the snap button has been pressed
  var state = {
    photoSnapped: false,
  };

  // function that returns a binary representation of the canvas
  function getImageAsBlobFromCanvas(canvas) {

    // function that converts the dataURL to a binary blob object
    function dataURLtoBlob(dataUrl) {
      // Decode the dataURL
      var binary = atob(dataUrl.split(',')[1]);

      // Create 8-bit unsigned array
      var array = [];
      for (var i = 0; i < binary.length; i++) {
        array.push(binary.charCodeAt(i));
      }

      // Return our Blob object
      return new Blob([new Uint8Array(array)], {
        type: 'image/jpg',
      });
    }

    var fullQuality = canvas.toDataURL('image/jpeg', 1.0);
    return dataURLtoBlob(fullQuality);

  }

  // function that returns a base64 representation of the canvas
  function getImageAsBase64FromCanvas(canvas) {
    return canvas.toDataURL('image/jpeg', 1.0);

  }

  // Function called when we upload an image
  function uploadImage() {
    if (state.photoSnapped) {
      var canvas = document.getElementById('canvas');
      var image = getImageAsBlobFromCanvas(canvas);
      var response;
      // TODO!!! Well this is for you ... YES you!!!
      // Good luck!

      // Create Form Data. Here you should put all data
      // requested by the face plus plus services and
      // pass it to ajaxRequest
      var data = new FormData();
      data.append('api_key', faceAPI.apiKey);
      data.append('api_secret', faceAPI.apiSecret);
      // add also other query parameters based on the request
      // you have to send
      /*The snapshot taken by the user, hopefully with his/her face.*/
      data.append('image_file', image);
      // You have to implement the ajaxRequest. Here you can
      // see an example usage of how you should call this
      // First argument: the HTTP method
      // Second argument: the URI where we are sending our request
      // Third argument: the data (the parameters of the request)
      ajaxRequestDetect('POST', faceAPI.detect, data);
    } else {
      alert('No image has been taken!');
    }
  }

  // Function for initializing things (event handlers, etc...)
  function init() {
    // Put event listeners into place
    // Notice that in this specific case handlers are loaded on the onload event
    window.addEventListener('DOMContentLoaded', function() {
      // Grab elements, create settings, etc.
      var canvas = document.getElementById('canvas');
      var context = canvas.getContext('2d');
      var video = document.getElementById('video');
      var mediaConfig = {
        video: true,
      };
      var errBack = function(e) {
        console.log('An error has occurred!', e);
      };

      // Put video listeners into place
      if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
        navigator.mediaDevices.getUserMedia(mediaConfig).then(function(stream) {
          video.srcObject = stream;
          video.onloadedmetadata = function(e) {
            video.play();
          };
        });
      }

      // Trigger photo take
      document.getElementById('snap').addEventListener('click', function() {
        context.drawImage(video, 0, 0, 450, 400);
        state.photoSnapped = true; // photo has been taken
      });

      // Trigger when upload button is pressed
      document.getElementById('upload').addEventListener('click', uploadImage);

    }, false);

  }

  // !!!!!!!!!!! ================ TODO  ADD YOUR CODE HERE  ====================
  // From here on there is code that should not be given....

    // You have to implement the ajaxRequest function!!!!

  // !!!!!!!!!!! =========== END OF TODO  ===============================

  /*Function to detect the user's face by the service.
    On success, setuserid() function is called*/
  function ajaxRequestDetect(method,type,data){
    var http = new XMLHttpRequest();
    http.onreadystatechange = function(){
      console.log(http);
      console.log(http.status, 'ajaxrequestDetect');
      if(this.readyState === 4 && this.status === 200){
        var response = JSON.parse(http.response);
        console.log(response.faces[0].face_token, 'FACE TOKEN');
        faceToken = response.faces[0].face_token;
        var data2 = new FormData();
        data2.append('api_key', faceAPI.apiKey);
        data2.append('api_secret', faceAPI.apiSecret);
        data2.append('face_token', faceToken);
        data2.append('user_id', document.getElementById('username').value);
        setuserid('POST',faceAPI.setuserId,data2);
      }
    }
    http.open(method, type, true);
    http.send(data);
  }

  /*Function to set user id to 'username' provided by the user.
    On success, addface() function is caled.*/
  function setuserid(method,type,data){
    console.log(data.get('face_token'), 'SETUSERID');
    var http = new XMLHttpRequest();
    http.onreadystatechange = function(){
      console.log(http);
      console.log(http.status, 'setuserid');
      if(this.readyState === 4 && this.status === 200){
        var response = JSON.parse(http.response);
        console.log(response.user_id, 'USER ID RETURNED FROM SETUSERID');
        var data3 = new FormData();
        data3.append('api_key', faceAPI.apiKey);
        data3.append('api_secret', faceAPI.apiSecret);
        data3.append('outer_id', 'hy359');
        data3.append('face_tokens', faceToken);
        addface('POST',faceAPI.addFace,data3);
      }
    }
    http.open(method, type, true);
    http.send(data);
  }

/*Function to add photo to hy359 existing faceset*/
  function addface(method,type,data){
    var http = new XMLHttpRequest();
    http.onreadystatechange = function(){
      console.log(http);
      console.log(http.status, 'addface');
      if(this.readyState === 4 && this.status === 200){
        var response = JSON.parse(http.response);
        console.log(response.outer_id, 'ADD FACE RESPONSE');
      }
    }
    http.open(method, type, true);
    http.send(data);
  }


  // Public API of function for facet recognition
  // You might need to add here other methods based on your implementation
  return {
    init: init,
  };

})();

