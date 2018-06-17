<template>
    <div id="livros" class="container">
        <table
            class="table table-hover table-condensed table-striped table-bordered">
            <thead>
                <tr>
                    <td style="width: 5%">#</td>
                    <td style="width: 10%">Titulo</td>
                    <td style="width: 10%">Autores</td>
                    <td style="width: 5%">ISBN</td>
                    <td style="width: 10%">Editora</td>
                    <td style="width: 5%">Ano</td>
                    <td style="width: 10%">Edicao</td>
                    <td style="width: 5%">N° Paginas</td>
                    <td style="width: 10%">Area</td>
                    <td style="width: 10%">Tema</td>
                    <td style="width: 10%">Editar</td>
                    <td style="width: 10%">Deletar</td>
                </tr>
            </thead>
            <tbody>
                <tr v-for="livro in livros" :key="livro.id">
                    <td>{{ livro.id }}</td>
                    <td>{{ livro.titulo }}</td>
                    <td>{{ livro.autores }}</td>
                    <td>{{ livro.isbn }}</td>
                    <td>{{ livro.editora }}</td>
                    <td>{{ livro.anoDePublicacao }}</td>
                    <td>{{ livro.edicao }}</td>
                    <td>{{ livro.numeroDePaginas }}</td>
                    <td>{{ livro.area }}</td>
                    <td>{{ livro.tema }}</td>
                    <td><button class="btn btn-warning" v-on:click="(event) => { editar(livro.id) }">Editar</button></td>
                    <td><button class="btn btn-danger" v-on:click="(event) => { deletar(livro.id) }">Deletar</button></td>
                </tr>
            </tbody>
            <tfoot>
                <td colspan="12">
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#modal-livro">Cadastrar Livro</button>
			</td>
            </tfoot>
        </table>
        <div class="modal fade" id="modal-livro" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="form-livro">
                        <div class="modal-header">
                            <h4 class="modal-title">Informações de Livro</h4>
                            <button type="button" class="close" data-dismiss="modal"
                                aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <label for="titulo">Titulo: </label>
                            <input class="form-control" v-model="livro.titulo">
                            <label for="autores">Autores: </label>
                            <input class="form-control" v-model="livro.autores">
                            <label for="isbn">ISBN: </label>
                            <input class="form-control" v-model="livro.isbn">
                            <label for="editora">Editora: </label>
                            <input class="form-control" v-model="livro.editora">
                            <label for="anoDePublicacao">Ano: </label>
                            <input class="form-control" v-model="livro.anoDePublicacao">
                            <label for="edicao">Edição: </label>
                            <input class="form-control" v-model="livro.edicao">
                            <label for="numeroDePaginas">N° Paginas: </label>
                            <input class="form-control" v-model="livro.numeroDePaginas">
                            <label for="area">Area: </label>
                            <input class="form-control" v-model="livro.area">
                            <label for="tema">Tema: </label>
                            <input class="form-control" v-model="livro.tema">
                            <input type="hidden" v-model="livro.id">
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
  name: 'Livros',
  data () {
      return {
        livros: [],
        livro: {
            id: "",
            item_tipo: "LIVRO",
            titulo: "",
            autores: "",
            isbn: "",
            editora: "",
            anoDePublicacao: "",
            edicao: "",
            numeroDePaginas: "",
            area: "",
            tema: ""
        }
      }
  },
  mounted() {
    axios({ method: "GET", url: "http://localhost:8080/livros" }).then(
      result => {
        this.livros = result.data;
      },
      error => {
        console.error(error);
      }
    )
  },
  methods: {
    recarregar() {
        axios({ method: "GET", url: "http://localhost:8080/livros" }).then(
        result => {
            this.livros = result.data;
        },
        error => {
            console.error(error);
      }
    )
    },
    salvar() {
        $('#modal-livro').modal('hide')
        if(this.livro.id == "") {
            axios({ method: "POST", url: "http://localhost:8080/livros",
            data: this.livro, headers: { "content-type": "application/json" }
            }).then(
                result => {
                    this.recarregar();
                    this.livro={
                        id: "",
                        item_tipo: "LIVRO",
                        titulo: "",
                        autores: "",
                        isbn: "",
                        editora: "",
                        anoDePublicacao: "",
                        edicao: "",
                        numeroDePaginas: "",
                        area: "",
                        tema: ""
                    }
                },
            error => {
                console.error(error);
            }
            );
        } else {
            axios({ method: "PUT", url: "http://localhost:8080/livros/" +this.livro.id,
            data: this.livro, headers: { "content-type": "application/json" }
        }).then(
            result => {
            this.recarregar();
            this.livro={
                id: "",
                item_tipo: "LIVRO",
                titulo: "",
                autores: "",
                isbn: "",
                editora: "",
                anoDePublicacao: "",
                edicao: "",
                numeroDePaginas: "",
                area: "",
                tema: ""
           }
        },
        error => {
          console.error(error);
        }
      );
     }
    },
    deletar(id) {
        axios({method: "DELETE", url: "http://localhost:8080/livros/"+id
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
        axios({ method: "GET", url: "http://localhost:8080/livros/"+id
        }).then(
            result => {
            this.livro = result.data;
        },
            error => {
                console.error(error);
            }
        );
        $('#modal-livro').modal('show')
    }
  }
};
</script>
