package br.com.business;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

public class Evento implements Serializable {
    private static int maxIDEventos = 0;
    private int id;
    private int capacidadeEsperada;
    private int quantidadeArtistas;
    private double valor;
    private String dataUriFoto;
    private String estilo;
    private String nome;
    private boolean open;
    private boolean finalizado;
    private LocalDateTime dataEvento;
    private LocalDateTime dataCriacao;
    private Endereco endereco;
    private String emailContratante;
    private Set<String> emailArtistasPendente = new LinkedHashSet<>();
    private Set<String> emailArtistasConfirmados = new LinkedHashSet<>();

    public Evento() {
    }

    public Evento(String nome, int capacidadeEsperada, double valor, String estilo, LocalDateTime dataEvento, Endereco endereco, int quantidadeArtistas, String emailContratante, String dataUriFoto) {
        setDataCriacao(LocalDateTime.now());
        setDataUriFoto(dataUriFoto);
        setEmailContratante(emailContratante);
        setQuantidadeArtistas(quantidadeArtistas);
        setNome(nome);
        setCapacidadeEsperada(capacidadeEsperada);
        setValor(valor);
        setEstilo(estilo);
        setDataEvento(dataEvento);
        setEndereco(endereco);
        maxIDEventos++;
        setId(getMaxIDEventos());
        setOpen(true);
    }

    public static int getMaxIDEventos() {
        return maxIDEventos;
    }

    public static void setMaxIDEventos(int maxIDEventos) {
        Evento.maxIDEventos = maxIDEventos;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataUriFoto() {
        return dataUriFoto;
    }

    public void setDataUriFoto(String dataUriFoto) {
        this.dataUriFoto = dataUriFoto;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Set<String> getEmailArtistasPendente() {
        return emailArtistasPendente;
    }

    public void setEmailArtistasPendente(Set<String> emailArtistasPendente) {
        this.emailArtistasPendente = emailArtistasPendente;
    }

    public Set<String> getEmailArtistasConfirmados() {
        return emailArtistasConfirmados;
    }

    public void setEmailArtistasConfirmados(Set<String> emailArtistasConfirmados) {
        this.emailArtistasConfirmados = emailArtistasConfirmados;
    }

    public boolean addArtistaPendente(String emaiArtista) {
        return emailArtistasPendente.add(emaiArtista);
    }

    public boolean removerArtistaPendente(String emailArtista) {
        return emailArtistasPendente.removeIf(o -> o.equals(emailArtista));
    }

    public boolean removerArtistaConfirmado(String emailArtista) {
        return emailArtistasConfirmados.remove(emailArtista);
    }

    public boolean confirmarArtistaPendente(String emailArtista) {
        if (!removerArtistaPendente(emailArtista)) return false;
        emailArtistasConfirmados.add(emailArtista);
        return true;
    }

    public String getEmailContratante() {
        return emailContratante;
    }

    public void setEmailContratante(String emailContratante) {
        this.emailContratante = emailContratante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void fecharCandidatura() {
        setOpen(false);
    }

    public void abrirCandidatura() {
        if (!(this.dataEvento.isAfter(LocalDateTime.now()))) {
            setOpen(true);
        }
    }

    public void aumentarArtistas() {
        this.quantidadeArtistas++;
    }

    public int getCapacidadeEsperada() {
        return capacidadeEsperada;
    }

    public void setCapacidadeEsperada(int capacidadeEsperada) {
        if (capacidadeEsperada <= 0) {
            capacidadeEsperada = 1;
        }
        this.capacidadeEsperada = capacidadeEsperada;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (valor < 0) {
            valor = 0;
        }
        this.valor = valor;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public LocalDateTime getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDateTime dataEvento) {
        this.dataEvento = dataEvento;
    }

    public void setMaxIDEvento(int cont) {
        Evento.maxIDEventos = cont;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidadeArtistas() {
        return quantidadeArtistas;
    }

    public void setQuantidadeArtistas(int quantidadeArtistas) {
        this.quantidadeArtistas = quantidadeArtistas;
    }

    @Override
    public String toString() {
        return "Evento: "
                + this.id
                + "\nNome: "
                + this.nome
                + "\nEstilo: "
                + this.estilo
                + "\nValor: "
                + this.valor
                + "\nCapacidade Esperada: "
                + this.capacidadeEsperada
                + "\nEndereco:\n"
                + this.endereco
                + "\nQuantidade artistas:"
                + this.quantidadeArtistas
                + "\nContratante:"
                + this.emailContratante
                + "\nNumero De Artista pendentes:"
                + this.emailArtistasPendente.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Evento) return this.id == ((Evento) obj).id;
        else return false;
    }
}
