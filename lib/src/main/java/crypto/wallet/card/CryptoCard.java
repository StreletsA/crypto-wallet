package crypto.wallet.card;

import lombok.Getter;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import static crypto.wallet.utils.Convertor.*;

@Getter
public abstract class CryptoCard {

    String privateKey;
    String publicKey;
    String address;
    String algorithm;
    String concurrency;
    KeyPair keyPair;

    public abstract byte[] calculateSignature(String itemForSigningAsHex) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException;

    public String calculateSignatureAsHex(String itemForSigningAsHex) throws UnsupportedEncodingException, SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        return bytesToHex(calculateSignature(itemForSigningAsHex));
    }

    public String getPrivateKeyAsBase64() {
        return keyToBase64(keyPair.getPrivate());
    }

    public String getPublicKeyAsBase64() {
        return keyToBase64(keyPair.getPublic());
    }

    public String getPrivateKeyAsHex() {
        return keyToHex(keyPair.getPrivate());
    }

    public String getPublicKeyAsHex() {
        return keyToHex(keyPair.getPublic());
    }
}
