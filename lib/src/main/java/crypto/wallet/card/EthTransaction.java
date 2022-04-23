package crypto.wallet.card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nullable;
import java.math.BigInteger;

@Setter
@Getter
@NoArgsConstructor
public class EthTransaction {

    private BigInteger nonce;
    private BigInteger gasPrice;
    private BigInteger gasLimit;
    private String to;
    private BigInteger value;

    @Nullable
    private String data;

    public EthTransaction(BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, String to, BigInteger value, @Nullable String data) {

        this.nonce = nonce;
        this.gasPrice = gasPrice;
        this.gasLimit = gasLimit;
        this.to = to;
        this.value = value;
        this.data = data;

    }

    public EthTransaction(String nonce, String gasPrice, String gasLimit, String to, String value, @Nullable String data){
        this(
                new BigInteger(nonce),
                new BigInteger(gasPrice),
                new BigInteger(gasLimit),
                to,
                new BigInteger(value),
                data
        );
    }

    public EthTransaction(int nonce, int gasPrice, int gasLimit, String to, int value, @Nullable String data){
        this(
                String.valueOf(nonce),
                String.valueOf(gasPrice),
                String.valueOf(gasLimit),
                to,
                String.valueOf(value),
                data
        );
    }

}
