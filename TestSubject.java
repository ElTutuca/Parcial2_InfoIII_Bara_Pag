import java.util.Date;

public class TestSubject {

    private int idEventoCaso;
    private char sexo;
    private int edad;
    private boolean edadTipo; // 1 -> meses //// 0 -> anios
    private String residenciaPais;
    private String residenciaProvincia;
    private String residenciaDepartamento;
    private String cargaProvincia;
    private Date fechaInicioSintomas;
    private Date fechaApertura;
    private String sepiApertura;
    private Date fechaInternacion;
    private boolean cuidadoIntensivo; // Verdadero -> Estuvo en cuidado
    private Date fechaCuidadoIntensivo;
    private boolean fallecido; // Verdadero->Muerto //hatch en cs = verdadero
    private Date fechaFallecimiento;
    private boolean asistenciaRespiratoriaMecanica; // Verdadero-> Tuvo asistencia
    private int cargaProvinciaId;
    private boolean origenFinanciamiento; // Verdadero-> Tenia obra social
    private String clasificacion;
    private String clasificacionResumen;
    private int residenciaProvinciaId;
    private Date fechaDiagnostico;
    private int residenciaDepartamentoId;
    private Date ultimaActualizacion; // Fecha

    TestSubject() {
    }
    // ********* INICIO SETTERS

    public void setIdEventoCaso(int idEventoCaso) {
        this.idEventoCaso = idEventoCaso;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setEdadTipo(boolean edadTipo) {
        this.edadTipo = edadTipo;
    }

    public void setResidenciaPais(String residenciaPais) {
        this.residenciaPais = residenciaPais;
    }

    public void setResidenciaProvincia(String residenciaProvincia) {
        this.residenciaProvincia = residenciaProvincia;
    }

    public void setResidenciaDepartamento(String residenciaDepartamento) {
        this.residenciaDepartamento = residenciaDepartamento;
    }

    public void setCargaProvincia(String cargaProvincia) {
        this.cargaProvincia = cargaProvincia;
    }

    public void setFechaInicioSintomas(Date fechaInicioSintomas) {
        this.fechaInicioSintomas = fechaInicioSintomas;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public void setSepiApertura(String sepiApertura) {
        this.sepiApertura = sepiApertura;
    }

    public void setFechaInternacion(Date fechaInternacion) {
        this.fechaInternacion = fechaInternacion;
    }

    public void setCuidadoIntensivo(boolean cuidadoIntensivo) {
        this.cuidadoIntensivo = cuidadoIntensivo;
    }

    public void setFechaCuidadoIntensivo(Date fechaCuidadoIntensivo) {
        this.fechaCuidadoIntensivo = fechaCuidadoIntensivo;
    }

    public void setFallecido(boolean fallecido) {
        this.fallecido = fallecido;
    }

    public void setFechaFallecimiento(Date fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public void setAsistenciaRespiratoriaMecanica(boolean asistenciaRespiratoriaMecanica) {
        this.asistenciaRespiratoriaMecanica = asistenciaRespiratoriaMecanica;
    }

    public void setCargaProvinciaId(int cargaProvinciaId) {
        this.cargaProvinciaId = cargaProvinciaId;
    }

    public void setOrigenFinanciamiento(boolean origenFinanciamiento) {
        this.origenFinanciamiento = origenFinanciamiento;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public void setClasificacionResumen(String clasificacionResumen) {
        this.clasificacionResumen = clasificacionResumen;
    }

    public void setResidenciaProvinciaId(int residenciaProvinciaId) {
        this.residenciaProvinciaId = residenciaProvinciaId;
    }

    public void setFechaDiagnostico(Date fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public void setResidenciaDepartamentoId(int residenciaDepartamentoId) {
        this.residenciaDepartamentoId = residenciaDepartamentoId;
    }

    public void setUltimaActualizacion(Date ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }

    // ********* INICIO GETTERS *************

    public int getIdEventoCaso() {
        return idEventoCaso;
    }

    public char getSexo() {
        return sexo;
    }

    public int getEdad() {
        return edad;
    }

    public boolean isEdadTipo() {
        return edadTipo;
    }

    public String getResidenciaPais() {
        return residenciaPais;
    }

    public String getResidenciaProvincia() {
        return residenciaProvincia;
    }

    public String getResidenciaDepartamento() {
        return residenciaDepartamento;
    }

    public String getCargaProvincia() {
        return cargaProvincia;
    }

    public Date getFechaInicioSintomas() {
        return fechaInicioSintomas;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public String getSepiApertura() {
        return sepiApertura;
    }

    public Date getFechaInternacion() {
        return fechaInternacion;
    }

    public boolean isCuidadoIntensivo() {
        return cuidadoIntensivo;
    }

    public Date getFechaCuidadoIntensivo() {
        return fechaCuidadoIntensivo;
    }

    public boolean isFallecido() {
        return fallecido;
    }

    public Date getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public boolean isAsistenciaRespiratoriaMecanica() {
        return asistenciaRespiratoriaMecanica;
    }

    public int getCargaProvinciaId() {
        return cargaProvinciaId;
    }

    public boolean isOrigenFinanciamiento() {
        return origenFinanciamiento;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public String getClasificacionResumen() {
        return clasificacionResumen;
    }

    public int getResidenciaProvinciaId() {
        return residenciaProvinciaId;
    }

    public Date getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public int getResidenciaDepartamentoId() {
        return residenciaDepartamentoId;
    }

    public Date getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    @Override
    public String toString() {
        return "TestSubject{" +
                "idEventoCaso=" + idEventoCaso +
                ", sexo=" + sexo +
                ", edad=" + edad +
                ", edadTipo=" + edadTipo +
                ", residenciaPais='" + residenciaPais + '\'' +
                ", residenciaProvincia='" + residenciaProvincia + '\'' +
                ", residenciaDepartamento='" + residenciaDepartamento + '\'' +
                ", cargaProvincia='" + cargaProvincia + '\'' +
                ", fechaInicioSintomas=" + fechaInicioSintomas +
                ", fechaApertura=" + fechaApertura +
                ", sepiApertura='" + sepiApertura + '\'' +
                ", fechaInternacion=" + fechaInternacion +
                ", cuidadoIntensivo=" + cuidadoIntensivo +
                ", fechaCuidadoIntensivo=" + fechaCuidadoIntensivo +
                ", fallecido=" + fallecido +
                ", fechaFallecimiento=" + fechaFallecimiento +
                ", asistenciaRespiratoriaMecanica=" + asistenciaRespiratoriaMecanica +
                ", cargaProvinciaId=" + cargaProvinciaId +
                ", origenFinanciamiento=" + origenFinanciamiento +
                ", clasificacion='" + clasificacion + '\'' +
                ", clasificacionResumen='" + clasificacionResumen + '\'' +
                ", residenciaProvinciaId=" + residenciaProvinciaId +
                ", fechaDiagnostico=" + fechaDiagnostico +
                ", residenciaDepartamentoId=" + residenciaDepartamentoId +
                ", ultimaActualizacion=" + ultimaActualizacion +
                '}';
    }


}
