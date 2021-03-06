package br.com.service;

import br.com.business.Artista;
import br.com.business.Evento;
import br.com.business.Usuario;
import br.com.repository.Repository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("artista")
public class ArtistaService {
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Artista> getAll() {
        List<Artista> a = Repository.getINSTANCE().daoArtistas.getAll();
        return a;
    }

    @GET
    @Path("{email}")
    @Produces({MediaType.APPLICATION_JSON})
    public Artista get(@PathParam("email") String email) {
        Artista a = Repository.getINSTANCE().daoArtistas.get(email);
        return a;
    }

    @POST
    @Path("add")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean add(Artista artista) {
        System.out.println("ADD Artista:");
        Artista artistanew =
                new Artista(
                        artista.getNome(),
                        artista.getNomeArtistico(),
                        artista.getSenha(),
                        artista.getEmail(),
                        artista.getEstilo(),
                        artista.getTipoArtista(),
                        artista.getDataUriFoto());
        System.out.println(artistanew);
        boolean b = Repository.getINSTANCE().daoUsuarios.add(artistanew);
        boolean a = Repository.getINSTANCE().daoArtistas.add(artistanew);
        System.out.println(a);
        System.out.println(b);
        return b && a;
    }

    @PUT
    @Path("update")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean update(Artista artista) {
        System.out.println(artista);
        String email = artista.getEmail();
        boolean a = Repository.getINSTANCE().daoUsuarios.update(email, artista);
        boolean b = Repository.getINSTANCE().daoArtistas.update(email, artista);
        System.out.println(a);
        System.out.println(b);
        return a && b;
    }

    @DELETE
    @Path("delete/{email}")
    public boolean remove(@PathParam("email") String email) {
        System.out.println("DELETE Artista:");
        List<Usuario> artistas =
                Repository.getINSTANCE().daoUsuarios.getAll().stream()
                        .filter(a -> a.getEmail().equals(email))
                        .filter(a -> a instanceof Artista)
                        .collect(Collectors.toList());
        if (!artistas.isEmpty()) {
            Artista artista = ((Artista) artistas.get(0));
            System.out.println(artista);
            if (artista != null) {
                boolean a = Repository.getINSTANCE().daoUsuarios.remove(artista);
                boolean b = Repository.getINSTANCE().daoArtistas.remove(artista);
                System.out.println(a);
                System.out.println(b);
                return a && b;
            } else return false;
        } else return false;
    }

    @POST
    @Path("log")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean log(LoginUsuario lodDados) {
        Artista a = Repository.getINSTANCE().daoArtistas.get(lodDados.getEmail());
        if (a != null) {
            return a.isSenha(lodDados.getSenha());
        } else return false;
    }

    @GET
    @Path("{email}/eventos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Evento> getEventos(@PathParam("email") String email) {
        List<Evento> e = new ArrayList();
        Repository.getINSTANCE().daoArtistas.get(email).getEventos().forEach(o -> e.add(Repository.getINSTANCE().daoEventos.get(o)));
        return e;
    }

    @GET
    @Path("filter/{style}/{min}/{max}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Artista> filter(@PathParam("style") String estiloURL, @PathParam("min") double min, @PathParam("max") double max){
        List<Artista> filtrado = Repository.getINSTANCE().daoArtistas.getAll();
        List<Artista> aux = new ArrayList<Artista>();
        if(!(estiloURL.equals("@"))){
            filtrado.stream().filter(e -> e.getEstilo() != null && e.getEstilo().toLowerCase().contains(estiloURL.toLowerCase()))
                    .filter(e -> e.getValorPadrao() +1 >= min && e.getValorPadrao() <= max)
                    .forEach(e -> aux.add(e));
            filtrado = aux;
        } else{
            filtrado.stream()
                    .filter(e -> e.getValorPadrao() +1> min && e.getValorPadrao() <= max)
                    .forEach(e -> aux.add(e));
            filtrado = aux;
        }
        return filtrado;
    }
}
