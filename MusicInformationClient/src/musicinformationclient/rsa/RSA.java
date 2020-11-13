package musicinformationclient.rsa;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;

public class RSA {
    private byte[] pubKey;
    private byte[] priKey;

    public RSA() {
        System.out.println("Create RSA");
        createKey();
    }

    public void createKey() {
        try {
            SecureRandom sr = new SecureRandom();

            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024, sr);

            KeyPair kp = kpg.genKeyPair();
            PublicKey publicKey = kp.getPublic();
            PrivateKey privateKey = kp.getPrivate();

            pubKey = publicKey.getEncoded();
            priKey = privateKey.getEncoded();
            System.err.println("Create RSA Key Success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] giaMa(byte[] data) {
        try {
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(priKey);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PrivateKey priKey = factory.generatePrivate(spec);
            Cipher c = Cipher.getInstance("RSA");

            c.init(Cipher.DECRYPT_MODE, priKey);
            byte decryptOut[] = c.doFinal(data);
            System.err.println("Decrypt RSA Success: " + new String(decryptOut));
            return decryptOut;
        } catch (Exception e) {
        }
        return null;
    }

    public byte[] getPubKey() {
        return pubKey;
    }
}
