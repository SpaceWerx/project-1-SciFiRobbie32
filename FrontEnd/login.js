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