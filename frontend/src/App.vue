<template>
  <div id="app">
    <div>
      <b-navbar type="dark" variant="primary" toggleable>
        <b-navbar-toggle target="nav_dropdown_collapse"></b-navbar-toggle>
        <b-collapse is-nav id="nav_dropdown_collapse">
          <b-navbar-brand href="/">Biblioteca UFAB</b-navbar-brand>
          <b-navbar-nav>
            <b-nav-item href="/#/cursos">Cursos</b-nav-item>
            <b-nav-item href="/#/alunos">Alunos</b-nav-item>
            <!-- Navbar dropdowns -->
            <b-nav-item-dropdown text="Itens" right>
              <b-dropdown-item href="/#/anais">Anais</b-dropdown-item>
              <b-dropdown-item href="/#/jornais">Jornais</b-dropdown-item>
              <b-dropdown-item href="/#/livros">Livros</b-dropdown-item>
              <b-dropdown-item href="/#/midias">Midias</b-dropdown-item>
              <b-dropdown-item href="/#/revistas">Revistas</b-dropdown-item>
              <b-dropdown-item href="/#/tcc">TCC</b-dropdown-item>
            </b-nav-item-dropdown>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </div>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>
