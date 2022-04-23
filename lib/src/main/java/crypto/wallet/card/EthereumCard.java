package crypto.wallet.card;

import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.util.Arrays;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;

import static crypto.wallet.utils.Convertor.*;
import static crypto.wallet.utils.CryptoHelper.generateECKeyPair;
import static org.web3j.crypto.Hash.sha3;

public class EthereumCard extends CryptoCard{

    private static final String DEFAULT_ALGORITHM = "SHA256withECDSA";

    public EthereumCard() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {
        this(DEFAULT_ALGORITHM);
    }

    public EthereumCard(String algorithm) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {

        this.algorithm = algorithm;
        keyPair = generateECKeyPair();
        privateKey = bytesToHex(keyPair.getPrivate().getEncoded());
        publicKey = bytesToHex(keyPair.getPublic().getEncoded());
        address = deriveAddress(keyPair.getPrivate().getEncoded());
        concurrency = CryptoConcurrency.ETH;

    }

    public EthereumCard(String privateKeyAsHex, String publicKeyAsHex) throws IOException {

        PrivateKey createdPrivateKey = (PrivateKey) PrivateKeyFactory.createKey(privateKeyAsHex.getBytes());
        PublicKey createdPublicKey = (PublicKey) PublicKeyFactory.createKey(publicKeyAsHex.getBytes());

        keyPair = new KeyPair(createdPublicKey, createdPrivateKey);
        privateKey = bytesToHex(keyPair.getPrivate().getEncoded());
        publicKey = bytesToHex(keyPair.getPublic().getEncoded());
        address = deriveAddress(keyPair.getPrivate().getEncoded());
        algorithm = DEFAULT_ALGORITHM;
        concurrency = CryptoConcurrency.ETH;

    }

    private static String deriveAddress(byte[] privateKeyBytes){

        byte[] sha3Bytes = sha3(privateKeyBytes);
        byte[] lastTwentyBytes = Arrays.copyOfRange(sha3Bytes,sha3Bytes.length - 20, sha3Bytes.length);

        return "0x" + bytesToHex(lastTwentyBytes);

    }

    public EthTransaction createTransaction(String to, int value){

        return new EthTransaction(0, 0, 0, to, value, null);
    };

    public EthTransaction createTransaction(String to, int gasPrice, int gasLimit, int value){

        return new EthTransaction(0, gasPrice, gasLimit, to, value, null);
    };

    @Override
    public byte[] calculateSignature(String itemForSigningAsHex) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        PrivateKey privateKey = keyPair.getPrivate();

        Signature ecdsaSign = Signature.getInstance(algorithm);
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(itemForSigningAsHex.getBytes("UTF-8"));

        byte[] hash = sha3(itemForSigningAsHex.getBytes(StandardCharsets.UTF_8));

        return ecdsaSign.sign();
    }



}
