# 💼 Sistema Folha de Pagamento

> **Unisociesc** — Curso Superior de Tecnologia em Análise e Desenvolvimento de Sistemas  
> **UC:** Algoritmos e Programação  
> **Avaliação:** A3 — Projeto 1  
> **Período:** 1º Semestre  
> **Aluno:** Vinicius dos Santos Zapella

---

## 📋 Sobre o Projeto

API REST para gerenciamento de folha de pagamento, desenvolvida com **Java + Spring Boot**.

O sistema suporta três tipos de colaboradores, cada um com sua própria regra de cálculo de salário:

| Tipo | Discriminador | Regra de Cálculo |
|---|---|---|
| Padrão | `STANDARD` | `salárioFinal = salárioBase` |
| Comissionado | `COMMISSIONED` | `salárioFinal = salárioBase + (totalVendas * percentualComissão / 100)` |
| Produção | `PRODUCTION` | `salárioFinal = salárioBase + (valorPorPeça * quantidadeProduzida)` |

---

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas bem definida:

```
src/main/java/com/unisociesc/SistemaFolhaPagamento/
│
├── application/
│   ├── dto/                  # Interfaces e records de request/response
│   ├── mapper/               # Conversão entre DTOs e entidades
│   └── usecase/              # Implementação dos casos de uso (Interactor)
│
├── domain/
│   ├── contract/             # Interface CollaboratorUseCase
│   ├── entity/               # Entidades JPA (Collaborator e subtipos)
│   ├── exception/            # Exceções e handler global
│   └── strategy/             # Interface e implementações de cálculo salarial
│
└── infra/
    ├── controller/           # CollaboratorController (REST)
    └── repository/           # CollaboratorRepository (JPA)
```

---

## ⚙️ Padrões e Boas Práticas

- **Strategy Pattern** — cada tipo de colaborador possui sua própria estratégia de cálculo salarial (`StandardSalaryCalculator`, `CommissionedSalaryCalculator`, `ProductionSalaryCalculator`)
- **Polimorfismo com Jackson** — o campo `bond_type` no JSON determina automaticamente o subtipo de colaborador na desserialização
- **Single Table Inheritance** — todos os colaboradores são persistidos em uma única tabela (`collaborator_table`), diferenciados pela coluna `bond_type`
- **Global Exception Handler** — tratamento centralizado de erros com respostas padronizadas
- **Immutable Records** — DTOs implementados como Java records para imutabilidade e segurança

---

## 🚀 Tecnologias

- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- Spring Validation (Bean Validation)
- Hibernate
- Springdoc OpenAPI (Swagger UI)
- Lombok
- JUnit 5
- Mockito
- Maven

---

## 📡 Endpoints

Base URL: `/api/collaborators`

| Método | Endpoint | Descrição | Status de Sucesso |
|---|---|---|---|
| `POST` | `/api/collaborators` | Cadastra um novo colaborador | `201 Created` |
| `GET` | `/api/collaborators` | Lista todos os colaboradores | `200 OK` |
| `PUT` | `/api/collaborators/{id}` | Atualiza um colaborador | `200 OK` |
| `DELETE` | `/api/collaborators/{id}` | Remove um colaborador | `204 No Content` |

### Exemplo de Request — Colaborador Padrão
```json
{
  "bond_type": "STANDARD",
  "registrationNumber": 1,
  "name": "João Silva"
}
```

### Exemplo de Request — Colaborador Comissionado
```json
{
  "bond_type": "COMMISSIONED",
  "registrationNumber": 2,
  "name": "Victoria Lima",
  "totalSales": 5000.0,
  "percentageCommission": 10.0
}
```

### Exemplo de Request — Colaborador de Produção
```json
{
  "bond_type": "PRODUCTION",
  "registrationNumber": 3,
  "name": "Carlos Mendes",
  "valuePerPiece": 15.0,
  "quantityProduced": 100
}
```

### Exemplo de Response (POST/GET/PUT)
```json
{
  "id": 2,
  "bond_type": "COMMISSIONED",
  "name": "Victoria Lima",
  "registrationNumber": 2,
  "baseSalary": 2000.0,
  "extras": 500.0,
  "finalSalary": 2500.0,
  "totalSales": 5000.0,
  "percentageCommission": 10.0
}
```
---

## ⚠️ Respostas de Erro

| Status | Descrição |
|---|---|
| `400 Bad Request` | JSON malformado ou tipo de dado incorreto |
| `404 Not Found` | Colaborador não encontrado |
| `409 Conflict` | Número de matrícula já cadastrado |
| `500 Internal Server Error` | Erro inesperado no servidor |

---

## 🧪 Testes

O projeto possui testes unitários cobrindo todas as camadas principais:

| Classe de Teste | O que testa | Quantidade |
|---|---|---|
| `CollaboratorControllerTest` | Endpoints REST via MockMvc (201, 200, 204, 404) | 8 testes |
| `CollaboratorInteractorTest` | Casos de uso com mocks do repository, mapper e strategies | 9 testes |
| `CollaboratorMapperTest` | Conversão DTO → Entity e Entity → ResponseDTO para os 3 tipos | 9 testes |
| `CommissionedSalaryCalculatorStrategyTest` | Cálculo de comissão: casos normais, vendas zero, comissão zero, 100% | 4 testes |
| `ProductStrategyCalculatorTest` | Cálculo por produção: casos normais, quantidade zero, valor zero | 4 testes |
| `StandardStrategyCalculatorTest` | Sempre retorna salário base, suporte por tipo | 2 testes |

**Total: 36+ testes**

Para rodar os testes:

```bash
./mvnw test
```

---

## 📖 Documentação da API

Com a aplicação rodando, acesse a interface interativa do Swagger:

```
http://localhost:8080/swagger-ui.html
```

---

## ▶️ Como Executar

**Pré-requisitos:** Java 17+ e Maven instalados.

```bash
# Clone o repositório
git clone https://github.com/Vinizapella/Sistema-Folha-Pagamento.git

# Acesse a pasta do projeto (backend)
cd Sistema-Folha-Pagamento-BackEnd

# Execute a aplicação
./mvnw spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

---

## ⚙️ Configuração

No arquivo `src/main/resources/application.properties`, configure o salário base:

```properties
empresa.config.salario-base=2000.0
```

---

## 🔄 Integração com Frontend

O backend está totalmente integrado com o frontend React. Certifique-se de:

1. **CORS habilitado** — O backend aceita requisições do frontend
2. **URL base do backend** — Frontend está configurado para `http://localhost:8080`
3. **ID retornado** — Todas as respostas agora incluem o campo `id` necessário para operações de atualização e exclusão

Exemplo de integração no frontend:

```javascript
// Frontend recebe:
const collaborator = {
  id: 1,                         
  name: "João Silva",
  registrationNumber: 1,
  baseSalary: 2000.0,
  extras: 0.0,
  finalSalary: 2000.0,
  bond_type: "STANDARD"
};

// Delete com o ID:
DELETE /api/collaborators/1  → 204 No Content ✅
```


---

*Desenvolvido por **Vinicius dos Santos Zapella***
