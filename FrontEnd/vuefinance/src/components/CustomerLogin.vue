<template>
    <!-- define the layout -->
    <div>
        <br>
        <h4>Welcome to Personal Finance App</h4>
        <form class="loginForm">
            <div class="imgcontainer">
                <img src = "../assets/appLogo.png" alt="Avatar" class="avatar" >
            </div>
            <div class="container">
                <label for="username">Username</label>
                <input type="text" placeholder="Enter Username" id="username" v-model="customerLoginRequest.username" />
                <label for="password">Password</label>
                <input type="password" placeholder="Enter Password" id="password" v-model="customerLoginRequest.password" />
            </div>
        </form>

        <button class="loginSubmit" type="submit" @click="login">Login</button>
        <p>{{ message }}</p>
    </div>

</template>

<script>
import LoginService from "../services/LoginService";

export default {
    name: "customerLogin",
    data() {            // data or variables used
        return {
            customerLoginRequest: { username: "", password: "" },  // json
            message: ""
        };
    },
    methods: {
        login() {
            LoginService.login(this.customerLoginRequest)
                .then(response => {       // HttpStatus.OK
                    var customer = response.data;
                    console.log(customer);
                    localStorage.setItem("cid", customer.id);
                    // store the student (sid) to the local storage
                    // RESTful server does not have session
                    // Session info is saved in local storage and talk to
                    // server as json
                    this.$router.push({ name: "login" });
                })
                .catch(e => {
                    this.customerLoginRequest.username = "";
                    this.customerLoginRequest.password = "";
                    this.message = e.response.data.message;
                    console.log(e.response.data);
                });
        }
    },
    mounted() {   // called when component is loaded
        this.message = "";
    }
};
</script>

<style>
/*
   Created by: Ngoc Khanh Trinh
   Created on: 13/06/2022
*/
@charset "utf-8";

/* Set the default page element styles */
html {
   background-color: rgba(230, 235, 237, 0.25);
   font-size: 12px;
   font-family: Tahoma, Geneva, sans-serif;
}

/* HTML and Body Styles */
body {
   font-family: Verdana, Geneva, sans-serif;
   font-size: 100%;
   font-weight: inherit;
   line-height: 1.2em;
   margin: 0px auto;
   min-height: 100%;
   min-width: 200px;
   max-width: 1100px;
   width: 100%;
   padding: 0px;
   text-decoration: none;
   vertical-align: baseline;
   box-shadow: 0px 10px 50px rgb(51,51,51);
}

/* Basic styles to be used with all devices and under all conditions */

address, article, aside, blockquote, cite, dl, dt, dd, em, figcaption, figure, footer, h1, h2, h3, h4, h5, h6, header, img, li, main, nav, nav a, ol, p, section, span, ul {
   margin: 0;
   padding: 0;
   border: 0;
   font-size: 100%;
   vertical-align: baseline;
   background: transparent;
   -webkit-box-sizing: border-box;
   -moz-box-sizing: border-box;
   box-sizing: border-box;
}

ul, ol {
   list-style: none;
}

nav ul {
   list-style: none;
   list-style-image: none;
}

nav a {
   text-decoration: none;
}

h4 {
    font-size: 1.2em;
    margin: 20 auto 5% auto;
    text-align: center;
}


/* Bordered form */
form.loginForm {
  border: 0px solid #f1f1f1;
  width: 50%;
  margin: 0 auto; 
}

/* Full-width inputs */
input[type=text], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

/* Set a style for all buttons */
button.loginSubmit {
  background-color: #006381;
  color: white;
  padding: 14px 20px;
  margin: 10px 30% 5% 30%;
  border: none;
  cursor: pointer;
  width: 40%;
}

/* Add a hover effect for buttons */
button:hover {
  opacity: 0.8;
}

/* Extra style for the cancel button (red) */
.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

/* Center the avatar image inside this container */
.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
}

/* Avatar image */
img.avatar {
  width: 30%;
  border-radius: 0;
}

/* Add padding to containers */
.container {
  padding: 16px;
}

/* The "Forgot password" text */
span.psw {
  float: right;
  padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
    display: block;
    float: none;
  }
  .cancelbtn {
    width: 100%;
  }
}
</style>
