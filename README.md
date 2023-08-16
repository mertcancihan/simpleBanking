# Simple Banking Uygulaması

Simple Banking, kullanıcıların banka hesaplarına para yatırma ve çekme işlemlerini gerçekleştirebildiği bir Spring Boot uygulamasıdır. Bu projede PostgreSQL veritabanı kullanılmıştır.

## Başlangıç

Bu bölüm, projeyi yerel makinenizde çalıştırmanız için adımları içermektedir.

### Önkoşullar

- **Java 8** veya üstü
- **Gradle**
- **PostgreSQL**

### Kurulum

1. Projeyi yerel makinenize klonlayın:

```bash
git clone https://github.com/mertcancihan/simpleBanking.git
cd simple-banking
```

2. PostgreSQL'de simplebanking adında bir veritabanı oluşturun:

```bash
CREATE DATABASE simplebanking;
```

3. application.properties dosyasındaki veritabanı bağlantı bilgilerini kontrol edin ve gerekli değişiklikleri yapın:

```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/simplebanking
spring.datasource.username=whisper
spring.datasource.password=whisper
```
4. Projeyi Gradle ile build edin ve çalıştırın:

```bash
./gradlew clean build
./gradlew bootRun
```

Bu komutlar Windows'ta çalıştırılıyorsa, ./gradlew yerine gradlew kullanın.

Artık uygulama http://localhost:8080 adresinde çalışıyor olmalı!

5. Postman ile testler yapmak için örnek postman istekleri ``SimpleBanking.postman_collection.json`` dosyası olarak eklenmiştir. Postman import yapılarak kullanılabilir.

## Özellikler

- Banka hesabı oluşturma
- Belirtilen hesaba para yatırma (credit)
- Belirtilen hesaptan para çekme (debit)


## Kullanılan Teknolojiler

- Spring Boot
- Spring Data JPA
- PostgreSQL
- Hibernate
- Gradle


