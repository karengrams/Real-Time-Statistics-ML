# Real Time Statistics ML

## Table of Content
- [iOS setup](#ios-setup)
  - [Requirements](#requirements)
    - [Set up](#set-up)
  - [How to setup to run locally](#how-to-run-locally)
  - [How to run in Docker container](#how-to-run-in-docker-container)
- [How it works](#how-it-works)
- [Decision taken](#decisions-taken)

## iOS Set Up

### Requirements

- Java 8
- Maven 3.6.2
- Docker

#### Set up

- For installing Java 8:
  1. Open the Terminal
  2. Enter the followings commands in the terminal:
  ```bash
  $ brew tap adoptopenjdk/openjdk
  $ brew cask install adoptopenjdk8
  ```

- For installing Maven:
  1. Open the Terminal
  2. Enter the followings commands in the terminal:
  ```bash
  $ brew install maven
  ```
  
- For installing Docker:
  1. Open the Terminal
  2. Enter the followings commands in the terminal:
  ```bash
  $ brew cask install docker
  ```

If you don’t have Homebrew and Homebrek Cask, then you can [read here how to install them on Mac OS](https://devqa.io/brew-install-java/).

### How to run locally

Run the following commands in the root of the project:

```bash
 $ mvn package
 $ java -jar target/Real-Time-Statistics-ML-0.0.1-SNAPSHOT.jar
```

### How to run in Docker container

Run the following command in the root of the project:

```bash
$ sudo sh deploy-project.sh
```

## How it works
When the application runs, there are four end-points that can be called. Those endpoints will be explained in the following secction.

### `POST /transactions`
In this endpoint there has to be a body in the following format:

```json
[
  {
    "amount": "12.3343", 
    "timestamp": "2021-04-21T09:59:51.312Z"
  }
]
```

If the format is correct, and the values can be parsed, there are two validations in order to check if the transaction is valid:

- The transaction has to be in the present.
- The transaction's timestamps has to be in the last 60 seconds.

### `GET /transactions`
When this endpoint is called, all the transactions saved in the cache, will be returned.

### `DELETE /transactions`
If this endpoint is called, all the transactions will be erased.

### `GET /statistics`
In order to get statistics with valid transactions, this endpoint should be called, and will returned the following calculates:

- Max
- Min
- Average
- Count
- 90th percentile
- Sum

## Decisions taken

### Why did I decide to use Guava Cache in order to save the transactions?
There are multiple ways to save the transactions in memory, one option is to use an `ArrayList` or similar collection type but, this solution has to be thread-safe, so in order to accomplish that and maintain transactions consistency, there has to be atomic variables and synchronized methods. [Guava Cache](https://github.com/google/guava/wiki/CachesExplained) is a Java library that implements a thread-safe cache. Furthermore, one of the requirements is discard the transactions that are considered expired after sixty seconds, Guava Cache implementation has a built-in method that solves it.

### Why did I decide to use Spring Boot?
Because is a well-known and documented framework that simplifies API implementation.

### If there are no transactions saved, statistics can't be asked
If statistics are asked when the cache is empty, a `400 Bad Request` response will be delivered, because this solution considered necessary at least one transaction to calculate them.

