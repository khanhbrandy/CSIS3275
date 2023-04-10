import http from "../http-common"

class GoalService{
    //get all goals
    viewGoals(cid){
        return http.get(`/customers/${cid}`)
    }
    //get single goal
    getGoal(customer_id, goal_id){
        return http.get(`/customers/${customer_id}/goals/${goal_id}`)
    }

    //Create a goal
    createGoal(customer_id, goalData){
        return http.post(`/customers/${customer_id}/goals`, goalData)
        
    }

    update(customer_id, goal_id, data){
        return http.put(`/customers/${customer_id}/goals/${goal_id}`, data)
    }

    delete(customer_id, goal_id){
        return http.delete(`/customers/${customer_id}/goals/${goal_id}`)
    }
    
    deleteAll(customer_id){
        return http.delete(`/customers/${customer_id}/goals`)
    }
}

export default new GoalService();
