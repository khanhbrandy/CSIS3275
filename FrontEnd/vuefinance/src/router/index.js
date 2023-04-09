import { createWebHistory, createRouter } from "vue-router";
import CustomerLogin from "../components/CustomerLogin.vue";
import CustomerTransaction from "../components/CustomerTransaction.vue";
import CustomerRegister from "../components/CustomerRegister.vue";
import FinancialGoal from "../components/FinancialGoal"
import CustomerReport from "../components/CustomerReport"

const routes = [
    {
        path: "/",
        alias: "/login",
        name: "login",
        component: CustomerLogin
    },
    {
        path: "/transaction",
        alias: "/transaction",
        name: "transaction",
        component: CustomerTransaction
    },
    {
        path: "/register",
        alias: "/register",
        name: "register",
        component: CustomerRegister
    },
    {
        path: "/report",
        alias: "/report",
        name: "report",
        component: CustomerReport
    },

    {
        path:"/goals",
        name: "FinancialGoals",
        component: FinancialGoal
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
