import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [

    {
      path: '/index',
      name: 'Index',
      component: () => import('../view/index.vue'),
      children:[
        {
          path: '/welcome',
          component: () => import('../view/welcome.vue')
        },
      ]

    }
  ]
})

export default router
