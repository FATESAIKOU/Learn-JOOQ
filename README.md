# Todo API Application

## 技術棧
- Gradle
- Spring Boot 3.4.5
- JOOQ（含 codegen 型別安全）
- Postgres
- Docker + Docker Compose

## 開發與測試流程

### 本地應用程式啟動 & 測試
```sh
# 啟動 postgresdb + JOOQ CodeGen
$ docker-compose up -d db && \
  ./gradlew generateJooq

# Application Launch
$ ./gradlew bootRun or test
```

### 程式編譯
```sh
# 啟動 postgresdb + JOOQ CodeGen
$ docker-compose up -d db && \
  ./gradlew generateJooq && \
  docker-compose down

# Application Launch
$ ./gradlew build
```

### docker-compose 整組啟動
```sh
# 啟動 postgresdb + JOOQ CodeGen
$ docker-compose up -d db && \
  ./gradlew generateJooq

# 啟動 application
$ docker-compose up
```

## 測試用 curl 指令

### 建立 Todo
```sh
curl -X POST http://localhost:8080/api/todos -H 'Content-Type: application/json' -d '{"title":"測試任務","description":"說明","completed":false}'
```

### 查詢 Todo 列表
```sh
curl http://localhost:8080/api/todos
```

### 查詢單一 Todo
```sh
curl http://localhost:8080/api/todos/1
```

### 更新 Todo
```sh
curl -X PUT http://localhost:8080/api/todos/1 -H 'Content-Type: application/json' -d '{"title":"新標題","description":"新說明","completed":true}'
```

#### 刪除 Todo
```sh
curl -X DELETE http://localhost:8080/api/todos/1
```

---

如需 docker-compose 一起啟動 app + db，請參考上方說明。
