## 📘 **Projeto AntiGolpe BC**

### 🏛️ Contexto do Projeto

O **AntiGolpe BC** é um sistema acadêmico desenvolvido com o objetivo de **registrar e monitorar relatos de golpes envolvendo o nome do Banco Central**, como forma de apoio à segurança digital dos cidadãos. Foi idealizado como parte de uma proposta prática, em parceria com o **Banco Bradesco**, abordando fraudes e desinformações que afetam a população brasileira, especialmente por meios digitais.

---

## 🎯 Objetivo

Criar um sistema funcional e intuitivo que:

* Cadastre usuários vítimas ou informantes de tentativas de golpe.
* Registre os relatos com detalhes organizados.
* Alerte o usuário com orientações sobre como agir em caso de fraude.
* Estruture os dados em um banco relacional para futura análise.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem**: Java 
* **GUI**: Swing (interfaces gráficas)
* **Banco de dados**: MySQL
* **Estrutura de projeto**: MVC

---

## 🧱 Estrutura do Projeto

### 1. **Pacotes**

* `VIEW`: Interfaces gráficas (cadastro, relatos e alertas)
* `DTO`: Objetos de transporte de dados (Relatorio, Usuario, Pessoa)
* `DAO`: Classes de acesso ao banco (UsuarioDAO, RelatorioDAO, ConexaoDAO)
* `MODEL`: Classes de mensagens abstratas e suas variações (Mensagem, MensagemAlerta, MensagemConfirmacao)

---

### 2. **Fluxo do Sistema**

1. **Cadastro de Usuário** (`TelaCadastro1`)

   * Coleta nome, CPF, e-mail e telefone.
   * Valida os campos.
   * Salva no banco de dados via `UsuarioDAO`.

2. **Relato de Golpe** (`TelaRelatos2`)

   * Usuário insere data, hora, tipo de contato (WhatsApp, ligação, etc.), assunto e descrição.
   * Dados são validados e salvos via `RelatorioDAO`.

3. **Tela de Alerta** (`TelaAlerta`)

   * Mostra mensagem de confirmação (via `MensagemConfirmacao`) ou alerta (via `MensagemAlerta`).
   * Usa polimorfismo para exibir textos diferentes conforme a situação.

---

## 🧠 Conceitos de Programação Aplicados

### ✅ **POO (Programação Orientada a Objetos)**

* **Herança**:
  A classe `Usuario` herda atributos e comportamentos da classe `Pessoa`.

* **Polimorfismo**:
  A superclasse `Mensagem` possui o método `getTexto()`, sobrescrito em `MensagemAlerta` e `MensagemConfirmacao`, personalizando o texto conforme o contexto.

* **Encapsulamento**:
  As classes `Pessoa`, `Relatorio` e `Usuario` possuem atributos privados e métodos `get`/`set`.

---

## 💾 Banco de Dados

* Nome do banco: `antigolpes_db`
* Tabelas:

  * `usuarios` (nome, cpf, email, telefone)
  * `relatorio` (data, hora, tipo\_contato, tipo\_mensagem, descricao)

---

## 📋 Funcionalidades Detalhadas

| Tela        | Função                                         | Classe                               |
| ----------- | ---------------------------------------------- | ------------------------------------ |
| Cadastro    | Entrada e validação de dados do usuário        | `TelaCadastro1`                      |
| Relato      | Entrada do ocorrido, formato orientado ao caso | `TelaRelatos2`                       |
| Confirmação | Alerta visual personalizado após o envio       | `TelaAlerta` + `MensagemConfirmacao` |
| DAO         | Persistência no MySQL com prepared statements  | `UsuarioDAO`, `RelatorioDAO`         |
| Conexão     | Abertura segura com tratamento de exceções     | `ConexaoDAO`                         |

---

## 🔐 Segurança e Boas Práticas

* Uso de `PreparedStatement` para evitar SQL Injection.
* Validação de campos obrigatórios nas interfaces.
* Orientação ao usuário com feedbacks visuais.

---

## 📌 Considerações Finais

O projeto **AntiGolpe BC** não apenas simula um sistema real de denúncia e monitoramento de fraudes, mas também demonstra habilidades práticas com Java, banco de dados, design de interfaces e arquitetura orientada a objetos.

A proposta acadêmica contribui para o debate sobre segurança digital e responsabilidade institucional, oferecendo um ambiente de aprendizado significativo.

---

## 🔗 Referências

* Documentação Java: [https://docs.oracle.com/javase/8/docs/api/](https://docs.oracle.com/javase/8/docs/api/)
* Banco Central – Golpes:

  * [https://www.bcb.gov.br/meubc/faqs/s/golpes](https://www.bcb.gov.br/meubc/faqs/s/golpes)
  * [https://www.bcb.gov.br/meubc/faqs/p/o-que-fazer-em-caso-de-golpe-fraude-ou-um-crime](https://www.bcb.gov.br/meubc/faqs/p/o-que-fazer-em-caso-de-golpe-fraude-ou-um-crime)
  * [https://www.bcb.gov.br/meubc/faqs/p/golpes-envolvendo-o-nome-do-banco-central](https://www.bcb.gov.br/meubc/faqs/p/golpes-envolvendo-o-nome-do-banco-central)
