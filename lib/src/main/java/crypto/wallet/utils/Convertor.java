package crypto.wallet.utils;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

public class Convertor {

    private static final HexBinaryAdapter hexBinaryAdapter = new HexBinaryAdapter();

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
        return hexBinaryAdapter.marshal(bytes).toLowerCase();
    }

    public static byte[] hexToBytes(String hex){
        return hexBinaryAdapter.unmarshal(hex);
    }

    public static String utf8ToHex(String string){
        return hexBinaryAdapter.marshal(string.getBytes(StandardCharsets.UTF_8));
    }

    public static String hexToUtf8(String hex){
        return new String(hexBinaryAdapter.unmarshal(hex), StandardCharsets.UTF_8);
    }

}
