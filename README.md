# otus-homework
#### Description
**Цель:** нучиться создавать проект Gradle (Maven), подготовиться к выполнению домашних заданий.
0. Создайте аккаунт на github.com (если еще нет)
1. Создайте репозиторий для домашних работ
2. Сделайте checkout репозитория на свой компьютер
3. Создайте локальный бранч hw01-gradle
4. Создать проект gradle
5. В проект добавьте последнюю версию зависимости
```
<groupId>com.google.guava</groupId>
<artifactId>guava</artifactId>
```
6. Создайте модуль hw01-gradle
7. В модуле сделайте класс HelloOtus
8. В этом классе сделайте вызов какого-нибудь метода из guava
9. Создайте "толстый-jar"
10. Убедитесь, что "толстый-jar" запускается.
11. Сделайте pull-request в gitHub

#### Main goals
mastering Gradle

#### How to run/check
```
$ git clone --single-branch --branch HWJ-01 https://github.com/turneps403/otus-homework.git hwj01
$ cd hwj01
$ cat hw01-gradle/src/main/java/ru/otus/app/service/HelloOtus.java
$ ./gradlew clean build
$ unzip -l hw01-gradle/build/libs/gradleHelloWorld-0.1.jar
$ java -jar hw01-gradle/build/libs/gradleHelloWorld-0.1.jar
```

#### Useful sources
* https://guides.gradle.org/building-java-applications/
* https://habr.com/ru/post/454198/
* https://github.com/petrelevich/otus_java_2020_09/blob/master/L01-gradle/build.gradle
* https://docs.gradle.org/current/dsl/org.gradle.api.tasks.bundling.Jar.html

#### Troublesooting
* https://stackoverflow.com/questions/4871656/using-gradle-to-build-a-jar-with-dependencies
* https://stackoverflow.com/questions/18965528/using-archivesbasename-in-a-gradle-project-has-no-effect
* https://stackoverflow.com/questions/31405818/want-to-specify-jar-name-and-version-both-in-build-gradle/31407245