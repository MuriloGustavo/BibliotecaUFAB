<template>
    <div id="tcc" class="container">
      <table
            class="table table-hover table-condensed table-striped table-bordered">
            <thead>
              <tr class="text-center">
                <td style="width: 5%">#</td>
                <td style="width: 15%">Titulo</td>
                <td style="width: 20%">Autores</td>
                <td style="width: 10%">Orientadores</td>
                <td style="width: 10%">Tipo</td>
                <td style="width: 10%">Ano Defesa</td>
                <td style="width: 10%">Local</td>
                <td style="width: 10%">Editar</td>
                <td style="width: 10%">Deletar</td>
              </tr>
          </thead>
          <tbody>
            <tr v-for="tcc in tccs" :key="tcc.id">
              <td>{{ tcc.id }}</td>
              <td>{{ tcc.titulo }}</td>
              <td>{{ tcc.autores }}</td>
              <td>{{ tcc.orientadores }}</td>
              <td>{{ tcc.tipo }}</td>
              <td>{{ tcc.anoDeDefesa }}</td>
              <td>{{ tcc.local }}</td>
              <td><button class="btn btn-warning" v-on:click="(event) => { editar(tcc.id) }">Editar</button></td>
              <td><button class="btn btn-danger" v-on:click="(event) => { deletar(tcc.id) }">Deletar</button></td>
          </tr>
          </tbody>
          <tfoot>
            <td colspan="9">
              <button type="button" class="btn btn-primary" data-toggle="modal"
              data-target="#modal-tcc">Cadastrar TCC</button>
            </td>
          </tfoot>
      </table>
    </div>
</template>

<script>
/* eslint-disable */ 
import axios from "axios";
export default {
  name: 'TCC',
  data () {
      return {
        tccs: [],
        tcc: {
            id: "",
            item_tipo: "TRABALHOSDECONCLUSAO",
            titulo: "",
            autores: "",
            orientadores: "",
            tipo: "",
            anoDeDefesa: "",
            local: ""
        }
      }
  },
  mounted() {
    axios({ method: "GET", url: "http://localhost:8080/tccs" }).then(
      result => {
        this.tccs = result.data;
      },
      error => {
        console.error(error);
      }
    )
  },
  methods: {
    recarregar() {
        axios({ method: "GET", url: "http://localhost:8080/tccs" }).then(
        result => {
            this.tccs = result.data;
        },
        error => {
            console.error(error);
      }
    )
    },
    salvar() {
        var modal = $("#modal-tcc");
        modal.hide();
        axios({ method: "POST", url: "http://localhost:8080/tccs",
            data: this.tcc, headers: { "content-type": "application/json" }
        }).then(
            result => {
              this.recarregar();
              this.anal={
                id: "",
                item_tipo: "TRABALHOSDECONCLUSAO",
                titulo: "",
                autores: "",
                orientadores: "",
                tipo: "",
                anoDeDefesa: "",
                local: ""
            }
        },
        error => {
          console.error(error);
        }
      );
    },
    deletar(id) {
        axios({method: "DELETE", url: "http://localhost:8080/anais/"+id
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
        
    }
  }
};
</script>