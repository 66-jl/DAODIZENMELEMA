import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/login',   // 访问根路径时自动跳到登录页
    },

    {
      path: '/index',
      name: 'Index',
      component: () => import('../view/index.vue'),
      children: [
        {
          path: '/welcome',
          component: () => import('../view/welcome.vue')
        },
        {
          path: '/admin',
          component: () => import('../view/admin/list.vue'),

        },

      ],

    },
    {
      path: '/login',
      name: 'Login',
      component: () => import('../view/login.vue'),
    }
  ]
})

export default router
