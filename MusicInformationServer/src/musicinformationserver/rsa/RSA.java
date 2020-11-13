/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicinformationserver.rsa;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author DinhSang
 */
public class RSA {
    private byte[] pubKey;
    
    public RSA(byte[] pubKey) {
        this.pubKey = pubKey;
    }
    
    public byte[] maHoa(byte[] data) {
        try {
            X509EncodedKeySpec spec = new X509EncodedKeySpec(pubKey);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = factory.generatePublic(spec);

            Cipher c = Cipher.getInstance("RSA");
            c.init(Cipher.ENCRYPT_MODE, pubKey);

            byte encryptOut[] = c.doFinal(data);

            return encryptOut;
        } catch (Exception e) {
            return maHoa(data);
        }
    }
}
