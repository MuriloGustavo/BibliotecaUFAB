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
        var modal = $("#modal-livro");
        modal.hide();
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
                tema: ""
           }
        },
        error => {
          console.error(error);
        }
      );
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
        var modal = $("#modal-livro");
        axios({ method: "GET", url: "http://localhost:8080/livros/"+id
        }).then(
            result => {
            this.livro = result.data;
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
