package net.rohisa.mascotas.restApi.deserializador;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.rohisa.mascotas.pojo.Mascota;
import net.rohisa.mascotas.restApi.JsonKeys;
import net.rohisa.mascotas.restApi.model.MascotaResponse;
import net.rohisa.mascotas.restApi.model.UsuarioResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by frojash on 2/3/18.
 */

public class UsuarioDeserializador implements JsonDeserializer<UsuarioResponse> {


    @Override
    public UsuarioResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        UsuarioResponse usuarioResponse = gson.fromJson(json, UsuarioResponse.class);
        JsonArray usuarioResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        usuarioResponse = deserializarUsuarioDeJson(usuarioResponseData);

        return usuarioResponse;
    }

    private UsuarioResponse deserializarUsuarioDeJson(JsonArray usuarioResponseData) {
        UsuarioResponse usuarioResponse = new UsuarioResponse();
        if (usuarioResponseData.size()>0) {
            JsonObject usuarioResponseDataObject = usuarioResponseData.get(0).getAsJsonObject();

            String id = usuarioResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            String userInstagram = usuarioResponseDataObject.get(JsonKeys.USER_INSTAGRAM).getAsString();

            usuarioResponse.setUsuario(userInstagram);
            usuarioResponse.setId(id);
        }
        return usuarioResponse;

    }
}
