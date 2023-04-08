<template>
    <div>
        <table class="table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Target</th>
                    <th>Current Amount</th>
                    <th> Progress </th>
                    <th>Deadline</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="goal in goals" :key="goal.id">
                    <td>{{ goal.name }}</td>
                    <td>{{ goal.amount }}</td>
                    <td>{{ goal.currentAmount }}</td>
                    <td>{{(goal.currentAmount*100/goal.amount).toFixed(2)}} %</td>
                    <td>{{ goal.deadline }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import GoalService from '../services/GoalService'
export default {

    name: "ViewGoals",
    data() {
        return {
            //A list of goals
            goals: [],
            currentGoal: null,
            currentIndex: -1,
            title: ""

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
        refreshList() {

            this.goalList();
            this.currentTutorial = null;
            this.currentIndex = -1;
        },

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

.table th,
.table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

.table th {
  background-color: #00da80;
  color: #001e28;
}
</style>