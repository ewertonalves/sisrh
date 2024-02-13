# SisRH

## **Metodo:** `cadastrar` ##
Este método é responsável por cadastrar um novo funcionário no sistema.

**Método HTTP:** POST
* **URL:** /api/v1/sisrh/funcionario/cadastrar
* **Parâmetros:** O corpo da solicitação deve conter os detalhes do funcionário a ser cadastrado no formato JSON.

  Exemplo de corpo da solicitação:

  ```
  {
    "nome": "Fulano de tal"
    "rg": "123456"
    "cpf": "12345678901",
    "dtNascimento": 01/01/2000,
    "emailCorp": "exemplo@exemplo.com.br",
    "funcao": "Desenvolvedor de Software",
    "dtAdmissao": "02/05/2019",
    "dtDemissao": null
    "salario": 5000.00
  }
  ```

## **Método:** `atualizar` ##
Este método é responsável por atualizar os dados de um funcionário existente com base no CPF.

* **Método HTTP:** PUT
* **URL:** /api/v1/sisrh/funcionario/{cpf}
* **Parâmetros:**
  
  1. **{cpf}:** A atualização do cadastro é feita a parti da busca por CPF.
  2. **Corpo da solicitação:** Deve conter os novos detalhes do funcionário a serem atualizados no formato JSON.


## **Método:** `deletar` ##
Este método é responsável por excluir um funcionário do sistema com base no ID.

* **Método HTTP:** DELETE
* **URL:** /api/v1/sisrh/funcionario/{id}
* **Parâmetros:**

    1. **{id}:** O ID do funcionário que deseja excluir.
 

## **Método:** `pesquisar` ##
Este método é responsável por pesquisar e recuperar os detalhes de um funcionário com base no CPF.

* **Método HTTP:** GET
* **URL:** /api/v1/sisrh/funcionario/{cpf}
* **Parâmetros:**

  1.**{cpf}:** O CPF do funcionário que deseja pesquisar.


## Observações Gerais ##

* Para acessar esses endpoints via Postman ou qualquer outra ferramenta de cliente HTTP, certifique-se de incluir os dados necessários no corpo da solicitação (para métodos POST e PUT) ou na URL (para métodos DELETE e GET).
* Todos os endpoints respondem com JSON contendo os dados do funcionário ou mensagens de erro em caso de falha na solicitação.
* Certifique-se de configurar corretamente o cabeçalho Content-Type para application/json ao enviar solicitações POST e PUT.
