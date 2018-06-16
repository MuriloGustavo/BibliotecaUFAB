<template>
    <div id="midias" class="container">
        <table
            class="table table-hover table-condensed table-striped table-bordered">
            <thead>
                <tr>
                    <td style="width: 5%">#</td>
                    <td style="width: 25%">Titulo</td>
                    <td style="width: 25%">Autores</td>
                    <td style="width: 15%">Tipo</td>
                    <td style="width: 10%">Data</td>
                    <td style="width: 10%">Editar</td>
                    <td style="width: 10%">Deletar</td>
                </tr>
            </thead>
            <tbody>
                <tr v-for="midia in midias" :key="midia.id">
                    <td>{{ midia.id }}</td>
                    <td>{{ midia.titulo }}</td>
                    <td>{{ midia.autores }}</td>
                    <td>{{ midia.tipo }}</td>
                    <td>{{ midia.data }}</td>
                    <td><button class="btn btn-warning" v-on:click="(event) => { editar(midia.id) }">Editar</button></td>
                    <td><button class="btn btn-danger" v-on:click="(event) => { deletar(midia.id) }">Deletar</button></td>
                </tr>
            </tbody>
            <tfoot>
                <td colspan="7">
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#modal-midia">Cadastrar Midia</button>
			</td>
            </tfoot>
        </table>
        
    </div>
</template>

<script>
/* eslint-disable */ 
import axios from "axios";
export default {
  name: 'Midias',
  data () {
      return {
        midias: [],
        midia: {
            id: "",
            item_tipo: "MIDIAELETRONICA",
            titulo: "",
            autores: "",
            tipo: "",
            data: ""
        }
      }
  },
  mounted() {
    axios({ method: "GET", url: "http://localhost:8080/midias" }).then(
      result => {
        this.midias = result.data;
      },
      error => {
        console.error(error);
      }
    )
  },
  methods: {
    recarregar() {
        axios({ method: "GET", url: "http://localhost:8080/midias" }).then(
        result => {
            this.midias = result.data;
        },
        error => {
            console.error(error);
      }
    )
    },
    salvar() {
        var modal = $("#modal-midia");
        modal.hide();
        axios({ method: "POST", url: "http://localhost:8080/midias",
            data: this.midia, headers: { "content-type": "application/json" }
        }).then(
            result => {
            this.recarregar();
            this.midia={
                id: "",
                item_tipo: "MIDIAELETRONICA",
                titulo: "",
                autores: "",
                tipo: "",
                data: ""
           }
        },
        error => {
          console.error(error);
        }
      );
    },
    deletar(id) {
        axios({method: "DELETE", url: "http://localhost:8080/midias/"+id
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
        var modal = $("#modal-midia");
        axios({ method: "GET", url: "http://localhost:8080/midias/"+id
        }).then(
            result => {
            this.midia = result.data;
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
