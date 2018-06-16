import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import Cursos from '@/components/Cursos'
import Alunos from '@/components/Alunos'
import Anais from '@/components/Anais'
import Jornais from '@/components/Jornais'
import Livros from '@/components/Livros'
import Midias from '@/components/Midias'
import Revistas from '@/components/Revistas'
import TCC from '@/components/TCC'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index
    },
    {
      path: '/cursos',
      name: 'Cursos',
      component: Cursos
    },
    {
      path: '/alunos',
      name: 'Alunos',
      component: Alunos
    },
    {
      path: '/anais',
      name: 'Anais',
      component: Anais
    },
    {
      path: '/jornais',
      name: 'Jornais',
      component: Jornais
    },
    {
      path: '/livros',
      name: 'Livros',
      component: Livros
    },
    {
      path: '/midias',
      name: 'Midias',
      component: Midias
    },
    {
      path: '/revistas',
      name: 'Revistas',
      component: Revistas
    },
    {
      path: '/tcc',
      name: 'TCC',
      component: TCC
    }
  ]
})
