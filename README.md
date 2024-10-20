## Autenticação e Cadastro de Estoque em Memória

Esse repositório contém o código de um exercício da disciplina "IMD0409 - DESENVOLVIMENTO DE SISTEMAS WEB II - T02 (2024.2)" da minha graduação em BTI.

<details>
  <summary>Descrição da atividade:</summary>

  ## Objetivo
  Criar uma aplicação web com Spring MVC que permita o cadastro de itens em um estoque em memória, com autenticação de usuários utilizando Spring Security.
  ## Funcionalidades
  ### Autenticação: 
  - ☑️ A aplicação deve ter uma página de login para usuários se autenticarem.
  - ☑️ Utilize Spring Security para proteger as funcionalidades de cadastro de estoque, permitindo acesso apenas a usuários autenticados.
  - ☑️ Crie dois usuários em memória: "admin" com senha "admin" e "user" com senha "user".
  - ☑️ O usuário "admin" terá acesso a todas as funcionalidades.
  - ☑️ O usuário "user" terá acesso apenas à visualização do estoque.
  ### Cadastro de Estoque:
  - ☑️ Crie um formulário para cadastrar novos itens no estoque, contendo campos para nome, descrição e quantidade.
  - ☑️ Implemente a lógica para salvar os itens em uma lista em memória.
  - ☑️ Crie uma página para listar os itens cadastrados no estoque.
  - ☑️ Permita que o usuário "admin" possa editar e remover itens do estoque.
  ### Bônus:
  - ☑️ Implemente paginação na listagem de itens do estoque.
  - ☑️ Adicione validação nos campos do formulário de cadastro.
  - ☑️ Crie testes unitários para as classes da aplicação.
  - ☑️ Implemente a funcionalidade de logout.
  - ☑️ Utilize um banco de dados para persistir os dados do estoque.
  ## Dicas fornecidas
  - Utilize o Spring Initializr para criar o projeto base.
  - Configure o Spring Security para usar autenticação em memória.
  - Crie um controlador para lidar com as requisições relacionadas ao cadastro de estoque.
  - Utilize Thymeleaf para criar as views da aplicação.
  - Utilize o `@Controller, @RequestMapping, @GetMapping, @PostMapping, @ModelAttribute` e outras anotações do Spring MVC para mapear as requisições e processar os dados.
  - Utilize o UserDetailsService e PasswordEncoder do Spring Security para configurar a autenticação.
  - Utilize as tags `<sec:authorize>` do Spring Security para controlar o acesso às funcionalidades da aplicação.
  
</details>
