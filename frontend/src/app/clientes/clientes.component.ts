import { Component, OnInit } from '@angular/core';
import { TiposRiscoService } from '../tipos-risco.service';
import { ClientesService } from '../clientes.service';
import { TipoRisco } from '../tipos-risco.service';
import { Cliente } from '../clientes.service';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})


export class ClientesComponent implements OnInit {

  private tipoRisco: TipoRisco;
  private tiposRisco: Array<TipoRisco>;
  private cliente: Cliente;

  private clienteForm: FormGroup;

  private page: number = 0;
  private clientes: Array<any>;
  private pages: Array<number>;

  private update: boolean;

  constructor(
    private _tiposRiscoService: TiposRiscoService,
    private _clientesService: ClientesService) {
    this.clienteForm = this.criaFormGroup();
  }

  ngOnInit() {
    this.getTiposRisco();
    this.getClientes();
  }

  createFormGroupWithBuilder(formBuilder: FormBuilder) {
    return formBuilder.group({
      dadosCliente: formBuilder.group({
        nome: '',
        limite: '',
        risco: ''
      })
    });
  }

  criaFormGroup() {
    return new FormGroup({
      dadosCliente: new FormGroup({
        nome: new FormControl('',[
          Validators.minLength(4),
          Validators.pattern("w+"),
          Validators.required
        ]),
        limite: new FormControl('', [
          Validators.required,
          Validators.maxLength(10),
          Validators.min(0.01),
          Validators.pattern("^[0-9]{1-10}(\.[0-9]{1,2})?$")
        ]),
        risco: new FormControl('', [
          Validators.required,
          Validators.pattern("^[ABC]?$")
        ])
      })
    });
  }

  getTiposRisco() {
    this._tiposRiscoService.buscaTiposRisco().subscribe(
      data => {
        this.tiposRisco = Object(data);
      },
      error => {
        console.error(error.error.message);
      }

    );
  }

  get nome(): string {
    return this.clienteForm ? this.clienteForm.get('dadosCliente.nome').value : '';
  }
  get limite(): string {
    return this.clienteForm ? this.clienteForm.get('dadosCliente.limite').value : '';
  }
  get risco(): string {
    return this.clienteForm ? this.clienteForm.get('dadosCliente.risco').value : '';
  }

  setPage(i: number, $event: any) {
    event.preventDefault();
    this.page = i;
    this.getClientes();
  }

  getClientes() {
    this._clientesService.getClientes(this.page).subscribe(
       data => {
        this.clientes = data['content'];
        this.pages = new Array(data['totalPages']);
       },
       error => {
         console.error(error.error.message);
       }
       
    );
  }

  onSubmit() {
    const result: Cliente = Object.assign({}, this.clienteForm.value);
    var c = new Cliente(
      result['dadosCliente']['nome'],
      result['dadosCliente']['limite'],
      result['dadosCliente']['risco']
    );
    if (! this.update) {
      this._clientesService.saveCliente(c).subscribe(
        data => {
          console.log(data);
          this.update = false;
          this.limpaForm();
          this.getClientes();
        },
        error => {
          console.error(error.error.message);
        }
      ) 
    } else {
      this._clientesService.updateCliente(c).subscribe(
        data => {
          console.log(data);
          this.update = false;
          this.limpaForm();
          this.getClientes();
        },
        error => {
          console.error(error.error.message);
        }
      )
    }
  }

  limpaForm() {
    this.clienteForm.reset();
  }

  public alterar(cliente: Cliente) {
    this.update = true;
    console.log("caiu pra alterar o cliente " + JSON.stringify(cliente));
    this.limpaForm();
    this.clienteForm.setValue(
      {dadosCliente: 
        {nome: cliente.nome, limite: cliente.limite, risco: cliente.risco.tipo}
      });

  }

  public remove(cliente: Cliente) {
    console.log("caiu pra remover o cliente " + JSON.stringify(cliente));
    this._clientesService.deleteCliente(cliente.nome).subscribe(
      data => {
        this.getClientes();
      },
      error => {
        console.error(error.error.message);
      }
    ) 
  }

  public nomeSelecionado:any;
  
  public highlightRow(cli: Cliente) {
    this.nomeSelecionado = cli.nome;
  }

  public getFormattedCurrency(valor: number) {
    return new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(valor);
  }

}

