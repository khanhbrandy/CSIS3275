<template>
    <div>
        
        <h4> {{messageTop}} </h4>


            <form>
                <div class="container">


                <label>Name your goal </label>
                <input type="text" v-model="GoalRequest.name" required>

                    <label>Description</label>
                    <textarea placeholder="Add some description..." v-model="GoalRequest.description" required> </textarea>

                    <label>Target Amount</label>
                    <input type="number" v-model="GoalRequest.amount" required>

                    <label>Current Amount (What you have so far)</label>
                    <input type="number" v-model="GoalRequest.currentAmount" required>

                    <div class="date">
                        <label>Deadline</label>
                        <input type="date" v-model="GoalRequest.deadline" required>
                    </div>



                    <h4>{{ message }}</h4>

                    <div class="clearfix">
                        <!--Render On Create Mode (edit Mode = False)-->
                        <button type="button" class="signupbtn" @click="createGoal" v-if="!edit"> Create Goal </button>
                        <button type="button" class="cancelbtn" @click="showGoals = !showGoals" v-if="!edit"> View current goals</button>
                        <!--Render in edit Mode. Change edit to fals once is clicked-->
                        <button type="but" class="signupbtn" v-if="edit" @click="editGoal()" >Edit Goal</button>
                    </div>


                </div>
            </form>
    </div>

    <aside>
        <!--show All Goals if the button is clicked-->
        <!-- Pass function to child to be called with the right id-->
        <viewGoals v-if="showGoals" :PassGoalId="loadGoal" :edit="edit" v-on:edit-changed="edit = $event" />

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
            messageTop: 'Create and track a Financial Goal',
            message: '',
            // Call the view Goal component
            showGoals: false,
            //Signal about Edit/ Create Mode to pass as prop to child
            edit: false,
            goalid: null
        }
    },
    methods: {
        createGoal() {
            const customerId = localStorage.getItem('cid')
            //Basic Incorrect input handling
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
                alert("Please Fill out the data first!")
            }
           // Hide Goal List
           this.showGoals = false

        },
        //Load a specific Goal to edit
        loadGoal(gid){
            
            this.messageTop ='Edit Financial Goal'
            this.message =''
            //Get Customer ID
            const customer_id = localStorage.getItem('cid')
            //Get Goal From Backend
            GoalService.getGoal(customer_id, gid)
            .then(response =>{
                var goal = response.data
                console.log(goal)
                this.goalid = goal.id
                // Load Goal Information to Edit
                this.GoalRequest.name = goal.name
                this.GoalRequest.amount = goal.amount
                this.GoalRequest.currentAmount = goal.currentAmount
                this.GoalRequest.description = goal.description
                this.GoalRequest.deadline = goal.deadline
            })
            .catch(e=>{
                console(e.response.data)
            })
            
        },
        editGoal(){
            const customer_id = localStorage.getItem('cid')
            //Update the Goal
            GoalService.update(customer_id,this.goalid, this.GoalRequest)
            //Hide the Edit Button
            this.edit = !this.edit
            alert("Goal has been updated")
           // If the user has achived the goal, celebrate
            if (this.GoalRequest.currentAmount > this.GoalRequest.amount || this.GoalRequest.amount == this.GoalRequest.currentAmount){
                alert(`Congratulations ${localStorage.getItem('uname')}, you've achieved your Goal! 🎉🎉`)
            }
             //Hide Show All Goals
            this.showGoals =!this.showGoals
            this.message = ''
            this.messageTop = 'Create and track a Financial Goal'

        }


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