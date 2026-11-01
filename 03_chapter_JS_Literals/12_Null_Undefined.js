// ============================================================
// Topic: null vs undefined in JavaScript
// ============================================================

/*
  SIMPLE DEFINITIONS:

  undefined  ->  A variable exists, but it has not been assigned any value yet.
                 JavaScript itself sets this automatically.

  null       ->  A variable exists, but the developer explicitly assigns 
                "no value" or "empty".
                 It is intentional absence of any value.
*/

// .var.x; // value is undefined because it has not been assigned any value yet.
// console.log(x); // undefined

// .var.y; // value is null because it has been explicitly assigned "no value" or "empty".
// let y = null;
// console.log(y); // null

// --------------------------------------------------------
// 1. undefined
// --------------------------------------------------------

let userName; // declared but not assigned
console.log(userName);
console.log(typeof userName);

// function greet() {
//     // no return statement
// }
// console.log(greet()); 

let x;
x = 10;
console.log(x);

// --------------------------------------------------------
// 2. null
// --------------------------------------------------------
let profilePicture = null;
console.log(profilePicture);
console.log(typeof profilePicture); // "object"   <-- known JS quirk!

/*
  | Feature              | undefined                     | null                           |
  |----------------------|-------------------------------|--------------------------------|
  | Meaning              | Not assigned yet              | Intentionally empty            |
  | Who sets it?         | JavaScript automatically      | Developer manually             |
  | Type                 | undefined                     | object (historical bug in JS)  |
  | ==  comparison        | null == undefined  -> true    |                                |
  | === comparison       | null === undefined -> false   |                                |
*/