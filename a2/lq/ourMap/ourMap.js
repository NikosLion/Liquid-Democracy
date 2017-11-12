function ourMap(array) {
 // What do we have to hold/track???
 var current = array;TO COMPLETE!
 var original = array;TO COMPLETE!
 return (function mapWithCallback(fn, callback) {
 // If fn is not a function return current Array
 if(typeof fn !== 'function'){TO COMPLETE TOGETHER!
 return current;}TO COMPLETE TOGETHER!
 // map fn to array and save result to current array
 for(i = 0; i < current.length; i++){current[i] = fn(array[i]);}TO COMPLETE!
 // If callback is a function, execute callback
 if(typeof callback === 'function'){TO COMPLETE TOGETHER!
 callback(original);}TO COMPLETE TOGETHER!
 return mapWithCallback;TO COMPLETE! // We have to return something!
 })
}
