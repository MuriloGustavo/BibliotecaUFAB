<template>
    <div id="jornais" class="container">
        <table
            class="table table-hover table-condensed table-striped table-bordered">
            <thead>
                <tr>
                    <td style="width: 5%">#</td>
                    <td style="width: 25%">Titulo</td>
                    <td style="width: 25%">Autores</td>
                    <td style="width: 15%">Data</td>
                    <td style="width: 10%">Edicao</td>
                    <td style="width: 10%">Editar</td>
                    <td style="width: 10%">Deletar</td>
                </tr>
            </thead>
            <tbody>
                <tr v-for="jornal in jornais" :key="jornal.id">
                    <td>{{ jornal.id }}</td>
                    <td>{{ jornal.titulo }}</td>
                    <td>{{ jornal.autores }}</td>
                    <td>{{ jornal.data }}</td>
                    <td>{{ jornal.edicao }}</td>
                    <td><button class="btn btn-warning" v-on:click="(event) => { editar(jornal.id) }">Editar</button></td>
                    <td><button class="btn btn-danger" v-on:click="(event) => { deletar(jornal.id) }">Deletar</button></td>
                </tr>
            </tbody>
            <tfoot>
                <td colspan="7">
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#modal-jornal">Cadastrar Jornal</button>
			    </td>
            </tfoot>
        </table>
        
    </div>
</template>

<script>
/* eslint-disable */ 
import axios from "axios";
export default {
  name: 'Jornais',
  data () {
      return {
        jornais: [],
        jornal: {
            id: "",
            item_tipo: "JORNAL",
            titulo: "",
            autores: "",
            data: "",
            edicao: ""
        }
      }
  },
  mounted() {
    axios({ method: "GET", url: "http://localhost:8080/jornais" }).then(
      result => {
        this.jornais = result.data;
      },
      error => {
        console.error(error);
      }
    )
  },
  methods: {
    recarregar() {
        axios({ method: "GET", url: "http://localhost:8080/jornais" }).then(
        result => {
            this.jornais = result.data;
        },
        error => {
            console.error(error);
      }
    )
    },
    salvar() {
        var modal = $("#modal-jornal");
        modal.hide();
        axios({ method: "POST", url: "http://localhost:8080/jornais",
            data: this.jornal, headers: { "content-type": "application/json" }
        }).then(
            result => {
            this.recarregar();
            this.jornal={
                id: "",
                item_tipo: "JORNAL",
                titulo: "",
                autores: "",
                data: "",
                edicao: ""
           }
        },
        error => {
          console.error(error);
        }
      );
    },
    deletar(id) {
        axios({method: "DELETE", url: "http://localhost:8080/jornais/"+id
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
        var modal = $("#modal-jornal");
        axios({ method: "GET", url: "http://localhost:8080/jonais/"+id
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
