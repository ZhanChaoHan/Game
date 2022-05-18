/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projecte.td.managers;

/**
 *
 * @author media
 */
public class InfoEnemicManager implements Comparable{

    private String tipusEnemic;
    private int tempsSortida;

    public InfoEnemicManager(String tipusEnemic, int tempsSortida) {
        this.tipusEnemic = tipusEnemic;
        this.tempsSortida = tempsSortida;
    }

    public InfoEnemicManager(String tipusEnemic, int tempsSortida, int carril) {
        this.tipusEnemic = tipusEnemic;
        this.tempsSortida = tempsSortida;
    }

    public int compareTo(Object object) {
        InfoEnemicManager iem = (InfoEnemicManager) object;
        if (iem.getTempsSortida() < this.tempsSortida) {
            return 1;
        } else if (iem.getTempsSortida() == this.tempsSortida) {
            return 0;
        } else {
            return -1;
        }
    }

    public int getTempsSortida() {
        return tempsSortida;
    }

    public String getTipusEnemic() {
        return this.tipusEnemic;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InfoEnemicManager other = (InfoEnemicManager) obj;
        if ((this.tipusEnemic == null) ? (other.tipusEnemic != null) : !this.tipusEnemic.equals(other.tipusEnemic)) {
            return false;
        }
        if (this.tempsSortida != other.tempsSortida) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }
}
