package net.rohisa.mascotas.restApi;

import net.rohisa.mascotas.restApi.model.MascotaResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by frojash on 1/15/18.
 */

public interface EndpointsApi {
    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia();
}
