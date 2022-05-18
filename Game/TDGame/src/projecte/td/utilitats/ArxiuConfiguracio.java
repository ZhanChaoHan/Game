package projecte.td.utilitats;

import java.io.FileOutputStream;
import java.net.URL;
import java.util.Properties;
import java.util.Set;
import org.newdawn.slick.util.ResourceLoader;

/**
 * Aquesta classe s'encarrega de carregar en memoria els fitxers de configuració
 * i extreure'n les dades necessaries.
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class ArxiuConfiguracio {

    // Objecte properties que llegira els valors
    private Properties propietats;
    // Path on es troba el fitxer de configuració
    private String arxiu;

    /**
     * Obre un arxiu de configuracio a partir de la ruta passada
     * @param referencia La ruta on es troba l'arxiu de configuració
     */
    public ArxiuConfiguracio(String referencia) {
        boolean cond = false;
        while (!cond) {
            try {
                propietats = new Properties();
                URL url = ResourceLoader.getResource(referencia);
                arxiu = url.toURI().getPath();
                propietats.load(url.openStream());
                cond = true;

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Desa les dades a l'arxiu de configuració
     */
    public void guardar() {
        try {
            propietats.store(new FileOutputStream(arxiu), "Configuracio");
        } catch (Exception e) {
            throw new RuntimeException("No s'ha pogut guardar el fitxer: " + arxiu);
        }
    }

    // Getters i setters

    public String getPropietatString(String clau) {
        return propietats.getProperty(clau);
    }

    public void setPropietatString(String clau, String valor) {
        propietats.setProperty(clau, valor);
    }

    public Boolean getPropietatBoolean(String clau) {
        return Boolean.parseBoolean(propietats.getProperty(clau));
    }

    public void setPropietatBoolean(String clau, Boolean valor) {
        propietats.setProperty(clau, String.valueOf(valor));
    }

    public int getPropietatInt(String clau) {
        return Integer.parseInt(propietats.getProperty(clau));
    }

    public void setPropietatInt(String clau, int valor) {
        propietats.setProperty(clau, String.valueOf(valor));
    }

    public float getPropietatFloat(String clau) {
        Float f=null;
        String propietat=propietats.getProperty(clau);
        if(propietat.contains("-")){
            String[] linia=propietat.split("-");
            f=Float.parseFloat(linia[1]);
            f*=-1;
        }
        else{
            f=Float.parseFloat(propietat);
        }
        return f;
    }

    public void setPropietatFloat(String clau, float valor) {
        propietats.setProperty(clau, String.valueOf(valor));
    }

    public long getPropietatLong(String clau) {
        return Long.parseLong(propietats.getProperty(clau));
    }

    public void setPropietatLong(String clau, long valor) {
        propietats.setProperty(clau, String.valueOf(valor));
    }

    public double getDoublePropietat(String clau) {
        return Double.parseDouble(propietats.getProperty(clau));
    }

    public void setDoublePropietat(String clau, double valor) {
        propietats.setProperty(clau, String.valueOf(valor));
    }

    public Set<Object> getTotesPropietats() {
        return propietats.keySet();
    }

    public String getStringArxiu() {
        return arxiu;
    }
}
