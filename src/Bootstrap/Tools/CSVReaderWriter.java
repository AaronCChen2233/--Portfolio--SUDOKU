package Bootstrap.Tools;

import java.io.*;
import java.util.ArrayList;

/***
 * @author Aaron Chen
 */
public class CSVReaderWriter {
    /***
     * @param scvPath
     * @return
     */
    public static ArrayList<String> reader(String scvPath) {
        ArrayList<String> lines = null;
        InputStreamReader isr = null;
        BufferedReader reader = null;
        try {
            isr = new InputStreamReader(new FileInputStream(scvPath));
            reader = new BufferedReader(isr);
            String line = null;
            lines = new ArrayList<String>();

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return lines;
    }

    /***
     * @param scvPath
     * @param pushData
     * @return
     */
    public static boolean pushWriter(String scvPath, String pushData) {
        InputStreamReader isr = null;
        BufferedReader reader = null;
        BufferedWriter bw = null;
        try {
            /*Read*/
            isr = new InputStreamReader(new FileInputStream(scvPath));
            reader = new BufferedReader(isr);

            /*keep in lines*/
            ArrayList<String> lines = new ArrayList<String>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            /*write*/
            bw = new BufferedWriter(new FileWriter(scvPath));
            for (String ostr : lines) {
                bw.write(ostr);
                bw.newLine();
            }

            /*push*/
            bw.write(pushData);
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /***
     * @param scvPath
     * @param Data
     * @return
     */
    public static boolean writer(String scvPath, String Data) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(scvPath));
            bw.write(Data);
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}