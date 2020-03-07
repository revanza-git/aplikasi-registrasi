package com.muhardin.endy.aplikasiregistrasi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@SpringBootTest
public class StoredCredentialBase64Tests {
    @Value("${gmail.folder}")
    private String dataStoreFolder;

    @Test
    public void testConvertStoredCredential() throws IOException {
        byte[] credentialFile = Files.readAllBytes(
                Paths.get(dataStoreFolder + File.separator +
                AplikasiRegistrasiApplication.STORED_CREDENTIAL_FILE));
        String base64Encoded
                = Base64.getEncoder()
                .encodeToString(credentialFile);

        System.out.println(base64Encoded);
    }

    @Test
    public void tetConvertClientSecret() throws IOException {
        byte[] clientSecretJson = Files.readAllBytes(
                Paths.get(dataStoreFolder + File.separator +
                        AplikasiRegistrasiApplication.CLIENT_SECRET_JSON_FILE));
        String base64Encoded
                = Base64.getEncoder()
                .encodeToString(clientSecretJson);

        System.out.println(base64Encoded);
    }
}
