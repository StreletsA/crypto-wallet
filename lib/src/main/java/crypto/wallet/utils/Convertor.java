package crypto.wallet.utils;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.Key;
import java.util.Base64;

public class Convertor {

    public static String keyToBase64(Key key){
        return bytesToBase64(key.getEncoded());
    }

    public static String bytesToBase64(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String keyToHex(Key key){
        return bytesToHex(key.getEncoded());
    }

    public static String bytesToHex(byte[] bytes){
        HexBinaryAdapter hexBinaryAdapter = new HexBinaryAdapter();
        return  hexBinaryAdapter.marshal(bytes);
    }

}
