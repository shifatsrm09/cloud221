import java.io.*;

public class T2 {
    public static void main(String[] args) throws IOException {
        BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(BR.readLine());

        for (int i = 0; i < T; i++) {
            double res = 0;
            String s = BR.readLine();
            String exp = s.replace("calculate ", "").trim(); // ✅ note the space

            if (exp.contains("+")) {
                String[] parts = exp.split("\\+");
                res = Double.parseDouble(parts[0].trim()) + Double.parseDouble(parts[1].trim());
            } else if (exp.contains("-")) {
                String[] parts = exp.split("-");
                res = Double.parseDouble(parts[0].trim()) - Double.parseDouble(parts[1].trim());
            } else if (exp.contains("*")) {
                String[] parts = exp.split("\\*");
                res = Double.parseDouble(parts[0].trim()) * Double.parseDouble(parts[1].trim());
            } else if (exp.contains("/")) {
                String[] parts = exp.split("/");
                res = Double.parseDouble(parts[0].trim()) / Double.parseDouble(parts[1].trim());
            }

            BW.write(String.valueOf(res));
            BW.newLine();
        }

        BW.flush();
        BW.close();
        BR.close();
    }
}
