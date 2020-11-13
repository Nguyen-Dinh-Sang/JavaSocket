package musicinformationserver.service;

import musicinformationserver.aes.AES;
import musicinformationserver.rsa.RSA;

public class Service {

    private TuongTac tuongTac;
    private AES AES;

    public Service(TuongTac tuongTac) {
        this.tuongTac = tuongTac;
    }

    public void xyLy(byte[] data) {
        String message = "";

        if (AES != null) {
            message = AES.giaMa(data);

            String[] cases = {"OVER###", "SINGS###", "SINGINFO###", "SONGS###", "SONGINFO###"};

            int i;
            for (i = 0; i < cases.length; i++) {
                if (message.startsWith(cases[i])) {
                    break;
                }
            }

            switch (i) {
                case 0: {
                    System.err.println("OVER");
                    tuongTac.closeThread();
                    break;
                }
                case 1: {
                    System.err.println("SINGS");
                    String ok = "RESULT###" + "ok" + message;
                    tuongTac.send(AES.maHoa(ok));
                    break;
                }

                case 2: {
                    System.err.println("SINGINFO");
                    break;
                }

                case 3: {
                    System.err.println("SONGS");
                    break;
                }

                case 4: {
                    System.err.println("SONGINFO");
                    break;
                }

                default: {
                    System.out.println("Do Nothing");
                    tuongTac.send(AES.maHoa("WHAT"));
                    break;
                }

            }
        } else {
            AES = new AES();
            RSA RSA = new RSA(data);
            tuongTac.send(RSA.maHoa(AES.getSecretKey()));
        }
    }

    public interface TuongTac {

        void send(byte[] data);

        void closeThread();
    }
}
