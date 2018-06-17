<template>
    <div id="alunos" class="container">
        <table
            class="table table-hover table-condensed table-striped table-bordered">
            <thead>
                <tr>
                    <td style="width: 5%">#</td>
                    <td style="width: 10%">Nome</td>
                    <td style="width: 5%">Matricula</td>
                    <td style="width: 5%">CPF</td>
                    <td style="width: 5%">RG</td>
                    <td style="width: 10%">Naturalidade</td>
                    <td style="width: 5%">Mãe</td>
                    <td style="width: 10%">Endereço</td>
                    <td style="width: 5%">Telefone</td>
                    <td style="width: 10%">Curso</td>
                    <td style="width: 5%">Ano</td>
                    <td style="width: 5%">Periodo</td>
                    <td style="width: 10%">Editar</td>
                    <td style="width: 10%">Deletar</td>
                </tr>
            </thead>
            <tbody>
                <tr v-for="aluno in alunos" :key="aluno.id">
                    <td>{{ aluno.id }}</td>
                    <td>{{ aluno.nome }}</td>
                    <td>{{ aluno.matricula }}</td>
                    <td>{{ aluno.cpf }}</td>
                    <td>{{ aluno.rg }}</td>
                    <td>{{ aluno.naturalidade }}</td>
                    <td>{{ aluno.nomeDaMae }}</td>
                    <td>{{ aluno.endereco }}</td>
                    <td>{{ aluno.telefone }}</td>
                    <td>{{ aluno.curso.nome }}</td>
                    <td>{{ aluno.ano }}</td>
                    <td>{{ aluno.periodo }}</td>
                    <td><button class="btn btn-warning" v-on:click="(event) => { editar(aluno.id) }">Editar</button></td>
                    <td><button class="btn btn-danger" v-on:click="(event) => { deletar(aluno.id) }">Deletar</button></td>
                </tr>
            </tbody>
            <tfoot>
                <td colspan="14">
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#modal-aluno">Cadastrar Aluno</button>
			</td>
            </tfoot>
        </table>
        <div class="modal fade" id="modal-aluno" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="form-aluno">
                        <div class="modal-header">
                            <h4 class="modal-title">Informações do Aluno</h4>
                            <button type="button" class="close" data-dismiss="modal"
                                aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <label for="nome">Nome: </label>
                            <input class="form-control" v-model="aluno.nome">
                            <label for="cpf">CPF: </label>
                            <input class="form-control" v-model="aluno.cpf">
                            <label for="rg">RG: </label>
                            <input class="form-control" v-model="aluno.rg">
                            <label for="naturalidade">Naturalidade: </label>
                            <input class="form-control" v-model="aluno.naturalidade">
                            <label for="nomeDaMae">Nome da Mãe: </label>
                            <input class="form-control" v-model="aluno.nomeDaMae">
                            <label for="endereco">Endereço: </label>
                            <input class="form-control" v-model="aluno.endereco">
                            <label for="telefone">Telefone: </label>
                            <input class="form-control" v-model="aluno.telefone">
                            <label for="curso">Cursos: </label>
                            <select class="form-control" v-model="aluno.curso">
                                <option v-for="curso in cursos" :key="curso.id" v-bind:value="curso">{{curso.nome}}</option>
                            </select>
                            <label for="ano">Ano: </label>
                            <input class="form-control" v-model="aluno.ano">
                            <label for="periodo">Periodo: </label>
                            <input class="form-control" v-model="aluno.periodo">
                            <input type="hidden" v-model="aluno.id">
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
        alunos: [],
        aluno: {
            id: "",
            nome: "",
            matricula: "",
            cpf: "",
            rg: "",
            naturalidade: "",
            nomeDaMae: "",
            endereco: "",
            telefone: "",
            curso: "",
            ano: "",
            periodo: ""
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
    ),
    axios({ method: "GET", url: "http://localhost:8080/alunos" }).then(
      result => {
        this.alunos = result.data;
      },
      error => {
        console.error(error);
      }
    )
  },
  methods: {
    recarregar() {
        axios({ method: "GET", url: "http://localhost:8080/alunos" }).then(
        result => {
            this.alunos = result.data;
        },
        error => {
            console.error(error);
      }
    )
    },
    salvar() {
        $('#modal-aluno').modal('hide')
        if(this.aluno.id == "") {
            axios({ method: "POST", url: "http://localhost:8080/alunos",
            data: this.aluno, headers: { "content-type": "application/json" }
            }).then(
                result => {
                this.recarregar();
                this.aluno={
                    id: "",
                    nome: "",
                    matricula: "",
                    cpf: "",
                    rg: "",
                    naturalidade: "",
                    nomeDaMae: "",
                    endereco: "",
                    telefone: "",
                    curso: "",
                    ano: "",
                    periodo: ""
            }
            },
            error => {
            console.error(error);
            }
        );
        } else {
            axios({ method: "PUT", url: "http://localhost:8080/alunos/" +this.aluno.id,
            data: this.aluno, headers: { "content-type": "application/json" }
            }).then(
                result => {
                this.recarregar();
                this.aluno={
                    id: "",
                    nome: "",
                    matricula: "",
                    cpf: "",
                    rg: "",
                    naturalidade: "",
                    nomeDaMae: "",
                    endereco: "",
                    telefone: "",
                    curso: "",
                    ano: "",
                    periodo: ""
            }
            },
            error => {
                console.error(error);
            }
        );
        }
    },
    deletar(id) {
        axios({method: "DELETE", url: "http://localhost:8080/alunos/"+id
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
        axios({ method: "GET", url: "http://localhost:8080/alunos/"+id
        }).then(
            result => {
            this.aluno = result.data;
        },
            error => {
                console.error(error);
            }
        );
        $('#modal-aluno').modal('show')
    }
  }
};
</script>
