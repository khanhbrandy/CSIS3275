<template>
    <div>
        <h1> Select a Goal to edit</h1>
        <table class="table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Target</th>
                    <th>Current Amount</th>
                    <th> Progress </th>
                    <th>Deadline</th>
                    <th>Edit/Delete</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="goal in goals" :key="goal.id">
                    <td>{{ goal.name }}</td>
                    <td>{{ goal.amount }}</td>
                    <td>{{ goal.currentAmount }}</td>
                    <td>{{(goal.currentAmount*100/goal.amount).toFixed(2)}} %</td>
                    <td>{{ goal.deadline }} </td>
                    <!--Use the Function from the parent component FinancialGoal-->
                    <td><span id =edit-icon v-on:click="editGoal(goal.id)">  📝</span><span v-on:click="confirmation(goal.id)" id=delete-icon>  ❌</span></td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>

import GoalService from '../services/GoalService'
export default {

    name: "ViewGoals",
    // PassGoalId: propFunction To load the Goal Data Into The edit Form
     //Signal about Edit/ Create Mode
    props:['PassGoalId', 'edit'],
    
    data() {
        return {
            //A list of goals
            goals: [],
            message:"",
        };
    },
    methods: {
        goalList() {
            const customerId = localStorage.getItem('cid')
            GoalService.viewGoals(customerId)
                .then(response => {
                    {
                        this.goals = response.data.goal;
                        console.log(response.data.goal);

                    }
                })
                .catch(e => {
                    console.log(e)

                });

        },

        deletegoal(gid){
            const customerId = localStorage.getItem('cid')
            GoalService.delete(customerId, gid)
        },
        confirmation(gid){
            let text = "Are you sure you want to delete this Goal?"
            if(confirm(text)==true){
                this.deletegoal(gid)
                alert("Goal has been deleted")
                this.goalList();
            }

        },
        editGoal(gid){
            // Call function prop From parent component
            this.PassGoalId(gid)
            this.setEdit(true)
        },
        // To update The form In the Parent Component
        setEdit(value) {
    this.$emit('edit-changed', value);
  }

    },
    mounted() {
        this.goalList();
    
}
}





</script>

<style scoped>
.table {
  border-collapse: collapse;
  width: 100%;
}
tr:hover {
    background-color: #c7d8d9;
}
.table th,
.table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

.table th {
  background-color: #e9fcff;
  color: #001e28;
}

td #edit-icon,
td #delete-icon {
  display: none;
}

tr:hover #edit-icon,
tr:hover #delete-icon {
  display: inline;
  
}
#edit-icon:hover, #delete-icon:hover{
    font-size: large;

}

</style>