#### задание
**Prometheus. Grafana**
Инструментировать сервис из прошлого задания метриками в формате Prometheus с помощью библиотеки для вашего фреймворка и ЯП.

Сделать дашборд в Графане, в котором были бы метрики с разбивкой по API методам:
1. Latency (response time) с квантилями по 0.5, 0.95, 0.99, max
2. RPS
3. Error Rate - количество 500ых ответов

Добавить в дашборд графики с метрикам в целом по сервису, взятые с nginx-ingress-controller:
1. Latency (response time) с квантилями по 0.5, 0.95, 0.99, max
2. RPS
3. Error Rate - количество 500ых ответов

Настроить алертинг в графане на Error Rate и Latency.

На выходе должно быть:
0. скриншоты дашборды с графиками в момент стресс-тестирования сервиса. Например, после 5-10 минут нагрузки.
1. json-дашборды.

Задание со звездочкой (+5 баллов)
Используя существующие системные метрики из кубернетеса, добавить на дашборд графики с метриками:
1. Потребление подами приложения памяти
2. Потребление подами приолжения CPU

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

#### Useful snippets
```
$ while 1; do ab -n 50 -c 5 http://192.168.176.128:32033/db ; sleep 3; done
```

#### Knoweledge
* https://www.youtube.com/watch?v=nJMRmhbY5hY
* https://www.youtube.com/watch?v=mLPg49b33sA
* https://ru.coursera.org/lecture/znakomstvo-r-bazovaya-statistika/miediana-i-kvantili-Tj9Lf

#### Troublesooting
* https://github.com/helm/charts/issues/21690
