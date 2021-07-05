import Vue from 'vue'
import VueRouter from 'vue-router'
import Users from '../views/Users'
import Login from "../views/Login";
import Articles from "../views/Articles";
import Categories from "../views/Categories";
import EditArticle from "../views/EditArticle";
import EditCategory from "../views/EditCategory";
import EditUser from "../views/EditUser";
import NewArticle from "../views/NewArticle";
import NewCategory from "../views/NewCategory";
import Prijava from "../views/Prijava";
import SingleArticle from "../views/SingleArticle";
import MostVisited from "../views/MostVisited";

Vue.use(VueRouter)

const routes = [
    {
        path: '/users',
        name: 'Users',
        component: Users,
        meta: {
            authRequired: true,
        },
    },
    {
        path: '/',
        name: 'Login',
        component: Login,
    },
    {
        path: '/articles',
        name: 'Articles',
        component: Articles,
        meta: {
            authRequired: true,
        }
    },
    {
        path: '/mostVisited',
        name: 'MostVisited',
        component: MostVisited,
        meta: {
            authRequired: true,
        }
    },
    {
        path: '/articles/category/:id',
        component: Articles,
        meta: {
            authRequired: true,
        },
    },
    {
        path: '/articles/tag/:id',
        component: Articles,
        meta: {
            authRequired: true,
        },
    },
    {
        path: '/categories',
        name: 'Categories',
        component: Categories,
        meta: {
            authRequired: true,
        },
    },
    {
        path: '/editArticle/:id',
        name: 'EditArticle',
        component: EditArticle,
        meta: {
            authRequired: true,
        },
    },
    {
        path: '/editCategory/:id',
        name: 'EditCategory',
        component: EditCategory,
        meta: {
            authRequired: true,
        },
    },
    {
        path: '/editUser/:id',
        name: 'EditUser',
        component: EditUser,
        meta: {
            authRequired: true,
        },
    },
    {
        path: '/newArticle',
        name: 'NewArticle',
        component: NewArticle,
        meta: {
            authRequired: true,
        },
    },
    {
        path: '/newCategory',
        name: 'NewCategory',
        component: NewCategory,
        meta: {
            authRequired: true,
        },
    },
    {
        path: '/prijava',
        name: 'Prijava',
        component: Prijava,
        meta: {
            authRequired: true,
        },
    },
    {
        path: '/articles/:id',
        name: 'SingleArticle',
        component: SingleArticle,
        meta: {
            authRequired: true,
        },
    }
]

const router = new VueRouter({
    routes, mode: 'history'
})

router.beforeEach((to, from, next) => {
    if (to.meta.authRequired) {
        const jwt = localStorage.getItem('jwt');

        if (!jwt) {
            next({name: 'Login'});
            return;
        }

        const payload = JSON.parse(atob(jwt.split('.')[1]));
        const expDate = new Date(payload.exp * 1000);
        if (expDate < new Date()) {
            next({name: 'Login'});
            return;
        }
    }

    next();
});

export default router
