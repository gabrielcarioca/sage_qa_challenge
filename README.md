# Desafio QA

Esse projeto é uma submissão do desafio QA para a Sage
(Os testes foram desenvolvidos e executados em OSX, pode ser necessário algumas alterações mínimas para a exeução em outros sistemas)

## Pre requisitos

Uma lista dos pré requisitos e como instalá-los

### Selenium

```
npm install -g selenium-server
npm install -g selenium-standalone
```

### Chromedriver

Tenha certeza que a última versão do Google Chrome está instalada no seu computador.

```
npm install -g chromedriver
```

### Gradle
```
brew install gradle
```

## Executando os testes

### Rodando o Selenium

```
selenium-standalone start -- -role hub
```

### Executando testes do frontend

Do repositório do projeto (em outra aba do terminal)

```
gradle test -Dcucumber.options="-t @PersonListFeature" --rerun-tasks

ou

./gradlew test -Dcucumber.options="-t @PersonListFeature" --rerun-tasks
```

### Executando testes de Web Service

Do repositório do projeto

```
gradle test -Dcucumber.options="-t @WebServiceRequestFeature" --rerun-tasks

ou

gradle test -Dcucumber.options="-t @WebServiceRequestFeature" --rerun-tasks
```