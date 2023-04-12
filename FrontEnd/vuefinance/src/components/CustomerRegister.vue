<template>
    <!-- define the layout -->
    <div>
        <form style="border:1px solid #ccc">
            <div class="container">
                <h4>Register an account</h4><br>
                <p>{{ message }}</p><br>
                <!--Please fill in this form to create an account -->
                <hr>
                <label for="username"><b>Username</b></label>
                <input type="text" placeholder="Enter your username" name="username" id="username"
                    v-model="customerRegisterRequest.username" required>

                <label for="password"><b>Password</b></label>
                <input type="password" placeholder="Enter your password" name="password" id="password"
                    v-model="customerRegisterRequest.password" required>

                <label for="name"><b>Full name</b></label>
                <input type="text" placeholder="Enter your full name" name="name" id="name"
                    v-model="customerRegisterRequest.name">

                <label for="age"><b>Your age</b></label>
                <input type="number" placeholder="Enter your age" name="age" id="age" v-model="customerRegisterRequest.age">

                <label for="email"><b>Email</b></label>
                <input type="text" placeholder="Enter your email" name="email" id="email"
                    v-model="customerRegisterRequest.email">

                <label for="profession"><b>Your profession</b></label>
                <input type="text" placeholder="Enter your profession (teacher/software engineer/..)" name="profession"
                    id="profession" v-model="customerRegisterRequest.profession">

                <label for="country"><b>Country</b></label>
                <input type="text" placeholder="Enter your country" name="country" id="country"
                    v-model="customerRegisterRequest.country">



                <label>
                    <input type="checkbox" checked="checked" name="remember" style="margin-bottom:15px"> Remember me
                </label>

                <p>By creating an account you agree to our <b>Terms & Privacy</b>.</p>


            </div>
        </form>
        <div class="clearfix">
            <button type="button" class="cancelbtn" @click="login">Login</button>
            <button type="submit" class="signupbtn" @click="register">Register</button>
        </div>
    </div>
</template>

<script>
import RegisterService from "../services/RegisterService";
export default {
    name: "customerRegister",
    data() {
        return {
            customerRegisterRequest: { name: "", age: "", email: "", profession: "", country: "", username: "", password: "" },  // json
            message: "Please fill in this form to create an account"
        }
    },
    methods: {
        register() {
            //If the user has fillout the form
            if(this.customerRegisterRequest.name !='' && this.customerRegisterRequest.age!='' && this.customerRegisterRequest.email != '' && this.customerRegisterRequest.profession !="" && this.customerRegisterRequest.username !='' && this.customerRegisterRequest.password !='' &&this.customerRegisterRequest.country !=''){
                RegisterService.register(this.customerRegisterRequest)
                .then(response => {       // HttpStatus.OK
                    var customer = response.data;
                    console.log(customer)
                    this.message = `Account for ${this.customerRegisterRequest.name} created. Please click on Login button to start using.`
                    // this.$router.push({ name: "login" });
                })
                .catch(e => {
                    this.message = e.response.data.message;
                    console.log(e.response.data);
                });
            }else{
                alert("Please fill out the registration form")
            }
           
        },
        login() {
            this.$router.push({ name: "login" });
        }

    },

    mounted() {

    }
}
</script>

<style>
* {
    box-sizing: border-box
}

/* Full-width input fields */
input[type=text],
input[type=password] {
    width: 100%;
    padding: 15px;
    margin: 5px 0 22px 0;
    display: inline-block;
    border: none;
    background: #f1f1f1;
}

input[type=text]:focus,
input[type=password]:focus {
    background-color: #ddd;
    outline: none;
}

hr {
    border: 1px solid #f1f1f1;
    margin-bottom: 25px;
}

h4 {
    font-size: 1.2em;
    margin: 20 auto 10% auto;
    text-align: center;
    text-decoration: none;
}

p {
    font-size: 0.9em;
    margin: 20 auto 10% auto;
    text-align: center;
    text-decoration: none;
}

/* Set a style for all buttons */
button {
    background-color: #006381;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
    opacity: 0.9;
}

button:hover {
    opacity: 1;
}

/* Extra styles for the cancel button */
.cancelbtn {
    padding: 14px 20px;
    background-color: #840f06;
}

/* Float cancel and signup buttons and add an equal width */
.cancelbtn,
.signupbtn {
    float: left;
    width: 50%;
    margin: auto ;
}

/* Add padding to container elements */
.container {
    padding: 16px;
}

/* Clear floats */
.clearfix::after {
    content: "";
    clear: both;
    display: table;
}

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {

    .cancelbtn,
    .signupbtn {
        width: 100%;
    }
}
</style>