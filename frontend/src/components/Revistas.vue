<template>
    <div id="revistas" class="container">
        <table
            class="table table-hover table-condensed table-striped table-bordered">
            <thead>
                <tr>
                    <td style="width: 5%">#</td>
                    <td style="width: 25%">Titulo</td>
                    <td style="width: 25%">Autores</td>
                    <td style="width: 10%">Data</td>
                    <td style="width: 10%">Edicao</td>
                    <td style="width: 5%">N° Paginas</td>
                    <td style="width: 10%">Editar</td>
                    <td style="width: 10%">Deletar</td>
                </tr>
            </thead>
            <tbody>
                <tr v-for="revista in revistas" :key="revista.id">
                    <td>{{ revista.id }}</td>
                    <td>{{ revista.titulo }}</td>
                    <td>{{ revista.autores }}</td>
                    <td>{{ revista.dataDePublicacao }}</td>
                    <td>{{ revista.edicao }}</td>
                    <td>{{ revista.numeroDePaginas }}</td>
                    <td><button class="btn btn-warning" v-on:click="(event) => { editar(revista.id) }">Editar</button></td>
                    <td><button class="btn btn-danger" v-on:click="(event) => { deletar(revista.id) }">Deletar</button></td>
                </tr>
            </tbody>
            <tfoot>
                <td colspan="8">
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#modal-revista">Cadastrar Revista</button>
			</td>
            </tfoot>
        </table>
        <div class="modal fade" id="modal-revista" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="form-revista">
                        <div class="modal-header">
                            <h4 class="modal-title">Informações da Revista</h4>
                            <button type="button" class="close" data-dismiss="modal"
                                aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <label for="titulo">Titulo: </label>
                            <input class="form-control" v-model="revista.titulo">
                            <label for="autores">Autores: </label>
                            <input class="form-control" v-model="revista.autores">
                            <label for="dataDePublicacao">Data: </label>
                            <input type="date" class="form-control" v-model="revista.dataDePublicacao">
                            <label for="edicao">Edição: </label>
                            <input class="form-control" v-model="revista.edicao">
                            <label for="numeroDePaginas">N°  Paginas: </label>
                            <input class="form-control" v-model="revista.numeroDePaginas">
                            <input type="hidden" v-model="revista.id">
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
  name: 'Revistas',
  data () {
      return {
        revistas: [],
        revista: {
            id: "",
            item_tipo: "REVISTA",
            titulo: "",
            autores: "",
            dataDePublicacao: "",
            edicao: "",
            numeroDePaginas: ""
        }
      }
  },
  mounted() {
    axios({ method: "GET", url: "http://localhost:8080/revistas" }).then(
      result => {
        this.revistas = result.data;
      },
      error => {
        console.error(error);
      }
    )
  },
  methods: {
    recarregar() {
        axios({ method: "GET", url: "http://localhost:8080/revistas" }).then(
        result => {
            this.revistas = result.data;
        },
        error => {
            console.error(error);
      }
    )
    },
    salvar() {
        $('#modal-revista').modal('hide')
        if(this.revista.id == "") {
            axios({ method: "POST", url: "http://localhost:8080/revistas",
            data: this.revista, headers: { "content-type": "application/json" }
            }).then(
                result => {
                this.recarregar();
                this.revista={
                    id: "",
                    item_tipo: "REVISTA",
                    titulo: "",
                    autores: "",
                    dataDePublicacao: "",
                    edicao: "",
                    numeroDePaginas: ""
                }
            },
            error => {
            console.error(error);
            }
        );
        } else {
            axios({ method: "PUT", url: "http://localhost:8080/revistas/" +this.revista.id,
            data: this.revista, headers: { "content-type": "application/json" }
            }).then(
                result => {
                this.recarregar();
                this.revista={
                    id: "",
                    item_tipo: "REVISTA",
                    titulo: "",
                    autores: "",
                    dataDePublicacao: "",
                    edicao: "",
                    numeroDePaginas: ""
                }
            },
            error => {
            console.error(error);
            }
        );
        }
    },
    deletar(id) {
        axios({method: "DELETE", url: "http://localhost:8080/revistas/"+id
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
        axios({ method: "GET", url: "http://localhost:8080/revistas/"+id
        }).then(
            result => {
            this.revista = result.data;
        },
            error => {
                console.error(error);
            }
        );
        $('#modal-revista').modal('show')
    }
  }
};
</script>
