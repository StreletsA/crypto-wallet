package crypto.wallet.utils;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.NamedParameterSpec;

public class CryptoHelper {

    private static final String DEFAULT_SEC_NAME = "secp256k1";
    private static final String DEFAULT_ALGORITHM = "EC";

    public static KeyPair generateECKeyPair(String secName, String algorithm) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {

        ECGenParameterSpec ecSpec = new ECGenParameterSpec(secName);
        KeyPairGenerator g = KeyPairGenerator.getInstance(algorithm);
        g.initialize(ecSpec, new SecureRandom());

        return g.generateKeyPair();

    }

    public static KeyPair generateECKeyPair() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {
        return generateECKeyPair(DEFAULT_SEC_NAME, DEFAULT_ALGORITHM);
    }

}
