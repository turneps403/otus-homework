#### задание
Сделать простейший **RESTful CRUD** по созданию, удалению, просмотру и обновлению пользователей.
Пример API - https://app.swaggerhub.com/apis/otus55/users/1.0.0 yaml [здесь](https://github.com/turneps403/otus-homework/blob/HWA-02/otus55-users-1.0.0-resolved.yaml)

1. Запилить REST crud сервис согласно OpenAPI https://app.swaggerhub.com/apis/otus55/users/1.0.0 yaml [здесь](https://github.com/turneps403/otus-homework/blob/HWA-02/otus55-users-1.0.0-resolved.yaml)
1. Организовать первоначальную миграцию (создать юзера, базу и таблицу)
1. Поднимать все под k8s, helm обязательно
    2. Жедательно использовать официальный Chart для БД
    2. Ingress-ы должны также вести на url ```arch.homework/otusapp/{student-name}/*```
1. Реализовать тестирование через Postman

#### структура файлов
```
├── Dockerfile - мой докер-файл для app
├── PostgreSQLDockerfile - это ручная сборка image БД с миграцией
├── OTUS-HWA-02.postman_collection.json
├── homework_db.sql - это миграционный sql
├── k8s - папка с манифестами для запуска через kubectl
├── hw02chart - папка для запуска через helm
├── hw02helmfile - папка для запуска через helmfile
```
#### что делал
###### приложенька
Вначале сделал приложение на Spring framework и Postgresql. 
Для реализации OpenAPI использовал либу springdoc-openapi-ui.
*Подводные камни*: генератор openapi из yaml в java переливает с использованием 
устаревшей библиотеки, которая саппортит только OpenAPI 2.x, пришлось отказаться
*Проверки*: ```./mvnw package && java -jar target/gs-spring-boot-docker-0.1.0.jar```
###### чистый Docker
Написан ```Dockerfile``` для сборки image, после вызова ```./mvnw package```
Image залит на DockerHub [здесь](https://hub.docker.com/r/turneps403/homework02)

Для запуска миграционных скриптов, согласно документации [Postgres:Initialization scripts](https://hub.docker.com/_/postgres)
запилил еще один ```PostgreSQLDockerfile``` где создается образ с нужными миграциями
Image залит на DockerHub [здесь](https://hub.docker.com/r/turneps403/home02postgre)
*Проверки*: запуск двух котнейнеров показал рабочий результат
###### чистый k8s
в папке ```k8s``` лежат пронумерованные списки манифестов в порядке их запуска
*Проверки*: ```kubectl apply -f k8s/1-...```
*Подводные камни*: это единственный вариант, где было уместо использовать Job для миграции в Postgres
###### helm
Папка ```hw02chart```, запуск из папки ```helm install foo-bar .``` и удаление ```helm delete foo-bar```
Сделано, как и просили с зависимостью на официальный Chart ```bitnami/postgresql``` 
*Подводные камни*: я сделал миграцию с Job, но это явно лишнее. 
Вариант работает, но хотелось чего-то еще. При большом количестве Chart управление неуджобное.
##### helmfile
Папка ```hw02helmfile``` - это последний вариант, на котором я остановился.
Запуск и удаление pod ```helmfile sync``` и ```helmfile destroy```
Здесь я использовал разные ```namespace``` для базы и приложения
*Подводные камни*: 
* я отказался от Job в пользу первоначальной миграции через инициирующий ConfigMap
* minikube на MacOS не умеет линкать pvc на локальную файлуху, 
поэтому ```hostDir``` нужно искать через ```minikube ssh```
* ingress сделан с учетом включенного аддона ```minikube addons enable ingress```
* были проблемы с ingress и я включал minikube через ```minikube start --vm=true```
###### postman
На всех этапах для проверки использовался postman ```OTUS-HWA-02.postman_collection.json```

#### как проверять
```
git clone --single-branch --branch HWA-02 https://github.com/turneps403/otus-homework.git
cd otus-homework/hw02helmfile
helmfile sync
...
проверям статусы
get pods -n myapp-database
get pods -n myapp-application
...
запускаем тесты Postman
...
helmfile destroy
```

#### немного ссылок
- описание того, как именно нужно добиться [работоспособности запроса](https://kubernetes.io/docs/tasks/access-application-cluster/ingress-minikube/)
