import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

export class TipoRisco {
  tipo: string;
  taxaJuros: number;
  constructor (tipo: string, taxaJuros: number) {
    this.tipo = tipo;
    this.taxaJuros = taxaJuros;
  }
}

@Injectable({
  providedIn: 'root'
})
export class TiposRiscoService {

  private baseUrl: string = "http://localhost:8080/api";

  constructor(private _http:HttpClient) { 
  }

  public buscaTiposRisco() {
    return this._http.get(this.baseUrl + '/riscos');
  }

  public buscaUmTiposRisco(tipo: string) {
    return this._http.get(this.baseUrl + '/riscos/' + tipo);
  }
}
