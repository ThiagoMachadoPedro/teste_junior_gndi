
// EMPRESA

end-point para salvar uma empresa POST
http://localhost:8080/tgid/empresas
{
  "nome": "fulana",
  "numeroConta": "2424",
   "email": "thi@gmail.com",
  "saldo": 1000.00,
  "cnpj": "12345678901234",
  "taxas": [
    {
      "nome": "Taxa1",
      "porcentagem": 1.5
    },
    {
      "nome": "Taxa2",
      "porcentagem": 1.4
    }
  ]
}


buscar todas empresas GET
http://localhost:8080/tgid/empresas

buscar empresas por ID GET
http://localhost:8080/tgid/empresas{id}


buscar empresas deletar empresa DELETE
http://localhost:8080/tgid/empresas{id}




// CLIENTE

end-point para salvar uma cliente POST
http://localhost:8080/tgid/clientes
{
  "nome": "veronico",
  "email": "ver@gmail.com",
  "numeroConta": "12345gfg561",
  "saldo": 1000.00,
  "cpf": "123.456.789-01",
  "taxas": [
    {
      "nome": "Taxa1",
      "porcentagem": 0.5
    }
  ]
}

buscar todas clientes GET
http://localhost:8080/tgid/clientes

buscar cliente por ID GET
http://localhost:8080/tgid/clientes{id}


buscar cliente deletar DELETE
http://localhost:8080/tgid/clientes{id}



// end point de taxa

end-point para salvar uma taxa POST
http://localhost:8080/tgid/taxas
{
  "nome": "taxa_transacao",
  "porcentagem": 2.5
}


buscar todas taxas GET
http://localhost:8080/tgid/taxas

buscar taxas por ID GET
http://localhost:8080/tgid/taxas{id}


buscar taxas deletar DELETE
http://localhost:8080/tgid/taxas{id}





// end point de tranferencia de Saldo

// tranferencias de Empresa pra cliente
http://localhost:8080/tgid/tranferencias/empresa?idRemetente=2&idDestinatario=1
{
  "valor": 100.00
}


// tranferencias de Cliente para Empresa
http://localhost:8080/tgid/tranferencias/cliente?idRemetente=2&idDestinatario=1
{
  "valor": 100.00
}



// ATENÇÂO EU COMENTEI OS METODOS DE ENVIAR E-MAIL, PARA TESTAR APLICAÇÃO, PARA USAR SO DESCOMENTAR E COLOCAR A CHAVE DO EMAIL, PARA ITILIZAR
