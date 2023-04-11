<template>
  <div>
    <h1>Select a Reminder to edit</h1>
    <table class="table">
      <thead>
        <tr>
          <th>Name</th>
          <th>Description</th>
          <th>Amount</th>
          <th>Deadline</th>
          <th>Frequency</th>
          <th>Status</th>
          <th>Edit/Delete</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="reminder in reminders" :key="reminder.id">
          <td>{{ reminder.name }}</td>
          <td>{{ reminder.description }}</td>
          <td>{{ reminder.amount }}</td>
          <td>{{ reminder.deadline }}</td>
          <td>{{ reminder.frequency }}</td>
          <td>{{ reminder.status }}</td>
          <!--Use the Function from the parent component FinancialGoal-->
          <td>
            <span id="edit-icon" v-on:click="editReminder(reminder.id)">
              📝</span
            ><span v-on:click="deleteReminder(reminder.id)" id="delete-icon">
              ❌</span
            >
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import ReminderService from "@/services/ReminderService";
export default {
  name: "viewReminders",
  props: ["PassReminderId"],
  data() {
    return {
      reminders: [],
    };
  },
  methods: {
    loadReminders() {
      const customerId = localStorage.getItem("cid");
      ReminderService.viewReminders(customerId)
        .then((response) => {
            console.log(response);
          this.reminders = response.data;
        })
        .catch((e) => console.log(e));
    },
    deleteReminder(rId) {
        const customerId = localStorage.getItem("cid");
        if(this.deleteConfirmation()){
            ReminderService.deleteReminderById(customerId,rId)
        .then((response) => {
            alert("Reminder has been deleted");
            this.loadReminders();
          console.log(response);
        })
        .catch((e) => console.log(e));
        }
        
    },
    deleteConfirmation() {
        let text = "Are you sure you want to delete this Reminder?"
            if(confirm(text)==true){
                return true;
            }
            return false;
    },
    editReminder(rId){
        this.$emit('edit-changed',true);
        this.$emit('set-rid',rId);
        this.PassReminderId(rId);
    },

  },
   mounted() {
        this.loadReminders();
    
}
};
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