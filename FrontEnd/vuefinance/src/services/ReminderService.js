import http from "../http-common";

class ReminderService{

    viewReminders(cId){
        return http.get(`/${cId}/reminders`);
    }
    getReminderById(reminderId,cId){
        return http.get(`/${cId}/reminders/${reminderId}`);
    }
    createReminder(cId,reminderData){
        return http.post(`/${cId}/reminders`,reminderData);
    }

    updateReminder(cId,reminderData,rId){
        return http.put(`/${cId}/reminders/${rId}`,reminderData);
    }

    deleteReminderById(cId,rId){
        return http.delete(`/${cId}/reminders/${rId}`);
    }
    deleteAllReminder(cId){
        return http.delete(`/${cId}/reminders`);
    }
}
export default new ReminderService();