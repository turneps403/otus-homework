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
├── OTUS-HWA.postman_collection.json - тесты для postman
├── hwHelmFile - запуск helmfile sync
├── myFiles - доп файлы, скриншоты
```
#### что делал
домашнее задание сделано на основе [предыдущего](https://github.com/turneps403/otus-homework/tree/HWA-02)

я не следовал в прямую материалам урока, поскольку мы отключали addon ingress, а мне хотелось заэкспозить и мой сервис и графану и прометеус.
что было сделано:
- приложение перевел с maven на gradle
- добавил actuator
- запилил кастомные вызовы для сбора статистики для prometeus ```com.otus.homework.my.resources.Prometheus``` поддержал все типы: counter, gauge, histogram, summary Ручные вызовы будут полезны для сбора бизнес метрик приложения
- поддержал там же сбор статистики через аннотации (попробовал только с ```@Timed```)
- расширил описание helmfile релизами prometheus и prometheus-operator (kube-prometheus-stack)
- добавил релиз с ингрессом для графаны и прометеуса + положил сюда service monitor
- весь стек прометеуса, ингрессы, и сервис-монитор я положил в отдельный неймспейс, избегая смешивать с неймспейсами приложения
- для мониторинга памяти приложения (задание со звездой), я использовал готовый дашборд [JVM (Micrometer)](https://grafana.com/grafana/dashboards/4701)
- для кастомного графика типа counter я сделал accumulate график и добавил alert средствами графаны
- для кастомного графика типа histogram настроил два персентиля 50 и 95
- json кастомного дашборда а папке ```myFiles```

#### after helmfile
будут доступны urls, если прописать три домена на ```minikube ip```
```
http://graphana.arch.homework/ (admin + prom_operator)
http://prometheus.arch.homework/graph

http://arch.homework/otusapp/sivirinov/actuator/prometheus
http://arch.homework/otusapp/sivirinov/prom/hello
http://arch.homework/otusapp/sivirinov/prom/count
http://arch.homework/otusapp/sivirinov/prom/gauge/inc
```
тесты postman будут работать

#### useful snippets
```
$ while 1; do ab -n 10 -c 5 http://arch.homework/otusapp/sivirinov/prom/count ; sleep 3; done
$ while 1; do ab -n 10 -c 5 http://arch.homework/otusapp/sivirinov/prom/hist ; sleep 3; done
```

#### Enhance
неплохо бы оформить кастомные объекы для сбора статистики в виде ```@Component``` чтобы
соотвествовать **IoC** идеологии Spring
* https://koudingspawn.de/spring-boot-cloud-ready-part-ii/

#### Knoweledge
* https://stackabuse.com/monitoring-spring-boot-apps-with-micrometer-prometheus-and-grafana/
* https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html
* https://www.youtube.com/watch?v=nJMRmhbY5hY
* https://www.youtube.com/watch?v=mLPg49b33sA
* https://ru.coursera.org/lecture/znakomstvo-r-bazovaya-statistika/miediana-i-kvantili-Tj9Lf
* https://github.com/mweirauch/micrometer-jvm-extras
* https://blog.gojekengineering.com/diy-how-to-set-up-prometheus-and-ingress-on-kubernetes-d395248e2ba

#### MostTimeConsumingTrouble
* service monitor was discovered but had 0/1 active targets

#### Troublesooting
* https://github.com/helm/charts/issues/21690
* https://github.com/prometheus-operator/prometheus-operator/issues/1470#issuecomment-652142735
* https://stackoverflow.com/questions/52991038/how-to-create-a-servicemonitor-for-prometheus-operator
* https://github.com/prometheus-operator/prometheus-operator/blob/master/Documentation/troubleshooting.md#troubleshooting-servicemonitor-changes
