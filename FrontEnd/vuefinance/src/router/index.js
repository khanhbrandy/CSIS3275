import { createWebHistory, createRouter } from "vue-router";
import CustomerLogin from "../components/CustomerLogin.vue";

const routes = [
    {
        path: "/",
        alias: "/login",
        name: "login",
        component: CustomerLogin
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
