<template>
    <div id="anais" class="container">
      <table
            class="table table-hover table-condensed table-striped table-bordered">
            <thead>
              <tr class="text-center">
                <td style="width: 5%">#</td>
                <td style="width: 15%">Titulo</td>
                <td style="width: 20%">Autores</td>
                <td style="width: 10%">Tipo</td>
                <td style="width: 10%">Congresso</td>
                <td style="width: 10%">Ano</td>
                <td style="width: 10%">Local</td>
                <td style="width: 10%">Editar</td>
                <td style="width: 10%">Deletar</td>
              </tr>
          </thead>
          <tbody>
            <tr v-for="anal in anais" :key="anal.id">
              <td>{{ anal.id }}</td>
              <td>{{ anal.titulo }}</td>
              <td>{{ anal.autores }}</td>
              <td>{{ anal.tipo }}</td>
              <td>{{ anal.nomeDoCongresso }}</td>
              <td>{{ anal.anoDePublicacao }}</td>
              <td>{{ anal.local }}</td>
              <td><button class="btn btn-warning" v-on:click="(event) => { editar(anal.id) }">Editar</button></td>
              <td><button class="btn btn-danger" v-on:click="(event) => { deletar(anal.id) }">Deletar</button></td>
          </tr>
          </tbody>
          <tfoot>
            <td colspan="9">
              <button type="button" class="btn btn-primary" data-toggle="modal"
              data-target="#modal-anais">Cadastrar Anais</button>
            </td>
          </tfoot>
      </table>
      <div class="modal fade" id="modal-anais" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="form-anais">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title">Informações de Anais</h4>
                        </div>
                        <div class="modal-body">
                            <label for="titulo">Titulo: </label>
                            <input class="form-control" v-model="anal.titulo">
                            <label for="autores">Autores: </label>
                            <input class="form-control" v-model="anal.autores">
                            <label for="tipo">Tipo: </label>
                            <select class="form-control" v-model="anal.tipo">
                                <option value="ARTIGO">Artigo</option>
                                <option value="POSTER">Poster</option>
                                <option value="RESUMO">Resumo</option>
                            </select>
                            <label for="nomeDoCongresso">Congresso: </label>
                            <input class="form-control" v-model="anal.nomeDoCongresso">
                            <label for="anoDePublicacao">Ano: </label>
                            <input class="form-control" v-model="anal.anoDePublicacao">
                            <label for="local">Local: </label>
                            <input class="form-control" v-model="anal.local">
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
  name: 'Anais',
  data () {
      return {
        anais: [],
        anal: {
            id: "",
            item_tipo: "ANAISDECONGRESSO",
            titulo: "",
            autores: "",
            tipo: "",
            nomeDoCongresso: "",
            anoDePublicacao: "",
            local: ""
        }
      }
  },
  mounted() {
    axios({ method: "GET", url: "http://localhost:8080/anais" }).then(
      result => {
        this.anais = result.data;
      },
      error => {
        console.error(error);
      }
    )
  },
  methods: {
    recarregar() {
        axios({ method: "GET", url: "http://localhost:8080/anais" }).then(
        result => {
            this.anais = result.data;
        },
        error => {
            console.error(error);
      }
    )
    },
    salvar() {
        var modal = $("#modal-anais");
        modal.hide();
        axios({ method: "POST", url: "http://localhost:8080/anais",
            data: this.anal, headers: { "content-type": "application/json" }
        }).then(
            result => {
              this.recarregar();
              this.anal={
                id: "",
                item_tipo: "ANAISDECONGRESSO",
                titulo: "",
                autores: "",
                tipo: "",
                nomeDoCongresso: "",
                anoDePublicacao: "",
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