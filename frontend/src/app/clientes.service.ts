import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { TipoRisco } from './tipos-risco.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {
  
  private baseUrl: string = environment.urlBase;
  
  constructor(private _http:HttpClient) { 

  }
  
  getOneCliente(nome: string) {
    var data = this._http.get(this.baseUrl + '/clientes/' + nome);
    if (data) {
      var risco = new TipoRisco(
        data['risco'],
        data['taxaJuros']
      );
  
      var cli = new Cliente(
        data['nome'],
        data['limite'],
        risco
      );
      return cli;
    }
  }

  getClientes(page: number) {
    return this._http.get(this.baseUrl + '/clientes?size=3&page=' + page);
  }

  saveCliente(cli: Cliente){
    return this._http.post(this.baseUrl + '/clientes', cli);
  }

  deleteCliente(nome: string){
    return this._http.delete(this.baseUrl + '/clientes/' + nome);
  }

  updateCliente(cli: Cliente){
    return this._http.put(this.baseUrl + '/clientes/' + cli.nome, cli);
  }
}

export class Cliente {
  nome: string;
  limite: number;
  risco: TipoRisco;
  constructor (nome: string, limite: number, risco: TipoRisco) {
    this.nome = nome;
    this.limite = limite;
    this.risco = risco;
  }
}
