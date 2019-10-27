package Ashow.service;

import Ashow.business.Artista;
import Ashow.business.Sistema;
import Ashow.business.Usuario;
import Ashow.interfac.IService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("artista")
public class ArtistaService implements IService{
    private Sistema sistema;

//    public String getAllUser() {
//        return sistema.artistaDao.ARTISTA_DAO.getAll().toString();
//    }
//
//    public String getUser(@PathParam("id") String id) {
//        Usuario user = sistema.artistaDao.ARTISTA_DAO.get(Integer.parseInt(id));
//        return user.toString();
//    }
//
//    public Response addUser(Artista user) {
//        sistema.artistaDao.ARTISTA_DAO.add(user);
//        return Response.status(Response.Status.CREATED).build();
//    }
//
//    public Response updateUser(Artista user) {
//        sistema.artistaDao.ARTISTA_DAO.update(user);
//        return Response.ok().build();
//    }
//
//    public Response deleteProduct( Integer id) {
//        System.out.println(sistema.artistaDao.ARTISTA_DAO.getAll());
//        Artista user = sistema.artistaDao.ARTISTA_DAO.get(id);
//        System.out.println();
//        sistema.artistaDao.ARTISTA_DAO.remove(user);
//        System.out.println();
//        System.out.println(sistema.artistaDao.ARTISTA_DAO.getAll());
//        System.out.println(user.toString());
//        return Response.status(202).entity("Produto " + id + " removido com sucesso.").build();
//    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String getAll() {
        return sistema.artistaDao.ARTISTA_DAO.getAll().toString();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String get(@PathParam("id") String integer) {
        return sistema.artistaDao.ARTISTA_DAO.get(Integer.parseInt(integer)).toString();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public String add(String artista) {
        return null;
    }

    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public String update(String artista) {
        return null;
    }

    @DELETE
    @Path("delete/{id}")
    @Override
    public String remove(@PathParam("id") String integer) {
        return null;
    }

}