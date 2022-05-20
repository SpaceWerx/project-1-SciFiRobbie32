const url = "https://localhost:3000";
//localhost is a placeholder

document.getElementById("getEmployeeButton").addEventListener("click",getEmployees);
document.getElementById("loginButton").addEventListener("click",loginFunction);

async function getEmployees(){
    let response = await fetch(url+"employee");
    console.log(response);
    if(response.status=200){
        let data = await response.json(); //The response is not JSON. Its something else, therefore .json() parse's the response as a JS object.
        for(let employee of data){
            let row = document.createElement("tr");
            let cell1 = document.createElement("td");
            cell.innerHTML = employee.id
            row.appendChild(cell1)

            let cell2 = document.createElement("td");
            cell.innerHTML = employee.username
            row.appendChild(cell2)

            let cell3 = document.createElement("td");
            cell.innerHTML = employee.password
            row.appendChild(cell3)

            let cell4 = document.createElement("td");
            cell.innerHTML = employee.role
            row.appendChild(cell4)

            document.getElementById("employeeBody").appendChild(row)
            
        }

    }
}
async function loginFunction(){
    let usern = document.getElementById("username").value;
    let userp = document.getElementById("password").Value;

    //user json object, like a user object
    let user ={
        username: usern,
        password: userp,
        role: userr
    }

    console.log(user);

    let response = await fetch(url + "login",{
        method: "POST", //what we are doing.
        body: JSON.stringify(user), //turns user object into sendable json
        credentials: "include"
    });

    console.log(response.status);
    if(response.status==202) document.getElementById("loginRow").innerText="Welcome";
    else if(response.status==202) document.getElementById("loginRow").innerText="Welcome";
    else document.getElementById("loginRow").innerText="Login Failed, Please refresh";
}