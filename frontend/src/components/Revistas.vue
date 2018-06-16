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
        var modal = $("#modal-revista");
        modal.hide();
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
        var modal = $("#modal-revista");
        axios({ method: "GET", url: "http://localhost:8080/midias/"+id
        }).then(
            result => {
            this.revista = result.data;
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
