# Testes Não Funcionais - Testes de Segurança

Este projeto é um ambiente **intencionalmente vulnerável**, criado para fins educacionais e testes com ferramentas como OWASP ZAP, SonarQube e outras. Ele permite explorar vulnerabilidades reais em APIs Java Spring Boot, com comparação entre implementações seguras e inseguras.

---

## 🎯 Objetivo do Projeto

- Simular vulnerabilidades reais de aplicações web
- Permitir estudo e demonstração de ataques (SQLi, XSS, IDOR, etc.)
- Integrar facilmente com scanners de segurança
- Exibir diferenças entre práticas seguras e inseguras

---

## 🏗️ Arquitetura

- **Linguagem:** Java 17+
- **Framework:** Spring Boot 3
- **Banco:** H2 (em memória)
- **Build:** Maven

---

## 🚀 Como Executar

### 1️⃣ Pré-requisitos
- Java 17+
- Maven 3+

### 2️⃣ Clonar o repositório
```
git clone <seu-repositorio>
cd security-test
```

### 3️⃣ Executar a aplicação
```
mvn spring-boot:run
```

### 4️⃣ Acessar
- Aplicação: `http://localhost:8081`
- Endpoints inseguros: `http://localhost:8081/insecure`
- Console H2: `http://localhost:8081/h2`

Config H2:
```
JDBC URL: jdbc:h2:mem:security
User: sa
Password:
```

---

## 🗄️ Banco de Dados (data.sql)
Carrega automaticamente:
- `users`
- `comments`

Com dados iniciais para testes.

---

## 🧪 Testes com OWASP ZAP

### Active Scan
Use como URL base:
```
http://localhost:8081/insecure/
```
ZAP detectará:
- SQL Injection
- XSS
- IDOR
- Exposição de dados

---

## 📊 Testes com SonarQube

Executar análise:
```
mvn clean verify sonar:sonar -Dsonar.projectKey=hackme
```

---
