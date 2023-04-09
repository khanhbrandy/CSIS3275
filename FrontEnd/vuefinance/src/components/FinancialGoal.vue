<template>
    <div>
        <br>
        <h4> Create and track a Financial Goal </h4>

        <form>
            <div class="container">


                <label>Name of your goal</label>
                <input type="text" v-model="GoalRequest.name" required>

                <label>Description</label>
                <textarea placeholder="Add some description..." v-model="GoalRequest.description" required> </textarea>

                <label>Target Amount</label>
                <input type="number" v-model="GoalRequest.amount" required>

                <label>Current Amount (How much do you have so far)</label>
                <input type="number" v-model="GoalRequest.currentAmount" required>
                
                <div class="date">
                <label >Deadline</label>
                <input type="date" v-model="GoalRequest.deadline" required>
                </div>
                
                
                

                <div class="clearfix">
                    <button type="button" class="signupbtn" @click="createGoal"> Create Goal </button>
                    <button type="button" class="cancelbtn" @click="showGoals = !showGoals"> View current goals</button>
                </div>


            </div>
        </form>
    </div>
    <div>
        <!--show if the button is clicked-->
        <viewGoals v-if="showGoals" />
    </div>
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
            GoalRequest: { name: '', amount: null, currentAmount: null, description: '' },
            message: '',
            showGoals: false,
        }
    },
    methods: {
        createGoal() {
            const customerId = localStorage.getItem('cid')
            GoalService.createGoal(customerId, this.GoalRequest)
                .then(response => {       // HttpStatus.OK
                    var goal = response.data;
                    console.log(goal)
                    this.message = `Goal created succesfully.`
                })
                .catch(e => {
                    this.message = e.response.data.message;
                    console.log(e.response.data);
                });
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
.createGoal {
    float: left;
    width: 50%;
    margin: auto;
}
</style>