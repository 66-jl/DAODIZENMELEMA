import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path:'/',
      name:'welcome_vue',
      component:() => import('../view/welcome.vue')

    }
  ],
})

export default router
