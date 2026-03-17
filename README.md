# 🚀 Spring Boot Multilang JVM: Dynamic Polyglot Engine

Este projeto demonstra a implementação de uma arquitetura **multilinguagem na JVM**, explorando a interoperabilidade nativa entre **Java, Kotlin e Groovy**.

O grande diferencial técnico deste repositório é a execução de regras de negócio dinâmicas em runtime, permitindo alterações em lógicas críticas (como cálculos financeiros ou regras de bônus) **sem a necessidade de um novo deploy da aplicação**.

---

## 🛠️ Tecnologias e Papéis de Linguagem

* **Java 17 (Core & Orchestration):**
  Utilizado para a estrutura do Spring Boot, Controllers e orquestração.

* **Kotlin (Data Layer):**
  Models e DTOs usando *data classes*, com foco em concisão e null-safety.

* **Groovy (Dynamic Scripting):**
  Motor de regras carregado dinamicamente via `GroovyClassLoader`.

* **Spring Boot:**
  Framework base para construção da API REST.

* **Maven:**
  Gerenciamento de dependências e build híbrido (Java/Kotlin).

---

## 🧠 Diferenciais de Arquitetura (Showcase)

Como um desenvolvedor com experiência em sistemas bancários de larga escala, apliquei neste projeto padrões focados em performance e manutenibilidade:

### 1. Dynamic Rule Loading

As regras de bônus ficam em scripts Groovy externos ao artefato compilado.
Isso permite **hot swapping de regras em runtime**, sem necessidade de deploy.

---

### 2. Estratégia de Cache e Performance

Implementação de cache baseada em `lastModified`:

* O script só é recompilado se houver alteração no arquivo
* Evita recompilações desnecessárias
* Previne uso excessivo de **Metaspace da JVM**

---

### 3. Clean Architecture & Service Layer

* Controller "thin" (responsabilidade mínima)
* Lógica isolada na camada de serviço
* Encapsulamento de reflexão e classloader

---

### 4. Injeção de Dependência

* Uso de injeção via construtor
* Service como Singleton gerenciado pelo Spring
* Cache preservado durante o ciclo de vida da aplicação

---

## 📁 Estrutura do Projeto

```text
src/main
 ├── java/.../controller    # Endpoints REST (Java)
 ├── java/.../service       # Engine de regras e cache (Java)
 ├── kotlin/.../model       # DTOs / Models (Kotlin)
 └── groovy/.../rules       # Regras dinâmicas (Groovy)
```

---

## ▶️ Como executar o projeto

Clone o repositório:

```bash
git clone https://github.com/andersonlopes/multilang.git
```

Entre na pasta:

```bash
cd multilang
```

Execute a aplicação:

```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em:

```text
http://localhost:8080
```

---

## 🧪 Exemplo de Uso

### 📌 Endpoint: Cálculo de Bônus (Dinâmico)

Este endpoint demonstra o fluxo completo:

```text
Java → Kotlin → Groovy
```

---

### Request

```http
POST /bonus
```

```json
{
  "nome": "Anderson",
  "salario": 15000
}
```

---

### Response

```json
3000.0
```

---

## 🎯 Objetivo

Explorar a construção de aplicações backend utilizando múltiplas linguagens na JVM com foco em:

* Flexibilidade de regras de negócio
* Redução de deploys
* Arquitetura desacoplada
* Performance e cache inteligente

---

## 👨‍💻 Autor

**Anderson Lopes**
Desenvolvedor Backend com sólida experiência no ecossistema Java (ex-Bradesco),
focado nos estudos em arquiteturas escaláveis, automação e novas tecnologias da JVM.
