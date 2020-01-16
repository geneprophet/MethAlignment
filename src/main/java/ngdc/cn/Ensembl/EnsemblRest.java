package ngdc.cn.Ensembl;

import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @author Kang
 *访问Ensembl API获取指定序列
 */

public class EnsemblRest {
    String Species;
    String Chromosome;
    String Start;
    String End;
    String Strand;
    public EnsemblRest(String Species,String Chromosome,String Strand,String Start,String End) {
        this.Species = Species;
        this.Chromosome = Chromosome;
        this.Start = Start;
        this.End = End;
        this.Strand = Strand;
    }

    public String getSpecies() {
        return Species;
    }

    public void setSpecies(String species) {
        Species = species;
    }

    public String getChromosome() {
        return Chromosome;
    }

    public void setChromosome(String chromosome) {
        Chromosome = chromosome;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getEnd() {
        return End;
    }

    public void setEnd(String end) {
        End = end;
    }

    public String getStrand() {
        return Strand;
    }

    public void setStrand(String strand) {
        Strand = strand;
    }

    public String GetSequence() throws Exception {
        String server = "https://rest.ensembl.org";
        String ext = "/sequence/region/"+Species+"/"+Chromosome+":"+Start+".."+End+":"+Strand+"?";
        URL url = new URL(server + ext);

        URLConnection connection = url.openConnection();
        HttpURLConnection httpConnection = (HttpURLConnection)connection;

        httpConnection.setRequestProperty("Content-Type", "text/plain");

        InputStream response = connection.getInputStream();
        int responseCode = httpConnection.getResponseCode();

        if(responseCode != 200) {
            throw new RuntimeException("Response code was not 200. Detected response was "+responseCode);
        }

        String output;
        Reader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(response, "UTF-8"));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            output = builder.toString();
        }
        finally {
            if (reader != null) try {
                reader.close();
            } catch (IOException logOrIgnore) {
                logOrIgnore.printStackTrace();
            }
        }

        return output;
    }
}
