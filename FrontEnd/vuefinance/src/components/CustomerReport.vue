<template>
    <!-- define the layout -->
    <div>
        <br>
        <h4 v-if="customer">Hi {{ customer.name }}. Please find below the summary report.</h4>
        <h4 v-else>Please log back in...</h4><br>
        <div class="imgcontainer">
            <img src="../assets/report.png" alt="Avatar" class="avatar">
        </div>
        <table class="summary">
            <tr>
                <th style="width:50%">Category</th>
                <th>Expense</th>
                <th>Income</th>
                <th>Total</th>
            </tr>
            <tr>
                <td>Number of transactions</td>
                <td><i class="fa fa-remove">{{ noExpense }}</i></td>
                <td><i class="fa fa-check">{{ noIncome }}</i></td>
                <td><i class="fa fa-check">{{ noTotal }}</i></td>
            </tr>
            <tr>
                <td>Average transaction value</td>
                <td><i class="fa fa-check">{{ avgExpense }}</i></td>
                <td><i class="fa fa-check">{{ avgIncome }}</i></td>
                <td><i class="fa fa-check">{{ avgTotal }}</i></td>
            </tr>

        </table>
        <table class="details">
            <thead>

                <tr>
                    <th>Created Date</th>
                    <th>Amount</th>
                    <th>Category</th>
                    <th>Type</th>
                    <th>Note</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="transaction in transactions" :key="transaction.id">
                    <td>{{ transaction.createdAt }}</td>
                    <td>{{ transaction.amount }}</td>
                    <td>{{ transaction.category }}</td>
                    <td>{{ transaction.type }}</td>
                    <td>{{ transaction.note }}</td>
                </tr>
            </tbody>
        </table>
        <br>
    </div>
</template>

<script>
import TransactionService from "../services/TransactionService"
export default {
    name: "customerReport",
    data() {
        return {
            noExpense: 0,
            noIncome: 0,
            noTotal: 0,
            avgExpense: 0,
            avgIncome: 0,
            avgTotal: 0,
            customer: null,
            transactions: []
        };
    },
    methods: {
        retrieveTransaction() {
            const id = localStorage.getItem('cid');
            TransactionService.retrieveTransaction(id)
                .then(response => {
                    this.transactions = response.data;
                    // Aggregate total
                    let totalValue = 0;
                    for (let i = 0; i < this.transactions.length; i++) {
                        totalValue += this.transactions[i].amount
                    }
                    this.noTotal = this.transactions.length;
                    this.avgTotal =  parseFloat(totalValue / this.noTotal).toFixed( 2 )

                    // Aggregate Expense
                    let totalExpenseValue = 0;
                    for (let i = 0; i < this.transactions.length; i++) {
                        if (this.transactions[i].type === "expense") {
                            totalExpenseValue += this.transactions[i].amount
                            this.noExpense++;
                        }
                    }
                    this.avgExpense =  parseFloat(totalExpenseValue / this.noExpense).toFixed( 2 )

                    // Aggregate Income
                    let totalIncomeValue = 0;
                    for (let i = 0; i < this.transactions.length; i++) {
                        if (this.transactions[i].type === "income") {
                            totalIncomeValue += this.transactions[i].amount
                            this.noIncome++;
                        }
                    }
                    this.avgIncome =  parseFloat(totalIncomeValue / this.noIncome).toFixed( 2 )

                })
                .catch(e => {
                    this.message = e.response.data.message;
                    console.log(e.response.data);
                });
        },
        retrieveCustomer() {
            const id = localStorage.getItem('cid');
            TransactionService.get(id)
                .then(response => {
                    this.customer = response.data;
                    console.log(this.customer);
                })
                .catch(error => {
                    console.log(error);
                })
        }
    },

    mounted() {
        this.retrieveCustomer();
        this.retrieveTransaction();
    }
};
</script>

<style scoped>
.details {
    width: 90%;
    border-collapse: collapse;
    margin: 2% auto 2% auto;
}

.summary {
    margin: 2% auto 2% auto;
    border-collapse: collapse;
    border-spacing: 0;
    width: 50%;
    border: 1px solid #ddd;
}

/* Style table headers and table data */
.summary th,
.summary td {
    text-align: center;
    padding: 16px;
}

.details th,
.details td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
}

.highlighted {
    background-color: yellow;
}

tr:nth-child(even) {
    background-color: #c7d8d9;
}

h4 {
    font-size: 1.2em;
    margin: 20 auto 10% auto;
    text-align: center;
    text-decoration: none;
}

h2 {
    font-size: 0.8em;
    margin: 20 auto 10% auto;
    text-align: center;
    text-decoration: none;
}

.imgcontainer {
    text-align: center;
    margin: 24px 0 12px 0;
}

img.avatar {
    width: 20%;
    border-radius: 0;
}
</style>