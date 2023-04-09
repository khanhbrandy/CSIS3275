<template>
    <header>
        <h4> Create and track a Financial Goal </h4>
    </header>
    <section>
        <div>

            <form>
                <div class="container">


                    <label>Name</label>
                    <input type="text" v-model="GoalRequest.name" required>

                    <label>Description</label>
                    <textarea placeholder="Add some description..." v-model="GoalRequest.description" required> </textarea>

                    <label>Target Amount</label>
                    <input type="number" v-model="GoalRequest.amount" required>

                    <label>Current Amount (How much do you have so far)</label>
                    <input type="number" v-model="GoalRequest.currentAmount" required>

                    <div class="date">
                        <label>Deadline</label>
                        <input type="date" v-model="GoalRequest.deadline" required>
                    </div>



                    <h4>{{ message }}</h4>

                    <div class="clearfix">
                        <button type="button" class="signupbtn" @click="createGoal"> Create Goal </button>
                        <button type="button" class="cancelbtn" @click="showGoals = !showGoals"> View current goals</button>
                    </div>


                </div>
            </form>
        </div>
    </section>

    <aside>
        <!--show if the button is clicked-->
        <viewGoals v-if="showGoals" />

    </aside>
</template>
  
<script>

import GoalService from '../services/GoalService'
import viewGoals from '../components/ViewGoals.vue'

export default {
    name: "customerGoal",
    props: [],
    components: {
        viewGoals
    },
    data() {
        return {
            customer: null,
            GoalRequest: { name: '', amount: null, currentAmount: null, description: '', deadline: '' },
            message: '',
            showGoals: false,
        }
    },
    methods: {
        createGoal() {
            const customerId = localStorage.getItem('cid')
            if (this.GoalRequest.name != '' && this.GoalRequest.amount != null && this.GoalRequest.deadline != '') {
                GoalService.createGoal(customerId, this.GoalRequest)
                    .then(response => {       // HttpStatus.OK
                        var goal = response.data;
                        console.log(goal)
                        this.message = "New Financial Goal has been created. You got this!"
                        //Refresh the data after success
                        this.GoalRequest = { name: '', amount: null, currentAmount: null, description: '', deadline: '' }
                    })
                    .catch(e => {
                        this.message = e.response.data.message;
                        console.log(e.response.data);
                        //Refresh the data in the form
                        this.GoalRequest = { name: '', amount: null, currentAmount: null, description: '', deadline: '' }
                    });
            }else {
                this.message = "Fill out the data first!"
            }
           // Hide Goal List
           this.showGoals = false

        },


    },
    mounted() {

    }
}
</script>
  
<style scoped>
.date {
    padding-bottom: 5%;

}

aside {
    padding: 3%;
}
h4{
    padding-top: 2%;
    padding-bottom: 2%;
}

.createGoal {
    float: left;
    width: 50%;
    margin: auto;
}
</style>