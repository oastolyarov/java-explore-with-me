# java-explore-with-me
Афиша, где можно предложить какое-либо событие от выставки до похода в кино и набрать компанию для участия в нём.

## ER-diagram
- [main_server](https://github.com/oastolyarov/java-explore-with-me/blob/main/main_server_final.pdf)

- [stat_server](https://github.com/oastolyarov/java-explore-with-me/blob/main/stat_server_final.pdf)


Ссылка на PR https://github.com/oastolyarov/java-explore-with-me/pull/2

# Features

* События и работа с ними - создание, модерация, комментарии;
* Создание пользователей администратором;
* Пользователи разделены на обычных и администраторов;
* Доступен просмотр событий без авторизации;
* Возможность создания и управления категориями;
* Добавление комментариев;
* Отдельный сервис для сбора статистики;
* Создание и управление подборками.

# Архитектура

Приложение состоит из 2 сервисов:

1. основной сервис (работа с событиями, подборками, управление пользователями и т.д.);
2. сервис сбора статистики по просмотрам.

# Инструкция по запуску

```Bash
mvn clean package
mvn install
docker-compose up
```

# API

- [API основного сервиса](https://github.com/oastolyarov/java-explore-with-me/blob/main/ewm-main-service-spec.json)
- [API stats-сервиса](https://github.com/oastolyarov/java-explore-with-me/blob/main/ewm-stats-service-spec.json)







