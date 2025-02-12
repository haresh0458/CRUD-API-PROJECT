// package com.example.resource;

// import com.example.model.User;
// import com.example.repository.UserRepository;
// import jakarta.inject.Inject;
// import jakarta.transaction.Transactional;
// import jakarta.ws.rs.*;
// import jakarta.ws.rs.core.MediaType;
// import jakarta.ws.rs.core.Response;

// import java.util.List;

// @Path("/users")
// @Consumes(MediaType.APPLICATION_JSON)
// @Produces(MediaType.APPLICATION_JSON)
// public class UserResource {

//     @Inject
//     UserRepository userRepository;

//     @GET
//     public List<User> getAllUsers() {
//         return userRepository.listAll();
//     }

//     @GET
//     @Path("/{id}")
//     public User getUserById(@PathParam("id") Long id) {
//         return userRepository.findById(id);
//     }

//     @POST
//     @Transactional
//     public Response createUser(User user) {
//         userRepository.persist(user);
//         return Response.status(Response.Status.CREATED).entity(user).build();
//     }

//     // @PUT
//     // @Path("/{id}")
//     // @Transactional
//     // public Response updateUser(@PathParam("id") Long id, User user) {
//     //     User existingUser = userRepository.findById(id);
//     //     if (existingUser == null) {
//     //         return Response.status(Response.Status.NOT_FOUND).build();
//     //     }
//     //     existingUser.setName(user.getName());
//     //     existingUser.setEmail(user.getEmail());
//     //     return Response.ok(existingUser).build();
//     // }

//     @PUT
// @Path("/{id}")
// @Transactional
// public Response updateUser(@PathParam("id") Long id, User user) {
//     User existingUser = userRepository.findById(id);
//     if (existingUser == null) {
//         return Response.status(Response.Status.NOT_FOUND).build();
//     }

//     // Update only the fields that are not null in the incoming request
//     if (user.getName() != null) {
//         existingUser.setName(user.getName());
//     }
//     if (user.getEmail() != null) {
//         existingUser.setEmail(user.getEmail());
//     }
//     if (user.getSchool() != null) { // Add school field logic
//         existingUser.setSchool(user.getSchool());
//     }

//     return Response.ok(existingUser).build();
// }


//     @DELETE
//     @Path("/{id}")
//     @Transactional
//     public Response deleteUser(@PathParam("id") Long id) {
//         boolean deleted = userRepository.deleteById(id);
//         if (!deleted) {
//             return Response.status(Response.Status.NOT_FOUND).build();
//         }
//         return Response.noContent().build();
//     }
// }
package com.example.resource;

import com.example.model.User;
import com.example.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserRepository userRepository;

    @GET
    public Response getAllUsers() {
        List<User> users = userRepository.listAll();
        if (users.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).entity("No users found").build();
        }
        return Response.ok(users).build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }
        return Response.ok(user).build();
    }

    @POST
    @Transactional
    public Response createUser(User user) {
        if (user == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("User data is required").build();
        }
        userRepository.persist(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateUser(@PathParam("id") Long id, User user) {
        User existingUser = userRepository.findById(id);
        if (existingUser == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }

        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getSchool() != null) {
            existingUser.setSchool(user.getSchool());
        }

        return Response.ok(existingUser).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteUser(@PathParam("id") Long id) {
        boolean deleted = userRepository.deleteById(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }
        return Response.noContent().build();
    }
}
