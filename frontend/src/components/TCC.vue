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
      <div class="modal fade" id="modal-tcc" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="form-tcc">
                        <div class="modal-header">
                          <h4 class="modal-title">Informações do TCC</h4>
                          <button type="button" class="close" data-dismiss="modal"
                              aria-label="Close">
                              <span aria-hidden="true">&times;</span>
                          </button>
                        </div>
                        <div class="modal-body">
                            <label for="titulo">Titulo: </label>
                            <input class="form-control" v-model="tcc.titulo">
                            <label for="autores">Autores: </label>
                            <input class="form-control" v-model="tcc.autores">
                            <label for="orientadores">Orientadores: </label>
                            <input class="form-control" v-model="tcc.orientadores">
                            <label for="tipo">Tipo: </label>
                            <select class="form-control" v-model="tcc.tipo">
                                <option value="MONOGRAFIA">Monografia</option>
                                <option value="TESE">Tese</option>
                                <option value="DISSERTACAO">Dissertacao</option>
                            </select>
                            <label for="anoDeDefesa">Ano: </label>
                            <input class="form-control" v-model="tcc.anoDeDefesa">
                            <label for="local">Local: </label>
                            <input class="form-control" v-model="tcc.local">
                            <input type="hidden" v-model="tcc.id">
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
      $('#modal-tcc').modal('hide')
      if(this.tcc.id == "") {
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
      } else {
        axios({ method: "PUT", url: "http://localhost:8080/tccs/"+this.tcc.id,
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
        }
    },
    deletar(id) {
        axios({method: "DELETE", url: "http://localhost:8080/tccs/"+id
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
        axios({ method: "GET", url: "http://localhost:8080/tccs/"+id
        }).then(
            result => {
            this.tcc = result.data;
        },
            error => {
                console.error(error);
            }
        );
        $('#modal-tcc').modal('show')
    }
  }
};
</script>