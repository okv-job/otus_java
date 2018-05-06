import VueRouter from "vue-router";

import Login from "./../components/Login.vue";
import Status from "./../components/Status.vue";

const routes = [{
    path: '/',
    component: Login
},
{
    path: '/status',
    component: Status
}
];

const router = new VueRouter({
    routes
});

export default router;