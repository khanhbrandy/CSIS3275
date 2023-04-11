import { createWebHistory, createRouter } from "vue-router";
import CustomerLogin from "../components/CustomerLogin.vue";
import CustomerTransaction from "../components/CustomerTransaction.vue";
import CustomerRegister from "../components/CustomerRegister.vue";
import FinancialGoal from "../components/FinancialGoal"
import CustomerReport from "../components/CustomerReport"
import ReminderList from "../components/ReminderList"
import AboutUs from "../components/AboutUs"

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
    },

    {
        path:"/reminder",
        name: "ReminderList",
        component: ReminderList
    },
    {
        path:"/aboutus",
        name: "AboutUs",
        component: AboutUs
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
