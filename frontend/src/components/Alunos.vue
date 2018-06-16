<template>
    <div id="alunos" class="container">
        <table
            class="table table-hover table-condensed table-striped table-bordered">
            <thead>
                <tr>
                    <td style="width: 5%">#</td>
                    <td style="width: 30%">Nome</td>
                    <td style="width: 25%">Area</td>
                    <td style="width: 20%">Tipo</td>
                    <td style="width: 10%">Editar</td>
                    <td style="width: 10%">Deletar</td>
                </tr>
            </thead>
            <tbody>
                <tr v-for="curso in cursos" :key="curso.id">
                    <td>{{ curso.id }}</td>
                    <td>{{ curso.nome }}</td>
                    <td>{{ curso.area }}</td>
                    <td>{{ curso.tipo }}</td>
                    <td><button class="btn btn-warning" v-on:click="(event) => { editar(curso.id) }">Editar</button></td>
                    <td><button class="btn btn-danger" v-on:click="(event) => { deletar(curso.id) }">Deletar</button></td>
                </tr>
            </tbody>
            <tfoot>
                <td colspan="6">
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#modal-curso">Cadastrar Curso</button>
			</td>
            </tfoot>
        </table>
        <div class="modal fade" id="modal-curso" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="form-curso" method="post">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title">Informações do Curso</h4>
                        </div>
                        <div class="modal-body">
                            <label for="nome">Nome: </label>
                            <input class="form-control" v-model="curso.nome">
                            <label for="area">Area: </label>
                            <input class="form-control" v-model="curso.area">
                            <label for="tipo">Tipo: </label>
                            <select class="form-control" v-model="curso.tipo">
                                <option value="GRADUACAO">GRADUACAO</option>
                                 <option value="POSGRADUACAO">POSGRADUACAO</option>
                            </select>
                            <input type="hidden" v-model="curso.id">
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-default" data-dismiss="modal">Cancelar</button>
                            <button id="btn-salvar" type="button" class="btn btn-primary" v-on:click="salvar()">Salvar Informações</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
/* eslint-disable */ 
import axios from "axios";
export default {
  name: 'Alunos',
  data () {
      return {
        cursos: [],
        curso: {
            id: "",
            nome: "",
            area: "",
            tipo: ""
        }
      }
  },
  mounted() {
    axios({ method: "GET", url: "http://localhost:8080/cursos" }).then(
      result => {
        this.cursos = result.data;
      },
      error => {
        console.error(error);
      }
    )
  },
  methods: {
    recarregar() {
        axios({ method: "GET", url: "http://localhost:8080/cursos" }).then(
        result => {
            this.cursos = result.data;
        },
        error => {
            console.error(error);
      }
    )
    },
    salvar() {
        var modal = $("#modal-curso");
        modal.hide();
        axios({ method: "POST", url: "http://localhost:8080/cursos",
            data: this.curso, headers: { "content-type": "application/json" }
        }).then(
            result => {
            this.recarregar();
            this.curso={
                id:"",
                nome:"",
                area:"",
                tipo:""
           }
        },
        error => {
          console.error(error);
        }
      );
    },
    deletar(id) {
        axios({method: "DELETE", url: "http://localhost:8080/cursos/"+id
      }).then(
        result => {
          this.recarregar();
        },
        error => {
          console.error(error);
        }
      );
    },
    editar(id) {
        var modal = $("#modal-curso");
        axios({ method: "GET", url: "http://localhost:8080/cursos/"+id
        }).then(
            result => {
            this.curso = result.data;
        },
            error => {
                console.error(error);
            }
        );
        modal.show();
    }
  }
};
</script>
