# Aplikasi Registrasi #

Fitur Aplikasi:

* Pendaftaran Peserta Workshop
* Verifikasi email
* Generate tagihan
* Pembayaran

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