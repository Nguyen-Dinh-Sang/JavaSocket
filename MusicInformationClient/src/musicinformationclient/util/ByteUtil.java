package musicinformationclient.util;

import java.nio.charset.StandardCharsets;

public class ByteUtil {
    public static byte[] getByteUTF16(String message) {
        byte[] data;
        data = message.getBytes(StandardCharsets.UTF_16LE);
        return data;
    }

    public static String getStringUTF16(byte[] data) {
        String message = new String(data, StandardCharsets.UTF_16LE);
        return message;
    }
}
