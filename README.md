# 💰 Gestor Financeiro

Uma aplicação web simples desenvolvida para estudo da linguagem **Java** e do ecossistema **Spring Boot**, focada no gerenciamento de finanças pessoais com registro de entradas e saídas.

---

## 📌 Funcionalidades

- **Gerenciamento de Lançamentos:**
  - Cadastrar novas **entradas** (receitas) e **saídas** (despesas).
  - Editar lançamentos existentes.
  - Excluir lançamentos.
- **Painel de Controle (Dashboard):**
  - Visualização do total de **Entradas**.
  - Visualização do total de **Saídas**.
  - Cálculo automático do **Saldo Final** (Entradas - Saídas).

---

## 🛠️ Tecnologias Utilizadas

### **Back-end**
- **Java** (Versão 17 ou superior)
- **Spring Boot** (Web, Data JPA)
- **Lombok** (Para redução de código boilerplate como Getters, Setters, Constructors)
- **MySQL** (Banco de dados relacional)

### **Front-end**
- **HTML5**
- **CSS3**
- **JavaScript (ES6+)** (Consumo da API REST via `fetch`)

---

## 📋 Pré-requisitos

Antes de começar, certifique-se de ter instalado em sua máquina:

- [Java JDK 17+](https://www.oracle.com/java/technologies/downloads/)
- [Maven](https://maven.apache.org/) (ou utilizar o wrapper `./mvnw` do projeto)
- [MySQL Server](https://dev.mysql.com/downloads/installer/)

---

## ⚙️ Configuração e Instalação

### 1. Clonar o repositório

```bash
git clone [https://github.com/seu-usuario/gestor-financeiro.git](https://github.com/seu-usuario/gestor-financeiro.git)
cd gestor-financeiro
