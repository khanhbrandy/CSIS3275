<template>
    <div>
        <div>
            <button class="logoutSubmit" type="submit" @click="logout">Click here to log out and back to Home</button>
        </div>

        <h4 v-if="customer">Welcome {{ customer.name }}. Please enter your expense/income.</h4>
        <h4 v-else>Please log back in...</h4><br>
        <p>{{ message }}</p><br>
        <div class="container">
            <form>

                <label for="amount">Amount</label>
                <input type="number" id="amount" name="amount" placeholder="Enter amount..." v-model="transactionRequest.amount">
                <label for="category">Category</label>
                <select id="category" name="category" v-model="transactionRequest.category">
                    <option disabled value="">Please select one</option>
                    <option value="Food">Food</option>
                    <option value="Clothing">Clothing</option>
                    <option value="Transportation">Transportation</option>
                    <option value="Study">Study</option>
                    <option value="Housing">Housing</option>
                    <option value="Investment">Investment</option>
                    <option value="Salary">Salary</option>
                </select>
                <label for="type">Type</label>
                <select id="type" name="type" v-model="transactionRequest.type">
                    <option disabled value="">Please select one</option>
                    <option value="expense">Expense</option>
                    <option value="income">Income</option>
                </select>
                <label for="note">Note</label>
                <textarea id="note" name="note" placeholder="Notes on your transaction..." style="height:100px" v-model="transactionRequest.note"></textarea>

                <!-- <input type="submit" value="Add transaction" @click="createTransaction"> -->

            </form>
            <div class="clearfix">
            <button type="button" class="signupbtn"  @click="createTransaction">Add transaction</button>
            <button type="button" class="cancelbtn"  @click="report">See summary report</button>
        </div>
        </div>
    </div>
</template>

<script>
import TransactionService from "../services/TransactionService"
export default {
    name: "customerTransaction",
    data() {
        return {
            customer: null,
            transactionRequest: { amount: 0, category: "", type: "", note: "" },  // json
            message: ""


        }
    },
    methods: {
        logout(event) {
            event.preventDefault();
            localStorage.removeItem('cid');
            this.$router.push({ name: "login" });
        },
        retrieveCustomer() {
            const id = localStorage.getItem('cid');
            console.log("CID: " + id)
            TransactionService.get(id)
                .then(response => {
                    this.customer = response.data;
                    console.log(this.customer);
                })
                .catch(error => {
                    console.log(error);
                })
        },
        createTransaction() {
            const id = localStorage.getItem('cid');
            TransactionService.createTransaction(id, this.transactionRequest)
                .then(response => {       // HttpStatus.OK
                    var transaction = response.data;
                    console.log(transaction)
                    this.message = `New transaction added succesfully. Click See summary report for further information.`
                })
                .catch(e => {
                    this.message = e.response.data.message;
                    console.log(e.response.data);
                });
        },
        report() {
            this.$router.push({ name: "report" });
        }
    },

    mounted() {
        this.retrieveCustomer();

    }
}
</script>

<style>
button.logoutSubmit {
  background-color: #006381;
  color: white;
  padding: 14px 20px;
  margin: 10px 30% 1% 30%;
  border: none;
  cursor: pointer;
  width: 40%;
  border-radius: 25px;
  border: 2px solid  #006381;
  font-size: 1.2em;
}
/* Style inputs with type="text", select elements and textareas */
input[type=text], input[type=number], select, textarea {
  width: 100%; /* Full width */
  padding: 12px; /* Some padding */ 
  border: 1px solid #ccc; /* Gray border */
  border-radius: 4px; /* Rounded borders */
  box-sizing: border-box; /* Make sure that padding and width stays in place */
  margin-top: 6px; /* Add a top margin */
  margin-bottom: 16px; /* Bottom margin */
  resize: vertical /* Allow the user to vertically resize the textarea (not horizontally) */
}

/* Style the submit button with a specific background color etc */
input[type=submit] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

/* When moving the mouse over the submit button, add a darker green color */
input[type=submit]:hover {
  background-color: #45a049;
}

/* Add a background color and some padding around the form */
.container {
  border-radius: 5px;
  background-color: #ffffff;
  padding: 20px;
}

p {
    font-size: 1.1em;
    margin: 20 auto 10% auto;
    text-align: center;
    text-decoration: none;
    font-style: normal
}
</style>