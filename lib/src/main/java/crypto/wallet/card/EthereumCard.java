package crypto.wallet.card;

import org.bouncycastle.util.Arrays;

import java.io.UnsupportedEncodingException;
import java.security.*;

import static crypto.wallet.utils.Convertor.*;
import static crypto.wallet.utils.CryptoHelper.generateECKeyPair;
import static org.web3j.crypto.Hash.sha3;

public class EthereumCard extends CryptoCard{


    public EthereumCard() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {
        this("SHA256withECDSA");
    }

    public EthereumCard(String algorithm) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {

        this.algorithm = algorithm;
        keyPair = generateECKeyPair();
        address = deriveAddress(keyPair.getPrivate().getEncoded());

    }

    private static String deriveAddress(byte[] privateKeyBytes){

        byte[] sha3Bytes = sha3(privateKeyBytes);
        byte[] lastTwentyBytes = Arrays.copyOfRange(sha3Bytes,sha3Bytes.length - 20, sha3Bytes.length);

        return "0x" + bytesToHex(lastTwentyBytes);

    }


    @Override
    public byte[] calculateSignature(String itemForSigningAsHex) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        PrivateKey privateKey = keyPair.getPrivate();

        Signature ecdsaSign = Signature.getInstance(algorithm);
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(itemForSigningAsHex.getBytes("UTF-8"));

        return ecdsaSign.sign();
    }



}
