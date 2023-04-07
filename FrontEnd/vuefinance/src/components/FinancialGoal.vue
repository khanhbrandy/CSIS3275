<template>
    <div>

        <h1>Create and track a Financial Goal</h1>

        <form>
            <label>Name</label>
            <input type="text" v-model="GoalRequest.name" required>

            <label>Description</label>
            <input type="text" v-model="GoalRequest.description" required>

            <label>Target Amount</label>
            <input type="number" v-model="GoalRequest.amount" required>

            <label>Current Amount (How much do you have so far)</label>
            <input type="number" v-model="GoalRequest.currentAmount" required>

            <label>Deadline</label>
            <input type="date" v-model="GoalRequest.deadline" required>

            <div class="clearfix">
                <button type="button" class="createGoal" @click="createGoal"> Create Goal </button>
                <button type="button" class="viewGoal" @click="viewGoals"> View current goals</button>
            </div>

        </form>
    </div>
</template>
  
<script>

import GoalService from '../services/GoalService'

export default {
    name: "customerGoal",
    data() {
        return {
            customer: null,
            GoalRequest: { name: '', amount: null, currentAmount: null, description: '' },
            message: ''
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
        viewGoals(){
            const customerId = localStorage.getItem('cid')
            GoalService.viewGoals(customerId)
            .then(response =>{
                var goal = response.data;
                console.log(goal)
                

            })
            .catch(e=>{
                this.message = "error " + e
                
            })
        }


    }
    ,
    mounted() {

    }
}
</script>
  
<style scoped>
.viewGoal, .createGoal {
    float: left;
    width: 50%;
    margin: auto;
}
</style>