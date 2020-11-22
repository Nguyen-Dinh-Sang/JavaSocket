package musicinformationserver.service;

import musicinformationserver.aes.AES;
import musicinformationserver.json.URLConnect;
import musicinformationserver.json.URLConnect.TYPE;
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

            URLConnect urlConnect = new URLConnect();
            String tmp[] = message.split("###");
            String ketQuaSearchCaSi = "RESULTSEARCHCASI###";
            String ketQuaSearchBaiHat = "RESULTSEARCHBAIHAT###";
            String ketQuaInfoCaSi= "RESULTINFOCASI###";
            String ketQuaInfoBaiHat= "RESULTINFOBAIHAT###";
            switch (i) {
                case 0: {
                    System.err.println("OVER");

                    tuongTac.closeThread();
                    break;
                }
                case 1: {
                    System.err.println("SINGS");

                    ketQuaSearchCaSi += urlConnect.getData(tmp[1], TYPE.SINGS);
                    tuongTac.send(AES.maHoa(ketQuaSearchCaSi));
                    break;
                }

                case 2: {
                    System.err.println("SINGINFO");

                    ketQuaInfoCaSi += urlConnect.getData(tmp[1], TYPE.SINGINFO);
                    tuongTac.send(AES.maHoa(ketQuaInfoCaSi));
                    break;
                }

                case 3: {
                    System.err.println("SONGS");

                    ketQuaSearchBaiHat += urlConnect.getData(tmp[1], TYPE.SONGS);
                    tuongTac.send(AES.maHoa(ketQuaSearchBaiHat));
                    break;
                }

                case 4: {
                    System.err.println("SONGINFO");

                    ketQuaInfoBaiHat += urlConnect.getData(tmp[1], TYPE.SONGINFO);
                    tuongTac.send(AES.maHoa(ketQuaInfoBaiHat));
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
