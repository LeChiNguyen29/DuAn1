
package Model.Internal;
import java.sql.Timestamp;
import java.util.Objects;
/**
 *
 * @author phamq
 */
public class KichCoModel {
     private int id;
    private String maKichCo;
    private Timestamp ngaySua;
    private Timestamp ngayTao;
    private int size;
    private int trangThai;

    public KichCoModel() {
    }

    @Override
    public String toString() {
        return String.valueOf(size);
    }

    public KichCoModel(String maKichCo, Timestamp ngaySua, int size, int trangThai) {
        this.maKichCo = maKichCo;
        this.ngaySua = ngaySua;
        this.size = size;
        this.trangThai = trangThai;
    }

    public KichCoModel(String maKichCo, Timestamp ngaySua, Timestamp ngayTao, int size, int trangThai) {
        this.maKichCo = maKichCo;
        this.ngaySua = ngaySua;
        this.ngayTao = ngayTao;
        this.size = size;
        this.trangThai = trangThai;
    }

    public KichCoModel(int id, String maKichCo, Timestamp ngaySua, Timestamp ngayTao, int size, int trangThai) {
        this.id = id;
        this.maKichCo = maKichCo;
        this.ngaySua = ngaySua;
        this.ngayTao = ngayTao;
        this.size = size;
        this.trangThai = trangThai;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

  

   

    public int getId() {
        return id;
    }

 

    public String getMaKichCo() {
        return maKichCo;
    }

    public void setMaKichCo(String maKichCo) {
        this.maKichCo = maKichCo;
    }

    public Timestamp getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Timestamp ngaySua) {
        this.ngaySua = ngaySua;
    }

    public Timestamp getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
    }


    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KichCoModel other = (KichCoModel) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.size != other.size) {
            return false;
        }
        if (this.trangThai != other.trangThai) {
            return false;
        }
        if (!Objects.equals(this.maKichCo, other.maKichCo)) {
            return false;
        }
        if (!Objects.equals(this.ngaySua, other.ngaySua)) {
            return false;
        }
        return Objects.equals(this.ngayTao, other.ngayTao);
    }
    
}
