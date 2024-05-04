function display(event){
    document.getElementById("result").value+=event;
}

function clr() {
    document.getElementById("result").value = ""
}

function solve(){
    let ip  = document.getElementById("result").value
    let cal = eval(ip)
    document.getElementById("result").value = cal
}
