package org.acme;

import org.acme.model.Episode;
import org.acme.model.TvSeries;
import org.acme.proxy.EpisodeProxy;
import org.acme.proxy.TvSeriesProxy;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/tvseries")
public class GreetingResource {


    @RestClient
    TvSeriesProxy proxy;
    @RestClient
    EpisodeProxy episodeProxy;
    private List<TvSeries> tvSeriesList = new ArrayList<>();

    @GET
    //@RolesAllowed("comunidad")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("title") String title) {
        TvSeries tvSeries = proxy.get(title);
        List<Episode> episodes = episodeProxy.get(tvSeries.getId());
        tvSeriesList.add(tvSeries);

        return Response.ok(episodes).build();
    }
}