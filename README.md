# Todo API Application

## 技術棧
- Gradle
- Spring Boot 3.4.5
- JOOQ
- Postgres
- Docker + Docker Compose

## 開發與測試流程

### 1. 編譯專案
```sh
./gradlew build
```

### 2. 執行單元測試
```sh
./gradlew test
```

### 3. 啟動 Postgres（僅資料庫）
```sh
docker-compose -f docker-compose-db.yml up -d
```

### 4. 啟動 Application（本地）
```sh
./gradlew bootRun
```

### 5. 測試用 curl 指令

#### 建立 Todo
```sh
curl -X POST http://localhost:8080/api/todos -H 'Content-Type: application/json' -d '{"title":"測試任務","description":"說明","completed":false}'
```

#### 查詢 Todo 列表
```sh
curl http://localhost:8080/api/todos
```

#### 查詢單一 Todo
```sh
curl http://localhost:8080/api/todos/1
```

#### 更新 Todo
```sh
curl -X PUT http://localhost:8080/api/todos/1 -H 'Content-Type: application/json' -d '{"title":"新標題","description":"新說明","completed":true}'
```

#### 刪除 Todo
```sh
curl -X DELETE http://localhost:8080/api/todos/1
```

---

如需 docker-compose 一起啟動 app + db，請再告知。
