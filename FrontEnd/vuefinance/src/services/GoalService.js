import http from "../http-common"

class GoalService{
    viewGoals(cid){
        return http.get("/customers/" + cid)
    }
    createGoal(customer_id, goalData){
        return http.post(`/customers/${customer_id}/goals`, goalData)
        
    }
}

export default new GoalService();
