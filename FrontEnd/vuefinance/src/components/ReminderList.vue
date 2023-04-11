<template>
    <div>

        <h1> Create Reminders </h1>

        <form>
            <div class="container">


                <label>Name</label>
                <input type="text" v-model="ReminderRequest.name" required>

                <label>Description</label>
                <textarea placeholder="Add some description..." v-model="ReminderRequest.description" required> </textarea>

                <label>Amount</label>
                <input type="number" v-model="ReminderRequest.amount" required>

                <div class="date">
                <label >Deadline</label>
                <input type="date" v-model="ReminderRequest.deadline" required>
                </div>

                <label>Frequency</label>
                <select name="frequency" v-model="ReminderRequest.frequency" >
                    <option value="weekly">weekly</option>
                    <option value="bi-Weekly">Bi-Weekly</option>
                    <option value="monthly">Monthly</option>
                </select>

                <label>Status</label>
                <select name="status" v-model="ReminderRequest.status" >
                    <option value="pending">Pending</option>
                    <option value="completed">Completed</option>
                </select>
                
                <div class="clearfix">
                    <button type="button" class="signupbtn" @click="createReminder" v-if="!edit"> Create Reminder </button>
                    <button type="button" class="cancelbtn" @click="showReminders = !showReminders" v-if="!edit"> View current Reminders</button>
                    <button type="button" class="signupbtn" @click="editReminder" v-if="edit"> Edit Reminder </button>
                </div>


            </div>
        </form>
    </div>
    <div>
        <!--show if the button is clicked-->
        <viewReminders v-if="showReminders" :PassReminderId="loadReminder" v-on:edit-changed="edit = $event" v-on:set-rid="rId = $event" />
    </div>
</template>

<script>
import ReminderService from '../services/ReminderService'
import viewReminders from "../components/ViewReminders.vue"
export default{
    name:"ReminderList",
    props: [],
    components: {
         viewReminders
    },
    data() {
        return {
            customer: null,
            ReminderRequest: { name: '', amount: null, description: '' ,deadline:'', status:'', frequency:'',createdAt:'1111111'},
            message: '',
            showReminders: false,
            edit:false,
            rId:''
        }
    },
    methods: {
        createReminder() {
            const customerId = localStorage.getItem('cid');
            console.log(this.ReminderRequest);

            ReminderService.createReminder(customerId, this.ReminderRequest)
                .then(response => {       // HttpStatus.OK
                    var reminder = response.data;
                    console.log(reminder)
                    this.message = `Reminder created succesfully.`
                })
                .catch(e => {
                    this.message = e.response.data.message;
                    console.log(e.response.data);
                });
        },
        loadReminder(rId){
            const customer_id = localStorage.getItem('cid')
            
            ReminderService.getReminderById(rId,customer_id)
            .then(response =>{
                var reminder = response.data
                console.log(reminder)
                this.reminderId = reminder.id
               
                this.ReminderRequest.name = reminder.name
                this.ReminderRequest.amount = reminder.amount
                this.ReminderRequest.description = reminder.description
                this.ReminderRequest.deadline = reminder.deadline
                this.ReminderRequest.status = reminder.status
                this.ReminderRequest.frequency = reminder.frequency
            })
            .catch(e=>{
                console(e.response.data)
            })
        },
        editReminder(){
            const customer_id = localStorage.getItem('cid')
            //Update the Reminder
            ReminderService.updateReminder(customer_id, this.ReminderRequest,this.rId)
            //Hide the Edit Button
            this.edit = !this.edit
            alert("Reminder has been updated")
            
            this.showReminders =!this.showReminders
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
.createGoal {
    float: left;
    width: 50%;
    margin: auto;
}
</style>