# Calendar
This is a calendar template to create API HTTP using java and postgres to save data.

## Structure


- `src/main/java/com/calendar/App.java`: Start point of API HTTP
- `src/main/java/com/calendar/config`: Configs of bank connections
- `src/main/java/com/calendar/exception`: API exceptions
- `src/main/java/com/calendar/http`: Server, routes, utilities HTTP
- `src/main/java/com/calendar/model`: Objects of program
- `src/main/java/com/calendar/repository`: Access to bank
- `src/main/java/com/calendar/service`: Rules and validation

## How to run

### Docker Compose

For run API + PostgreSQL with normal variables:

```bash
docker compose up --build -d app
```

For down containers:

```bash
docker compose down
```

## Endpoints

- `GET /health`
- `GET /api/events`
- `POST /api/products`


## JSON example

`POST /api/events`

```json
{
  "title":"Lear Java",
  "description":"",
  "locate":"Home",
  "start_date":"2026-10-01 14:00:00",
  "end_date":"2026-10-01 17:00:00",
  "is_repeated":true
}
```