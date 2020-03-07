# Aplikasi Registrasi #

Fitur Aplikasi:

* Pendaftaran Peserta Workshop
* Verifikasi email
* Generate tagihan
* Pembayaran

[![Mockup Aplikasi](docs/mockup-aplikasi-registrasi.jpg)]((docs/mockup-aplikasi-registrasi.jpg))

## Cara Membuat Database ##

1. Create user untuk connect ke database

    ```
    createuser -P registrasiuser
    ```

2. Create database untuk user tersebut

    ```
   createdb -Oregistrasiuser registrasidb
   ```

3. Konfigurasi database

    ```
   spring.datasource.url=jdbc:postgresql://localhost/registrasidb
   spring.datasource.username=registrasiuser
   spring.datasource.password=registrasiuser123
   ```

## Referensi ##

* [Cara mengirim email dengan GMail API](https://software.endy.muhardin.com/java/mengirim-email-gmail-api/)
* [Konsep Spring Framework](https://www.youtube.com/playlist?list=PL9oC_cq7OYbyhdZmCECQqp7OcS8J5QpAo)