# otus: Spring framework course
## homework 01

**Цель:** создать приложение с помощью Spring IoC, чтобы познакомиться с основной функциональностью IoC, на которой строится весь Spring.

**Результат:** простое приложение, сконфигурированное XML-контекстом.

**Требования:**
0. В приложении должна присутствовать объектная модель (отдаём предпочтение объектам и классам, а не строчкам и массивам/спискам строчек).
1. Все классы в приложении должны решать строго определённую задачу (см. п. 18-19 "Правила оформления кода.pdf", прикреплённые к материалам занятия).
2. Контекст описывается XML-файлом.
3. Все зависимости должны быть настроены в IoC контейнере.
4. Имя ресурса с вопросами (CSV-файла) необходимо захардкодить строчкой в XML-файле с контекстом.
5. CSV с вопросами читается именно как ресурс, а не как файл.
6. Scanner, PrintStream и другие стандартные типы в контекст класть не нужно!
7. Весь ввод-вывод осуществляется на английском языке.
8. Крайне желательно написать юнит-тест какого-нибудь сервиса (оцениваться будет только попытка написать тест).
9. Помним - "без фанатизма".

#### How to run

```mvn clean package && java -jar target/hw01-1.0-SNAPSHOT.jar```

#### Main goals
* understanding of IoC (Inversion of Control)
* implement DI (Dependency injection) with XML

#### Useful sources
* https://www.baeldung.com/inversion-control-and-dependency-injection-in-spring
* https://www.baeldung.com/spring-xml-injection
* http://sergeyteplyakov.blogspot.com/2014/11/di-vs-dip-vs-ioc.html
* https://www.youtube.com/watch?time_continue=1&v=Sm6QooVMt6k&feature=emb_logo
* https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html
* https://www.codeflow.site/ru/article/spring-classpathxmlapplicationcontext

#### Helpers
* https://stackoverflow.com/questions/29920434/maven-adding-mainclass-in-pom-xml-with-the-right-folder-path
* https://github.com/petrelevich/maven-example
* https://stackoverflow.com/questions/574594/how-can-i-create-an-executable-jar-with-dependencies-using-maven
