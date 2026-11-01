let a = 10; 
console.log(a);
let x; 

for(let a = 0; a<10000; a++){
    console.log(a);
    badCodeFn();
}

function badCodeFn(){
    console.log("Hello");
}